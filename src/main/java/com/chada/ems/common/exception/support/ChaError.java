/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.exception.support;

import com.chada.ems.common.i18n.ChaMessageSource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.util.Assert;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 10:50
 */
@JsonPropertyOrder({"errorCode", "errorName", "systemMessage", "userMessage"})
public class ChaError {

    private static final String ERROR_CODE_RESOURCE_BASE = "i18n/errorCode/ChaErrorCode";

    private ChaErrorName errorName;

    @JsonIgnore
    private Object[] args;

    private String systemMessage;

    @SuppressWarnings("unused")
    protected ChaError() {
    }

    public ChaError(ChaErrorName errorName, Object[] args, String systemMessage) {
        Assert.notNull(errorName, "ChaErrorName cannot be empty.");
        this.errorName = errorName;
        this.args = args;
        this.systemMessage = systemMessage;
    }

    public ChaErrorName getErrorName() {
        return errorName;
    }

    @JsonProperty
    public String getErrorCode() {
        return errorName.getErrorCode();
    }

    @JsonIgnore
    public void setErrorCode(String mock) {
        //do nothing here, we don't set error code. The method is declared to add JsonIgnore
    }

    public Object[] getArgs() {
        return args;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    /*
    * 获取用户消息，用户消息通过 ChaErrorCode.properties 支持本地化
    * */
    @JsonProperty
    public String getUserMessage() {
        return ChaMessageSource.getMessage(ERROR_CODE_RESOURCE_BASE, errorName.getPropertyName(), args);
    }


    @JsonIgnore
    private void setUserMessage(String toIgnore) {
        //do nothing, used for marking of JsonIgnore.
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(errorName).append(":").append(getSystemMessage());
        return builder.toString();
    }

}
