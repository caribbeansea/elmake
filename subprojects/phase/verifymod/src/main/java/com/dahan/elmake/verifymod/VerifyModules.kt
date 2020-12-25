package com.dahan.elmake.verifymod

import com.dahan.elmake.dsl.Makefile

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

/**
 * 验证模块信息
 *
 * @author tiansheng
 */
class VerifyModules(cmakefile: Makefile) {

    private val makefile = cmakefile

    /**
     * 验证模块信息
     */
    fun verify() {
        val submakefiles = makefile.subMakefile
    }

}