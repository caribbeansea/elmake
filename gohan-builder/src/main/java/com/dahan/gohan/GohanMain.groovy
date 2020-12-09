package com.dahan.gohan

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

    private static Options options

    private static def commandArgs = [
            BUILD: opt('build', 'build', true, '构建项目，参数为项目名或模块名。如果不传则默认打包整个项目。'),
            CLEAN: opt('clean', 'clean', true, '清空编译缓存'),
            RUN  : opt('run', 'run', true, '使用普通模式运行项目，参数为入口函数所存在的类全名。'),
            DEBUG: opt('debug', 'debug', true, '使用DEBUG模式运行项目，参数为入口函数所存在的类全名。'),
            HELP : opt('help', 'h', true, '查看帮助'),
    ]

    /**
     * 入口函数
     * @param args 参数命令
     */
    static void main(String[] args)
    {
        run(args)
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
        } catch (Throwable ignore)
        {
            new HelpFormatter().printHelp('命令解析有误，请查看帮助', options)
        }
    }

    /**
     * 解析命令
     * @param commandLine 判断当前执行命令
     */
    static void execute(CommandLine cli)
    {
        if (cli.hasOption(getCommandKey(commandArgs.RUN)))
        {
            // TODO 构建当前项目
        }
    }

    /**
     * 创建{@code Option}参数
     *
     * @param longOpt 完整命令
     * @param shortOpt 精简的命令
     * @param hasArgs 是否有参数
     * @param description 介绍
     * @return Option对象实例
     */
    static Option opt(String longOpt, String shortOpt, boolean hasArgs, String description)
    {
        def option = new Option(longOpt, shortOpt, hasArgs, description)
        return option
    }

    /** 加载命令行 **/
    static void loadCommandOptions()
    {
        options = new Options()
        commandArgs.each { K, V ->
            options.addOption(V)
        }
    }

    /** 只获取longOpt，也就是长命令。为了统一不使用短命令行 **/
    static String getCommandKey(Option hasOpt) { hasOpt.getLongOpt() }

    /** 退出程序 **/
    static void exit()
    {
        System.exit(0)
    }

}
