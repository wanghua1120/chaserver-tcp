/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.configuration;

import com.chada.ems.core.jpa.repository.impl.ChaRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 20:22
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "userAuditor")
@EntityScan("com.chada.ems.common.domain")
@EnableJpaRepositories(basePackages = {"com.chada.ems.core.jpa.repository", "com.chada.ems.repository"},
repositoryBaseClass = ChaRepositoryImpl.class)
public class SpringJpaConfiguration {

}
