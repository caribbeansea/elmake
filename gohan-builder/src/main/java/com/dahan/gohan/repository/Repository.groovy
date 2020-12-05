package com.dahan.gohan.repository

import com.dahan.gohan.Files
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.collection.exception.DNOCollects
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Scope

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
 * Creates on 2020/12/2.
 */

/**
 * 仓库对象
 * @author kevin
 */
class Repository
{

    // 仓库名称
    private String name

    // 仓库地址
    private String address

    private static String USER_DIR = System.getProperty("user.dir")

    // 本地仓库地址，下载的也放在该地址下。
    static String localDirectory = USER_DIR + "/repository/"

    // 仓库地址类型
    static final int LOCAL = 0, REMOTE = 1

    /**
     * 所有可用的仓库对象
     */
    static final Map<String, Repository> repositories = Maps.newHashMap()

    static final DNOCollects collects = new DNOCollects()

    static {
        Files.mkdirsNotExist(localDirectory)
    }

    // 仓库类型
    private int type

    Repository() {}

    Repository(String name, String address)
    {
        this.name = name
        this.address = address

        // 如果是URL
        if (address.startsWith("http://") || address.startsWith("https://"))
        {
            type = REMOTE
        } else
        {
            def fileAddress = new File(address)
            if (fileAddress.isDirectory())
            {
                type = LOCAL
            } else
            {
                throw new UnknownHostException(address)
            }
        }

        repositories.put(name, this)
    }

    /**
     * 仓库URL，可以是本地也可以是远程
     */
    void setRepository(String address)
    {
        this.address = address
    }

    Dependency getDependency(String groupId, String artifactId, String version)
    {
        return getDependency(groupId, artifactId, version, null, null)
    }

    /**
     * 获取仓库中的依赖包
     *
     * @param from 依赖的下载是因为来自其他依赖的引用
     */
    Dependency getDependency(String groupId, String artifactId, String version, Scope scope, Dependency from)
    {
        Dependency dependency = new Dependency(groupId, artifactId, version, scope)
        // 如果本地仓库没有当前依赖的文件目录就创建
        Files.mkdirsNotExist(localDirectory + dependency.getLocalDirectory())
        def jarfile = getLocalJarfile(dependency)
        def pomxml = getLocalPomxml(dependency)
        // 如果没有pom就去当前仓库目录下载
        if (!pomxml.exists())
        {
            RepositoryUtils.downloadDependency(dependency, this, from)
            // 下载完了后再进行一次判断
            if (pomxml.exists())
            {
                dependency.setProjectObjectModel(pomxml)
            }
        } else
        {
            dependency.setProjectObjectModel(pomxml)
        }

        if (jarfile.exists())
        {
            dependency.setJarfile(jarfile)
        }

        return dependency
    }

    String getDownloadAddress(Dependency dependency, int downloadType)
    {
        return address + (downloadType == Dependency.JAR ? dependency.jar() : dependency.pom())
    }

    int getType()
    {
        return type
    }

    /**
     * 获取本地的Jar文件
     */
    protected static File getLocalJarfile(Dependency dependency)
    {
        return new File(localDirectory + dependency.jar())
    }

    /**
     * 获取本地的pom文件
     */
    protected static File getLocalPomxml(Dependency dependency)
    {
        return new File(localDirectory + dependency.pom())
    }

}
