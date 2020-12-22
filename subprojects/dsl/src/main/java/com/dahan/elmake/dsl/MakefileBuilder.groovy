package com.dahan.elmake.dsl

import com.dahan.elmake.Files
import com.dahan.elmake.Streams
import com.dahan.elmake.dsl.kotlin.MakeOfKts
import com.dahan.elmake.reflect.ClassUtils

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
 * Creates on 2020/12/22.
 */

/**
 * @author tiansheng
 */
class MakefileBuilder {

    private String basedir

    private GroovyClassLoader classLoader

    MakefileBuilder() {}

    MakefileBuilder(String basedir, ClassLoader classLoader) {
        this.basedir = basedir
        this.classLoader = new GroovyClassLoader(classLoader)
    }

    //
    // 1) 编译makefile构建脚本
    //
    // 2) 获取settings配置文件并编译
    //
    Makefile parseMakefile() {

        def makefile = compileMakefile()

        def settings = compileSettings()

        return null
    }

    /**
     * 编译 autoconf.elmake 构建脚本
     */
    private Makefile compileMakefile() {
        return compileGroovy(readIntegValue(AutoconfConst.MAKEFILE_NAME, false))
    }

    /**
     * 编译 settings.elmake 构建脚本
     */
    private Settingsfile compileSettings() {
        def src = readIntegValue(AutoconfConst.SETTINGS_NAME, true)
        if (src == null) return null
        return compileGroovy(src)
    }

    /**
     * 读取并合并构建脚本(settings.elmake)源码
     */
    private String readIntegValue(String name, boolean nullable) {

        def src

        if (nullable) {
            src = readHomeValue(name, null)
            if (src == null) return null
        } else {
            src = readHomeValue(name, "未能获取到 ${name} 文件")
        }

        def stream = Streams.getResourceAsStream("com/dahan/elmake/overview/${name}.overview", classLoader)
        def tmp = new String(stream.readAllBytes()).replace("#IMPL", src)
        return tmp.replace("#IMPL", src)
    }

    /**
     * 获取项目根目录下的文件内容
     *
     * @param name 文件名
     * @return 文件内容
     */
    private String readHomeValue(String name, String notfound) {
        def file = new File("${basedir}/${name}")
        if (file.exists()) {
            return Files.readString(new File("${basedir}/${name}"))
        } else {
            if (notfound != null) {
                throw new FileNotFoundException(notfound)
            }
        }
        return null
    }

    /**
     * 编译构建脚本（settings, makefile）
     *
     * @param src 脚本源码
     * @return 对应的脚本对象
     */
    private <T> T compileGroovy(String src) {
        return ClassUtils.newInstance(classLoader.parseClass(src))
    }

}
