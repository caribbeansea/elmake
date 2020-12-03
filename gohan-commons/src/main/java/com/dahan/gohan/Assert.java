package com.dahan.gohan;

/*
 * Creates on 2019/11/13.
 */

import java.util.Collection;

/**
 * @author kevin
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
