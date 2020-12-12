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
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author kevin
 */
public abstract class GohanOption extends Option implements Comparable<GohanOption>
{

    /**
     * 操作命令执行顺序（ 优先级，比如 lvar > clean > build > run -h ）
     */
    private int order;

    /**
     * 参数列表
     */
    private String[] params;

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

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public String[] getParams()
    {
        return params;
    }

    public void setParams(String... params)
    {
        this.params = params;
    }

    public abstract void exec(String... args);

    @Override
    public int compareTo(@NotNull GohanOption o)
    {
        return this.order - o.order;
    }
}
