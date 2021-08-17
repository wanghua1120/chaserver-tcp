/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.core.jpa.repository;

import com.chada.ems.common.domain.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.persistence.Table;
import java.text.MessageFormat;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 15:53
 */
@Slf4j
public abstract class RepoUtil<T extends BaseEntity> {
    public static String getEntityTableName(Class cls) {
        Table tableAnnotation = (Table) cls.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            Assert.notNull(tableAnnotation, MessageFormat.format("There is no @Table annotation in the entity '{0}'" +
                    ".", cls.getSimpleName()));
        }
        String tableName = tableAnnotation.name();
        return tableName;
    }
}
