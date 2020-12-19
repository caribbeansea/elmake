package com.dahan.eimoto

import com.dahan.eimoto.commandline.Option
import com.dahan.eimoto.commandline.Options

object OptionBeans {

    /**
     * 获取options实例
     */
    val opts: Options = Options(
            build(),
            clean(),
            run(),
            debug(),
            lvar(),
            help()
    )

}

/**
 * 执行构建
 */
private class build : Option("build", "build", false, "构建项目，参数为项目名或模块名。如果不传则默认打包整个项目。") {

    init {
        order = 2
    }

    override fun exec() {

    }

}

/**
 * 清空编译缓存
 */
private class clean : Option("clean", "clean", false, "清空编译缓存") {

    init {
        order = 1
    }

    override fun exec() {

    }

}

/**
 * 使用run启动
 */
private class run : Option("run", "run", true, "使用普通模式运行项目，参数为入口函数所存在的类全名。") {

    init {
        order = 5
    }

    override fun exec() {

    }

}

/**
 * 使用debug启动
 */
private class debug : Option("debug", "debug", true, "使用DEBUG模式运行项目，参数为入口函数所存在的类全名。") {

    init {
        order = 5
    }

    override fun exec() {

    }

}

/**
 * 查看帮助
 */
private class help : Option("help", "help", false, "查看帮助") {

    init {
        order = 10
    }

    override fun exec() {

    }

}

/**
 * 设置局部变量，参数传递使用
 */
private class lvar : Option("lvar", "lvar", true, "设置局部变量") {

    init {
        order = 0
    }

    override fun exec() {

    }

}