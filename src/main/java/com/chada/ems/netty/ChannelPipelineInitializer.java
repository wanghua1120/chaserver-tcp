/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.netty;

import com.chada.ems.netty.decoder.ChaLengthFieldBasedFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
@Component
public class ChannelPipelineInitializer extends ChannelInitializer<SocketChannel> {
    private static final EventExecutorGroup group = new DefaultEventExecutorGroup(2);
    public static int LENGTH_FIELD_OFFSET = 2;
    public static int LENGTH_FIELD_LENGTH = 4;
    public static int LENGTH_ADJUSTMENT = 4;  // 有的数据包每个指令后带有 \n 分隔符，这时调整长度就为 5 了，而不是4

    @Autowired
    private HttpPackageHandler httpPackageHandler;
    @Autowired
    private GenericPackageHandler genericPackageHandler;

    public ChannelPipelineInitializer() throws InterruptedException {
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 管道注册 handler
        ChannelPipeline pipeline = socketChannel.pipeline();

        // IdleStateHandler心跳机制,如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast("idleStateHandler", new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));

        /*
        自定义Hander,可用于处理耗时操作，不阻塞IO处理线程。
        把一些比较耗时的操作（如存储、入库）等操作放在BussinessHandler中进行，因为我们为它单独
         分配了EventExecutorGroup 线程池执行，所以说即使这里发生阻塞，也不会影响TCPServerHandler中数据的接收。
         */
        pipeline.addLast(group, httpPackageHandler);

        /*
        不定长包解码器。
        通过指定长度来标识整包消息，这样就可以自动的处理黏包和半包消息，只要传入正确的参数，就可以轻松解决“读半包”的问题。
        注意：
        （1）如果 pipeline 前已有 StringDecoder 解码器，那么 ChaLengthFieldBasedFrameDecoder 这个解码器就失效了。
        （2）虽然设置了 LENGTH_ADJUSTMENT = 4; 但实际上这个值并不固定，有的数据包每个指令后带有 \n 分隔符，这时调整长度就为 5 了，这会导致解码错误，
             因此放弃使用 ChaLengthFieldBasedFrameDecoder ，改
         */
        //pipeline.addLast(new ChaLengthFieldBasedFrameDecoder(1024 * 1024, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, LENGTH_FIELD_OFFSET + LENGTH_FIELD_LENGTH));

        /*
        分隔符器解码。
        每个指令后面必须带 '\n'，否则分隔符解码器解码失败，目前来看，这种解码器比之前采用的 ChaLengthFieldBasedFrameDecoder 好多了，不存在 ADJUSTMENT 问题。
         */
        ByteBuf delimiter = Unpooled.copiedBuffer("\n".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024 * 1024, delimiter));

        // 通过 LengthFieldPrepender 可以将待发送消息的长度写入到 ByteBuf 的前4个字节，编码后的消息组成为长度字段+原消息的方式。
        //pipeline.addLast(new LengthFieldPrepender(4));


        // netty基于分割符的自带解码器，根据提供的分隔符解析报文，这里是0x7e;1024表示单条消息的最大长度，解码器在查找分隔符的时候，达到该长度还没找到的话会抛异常
//        pipeline.addLast(
//                new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[] { 0x7e }),
//                        Unpooled.copiedBuffer(new byte[] { 0x7e })));
        //自定义编解码器
//        pipeline.addLast(
//                new MessagePacketDecoder(),
//                new MessagePacketEncoder()
//        );

        // 编码通道处理
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 转码通道处理
        //pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

        // 自定义Hadler。由于使用了 pipeline，因此是有先后顺序的，先设置的先处理，因此要想这个Handler能使用 LengthFieldBasedFrameDecoder 解码器，必须将 Handler 定义放在解码器定义后面。
        pipeline.addLast(group, genericPackageHandler);
    }
}
