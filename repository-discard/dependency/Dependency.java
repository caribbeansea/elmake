package com.dahan.gohan.repository.dependency;

import com.dahan.gohan.StringUtils;
import com.dahan.gohan.collect.Arrays;
import com.dahan.gohan.collect.Maps;
import com.dahan.gohan.repository.Repository;
import com.dahan.gohan.repository.pom.ProjectObjectModel;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Stack;

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
 * @author kevin
 */
public class Dependency
{

    /**
     * groupId
     **/
    private String groupId;

    /**
     * artifactId
     **/
    private String artifactId;

    /**
     * 版本好
     **/
    private String version;

    /**
     * 运行范围
     **/
    private Scope scope;

    /**
     * 依赖jar包
     **/
    private File jarfile;

    /**
     * pom文件
     **/
    private ProjectObjectModel projectObjectModel;

    /**
     * 其他配置信息
     **/
    private final LinkedHashMap<String, String> settings = Maps.newLinkedHashMap();

    /**
     * 依赖路径
     **/
    private String localDirectory;

    /**
     * 依赖文件名称格式
     **/
    private String dependencyName;

    /**
     * 当前依赖来自来个仓库
     */
    private Repository fromRepository;

    /**
     * 如果依赖有多个可用版本的话将版本信息保存在该成员内。（仅供下载使用，所以数据结构使用栈结构的方式）。
     * <p>
     * 当一个版本下载失败后弹出其他版本继续尝试下载。直至所有版本下载完依然没有下载下来的话那么就提示用户该
     * 依赖已经下载失败了。如果下载成功的话那么就以第一次下载成功的依赖版本作为使用的依赖版本。
     */
    private Stack<String> preselectVersions;

    private boolean resolve = true;

    private static final int JAR = 0, POM = 1;

    public Dependency()
    {

    }

    public Dependency(String groupId, String artifactId, String version)
    {
        this(groupId, artifactId, version, null, Scope.COMPILE, null);
    }

    public Dependency(String groupId, String artifactId, String version, Repository repository)
    {
        this(groupId, artifactId, version, null, Scope.COMPILE, repository);
    }

    public Dependency(String groupId, String artifactId, String version, String[] preselectVersions)
    {
        this(groupId, artifactId, version, preselectVersions, Scope.COMPILE, null);
    }

    public Dependency(String groupId, String artifactId, String version, String[] preselectVersions, Repository repository)
    {
        this(groupId, artifactId, version, preselectVersions, Scope.COMPILE, repository);
    }

    /**
     * 依赖实例对象构造器
     *
     * @param groupId           groupId
     * @param artifactId        artifactId
     * @param version           version
     * @param preselectVersions 预选版本
     * @param scope             范围
     * @param repository        由哪个仓库下载的
     */
    public Dependency(String groupId, String artifactId, String version,
                      String[] preselectVersions, Scope scope, Repository repository)
    {
        setGroupId(groupId);
        setArtifactId(artifactId);
        setVersion(version);

        if (preselectVersions != null && preselectVersions.length != 0)
        {
            this.preselectVersions = new Stack<>();
            this.preselectVersions.addAll(Arrays.asList(preselectVersions));
        }

        this.scope = scope;
        this.fromRepository = repository;
        // 生成依赖的本地路径
        this.localDirectory = StringUtils.append(groupId.replaceAll("\\.", "/"), "/", artifactId, "/", version, "/");
        this.dependencyName = artifactId.concat("-").concat(version);
    }

    public ProjectObjectModel getProjectObjectModel()
    {
        return projectObjectModel;
    }

    public void setProjectObjectModel(File pomxml)
    {
        this.projectObjectModel = new ProjectObjectModel(pomxml, this);
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = StringUtils.deleteEmpty(groupId);
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(String artifactId)
    {
        this.artifactId = StringUtils.deleteEmpty(artifactId);
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = StringUtils.deleteEmpty(version);
    }

    public Scope getScope()
    {
        return scope;
    }

    public void setScope(Scope scope)
    {
        this.scope = scope;
    }

    public LinkedHashMap<String, String> getSettings()
    {
        return settings;
    }

    public void setSettings(LinkedHashMap<String, String> settings)
    {
        this.settings.putAll(settings);
    }

    public void putSettings(String key, String value)
    {
        this.settings.put(key, value);
    }

    public File getJarfile()
    {
        return jarfile;
    }

    public void setJarfile(File jarfile)
    {
        this.jarfile = jarfile;
    }

    public String getLocalDirectory()
    {
        return localDirectory;
    }

    public String getDependencyName()
    {
        return dependencyName;
    }

    public void setDependencyName(String dependencyName)
    {
        this.dependencyName = dependencyName;
    }

    public String basePath()
    {
        return localDirectory.concat(dependencyName);
    }

    public String pom()
    {
        return basePath().concat(".pom");
    }

    public String jar()
    {
        return basePath().concat(".jar");
    }

    public String getCoordinate()
    {
        return groupId.concat(":").concat(artifactId).concat(":").concat(version);
    }

    public static String getCoordinate(String groupId, String artifactId, String version)
    {
        return groupId.concat(":").concat(artifactId).concat(":").concat(version);
    }

    public static int getJAR()
    {
        return JAR;
    }

    public static int getPOM()
    {
        return POM;
    }

    public void setProjectObjectModel(ProjectObjectModel projectObjectModel)
    {
        this.projectObjectModel = projectObjectModel;
    }

    public void setLocalDirectory(String localDirectory)
    {
        this.localDirectory = localDirectory;
    }

    public Repository getFromRepository()
    {
        return fromRepository;
    }

    public void setFromRepository(Repository fromRepository)
    {
        this.fromRepository = fromRepository;
    }

    public boolean isResolve()
    {
        return resolve;
    }

    public void setResolve(boolean resolve)
    {
        this.resolve = resolve;
    }

    /**
     * 使用预选版本
     */
    public boolean exchangePreselectVersion()
    {
        if (preselectVersions != null && !preselectVersions.isEmpty())
        {
            this.version = preselectVersions.pop();
            return true;
        }
        return false;
    }

}
