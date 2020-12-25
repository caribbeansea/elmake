package com.dahan.elmake.reslovedep

import com.dahan.elmake.dsl.Makefile
import com.dahan.elmake.repository.utils.RepositoryUtils

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
 * Creates on 2020/12/22.
 */

/**
 * 下载并整合依赖数据
 *
 * @author tiansheng
 */
class ResolveDependency(val cabsmake: Makefile) {

    /**
     * root make 构建对象
     */
    private val makefile: Makefile = cabsmake

    fun resolve() {
        val dependencies = makefile.dependencies
        val depresult = RepositoryUtils.resolveDependencies(dependencies)

        depresult?.root?.children?.forEach {
            makefile.addUsingDependency(it)
        }

    }

}