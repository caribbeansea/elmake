package com.dahan.gohan.option.command

import com.dahan.gohan.option.GohanOption

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
 * Creates on 2020/12/12.
 */

/**
 * 构建命令
 *
 * @author kevin
 */
class C_Run extends GohanOption
{

    C_Run()
    {
        this("run", "run", true, "使用普通模式运行项目，参数为入口函数所存在的类全名。")
        setOrder(5)
    }

    private C_Run(String opt, String description) throws IllegalArgumentException
    {
        super(opt, description)
    }

    private C_Run(String opt, boolean hasArg, String description) throws IllegalArgumentException
    {
        super(opt, hasArg, description)
    }

    private C_Run(String opt, String longOpt, boolean hasArg, String description) throws IllegalArgumentException
    {
        super(opt, longOpt, hasArg, description)
    }

    @Override
    void exec(String... args)
    {
        println("run:" + values[0])
    }

}