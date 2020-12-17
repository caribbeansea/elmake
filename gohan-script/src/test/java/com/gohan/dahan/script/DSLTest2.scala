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
import helper.DateHelper._

package helper {
  import java.util.Calendar
  import java.util.Date

  class DateHelper private(number: Int) {

    def days(when: String): Date = {
      var date = Calendar.getInstance()
      when match {
        case DateHelper.ago => date.add(Calendar.DAY_OF_MONTH, -number)
        case DateHelper.from_now => date.add(Calendar.DAY_OF_MONTH, number)
        case _ => date
      }
      date.getTime()
    }
  }

  object DateHelper {
    val ago = "ago"
    val from_now = "from_now"
    implicit def convertInt2Date(number: Int) = new DateHelper(number)
  }
}

object Sample {

  def main(args: Array[String]): Unit = {

    val twoDayAgo = 2 days ago
    val twoDayAfter = 2 days from_now
    println("two days ago is " + twoDayAgo)
    println("two days after is " + twoDayAfter)

  }
}
