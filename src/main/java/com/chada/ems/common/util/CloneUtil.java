/*
 * Copyright (c) 2019, Chada Technologies. All rights reserved.
 * Chada PROPRIETARY/CONFIDENTIAL.
 */
package com.chada.ems.common.util;

import com.chada.ems.common.exception.support.ChaError;
import com.chada.ems.common.exception.support.ChaErrorName;
import com.chada.ems.common.exception.support.ChaRuntimeException;

import java.io.*;

/**
 * @author Hua Wang
 * Created On: 2019/4/9 18:07
 */
public class CloneUtil {
    /**
     * 深度克隆
     *
     * @param obj 要克隆的对象
     * @return 返回深度克隆后的对象
     */
    public static Object depthClone(Object obj) {
        try {
            // 字节数组输出流，暂存到内存中
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 序列化
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            // 反序列化
            return ois.readObject();
        } catch (ClassNotFoundException e1) {
            ChaError chaError = new ChaError(ChaErrorName.DEPTH_CLONE_EXCEPTION, null, e1.getMessage());
            throw new ChaRuntimeException(chaError, e1);
        } catch (IOException e2) {
            ChaError chaError = new ChaError(ChaErrorName.DEPTH_CLONE_EXCEPTION, null, e2.getMessage());
            throw new ChaRuntimeException(chaError, e2);
        }
    }

}
