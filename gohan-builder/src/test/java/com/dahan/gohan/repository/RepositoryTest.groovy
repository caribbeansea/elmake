package com.dahan.gohan.repository

import com.dahan.gohan.repository.initialize.alibaba.MavenOfAlibaba
import org.junit.Test

/*
 * Creates on 2020/12/4.
 */

/**
 * @author kevin
 */
class RepositoryTest
{

    @Test
    void importDependency()
    {
        def alibaba = new MavenOfAlibaba()
        alibaba.getDependency("com.alibaba", "fastjson", "1.2.66")
    }

}
