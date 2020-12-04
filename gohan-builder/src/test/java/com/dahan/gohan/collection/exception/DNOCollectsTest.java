package com.dahan.gohan.collection.exception;

/*
 * Creates on 2020/12/3.
 */

import com.dahan.gohan.repository.dependency.Dependency;

/**
 * DependencyNotObtained异常收集器
 *
 * @author kevin
 */
public class DNOCollectsTest {

    public static void main(String[] args) {
        DNOCollects collects = new DNOCollects();
        collects.rollout("构建依赖集合不能解决的依赖有如下: ");
    }

}
