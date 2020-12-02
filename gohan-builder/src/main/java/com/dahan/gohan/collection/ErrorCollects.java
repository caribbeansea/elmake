package com.dahan.gohan.collection;

/*
 * Creates on 2020/12/1.
 */

import com.dahan.gohan.collection.exception.DependencyNotObtained;
import com.dahan.gohan.collection.exception.SuperException;
import com.dahan.gohan.repository.Dependency;

import java.util.Stack;

/**
 * 错误收集器
 *
 * @author kevin
 */
public class ErrorCollects {

    /**
     * 异常栈
     */
    private static final Stack<SuperException> exceptionStack = new Stack<>();

    /**
     * 抛出依赖未找到异常，并添加异常信息
     */
    public static void pushDependencyNotObtained(Dependency dependency, String message) {
        exceptionStack.add(new DependencyNotObtained(dependency, message));
    }

    /**
     * 将收集的异常信息打印出去
     */
    public static void rolloutException() {
        throw exceptionStack.pop();
    }

    /**
     * 将收集的异常信息打印出去
     *
     * @param title 设置异常标题
     */
    public static void rolloutException(String title) {
        System.out.println(title);
        for (SuperException superException : exceptionStack) {
            System.out.println("    ".concat(superException.getMessage()));
        }
    }

    public static void main(String[] args) {
        pushDependencyNotObtained(new Dependency("org.apache.maven.shared", "maven-shared-utils", "3.2.1"), "不能从以下仓库下载");
        pushDependencyNotObtained(new Dependency("org.fusesource.jansi", "jansi", "1.18"), "不能从以下仓库下载");
        pushDependencyNotObtained(new Dependency("org.slf4j", "slf4j-api", "1.2.1"), "不能从以下仓库下载");
        pushDependencyNotObtained(new Dependency("ch.qos.logback", "logback-classic", "1.2.1"), "不能从以下仓库下载");
        pushDependencyNotObtained(new Dependency("org.apache.maven.shared", "maven-shared-utils", "3.2.1"), "不能从以下仓库下载");
        rolloutException("构建依赖集合不能解决的依赖有如下: ");
    }

}
