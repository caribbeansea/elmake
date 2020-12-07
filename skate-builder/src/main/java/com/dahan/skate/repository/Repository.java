package com.dahan.skate.repository;

import com.dahan.skate.Files;
import com.dahan.skate.collection.exception.DNOCollects;
import com.dahan.skate.exception.DownloadException;
import com.dahan.skate.exception.UnknownAddressException;
import com.dahan.skate.repository.dependency.Dependency;
import com.dahan.skate.repository.dependency.Scope;

import java.io.File;

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

/**
 * 仓库对象
 *
 * @author kevin
 */
public class Repository
{
    private String address;
    private static final String USER_DIR = System.getProperty("user.dir");
    private static String localDirectory = USER_DIR + "/repository/";
    private static final int LOCAL = 0, REMOTE = 1;

    private static final DNOCollects collects = new DNOCollects();

    private int type;

    public Repository()
    {
    }

    public Repository(String address)
    {
        if (!address.endsWith("/"))
        {
            address = address.concat("/");
        }

        this.address = address;

        // 如果是URL
        if (address.startsWith("http://") || address.startsWith("https://"))
        {
            type = REMOTE;
        } else
        {
            File fileAddress = new File(address);
            if (fileAddress.isDirectory())
            {
                type = LOCAL;
            } else
            {
                throw new UnknownAddressException(address);
            }
        }

    }

    /**
     * 仓库URL，可以是本地也可以是远程
     */
    public void setRepository(String address)
    {
        this.address = address;
    }

    public Dependency getDependency(String groupId, String artifactId, String version)
    {
        return getDependency(groupId, artifactId, version, null, null, false);
    }

    /**
     * 获取仓库中的依赖包
     *
     * @param groupId           group
     * @param artifactId        artifact
     * @param version           version
     * @param scope             范围
     * @param from              是否来自其他依赖的引用
     * @param dependencyManager 是否是从dependencyManger标签下扫描进来的
     * @return {@code Dependency}
     */
    public Dependency getDependency(String groupId, String artifactId, String version, Scope scope, Dependency from,
                                    boolean dependencyManager)
    {
        Dependency dependency = new Dependency(groupId, artifactId, version, scope, this);

        // 查找已加载的依赖是否存在
        if (DependencyUtils.contains(dependency))
        {
            return DependencyUtils.getLoadedDependency(dependency);
        }

        if (dependencyManager)
        {
            return dependency;
        }

        // 如果本地仓库没有当前依赖的文件目录就创建
        Files.mkdirsNotExist(localDirectory + dependency.getLocalDirectory());
        File jarfile = getLocalJarfile(dependency);
        File pomxml = getLocalPomxml(dependency);
        // 如果没有pom就去当前仓库目录下载
        if (!pomxml.exists())
        {
            if (!RepositoryUtils.downloadDependency(dependency, this, from))
            {
                dependency.setResolve(false);
                return dependency;
            }

            // 下载完了后再进行一次判断
            if (pomxml.exists())
            {
                dependency.setProjectObjectModel(pomxml);
            }
        } else
        {
            dependency.setProjectObjectModel(pomxml);
        }

        if (jarfile.exists())
        {
            dependency.setJarfile(jarfile);
        }

        DependencyUtils.loaded(dependency);

        return dependency;
    }

    public String getDownloadAddress(Dependency dependency, int downloadType)
    {
        return address + (downloadType == Dependency.getJAR() ? dependency.jar() : dependency.pom());
    }

    public int getType()
    {
        return type;
    }

    /**
     * 获取本地的Jar文件
     */
    protected static File getLocalJarfile(Dependency dependency)
    {
        return new File(localDirectory + dependency.jar());
    }

    /**
     * 获取本地的pom文件
     */
    protected static File getLocalPomxml(Dependency dependency)
    {
        return new File(localDirectory + dependency.pom());
    }

    public static String getLocalDirectory()
    {
        return localDirectory;
    }

    public static void setLocalDirectory(String localDirectory)
    {
        Repository.localDirectory = localDirectory;
    }

    public static int getLOCAL()
    {
        return LOCAL;
    }

    public static int getREMOTE()
    {
        return REMOTE;
    }

    public static DNOCollects getCollects()
    {
        return collects;
    }

}
