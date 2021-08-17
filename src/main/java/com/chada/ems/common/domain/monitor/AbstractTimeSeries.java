/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import com.chada.ems.common.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Hua Wang
 *         Created On: 2019/4/18 11:07
 */
@Data
@MappedSuperclass
public abstract class AbstractTimeSeries extends BaseEntity {
    /**
     * 数据时间
     * <p>
     * 注：若要查询 2019-05-12 10:00:00 到 2019-05-12 11:00:00 的数据，
     * Sql 需要这样写“where dataTime gt 2019-05-12 10:00:00 and createdTime le 2019-05-12 11:00:00”
     */
    protected Date dataTime;
    /**
     * 通讯包 外键
     */
    protected long trafficPackageId;
    /**
     * 设备唯一编号（冗余）
     */
    @NotBlank
    protected String mn;
    /**
     * ST 系统编码（冗余）
     */
    private Long codingSystemId;
    /**
     * CN 命令编码（冗余）
     */
    @NotBlank
    private Long codingCommandId;
}
