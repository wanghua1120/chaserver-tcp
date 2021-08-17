/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.netty;

import com.chada.ems.common.domain.traffic.PackageTypeEnum;
import com.chada.ems.common.util.StringUtil;
import com.chada.ems.service.TrafficPackageService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author Hua Wang
 *         Created On: 2019/4/9 18:07
 */
@Slf4j
@Sharable
@Component
public class HttpPackageHandler extends SimpleChannelInboundHandler<String> {
    @Autowired
    private TrafficPackageService trafficPackageService;
    @Autowired
    private PackageReaderHelper packageReaderHelper;

    /**
     * 所有的活动用户
     */
    public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String rawText = buf.toString(Charset.forName("utf-8"));
        log.debug(">>> rawText：" + rawText);

        if (packageReaderHelper.isHttpPackage(rawText)) {
            channelRead0(ctx, rawText);
        } else {
            // 让包继续在 pipeline 中往下处理
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        String channelId = channel.id().asLongText();

        Pair<String, String> remoteIpAndPort = getRemoteIpAndPort(channel);
        String remoteIp = remoteIpAndPort == null ? null : remoteIpAndPort.getFirst();
        String remotePort = remoteIpAndPort == null ? null : remoteIpAndPort.getSecond();

        trafficPackageService.saveTrafficPackages(msg, remoteIp, remotePort, PackageTypeEnum.HTTP_PACKAGE);

        // 收到及发送，这里如果没有writeAndFlush，上面声明的ByteBuf需要ReferenceCountUtil.release主动释放
        //ctx.writeAndFlush(receivedMsg);
        ReferenceCountUtil.release(msg);
    }

    /**
     * 处理新加的消息通道
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        for (Channel ch : group) {
            if (ch == channel) {
                ch.writeAndFlush("[" + channel.remoteAddress() + "] 进来");
            }
        }
        group.add(channel);
    }

    /**
     * 处理退出消息通道
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        for (Channel ch : group) {
            if (ch == channel) {
                ch.writeAndFlush("[" + channel.remoteAddress() + "] 退出");
            }
        }
        group.remove(channel);
    }

    /**
     * 在建立连接时发送消息
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception 抛出异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        boolean active = channel.isActive();
        if (active) {
            log.info(">>> " + getFormattedRemoteAddress(channel) + " 上线");
        } else {
            log.info(">>> " + getFormattedRemoteAddress(channel) + " 下线");
        }
        ctx.writeAndFlush("TCP服务器：欢迎");
    }

    /**
     * 退出时发送消息
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception 抛出异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!channel.isActive()) {
            log.info(">>> " + getFormattedRemoteAddress(channel) + " 下线");
        } else {
            log.info(">>> " + getFormattedRemoteAddress(channel) + " 在线");
        }
    }

    /**
     * 异常捕获
     *
     * @param ctx ChannelHandlerContext
     * @param e   Throwable
     * @throws Exception 抛出异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        log.error(StringUtil.getStackTrace(e));
        Channel channel = ctx.channel();
        log.info(">>> " + getFormattedRemoteAddress(channel) + " 异常退出");
        ctx.close().sync();
    }


    private String getFormattedRemoteAddress(Channel channel) {
        String remoteAddress = channel.remoteAddress().toString();
        String myaddr = "[" + remoteAddress.replace("/", "") + "]";
        return myaddr;
    }

    private Pair<String, String> getRemoteIpAndPort(Channel channel) {
        Pair<String, String> result = null;

        try {
            String remoteAddress = channel.remoteAddress().toString();
            remoteAddress = remoteAddress.replace("/", "");
            String[] myArray = remoteAddress.split(":");
            result = Pair.of(myArray[0], myArray[1]);
        } catch (Throwable ex) {
            log.error(StringUtil.getStackTrace(ex));
        }

        return result;
    }

}