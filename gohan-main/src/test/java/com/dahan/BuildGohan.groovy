package com.dahan

import com.dahan.gohan.buildscript.GohanBuilder

class BuildGohan extends GohanBuilder
{
    def buildscript = {

        //
        // 设置当前项目的GAV坐标
        //
        groupId "com.dahan"
        artifactId "gohan"
        version "1.0.0.CARVING"

        //
        // 添加Maven储存库，这里以阿里巴巴为例。
        //
        repositories {

            repository "https://maven.aliyun.com/repository/central"

        }

        //
        // dependency是一个Map, key有以下几个参数:
        //
        // 1) group, 这个参数就相当于pom中的groupId
        //
        // 2) name, 这个参数相当于pom中的artifactId
        //
        // 3) version, 这个没啥好说的就是版本
        //
        // 4) scope，指定依赖范围
        //
        //   4.1) compile 默认scope。
        //        表示dependency可以在生命周期中使用，而且这些dependencies会传递到依赖的项目中。
        //
        //   4.2) provided
        //        它与compile有些相似，表明dependency由jdk或容器提供。例如Servlet API和一些Java EE APIs。
        //        这个scope只能作用在编译和测试阶段，同时没有传递性。
        //
        //   4.3) runtime
        //        表示dependency不会作用于编译时，但是作用于运行和测试时。
        //
        //   4.4) test
        //        表示dependency作用在测试时，不作用在运行时。
        //
        //
        // 5) classifier, 这个参数一般很少用到。也等于pom中的classifier
        //
        dependencies {

            dependency group: "org.codehaus.groovy", name: "groovy-all", version: "${exts.groovyVersion}"

        }

        //
        // 自定义任务，以及执行脚本
        //
        tasks {

            //
            // 定义一个任务名称以及任务内容,然后在控制台执行[ gohan task hello ]，
            // 即可执行你自定义的任务内容。
            //
            task "hello", {
                println("this is my task.")
            }

        }

    }

    static void main(String[] args)
    {
        new BuildGohan().buildscript.call()
    }

}