/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.core.jpa.repository;

import com.chada.ems.common.domain.base.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 15:50
 */
public interface GenericEntityReposity {

    List<?> findByIds(Collection<String> ids, Class entityClass);

    List<?> find(Map<String, Object> params, Class entityClass);

    <T> T merge(T entity);

    void persist(BaseEntity entity);

    boolean exists(Class clazz);

}
