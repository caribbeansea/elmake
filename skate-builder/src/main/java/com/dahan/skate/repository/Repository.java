package com.dahan.skate.repository;

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
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
 * Creates on 2020/12/3.
 */

import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

/**
 * 仓库对象
 *
 * @author kevin
 */
public final class Repository
{

    //
    // 本地储存路径
    //
    private static final String localDirectory = System.getProperty("user.dir") + "/repository/";

    private static RemoteRepository remoteRepository;

    public Repository(String address)
    {

        RepositorySystem repositorySystem = RepositoryUtils.newRepositorySystem();
        RepositorySystemSession repositorySystemSession
                = RepositoryUtils.newRepositorySystemSession(repositorySystem, localDirectory);

        remoteRepository = new RemoteRepository.Builder("central", "default", address).build();

    }

    public Dependency getDependency(GohanDependency gohanDependency)
    {
        return null;
    }

}
