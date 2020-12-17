package com.dahan.gohan.script

import com.dahan.gohan.repository.GohanDependency

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
trait public_func_lib extends gohan_util_lib {

  def include(coords: string): rely = include(coords, null)

  /**
   * 导入依赖
   *
   * 如果依赖没有指定版本好那么会从父模块去寻找，如果父模块找不到就会到</dependencyManager>去找。依然
   * 没有找到的话就会报错提示依赖引入失败。
   *
   * @param coords     依赖坐标
   * @param classifier 分类
   * @param scope      范围
   * @return 依赖对象 #GohanDependency
   */
  def include(coords: string, classifier: string = null, scope: string = null): rely = {
    return dependency(coords, classifier, scope)
  }

}
