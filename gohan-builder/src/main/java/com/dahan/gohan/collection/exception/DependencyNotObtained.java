package com.dahan.gohan.collection.exception;

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
 * Creates on 2020/12/2.
 */

import com.dahan.gohan.Formprint;
import com.dahan.gohan.Langs;
import com.dahan.gohan.exception.GohanException;
import com.dahan.gohan.repository.dependency.Dependency;

/**
 * @author kevin
 */
public class DependencyNotObtained extends GohanException {

    private final Dependency dependency;

    private final int type;

    private final String selfCause;

    public DependencyNotObtained(Dependency dependency, int type) {
        this(dependency, type, Langs.ERROR_DEPENDENCY_CANNOT_IMPORT());
    }

    public DependencyNotObtained(Dependency dependency, int type, String message) {
        super(dependency.getGroupId().concat(":").concat(dependency.getArtifactId())
                .concat(":").concat(dependency.getVersion()).concat(" ").concat(message));
        this.dependency = dependency;
        this.type = type;
        this.selfCause = message;
    }

    public void addToForm(Formprint formprint) {
        formprint.addFormContent(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion());
    }

    public String getGroupId() {
        return dependency.getGroupId();
    }

    public String getName() {
        return dependency.getArtifactId();
    }

    public String getVersion() {
        return dependency.getVersion();
    }

    public String getSelfCause() {
        return selfCause;
    }

    public int getType() {
        return type;
    }
}
