package com.dahan.elmake.repository;

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
 * Creates on 2020/12/9.
 */

import com.dahan.elmake.collect.Lists;
import com.dahan.elmake.repository.utils.RepositoryUtils;
import org.junit.jupiter.api.Test;

/**
 * @author tiansheng
 */
public class ResolveDependencyTest {

    @Test
    public void resolveDependencyTest() {
        RepositoryUtils.INSTANCE.resolveDependencies(Lists.of(
                new DependencyInfo("com.alibaba:fastjson:1.2.66"),
                new DependencyInfo("net.sf.json-lib:json-lib:2.4", "jdk15")
        ));
    }

}
