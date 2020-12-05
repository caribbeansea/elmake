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
class ProjectInformation {

    private String groupId

    private String artifactId

    private String version

    String getGroupId() {
        return groupId
    }

    void setGroupId(String groupId) {
        this.groupId = groupId
    }

    String getArtifactId() {
        return artifactId
    }

    void setArtifactId(String artifactId) {
        this.artifactId = artifactId
    }

    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
    }
}
