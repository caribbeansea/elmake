package com.dahan.eimoto.bootsrap

import com.dahan.eimoto.EimotoMainKt

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
 * Creates on 2020/12/18.
 */

/**
 * @author tiansheng
 */
object StartBuilding {

    @JvmStatic
    fun main(args: Array<String>) {
        val userdir = System.getProperty("user.dir")
        val clistr = "build lvar buildKts=${userdir}/build.eimoto.kts settingsKts=${userdir}/settings.eimoto.kts"
        EimotoMainKt.main(clistr.split(" ").toTypedArray())
    }

}