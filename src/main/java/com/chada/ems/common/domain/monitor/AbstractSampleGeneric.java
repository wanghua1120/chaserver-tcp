/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import com.chada.ems.common.domain.factor.FactorTypeEnum;
import com.chada.ems.common.domain.traffic.ExecutionResultDefinitionEnum;
import com.chada.ems.common.domain.traffic.RequestCommandReturnEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Hua Wang
 *         Created On: 2019/4/19 10:56
 */
@Data
@MappedSuperclass
public abstract class AbstractSampleGeneric<T> extends AbstractTimeSeries implements FactorHolder<T> {

    @NotNull
    @Enumerated(EnumType.STRING)
    protected T factor;

    /**
     * 系统时间
     */
    protected Date systemTime;
    /**
     * 请求回应代码
     */
    @Enumerated(EnumType.STRING)
    protected RequestCommandReturnEnum qnRtn;
    /**
     * 执行结果回应代码
     */
    @Enumerated(EnumType.STRING)
    protected ExecutionResultDefinitionEnum exeRtn;
    /**
     * 实时采样数据上报间隔（单位：秒）
     */
    protected Integer rtdInterval;
    /**
     * 分钟数据上报间隔
     */
    protected Integer minInterval;
    /**
     * 数采仪开机时间
     */
    protected Date restartTime;
    /**
     * 污染因子类型
     */
    @Enumerated(EnumType.STRING)
    protected FactorTypeEnum polType;
    /**
     * 污染因子编码，
     * <p>
     * 注：
     * 该字段储存的是 WaterFactorEnum/AirFactorEnum 等枚举字符串，而不是编码。
     * 该字段应与 FactorTypeEnum 配合使用。
     */
    protected String polId;
    /**
     * 开始时间
     */
    protected Date beginTime;
    /**
     * 截止时间
     */
    protected Date endTime;
    /**
     * 新密码
     */
    protected String newPw;
    /**
     * 超时时间
     */
    protected Integer overTime;
    /**
     * 重发次数
     */
    protected Integer reCount;
    /**
     * 采样瓶编
     */
    protected String vaseNo;
    /**
     * 设备采样起始时间（格式：hhmmss）
     */
    protected String cStartTime;
    /**
     * 采样周期
     */
    protected Integer cTime;
    /**
     * 出样时间
     */
    protected Integer sTime;
}