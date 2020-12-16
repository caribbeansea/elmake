package com.dahan.gohan.script

import com.dahan.gohan.reflect.ClassUtils
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.tools.GroovyClass

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
class GohanClassLoader extends GroovyClassLoader
{

    private GohanClassLoader()
    {
    }

    private GohanClassLoader(ClassLoader loader)
    {
        super(loader)
    }

    private GohanClassLoader(GroovyClassLoader parent)
    {
        super(parent)
    }

    private GohanClassLoader(ClassLoader parent, CompilerConfiguration config, boolean useConfigurationClasspath)
    {
        super(parent, config, useConfigurationClasspath)
    }

    private GohanClassLoader(ClassLoader loader, CompilerConfiguration config)
    {
        super(loader, config)
    }

    /**
     * @return 获取配置好的类加载起
     */
    static GohanClassLoader getInstance()
    {
        def configuration = new CompilerConfiguration()
        configuration.setSourceEncoding("UTF-8")
        // 设置父ClassLoader为当前线程加载的ClassLoader
        return new GohanClassLoader(Thread.currentThread().contextClassLoader, configuration)
    }

    /**
     * 获取GroovyObject
     *
     * @param source 源码
     * @return groovyObject实例
     */
    static GroovyObject getGroovyObject(String source)
    {
        return ClassUtils.newInstance(getInstance().parseClass(source)) as GroovyObject
    }

}
