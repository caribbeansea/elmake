package com.dahan.gohan.script

import com.dahan.gohan.Files

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
 * Creates on 2020/12/15.
 */

/**
 * .gcript文件解析
 * @author kevin
 */
class Gcript
{

    static void parse(File file)
    {
        // file != null && file.exists()
        if (file && file.exists())
        {
            // new instance classloader
            def classLoader = GohanClassLoader.instance
            def script = buildScript(Files.readString(file))
        }
    }

    private static String buildScript(String source)
    {

    }

}
