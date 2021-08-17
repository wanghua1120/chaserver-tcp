/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.exception.support;

/**
 * @author Hua Wang
 *         Created On: 2019/4/13 10:50
 */
public enum ChaErrorName {

    // 01XXX 普通错误
    SYSTEM_DEFAULT("CHA-01001"),
    VALIDATION_EXCEPTION("CHA-01002"),
    JSON_READ_EXCEPTION("CHA-01003"),
    JSON_WRITE_EXCEPTION("CHA-01004"),
    INTERRUPTED_EXCEPTION("CHA-01005"),
    TIMEOUT_EXCEPTION("CHA-01006"),
    CANCELLED_EXCEPTION("CHA-01007"),
    RESOURCE_OUT_OF_SYNC("CHA-01008"),
    FILESYSTEM_INPUT_OUTPUT_EXCEPTION("CHA-01009"),
    NULL_POINTER_EXCEPTION("CHA-01010"),

    // 02XXX 数据库错误
    RESOURCE_NOT_FOUND("CHA-02001"),
    INTEGRITY_CONSTRAINT_VIOLATION_EXCEPTION("CHA-02002"),

    // 03XXX 实体操作错误
    DEPTH_CLONE_EXCEPTION("CHA-03001");

    private String errorCode;

    private ChaErrorName(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return errorCode + ":" + name();
    }

    /**
     * 获取 error code 的属性名，用于 ChaErrorCode.properties file.
     *
     * @return error code 的属性名
     */
    public String getPropertyName() {
        return errorCode + "." + name();
    }
}
