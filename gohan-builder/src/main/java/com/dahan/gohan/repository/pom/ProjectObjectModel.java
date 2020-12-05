package com.dahan.gohan.repository.pom;

import com.dahan.gohan.collect.Maps;
import com.dahan.gohan.repository.dependency.Dependency;
import com.dahan.gohan.repository.dependency.Plugin;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 *
 * @author kevin
 */
public class ProjectObjectModel
{

    private static final Logger logger = LoggerFactory.getLogger(ProjectObjectModel.class);

    /**
     * 父模块
     **/
    private Dependency parent;

    /**
     * 自己的依赖
     **/
    private Dependency self;

    /**
     * 依赖列表
     **/
    private List<Dependency> dependencies;

    /**
     * dependenciesManager标签下的依赖列表
     **/
    private List<Dependency> dependencyManager;

    /**
     * 属性列表
     **/
    private final Map<String, String> selfProperties = Maps.newHashMap(6);

    /**
     * 插件列表
     **/
    private List<Plugin> plugins;

    public ProjectObjectModel()
    {
    }

    public ProjectObjectModel(File file, Dependency self) throws DocumentException
    {
        this.self = self;
        // 解析pom.xml
        ProjectObjectModelParses.parse(file, this);
    }

    /**
     * 查找pom依赖继承关系中的依赖
     */
    public Dependency findDependency(String groupId, String artifactId)
    {
        Dependency dependency = null;

        // 查找当前依赖列表
        if (dependencies != null && !dependencies.isEmpty())
        {
            dependency = dependencies.stream()
                    .filter(it -> it.getCoordinate().contains(groupId.concat(":").concat(artifactId)))
                    .findFirst().orElse(null);
        }


        // 查找dependencyManager中的依赖列表
        if (dependencyManager != null && !dependencyManager.isEmpty())
        {
            dependency = dependencyManager.stream()
                    .filter(it -> it.getCoordinate().contains(groupId.concat(":").concat(artifactId)))
                    .findFirst().orElse(null);
        }


        // 如果都没有就去parent查找
        if (parent != null)
        {
            parent.getProjectObjectModel().findDependency(groupId, artifactId);
        }

        return dependency;
    }

    /**
     * 将pom对象转成pom.xml
     */
    public void toProjectObjectModelXml()
    {
        // todo 将pom对象转成pom.xml
    }

    public Dependency getParent()
    {
        return parent;
    }

    public void setParent(Dependency parent)
    {
        this.parent = parent;
    }

    public List<Dependency> getDependencies()
    {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies)
    {
        this.dependencies = dependencies;
    }

    public List<Dependency> getDependencyManager()
    {
        return dependencyManager;
    }

    public void setDependencyManager(List<Dependency> dependencyManager)
    {
        this.dependencyManager = dependencyManager;
    }

    public String getSelfProperties(String key)
    {
        return selfProperties.get(key);
    }

    public void putSelfProperties(String key, String value)
    {
        this.selfProperties.put(key, value);
    }

    public List<Plugin> getPlugins()
    {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins)
    {
        this.plugins = plugins;
    }

    public Dependency getSelf()
    {
        return self;
    }

    public void setSelf(Dependency self)
    {
        this.self = self;
        putSelfProperties("project.version", self.getVersion());
    }

}
