/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

/**
 * 监测仪器数据标记
 *
 * @author Hua Wang
 * Created On: 2019/5/6 23:26
 */
public enum FlagEnum {
    /**
     * 在线监控（监测）仪器仪表工作正常
     */
    N,
    /**
     * 在线监控（监测）仪器仪表停运
     */
    F,
    /**
     * 在线监控（监测）仪器仪表处于维护期间产生的数据
     */
    M,
    /**
     * 手工输入的设定值
     */
    S,
    /**
     * 在线监控（监测）仪器仪表故障
     */
    D,
    /**
     * 在线监控（监测）仪器仪表处于校准状态
     */
    C,
    /**
     * 在线监控（监测）仪器仪表采样数值超过测量上限
     */
    T,
    /**
     * 在线监控（监测）仪器仪表与数采仪通讯异常
     */
    B
}