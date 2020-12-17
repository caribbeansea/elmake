package com.dahan.gohan.objects

import com.dahan.gohan.StringUtils
import org.junit.Test
import org.openjdk.jol.info.ClassLayout
import org.openjdk.jol.vm.VM

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
 * Creates on 2020/12/7.
 */

/**
 * @author tiansheng
 */
class ObjectLayoutInMemory
{

    @Test
    void printLayout()
    {
        println VM.current().details()

        println(StringUtils.getSpaceOfSize(64, "-") + "\n")

        println("对象头布局")
        println(ClassLayout.parseInstance(new Object()).toPrintable())

    }

}
