/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.monitor;

import javax.validation.constraints.NotNull;

/**
 * @author Hua Wang
 * Created On: 2019/5/6 22:17
 */
public interface FactorHolder<T> {
    T getFactor();

    void setFactor(@NotNull T factor);
}
