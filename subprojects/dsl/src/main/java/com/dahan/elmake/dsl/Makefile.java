package com.dahan.elmake.dsl;

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
 * Creates on 2020/12/21.
 */

import com.dahan.elmake.collect.Lists;
import com.dahan.elmake.repository.ElMakeDependency;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author tiansheng
 */
public abstract class Makefile implements SpecificApi, TagFuncApi
{

    /**
     * 组ID，也就是maven中的groupId
     */
    protected String groupId;

    /**
     * name，可以理解为artifactId
     */
    protected String name;

    /**
     * 项目版本
     */
    protected String version;

    /**
     * 支持的语言种类
     */
    protected List<String> langs;

    /**
     * 当前makefile是不是子项目
     */
    protected boolean subproject;

    /**
     * 完整路径
     */
    protected File self;

    /**
     * 引用的依赖包列表
     */
    protected List<ElMakeDependency> dependencies;

    /**
     * 依赖管理列表
     */
    protected Map<String, ElMakeDependency> dependencyManager;

    /**
     * 项目配置
     */
    protected Setfile setfile;

    /**
     * 子模块的构建脚本
     */
    protected List<Makefile> subMakefile;

    /**
     * 父模块
     */
    protected Makefile parent;

    @Override
    public void lang(String... langs)
    {
        if (langs != null)
        {
            this.langs = Lists.of(langs);
        }
    }

    @Override
    public void group(String name)
    {
        this.groupId = name;
    }

    @Override
    public void name(String name)
    {
        this.name = name;
    }

    @Override
    public void version(String version)
    {
        this.version = version;
    }

    @Override
    public void include(String coords)
    {
        include(coords, null);
    }

    @Override
    public void include(String coords, String scope)
    {
        include(coords, scope, null);
    }

    @Override
    public void include(String coords, String scope, String classifier)
    {
        include(coords, scope, classifier, null);
    }

    @Override
    public void include(String coords, String scope, String classifier, String ext)
    {
        if (dependencies == null)
        {
            dependencies = Lists.newArrayList();
        }
        dependencies.add(new ElMakeDependency(coords, classifier, ext, scope));
    }

    @Override
    public void includes(MakeFunction makeFunction)
    {
        makeFunction.apply();
    }

    @Override
    public void includeManager(MakeFunction makeFunction)
    {
        makeFunction.apply();
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public List<String> getLangs()
    {
        return langs;
    }

    public void setLangs(List<String> langs)
    {
        this.langs = langs;
    }

    public List<ElMakeDependency> getDependencies()
    {
        return dependencies;
    }

    public void setDependencies(List<ElMakeDependency> dependencies)
    {
        this.dependencies = dependencies;
    }

    public Map<String, ElMakeDependency> getDependencyManager()
    {
        return dependencyManager;
    }

    public void setDependencyManager(Map<String, ElMakeDependency> dependencyManager)
    {
        this.dependencyManager = dependencyManager;
    }

    public boolean isSubproject()
    {
        return subproject;
    }

    public void setSubproject(boolean subproject)
    {
        this.subproject = subproject;
    }

    public Setfile getSetfile()
    {
        return setfile;
    }

    public void setSetfile(Setfile setfile)
    {
        this.setfile = setfile;
    }

    public File getSelf()
    {
        return self;
    }

    public void setSelf(File self)
    {
        this.self = self;
    }

    public List<Makefile> getSubMakefile()
    {
        return subMakefile;
    }

    public void addSubMakefile(Makefile subMakefile)
    {
        if(this.subMakefile == null) {
            this.subMakefile = Lists.newArrayList();
        }
        this.subMakefile.add(subMakefile);
    }

    public void setSubMakefile(List<Makefile> subMakefile)
    {
        this.subMakefile = subMakefile;
    }

    public Makefile getParent()
    {
        return parent;
    }

    public void setParent(Makefile parent)
    {
        this.parent = parent;
    }
}
