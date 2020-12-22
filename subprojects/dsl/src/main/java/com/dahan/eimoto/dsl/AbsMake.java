package com.dahan.eimoto.dsl;

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

import com.dahan.eimoto.collect.Lists;
import com.dahan.eimoto.repository.EimotoDependency;

import java.util.List;
import java.util.Map;

/**
 * @author tiansheng
 */
public abstract class AbsMake implements SpecificApi, TagFuncApi
{

    protected String groupId;

    protected String name;

    protected String version;

    protected List<String> langs;

    protected List<EimotoDependency> dependencies;

    protected Map<String, EimotoDependency> dependencyManager;

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
        dependencies.add(new EimotoDependency(coords, classifier, ext, scope));
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

    public List<EimotoDependency> getDependencies()
    {
        return dependencies;
    }

    public void setDependencies(List<EimotoDependency> dependencies)
    {
        this.dependencies = dependencies;
    }

    public Map<String, EimotoDependency> getDependencyManager()
    {
        return dependencyManager;
    }

    public void setDependencyManager(Map<String, EimotoDependency> dependencyManager)
    {
        this.dependencyManager = dependencyManager;
    }
}
