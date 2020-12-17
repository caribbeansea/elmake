package com.dahan.gohan;

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2019/11/13.
 */

import java.util.Collection;

/**
 * @author tiansheng
 */
public class Assert {

    /**
     * 非空判断
     *
     * @param t 对象实例
     */
    public static <T> T requiredNonNull(T t) {
        return requiredNonNull(t, t.getClass().getSimpleName());
    }

    /**
     * 非空判断
     *
     * @param t       对象实例
     * @param message 报错信息
     */
    public static <T> T requiredNonNull(T t, String message) {
        if (t == null)
            throw new NullPointerException(message);
        return t;
    }

    /**
     * 不能为false
     *
     * @param value    布尔值
     * @param runnable 如果是false执行runnable
     */
    public static void notFalse(boolean value, Runnable runnable) {
        if (!value)
            runnable.run();
    }

    /**
     * List不能为Null以及空
     */
    public static <T> void notNullAndEmpty(Collection<T> list) {
        notNullAndEmpty(list, "集合对象不能为NULL以及为空");
    }

    /**
     * List不能为Null以及空
     */
    public static <T> void notNullAndEmpty(Collection<T> list, String message) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException(message);
    }


    /**
     * String不能为Null以及空字符串
     */
    public static <T> void notNullAndEmpty(String str) {
        notNullAndEmpty(str, "String对象不能未空");
    }

    /**
     * String不能为Null以及空字符串
     */
    public static <T> void notNullAndEmpty(String str, String message) {
        if (StringUtils.isEmpty(str))
            throw new IllegalArgumentException(message);
    }

}
