/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.math.BigDecimal;

import static com.chada.ems.common.domain.base.BaseEntity.ID_GENERATOR_TABLE_NAME;
import static com.chada.ems.common.domain.monitor.SampleDeviceRunningStatus.TABLE_NAME;

/**
 * 设备运行状态
 *
 * @author Hua Wang
 *         Created On: 2019/5/18 12:20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = TABLE_NAME)
@TableGenerator(name = TABLE_NAME, table = ID_GENERATOR_TABLE_NAME, pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = TABLE_NAME, allocationSize = 1)
public class SampleDeviceRunningStatus extends AbstractTimeSeries {
    public static final String TABLE_NAME = TABLE_PREFIX + "sample_device_running_status";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;

    /**
     * 运行状态的实时采样值
     */
    @Enumerated(EnumType.STRING)
    private DeviceRunningStatusEnum sbRs;
    /**
     * 运行状态的实时采样值 对应的污染治理设备号
     */
    private String sbRsDeviceNumber;
    /**
     * 一日内的运行时间
     */
    @Column(precision = 23, scale = 6)
    private BigDecimal sbRt;
    /**
     * 一日内的运行时间 对应的污染治理设备号
     */
    private String sbRtDeviceNumber;
}