/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

/**
 * 系统编码类别
 *
 * @author Hua Wang
 * Created On: 2019/4/16 15:59
 */
public enum CodingSystemTypeEnum {
    /**
     * 环境质量（10～29）
     */
    ENVIRONMENTAL_QUALITY,
    /**
     * 环境污染源（30～49）
     */
    ENVIRONMENTAL_POLLUTION_SOURCE,
    /**
     * 工况（50～69）
     */
    WORKING_CONDITION,
    /**
     * 系统交互（91～99）
     */
    SYSTEM_INTERACTION,
    /**
     * 未知系统编码（A0～Z9）
     */
    UNKNOW
}