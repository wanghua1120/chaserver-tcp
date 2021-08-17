/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.core.jpa.repository.impl;

import com.chada.ems.common.domain.base.AuditBaseEntity;
import com.chada.ems.common.domain.base.BaseEntity;
import com.chada.ems.common.exception.support.ChaRuntimeException;
import com.chada.ems.core.jpa.repository.ChaRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Hua Wang
 *         Created On: 2019/4/13 15:53
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ChaRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements ChaRepository<T, ID> {

    protected final JpaEntityInformation<T, ID> entityInformation;
    protected final EntityManager em;

    @Getter
    protected Root root;
    @Getter
    protected CriteriaBuilder cb;
    @Getter
    protected CriteriaQuery cq;

    public ChaRepositoryImpl(JpaEntityInformation<T, ID> metadata, EntityManager em) {
        super(metadata, em);
        this.entityInformation = metadata;
        this.em = em;

        cb = em.getCriteriaBuilder();
        cq = cb.createQuery();
        root = cq.from(entityInformation.getJavaType());
    }

    @Override
    public Optional<T> findById(ID id) {
        return super.findById(id);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return findAllById(ids, null);
    }

    @Override
    public List<T> findAll() {
        return this.findAll((Sort) null);
    }

    @Override
    public List<T> findAll(Sort sort) {
        PageRequest pageRequest = PageRequest.of(0, 1, sort);
        Page<T> page = findAll(pageRequest);
        return page.getContent();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        boolean isPaging = pageable.getPageSize() > 1;

        //1.获取 total
        Long total = 0l;
        if (isPaging) {
            cq.select(cb.count(root));
            total = (Long) em
                    .createQuery(cq)
                    .getSingleResult();
        }

        //2.公用排序（totol 计算没必要用）
        List<Order> orderList = new ArrayList<>();
        Sort sort = pageable.getSort();
        if (sort != null) {
            Iterator<Sort.Order> orderIterator = sort.iterator();
            while (orderIterator.hasNext()) {
                Sort.Order order = orderIterator.next();
                String prop = order.getProperty();
                Sort.Direction direction = order.getDirection();
                if (Sort.Direction.ASC.equals(direction)) {
                    orderList.add(cb.asc(root.get(prop)));
                } else if (Sort.Direction.DESC.equals(direction)) {
                    orderList.add(cb.desc(root.get(prop)));
                }
            }
            if (orderList.size() > 0) cq.orderBy(orderList);
        } else {
            cq.orderBy(cb.desc(root.get("id")));
        }

        //3.获取 List<T>
        cq.select(root);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        if (isPaging) {
            typedQuery = typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            typedQuery = typedQuery.setMaxResults(pageable.getPageSize());
        }
        List<T> results = typedQuery.getResultList();

        return new PageImpl<T>(results, pageable, total);
    }

    @Transactional
    @Override
    public <S extends T> S save(S transientEntity) {
        S result = null;
        Assert.notNull(transientEntity, "The object to be saved can not be empty.");
        if (BaseEntity.class.isAssignableFrom(transientEntity.getClass())) {
            BaseEntity traBaseEntity = transientEntity;
            ID id = (ID) traBaseEntity.getId();
            if (id != null) {  // update
                S persistentEntity = (S) this.findById(id).orElse(null);
                Assert.notNull(persistentEntity, "The record has been deleted.");

                BaseEntity perBaseEntity = persistentEntity;
                if (!perBaseEntity.getVersion().equals(traBaseEntity.getVersion()))
                    throw new ChaRuntimeException("The record was updated or deleted by another user.");

                AuditBaseEntity.mergeAuditFields(traBaseEntity, perBaseEntity);

                result = em.merge((S) perBaseEntity);
            } else {  // add
                Date now = new Date();
                transientEntity.setCreatedTime(now);
                transientEntity.setLastModifiedTime(now);
                em.persist(transientEntity);
                result = transientEntity;
            }
        }

        return result;
    }

    @Transactional
    @Override
    public <S extends T> S saveAndFlush(S transientEntity) {
        S result = this.save(transientEntity);
        super.flush();
        return result;
    }

    @Transactional
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> transientEntities) {
        Assert.notNull(transientEntities, "The given Iterable of entities not be null!");
        List<S> result = new ArrayList<>();
        for (S entity : transientEntities) {
            result.add(this.save(entity));
        }
        return result;
    }

    @Override
    public boolean existsById(ID id) {
        return this.findById(id).isPresent();
    }

    @Override
    public long count() {
        cq.select(cb.count(root));
        Long total = (Long) em
                .createQuery(cq)
                .getSingleResult();

        return total;
    }

    //----------------------------------------------------
    // Override ChaRepository
    //----------------------------------------------------

    @Override
    public List<T> findList(Predicate pred, EntityGraph<T> eg, Sort sort) {

        List<Order> orderList = new ArrayList<>();
        if (sort != null) {
            Iterator<Sort.Order> orderIterator = sort.iterator();
            while (orderIterator.hasNext()) {
                Sort.Order order = orderIterator.next();
                String prop = order.getProperty();
                Sort.Direction direction = order.getDirection();
                if (Sort.Direction.ASC.equals(direction)) {
                    orderList.add(cb.asc(root.get(prop)));
                } else if (Sort.Direction.DESC.equals(direction)) {
                    orderList.add(cb.desc(root.get(prop)));
                }
            }
            cq.orderBy(orderList);
        } else {
            cq.orderBy(cb.desc(root.get("id")));
        }

        if (pred != null) {
            cq.select(root).where(pred);
        } else {
            cq.select(root);
        }
        TypedQuery<T> tTypedQuery = em.createQuery(cq);
        if (eg != null) {
            tTypedQuery = tTypedQuery.setHint("javax.persistence.loadgraph", eg);
        }

        List<T> results = tTypedQuery.getResultList();

        return results;
    }

    @Override
    public T findOne(Predicate pred, EntityGraph<T> eg) {
        List<T> list = findList(pred, eg, null);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public T findById(ID id, EntityGraph<T> eg) {
        Predicate predicate = cb.equal(root.get("id"), id);
        return findOne(predicate, eg);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids, EntityGraph<T> eg) {
        Assert.notNull(ids, "The given Iterable of Id's must not be null!");

        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        Predicate predicate = null;
        for (ID id : ids) {
            Predicate idPred = cb.equal(root.get("id"), id);
            if (predicate == null)
                predicate = idPred;
            else
                predicate = cb.or(predicate, idPred);
        }
        List list = findList(predicate, eg, null);

        return list;
    }
}
