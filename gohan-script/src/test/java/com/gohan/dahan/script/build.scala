package com.gohan.dahan.script

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
 * @author tiansheng
 */

import com.dahan.gohan.script.annotation._
import com.dahan.gohan.script.build_func_lib._

object BuildRun {
  def main(args: stringArray): Unit = {
    build // 执行build脚本
    build.ext
    build.includes
    build.manager
    build.tasks
    println("执行完毕")
  }
}

@buildtool object build {

  group("com.dahan")
  name("gohan")
  version("gh-1+0")

  @ext object ext {
    val fastJsonVersion = "2.6.66"
  }

  @includes object includes {
    include(s"com.alibaba:fastjson:${ext.fastJsonVersion}")
  }

  @includesManager object manager {
  }

  @tasks object tasks {

    task("mytask", () => {
      println("x")
      println("x")
      println("x")
      println("x")
    })

  }

}
