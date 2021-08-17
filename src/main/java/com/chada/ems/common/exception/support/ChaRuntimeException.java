/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.exception.support;

import org.springframework.util.Assert;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 10:50
 */
public class ChaRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ChaError chaError;

    public ChaRuntimeException(ChaErrorName errorName, Object[] args, String message, Throwable cause) {
        super(message, cause);
        Assert.notNull(errorName, "ChaErrorName cannot be empty.");
        this.chaError = new ChaError(errorName, args, message);
    }

    public ChaRuntimeException(ChaError error, Throwable cause) {
        super(error.getSystemMessage(), cause);
        this.chaError = error;
    }

    public ChaRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.chaError = new ChaError(ChaErrorName.SYSTEM_DEFAULT, null, message);
    }

    public ChaRuntimeException(ChaErrorName errorName, Object[] args, String message) {
        super(message);
        Assert.notNull(errorName, "ChaErrorName cannot be empty.");
        this.chaError = new ChaError(errorName, args, message);
    }

    public ChaRuntimeException(String message) {
        super(message);
        this.chaError = new ChaError(ChaErrorName.SYSTEM_DEFAULT, null, message);
    }

    public ChaRuntimeException(Throwable cause) {
        super(cause);
        this.chaError = new ChaError(ChaErrorName.SYSTEM_DEFAULT, null, cause.getMessage());
    }

    public ChaError getChaError() {
        return chaError;
    }

    public ChaErrorName getErrorName() {
        return chaError.getErrorName();
    }

    @Override
    public String getMessage() {

        if (chaError != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(chaError.getErrorName());
            sb.append(":");
            sb.append(chaError.getUserMessage());
            sb.append(" ");
            sb.append(super.getMessage());
            return sb.toString();
        } else {
            return super.getMessage();
        }
    }

}
