/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 土壤监测因子
 *
 * @author Hua Wang
 *         Created On: 2019/5/14 11:25
 */
public enum SoilFactorEnum {
    /**
     * 土壤温度1（单位：摄氏度）
     */
    SOIL_TEMPERATURE_1("t80001"),
    /**
     * 土壤温度1（单位：摄氏度）
     */
    SOIL_TEMPERATURE_2("t80002"),
    /**
     * 土壤湿度1（单位：%）
     */
    SOIL_HUMIDITY_1("t80011"),
    /**
     * 土壤湿度2（单位：%）
     */
    SOIL_HUMIDITY_2("t80012"),
    /**
     * 土壤PH（单位：无量纲）
     */
    SOIL_PH("t80021");


    private String code;
    private static Map<String, SoilFactorEnum> codeMap = new HashMap();

    static {
        for (SoilFactorEnum item : SoilFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    SoilFactorEnum(String code) {
        this.code = code;
    }

    public static SoilFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}