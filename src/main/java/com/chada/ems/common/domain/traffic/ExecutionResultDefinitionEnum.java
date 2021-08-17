/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行结果定义
 *
 * @author Hua Wang
 * Created On: 2019/4/18 10:39
 */
public enum ExecutionResultDefinitionEnum {
    /**
     * 执行成功
     */
    EXECUTE_SUCCESS("1"),
    /**
     * 执行失败，但不知道原因
     */
    EXECUTE_FAILURE("2"),
    /**
     * 命令请求条件错误
     */
    REQUEST_CONDITION_ERROR("3"),
    /**
     * 通讯超时
     */
    TIMEOUT("4"),
    /**
     * 系统繁忙不能执行
     */
    SYSTEM_BUSY("5"),
    /**
     * 系统故障
     */
    SYSTEM_FAILURE("6"),
    /**
     * 没有数据
     */
    NO_DATA("100");

    private String code;
    private static Map<String, ExecutionResultDefinitionEnum> codeMap = new HashMap();

    static {
        for (ExecutionResultDefinitionEnum item : ExecutionResultDefinitionEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    ExecutionResultDefinitionEnum(String code) {
        this.code = code;
    }

    public static ExecutionResultDefinitionEnum lookup(String code) {
        return codeMap.get(code);
    }
}