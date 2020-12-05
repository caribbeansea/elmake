package com.dahan.gohan.repository.initialize.alibaba;

import com.dahan.gohan.repository.Repository;

/**
 * 阿里代理的maven仓库地址
 *
 * @author kevin
 */
public class MavenOfAlibaba extends Repository
{
    public MavenOfAlibaba()
    {
        super("alibaba-public", "https://maven.aliyun.com/repository/public/");
    }
}
