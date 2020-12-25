package com.dahan.elmake.repository

import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.graph.Dependency

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
 * Creates on 2020/12/25.
 */

/**
 * @author tiansheng
 */
/**
 * Dependency各项参数，以及坐标信息
 */
class DependencyInfo {

    private var groupId: String? = null

    private var artifactId: String? = null

    private var version: String? = null

    private var scope: Scope = Scope.COMPILER

    private var classifier: String? = null

    private var ext: String? = "jar"

    /**
     * eclipse dependency object.
     */
    private var dependency: Dependency? = null

    constructor(coords: String) : this(coords, null, null)

    constructor(coords: String, classifier: String?) : this(coords, classifier, null)

    constructor(coords: String, classifier: String?, scope: String? = null) : this(coords, classifier, null, scope)

    constructor(coords: String, classifier: String? = null, ext: String? = null, scope: String? = null) {

        val split = coords.split(":")
        val artifact = ArtifactParam(split[0], split[1], split[2])

        if (classifier != null) {
            artifact.classifier = classifier
        }

        this.groupId = artifact.groupId
        this.artifactId = artifact.artifactId
        this.version = artifact.version

        if (scope != null) {
            this.scope = Scope.valueOf(scope.toUpperCase())
        }

        if (ext != null) {
            this.ext = ext
        }

        this.classifier = artifact.classifier
        if (classifier == null && ext == null) {
            this.dependency = Dependency(DefaultArtifact("$groupId:$artifactId:$version"), this.scope.value)
        } else {
            this.dependency = Dependency(DefaultArtifact(groupId, artifactId, this.classifier, this.ext, version), this.scope.name)
        }

    }

    fun getGroupId(): String? = this.groupId

    fun getArtifactId(): String? = this.artifactId

    fun getVersion(): String? = this.version

    fun getDependency() = this.dependency

}
