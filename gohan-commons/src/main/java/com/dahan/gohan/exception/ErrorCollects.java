package com.dahan.gohan.exception;

/*
 * Creates on 2020/12/1.
 */

import java.util.Map;
import java.util.Stack;

/**
 * 错误收集器
 *
 * @author kevin
 */
public abstract class ErrorCollects<E extends GohanException> {

    /**
     * 异常栈
     */
    protected final Stack<E> stack = new Stack<>();

    /**
     * 添加一个异常信息
     */
    public void push(E ex) {
        stack.push(ex);
    }

    /**
     * 弹出栈顶的异常
     */
    public E pop() {
        return stack.pop();
    }

    /**
     * 将收集的异常信息打印出去
     */
    public void rollout() {
        for (E e : stack) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 将收集的异常信息打印出去
     *
     * @param values 配置信息
     */
    public void rollout(String... values) {
        rollout();
    }

}
