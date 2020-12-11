package com.dahan.gohan.buildscript

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
 * Creates on 2020/12/10.
 */

/**
 * settings.gohan 中的配置是全局适用的。
 *
 * @author kevin
 */
class GohanSettings
{

    /**
     * 设置路径为 settings.gohan 文件的同一级目录。假设当前目录下有
     * 一个 subprojects 目录，那么久可以这样写<code> subprojects/Module-A </code>
     *
     * project
     *    |
     *    | --- subprojects
     *    |          |
     *    |          | --- Module-A
     *    |          |
     *    |          | --- Module-B
     *    |          |
     *    |
     *    | --- settings.gohan
     *
     */
    private String[] subprojects

    /**
     * 属性，相当于pom中的properties
     */
    private Map<String, String> exts = new HashMap<>()

    /**
     * @param modules 模块列表
     */
    void setSubprojects(String[] modules)
    {
        this.subprojects = modules
    }

    /**
     * 属性
     */
    void exts(Closure closure) {
        this.exts = closure.call() as Map
    }

}
