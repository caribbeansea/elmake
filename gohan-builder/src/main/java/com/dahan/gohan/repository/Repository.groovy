package com.dahan.gohan.repository

/*
 * Creates on 2020/12/2.
 */

/**
 * 仓库对象
 *
 * @author kevin
 */
interface Repository {

    /**
     * 仓库URL，可以是本地也可以是远程
     */
    void setRepository(String localOrRemote)

    /**
     * 获取仓库中的依赖包
     */
    Dependency getDependency(String groupId, String artifactId, String version)

}
