package com.dahan.eimoto

import com.alibaba.fastjson.util.IOUtils
import com.dahan.eimoto.commandline.Option
import com.dahan.eimoto.commandline.Options
import com.dahan.eimoto.exception.KtsScriptNotFound
import java.io.File
import java.io.InputStream
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object OptionBeans {

    /**
     * 获取options实例
     */
    val opts: Options = Options(
            Build(),
            Clean(),
            Run(),
            Debug(),
            Lvar(),
            Help()
    )

    const val basepath = "com/dahan/eimoto"

}

/**
 * 执行构建
 */
private class Build : Option("build", "build", false, "构建项目，参数为项目名或模块名。如果不传则默认打包整个项目。") {

    init {
        order = 2
    }

    override fun exec(values: MutableList<String>) {

        val buildKtsPath = settings["buildKts"] as String
        val settingsKtsPath = settings["settingsKts"] as String

        val buildKts = File(buildKtsPath)

        if (!buildKts.exists()) throw KtsScriptNotFound("未找到 build.eimoto.kts 构建脚本，在路径：${buildKtsPath}")

        val src = insertKtsImpl(Files.readString(buildKts), "build")

        println()

    }

    fun insertKtsImpl(implstr: String, prefix: String): String {
        val streams: InputStream =
                Streams.getResourceAsStream("${OptionBeans.basepath}/${prefix}.eimoto.kts.tmp", this::class.java.classLoader)

        val tmp = String(streams.readAllBytes())

        return tmp.replace("#impl", implstr)
    }

}

/**
 * 清空编译缓存
 */
private class Clean : Option("clean", "clean", false, "清空编译缓存") {

    init {
        order = 1
    }

    override fun exec(values: MutableList<String>) {

    }

}

/**
 * 使用run启动
 */
private class Run : Option("run", "run", true, "使用普通模式运行项目，参数为入口函数所存在的类全名。") {

    init {
        order = 5
    }

    override fun exec(values: MutableList<String>) {

    }

}

/**
 * 使用debug启动
 */
private class Debug : Option("debug", "debug", true, "使用DEBUG模式运行项目，参数为入口函数所存在的类全名。") {

    init {
        order = 5
    }

    override fun exec(values: MutableList<String>) {

    }

}

/**
 * 查看帮助
 */
private class Help : Option("help", "help", false, "查看帮助") {

    init {
        order = 10
    }

    override fun exec(values: MutableList<String>) {

    }

}

/**
 * 设置局部变量，参数传递使用
 */
private class Lvar : Option("lvar", "lvar", true, "设置局部变量") {

    init {
        order = 0
    }

    override fun exec(values: MutableList<String>) {
        values.forEach {
            val split = it.split("=")
            settings[split[0]] = split[1]
        }
    }

}