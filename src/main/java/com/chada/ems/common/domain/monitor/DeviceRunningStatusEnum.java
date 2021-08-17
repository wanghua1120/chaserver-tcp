/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import java.util.HashMap;
import java.util.Map;

/**
 * 污染治理设施运行状态
 *
 * @author Hua Wang
 *         Created On: 2019/5/18 22:59
 */
public enum DeviceRunningStatusEnum {
    /**
     * 关闭
     */
    CLOSE("0"),
    /**
     * 运行
     */
    RUNNING("1"),
    /**
     * 校准
     */
    CALIBRATION("2"),
    /**
     * 维护
     */
    MAINTENANCE("3"),
    /**
     * 报警
     */
    ALARM("4"),
    /**
     * 反吹
     */
    BLOW_BACK("5");

    private String code;
    private static Map<String, DeviceRunningStatusEnum> codeMap = new HashMap();

    static {
        for (DeviceRunningStatusEnum item : DeviceRunningStatusEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    DeviceRunningStatusEnum(String code) {
        this.code = code;
    }

    public static DeviceRunningStatusEnum lookup(String code) {
        return codeMap.get(code);
    }
}