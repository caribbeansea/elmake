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
        collects.push(new DependencyNotObtained(new Dependency("org.apache.maven.shared", "maven-shared-utils", "3.2.1"), "cannot download form repository"));
        collects.push(new DependencyNotObtained(new Dependency("org.fusesource.jansi", "jansi", "1.18"), "cannot download form repository"));
        collects.push(new DependencyNotObtained(new Dependency("org.slf4j", "slf4j-api", "1.2.1"), "cannot download form repository"));
        collects.push(new DependencyNotObtained(new Dependency("ch.qos.logback", "logback-classic", "1.2.1"), "cannot download form repository"));
        collects.push(new DependencyNotObtained(new Dependency("org.apache.maven.shared", "maven-shared-utils", "3.2.1"), "cannot download form repository"));
        collects.rollout("构建依赖集合不能解决的依赖有如下: ");
    }

}
