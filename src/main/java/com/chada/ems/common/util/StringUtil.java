/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
public class StringUtil {
    public static String fatalMessage(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        sb.append("##########################   << FATAL >>  #############################\n");
        sb.append("###  \n");
        sb.append("###   ").append(msg).append("\n");
        sb.append("###  \n");
        sb.append("#######################################################################\n\n");
        return sb.toString();
    }

    public static String getStackTrace(Throwable t) {
        StringWriter mysw = new StringWriter();
        PrintWriter writer = new PrintWriter(mysw);
        t.printStackTrace(writer);
        String result = mysw.toString();
        writer.close();
        return result;
    }
}
