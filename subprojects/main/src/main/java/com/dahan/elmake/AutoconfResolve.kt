package com.dahan.elmake

import com.dahan.elmake.dsl.AbsMake
import com.dahan.elmake.reflect.ClassUtils.*
import com.dahan.elmake.repository.ElMakeDependency
import com.dahan.elmake.repository.utils.RepositoryUtils
import groovy.lang.GroovyClassLoader
import java.io.File

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
 * Creates on 2020/12/20.
 */

/**
 * @author tiansheng
 */
object AutoconfResolve {

    private const val conf_name = "/autoconf.make"

    /**
     * 执行项目的构建指令
     *
     * @param base_dir 项目根路径
     */
    fun doBuilding(base_dir: String) {
        val projectDirector = File("${base_dir}/${conf_name}")
        val scriptContent = Files.readString(projectDirector)
        val stream = Streams.getResourceAsStream("com/dahan/elmake/${conf_name}.tmp", this::class.java.classLoader)
        val tmp = String(stream.readAllBytes()).replace("#IMPL", scriptContent)

        val makeInstance = compileGroovy(tmp);

        println()
        // resolveDependency(absBuildDSL.dependencies)
    }

    /**
     * 编译Groovy
     */
    private fun compileGroovy(sourceCode: String): AbsMake {
        // 获取groovy类加载器
        val gcl = GroovyClassLoader(this::class.java.classLoader)
        val clazz = gcl.parseClass(sourceCode)

        // 实例化脚本
        return newInstance(clazz)
    }

    //
    // 解决依赖引用下载
    //
    private fun resolveDependency(dependencies: List<ElMakeDependency>) = RepositoryUtils.resolveDependencies(dependencies)

    @JvmStatic
    fun main(args: Array<String>) {
        doBuilding("/Users/wuyanzu/project/IdeaProjects/elmake/debugging")
    }

}