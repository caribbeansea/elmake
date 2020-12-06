package com.dahan.gohan.repository.initialize.alibaba;

import com.dahan.gohan.repository.Repository;

/**
 * 阿里代理的maven仓库地址
 *
 * @author kevin
 */
public class AlibabaCenter extends Repository
{
    public AlibabaCenter()
    {
        super("https://maven.aliyun.com/repository/public");
    }
}
