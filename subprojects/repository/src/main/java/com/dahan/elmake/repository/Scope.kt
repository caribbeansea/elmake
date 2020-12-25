package com.dahan.elmake.repository

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
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
 * Creates on 2020/12/25.
 */

/**
 * 依赖指定范围
 *
 * @author tiansheng
 */
enum class Scope(val value: String) {

    /**
     * 依赖会在编译和运行时期存在，并且会打包进classpath
     */
    COMPILER("compiler"),

    /**
     * 依赖只在测试时存在，不会被打包进classpath
     */
    TEST("test"),

    /**
     * 引入系统依赖，并且它的效果与 COMPILER 一样。
     * 同样会被打包进claspath。
     */
    SYSTEM("system"),

    /**
     * 引入系统依赖，但它不会被打包进classpath。
     */
    EXSYSTEM("system"),

    ;

}