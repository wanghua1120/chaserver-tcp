/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

import com.chada.ems.common.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.beans.ConstructorProperties;
import java.util.Date;

import static com.chada.ems.common.domain.base.BaseEntity.ID_GENERATOR_TABLE_NAME;
import static com.chada.ems.common.domain.traffic.CodingSystem.TABLE_NAME;

/**
 * 系统编码
 *
 * @author Hua Wang
 *         Created On: 2019/4/16 15:33
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Entity
@Table(name = TABLE_NAME, uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
@TableGenerator(name = TABLE_NAME, table = ID_GENERATOR_TABLE_NAME, pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = TABLE_NAME, allocationSize = 1)
public class CodingSystem extends BaseEntity {
    public static final String TABLE_NAME = TABLE_PREFIX + "coding_system";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;

    @NotBlank
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CodingSystemTypeEnum type;

    @NotBlank
    private String name;

    private String description;

    private CodingSystem(Long id, @NotBlank String code, @NotNull CodingSystemTypeEnum type, @NotBlank String name, String description) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.name = name;
        this.description = description;

        Date now = new Date();
        this.createdTime = now;
        this.lastModifiedTime = now;
    }

    public static CodingSystem of(Long id, @NotBlank String code, @NotNull CodingSystemTypeEnum type, @NotBlank String name, String description) {
        return new CodingSystem(id, code, type, name, description);
    }
}