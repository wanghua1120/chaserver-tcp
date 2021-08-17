/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.service.impl;

import com.chada.ems.common.TwoMemberObject;
import com.chada.ems.common.domain.factor.*;
import com.chada.ems.common.domain.monitor.*;
import com.chada.ems.common.domain.traffic.PackageTypeEnum;
import com.chada.ems.common.domain.traffic.TrafficPackage;
import com.chada.ems.common.util.DateTimeUtil;
import com.chada.ems.common.util.FactorUtil;
import com.chada.ems.common.util.StringUtil;
import com.chada.ems.repository.*;
import com.chada.ems.service.TrafficPackageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Hua Wang
 *         Created On: 2019/4/23 22:24
 */
@Slf4j
@Service
public class TrafficPackageServiceImpl implements TrafficPackageService {
    private static final String GENERIC_PACKAGE_HEADER_PREFIX = "##";

    @Autowired
    private TrafficPackageRepository trafficPackageRepo;
    @Autowired
    private SampleWaterRepository sampleWaterRepo;
    @Autowired
    private SampleAirRepository sampleAirRepo;
    @Autowired
    private SampleSoundRepository sampleSoundRepo;
    @Autowired
    private SampleGasDischargeRepository sampleGasDischargeRepo;
    @Autowired
    private SampleSewageDischargeRepository sampleSewageDischargeRepo;
    @Autowired
    private SampleOnSiteInformationRepository sampleOnSiteInformationRepo;
    @Autowired
    private SampleDeviceRunningStatusRepository sampleDeviceRunningStatusRepo;
    @Autowired
    private SampleSoilRepository sampleSoilRepo;
    private Set<String> soundFactorCodes = new LinkedHashSet<>();
    private Set<String> sewageDischargeCodes = new LinkedHashSet<>();

