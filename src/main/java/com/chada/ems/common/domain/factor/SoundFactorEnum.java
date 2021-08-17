/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 声监测因子
 *
 * @author Hua Wang
 * Created On: 2019/5/2 14:25
 */
public enum SoundFactorEnum {
    /**
     * A 权声级
     */
    LA("LA"),
    /**
     * 累计百分声级 L5
     */
    L5("L5"),
    /**
     * 累计百分声级 L10
     */
    L10("L10"),
    /**
     * 累计百分声级 L50
     */
    L50("L50"),
    /**
     * 累计百分声级 L90
     */
    L90("L90"),
    /**
     * 累计百分声级 L95
     */
    L95("L95"),
    /**
     * 等效声级
     */
    LEQ("Leq"),
    /**
     * 昼夜等效声级
     */
    LDN("Ldn"),
    /**
     * 昼间等效声级
     */
    LD("Ld"),
    /**
     * 夜间等效声级
     */
    LN("Ln"),
    /**
     * 最大的瞬时声级
     */
    LMX("LMx"),
    /**
     * 最小的瞬时声级
     */
    LMN("LMn");


    private String code;
    private static Map<String, SoundFactorEnum> codeMap = new HashMap();

    static {
        for (SoundFactorEnum item : SoundFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    SoundFactorEnum(String code) {
        this.code = code;
    }

    public static SoundFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}