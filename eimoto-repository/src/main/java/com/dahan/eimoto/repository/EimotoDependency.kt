package com.dahan.eimoto.repository

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.graph.Dependency

/**
 * 依赖基础信息类
 *
 * @author tiansheng
 */
class EimotoDependency {

    private var groupId: String? = null

    private var artifactId: String? = null

    private var version: String? = null

    private var scope: Scope = Scope.COMPILER

    private var classifier: String? = null

    /**
     * eclipse dependency object.
     */
    private var dependency: Dependency? = null

    /**
     * 依赖指定范围
     */
    enum class Scope(val value: String) {

        /**
         * 依赖会在编译和运行时期存在，并且会打包进classpath
         */
        COMPILER("compiler"),

        /**
         * 依赖只在测试时存在，不会被打包进classpath
         */
        TEST("test"),

        /**
         * 引入系统依赖，并且它的效果与 COMPILER 一样。
         * 同样会被打包进claspath。
         */
        SYSTEM("system"),

        /**
         * 引入系统依赖，但它不会被打包进classpath。
         */
        EXSYSTEM("system"),

        ;

    }

    constructor(coords: String) : this(coords, null, null)

    constructor(coords: String, classifier: String?) : this(coords, classifier, null)

    constructor(coords: String, classifier: String? = null, scope: Scope? = null) {
        val split = coords.split(":")
        val artifact = Artifact(split[0], split[1], split[2])

        if (classifier != null) {
            artifact.classifier = classifier
        }

        this.groupId = artifact.groupId
        this.artifactId = artifact.artifactId
        this.version = artifact.version

        if (scope != null) {
            this.scope = scope
        }

        this.classifier = artifact.classifier
        if (this.classifier == null) {
            this.dependency = Dependency(DefaultArtifact("$groupId:$artifactId:$version"), this.scope.value)
        } else {
            this.dependency = Dependency(DefaultArtifact(groupId, artifactId, classifier, "jar", version), this.scope.name)
        }

    }

    fun getGroupId(): String? = groupId

    fun getArtifactId(): String? = artifactId

    fun getVersion(): String? = version

    fun getDependency(): Dependency? = this.dependency

}