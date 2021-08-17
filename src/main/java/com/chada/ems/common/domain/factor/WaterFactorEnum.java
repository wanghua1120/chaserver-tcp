/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 水监测因子
 *
 * @author Hua Wang
 *         Created On: 2019/4/17 23:08
 */
public enum WaterFactorEnum {
    /**
     * 污水
     */
    SEWAGE("w00000"),
    /**
     * PH 值
     */
    PH("w01001"),
    /**
     * 色度
     */
    SHADE("w01002"),
    /**
     * 溶解性总固体
     */
    SOLUBLE_SOLID("w01006"),
    /**
     * 溶解氧
     */
    SOLUBLE_OXYGEN("w01009"),
    /**
     * 水温
     */
    WATER_TEMPERATURE("w01010"),
    /**
     * 悬浮物
     */
    SUSPENDED_SOLIDS("w01012"),
    /**
     * 电导率
     */
    CONDUCTIVITY("w01014"),
    /**
     * 五日生化需氧量
     */
    FIVE_DAY_BIOCHEMICAL_OXYGEN_DEMAND("w01017"),
    /**
     * 化学需氧量
     */
    CHEMICAL_OXYGEN_DEMAND("w01018"),
    /**
     * 高锰酸盐指数
     */
    PERMANGANATE_INDEX("w01019"),
    /**
     * 总有机碳
     */
    TOTAL_ORGANIC_CARBON("w01020"),
    /**
     * 粪大肠菌群
     */
    FECAL_COLIFORM_BACTERIA("w02003"),
    /**
     * 细菌总数
     */
    TOTAL_BACTERIA_COUNT("w02006"),
    /**
     * 总 α 放射性
     */
    TOTAL_ALPHA_RADIOACTIVITY("w03001"),
    /**
     * 总 β 放射性
     */
    TOTAL_BETA_RADIOACTIVITY("w03002"),
    /**
     * 表面活性剂
     */
    SURFACE_ACTIVE_AGENT("w19001"),
    /**
     * 阴离子表面活性剂
     */
    ANIONIC_SURFATANT("w19002"),
    /**
     * 钡
     */
    BARIUM("w20012"),
    /**
     * 硼
     */
    BORON("w20023"),
    /**
     * 钴
     */
    COBALT("w20038"),
    /**
     * 钼
     */
    MOLYBDENUM("w20061"),
    /**
     * 铊
     */
    THALLIUM("w20089"),
    /**
     * 锡
     */
    TIN("w20092"),
    /**
     * 总汞
     */
    TOTAL_MERCURY("w20111"),
    /**
     * 烷基汞
     */
    ALKYL_MERCURY_MERCURY("w20113"),
    /**
     * 总镉
     */
    TOTAL_CADMIUM("w20115"),
    /**
     * 六价铬
     */
    SIX_VALENCE_CHROMIUM("w20117"),
    /**
     * 总砷
     */
    TOTAL_ARSENIC("w20119"),
    /**
     * 总铅
     */
    TOTAL_LEAD("w20120"),
    /**
     * 总镍
     */
    TOTAL_NICKEL("w20121"),
    /**
     * 总铜
     */
    TOTAL_COPPER("w20122"),
    /**
     * 总锌
     */
    TOTAL_ZINC("w20123"),
    /**
     * 总锰
     */
    TOTAL_MANGANESE("w20124"),
    /**
     * 总铁
     */
    TOTAL_IRON("w20125"),
    /**
     * 总银
     */
    TOTAL_SILVER("w20126"),
    /**
     * 总铍
     */
    TOTAL_BERYLLIUM("w20127"),
    /**
     * 总硒
     */
    TOTAL_SELENIUM("w20128"),
    /**
     * 铜
     */
    COPPER("w20138"),
    /**
     * 锌
     */
    ZINC("w20139"),
    /**
     * 硒
     */
    SELENIUM("w20140"),
    /**
     * 砷
     */
    ARSENIC("w20141"),
    /**
     * 汞
     */
    MERCURY("w20142"),
    /**
     * 镉
     */
    CADMIUM("w20143"),
    /**
     * 铅
     */
    LEAD("w20144"),
    /**
     * 总氮
     */
    TOTAL_NITROGEN("w21001"),
    /**
     * 氨氮
     */
    AMMONIA_NITROGEN("w21003"),
    /**
     * 凯氏氮
     */
    KJELDAHL_NITROGEN("w21004"),
    /**
     * 亚硝酸盐
     */
    NITRITE("w21006"),
    /**
     * 硝酸盐
     */
    NITRATE("w21007"),
    /**
     * 总磷
     */
    TOTAL_PHOSPHORUS("w21011"),
    /**
     * 氰化物
     */
    CYANIDE("w21016"),
    /**
     * 氟化物
     */
    FLUORIDE("w21017"),
    /**
     * 硫化物
     */
    SULFIDE("w21019"),
    /**
     * 氯化物
     */
    CHLORIDE("w21022"),
    /**
     * 硫酸盐
     */
    SULFATE("w21038"),
    /**
     * 石油类
     */
    PETROLEUM("w22001"),
    /**
     * 挥发酚
     */
    VOLATILE_PHENOL("w23002"),
    /**
     * 苯并[α]芘
     */
    BENZO_ALPHA_PYRENE("w25043"),
    /**
     * 六六六
     */
    BHC("w33001"),
    /**
     * 滴滴涕
     */
    DDT("w33007"),
    /**
     * 有机氮
     */
    ORGANIC_NITROGEN("w99001"),

    /**
     * 水质浊度（单位：NTU）
     */
    WATER_QUALITY_TURBIDITY("w80001");

    private String code;
    private static Map<String, WaterFactorEnum> codeMap = new HashMap();

    static {
        for (WaterFactorEnum item : WaterFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    WaterFactorEnum(String code) {
        this.code = code;
    }

    public static WaterFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}