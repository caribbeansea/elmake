package com.dahan.gohan.commandline;
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

import com.dahan.gohan.collect.Lists;
import com.dahan.gohan.collect.Maps;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author tiansheng
 */
public class Options implements Iterable<Option>
{

    /**
     * 全称
     */
    private final Map<String, Option> longOptMap = Maps.newHashMap();

    /**
     * 简称
     */
    private final Map<String, Option> shortOptMap = Maps.newHashMap();

    private final List<Option> options = Lists.newArrayList();

    public Options()
    {
    }

    public Options(Option... options)
    {
        for (Option option : options)
        {
            addOpt(option);
        }
    }

    /**
     * 添加定义的命令行
     */
    public void addOpt(Option opt)
    {
        if (opt != null)
        {
            longOptMap.put(opt.getLongOpt(), opt);
            if (opt.getShortOpt() != null)
            {
                shortOptMap.put(opt.getShortOpt(), opt);
            }
            options.add(opt);
        }
    }

    /**
     * 获取Option
     *
     * @param optName 命令名称
     * @return Option对象实例
     */
    public Option getOption(String optName)
    {
        if (longOptMap.containsKey(optName))
            return longOptMap.get(optName);
        else return shortOptMap.getOrDefault(optName, null);
    }

    /**
     * 是否存在某个命令
     *
     * @param optName 命令名称
     * @return true就代表有这个命令
     */
    public boolean hasOption(String optName)
    {
        return getOption(optName) != null;
    }

    public void sort()
    {
        Collections.sort(options);
    }

    @NotNull
    @Override
    public Iterator<Option> iterator()
    {
        return options.iterator();
    }

}
