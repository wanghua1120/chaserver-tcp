/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableCaching
public class EmsApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EmsApplication.class, args);
    }

}
