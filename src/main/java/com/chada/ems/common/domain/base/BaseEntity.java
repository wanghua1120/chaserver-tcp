/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Hua Wang
 *         Created On: 2019/4/13 15:07
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements IdHolder, Serializable {
    protected static final String TABLE_PREFIX = "e_";
    public static final String ID_GENERATOR_TABLE_NAME = TABLE_PREFIX + "id_generator";

//    @Id
//    @Column(length = 32)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
//    private Long id;

    @Version
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer version = 0;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastModifiedTime;
}


