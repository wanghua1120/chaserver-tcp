/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 烟气排放监测因子（工况监测）
 * 注：后两位 xx 代表烟气处理过程中同一工艺中使用的相同设备的编号，取值范围为 1~99。
 *
 * @author Hua Wang
 * Created On: 2019/5/2 17:05
 */
public enum GasDischargeFactorEnum {
    /**
     * 增压风机状态
     */
    SUPERCHARGED_FAN_STATE("g101"),
    /**
     * 增压风机电流
     */
    BOOSTER_FAN_CURRENT("g102"),
    /**
     * 浆液循环泵状态
     */
    SLURRY_CIRCULATING_PUMP_STATUS("g103"),
    /**
     * 浆液循环泵电流
     */
    SLURRY_CIRCULATING_PUMP_CURRENT("g104"),
    /**
     * 密封剂状态
     */
    SEALANT_STATUS("g105"),
    /**
     * 密封剂电流
     */
    SEALANT_CURRENT("g106"),
    /**
     * GGH 运行状态
     */
    GGH_RUNNING_STATUS("g107"),
    /**
     * GGH 电机电流
     */
    GGH_MOTOR_STATUS("g108"),
    /**
     * 浆液泵状态
     */
    SLURRY_PUMP_STATUS("g109"),
    /**
     * 浆液泵流量
     */
    SLURRY_PUMP_FLOW("g110"),
    /**
     * 脱硫塔内浆液 pH
     */
    SLURRY_IN_DESULFURIZATION_TOWER_PH("g111"),
    /**
     * 吸收塔除雾器状态
     */
    ABSORPTION_TOWER_DEMISTER_STATUS("g112"),
    /**
     * 吸收塔除物器电流
     */
    ABSORBER_DEACTIVATOR_CURRENT("g113"),
    /**
     * 吸收塔搅拌器状态
     */
    AGITATOR_IN_ABSORPTION_TOWER_STATUS("g114"),
    /**
     * 吸收塔浆液密度
     */
    SLURRY_DENSITY_SLURRY_DENSITY("g115"),
    /**
     * 旁路挡板门开度
     */
    BYPASS_BAFFLE_DOOR_OPENING("g116"),
    /**
     * 石膏排除泵状态
     */
    GYPSUM_EXCLUSION_PUMP_STATUS("g117"),
    /**
     * 石膏排除泵电流
     */
    GYPSUM_EXCLUSION_PUMP_CURRENT("g118"),
    /**
     * 脱硫率
     */
    //DESULPHURIZATION_RATE("g119"),
    /**
     * 脱硫塔内喷水泵电流
     */
    SPRAY_PUMP_IN_DESULFURIZATION_TOWER_CURRENT("g201"),
    /**
     * 脱硫剂输送装置
     */
    DESULFURIZER_CONVEYOR("g202"),
    /**
     * 称重给煤机计量信号
     */
    MEASURING_SIGNAL_OF_WEIGHING_COAL_FEEDER("g203"),
    /**
     * 炉膛床压
     */
    FURNACE_BED_PRESSURE("g204"),
    /**
     * 炉膛床温
     */
    FURNACE_BED_TEMPERATURE("g205"),
    /**
     * 冷渣器转速
     */
    SLAG_COOLER_SPEED("g206"),
    /**
     * 返料风机电流
     */
    RECYCLING_FAN_CURRENT("g207"),
    /**
     * 引风机电流
     */
    INDUCED_DRAFT_FAN_CURRENT("g208"),
    /**
     * 一次风机电流
     */
    PRIMARY_FAN_CURRENT("g209"),
    /**
     * 二次风机电流
     */
    SECONDARY_FAN_CURRENT("g210"),
    /**
     * 石灰石给料机电流
     */
    LIMESTONE_FEEDER_CURRENT("g211"),
    /**
     * 脱硫率
     */
    DESULPHURIZATION_RATE("g212"),
    /**
     * 氨喷射系统电流
     */
    AMMONIA_INJECTION_SYSTEM_CURRENT("g301"),
    /**
     * 稀释风机状态
     */
    DILUTING_FAN_STATUS("g302"),
    /**
     * 稀释风机电流
     */
    DILUTING_FAN_CURRENT("g303"),
    /**
     * 氨泵风机状态
     */
    AMMONIA_PUMP_FAN_STATUS("g304"),
    /**
     * 氨泵风机电流
     */
    AMMONIA_PUMP_FAN_CURRENT("g305"),
    /**
     * 旁路挡板状态
     */
    BYPASS_BAFFLE_STATUS("g306"),
    /**
     * 旁路挡板开度
     */
    BYPASS_BAFFLE_OPENING("g307"),
    /**
     * 旁路挡板左右压差
     */
    BYPASS_BAFFLE_LEFT_AND_RIGHT_PRESSURE_DIFFERENCE("g308"),
    /**
     * 入口二氧化硫 SO2
     */
    ENTRANCE_SO2("g119"),
    /**
     * 入口氮氧化物 NOx
     */
    ENTRANCE_NOx("g120"),
    /**
     * 入口含氧量 O2
     */
    ENTRANCE_O2("g121"),
    /**
     * 入口流量
     */
    ENTRANCE_FLOW("g122"),
    /**
     * 入口温度
     */
    ENTRANCE_TEMPERATURE("g123"),
    /**
     * 入口烟尘
     */
    ENTRANCE_SMOKE("g124"),
    /**
     * 入口压力
     */
    ENTRANCE_PRESSURE("g125"),
    /**
     * 入口湿度
     */
    ENTRANCE_HUMIDITY("g126"),
    /**
     * 出口二氧化硫 SO2
     */
    EXIT_SO2("g127"),
    /**
     * 出口氮氧化物 NOx
     */
    EXIT_NOx("g128"),
    /**
     * 出口含氧量 O2
     */
    EXIT_O2("g129"),
    /**
     * 出口流量
     */
    EXIT_FLOW("g130"),
    /**
     * 出口温度
     */
    EXIT_TEMPERATURE("g131"),
    /**
     * 出口烟尘
     */
    EXIT_SMOKE("g132"),
    /**
     * 出口压力
     */
    EXIT_PRESSURE("g133"),
    /**
     * 出口湿度
     */
    EXIT_HUMIDITY("g134");

    private String code;
    private static Map<String, GasDischargeFactorEnum> codeMap = new HashMap();

    static {
        for (GasDischargeFactorEnum item : GasDischargeFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    GasDischargeFactorEnum(String code) {
        this.code = code;
    }

    public static GasDischargeFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}