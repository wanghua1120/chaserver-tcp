/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Hua Wang
 *         Created On: 2019/5/6 19:12
 */
public class DateTimeUtil {
    public static ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDateTime toLocalDateTime(String s) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(s, dtf);
        return ldt;
    }

    public static LocalDateTime toLocalDateTime(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }

    public static Date toDateTime(LocalDateTime ldt) {
        //Instant instant = ldt.toInstant(zoneOffset);
        Instant instant = ldt.atZone(zoneId).toInstant();
        Date d = Date.from(instant);
        return d;
    }

    public static Date toDateTime(String s) {
        LocalDateTime ldt = toLocalDateTime(s);
        Date d = toDateTime(ldt);
        return d;
    }

    public static String formatDateTime(Date d) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = toLocalDateTime(d);
        String s = dtf.format(localDateTime);
        return s;
    }
}
