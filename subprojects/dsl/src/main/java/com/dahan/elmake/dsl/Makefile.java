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
import com.dahan.elmake.collect.Maps;
import com.dahan.elmake.repository.DependencyInfo;
import org.eclipse.aether.graph.DependencyNode;

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
    protected List<DependencyInfo> includeDependencies;

    /**
     * 依赖管理列表
     */
    protected Map<String, DependencyInfo> includeManagerDependency;

    /**
     * 使用的依赖列表
     */
    protected Map<String, DependencyNode> usingDepsMap = Maps.newHashMap();

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

    enum Call
    {
        INCLUDES,
        INCLUDE_MANAGER,
    }

    /**
     * 添加当前项目使用的依赖
     *
     * @param node 工件结果对象（当 {@link DependencyInfo} 被下载或者是本地引用完成之后会返回一个 {@link DependencyNode} 结果。
     *             通过这个结果我们就可以去构建一个当前项目使用的所有依赖。例如：我们引用了 A 依赖，A 又去引用了 B，在这个结果中就包含
     *             A、B两个依赖。
     */
    public void addUsingDependency(DependencyNode node)
    {
        usingDepsMap.put(node.getArtifact().toString(), node);
    }

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
        if (includeDependencies == null)
        {
            includeDependencies = Lists.newArrayList();
        }
        includeDependencies.add(new DependencyInfo(coords, classifier, ext, scope));
    }

    @Override
    public void includes(MakeFunction makeFunction)
    {
        makeFunction.apply(Call.INCLUDES);
    }

    // TODO
    // public void includeManager(MakeFunction makeFunction)
    // {
    //     makeFunction.apply(Call.INCLUDE_MANAGER);
    // }

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

    public List<DependencyInfo> getDependencies()
    {
        return includeDependencies;
    }

    public void setDependencies(List<DependencyInfo> includeDependencies)
    {
        this.includeDependencies = includeDependencies;
    }

    public Map<String, DependencyInfo> getDependencyManager()
    {
        return includeManagerDependency;
    }

    public void setDependencyManager(Map<String, DependencyInfo> dependencyManager)
    {
        this.includeManagerDependency = dependencyManager;
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
        if (this.subMakefile == null)
        {
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
