/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 气监测因子
 *
 * @author Hua Wang
 *         Created On: 2019/5/2 14:08
 */
public enum AirFactorEnum {
    /**
     * 废气
     */
    WASTE_GAS("a00000"),
    /**
     * 温度
     */
    TEMPERATURE("a01001"),
    /**
     * 湿度
     */
    HUMIDITY("a01002"),
    /**
     * 气压
     */
    PRESSURE("a01006"),
    /**
     * 风速
     */
    WIND_SPEED("a01007"),
    /**
     * 风向
     */
    WIND_DIRECTION("a01008"),
    /**
     * 林格曼黑度
     */
    LINGMANN_BLACKNESS("a01010"),
    /**
     * 烟气流速
     */
    FLUE_GAS_VELOCITY("a01011"),
    /**
     * 烟气温度
     */
    FLUE_GAS_TEMPERATURE("a01012"),
    /**
     * 烟气压力
     */
    FLUE_GAS_PRESSURE("a01013"),
    /**
     * 烟气湿度
     */
    FLUE_GAS_HUMIDITY("a01014"),
    /**
     * 制冷温度
     */
    REFRIGERATION_TEMPERATURE("a01015"),
    /**
     * 烟道截面积
     */
    FLUE_SECTIONAL_AREA("a01016"),
    /**
     * 烟道截面积
     */
    FLUE_GAS_DYNAMIC_PRESSURE("a01017"),
    /**
     * 垃圾焚烧炉膛内焚烧平均温度
     */
    REFUSE_INCINERATOR_MEAN_INCINERATION_TEMPERATURE("a01901"),
    /**
     * 垃圾焚烧炉膛内 DCS 温度
     */
    REFUSE_INCINERATOR_DCS_TEMPERATURE("a01902"),
    /**
     * 二氧化碳
     */
    CARBON_DIOXIDE("a05001"),
    /**
     * 甲烷
     */
    METHANE("a05002"),
    /**
     * 三氯一氟甲烷
     */
    TRICHLOROFLUOROMETHANE("a05008"),
    /**
     * 二氯二氟甲烷
     */
    DICHLORODIFLUOROMETHANE("a05009"),
    /**
     * 三氯三氟乙烷
     */
    TRICHLOROTRIFLUOROETHANE("a05013"),
    /**
     * 氧气含量
     */
    OXYGEN_CONTENT("a19001"),
    /**
     * 砷
     */
    ARSENIC("a20007"),
    /**
     * 铍及其化合物
     */
    BERYLLIUM_COMPOUNDS("a20016"),
    /**
     * 镉及其化合物
     */
    CADMIUM_COMPOUNDS("a20025"),
    /**
     * 镉
     */
    CADMIUM("a20026"),
    /**
     * 铅及其化合物
     */
    LEAD_COMPOUNDS("a20043"),
    /**
     * 铅
     */
    LEAD("a20044"),
    /**
     * 汞及其化合物
     */
    MERCURY_COMPOUNDS("a20057"),
    /**
     * 汞
     */
    MERCURY("a20058"),
    /**
     * 镍及其化合物
     */
    NICKEL_COMPOUNDS("a20063"),
    /**
     * 锡及其化合物
     */
    TIN_COMPOUNDS("a20091"),
    /**
     * 氨（氨气）
     */
    AMMONIA("a21001"),
    /**
     * 氮氧化物
     */
    NITROGEN_OXIDE("a21002"),
    /**
     * 一氧化氮
     */
    NITRIC_OXIDE("a21003"),
    /**
     * 二氧化氮
     */
    NITROGEN_DIOXIDE("a21004"),
    /**
     * 一氧化碳
     */
    CARBON_MONOXIDE("a21005"),
    /**
     * 氰化物
     */
    CYANIDE("a21017"),
    /**
     * 氟化物
     */
    FLUORIDE("a21018"),
    /**
     * 氯气
     */
    CHLORINE("a21022"),
    /**
     * 氯化氢
     */
    HYDROGEN_CHLORIDE("a21024"),
    /**
     * 二氧化硫
     */
    SULFUR_DIOXIDE("a21026"),
    /**
     * 硫化氢
     */
    HYDROGEN_SULFIDE("a21028"),
    /**
     * 酚类
     */
    PHENOLS("a23001"),
    /**
     * 二氯甲烷
     */
    DICHLOROMETHANE("a24003"),
    /**
     * 三氯甲烷
     */
    TRICHLOROMETHANE("a24004"),
    /**
     * 四氯甲烷
     */
    TETRACHLORMETHANE("a24005"),
    /**
     * 二溴一氯甲烷
     */
    DIBROMOCHLOROMETHANE("a24006"),
    /**
     * 一溴二氯甲烷
     */
    MONOBROMODICHLOROMETHANE("a24007"),
    /**
     * 溴甲烷
     */
    METHYL_BROMIDE("a24008"),
    /**
     * 三溴甲烷
     */
    THREE_BROMINATED_METHANE("a24009"),
    /**
     * 氯乙烷
     */
    CHLOROETHANE("a24015"),
    /**
     * 1,1-二氯乙烷
     */
    TWO_CHLOROETHANE_1_1("a24016"),
    /**
     * 1,2-二氯乙烷
     */
    TWO_CHLOROETHANE_1_2("a24017"),
    /**
     * 1,1,1-三氯乙烷
     */
    THREE_CHLOROETHANE_1_1_1("a24018"),
    /**
     * 1,1,2-三氯乙烷
     */
    THREE_CHLOROETHANE_1_1_2("a24019"),
    /**
     * 1,1,2,2-四氯乙烷
     */
    FOUR_CHLOROETHANE_1_1_2_2("a24018"),
    /**
     * 1,2-二氯丙烷
     */
    TWO_CHLOROPROPANE_1_1("a24027"),
    /**
     * 1,2-二溴乙烷
     */
    ETHYLENE_GLYCOL_1_2("a24034"),
    /**
     * 环己烷
     */
    CYCLOHEXANE("a24036"),
    /**
     * 正己烷
     */
    NHEXANE("a24042"),
    /**
     * 正庚烷
     */
    HEPTANE("a24043"),
    /**
     * 氯乙烯
     */
    VINYL_CHLORIDE("a24046"),
    /**
     * 1,1-二氯乙烯
     */
    TWO_VINY1_CHLORIDE_1_1("a24047"),
    /**
     * 三氯乙烯
     */
    TRICHLOROETHYLENE("a24049"),
    /**
     * 四氯乙烯
     */
    FOUR_VINYL_CHLORIDE("a24050"),
    /**
     * 丙烯
     */
    PROPYLENE("a24053"),
    /**
     * 1,3-二氯丙烯
     */
    TWO_ALLYL_CHLORIDE_1_3("a24054"),
    /**
     * 1,4-二恶烷
     */
    TWO_OXANE_1_4("a24072"),
    /**
     * 1,3-丁二烯
     */
    BUTADIENE_1_3("a24078"),
    /**
     * 碳氢化合物
     */
    HYDROCARBON("a24087"),
    /**
     * 非甲烷总烃
     */
    NON_METHANE_TOTAL_HYDROCARBONS("a24088"),
    /**
     * 氯甲烷
     */
    CHLOROMETHANE("a24099"),
    /**
     * 反式-1,2-二氯乙烯
     */
    TRANS_DICHLOROETHYLENE_1_2("a24110"),
    /**
     * 顺式-1,2-二氯乙烯
     */
    CIS_DICHLOROETHYLENE_1_2("a24111"),
    /**
     * 反式-1,3-二氯丙烯
     */
    TRANS_PROPYLENE_DICHLORIDE_1_3("a24112"),
    /**
     * 六氯-1,3-丁二烯
     */
    HEXACHLOROBUTADIENE_1_3("a24113"),
    /**
     * 苯
     */
    BENZENE("a25002"),
    /**
     * 甲苯
     */
    TOLUENE("a25003"),
    /**
     * 乙苯
     */
    ETHYLBENZENE("a25004"),
    /**
     * 二甲苯
     */
    XYLENE("a25005"),
    /**
     * 1,2-二甲基苯
     */
    TWO_METHYL_BENZENE_1_2("a25006"),
    /**
     * 1,3-二甲基苯
     */
    TWO_METHYL_BENZENE_1_3("a25007"),
    /**
     * 1,4-二甲基苯
     */
    TWO_METHYL_BENZENE_1_4("a25008"),
    /**
     * 氯苯
     */
    CHLOROBENZENE("a25010"),
    /**
     * 1,2-二氯苯
     */
    TWO_CHLOROBENZENE_1_2("a25011"),
    /**
     * 1,3-二氯苯
     */
    TWO_CHLOROBENZENE_1_3("a25012"),
    /**
     * 1,4-二氯苯
     */
    TWO_CHLOROBENZENE_1_4("a25013"),
    /**
     * 1-乙基-4-甲基苯
     */
    ETHYL_METHYLBENZENE_1_4("a25014"),
    /**
     * 1,2,4-三氯苯
     */
    THREE_CHLOROBENZENE_1_2_4("a25015"),
    /**
     * 1,2,4-三甲基苯
     */
    THREE_METHYL_BENZENE_1_2_4("a25019"),
    /**
     * 1,2,3-三甲基苯
     */
    THREE_METHYL_BENZENE_1_2_3("a25020"),
    /**
     * 1,2,5-三甲基苯
     */
    THREE_METHYL_BENZENE_1_2_5("a25021"),
    /**
     * 硝基苯
     */
    NITROBENZENE("a25023"),
    /**
     * 乙烯基苯
     */
    VINYL_BENZENE("a25038"),
    /**
     * 苯并[a]芘
     */
    BENZO_A_PYRENE("a25044"),
    /**
     * 四氢呋喃
     */
    TETRAHYDROFURAN("a25072"),
    /**
     * 苯胺类
     */
    ANILINES("a26001"),
    /**
     * 乙酸乙酯
     */
    ETHYL_ACETATE("a29017"),
    /**
     * 乙酸乙烯酯
     */
    VINYL_ACETATE("a29026"),
    /**
     * 甲醇
     */
    METHANOL("a30001"),
    /**
     * 异丙醇
     */
    ISOPROPYL_ALCOHOL("a30008"),
    /**
     * 硫醇
     */
    MERCAPTAN("a30022"),
    /**
     * 甲醛
     */
    FORMALDEHYDE("a31001"),
    /**
     * 乙醛
     */
    ACETALDEHYDE("a31002"),
    /**
     * 丙酮
     */
    ACETONE("a31024"),
    /**
     * 丁酮
     */
    BUTANONE("a31025"),
    /**
     * 甲基异丁基甲酮
     */
    METHYL_ISOBUTYL_KETONE("a31030"),
    /**
     * 总悬浮颗粒物
     */
    TOTAL_SUSPENDED_PARTICULATE_MATTER("a34001"),
    /**
     * 可吸入颗粒物 PM10
     */
    INHALABLE_PARTICULATE_MATTER_PM10("a34002"),
    /**
     * 细微颗粒物 PM2.5
     */
    FINE_PARTICULATE_MATTER_PM2_5("a34004"),
    /**
     * 亚微米颗粒物 PM1.0
     */
    SUBMICRON_PARTICULATE_MATTER_PM1_0("a34005"),
    /**
     * 降尘
     */
    FALLEN_DUST("a34011"),
    /**
     * 烟尘
     */
    SMOKE_DUST("a34013"),
    /**
     * 炭黑尘
     */
    CARBON_BLACK_DUST("a34017"),
    /**
     * 沥青烟
     */
    PITCH_SMOKE("a34038"),
    /**
     * 硫酸雾
     */
    SULPHURIC_ACID_FOG("a34039"),
    /**
     * 铬酸雾
     */
    CHROMIUM_ACID_MIST("a34040"),
    /**
     * 丙烯腈
     */
    ACRYLONITRILE("a99010"),
    /**
     * 光气
     */
    PHOSGENE("a99049"),
    /**
     * 二硫化碳
     */
    CARBON_DISULFIDE("a99051"),

    /**
     * 雨量（单位：毫米）
     */
    RAINFALL("a80002"),
    /**
     * 负氧离子（单位：个/立方厘米）
     */
    NEGATIVE_OXYGEN_IONS("a80003");


    private String code;
    private static Map<String, AirFactorEnum> codeMap = new HashMap();

    static {
        for (AirFactorEnum item : AirFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    AirFactorEnum(String code) {
        this.code = code;
    }

    public static AirFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}