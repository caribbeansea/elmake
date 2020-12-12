package com.dahan.gohan.option;
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

import org.apache.commons.cli.Option;

/**
 * @author kevin
 */
public abstract class GohanOption extends Option
{

    public GohanOption(String opt, String description) throws IllegalArgumentException
    {
        super(opt, description);
    }

    public GohanOption(String opt, boolean hasArg, String description) throws IllegalArgumentException
    {
        super(opt, hasArg, description);
    }

    public GohanOption(String opt, String longOpt, boolean hasArg, String description) throws IllegalArgumentException
    {
        super(opt, longOpt, hasArg, description);
    }

    /**
     * 执行命令
     *
     * @param args 参数
     * @return true代表执行成功
     */
    public abstract boolean exec(String... args);

}
