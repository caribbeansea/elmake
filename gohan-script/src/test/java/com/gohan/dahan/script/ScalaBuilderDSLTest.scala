package com.gohan.dahan.script

import com.dahan.gohan.repository.GohanDependency
import com.dahan.gohan.script.buildscript.GohanBuilder

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
 * Creates on 2020/12/16.
 */

/**
 * @author kevin
 */
object ScalaBuilderDSLTest extends GohanBuilder {

  group("com.dahan")
  name("gohan")
  version("gh-2+0")

  dependencies(Array[GohanDependency](
    new GohanDependency("org.slf4j", "slf4j-api", "1.7.25"),
    new GohanDependency("de.defmacro", "eclipse-astparser", "8.1"),
  ))

}