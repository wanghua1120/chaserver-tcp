/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.base;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 15:07
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditBaseEntity extends BaseEntity {

    @CreatedBy
    protected Long createdBy;

    @LastModifiedBy
    protected Long lastModifiedBy;

    public static <T> void mergeAuditFields(T source, T target) {
        if (source == null || target == null) return;
        Assert.isInstanceOf(AuditBaseEntity.class, source, "Type check failure.");
        Assert.isInstanceOf(AuditBaseEntity.class, target, "Type check failure.");

        AuditBaseEntity src = (AuditBaseEntity) source;
        AuditBaseEntity tar = (AuditBaseEntity) target;

        src.setCreatedTime(tar.getCreatedTime());
        src.setCreatedBy(tar.getCreatedBy());
        src.setLastModifiedTime(null);
        src.setLastModifiedBy(null);
        BeanUtils.copyProperties(source, target);
    }
}
