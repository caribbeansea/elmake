package com.dahan.gohan.script

import scala.reflect.ClassTag

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
 * Creates on 2020/12/17.
 */

/**
 * @author tiansheng
 */
trait gohan_util_lib extends type_define_lib {

  /**
   * 这里的  _* 代表参数可变。妈的，scala的语法真他妈的傻逼（爱了❤️）。
   *
   * @param elems 元素列表
   */
  def listof[E](elems: E*): *[E] = List(elems: _*)

}
