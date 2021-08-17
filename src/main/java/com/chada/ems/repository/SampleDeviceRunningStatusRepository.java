/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.repository;

import com.chada.ems.common.domain.monitor.SampleDeviceRunningStatus;
import com.chada.ems.core.jpa.repository.ChaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hua Wang
 *         Created On: 2019/5/18 21:32
 */
@Repository
public interface SampleDeviceRunningStatusRepository extends ChaRepository<SampleDeviceRunningStatus, Long> {

}