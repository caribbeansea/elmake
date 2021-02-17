package com.dahan.elmake.joincompiler;

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
 * Creates on 2020/12/25.
 */

import com.dahan.elmake.collect.Lists;
import com.dahan.elmake.dsl.Makefile;

import java.util.List;

/**
 * 联合编译, 用于编译多种支持JVM语言的应用。能够在一个项目中同时使用如，
 * Java、Groovy、Scala等语言。
 *
 * @author tiansheng
 */
public class JoinCompiler {

    /**
     * 构建脚本
     */
    private Makefile makefile;

    enum SupportJVMLangs {
        GROOVY, KOTLIN, SCALA
    }

    public JoinCompiler() {
    }

    public JoinCompiler(Makefile makefile) {
        this.makefile = makefile;
    }

    public void compile() {
    }

    private List<SupportJVMLangs> getSupportJvmLangs()  {
        List<SupportJVMLangs> jvmLangs = Lists.newArrayList();
        List<String> langsStr = makefile.getLangs();
        if(langsStr != null && !langsStr.isEmpty()) {
            for (String lang : langsStr) {
                jvmLangs.add(SupportJVMLangs.valueOf(lang.toUpperCase()));
            }
        }
        return jvmLangs;
    }

}
