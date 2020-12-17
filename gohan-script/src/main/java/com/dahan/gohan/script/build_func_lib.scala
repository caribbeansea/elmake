package com.dahan.gohan.script

import com.dahan.gohan.StringUtils
import com.dahan.gohan.script.scriptparse.ProjectModel
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
 * Creates on 2020/12/17.
 */

/**
 * 一切从简
 *
 * @author tiansheng
 */
object build_func_lib extends public_func_lib {

  private val markGroup: int = 0
  private val markArtifact: int = 1
  private val markVersion: int = 2

  private var projectModel: ProjectModel = _

  def group(name: string): void = __notnull__(name, markGroup)

  def name(name: string): void = __notnull__(name, markArtifact)

  def version(version: string): void = __notnull__(version, markVersion)

  /**
   * 指定父模块
   *
   * @param coords 依赖GAV坐标
   */
  def parent(coords: string): void = {
    if (StringUtils.isNotEmpty(coords)) {

    }
  }

  /**
   * 使用插件
   *
   * @param coords 插件GAV坐标
   */
  def apply(coords: string): void = {}

  /**
   * 自定义任务
   *
   * @param name     任务名称
   * @param taskCall 任务实现（闭包）
   * @return TaskCall实例
   */
  def task(name: string, taskCall: TaskCall): void = {

  }

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
      case this.markGroup => projectModel.setGroupId(value)
      case this.markArtifact => projectModel.setArtifactId(value)
      case this.markVersion => projectModel.setVersion(value)
    }

  }

}
