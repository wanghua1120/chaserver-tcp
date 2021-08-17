/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.core.jpa.repository.impl;

import com.chada.ems.common.domain.base.BaseEntity;
import com.chada.ems.core.jpa.repository.GenericEntityReposity;
import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 15:53
 */
@Primary
@Repository
public class GenericEntityReposityImpl implements GenericEntityReposity {
    @Getter
    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<?> findByIds(Collection<String> ids, Class entityClass) {
        if (ids == null || ids.size() == 0) {
            return Collections.emptyList();
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entityClass);

        Path<String> exp = root.get("id");
        Predicate predicate = exp.in(ids);
        cq = cq.where(predicate);


        List result = em.createQuery(cq).getResultList();
        return result;
    }

    @Override
    public List<?> find(Map<String, Object> params, Class entityClass) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();
        for (String key : params.keySet()) {
            Predicate pred = cb.equal(root.get(key), params.get(key));
            predicates.add(pred);
        }

        cq = cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        List<?> results = em.createQuery(cq).getResultList();

        return results;
    }

    @Override
    public <T> T merge(T entity) {
        return em.merge(entity);
    }

    @Override
    public void persist(BaseEntity entity) {
        em.persist(entity);
    }

    @Override
    public boolean exists(Class clazz) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(clazz);

        criteriaQuery.select(criteriaBuilder.count(root));
        Long total = (Long) em.createQuery(criteriaQuery).getSingleResult();

        return total > 0;
    }
}
