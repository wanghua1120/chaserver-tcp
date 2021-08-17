/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.factor;

import java.util.HashMap;
import java.util.Map;

/**
 * 现场端信息编码
 *
 * @author Hua Wang
 * Created On: 2019/5/2 17:15
 */
public enum OneSiteInformationFactorEnum {
    /**
     * 运行日志（在线监控（监测）仪器仪表）
     */
    OMI_OPERATION_LOG("i11001"),
    /**
     * 工作状态（在线监控（监测）仪器仪表设备）
     */
    OMID_WORKING_STATUS("i12001"),
    /**
     * 分析仪与数采仪通讯状态（在线监控（监测）仪器仪表设备）
     */
    OMID_ANALYZER_AND_DIGITIZER_COMMUNICATION_STATUS("i12002"),
    /**
     * COD 分析仪报警状态（在线监控（监测）仪器仪表设备）
     */
    OMID_COD_ANALYZER_ALARM_STATUS("i12003"),
    /**
     * 测量量程（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_MEASUREMENT_RANGE("i13001"),
    /**
     * 测量精度（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_MEASUREMENT_ACCURACY("i13002"),
    /**
     * 测量间隔（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_MEASUREMENT_INTERVAL("i13003"),
    /**
     * 消解温度（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_DIGESTION_TEMPERATURE("i13004"),
    /**
     * 消解时长（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_DIGESTION_TIME("i13005"),
    /**
     * 消解时长（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_CALIBRATION_TIME("i13006"),
    /**
     * 截距（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_INTERCEPT("i13007"),
    /**
     * 截距（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_SLOPE("i13008"),
    /**
     * 测量检出限（在线监控（监测）仪器仪表设备（参数））
     */
    OMID_MEASUREMENT_DETECTION_LIMIT("i13009"),
    /**
     * 运行日志（数采仪）
     */
    DAI_OPERATION_LOG("i21001"),
    /**
     * 工作状态（数采仪）
     */
    DAI_WORKING_STATUS("i22001"),
    /**
     * 用户状态（数采仪）
     */
    DAI_USER_STATUS("i22002"),
    /**
     * 数采仪与上位机通讯状态（数采仪）
     */
    DAI_UPPER_COMPUTER_COMMUNICATION_STATUS("i22003"),
    /**
     * 数采仪通道通讯状态（数采仪）
     */
    DAI_CHANNEL_COMMUNICATION_STATUS("i22101"),
    /**
     * 本地大气压力（数采仪）
     */
    DAI_LOCAL_ATMOSPHERIC_PRESSURE("i23001"),
    /**
     * 门禁日志（辅助设备）
     */
    AE_ENTRANCE_GUARD_LOG("i31001"),
    /**
     * 门禁状态（辅助设备）
     */
    AE_ENTRANCE_GUARD_STATUS("i32001"),
    /**
     * CEMS 伴热管温度（辅助设备）
     */
    AE_CEMS_TRACER_TEMPERATURE("i33001"),
    /**
     * CEMS 冷凝温度（辅助设备）
     */
    AE_CEMS_CONDENSATION_TEMPERATURE("i33002"),
    /**
     * 监测站房温度（辅助设备）
     */
    AE_MONITORING_STATION_TEMPERATURE("i33101"),
    /**
     * 监测站房湿度（辅助设备）
     */
    AE_MONITORING_STATION_HUMIDITY("i33102"),
    /**
     * 监测站房电压（辅助设备）
     */
    AE_VOLTAGE("i33103"),
    /**
     * 监测站房原水压力（辅助设备）
     */
    AE_RAW_WATER_PRESSURE("i33104"),
    /**
     * 监测站房进样压力 1（辅助设备）
     */
    AE_INJECTION_PRESSURE_1("i33105"),
    /**
     * 监测站房进样压力 2（辅助设备）
     */
    AE_INJECTION_PRESSURE_2("i33106"),
    /**
     * 沉砂池清洗时间（辅助设备）
     */
    AE_SAND_SINK_CLEAN_TIME("i33107"),
    /**
     * 污水处理站（厂）电流量（辅助设备）
     */
    AE_SWWAGE_TREATMENT_STATION_CURRENT_QUANTITY("i33200"),
    /**
     * 污水处理站（厂）累计耗电量（辅助设备）
     */
    AE_SWWAGE_TREATMENT_STATION_CUMULATIVE_POWER_CONSUMPTION("i33201"),
    /**
     * 污水处理站（厂）日耗电量（辅助设备）
     */
    AE_SWWAGE_TREATMENT_STATION_DAILY_POWER_CONSUMPTION("i33202"),

