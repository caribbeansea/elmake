package com.dahan.eimoto


import com.dahan.eimoto.commandline.DefaultCommandLineParser
import com.dahan.eimoto.commandline.Options
import com.dahan.eimoto.commandline.exception.CommandLineParseException

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
 * Creates on 2020/11/29.
 */

/**
 * 命令行解析
 * @author tiansheng
 */
object EimotoMainKt {

    private val options: Options = OptionBeans.opts

    @JvmStatic
    fun main(args: Array<String>) {
        doMain(processInputCommands(args))
    }

    /**
     * 解析命令
     * @param args 命令参数
     */
    private fun doMain(args: Array<String>) {
        try {
            DefaultCommandLineParser(options).parse(args).orderExec()
        } catch (e: CommandLineParseException) {
            e.printStackTrace()
        }

    }

    /**
     * 处理是命令的参数
     */
    private fun processInputCommands(args: Array<String>): Array<String> {
        args.forEachIndexed { i: Int, item: String ->
            if (options.hasOption(item)) {
                args[i] = "-${item}"
            }
        }
        return args
    }

}
