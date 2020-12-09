package com.dahan.gohan.repository.utils;
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

import com.dahan.gohan.collect.Lists;
import com.dahan.gohan.collect.Maps;
import com.dahan.gohan.repository.GohanDependency;
import com.dahan.gohan.repository.GohanRepository;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.eclipse.aether.resolution.DependencyResult;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;

import java.util.List;
import java.util.Map;

/**
 * 工件储存库工具类
 *
 * @author kevin
 */
public final class RepositoryUtils
{

    //
    // 当前项目支持使用的储存库集合。
    // 如果需要使用其他的仓库的话请在 build 脚本下添加 repository {} 节点。
    //
    private static final Map<String, GohanRepository> repositorys = Maps.newHashMap(
            // 默认使用阿里云中央仓库
            "default", new GohanRepository("alibaba-central", "https://maven.aliyun.com/repository/central")
    );

    private static final GohanRepository defaultRepository = getDefaultRepository();

    /**
     * @return RepositorySystem接口实例
     */
    public static RepositorySystem newRepositorySystem()
    {
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
        locator.addService(TransporterFactory.class, FileTransporterFactory.class);
        locator.addService(TransporterFactory.class, HttpTransporterFactory.class);
        return locator.getService(RepositorySystem.class);
    }

    /**
     * 创建储存库会话
     *
     * @param repositorySystem    RepositorySystem实例
     * @param localRepositoryPath 本地仓库路径
     * @return RepositorySystemSession实例
     */
    public static RepositorySystemSession newRepositorySystemSession(RepositorySystem repositorySystem, String localRepositoryPath)
    {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        // 设置本地路径
        LocalRepository localRepository = new LocalRepository(localRepositoryPath);
        session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepository));
        return session;
    }

    /**
     * @return 默认工件储存库
     */
    public static GohanRepository getDefaultRepository()
    {
        return repositorys.get("default");
    }

    public static DependencyResult resolveDependencies(GohanDependency gohanDependency)
            throws DependencyCollectionException, DependencyResolutionException
    {
        return resolveDependencies(Lists.of(gohanDependency));
    }

    /**
     * 下载所需依赖
     */
    public static DependencyResult resolveDependencies(List<GohanDependency> gohanDependencies)
            throws DependencyCollectionException, DependencyResolutionException
    {
        DependencyRequest dependencyRequest
                = RequestUtils.newDependencyRequest(gohanDependencies, Lists.newArrayList(repositorys.values()), defaultRepository);

        return defaultRepository.resolveDependencies(dependencyRequest);
    }

}
