package com.dahan.eimoto.repository.utils

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

import com.dahan.eimoto.collect.Lists
import com.dahan.eimoto.repository.EimotoDependency
import com.dahan.eimoto.repository.EimotoRepository
import org.eclipse.aether.collection.CollectRequest
import org.eclipse.aether.collection.DependencyCollectionException
import org.eclipse.aether.resolution.DependencyRequest

/**
 * 集合请求依赖
 *
 * @author tiansheng
 */
object RequestUtils {

    @Throws(DependencyCollectionException::class)
    fun newDependencyRequest(
            dependencies: List<EimotoDependency>,
            repositories: List<EimotoRepository>,
            repository: EimotoRepository?,
    ): DependencyRequest {
        val collectResult = repository?.collectDependencies(newCollectRequest(dependencies, repositories))
        val root = collectResult?.root

        val dependencyRequest = DependencyRequest()
        dependencyRequest.root = root

        return dependencyRequest
    }

    fun newCollectRequest(dependencies: List<EimotoDependency>, eimotoRepository: EimotoRepository): CollectRequest {
        return newCollectRequest(dependencies, Lists.of(eimotoRepository))
    }

    private fun newCollectRequest(dependencies: List<EimotoDependency>, repositories: List<EimotoRepository>): CollectRequest {
        val request = CollectRequest()

        dependencies.forEach {
            request.addDependency(it.getDependency())
        }

        repositories.forEach {
            request.addRepository(it.getRemoteRepository())
        }

        return request
    }

}
