def commandCliVersion = "1.4"
def slf4jVersion = "1.7.25"
def logbackVersion = "1.1.11"
def fastjsonVersion = "1.2.66"
def okhttpVersion = "4.8.0"
def elmakeCommonVersion = "1.0.0.BETA"
def dom4jVersion = "1.6.1"
def aetherVersion = "1.0.0V20140518"
def mavenVersion = "3.1.0"
def wagonVersion = "1.0"
def scalaVersion = "2.13.4"
def kotlinVersion = "1.4.21"

/**
 * 导入依赖
 */
includes {
    include("org.slf4j:slf4j-api:${slf4jVersion}")
    include("ch.qos.logback:logback-core:${logbackVersion}")
    include("ch.qos.logback:logback-classic:${logbackVersion}")
    include("org.codehaus.groovy:groovy-all:3.0.7", ext = "pom")
    include("com.alibaba:fastjson:${fastjsonVersion}")
    include("com.squareup.okhttp3:okhttp:${okhttpVersion}")
    include("org.scala-lang:scala-library:${scalaVersion}")
    include("org.scala-lang:scala-reflect:${scalaVersion}")
    include("org.scala-lang:scala-compiler:${scalaVersion}")
    include("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    include("org.jetbrains.kotlin:kotlin-test:${kotlinVersion}", "test")
}

includeManager {
    include("de.defmacro:eclipse-astparser:8.1")
    include("dom4j:dom4j:${dom4jVersion}")
    include("org.openjdk.jol:jol-core:0.10", "test")
}