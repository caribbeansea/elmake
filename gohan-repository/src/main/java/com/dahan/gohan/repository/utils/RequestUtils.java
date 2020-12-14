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
import com.dahan.gohan.repository.GohanDependency;
import com.dahan.gohan.repository.GohanRepository;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.CollectResult;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.resolution.DependencyRequest;

import java.util.List;

/**
 * 集合请求依赖
 *
 * @author kevin
 */
public class RequestUtils
{

    public static DependencyRequest newDependencyRequest(List<GohanDependency> dependencies,
                                                         List<GohanRepository> repositories,
                                                         GohanRepository repository) throws DependencyCollectionException
    {
        CollectResult collectResult = repository.collectDependencies(newCollectRequest(dependencies, repositories));
        DependencyNode root = collectResult.getRoot();

        DependencyRequest dependencyRequest = new DependencyRequest();
        dependencyRequest.setRoot(root);

        return dependencyRequest;
    }

    public static CollectRequest newCollectRequest(List<GohanDependency> dependencies, GohanRepository gohanRepository)
    {
        return newCollectRequest(dependencies, Lists.of(gohanRepository));
    }

    public static CollectRequest newCollectRequest(List<GohanDependency> dependencies, List<GohanRepository> repositories)
    {
        CollectRequest request = new CollectRequest();

        for (GohanDependency dependency : dependencies)
        {
            request.addDependency(dependency.toEclipseDependency());
        }

        for (GohanRepository repository : repositories)
        {
            request.addRepository(repository.getRemoteRepository());
        }

        return request;
    }

}
