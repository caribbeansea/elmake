package com.dahan.eimoto.option.command

import com.dahan.eimoto.commandline.Option


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
 * @author tiansheng
 */
class C_Build extends Option
{
    C_Build()
    {
        super("build", "build", false, "构建项目，参数为项目名或模块名。如果不传则默认打包整个项目。")
        setOrder(2)
    }

    @Override
    void exec()
    {
    }

}