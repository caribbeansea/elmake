package com.dahan.gohan.repository


import org.junit.Test

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
 * Creates on 2020/12/4.
 */

/**
 * @author kevin
 */
class RepositoryTest
{

    @Test
    void importDependency()
    {
        def s = System.currentTimeMillis()
        RepositoryUtils.getDependency("com.alibaba", "fastjson", "1.2.66")
        RepositoryUtils.getDependency("org.springframework.boot", "spring-boot-starter-web", "2.1.4.RELEASE")
        RepositoryUtils.getDependency("org.springframework.boot", "spring-boot-starter-tomcat", "2.1.4.RELEASE")
        RepositoryUtils.getDependency("org.springframework.boot", "spring-boot-starter-aop", "2.1.4.RELEASE")
        RepositoryUtils.getDependency("com.baomidou", "mybatis-plus-boot-starter", "3.4.0")
        RepositoryUtils.getDependency("com.github.pagehelper", "pagehelper", "5.1.11")
        RepositoryUtils.getDependency("io.springfox", "springfox-swagger2", "2.9.2")
        RepositoryUtils.getDependency("commons-net", "commons-net", "3.7")
        def e = System.currentTimeMillis()
        println "已加载的依赖数量: ${DependencyUtils.getDependencyMap().size()}"

        println "加载失败的依赖数量: ${RepositoryUtils.getDownloadFailure().size()}"

        println "耗时：${e - s}ms"

    }

}
