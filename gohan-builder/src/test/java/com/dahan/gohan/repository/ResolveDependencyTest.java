package com.dahan.gohan.repository;
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
import com.dahan.gohan.repository.utils.RepositoryUtils;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.junit.Test;

/**
 * @author kevin
 */
public class ResolveDependencyTest
{

    @Test
    public void resolveDependencyTest() throws DependencyCollectionException, DependencyResolutionException
    {
        RepositoryUtils.resolveDependencies(Lists.of(
                new GohanDependency("com.alibaba", "fastjson", "1.2.66"),
                new GohanDependency("net.sf.json-lib", "json-lib", "2.4", "jdk15")
        ));
    }

}
