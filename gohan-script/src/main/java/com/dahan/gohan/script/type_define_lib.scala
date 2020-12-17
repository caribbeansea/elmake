package com.dahan.gohan.script

import com.dahan.gohan.repository.GohanDependency

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

  val dependency: GohanDependency.type = com.dahan.gohan.repository.GohanDependency

}
