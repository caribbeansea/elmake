package com.dahan.gohan.reflect;

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
 * Creates on 2020/3/21.
 */

import com.dahan.gohan.Assert;
import com.dahan.gohan.Objects;
import com.dahan.gohan.collect.Lists;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 类的静态工具类
 *
 * @author kevin
 */
@SuppressWarnings("unchecked")
public class ClassUtils {

    /**
     * 创建一个实例。
     */
    public static <T> T newInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return (T) constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据参数类型选择构造器去创建一个实例。
     *
     * @param paramClazz 参数类型
     * @param paramVal   参数数据
     */
    public static <T> T newInstance(Class<?> clazz,
                                    Class<?>[] paramClazz,
                                    Object... paramVal) {
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getConstructor(paramClazz);
            return (T) constructor.newInstance(paramVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建一个实例并传递构造函数参数。会自动推断构造参数
     *
     * @param paramVal 构造函数参数值。
     */
    public static <T> T newInstance(Class<?> clazz, Object... paramVal) {
        Constructor<?> constructor = null;
        try {
            Class<?>[] parametersType = new Class[paramVal.length];
            for (int i = 0; i < paramVal.length; i++) {
                parametersType[i] = paramVal[i].getClass();
            }
            constructor = clazz.getConstructor(parametersType);
            return (T) constructor.newInstance(paramVal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有父类
     */
    public static List<Class<?>> getSuperClasses(Class<?> target) {
        List<Class<?>> classes = Lists.newArrayList();
        getSuperClasses(target, classes);
        return classes;
    }

    /**
     * 获取所有父类递归方法
     */
    static void getSuperClasses(Class<?> target, List<Class<?>> classes) {
        Class<?> superclass = target.getSuperclass();
        if (superclass == null) return;
        classes.add(superclass);
        getSuperClasses(superclass, classes);
    }

    /**
     * 判断是不是接口
     */
    public static boolean isInterface(Class<?> target) {
        return target != null && target.isInterface();
    }

    /**
     * 获取泛型对象的真实类
     *
     * @param generic 泛型对象
     */
    public static Class<?> objectClass(Object generic) {
        return Objects.trycache(o ->
                Class.forName(Assert.requiredNonNull(generic).getClass().getName()));
    }

    /**
     * 判断两个对象的class是否相同
     */
    public static boolean equals(Object o1, Class<?> o2) {
        return Assert.requiredNonNull(o1).getClass() == Assert.requiredNonNull(o2);
    }

}