    @Transactional
    @Override
    public void saveTrafficPackages(String rawText, String remoteIp, String remotePort, PackageTypeEnum packageType) {

        try {
            List<SampleWater> sampleWaters = new ArrayList<>();
            List<SampleAir> sampleAirs = new ArrayList<>();
            List<SampleSound> sampleSounds = new ArrayList<>();
            List<SampleGasDischarge> sampleGasDischarges = new ArrayList<>();
            List<SampleSewageDischarge> sampleSewageDischarges = new ArrayList<>();
            List<SampleOnSiteInformation> sampleOnSiteInformations = new ArrayList<>();
            List<SampleDeviceRunningStatus> sampleDeviceRunningStatuses = new ArrayList<>();
            List<SampleSoil> sampleSoils = new ArrayList<>();

            if (packageType.equals(PackageTypeEnum.GENERIC_PACKAGE)) {
                for (String singleRawText : rawText.split(GENERIC_PACKAGE_HEADER_PREFIX)) {
                    if (StringUtils.isEmpty(singleRawText)) continue;
                    singleRawText = GENERIC_PACKAGE_HEADER_PREFIX + singleRawText;
                    TrafficPackage tp = TrafficPackage.createGenericPackage(singleRawText, remoteIp, remotePort);
                    tp = trafficPackageRepo.save(tp);

                    soundFactorCodes.clear();
                    sewageDischargeCodes.clear();

                    List<List<Pair<String, String>>> dataRegionProjects = tp.getDataRegionValues(singleRawText);
                    for (List<Pair<String, String>> pairs : dataRegionProjects) {
                        Map<String, Object> factorMap = getFactors(pairs);
                        for (Map.Entry<String, Object> entry : factorMap.entrySet()) {
                            String factorCode = entry.getKey();
                            Object factor = entry.getValue();

                            if (factor instanceof WaterFactorEnum) {
                                SampleWater sampleWater = new SampleWater();
                                sampleWater.setFactor((WaterFactorEnum) factor);
                                setTrafficPackageFields(sampleWater, tp);
                                setGenericFields(sampleWater, dataRegionProjects);
                                processWaterData(sampleWater, factorCode, pairs);
                                sampleWaters.add(sampleWater);
                            } else if (factor instanceof AirFactorEnum) {
                                SampleAir sampleAir = new SampleAir();
                                sampleAir.setFactor((AirFactorEnum) factor);
                                setTrafficPackageFields(sampleAir, tp);
                                setGenericFields(sampleAir, dataRegionProjects);
                                processAirData(sampleAir, factorCode, pairs);
                                sampleAirs.add(sampleAir);
                            } else if (factor instanceof SoundFactorEnum) {
                                if (soundFactorCodes.contains(factorCode)) {
                                    continue;
                                } else {
                                    soundFactorCodes.add(factorCode);
                                }
                                SampleSound sampleSound = new SampleSound();
                                sampleSound.setFactor((SoundFactorEnum) factor);
                                setTrafficPackageFields(sampleSound, tp);
                                setGenericFields(sampleSound, dataRegionProjects);
                                processSoundData(sampleSound, factorCode, dataRegionProjects);
                                sampleSounds.add(sampleSound);
                            } else if (factor instanceof GasDischargeFactorEnum) {
                                SampleGasDischarge sampleGasDischarge = new SampleGasDischarge();
                                sampleGasDischarge.setFactor((GasDischargeFactorEnum) factor);
                                setTrafficPackageFields(sampleGasDischarge, tp);
                                setGenericFields(sampleGasDischarge, dataRegionProjects);
                                processGasDischarge(sampleGasDischarge, factorCode, pairs);
                                sampleGasDischarges.add(sampleGasDischarge);
                            } else if (factor instanceof SewageDischargeFactorEnum) {
                                if (soundFactorCodes.contains(factorCode)) {
                                    continue;
                                } else {
                                    soundFactorCodes.add(factorCode);
                                }
                                SampleSewageDischarge sampleSewageDischarge = new SampleSewageDischarge();
                                sampleSewageDischarge.setFactor((SewageDischargeFactorEnum) factor);
                                setTrafficPackageFields(sampleSewageDischarge, tp);
                                setGenericFields(sampleSewageDischarge, dataRegionProjects);
                                processSewageDischarge(sampleSewageDischarge, factorCode, dataRegionProjects);
                                sampleSewageDischarges.add(sampleSewageDischarge);
                            } else if (factor instanceof OneSiteInformationFactorEnum) {
                                SampleOnSiteInformation sampleOnSiteInformation = new SampleOnSiteInformation();
                                sampleOnSiteInformation.setFactor((OneSiteInformationFactorEnum) factor);
                                setTrafficPackageFields(sampleOnSiteInformation, tp);
                                setGenericFields(sampleOnSiteInformation, dataRegionProjects);
                                processOnSiteInformation(sampleOnSiteInformation, factorCode, pairs, dataRegionProjects);
                                sampleOnSiteInformations.add(sampleOnSiteInformation);
                            } else if (factor instanceof SoilFactorEnum) {
                                SampleSoil sampleSoil = new SampleSoil();
                                sampleSoil.setFactor((SoilFactorEnum) factor);
                                setTrafficPackageFields(sampleSoil, tp);
                                setGenericFields(sampleSoil, dataRegionProjects);
                                processSoilData(sampleSoil, factorCode, pairs);
                                sampleSoils.add(sampleSoil);
                            }
                        }
                    }
                    processDeviceRunningStatusData(tp, dataRegionProjects, sampleDeviceRunningStatuses);
                }
            } else if (packageType.equals(PackageTypeEnum.HTTP_PACKAGE)) {
                //TODO
            }

            if (!CollectionUtils.isEmpty(sampleWaters))
                sampleWaterRepo.saveAll(sampleWaters);
            if (!CollectionUtils.isEmpty(sampleAirs))
                sampleAirRepo.saveAll(sampleAirs);
            if (!CollectionUtils.isEmpty(sampleSounds))
                sampleSoundRepo.saveAll(sampleSounds);
            if (!CollectionUtils.isEmpty(sampleGasDischarges))
                sampleGasDischargeRepo.saveAll(sampleGasDischarges);
            if (!CollectionUtils.isEmpty(sampleSewageDischarges))
                sampleSewageDischargeRepo.saveAll(sampleSewageDischarges);
            if (!CollectionUtils.isEmpty(sampleOnSiteInformations))
                sampleOnSiteInformationRepo.saveAll(sampleOnSiteInformations);
            if (!CollectionUtils.isEmpty(sampleDeviceRunningStatuses))
                sampleDeviceRunningStatusRepo.saveAll(sampleDeviceRunningStatuses);
            if (!CollectionUtils.isEmpty(sampleSoils))
                sampleSoilRepo.saveAll(sampleSoils);

        } catch (Throwable ex) {
            log.error(StringUtil.getStackTrace(ex));
            //throw new ChaRuntimeException("通信包保存失败。", ex);
        }
    }

