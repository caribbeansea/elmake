package com.dahan.gohan.repository

import com.dahan.gohan.Files
import com.dahan.gohan.collect.Maps
import com.dahan.gohan.collection.exception.DNOCollects
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Scope

/*
 * Creates on 2020/12/2.
 */

/**
 * 仓库对象
 * @author kevin
 */
class Repository {

    // 仓库名称
    private String name

    // 仓库地址
    private String address

    private static String USER_DIR = System.getProperty("user.dir")

    // 本地仓库地址，下载的也放在该地址下。
    static String localDirectory = USER_DIR + "/repository/"

    // 仓库地址类型
    static final int LOCAL = 0, REMOTE = 1

    /**
     * 所有可用的仓库对象
     */
    static final Map<String, Repository> repositories = Maps.newHashMap()

    static final DNOCollects collects = new DNOCollects()

    static {
        Files.mkdirsNotExist(localDirectory)
    }

    // 仓库类型
    private int type

    Repository() {}

    Repository(String name, String address) {
        this.name = name
        this.address = address

        // 如果是URL
        if (address.startsWith("http://") || address.startsWith("https://")) {
            type = REMOTE
        } else {
            def fileAddress = new File(address)
            if (fileAddress.isDirectory()) {
                type = LOCAL
            } else {
                throw new UnknownHostException(address)
            }
        }

        repositories.put(name, this)
    }

    /**
     * 仓库URL，可以是本地也可以是远程
     */
    void setRepository(String address) {
        this.address = address
    }

    Dependency getDependency(String groupId, String artifactId, String version) {
        return getDependency(groupId, artifactId, version, null)
    }

    /**
     * 获取仓库中的依赖包
     */
    Dependency getDependency(String groupId, String artifactId, String version, Scope scope) {
        Dependency dependency = new Dependency(groupId, artifactId, version, scope)
        // 如果本地仓库没有当前依赖的文件目录就创建
        Files.mkdirsNotExist(localDirectory + dependency.getLocalDirectory())
        def jarfile = getLocalJarfile(dependency)
        // 如果没有jar就去当前仓库目录下载
        if (!jarfile.exists()) {
            RepositoryUtils.downloadDependency(dependency, this)
        }
        return dependency
    }

    String getDownloadAddress(Dependency dependency, int downloadType) {
        return address + (downloadType == Dependency.JAR ? dependency.jar() : dependency.pom())
    }

    int getType() {
        return type
    }

    /**
     * 获取本地的Jar文件
     */
    protected static File getLocalJarfile(Dependency dependency) {
        return new File(localDirectory + dependency.jar())
    }

}
