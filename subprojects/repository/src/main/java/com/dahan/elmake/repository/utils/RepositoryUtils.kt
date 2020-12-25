package com.dahan.elmake.repository.utils

import com.dahan.elmake.collect.Lists
import com.dahan.elmake.collect.Maps
import com.dahan.elmake.repository.DependencyInfo
import com.dahan.elmake.repository.ElmakeRepository
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.resolution.DependencyResult
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory

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
 * Creates on 2020/12/9.
 */

/**
 * 工件储存库工具类
 *
 * @author tiansheng
 */
object RepositoryUtils {

    // 当前项目支持使用的储存库集合。
    // 如果需要使用其他的仓库的话请在 build 脚本下添加 repository {} 节点。
    private val REPOSITORYS: Map<String, ElmakeRepository> = Maps.newHashMap( // 默认使用阿里云中央仓库
            "default", ElmakeRepository("alibaba-central", "https://maven.aliyun.com/repository/central"))

    private val defaultRepository = getDefaultRepository()

    /**
     * @return RepositorySystem接口实例
     */
    fun newRepositorySystem(): RepositorySystem {
        val locator = MavenRepositorySystemUtils.newServiceLocator()
        locator.addService(RepositoryConnectorFactory::class.java, BasicRepositoryConnectorFactory::class.java)
        locator.addService(TransporterFactory::class.java, FileTransporterFactory::class.java)
        locator.addService(TransporterFactory::class.java, HttpTransporterFactory::class.java)
        return locator.getService(RepositorySystem::class.java)
    }

    /**
     * 创建储存库会话
     *
     * @param repositorySystem    RepositorySystem实例
     * @param localRepositoryPath 本地仓库路径
     * @return RepositorySystemSession实例
     */
    fun newRepositorySystemSession(repositorySystem: RepositorySystem, localRepositoryPath: String): RepositorySystemSession {
        val session = MavenRepositorySystemUtils.newSession()
        // 设置本地路径
        val localRepository = LocalRepository(localRepositoryPath)
        session.localRepositoryManager = repositorySystem.newLocalRepositoryManager(session, localRepository)
        return session
    }

    /**
     * @return 默认工件储存库
     */
    private fun getDefaultRepository(): ElmakeRepository? = REPOSITORYS["default"]

    fun resolveDependencies(DependencyInfo: DependencyInfo): DependencyResult? =
            resolveDependencies(Lists.of(DependencyInfo))

    /**
     * 下载所需依赖
     */
    fun resolveDependencies(elMakeDependencies: List<DependencyInfo>): DependencyResult? {
        val dependencyRequest =
                RequestUtils.newDependencyRequest(elMakeDependencies, Lists.newArrayList(REPOSITORYS.values), defaultRepository)
        return defaultRepository?.resolveDependencies(dependencyRequest)
    }

}