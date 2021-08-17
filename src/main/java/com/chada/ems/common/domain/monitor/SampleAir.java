/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import com.chada.ems.common.domain.factor.AirFactorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.chada.ems.common.domain.base.BaseEntity.ID_GENERATOR_TABLE_NAME;
import static com.chada.ems.common.domain.monitor.SampleAir.TABLE_NAME;

/**
 * 气（污染物）
 *
 * @author Hua Wang
 * Created On: 2019/4/18 0:20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = TABLE_NAME)
@TableGenerator(name = TABLE_NAME, table = ID_GENERATOR_TABLE_NAME, pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = TABLE_NAME, allocationSize = 1)
public class SampleAir extends AbstractPollutant<AirFactorEnum> {
    public static final String TABLE_NAME = TABLE_PREFIX + "sample_air";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;
}