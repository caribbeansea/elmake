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

import com.dahan.gohan.StringUtils;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.util.graph.transformer.ConflictResolver;

/**
 * 依赖基础信息类
 *
 * @author kevin
 */
public final class GohanDependency
{

    private String groupId;

    private String artifactId;

    private String version;

    private String scope = "compile";

    private String classifier = "classifier";

    private String extension = "jar";

    private Dependency dependency;

    public GohanDependency(String groupId, String artifactId, String version)
    {
        this(groupId, artifactId, version, null, null);
    }

    public GohanDependency(String groupId, String artifactId, String version, String classifier)
    {
        this(groupId, artifactId, version, classifier, null);
    }

    public GohanDependency(String groupId, String artifactId, String version, String classifier, String scope)
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;

        if (StringUtils.isNotEmpty(scope))
        {
            this.scope = scope;
        }

        if (StringUtils.isNotEmpty(classifier))
        {
            this.classifier = classifier;
        }

        DefaultArtifact defaultArtifact = new DefaultArtifact(groupId, artifactId, classifier, extension, version);

        dependency = new Dependency(defaultArtifact, this.scope);

    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public Dependency toEclipseDependency()
    {
        return dependency;
    }

}
