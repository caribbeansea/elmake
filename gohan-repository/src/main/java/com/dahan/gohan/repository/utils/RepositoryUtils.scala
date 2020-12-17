package com.dahan.gohan.repository.utils

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

import com.dahan.gohan.collect.Lists
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.repository.GohanDependency
import com.dahan.gohan.repository.GohanRepository
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.DefaultRepositorySystemSession
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.collection.DependencyCollectionException
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.impl.DefaultServiceLocator
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.resolution.DependencyRequest
import org.eclipse.aether.resolution.DependencyResolutionException
import org.eclipse.aether.resolution.DependencyResult
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory
import java.util

/**
 * 工件储存库工具类
 *
 * @author tiansheng
 */
object RepositoryUtils {

  // 当前项目支持使用的储存库集合。
  // 如果需要使用其他的仓库的话请在 build 脚本下添加 repository {} 节点。
  private val repositorys = Maps.newHashMap( // 默认使用阿里云中央仓库
    "default", new GohanRepository("alibaba-central", "https://maven.aliyun.com/repository/central"))

  private val defaultRepository = getDefaultRepository

  /**
   * @return RepositorySystem接口实例
   */
  def newRepositorySystem: RepositorySystem = {
    val locator = MavenRepositorySystemUtils.newServiceLocator
    locator.addService(classOf[RepositoryConnectorFactory], classOf[BasicRepositoryConnectorFactory])
    locator.addService(classOf[TransporterFactory], classOf[FileTransporterFactory])
    locator.addService(classOf[TransporterFactory], classOf[HttpTransporterFactory])
    return locator.getService(classOf[RepositorySystem])
  }

  /**
   * 创建储存库会话
   *
   * @param repositorySystem    RepositorySystem实例
   * @param localRepositoryPath 本地仓库路径
   * @return RepositorySystemSession实例
   */
  def newRepositorySystemSession(repositorySystem: RepositorySystem, localRepositoryPath: String): DefaultRepositorySystemSession = {
    val session = MavenRepositorySystemUtils.newSession
    // 设置本地路径
    val localRepository = new LocalRepository(localRepositoryPath)
    session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepository))
    return session
  }

  /**
   * @return 默认工件储存库
   */
  def getDefaultRepository: GohanRepository = repositorys.get("default")

  @throws[DependencyCollectionException]
  @throws[DependencyResolutionException]
  def resolveDependencies(gohanDependency: GohanDependency): DependencyResult = resolveDependencies(Lists.of(gohanDependency))

  /**
   * 下载所需依赖
   */
  @throws[DependencyCollectionException]
  @throws[DependencyResolutionException]
  def resolveDependencies(gohanDependencies: util.List[GohanDependency]): DependencyResult = {
    val dependencyRequest = RequestUtils.newDependencyRequest(gohanDependencies, Lists.newArrayList(repositorys.values), defaultRepository)
    defaultRepository.resolveDependencies(dependencyRequest)
  }

}