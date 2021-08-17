/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

/**
 * 监测因子类型
 *
 * @author Hua Wang
 *         Created On: 2019/5/9 23:55
 */
public enum FactorTypeEnum {
    /**
     * 水监测因子
     */
    WATER,
    /**
     * 气监测因子
     */
    AIR,
    /**
     * 声监测因子
     */
    SOUND,
    /**
     * 污水排放监测因子
     */
    SEWAGE_DISCHARGE,
    /**
     * 烟气排放监测因子
     */
    GAS_DISCHARGE,
    /**
     * 现场端信息编码
     */
    ON_SITE_INFORMATION,
    /**
     * 土壤
     */
    SOIL;
}