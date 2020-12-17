package com.dahan.gohan


import com.dahan.gohan.collect.Maps
import com.dahan.gohan.commandline.DefaultCommandLineParser
import com.dahan.gohan.commandline.Option
import com.dahan.gohan.commandline.Options
import com.dahan.gohan.commandline.exception.CommandLineParseException
import com.dahan.gohan.reflect.ClassUtils

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
class GohanMain
{

    public static Options options = {

        def c_home = "com/dahan/gohan/option/command"
        def source = GohanMain.classLoader.getResource(c_home)
        def classfiles = new File(source.path)

        def loadOpts = new Options()

        classfiles.listFiles().each {
            def path = it.path.replaceAll("/Users/wuyanzu/project/IdeaProjects/gohan/gohan-builder/target/classes/", "")
                    .replaceAll("/", ".")
                    .replaceAll(".class", "")

            loadOpts.addOpt(ClassUtils.newInstance(Class.forName(path)))
        }

        return loadOpts

    }.call() as Options

    /**
     * 入口函数
     * @param args 参数命令
     */
    static void main(String[] args)
    {
        run(processInputCommands(args))
    }

    /**
     * 解析命令
     * @param args 命令参数
     */
    static void run(String[] args)
    {
        try
        {
            new DefaultCommandLineParser(options).parse(args).orderExec()
        } catch (CommandLineParseException e)
        {
            e.printStackTrace()
        }

    }

    /**
     * 处理是命令的参数
     */
    static String[] processInputCommands(String[] args)
    {
        args.eachWithIndex { String entry, int index ->
            if(options.hasOption(entry))
            {
                args[index] = "-${entry}"
            }
        }
        return args
    }

}
