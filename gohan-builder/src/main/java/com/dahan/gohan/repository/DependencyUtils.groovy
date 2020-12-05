package com.dahan.gohan.repository

import com.dahan.gohan.repository.dependency.Dependency

/*
 * Creates on 2020/12/4.
 */

/**
 * @author kevin
 */
class DependencyUtils
{

    static boolean isJar(int type) { return Dependency.JAR == type }

    /**
     * 匹配子依赖
     * @param dependency 依赖信息
     */
    static void matchChildrenDependency(Dependency dependency)
    {
    }

}
