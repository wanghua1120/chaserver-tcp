/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.service;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
public interface NettyTcpService {
    void startServer();

    void stopServer();
}
