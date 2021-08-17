/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.util;

import com.chada.ems.common.TwoMemberObject;
import com.chada.ems.common.domain.factor.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hua Wang
 *         Created On: 2019/5/2 13:58
 */
@Slf4j
public class FactorUtil {
    private static final Pattern SAMPLE_TIME_PATTERN = Pattern.compile("(\\S+?)-SampleTime");
    private static final Pattern RTN_PATTERN = Pattern.compile("(\\S+?)-Rtd$");
    private static final Pattern MIN_PATTERN = Pattern.compile("(\\S+?)-Min$");
    private static final Pattern Avg_PATTERN = Pattern.compile("(\\S+?)-Avg");
    private static final Pattern Max_PATTERN = Pattern.compile("(\\S+?)-Max");
    private static final Pattern ZS_RTD_PATTERN = Pattern.compile("(\\S+?)-ZsRtd");
    private static final Pattern ZS_MIN_PATTERN = Pattern.compile("(\\S+?)-ZsMin");
    private static final Pattern ZS_Avg_PATTERN = Pattern.compile("(\\S+?)-ZsAvg");
    private static final Pattern ZS_MAX_PATTERN = Pattern.compile("(\\S+?)-ZsMax");
    private static final Pattern FLAG_PATTERN = Pattern.compile("(\\S+?)-Flag");
    private static final Pattern EFLAG_PATTERN = Pattern.compile("(\\S+?)-EFlag");
    private static final Pattern COU_PATTERN = Pattern.compile("(\\S+?)-Cou");
    private static final Pattern DATA_PATTERN = Pattern.compile("(\\S+?)-Data");
    private static final Pattern DAY_DATA_PATTERN = Pattern.compile("(\\S+?)-DayData");
    private static final Pattern NIGHT_DATA_PATTERN = Pattern.compile("(\\S+?)-NightData");
    private static final Pattern INFO_PATTERN = Pattern.compile("(\\S+?)-Info");
    private static final Pattern SN_PATTERN = Pattern.compile("(\\S+?)-SN");

    public static final String SB_RS = "SB_RS";
    public static final String SB_RT = "SB_RT";
    private static final Pattern SB_RS_PATTERN = Pattern.compile("SB(\\S+?)-RS");
    private static final Pattern SB_RT_PATTERN = Pattern.compile("SB(\\S+?)-RT");

    private static List<Pattern> PATTERN_LIST;

    static {
        PATTERN_LIST = Arrays.asList(
                SAMPLE_TIME_PATTERN, RTN_PATTERN, MIN_PATTERN, Avg_PATTERN, Max_PATTERN,
                ZS_RTD_PATTERN, ZS_MIN_PATTERN, ZS_Avg_PATTERN, ZS_MAX_PATTERN, FLAG_PATTERN,
                EFLAG_PATTERN, COU_PATTERN, DATA_PATTERN, DAY_DATA_PATTERN, NIGHT_DATA_PATTERN,
                INFO_PATTERN, SN_PATTERN
        );
    }

    public static String getFactorCode(String name) {
        String factorCode = "";

        for (Pattern pattern : PATTERN_LIST) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                factorCode = matcher.group(1);
                break;
            }
        }

        return factorCode;
    }

    public static Object getFactorByName(String name) {
        String code = getFactorCode(name);
        return getFactorByCode(code);
    }

    public static Object getFactorByCode(String code) {
        Object result = null;

        if (code.startsWith("w")) {
            result = WaterFactorEnum.lookup(code);
            if (result == null) log.warn("[" + code + "]可能是[水监测因子]，请确认!");
        } else if (code.startsWith("a")) {
            result = AirFactorEnum.lookup(code);
            if (result == null) log.warn("[" + code + "]可能是[气监测因子]，请确认!");
        } else if (code.startsWith("L")) {
            result = SoundFactorEnum.lookup(code);
            if (result == null) log.warn("[" + code + "]可能是[声监测因子]，请确认!");
        } else if (code.startsWith("e") && code.length() > 4) {
            String mycode = getWorkingConditionCode(code);
            result = SewageDischargeFactorEnum.lookup(mycode);
            if (result == null) log.warn("[" + code + "]可能是[污水排放工况监测因子]，请确认!");
        } else if (code.startsWith("g") && code.length() > 4) {
            String mycode = getWorkingConditionCode(code);
            result = GasDischargeFactorEnum.lookup(mycode);
            if (result == null) log.warn("[" + code + "]可能是[烟气排放工况监测因子]，请确认!");
        } else if (code.startsWith("i") || code.startsWith("I")) {
            result = OneSiteInformationFactorEnum.lookup(code);
            if (result == null) log.warn("[" + code + "]可能是[现场端信息编码]，请确认!");
        } else if (code.startsWith("t")) {
            result = SoilFactorEnum.lookup(code);
            if (result == null) log.warn("[" + code + "]可能是[土壤监测因子]，请确认!");
        } else {
            //noting to do!
        }
        return result;
    }

    public static FactorTypeEnum getFactorType(String code) {
        FactorTypeEnum result = null;

        Object type = getFactorByCode(code);
        if (type instanceof WaterFactorEnum) {
            result = FactorTypeEnum.WATER;
        } else if (type instanceof AirFactorEnum) {
            result = FactorTypeEnum.AIR;
        } else if (type instanceof SoundFactorEnum) {
            result = FactorTypeEnum.SOUND;
        } else if (type instanceof SewageDischargeFactorEnum) {
            result = FactorTypeEnum.SEWAGE_DISCHARGE;
        } else if (type instanceof GasDischargeFactorEnum) {
            result = FactorTypeEnum.GAS_DISCHARGE;
        } else if (type instanceof OneSiteInformationFactorEnum) {
            result = FactorTypeEnum.ON_SITE_INFORMATION;
        } else if (type instanceof SoilFactorEnum) {
            result = FactorTypeEnum.SOIL;
        }
        return result;
    }

    public static String getWorkingConditionCode(String code) {
        return code.substring(0, 4);
    }

    public static TwoMemberObject<String, String> tryGetDeviceNumberOfDeviceRunningStatus(String name) {
        String sbrs = null;
        String sbrt = null;

        Matcher matcher = SB_RS_PATTERN.matcher(name);
        if (matcher.find()) {
            sbrs = matcher.group(1);
        }
        matcher = SB_RT_PATTERN.matcher(name);
        if (matcher.find()) {
            sbrt = matcher.group(1);
        }

        if (sbrs == null && sbrt == null) {
            return null;
        } else {
            return new TwoMemberObject(sbrs, sbrt);
        }
    }

}
