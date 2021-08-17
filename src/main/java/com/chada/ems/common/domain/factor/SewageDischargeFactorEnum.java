/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 污水排放监测因子（工况监测）
 * 注：xx 代表污水处理过程中同一工艺中使用的相同设备的编号，取值范围为 01~99。
 *
 * @author Hua Wang
 * Created On: 2019/5/2 17:05
 */
public enum SewageDischargeFactorEnum {
    /**
     * 进水口流量
     */
    WATER_INLET_FLOW("e101"),
    /**
     * 进水口 COD
     */
    WATER_INLET_COD("e102"),
    /**
     * 进水口氨氮
     */
    WATER_INLET_AMMONIA_NITROGEN("e103"),
    /**
     * 进水口总磷
     */
    WATER_INLET_TOTAL_PHOSPHORUS("e104"),
    /**
     * 进水口总氮
     */
    WATER_INLET_TOTAL_NITROGEN("e105"),
    /**
     * 进水口 PH
     */
    WATER_INLET_PH("e106"),
    /**
     * 出水口流量
     */
    WATER_OUTLET_FLOW("e201"),
    /**
     * 出水口 COD
     */
    WATER_OUTLET_COD("e202"),
    /**
     * 出水口氨氮
     */
    WATER_OUTLET_AMMONIA_NITROGEN("e203"),
    /**
     * 出水口总磷
     */
    WATER_OUTLET_TOTAL_PHOSPHORUS("e204"),
    /**
     * 出水口 PH
     */
    WATER_OUTLET_PH("e205"),
    /**
     * 污水提升泵
     */
    SEWAGE_HOIST_PUMP("e401", "e501", "e601", "e701", "e801"),
    /**
     * 鼓风机
     */
    BLOWER("e302"),
    /**
     * 鼓风量
     */
    BLAST_VOLUME("e303"),
    /**
     * 生化池污泥浓度
     */
    BIOCHEMICAL_POOL_SLUDGE_CONCENTRATION("e304", "e403", "e504"),
    /**
     * 生化池溶解氧浓度
     */
    BIOCHEMICAL_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e305"),
    /**
     * 污泥剩余泵
     */
    SLUDGE_RESIDUAL_PUMP("e306", "e407", "e605"),
    /**
     * 污泥回流泵
     */
    SLUDGE_REFLUX_PUMP("e307", "e408", "e606"),
    /**
     * 污泥回流量
     */
    SLUDGE_REFLUX_QUANTITY("e308", "e409", "e607"),
    /**
     * 污泥剩余量
     */
    SLUDGE_RESIDUAL_QUANTITY("e309", "e410", "e608"),
    /**
     * 污泥压滤机
     */
    SLUDGE_PRESSURE_FILTER("e310", "e411", "e609", "e707"),
    /**
     * 阀门状态
     */
    VALVE_STATUS("e311", "e413", "e512", "e615", "e708"),
    /**
     * 储泥池液位
     */
    MUD_STORAGE_TANK_LC("e312", "e417", "e516", "e617", "e710"),
    /**
     * 加药量
     */
    DOSAGE("e313", "e418", "e517", "e701"),
    /**
     * 生化池氧化还原电位
     */
    BIOCHEMICAL_POOL_REDOX_POTENTIAL("e314"),
    /**
     * 曝气设备
     */
    AERATION_EQUIPMENT("e402", "e502", "e602", "e702", "e802"),
    /**
     * 厌氧池溶解氧浓度
     */
    ANAEROBIC_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e404", "e505"),
    /**
     * 缺氧池溶解氧浓度
     */
    ANOXIC_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e405", "e506"),
    /**
     * 好氧池溶解氧浓度
     */
    AERATION_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e406", "e507"),
    /**
     * 搅拌器状态
     */
    AGITATOR_STATUS("e412", "e511"),
    /**
     * 缺氧池氧化还原电位
     */
    ANOXIC_POOL_REDOX_POTENTIAL("e414", "e513"),
    /**
     * 好氧池氧化还原电位
     */
    AERATIONPOOL_REDOX_POTENTIAL("e415", "e514"),
    /**
     * 提升泵池液位
     */
    HOISTING_PUMP_POOL_LC("e416", "e515", "e616", "e709"),
    /**
     * 供气量状态
     */
    GAS_SUPPLY_QUANTITY_STATUS("e503"),
    /**
     * 混合液回流泵
     */
    MIXTURE_REFLUX_PUMP("e508"),
    /**
     * 剩余污泥泵
     */
    RESIDUAL_SLUDGE_PUMP("e509", "e705", "e805"),
    /**
     * 剩余污泥量
     */
    RESIDUAL_PUMP_QUANTITY("e510", "e706"),
    /**
     * SBR 池污泥浓度
     */
    SBR_POOL_SLUDGE_CONCENTRATION("e603"),
    /**
     * SBR 池溶解氧浓度
     */
    SBR_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e604"),
    /**
     * 搅拌器
     */
    AGITATOR("e610"),
    /**
     * 曝气搅拌时氧化还原电位
     */
    SBR_POOL_AERATION_STIRING_REDOX_POTENTIAL("e614"),
    /**
     * 接触氧化池污泥浓度
     */
    CONTACT_OXIDATION_POOL_SLUDGE_CONCENTRATION("e703"),
    /**
     * 接触氧化池溶解氧浓度
     */
    CONTACT_OXIDATION_POOL_DISSOLVED_OXYGEN_CONCENTRATION("e704"),
    /**
     * 污泥浓度
     */
    SLUDGE_CONCENTRATION("e803"),
    /**
     * 溶解氧浓度
     */
    DISSOLVED_OXYGEN_CONCENTRATION("e804");


    private String[] codes;
    private static Map<String, SewageDischargeFactorEnum> codeMap = new HashMap();

    static {
        for (SewageDischargeFactorEnum item : SewageDischargeFactorEnum.values()) {
            codeMap.put(item.name(), item);
            for (String code : item.codes) {
                codeMap.put(code, item);
            }
        }
    }

    SewageDischargeFactorEnum(String... codes) {
        this.codes = codes;
    }

    public static SewageDischargeFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}