    private void setTrafficPackageFields(AbstractTimeSeries sample, TrafficPackage tp) {
        sample.setTrafficPackageId(tp.getId());
        sample.setMn(tp.getMn());
        sample.setCodingSystemId(tp.getCodingSystemId());
        sample.setCodingCommandId(tp.getCodingCommandId());
    }

    private Map<String, Object> getFactors(List<Pair<String, String>> pairs) {
        Map<String, Object> factors = new LinkedHashMap<>();
        for (Pair<String, String> pair : pairs) {
            String key = pair.getFirst();
            //Object value = pair.getSecond();
            Object factor = FactorUtil.getFactorByName(key);
            if (factor == null) continue;
            String factorCode = FactorUtil.getFactorCode(key);
            factors.put(factorCode, factor);
        }
        return factors;
    }

    private void setGenericFields(AbstractTimeSeries sampleGeneric, List<List<Pair<String, String>>> dataRegionProjects) {
        for (List<Pair<String, String>> pairs : dataRegionProjects) {
            for (Pair<String, String> pair : pairs) {
                String name = pair.getFirst();
                String value = pair.getSecond();

                switch (name) {
                    case "DataTime":
                        sampleGeneric.setDataTime(DateTimeUtil.toDateTime(value));
                        break;
                    default:
                }
            }

        }
    }

    private void processWaterData(SampleWater sampleWater, String factorCode, List<Pair<String, String>> pairs) {
        for (Pair<String, String> pair : pairs) {
            String name = pair.getFirst();
            String value = pair.getSecond();
            if (!name.contains(factorCode)) continue;

            String fieldName = name.replace(factorCode + "-", "");
            setPollutantField(sampleWater, fieldName, value);
        }
    }

    private void processSoundData(SampleSound sampleSound, String factorCode, List<List<Pair<String, String>>> dataRegionProjects) {
        for (List<Pair<String, String>> pairs : dataRegionProjects) {
            for (Pair<String, String> pair : pairs) {
                String name = pair.getFirst();
                String value = pair.getSecond();
                if (!name.contains(factorCode)) continue;

                String fieldName = name.replace(factorCode + "-", "");
                switch (fieldName) {
                    case "Data":
                        sampleSound.setData(new BigDecimal(value));
                        break;
                    case "DayData":
                        sampleSound.setDayData(new BigDecimal(value));
                        break;
                    case "NightData":
                        sampleSound.setNightData(new BigDecimal(value));
                        break;
                    default:
                        setPollutantField(sampleSound, fieldName, value);
                }
            }
        }
    }

    private void processAirData(SampleAir sampleAir, String factorCode, List<Pair<String, String>> pairs) {
        for (Pair<String, String> pair : pairs) {
            String name = pair.getFirst();
            String value = pair.getSecond();
            if (!name.contains(factorCode)) continue;

            String fieldName = name.replace(factorCode + "-", "");
            setPollutantField(sampleAir, fieldName, value);
        }
    }

