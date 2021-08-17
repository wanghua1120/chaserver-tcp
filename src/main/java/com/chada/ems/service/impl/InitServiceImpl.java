/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.service.impl;

import com.chada.ems.common.domain.traffic.*;
import com.chada.ems.repository.CodingCommandRepository;
import com.chada.ems.repository.CodingSystemRepository;
import com.chada.ems.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hua Wang
 * Created On: 2019/4/16 16:22
 */
@Service
public class InitServiceImpl implements InitService {
    private static ReentrantLock lock = new ReentrantLock();
    @Autowired
    private CodingSystemRepository codingSystemRepo;
    @Autowired
    private CodingCommandRepository codingCommandRepo;

    @PostConstruct
    @Override
    public void initialize() {
        lock.lock();
        try {
            setupCodingSystemData();
            setupCodingCommandData();
        } finally {
            lock.unlock();
        }
    }

    private void setupCodingSystemData() {
        if (codingSystemRepo.count() > 0) return;

        List<CodingSystem> data = Arrays.asList(
                CodingSystem.of(null, "21", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "地表水质量监测", null),
                CodingSystem.of(null, "22", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "空气质量监测", null),
                CodingSystem.of(null, "23", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "声环境质量监测", null),
                CodingSystem.of(null, "24", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "地下水质量监测", null),
                CodingSystem.of(null, "25", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "土壤质量监测", null),
                CodingSystem.of(null, "26", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "海水质量监测", null),
                CodingSystem.of(null, "27", CodingSystemTypeEnum.ENVIRONMENTAL_QUALITY, "挥发性有机物监测", null),
                CodingSystem.of(null, "31", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "大气环境污染源", null),
                CodingSystem.of(null, "32", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "地表水体环境污染源", null),
                CodingSystem.of(null, "33", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "地下水体环境污染源", null),
                CodingSystem.of(null, "34", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "海洋环境污染源", null),
                CodingSystem.of(null, "35", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "土壤环境污染源", null),
                CodingSystem.of(null, "36", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "声环境污染源", null),
                CodingSystem.of(null, "37", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "振动环境污染源", null),
                CodingSystem.of(null, "38", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "放射性环境污染源", null),
                CodingSystem.of(null, "39", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "工地扬尘污染源", null),
                CodingSystem.of(null, "41", CodingSystemTypeEnum.ENVIRONMENTAL_POLLUTION_SOURCE, "电磁环境污染源", null),
                CodingSystem.of(null, "51", CodingSystemTypeEnum.WORKING_CONDITION, "烟气排放过程监控", null),
                CodingSystem.of(null, "52", CodingSystemTypeEnum.WORKING_CONDITION, "污水排放过程监控", null),
                CodingSystem.of(null, "91", CodingSystemTypeEnum.SYSTEM_INTERACTION, "系统交互", null)
        );

        codingSystemRepo.saveAll(data);
    }

    private void setupCodingCommandData() {
        if (codingCommandRepo.count() > 0) return;

        List<CodingCommand> data = Arrays.asList(
                CodingCommand.of(null, "1000", CodingCommandTypeEnum.INITIALIZATION, "设置超时时间及重发次数", null),
                CodingCommand.of(null, "1011", CodingCommandTypeEnum.PARAMETER, "提取或上传现场机时间", null),
                CodingCommand.of(null, "1012", CodingCommandTypeEnum.PARAMETER, "设置现场机时间", null),
                CodingCommand.of(null, "1013", CodingCommandTypeEnum.PARAMETER, "现场机时间校准请求", null),
                CodingCommand.of(null, "1061", CodingCommandTypeEnum.PARAMETER, "提取或上传实时数据间隔", null),
                CodingCommand.of(null, "1062", CodingCommandTypeEnum.PARAMETER, "设置实时数据间隔", null),
                CodingCommand.of(null, "1063", CodingCommandTypeEnum.PARAMETER, "提取或上传分钟数据间隔", null),
                CodingCommand.of(null, "1064", CodingCommandTypeEnum.PARAMETER, "设置分钟数据间隔", null),
                CodingCommand.of(null, "1072", CodingCommandTypeEnum.PARAMETER, "设置现场机密码", null),
                CodingCommand.of(null, "2011", CodingCommandTypeEnum.DATA, "取或上传污染物实时数据", null),
                CodingCommand.of(null, "2012", CodingCommandTypeEnum.DATA, "停止察看污染物实时数据", null),
                CodingCommand.of(null, "2021", CodingCommandTypeEnum.DATA, "取或上传设备运行状态数据", null),
                CodingCommand.of(null, "2022", CodingCommandTypeEnum.DATA, "停止察看设备运行状态", null),
                CodingCommand.of(null, "2031", CodingCommandTypeEnum.DATA, "取或上传污染物日历史数据", null),
                CodingCommand.of(null, "2041", CodingCommandTypeEnum.DATA, "取或上传设备运行时间日历史数据", null),
                CodingCommand.of(null, "2051", CodingCommandTypeEnum.DATA, "取或上传污染物分钟数据", null),
                CodingCommand.of(null, "2061", CodingCommandTypeEnum.DATA, "取或上传污染物小时数据", null),
                CodingCommand.of(null, "2081", CodingCommandTypeEnum.DATA, "上传数采仪开机时间", null),
                CodingCommand.of(null, "3011", CodingCommandTypeEnum.CONTROL, "零点校准量程校准", null),
                CodingCommand.of(null, "3012", CodingCommandTypeEnum.CONTROL, "即时采样", null),
                CodingCommand.of(null, "3013", CodingCommandTypeEnum.CONTROL, "启动清洗/反吹", null),
                CodingCommand.of(null, "3014", CodingCommandTypeEnum.CONTROL, "比对采样", null),
                CodingCommand.of(null, "3015", CodingCommandTypeEnum.CONTROL, "超标留样或上传超标留样", null),
                CodingCommand.of(null, "3016", CodingCommandTypeEnum.CONTROL, "设置采样时间周期", null),
                CodingCommand.of(null, "3017", CodingCommandTypeEnum.CONTROL, "提取或上传采样时间周期", null),
                CodingCommand.of(null, "3018", CodingCommandTypeEnum.CONTROL, "提取或上传出样时间", null),
                CodingCommand.of(null, "3019", CodingCommandTypeEnum.CONTROL, "提取或上传设备唯一标识", null),
                CodingCommand.of(null, "3020", CodingCommandTypeEnum.CONTROL, "提取或上传现场机信息", null),
                CodingCommand.of(null, "3021", CodingCommandTypeEnum.CONTROL, "设置现场机参数", null),
                CodingCommand.of(null, "9011", CodingCommandTypeEnum.INTERACTIVE, "请求应答", null),
                CodingCommand.of(null, "9012", CodingCommandTypeEnum.INTERACTIVE, "执行结果", null),
                CodingCommand.of(null, "9013", CodingCommandTypeEnum.INTERACTIVE, "通知应答", null),
                CodingCommand.of(null, "9014", CodingCommandTypeEnum.INTERACTIVE, "数据应答", null)
        );

        codingCommandRepo.saveAll(data);
    }
}