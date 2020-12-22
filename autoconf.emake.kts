group("com.dahan")
name("eimoto")
version("gh-1+01")

//
// 添加一门支持JVM的语言
//
lang("groovy", "kotlin", "scala")

val commandCliVersion = "1.4"
val slf4jVersion = "1.7.25"
val logbackVersion = "1.1.11"
val fastjsonVersion = "1.2.66"
val okhttpVersion = "4.8.0"
val eimotoCommonVersion = "1.0.0.BETA"
val dom4jVersion = "1.6.1"
val aetherVersion = "1.0.0V20140518"
val mavenVersion = "3.1.0"
val wagonVersion = "1.0"
val scalaVersion = "2.13.4"
val kotlinVersion = "1.4.21"

/**
 * 导入依赖
 */
includes {
    include("org.slf4j:slf4j-api:${slf4jVersion}")
    include("ch.qos.logback:logback-core:${logbackVersion}")
    include("ch.qos.logback:logback-classic:${logbackVersion}")
    include("org.codehaus.groovy:groovy-all:3.0.4")
    include("com.alibaba:fastjson:${fastjsonVersion}")
    include("com.squareup.okhttp3:okhttp:${okhttpVersion}")
    include("org.scala-lang:scala-library:${scalaVersion}")
    include("org.scala-lang:scala-reflect:${scalaVersion}")
    include("org.scala-lang:scala-compiler:${scalaVersion}")
    include("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    include("org.jetbrains.kotlin:kotlin-test:${kotlinVersion}", "test")
}

includesManager {
    include("de.defmacro:eclipse-astparser:8.1")
    include("dom4j:dom4j:${dom4jVersion}")
    include("org.openjdk.jol:jol-core:0.10", "test")
}
