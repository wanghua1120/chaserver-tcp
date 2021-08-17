/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求命令返回结果定义
 *
 * @author Hua Wang
 * Created On: 2019/4/18 10:43
 */
public enum RequestCommandReturnEnum {
    /**
     * 准备执行请求
     */
    PRE_EXECUTE_REQUEST("1"),
    /**
     * 请求被拒绝
     */
    REQUEST_REFUSED("2"),
    /**
     * 密码错误
     */
    PW_ERROR("3"),
    /**
     * 设备编号错误
     */
    MN_ERROR("4"),
    /**
     * 系统编码错误
     */
    ST_ERROR("5"),
    /**
     * Flag 错误
     */
    FLAG_ERROR("6"),
    /**
     * 请求编码错误
     */
    QN_ERROR("7"),
    /**
     * 命令编码错误
     */
    CN_ERROR("8"),
    /**
     * CRC 校验错误
     */
    CRC_ERROR("9"),
    /**
     * 未知错误
     */
    UNKNOW_ERROR("100");

    private String code;
    private static Map<String, RequestCommandReturnEnum> codeMap = new HashMap();

    static {
        for (RequestCommandReturnEnum item : RequestCommandReturnEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    RequestCommandReturnEnum(String code) {
        this.code = code;
    }

    public static RequestCommandReturnEnum lookup(String code) {
        return codeMap.get(code);
    }
}