/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common;

/**
 * @author Hua Wang
 *         Created On: 2019/5/18 23:31
 */
public class TwoMemberObject<A, B> {

    private A valueA;
    private B valueB;

    public TwoMemberObject(A valueA, B valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public A getFirstValue() {
        return valueA;
    }

    public B getSecondValue() {
        return valueB;
    }

}
