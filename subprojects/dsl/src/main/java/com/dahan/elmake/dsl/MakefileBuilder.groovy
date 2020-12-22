package com.dahan.elmake.dsl

import com.dahan.elmake.Files
import com.dahan.elmake.Streams
import com.dahan.elmake.StringUtils
import com.dahan.elmake.exception.LackRequiredParam
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


    Makefile parseMakefile() {
        return compileMakefile(this.basedir)
    }

    //
    // 1) 编译makefile构建脚本
    //
    // 2) 验证makefile
    //
    // 3) 如果 settings 不是空的，那么就先解析settings
    //
    private Makefile compileMakefile(String basedir) {
        // #1
        def makefile = compileGroovy(readIntegValue(AutoconfConst.MAKEFILE_NAME, basedir, false),
                new File("${basedir}/${AutoconfConst.MAKEFILE_NAME}")) as Makefile

        // #2
        verifyMarkfileRequiredParam(makefile)

        // #3
        def setfile = compileSettings(basedir)

        if (setfile != null) {
            def submodules = setfile.getSubModules()
            submodules.each {
                def subMakefile = compileMakefile("${basedir}/${it}")
                subMakefile.parent = makefile
                makefile.addSubMakefile(subMakefile)
            }
        }

        return makefile
    }

    /**
     * 编译 settings.elmake 构建脚本
     */
    private Setfile compileSettings(String basedir) {
        def src = readIntegValue(AutoconfConst.SETTINGS_NAME, basedir, true)
        if (src == null) return null
        return compileGroovy(src)
    }

    /**
     * 读取并合并构建脚本(settings.elmake)源码
     */
    private String readIntegValue(String name, String basedir, boolean nullable) {

        def src
        def path = "${basedir}/${name}"
        if (nullable) {
            src = readHomeValue(path, null)
            if (src == null) return null
        } else {
            src = readHomeValue(path, "未能获取到 ${name} 文件, 在${path}")
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
    private static String readHomeValue(String path, String notfound) {
        def file = new File(path)
        if (file.exists()) {
            return Files.readString(file)
        } else {
            if (notfound != null) {
                throw new FileNotFoundException(notfound)
            }
        }
        return null
    }

    /**
     * 验证Makefile必要参数数据
     *
     * @param makefile makefile对象
     */
    private static void verifyMarkfileRequiredParam(Makefile makefile) {

        if (makefile.isSubproject()) {

            if (StringUtils.isEmpty(makefile.name)) {
                throw new LackRequiredParam("${AutoconfConst.MAKEFILE_NAME}缺少必要参数：group")
            }

        } else {

        }

    }

    /**
     * 编译构建脚本（settings, makefile）
     *
     * @param src 脚本源码
     * @return 对应的脚本对象
     */
    private <T> T compileGroovy(String src, Object... objects) {
        return ClassUtils.newInstance(classLoader.parseClass(src), objects)
    }

}
