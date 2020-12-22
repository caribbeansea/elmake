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

/**
 * DSL的具体实现API
 *
 * @author tiansheng
 */
public interface SpecificApi
{

    /**
     * @param langs 当前项目所支持的语言种类（仅限运行在JVM上的语言）
     */
    void lang(String... langs);

    /**
     * 配置groupId
     */
    void group(String name);

    /**
     * 配置ArtifactId
     */
    void name(String name);

    /**
     * 配置版本号
     */
    void version(String version);

    /**
     * 引入依赖
     *
     * @param coords 依赖坐标，以fastjson为例。将groupId, artifactId, version以
     *               分号隔开。例如：<code>com.alibaba:fastjson:2.6.66</code>
     */
    void include(String coords);

    /**
     * 引入依赖
     *
     * @param coords 依赖坐标，以fastjson为例。将groupId, artifactId, version以
     *               分号隔开。例如：<code>com.alibaba:fastjson:2.6.66</code>
     *               <p>
     * @param scope  依赖存在范围，例如: <code>compiler, test</code>等
     */
    void include(String coords, String scope);

    /**
     * 引入依赖
     *
     * @param coords     依赖坐标，以fastjson为例。将groupId, artifactId, version以
     *                   分号隔开。例如：<code>com.alibaba:fastjson:2.6.66</code>
     *                   <p>
     * @param scope      依赖存在范围，例如: <code>compiler, test</code>等
     *                   <p>
     * @param classifier 使用场景一般为：区分不同JDK版本引用、区分工件不同部门组成。例如：源码、javadoc等。
     *                   通常用于区分从同一POM构建的具有不同内容的构件（artifact）。它是可选的，它可以是任意的字符串，附加在版本号之后。
     */
    void include(String coords, String classifier, String scope);

    /**
     * 引入依赖
     *
     * @param coords     依赖坐标，以fastjson为例。将groupId, artifactId, version以
     *                   <p>
     *                   分号隔开。例如：<code>com.alibaba:fastjson:2.6.66</code>
     *                   <p>
     * @param ext        用于区分要下载类型的后缀，默认为<code>jar</code>, 它会连同pom和jar包一起下载到项目本地库。
     *                   如果只需要下载pom，比如类似<code>spring-boot-parent</code>包，它只依赖pom文件，就可以配置为pom，表示
     *                   只下载pom文件。
     *                   <p>
     * @param scope      依赖存在范围，例如: <code>compiler, test</code>等
     *                   <p>
     * @param classifier 使用场景一般为：区分不同JDK版本引用、区分工件不同部门组成。例如：源码、javadoc等。
     *                   通常用于区分从同一POM构建的具有不同内容的构件（artifact）。它是可选的，它可以是任意的字符串，附加在版本号之后。
     */
    void include(String coords, String classifier, String ext, String scope);

}