    private void processGasDischarge(SampleGasDischarge sampleGasDischarge, String factorCode, List<Pair<String, String>> pairs) {
        for (Pair<String, String> pair : pairs) {
            String name = pair.getFirst();
            String value = pair.getSecond();
            if (!name.contains(factorCode)) continue;

            String fieldName = name.replace(factorCode + "-", "");
            setPollutantField(sampleGasDischarge, fieldName, value);
        }
    }

    private void processSewageDischarge(SampleSewageDischarge sampleSewageDischarge, String factorCode, List<List<Pair<String, String>>> dataRegionProjects) {
        String code = FactorUtil.getWorkingConditionCode(factorCode);
        String deviceNumber = factorCode.substring(code.length());
        sampleSewageDischarge.setWorkingConditionDeviceNumber(deviceNumber);
        for (List<Pair<String, String>> pairs : dataRegionProjects) {
            for (Pair<String, String> pair : pairs) {
                String name = pair.getFirst();
                String value = pair.getSecond();
                if (!name.contains(factorCode)) continue;


                String fieldName = name.replace(factorCode + "-", "");
                setPollutantField(sampleSewageDischarge, fieldName, value);
            }
        }
    }

    private void processOnSiteInformation(SampleOnSiteInformation sampleOnSiteInformation, String factorCode, List<Pair<String, String>> pairs, List<List<Pair<String, String>>> dataRegionProjects) {
        if (pairs.size() == 1) {
            Pair<String, String> pair = pairs.get(0);
            String name = pair.getFirst();
            String value = pair.getSecond();

            String fieldName = name.replace(factorCode + "-", "");
            switch (fieldName) {
                case "Info":
                    String info = TrafficPackage.decodeOnSiteLog(value);
                    sampleOnSiteInformation.setInfoValue(info);
                    break;
                default:
            }

            for (List<Pair<String, String>> mypairs : dataRegionProjects) {
                for (Pair<String, String> mypair : mypairs) {
                    name = mypair.getFirst();
                    value = mypair.getSecond();
                    switch (name) {
                        case "PolId":
                            Object factor = FactorUtil.getFactorByCode(value);
                            if (factor == null) {
                                log.error("无效的 PolId 编码 [" + value + "]。");
                            } else {
                                sampleOnSiteInformation.setPolId(factor.toString());
                                FactorTypeEnum type = FactorUtil.getFactorType(value);
                                sampleOnSiteInformation.setPolType(type);
                            }
                            break;
                        default:
                    }
                }
            }
        } else if (pairs.size() > 1) {
            for (Pair<String, String> pair : pairs) {
                String name = pair.getFirst();
                String value = pair.getSecond();

                String fieldName = name.replace(factorCode + "-", "");
                switch (fieldName) {
                    case "PolId":
                        Object factor = FactorUtil.getFactorByCode(value);
                        if (factor == null) {
                            log.error("无效的 PolId 编码 [" + value + "]。");
                        } else {
                            sampleOnSiteInformation.setPolId(factor.toString());
                            FactorTypeEnum type = FactorUtil.getFactorType(value);
                            sampleOnSiteInformation.setPolType(type);
                        }
                        break;
                    case "Info":
                        String info = TrafficPackage.decodeOnSiteLog(value);
                        sampleOnSiteInformation.setInfoValue(info);
                        break;
                    default:
                }
            }
        }
    }

    private void processSoilData(SampleSoil sampleSoil, String factorCode, List<Pair<String, String>> pairs) {
        for (Pair<String, String> pair : pairs) {
            String name = pair.getFirst();
            String value = pair.getSecond();
            if (!name.contains(factorCode)) continue;

            String fieldName = name.replace(factorCode + "-", "");
            setPollutantField(sampleSoil, fieldName, value);
        }
    }

