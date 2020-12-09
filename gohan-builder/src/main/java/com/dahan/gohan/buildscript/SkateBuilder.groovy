package com.dahan.gohan.buildscript

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
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class SkateBuilder
{

    /**
     * 模块信息
     */
    private ProjectInformation projectInformation

    private static final def GROUP = 0

    private static final def ARTIFACT = 1

    private static final def VERSION = 2

    /**
     * 模块groupId
     */
    def groupId(String group) { projectInformationNotNull(group, GROUP) }

    /**
     * 模块artifactId
     */
    def artifactId(String artifact) { projectInformationNotNull(artifact, ARTIFACT) }

    /**
     * 模块当前版本号
     */
    def version(String version) { projectInformationNotNull(version, VERSION) }


    /**
     * 模块引用的依赖包
     */
    void optionals(Closure closure) { closure.call() }

    /**
     * 使用插件
     * @param name 插件名称
     */
    def plugin(String name) {}

    /**
     * 依赖信息，主要信息为groupId, artifactId, version。其他的为自定义属性。
     * 例如pom.xml中dependency节点下的scpoe、type、compile等字段。
     */
    def optional(LinkedHashMap<String, String> settings) {
    }

    /**
     * 添加模块信息
     *
     * @param value 属性值
     * @param type 属性类型
     */
    void projectInformationNotNull(String value, int type) {
        if (projectInformation) {
            projectInformation = new ProjectInformation()
        }
        switch (type) {
            case GROUP: {
                projectInformation.setGroupId(value); break
            }
            case ARTIFACT: {
                projectInformation.setArtifactId(value); break
            }
            case VERSION: {
                projectInformation.setVersion(value); break
            }
        }
    }

}
