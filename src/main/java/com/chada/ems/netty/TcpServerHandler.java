/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
@Deprecated
public class TcpServerHandler extends ChannelInboundHandlerAdapter {
    public TcpServerHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object source) throws Exception {
        ByteBuf receivedMsg = (ByteBuf) source;
        // 收到及发送，这里如果没有writeAndFlush，上面声明的ByteBuf需要ReferenceCountUtil.release主动释放
        ctx.writeAndFlush(receivedMsg);
    }
}
