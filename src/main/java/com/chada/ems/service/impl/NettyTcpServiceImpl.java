/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.service.impl;

import com.chada.ems.netty.ChannelPipelineInitializer;
import com.chada.ems.service.NettyTcpService;
import com.chada.ems.common.util.StringUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
@Slf4j
@Service
public class NettyTcpServiceImpl implements NettyTcpService {
    public static final String SERVER_NAME = "NettyTcpServer";

    @Value("${chaserver-tcp.server.port:8889}")
    public int TCP_PORT;

    private Lock lock = new ReentrantLock();

    @Autowired
    private ChannelPipelineInitializer channelPipelineInitializer;

    private volatile boolean isRunning = false;

    // 处理Accept连接事件的线程，这里线程数设置为1即可，netty处理链接事件默认为单线程，过度设置反而浪费cpu资源
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    // 处理hadnler的工作线程，其实也就是处理IO读写 。线程数据默认为 CPU 核心数乘以2
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    @PostConstruct
    public void run() {
        startServer();
    }

    @Override
    public void startServer() {
        lock.lock();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup, workerGroup);
            sb.channel(NioServerSocketChannel.class);
            sb.childHandler(channelPipelineInitializer);
            // BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            // 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
            sb.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = sb.bind(TCP_PORT).sync();
            if (channelFuture.isSuccess()) {
                isRunning = true;
                log.info(">>> TCP服务启动成功，正在监听端口 " + String.valueOf(TCP_PORT) + "...");
            } else {
                throw new RuntimeException(">>> TCP服务启动失败。");
            }
        } catch (InterruptedException ex) {
            log.error(StringUtil.getStackTrace(ex));
            throw new RuntimeException(">>> TCP服务启动失败。");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void stopServer() {
        if (!this.isRunning) {
            throw new IllegalStateException(">>> " + SERVER_NAME + " 未启动 .");
        }

        lock.lock();
        try {
            Future<?> future = this.workerGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                log.error(">>> workerGroup 无法正常停止:{}", future.cause());
            }

            future = this.bossGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                log.error(">>> bossGroup 无法正常停止:{}", future.cause());
            }
        } catch (InterruptedException e) {
            log.error(StringUtil.getStackTrace(e));
        } finally {
            lock.unlock();
        }
        log.info(">>> TCP服务已经停止。");
    }
}
