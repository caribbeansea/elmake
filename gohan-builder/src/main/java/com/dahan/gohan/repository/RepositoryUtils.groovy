package com.dahan.gohan.repository

import com.dahan.gohan.http.Areyouok
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.initialize.alibaba.MavenOfAlibaba

/*
 * Creates on 2020/12/3.
 */

/**
 * 仓库工具类
 * @author kevin
 */
class RepositoryUtils {

    /**
     * 下载依赖
     * @param dependency 依赖信息
     */
    static void downloadDependency(Dependency dependency, Repository repository) {
        if (repository.getType() == Repository.REMOTE) {
            Areyouok.download(repository.getDownloadAddress(dependency, Dependency.JAR), repository.localDirectory, dependency.jar())
            Areyouok.download(repository.getDownloadAddress(dependency, Dependency.POM), repository.localDirectory, dependency.pom())
        }
    }

    public static void main(String[] args) {
        def r = new MavenOfAlibaba()
        def d = r.getDependency("com.alibaba", "fastjson", "1.2.66")
    }

}
