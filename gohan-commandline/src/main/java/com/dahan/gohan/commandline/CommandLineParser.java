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

import com.dahan.gohan.commandline.exception.CommandLineParseException;

/**
 * 命令行解析
 *
 * @author tiansheng
 */
public interface CommandLineParser
{

    /**
     * 解析启动时传入的参数
     *
     * @param args 命令行参数
     */
    CommandLine parse(String[] args) throws CommandLineParseException;

}
