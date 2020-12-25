package com.dahan.elmake

import com.dahan.elmake.dsl.AutoconfConst
import com.dahan.elmake.dsl.MakefileBuilder
import com.dahan.elmake.repository.DependencyInfo
import com.dahan.elmake.repository.utils.RepositoryUtils
import com.dahan.elmake.reslovedep.ResolveDependency
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

    //
    // 1) 解析构建脚本，输出 Makefile 对象
    //
    fun doBuilding(base_dir: String) {

        // 解析makefile
        val makefileBuilder = MakefileBuilder(base_dir, this::class.java.classLoader)
        val makefile = makefileBuilder.parseMakefile()

        // 下载依赖
        val resdep = ResolveDependency(cabsmake = makefile)
        resdep.resolve()

    }

    //
    // 解决依赖引用下载
    //
    private fun resolveDependency(dependencies: List<DependencyInfo>) = RepositoryUtils.resolveDependencies(dependencies)

    @JvmStatic
    fun main(args: Array<String>) {
        doBuilding("/Users/wuyanzu/project/IdeaProjects/elmake/debugging")
    }

}