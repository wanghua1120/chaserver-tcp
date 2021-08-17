/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hua Wang
 * Created On: 2019/4/28 18:44
 */
@Slf4j
@Component
public class PackageReaderHelper {
    private static final Pattern GENERIC_PACKAG_PATTERN = Pattern.compile("^##" + "([0-9]{4})");
    private static final Pattern HTTP_PACKAGE_PATTERN = Pattern.compile("^" + "（(GET)|(POST)|(PUT)|(DELETE) ", Pattern.CASE_INSENSITIVE);

    public boolean isGenericPackage(String rawText) {
        Matcher matcher = GENERIC_PACKAG_PATTERN.matcher(rawText);
        return matcher.find();
    }

    public boolean isHttpPackage(String rawText) {
        Matcher matcher = HTTP_PACKAGE_PATTERN.matcher(rawText);
        return matcher.find();
    }
}
