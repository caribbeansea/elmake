package com.dahan.eimoto.exception;

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/12/1.
 */

import java.util.Stack;

/**
 * 错误收集器
 *
 * @author tiansheng
 */
public abstract class ErrorCollects<E extends EimotoException> {

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
