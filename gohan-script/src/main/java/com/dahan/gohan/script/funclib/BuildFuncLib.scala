package com.dahan.gohan.script.funclib

import com.dahan.gohan.script.task.TaskCall

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
 * 一切从简
 *
 * @author tiansheng
 */
object BuildFuncLib {

  type int = Int
  type long = Long
  type byte = Byte
  type short = Short
  type float = Float
  type double = Double
  type bool = Boolean
  type char = Char
  type string = java.lang.String

  type array[T] = scala.Array[T]
  type stringArray = array[string]
  type intArray = array[int]
  type longArray = array[long]
  type shortArray = array[short]
  type byteArray = array[byte]
  type floatArray = array[float]
  type doubleArray = array[double]
  type boolArray = array[bool]
  type charArray = array[char]
  type relyArray = array[rely] // 依赖数组

  type map[K, V] = Map[K, V]
  type relyMap = map[string, rely] // 依赖Map类型
  type defMap = map[string, string] // 默认Map类型

  type rely = com.dahan.gohan.repository.GohanDependency // GohanDependency简写类型

  type void = Unit

  private var groupId: string = _
  private var artifactId: string = _
  private var version: string = _

  protected var exts: defMap = _

  def group(name: string): void = this.groupId = name

  def name(name: string): void = this.artifactId = name

  def version(version: string): void = this.version = version

  //
  // 指定父模块
  //
  def parent(coords: string): void = {

  }

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
  def include(coords: string, classifier: string = null, scope: string = null): rely = new rely(coords, classifier)

  //
  // 使用插件
  //
  def apply(coords: string): void = {}

  //
  // 定义任务
  //
  def task(name: string, taskCall: TaskCall): void = {

  }

}
