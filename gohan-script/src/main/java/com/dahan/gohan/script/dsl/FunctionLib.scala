package com.dahan.gohan.script.dsl

import com.dahan.gohan.repository.GohanDependency

import scala.concurrent.duration.DurationConversions.Classifier

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
object FunctionLib {

  def include(coor: String): Unit = {
    include(coor, null)
  }

  def include(coor: String, classifier: String): Unit = {
    new GohanDependency(coor, classifier)
  }

}
