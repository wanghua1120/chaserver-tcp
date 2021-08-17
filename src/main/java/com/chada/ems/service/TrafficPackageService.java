/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.service;

import com.chada.ems.common.domain.traffic.PackageTypeEnum;

/**
 * @author Hua Wang
 * Created On: 2019/4/23 22:24
 */
public interface TrafficPackageService {
    void saveTrafficPackages(String rawText, String remoteIp, String remotePort, PackageTypeEnum packageType);
}
