package com.dahan.gohan.repository.dependency

/*
 * Creates on 2020/12/2.
 */

/**
 * 依赖存在范围
 *
 * @author kevin
 */
enum Scope {

    /**
     * 编译时存在, 如果没有设置默认COMPILE范围。此范围会将依赖打包
     * 到classpath下去。与jar包一起打包。
     */
    COMPILE,

    /**
     * 只在测试时使用，打包并不会将scope为test的依赖打包到classpath下。
     */
    TEST,

    /**
     * 系统依赖，该依赖是指本地存在的依赖信息。且会打包到classpath下去供运行
     * 时使用。
     */
    LOCAL,

    /**
     * EXLOCAL和LOCAL范围效果一致，但是EXLOCAL不会将本地依赖打包到classpath下。
     */
    EXLOCAL,

    PROVIDED,

}
