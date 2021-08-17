/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.exception;

import com.chada.ems.common.exception.support.ChaErrorName;
import com.chada.ems.common.exception.support.ChaRuntimeException;

/**
 * @author Hua Wang
 * Created On: 2019/4/13 10:58
 */
public class ChaNullPointerException extends ChaRuntimeException {
    private static final long serialVersionUID = 1L;

    public ChaNullPointerException(String message, Throwable cause) {
        super(ChaErrorName.NULL_POINTER_EXCEPTION, null, message, cause);
    }

    public ChaNullPointerException(String message) {
        super(ChaErrorName.NULL_POINTER_EXCEPTION, null, message);
    }

    public ChaNullPointerException(Throwable cause) {
        super(ChaErrorName.NULL_POINTER_EXCEPTION, null, null, cause);
    }
}
