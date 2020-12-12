package com.dahan.gohan

import com.dahan.gohan.collect.Lists
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.option.GohanOption
import com.dahan.gohan.reflect.ClassUtils
import org.apache.commons.cli.*

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
 * @author kevin
 */
class GohanMain
{

    public static Options options

    private static def commands = {

        def c_home = "com/dahan/gohan/option/command"
        def source = GohanMain.classLoader.getResource(c_home)
        def classfiles = new File(source.path)

        def map = Maps.newHashMap()

        classfiles.listFiles().each {
            def path = it.path.replaceAll("/Users/wuyanzu/project/IdeaProjects/gohan/gohan-builder/target/classes/", "")
                    .replaceAll("/", ".")
                    .replaceAll(".class", "")

            def option = ClassUtils.newInstance(Class.forName(path)) as GohanOption
            map.put(option.getLongOpt(), option)
        }

        println()

        return map
    }.call() as Map<String, GohanOption>

    /*[
            build: opt(C_Build.class),
            clean: opt(C_Clean.class),
            run  : opt(C_Run.class),
            debug: opt(C_Debug.class),
            help : opt(C_Help.class),
            lvar : opt(C_Help.class),
    ]*/

    /**
     * 入口函数
     * @param args 参数命令
     */
    static void main(String[] args)
    {
        run(parseCommands(args))
    }

    /**
     * 解析命令
     * @param args 命令参数
     */
    static void run(String[] args)
    {
        loadCommandOptions()
        def commandLineParser = new DefaultParser()
        try
        {
            execute(commandLineParser.parse(options, args))
        } catch (Throwable e)
        {
            commands.help.exec(e.getMessage())
            e.printStackTrace()
        }
    }

    /**
     * 执行命令
     */
    static void execute(CommandLine cli)
    {
        List<GohanOption> inputOptions = Lists.newArrayList()
        commands.each { String key, GohanOption value ->
            if (cli.hasOption(getCommandKey(value)))
            {
                value.setParams(getOptionValueMap(cli, value))
                inputOptions.add(value)
            }
        }

        Collections.sort(inputOptions)

        inputOptions.each {
            it.exec(getOptionValueMap(cli, it))
        }

    }

    /** 加载命令行 **/
    static void loadCommandOptions()
    {
        options = new Options()
        commands.each { K, V ->
            options.addOption(V)
        }
    }

    /** 解析命令行 **/
    static String[] parseCommands(String[] args)
    {
        args.eachWithIndex { String entry, int i ->
            def option = commands.get(entry)

            if (option != null)
            {
                args[i] = "-" + args[i]
            }
        }
        return args
    }

    /** 只获取longOpt，也就是长命令。为了统一不使用短命令行 **/
    static String getCommandKey(Option hasOpt) { hasOpt.getLongOpt() }

    /** 获取命令参数 **/
    static String[] getOptionValueMap(CommandLine cli, GohanOption option)
    {
        return cli.getOptionValues(getCommandKey(option))
    }

}
