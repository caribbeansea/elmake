package com.dahan.elmake.commandline;
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

import com.dahan.elmake.commandline.exception.CommandLineParseException;
import org.junit.Test;

import java.util.List;

/**
 * @author tiansheng
 */
public class InputArgsTest
{

    private final String[] args = new String[]{
            "-build",
            "a",
            "b",
            "-run",
            "-debug p=8080"
    };

    @Test
    public void main() throws CommandLineParseException
    {
        // 创建Options
        Options options = new Options(new BuildOption());
        CommandLine commandLine = new DefaultCommandLineParser(options).parse(args);
        commandLine.defExec();
    }

    static class BuildOption extends Option
    {

        public BuildOption()
        {
            super.setLongOpt("build");
            super.setShortOpt("bui");
            super.setHasArgs(true);
            super.setDescription("build测试");
        }

        @Override
        public void exec(List<String> values)
        {
            System.out.println("---> build");
        }

    }

}
