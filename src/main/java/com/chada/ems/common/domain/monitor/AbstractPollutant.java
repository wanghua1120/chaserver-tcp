/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Hua Wang
 *         Created On: 2019/4/19 10:56
 */
@Data
@MappedSuperclass
public abstract class AbstractPollutant<T> extends AbstractSampleGeneric<T> {
    /**
     * 污染物采样时间
     */
    protected Date sampleTime;
    /**
     * 污染物实时采样数据
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal rtd;
    /**
     * 污染物指定时间内最小值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal min;
    /**
     * 污染物指定时间内平均值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal avg;
    /**
     * 污染物指定时间内最大值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal max;
    /**
     * 污染物实时采样折算数据
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal zsRtd;
    /**
     * 污染物指定时间内最小折算值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal zsMin;
    /**
     * 污染物指定时间内平均折算值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal zsAvg;
    /**
     * 污染物指定时间内最大折算值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal zsMax;
    /**
     * 监测仪器数据标记
     */
    @Enumerated(EnumType.STRING)
    protected FlagEnum flag;
    /**
     * 监测仪器扩充数据标记
     */
    @Column(length = 4)
    protected String eFlag;
    /**
     * 污染物指定时间内累计值
     */
    @Column(precision = 23, scale = 6)
    protected BigDecimal cou;
    /**
     * 在线监控（监测）仪器仪表编码
     */
    protected String sn;

    /**
     * 污水处理过程中同一工艺中使用的相同设备的编号，取值范围为 01~99
     * <p>
     * 注：此字段仅用于“污水排放监测因子” 和“烟气排放监测因子”两个工况因子。
     */
    protected String workingConditionDeviceNumber;

    /**
     * alarm（HJ212 未定义）
     */
    protected String alarm;
    /**
     * state（HJ212 未定义）
     */
    protected String state;
}