    private void processDeviceRunningStatusData(TrafficPackage tp, List<List<Pair<String, String>>> dataRegionProjects, List<SampleDeviceRunningStatus> sampleDeviceRunningStatuses) {
        for (List<Pair<String, String>> pairs : dataRegionProjects) {
            for (Pair<String, String> pair : pairs) {
                String name = pair.getFirst();
                String value = pair.getSecond();

                TwoMemberObject<String, String> twoMemberObject = FactorUtil.tryGetDeviceNumberOfDeviceRunningStatus(name);
                if (twoMemberObject == null) continue;

                String sbrs = twoMemberObject.getFirstValue();
                String sbrt = twoMemberObject.getSecondValue();
                String mysbrs = "SB" + sbrs + "-RS";
                String mysbrt = "SB" + sbrt + "-RT";

                String fieldName = "";
                if (name.equals(mysbrs)) {
                    fieldName = name.replace(sbrs, "");
                } else if (name.equals(mysbrt)) {
                    fieldName = name.replace(sbrt, "");
                }

                switch (fieldName) {
                    case "SB-RS":
                        SampleDeviceRunningStatus sampleDeviceRunningStatus = new SampleDeviceRunningStatus();
                        setTrafficPackageFields(sampleDeviceRunningStatus, tp);
                        setGenericFields(sampleDeviceRunningStatus, dataRegionProjects);
                        sampleDeviceRunningStatuses.add(sampleDeviceRunningStatus);

                        sampleDeviceRunningStatus.setSbRs(DeviceRunningStatusEnum.lookup(value));
                        sampleDeviceRunningStatus.setSbRsDeviceNumber(sbrs);
                        break;
                    case "SB-RT":
                        sampleDeviceRunningStatus = new SampleDeviceRunningStatus();
                        setTrafficPackageFields(sampleDeviceRunningStatus, tp);
                        setGenericFields(sampleDeviceRunningStatus, dataRegionProjects);
                        sampleDeviceRunningStatuses.add(sampleDeviceRunningStatus);

                        sampleDeviceRunningStatus.setSbRt(new BigDecimal(value));
                        sampleDeviceRunningStatus.setSbRtDeviceNumber(sbrt);
                        break;
                    default:
                }
            }
        }
    }

    private void setPollutantField(AbstractPollutant abstractPollutant, String name, String value) {
        switch (name) {
            case "SampleTime":
                abstractPollutant.setSampleTime(DateTimeUtil.toDateTime(value));
                break;
            case "Rtd":
                abstractPollutant.setRtd(new BigDecimal(value));
                break;
            case "Min":
                abstractPollutant.setMin(new BigDecimal(value));
                break;
            case "Avg":
                abstractPollutant.setAvg(new BigDecimal(value));
                break;
            case "Max":
                abstractPollutant.setMax(new BigDecimal(value));
                break;
            case "ZsRtd":
                abstractPollutant.setZsRtd(new BigDecimal(value));
                break;
            case "ZsMin":
                abstractPollutant.setZsMin(new BigDecimal(value));
                break;
            case "ZsAvg":
                abstractPollutant.setZsAvg(new BigDecimal(value));
                break;
            case "ZsMax":
                abstractPollutant.setZsMax(new BigDecimal(value));
                break;
            case "Flag":
                abstractPollutant.setFlag(FlagEnum.valueOf(value));
                break;
            case "EFlag":
                abstractPollutant.setEFlag(value);
                break;
            case "Cou":
                abstractPollutant.setCou(new BigDecimal(value));
                break;
            case "SBxxx-RS":
                //TODO
                break;
            case "SBxxx-RT":
                //TODO
                break;
            case "SN":
                abstractPollutant.setSn(value);
                break;
            case "Alarm":
                abstractPollutant.setAlarm(value);
                break;
            case "State":
                abstractPollutant.setState(value);
                break;
            default:
        }
    }

}
