package com.dahan.gohan.repository.dependency

import com.dahan.gohan.StringUtils
import com.dahan.gohan.collect.Lists
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.repository.pom.ProjectObjectModel

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
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class Dependency
{

    /** groupId **/
    private String groupId

    /** artifactId **/
    private String artifactId

    /** 版本好 **/
    private String version

    /** 运行范围 **/
    private Scope scope

    /** 依赖jar包 **/
    private File jarfile

    /** pom文件 **/
    private ProjectObjectModel projectObjectModel

    /** 其他配置信息 **/
    private LinkedHashMap<String, String> settings = Maps.newLinkedHashMap()

    /** 依赖路径 **/
    private String localDirectory

    /** 依赖文件名称格式 **/
    private String dependencyName

    /** 当前依赖使用的其他依赖 **/
    private List<Dependency> dependencies = Lists.newArrayList()

    static final int JAR = 0, POM = 1

    Dependency()
    {

    }

    /**
     * 提供最基本的依赖信息
     */
    Dependency(String groupId, String artifactId, String version)
    {
        this(groupId, artifactId, version, Scope.COMPILE)
    }

    /**
     * 提供依赖范围信息
     */
    Dependency(String groupId, String artifactId, String version, Scope scope)
    {
        this.groupId = groupId
        this.artifactId = artifactId
        this.version = version
        this.scope = scope
        // 生成依赖的本地路径
        this.localDirectory = StringUtils.append(groupId.replaceAll("\\.", "/"), "/",
                artifactId, "/", version, "/")
        this.dependencyName = artifactId + "-" + version
    }

    ProjectObjectModel getProjectObjectModel()
    {
        return projectObjectModel
    }

    void setProjectObjectModel(File pomxml)
    {
        this.projectObjectModel = new ProjectObjectModel(pomxml, this)
    }

    String getGroupId()
    {
        return groupId
    }

    void setGroupId(String groupId)
    {
        this.groupId = groupId
    }

    String getArtifactId()
    {
        return artifactId
    }

    void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId
    }

    String getVersion()
    {
        return version
    }

    void setVersion(String version)
    {
        this.version = version
    }

    Scope getScope()
    {
        return scope
    }

    void setScope(Scope scope)
    {
        this.scope = scope
    }

    LinkedHashMap<String, String> getSettings()
    {
        return settings
    }

    void setSettings(LinkedHashMap<String, String> settings)
    {
        this.settings.putAll(settings)
    }

    void putSettings(String key, String value)
    {
        this.settings.put(key, value)
    }

    File getJarfile()
    {
        return jarfile
    }

    void setJarfile(File jarfile)
    {
        this.jarfile = jarfile
    }

    String getLocalDirectory()
    {
        return localDirectory
    }

    String getDependencyName()
    {
        return dependencyName
    }

    void setDependencyName(String dependencyName)
    {
        this.dependencyName = dependencyName
    }

    String pom() { localDirectory + dependencyName + ".pom" }

    String jar() { localDirectory + dependencyName + ".jar" }

    String getCoordinate() { groupId + ":" + artifactId + ":" + version }

}