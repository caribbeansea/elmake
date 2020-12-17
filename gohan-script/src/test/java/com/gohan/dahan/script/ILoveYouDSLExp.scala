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
package love {

  class LoveHelper private(who: String) {

    def love(beLoved: String) = {
      beLoved match {
        case LoveHelper.You => println(who + " Love " + beLoved)
        case _ => println("You don't love " + beLoved )
      }

    }
  }

  object LoveHelper {

    val I = "I"

    val You = "You"

    implicit def conver(who: String) = new LoveHelper(who)

  }

}

import love.LoveHelper._

object LoveSample {

  def main(args: Array[String]): Unit = {

    I love You

    I love "marry"

  }

}