package com.dahan.skate.collection.exception;

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

import com.dahan.skate.Formprint;
import com.dahan.skate.exception.ErrorCollects;
import com.dahan.skate.repository.dependency.Dependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DependencyNotObtained异常收集器
 *
 * @author kevin
 */
public class DNOCollects extends ErrorCollects<DependencyNotObtained> {

    private static final Logger logger = LoggerFactory.getLogger(DNOCollects.class);

    @Override
    public void rollout() {
        if (!stack.isEmpty()) {
            Formprint formprint = new Formprint("groupId", "name", "type", "version", "cause");
            for (DependencyNotObtained dependencyNotObtained : stack) {
                formprint.addFormContent(dependencyNotObtained.getGroupId(), dependencyNotObtained.getName(),
                        dependencyNotObtained.getVersion(),
                        dependencyNotObtained.getType() == Dependency.getJAR() ? "jar" : "pom", dependencyNotObtained.getSelfCause());
            }
            logger.error(formprint.printf());
        }
    }

}
