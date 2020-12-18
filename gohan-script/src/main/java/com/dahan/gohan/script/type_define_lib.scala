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
 * DSL类型定义
 *
 * @author tiansheng
 */
trait type_define_lib {

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

  type *[T] = List[T] // list

  type map[K, V] = Map[K, V]
  type relyMap = map[string, rely] // 依赖Map类型
  type defMap = map[string, string] // 默认Map类型

  type rely = com.dahan.gohan.repository.GohanDependency // GohanDependency简写类型

  type void = Unit

  val rely: GohanDependency.type = com.dahan.gohan.repository.GohanDependency

}
