package com.dahan.eimoto

import com.dahan.eimoto.bootsrap.SettingsEimotoKts
import com.dahan.eimoto.dsl.BuildEimotoKts
import com.dahan.eimoto.repository.EimotoDependency
import com.dahan.eimoto.repository.utils.RepositoryUtils

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
object ProjectBuildResolve {

    /**
     * 执行项目的构建指令
     *
     * @param buildKts 主项目的构建脚本
     * @param settingsKts 项目配置
     */
    fun doBuilding(buildKts: BuildEimotoKts, settingsKts: SettingsEimotoKts) {
        resolveDependency(buildKts.dependencies)
    }

    //
    // 解决依赖引用下载
    //
    private fun resolveDependency(dependencies: List<EimotoDependency>) = RepositoryUtils.resolveDependencies(dependencies)

}