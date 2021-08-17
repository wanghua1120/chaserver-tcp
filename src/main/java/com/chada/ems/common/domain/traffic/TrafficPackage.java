/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.domain.traffic;

import com.chada.ems.common.domain.base.AuditBaseEntity;
import com.chada.ems.common.domain.base.BaseEntity;
import com.chada.ems.common.util.BytesUtil;
import com.chada.ems.common.util.StringUtil;
import com.chada.ems.core.spring.SpringApplicationContext;
import com.chada.ems.repository.CodingCommandRepository;
import com.chada.ems.repository.CodingSystemRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chada.ems.common.domain.base.BaseEntity.ID_GENERATOR_TABLE_NAME;
import static com.chada.ems.common.domain.traffic.TrafficPackage.TABLE_NAME;

/**
 * 通讯包
 *
 * @author Hua Wang
 *         Created On: 2019/4/16 9:32
 */
@Slf4j
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
//@Table(name=TABLE_NAME,uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
@Table(name = TABLE_NAME)
@TableGenerator(name = TABLE_NAME, table = ID_GENERATOR_TABLE_NAME, pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = TABLE_NAME, allocationSize = 1)
public class TrafficPackage extends BaseEntity {
    public static final int CRC_LENGTH = 4;
    public static final int SEGMENT_LENGTH = 4;
    public static final String TABLE_NAME = TABLE_PREFIX + "traffic_package";
    private static final Pattern CP_PATTERN = Pattern.compile("CP=&&([\\S\\s]*)&&");
    private static final Pattern ON_SITE_KEY_PATTERN = Pattern.compile("i[0-9]+-Info");

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;

    /**
     * 包类型
     */
    @Enumerated(EnumType.STRING)
    private PackageTypeEnum packageType;
    /**
     * 数据段长度
     */
    @Column(length = SEGMENT_LENGTH)
    private Integer segmentLength;
    /**
     * CRC 校验码
     */
    @Column(length = CRC_LENGTH)
    private String crc;
    /**
     * 接收的原始数据
     */
    @NotNull
    @Column(length = 10240)
    private String rawText;

    /**
     * QN 请求编码
     */
    @NotBlank
    private String qn;
    /**
     * ST 系统编码 外键（二级缓存）
     */
    private Long codingSystemId;
    /**
     * CN 命令编码 外键（二级缓存）
     */
    @NotBlank
    private Long codingCommandId;
    /**
     * 访问密码
     */
    @JsonIgnore
    private String pw;
    /**
     * 设备唯一编号
     */
    @NotBlank
    private String mn;
    /**
     * 拆分包及应答标志
     */
    private Integer flag;
    /**
     * 总包数
     */
    private String pnum;
    /**
     * 包号
     */
    private String pno;

    /**
     * Remote Ip
     */
    private String remoteIp;
    /**
     * Remote Port
     */
    private String remotePort;

    private TrafficPackage() {

    }

    public static TrafficPackage createGenericPackage(String singleRawText, String remoteIp, String remotePort) {
        int segmentLength = Integer.parseInt(singleRawText.substring(2, 6));
        String crc = singleRawText.substring(6 + segmentLength, 10 + segmentLength);

        TrafficPackage tp = new TrafficPackage()
                .setPackageType(PackageTypeEnum.GENERIC_PACKAGE)
                .setRawText(singleRawText)
                .setRemoteIp(remoteIp)
                .setRemotePort(remotePort)
                .setSegmentLength(segmentLength)
                .setCrc(crc);

        String dataSegment = singleRawText.substring(6, segmentLength + 6);
        setDataSegment(tp, dataSegment);

        return tp;
    }

    public static TrafficPackage createHttpPackage(String singleRawText, String remoteIp, String remotePort) {
        TrafficPackage tp = new TrafficPackage()
                .setPackageType(PackageTypeEnum.HTTP_PACKAGE)
                .setRawText(singleRawText)
                .setRemoteIp(remoteIp)
                .setRemotePort(remotePort);

        return tp;
    }

    public static List<List<Pair<String, String>>> getDataRegionValues(String singleRawText) {
        List<List<Pair<String, String>>> result = new ArrayList<>();

        String cp = getCp(singleRawText, 1);

        // 分割项目
        String[] projects = cp.split(";");
        for (String project : projects) {
            if (StringUtils.isEmpty(project)) continue;
            List<Pair<String, String>> pairs = new ArrayList<>();
            // 分割项目下的类别
            String[] categories = project.split(",");
            for (String category : categories) {
                String[] nameValue = category.split("=");
                String name = nameValue[0];
                String value = nameValue[1];
                pairs.add(Pair.of(name, value));
            }
            if (pairs.size() > 0) result.add(pairs);
        }

        return result;
    }

    public static String decodeOnSiteLog(String logText) {
        String result = logText;
        if (logText.startsWith("//R:")) {
            String hex = logText.replaceAll("(//R:)|(//)|( )", "");
            try {
                byte[] bytes = BytesUtil.hex2byte(hex);
                result = new String(bytes, "utf-8");
            } catch (Throwable ex) {
                log.error(StringUtil.getStackTrace(ex));
            }
        }
        return result;
    }

    private static void setDataSegment(TrafficPackage trafficPackage, String dataSegmentText) {
        Map<String, String> fieldMap = splitDataSegmentFields(dataSegmentText);

        ApplicationContext appCtx = SpringApplicationContext.getApplicationContext();

        String st = fieldMap.get("ST");
        CodingSystemRepository codingSystemRepo = appCtx.getBean(CodingSystemRepository.class);
        CodingSystem codingSystem = codingSystemRepo.findByCode(st);
        if (codingSystem == null) {
            log.error("未知的系统编码：" + st);
        }

        String cn = fieldMap.get("CN");
        CodingCommandRepository codingCommandRepo = appCtx.getBean(CodingCommandRepository.class);
        CodingCommand codingCommand = codingCommandRepo.findByCode(cn);
        if (codingCommand == null) {
            log.error("未知的命令编码：" + cn);
        }

        String qn = fieldMap.get("QN");
        String pw = fieldMap.get("PW");
        String mn = fieldMap.get("MN");
        int flag = Integer.parseInt(fieldMap.get("Flag"));
        String pnum = fieldMap.get("PNUM");
        String pno = fieldMap.get("PNO");

        trafficPackage
                .setCodingSystemId(codingSystem != null ? codingSystem.getId() : null)
                .setCodingCommandId(codingCommand != null ? codingCommand.getId() : null)
                .setQn(qn)
                .setPw(pw)
                .setMn(mn)
                .setFlag(flag)
                .setPnum(pnum)
                .setPno(pno);
    }

    private static Map<String, String> splitDataSegmentFields(String dataSegmentText) {
        Map<String, String> result = new LinkedHashMap<>();

        String cpRawText = getCp(dataSegmentText, 0);
        String textWithoutCp = dataSegmentText.replace(cpRawText, "");
        String[] items = textWithoutCp.split(";");
        for (String item : items) {
            String[] pair = item.split("=");
            result.put(pair[0], pair[1]);
        }

        return result;
    }

    private static String getCp(String singleRawText, int groupIndex) {
        String cpRawText = null;
        Matcher matcher = CP_PATTERN.matcher(singleRawText);
        while (matcher.find()) {
            cpRawText = matcher.group(groupIndex);
            break;
        }
        return cpRawText;
    }

    private static boolean isOnSiteKey(String text) {
        Matcher matcher = ON_SITE_KEY_PATTERN.matcher(text);
        return matcher.find();
    }


}