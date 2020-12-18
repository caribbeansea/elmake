package com.dahan.eimoto.repository;
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
 * Creates on 2020/12/8.
 */

import com.dahan.eimoto.collect.Lists;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;

/**
 * @author tiansheng
 */
public class DefaultRepositoryDownloadTest
{

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
     * 创建RepositorySystemSession
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

    public static void downloadDependency(String g, String a, String v) throws DependencyCollectionException, DependencyResolutionException
    {
        Dependency dependency = new Dependency(new DefaultArtifact(g + ":" + a + ":" + v), "compile");

        RepositorySystem repositorySystem = newRepositorySystem();
        RepositorySystemSession repositorySystemSession =
                newRepositorySystemSession(repositorySystem, "/Users/wuyanzu/project/IdeaProjects/eimoto/eimoto-builder/repository");

        RemoteRepository central =
                new RemoteRepository.Builder("central", "default", "https://maven.aliyun.com/repository/central").build();

        CollectRequest request = new CollectRequest();
        // request.setRoot(dependency);
        request.setDependencies(Lists.of(dependency));
        request.addRepository(central);

        DependencyNode dependencyNode = repositorySystem.collectDependencies(repositorySystemSession, request).getRoot();

        DependencyRequest dependencyRequest = new DependencyRequest();
        dependencyRequest.setRoot(dependencyNode);

        repositorySystem.resolveDependencies(repositorySystemSession, dependencyRequest);

        PreorderNodeListGenerator generator = new PreorderNodeListGenerator();
        dependencyNode.accept(generator);

        System.out.println(generator.getClassPath());

    }

    public static void main(String[] args) throws DependencyCollectionException, DependencyResolutionException
    {
        downloadDependency("com.alibaba", "fastjson", "1.2.66");
    }

}
