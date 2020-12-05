package com.dahan.gohan.repository.pom

import com.dahan.gohan.collect.Maps
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Plugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
 * Creates on 2020/12/4.
 */

/**
 * pom xml的解析对象
 * @author kevin
 */
class ProjectObjectModel
{

    private static Logger logger = LoggerFactory.getLogger(ProjectObjectModel.class)

    /** 父模块 **/
    private Dependency parent

    /** 自生的依赖 **/
    private Dependency self

    /** 依赖列表 **/
    private List<Dependency> dependencies

    /** dependenciesManager标签下的依赖列表 **/
    private List<Dependency> dependenciesManager

    /** 属性列表 **/
    private Map<String, String> selfProperties = Maps.newHashMap(6)

    /** 插件列表 **/
    private List<Plugin> plugins

    ProjectObjectModel() {}

    ProjectObjectModel(File file, Dependency self)
    {
        this.self = self
        // 解析pom.xml
        ProjectObjectModelParses.parse(file, this)
    }

    /**
     * 将pom对象转成pom.xml
     */
    void toPOMXml()
    {
        // todo 将pom对象转成pom.xml
    }

    Dependency getParent()
    {
        return parent
    }

    void setParent(Dependency parent)
    {
        this.parent = parent
    }

    List<Dependency> getDependencies()
    {
        return dependencies
    }

    void setDependencies(List<Dependency> dependencies)
    {
        this.dependencies = dependencies
    }

    List<Dependency> getDependenciesManager()
    {
        return dependenciesManager
    }

    void setDependenciesManager(List<Dependency> dependenciesManager)
    {
        this.dependenciesManager = dependenciesManager
    }

    String getSelfProperties(String key)
    {
        return selfProperties != null ? selfProperties.get(key) : null
    }

    void putSelfProperties(String key, String value)
    {
        this.selfProperties.put(key, value)
    }

    List<Plugin> getPlugins()
    {
        return plugins
    }

    void setPlugins(List<Plugin> plugins)
    {
        this.plugins = plugins
    }

    Dependency getSelf()
    {
        return self
    }

    void setSelf(Dependency self)
    {
        this.self = self
        putSelfProperties("project.version", self.version)
    }

}
