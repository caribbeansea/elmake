package com.dahan.gohan.script.buildscript

import com.dahan.gohan.repository.{GohanDependency, GohanRepository}
import com.dahan.gohan.script.scriptparse.ProjectModel
import com.dahan.gohan.script.task.GohanTask

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
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
 * Creates on 2020/12/1.
 */

/**
 * @author kevin
 */
class GohanBuilder {

  private val GROUP: Int = 0

  private val ARTIFACT: Int = 1

  private val VERSION: Int = 2

  /**
   * 模块信息摘要
   */
  var projectModel: ProjectModel = _

  /**
   * 依赖包
   */
  protected var dependencyArray: Array[GohanDependency] = _

  /**
   * 定义项目的GroupId
   */
  def group(value: String): Unit = {
    __notnull__(value, GROUP)
  }

  /**
   * 定义项目的ArtifactId
   */
  def name(value: String): Unit = {
    __notnull__(value, ARTIFACT)
  }

  /**
   * 指定版本
   */
  def version(value: String): Unit = {
    __notnull__(value, VERSION)
  }

  /**
   * 设置依赖仓库
   */
  def repositories(repository: Array[GohanRepository]): Unit = {

  }

  /**
   * 依赖引用
   */
  def dependencies(gohanDependencies: Array[GohanDependency]): Unit = {}

  /**
   * 自定义任务
   */
  def tasks(tasks: Array[GohanTask]): Unit = {}

  /**
   * 添加模块摘要信息
   *
   * @param value 设置的值
   * @param mark  根据 { @code mark} 来匹配设置哪个摘要值
   */
  private def __notnull__(value: String, mark: Int): Unit = {

    if (projectModel == null) {
      this.projectModel = new ProjectModel()
    }

    mark match {
      case GROUP => projectModel.setGroupId(value)
      case ARTIFACT => projectModel.setArtifactId(value)
      case VERSION => projectModel.setVersion(value)
    }

  }

}