    /**
     * 炉膛内上部焚烧温度（辅助设备0）
     */
    AE0_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33310"),
    /**
     * 炉膛内上部焚烧温度（辅助设备1）
     */
    AE1_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33311"),
    /**
     * 炉膛内上部焚烧温度（辅助设备2）
     */
    AE2_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33312"),
    /**
     * 炉膛内上部焚烧温度（辅助设备3）
     */
    AE3_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33313"),
    /**
     * 炉膛内上部焚烧温度（辅助设备4）
     */
    AE4_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33314"),
    /**
     * 炉膛内上部焚烧温度（辅助设备5）
     */
    AE5_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33315"),
    /**
     * 炉膛内上部焚烧温度（辅助设备6）
     */
    AE6_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33316"),
    /**
     * 炉膛内上部焚烧温度（辅助设备7）
     */
    AE7_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33317"),
    /**
     * 炉膛内上部焚烧温度（辅助设备8）
     */
    AE8_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33318"),
    /**
     * 炉膛内上部焚烧温度（辅助设备9）
     */
    AE9_FURNACE_UPPER_INCINERATION_TEMPERATURE("i33319"),

    /**
     * 炉膛内中部焚烧温度（辅助设备0）
     */
    AE0_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33320"),
    /**
     * 炉膛内中部焚烧温度（辅助设备1）
     */
    AE1_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33321"),
    /**
     * 炉膛内中部焚烧温度（辅助设备2）
     */
    AE2_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33322"),
    /**
     * 炉膛内中部焚烧温度（辅助设备3）
     */
    AE3_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33323"),
    /**
     * 炉膛内中部焚烧温度（辅助设备4）
     */
    AE4_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33324"),
    /**
     * 炉膛内中部焚烧温度（辅助设备5）
     */
    AE5_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33325"),
    /**
     * 炉膛内中部焚烧温度（辅助设备6）
     */
    AE6_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33326"),
    /**
     * 炉膛内中部焚烧温度（辅助设备7）
     */
    AE7_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33327"),
    /**
     * 炉膛内中部焚烧温度（辅助设备8）
     */
    AE8_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33328"),
    /**
     * 炉膛内中部焚烧温度（辅助设备9）
     */
    AE9_FURNACE_MIDDLE_INCINERATION_TEMPERATURE("i33329"),

    /**
     * 炉膛内下部焚烧温度（辅助设备0）
     */
    AE0_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33330"),
    /**
     * 炉膛内下部焚烧温度（辅助设备1）
     */
    AE1_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33331"),
    /**
     * 炉膛内下部焚烧温度（辅助设备2）
     */
    AE2_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33332"),
    /**
     * 炉膛内下部焚烧温度（辅助设备3）
     */
    AE3_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33333"),
    /**
     * 炉膛内下部焚烧温度（辅助设备4）
     */
    AE4_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33334"),
    /**
     * 炉膛内下部焚烧温度（辅助设备5）
     */
    AE5_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33335"),
    /**
     * 炉膛内下部焚烧温度（辅助设备6）
     */
    AE6_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33336"),
    /**
     * 炉膛内下部焚烧温度（辅助设备7）
     */
    AE7_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33337"),
    /**
     * 炉膛内下部焚烧温度（辅助设备8）
     */
    AE8_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33338"),
    /**
     * 炉膛内下部焚烧温度（辅助设备9）
     */
    AE9_FURNACE_LOWER_INCINERATION_TEMPERATURE("i33339"),

    /**
     * 炉膛内二次空气喷入点温度（辅助设备0）
     */
    AE0_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33340"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备1）
     */
    AE1_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33341"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备2）
     */
    AE2_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33342"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备3）
     */
    AE3_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33343"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备4）
     */
    AE4_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33344"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备5）
     */
    AE5_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33345"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备6）
     */
    AE6_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33346"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备7）
     */
    AE7_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33347"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备8）
     */
    AE8_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33348"),
    /**
     * 炉膛内二次空气喷入点温度（辅助设备9）
     */
    AE9_FURNACE_SECONDARY_AIR_INJECTION_POINT_TEMPERATURE("i33349"),

    /**
     * 生产负荷（辅助设备）
     */
    AE_PRODUCTION_LOAD("I33400");

    private String code;
    private static Map<String, OneSiteInformationFactorEnum> codeMap = new HashMap();

    static {
        for (OneSiteInformationFactorEnum item : OneSiteInformationFactorEnum.values()) {
            codeMap.put(item.name(), item);
            codeMap.put(item.code, item);
        }
    }

    OneSiteInformationFactorEnum(String code) {
        this.code = code;
    }

    public static OneSiteInformationFactorEnum lookup(String code) {
        return codeMap.get(code);
    }
}