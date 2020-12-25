package com.dahan.elmake.repository

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

import com.dahan.elmake.repository.utils.RepositoryUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.SyncContext
import org.eclipse.aether.collection.CollectRequest
import org.eclipse.aether.collection.CollectResult
import org.eclipse.aether.deployment.DeployRequest
import org.eclipse.aether.deployment.DeployResult
import org.eclipse.aether.installation.InstallRequest
import org.eclipse.aether.installation.InstallResult
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.LocalRepositoryManager
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.resolution.*

/**
 * 仓库对象
 *
 * @author tiansheng
 */
class ElmakeRepository(c_name: String, c_address: String) {

    object Constant {
        // TODO FIX(RELEASE)
        val localDirectory: String = System.getProperty("user.dir") + "/repository-test/"
    }

    private val name: String = c_name

    private val repositorySystem: RepositorySystem = RepositoryUtils.newRepositorySystem()

    private val session: RepositorySystemSession = RepositoryUtils.newRepositorySystemSession(repositorySystem, Constant.localDirectory);

    private val central: RemoteRepository = RemoteRepository.Builder("central", "default", c_address).build()

    fun getName(): String = name

    fun getRemoteRepository(): RemoteRepository = central

    fun resolveVersionRange(request: VersionRangeRequest): VersionRangeResult = this.repositorySystem.resolveVersionRange(session, request)

    fun resolveVersion(request: VersionRequest): VersionResult = this.repositorySystem.resolveVersion(session, request)

    fun readArtifactDescriptor(request: ArtifactDescriptorRequest): ArtifactDescriptorResult = this.repositorySystem.readArtifactDescriptor(session, request)

    fun collectDependencies(request: CollectRequest): CollectResult = this.repositorySystem.collectDependencies(session, request)

    fun resolveDependencies(request: DependencyRequest): DependencyResult = this.repositorySystem.resolveDependencies(session, request)

    fun resolveArtifact(request: ArtifactRequest): ArtifactResult = this.repositorySystem.resolveArtifact(session, request)

    fun resolveArtifacts(requests: List<ArtifactRequest>): List<ArtifactResult> = this.repositorySystem.resolveArtifacts(session, requests)

    fun resolveMetadata(requests: Collection<MetadataRequest>): List<MetadataResult> = this.repositorySystem.resolveMetadata(session, requests)

    fun install(request: InstallRequest): InstallResult = this.repositorySystem.install(session, request)

    fun deploy(request: DeployRequest): DeployResult = this.repositorySystem.deploy(session, request)

    fun newLocalRepositoryManager(localRepository: LocalRepository): LocalRepositoryManager = this.repositorySystem.newLocalRepositoryManager(session, localRepository)

    fun newSyncContext(shared: Boolean): SyncContext = this.repositorySystem.newSyncContext(session, shared)

    fun newResolutionRepositories(repositories: List<RemoteRepository>): List<RemoteRepository> = this.repositorySystem.newResolutionRepositories(session, repositories)

    fun newDeploymentRepository(repository: RemoteRepository): RemoteRepository = this.repositorySystem.newDeploymentRepository(session, repository)

}