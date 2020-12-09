package com.dahan.gohan.repository;

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

import com.dahan.gohan.repository.utils.RepositoryUtils;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.SyncContext;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.CollectResult;
import org.eclipse.aether.collection.DependencyCollectionException;
import org.eclipse.aether.deployment.DeployRequest;
import org.eclipse.aether.deployment.DeployResult;
import org.eclipse.aether.deployment.DeploymentException;
import org.eclipse.aether.installation.InstallRequest;
import org.eclipse.aether.installation.InstallResult;
import org.eclipse.aether.installation.InstallationException;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.*;

import java.util.Collection;
import java.util.List;

/**
 * 仓库对象
 *
 * @author kevin
 */
public final class GohanRepository
{

    //
    // 本地储存路径
    //
    private static final String localDirectory = System.getProperty("user.dir") + "/repository/";

    //
    // 仓库名称
    //
    private final String name;

    //
    // 远程仓库对象
    //
    private final RemoteRepository central;

    //
    // 会话
    //
    private final RepositorySystemSession session;

    //
    // 系统仓库
    //
    private final RepositorySystem repositorySystem;

    public GohanRepository(String name, String address)
    {

        this.name = name;

        this.repositorySystem = RepositoryUtils.newRepositorySystem();
        this.session = RepositoryUtils.newRepositorySystemSession(repositorySystem, localDirectory);

        central = new RemoteRepository.Builder("central", "default", address).build();

    }

    public String getName()
    {
        return name;
    }

    public RemoteRepository getRemoteRepository()
    {
        return central;
    }

    public RepositorySystemSession getRemoteSystemSession()
    {
        return session;
    }

    public RepositorySystem getRepositorySystem()
    {
        return repositorySystem;
    }

    // ----------------- 下面的方法来自实现 RepositorySystem 接口的 ---------------

    public VersionRangeResult resolveVersionRange(VersionRangeRequest request) throws VersionRangeResolutionException
    {
        return this.repositorySystem.resolveVersionRange(session, request);
    }


    public VersionResult resolveVersion(VersionRequest request) throws VersionResolutionException
    {
        return this.repositorySystem.resolveVersion(session, request);
    }


    public ArtifactDescriptorResult readArtifactDescriptor(ArtifactDescriptorRequest request) throws ArtifactDescriptorException
    {
        return this.repositorySystem.readArtifactDescriptor(session, request);
    }


    public CollectResult collectDependencies(CollectRequest request) throws DependencyCollectionException
    {
        return this.repositorySystem.collectDependencies(session, request);
    }


    public DependencyResult resolveDependencies(DependencyRequest request) throws DependencyResolutionException
    {
        return this.repositorySystem.resolveDependencies(session, request);
    }


    public ArtifactResult resolveArtifact(ArtifactRequest request) throws ArtifactResolutionException
    {
        return this.repositorySystem.resolveArtifact(session, request);
    }


    public List<ArtifactResult> resolveArtifacts(Collection<? extends ArtifactRequest> requests) throws ArtifactResolutionException
    {
        return this.repositorySystem.resolveArtifacts(session, requests);
    }


    public List<MetadataResult> resolveMetadata(Collection<? extends MetadataRequest> requests)
    {
        return this.repositorySystem.resolveMetadata(session, requests);
    }


    public InstallResult install(InstallRequest request) throws InstallationException
    {
        return this.repositorySystem.install(session, request);
    }


    public DeployResult deploy(DeployRequest request) throws DeploymentException
    {
        return this.repositorySystem.deploy(session, request);
    }


    public LocalRepositoryManager newLocalRepositoryManager(LocalRepository localRepository)
    {
        return this.repositorySystem.newLocalRepositoryManager(session, localRepository);
    }


    public SyncContext newSyncContext(boolean shared)
    {
        return this.repositorySystem.newSyncContext(session, shared);
    }


    public List<RemoteRepository> newResolutionRepositories(List<RemoteRepository> repositories)
    {
        return this.repositorySystem.newResolutionRepositories(session, repositories);
    }


    public RemoteRepository newDeploymentRepository(RemoteRepository repository)
    {
        return this.repositorySystem.newDeploymentRepository(session, repository);
    }

}
