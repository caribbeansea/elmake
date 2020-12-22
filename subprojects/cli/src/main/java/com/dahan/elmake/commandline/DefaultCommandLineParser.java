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

/**
 * @author tiansheng
 */
public class DefaultCommandLineParser implements CommandLineParser
{

    private final Options options;

    public DefaultCommandLineParser(Options options)
    {
        this.options = options;
    }

    @Override
    public CommandLine parse(String[] args) throws CommandLineParseException
    {
        Option previous = null;
        Options inputOpts = new Options();
        for (String optValue : args)
        {
            if (optValue.startsWith("-"))
            {
                Option option = options.getOption(optValue.substring(1));
                if (option != null)
                {
                    inputOpts.addOpt(option);
                    previous = option;
                }
            } else
            {
                if(previous != null)
                {
                    previous.addValues(optValue);
                }
            }
        }
        return new DefaultCommandLine(inputOpts);
    }

}
