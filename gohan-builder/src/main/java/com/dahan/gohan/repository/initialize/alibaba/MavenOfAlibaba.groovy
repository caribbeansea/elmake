package com.dahan.gohan.repository.initialize.alibaba

import com.dahan.gohan.repository.Repository

/*
 * Creates on 2020/12/3.
 */

/**
 * 阿里代理的maven仓库地址
 * @author kevin
 */
class MavenOfAlibaba extends Repository {

    MavenOfAlibaba() {
        super("alibaba-public", "https://maven.aliyun.com/repository/public/")
    }

}
