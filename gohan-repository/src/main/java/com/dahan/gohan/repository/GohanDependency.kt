package com.dahan.gohan.repository

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
import scala.concurrent.duration.DurationConversions
import kotlin.coroutines.coroutineContext

/**
 * 依赖基础信息类
 *
 * @author tiansheng
 */
class GohanDependency {

    private var groupId: String? = null

    private var artifactId: String? = null

    private var version: String? = null

    private var scope: String = "compile"

    private var classifier: String? = null

    /**
     * eclipse dependency object.
     */
    private var dependency: Dependency? = null

    constructor(coords: String) : this(coords, null, null)

    constructor(coords: String, classifier: String?) : this(coords, classifier, null)

    constructor(coords: String, classifier: String? = null, scope: String? = null) {
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
            this.dependency = Dependency(DefaultArtifact("$groupId:$artifactId:$version"), this.scope)
        } else {
            this.dependency = Dependency(DefaultArtifact(groupId, artifactId, classifier, "jar", version), this.scope)
        }

    }

    fun getGroupId(): String? = groupId

    fun getArtifactId(): String? = artifactId

    fun getVersion(): String? = version

    fun getDependency(): Dependency? = this.dependency

}