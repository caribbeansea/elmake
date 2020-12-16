package com.gohan.dahan.script

import com.dahan.gohan.script.BuildGohan
import org.junit.Test

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
 * @author kevin
 */
class BuildGohanParseTest
{

    @Test
    void main()
    {
        def path = "/Users/wuyanzu/project/IdeaProjects/gohan/gohan-script/src/test/java/com/gohan/dahan/script/build.gohan"
        BuildGohan.parse(new File(path))
    }

}
