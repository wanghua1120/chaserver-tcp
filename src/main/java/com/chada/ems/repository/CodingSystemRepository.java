/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.repository;

import com.chada.ems.common.domain.traffic.CodingSystem;
import com.chada.ems.core.jpa.repository.ChaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author Hua Wang
 * Created On: 2019/4/16 16:22
 */
@Repository
public interface CodingSystemRepository extends ChaRepository<CodingSystem, Long> {
    CodingSystem findByCode(String code);
}