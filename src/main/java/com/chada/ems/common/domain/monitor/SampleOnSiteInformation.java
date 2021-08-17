/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import com.chada.ems.common.domain.factor.OneSiteInformationFactorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static com.chada.ems.common.domain.base.BaseEntity.ID_GENERATOR_TABLE_NAME;
import static com.chada.ems.common.domain.monitor.SampleOnSiteInformation.TABLE_NAME;

/**
 * 现场端信息
 *
 * @author Hua Wang
 *         Created On: 2019/4/18 13:51
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = TABLE_NAME)
@TableGenerator(name = TABLE_NAME, table = ID_GENERATOR_TABLE_NAME, pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = TABLE_NAME, allocationSize = 1)
public class SampleOnSiteInformation extends AbstractSampleGeneric<OneSiteInformationFactorEnum> {
    public static final String TABLE_NAME = TABLE_PREFIX + "sample_onsite_information";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;

    /**
     * 现场端信息编码
     */
    private String infoId;

    /**
     * 现场端信息
     */
    @Column(length = 10240)
    private String infoValue;

}