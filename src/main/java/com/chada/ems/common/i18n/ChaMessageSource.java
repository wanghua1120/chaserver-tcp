/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hua Wang
 * Created On: 2018/8/16 0:22
 */
public class ChaMessageSource {

    private static Map<String, ResourceBundleMessageSource> messageSourceMap = new ConcurrentHashMap<>();

    public static String getMessage(String resourceBaseName, String code) {
        return getMessage(resourceBaseName, code, null);
    }

    public static String getMessage(String resourceBaseName, String code, Object[] args) {
        ResourceBundleMessageSource myms = messageSourceMap.get(resourceBaseName);
        if (myms == null) {
            myms = new ResourceBundleMessageSource();
            myms.setBasename(resourceBaseName);
            myms.setDefaultEncoding(StandardCharsets.UTF_8.name());
            myms.setFallbackToSystemLocale(false);

            messageSourceMap.put(resourceBaseName, myms);
        }

        Locale locale = LocaleContextHolder.getLocale();

        String message = myms.getMessage(code, args, locale);
        return message;
    }

}
