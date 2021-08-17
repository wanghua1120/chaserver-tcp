/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.core.jpa.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 15:50
 */
@NoRepositoryBean
@Cacheable(value = "springCache")
public interface ChaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Root getRoot();

    CriteriaBuilder getCb();

    CriteriaQuery getCq();

    List<T> findList(Predicate pred, EntityGraph<T> eg, Sort sort);

    T findOne(Predicate pred, EntityGraph<T> eg);

    T findById(ID id, EntityGraph<T> eg);

    List<T> findAllById(Iterable<ID> ids, EntityGraph<T> eg);

}
