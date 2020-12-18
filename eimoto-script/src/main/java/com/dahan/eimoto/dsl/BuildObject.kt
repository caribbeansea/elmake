package com.dahan.eimoto.dsl

import com.dahan.eimoto.collect.Lists
import com.dahan.eimoto.collect.Maps
import com.dahan.eimoto.repository.EimotoDependency

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
 * Creates on 2020/12/18.
 */

/**
 * @author tiansheng
 */
open class BuildObject {

    /**
     * 常量
     */
    object ConstVar {

        const val GROUP_VALUE: String = "group"

        const val ARTIFACT_VALUE: String = "name"

        const val VERSION_VALUE: String = "version"

        const val PARENT_VALUE: String = "parent"

    }

    /**
     * 依赖列表
     */
    private val dependencies: MutableList<EimotoDependency> = Lists.newArrayList()

    /**
     * 所有的信息都保存在settings map中
     */
    private var settings: MutableMap<String, Any> = Maps.newHashMap()

    // set project group id.
    fun group(value: String) = settings.put(ConstVar.GROUP_VALUE, value)

    // set project artifact id.
    fun name(value: String) = settings.put(ConstVar.ARTIFACT_VALUE, value)

    // set project version.
    fun version(value: String) = settings.put(ConstVar.VERSION_VALUE, value)

    // set project parent.
    fun parent(value: String) = settings.put(ConstVar.PARENT_VALUE, value)

    // 添加插件
    fun apply(coords: String) = Unit

    fun include(coords: String) = include(coords, null, null)

    fun include(coords: String, scope: EimotoDependency.Scope) = include(coords, null, scope)

    // 引入依赖包
    fun include(coords: String, classifier: String?, scope: EimotoDependency.Scope?) =
            dependencies.add(EimotoDependency(coords, classifier, scope))

    // ############################ 定义代码块 ############################

    // 定义公共属性
    // todo delay fun exts(function: () -> Unit) = function()

    // 定义任务模块
    // todo delay fun tasks(function: () -> Unit) = function()

    // includes代码块，所有include的导入依赖声明应该放在includes代码块下面
    fun includes(function: () -> Unit) = function()

}