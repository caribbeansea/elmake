package com.dahan.eimoto.commandline;
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
 * Creates on 2020/12/14.
 */

import com.dahan.eimoto.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author tiansheng
 */
public abstract class Option implements Comparable<Option>
{

    /**
     * 命令全称
     */
    private String longOpt;

    /**
     * 命令简称
     */
    private String shortOpt;

    /**
     * 该命令参数是否必须
     */
    private boolean hasArgs = false;

    /**
     * 命令介绍
     */
    private String description;

    /**
     * 命令参数列表
     */
    private List<String> values;

    /**
     * 执行顺序
     */
    private int order;

    public Option()
    {
    }

    public Option(String longOpt, String shortOpt, boolean hasArgs, String description)
    {
        this.longOpt = longOpt;
        this.shortOpt = shortOpt;
        this.hasArgs = hasArgs;
        this.description = description;
    }

    public String getLongOpt()
    {
        return longOpt;
    }

    public void setLongOpt(String longOpt)
    {
        this.longOpt = longOpt;
    }

    public String getShortOpt()
    {
        return shortOpt;
    }

    public void setShortOpt(String shortOpt)
    {
        this.shortOpt = shortOpt;
    }

    public boolean isHasArgs()
    {
        return hasArgs;
    }

    public void setHasArgs(boolean hasArgs)
    {
        this.hasArgs = hasArgs;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getValues()
    {
        return values;
    }

    public void addValues(String value)
    {
        if (this.values == null)
        {
            this.values = Lists.newArrayList();
        }
        this.values.add(value);
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    @Override
    public int compareTo(@NotNull Option o)
    {
        return this.order - o.order;
    }

    /**
     * 执行策略
     */
    public abstract void exec();

}
