/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

/**
 * 命令编码类别
 *
 * @author Hua Wang
 * Created On: 2019/4/16 15:59
 */
public enum CodingCommandTypeEnum {
    /**
     * 初始化命令（1000～1010）
     */
    INITIALIZATION,
    /**
     * 参数命令（1011～1999）
     */
    PARAMETER,
    /**
     * 数据命令（2000～2999）
     */
    DATA,
    /**
     * 控制命令（3000～3999）
     */
    CONTROL,
    /**
     * 交互命令（9000～9999）
     */
    INTERACTIVE
}