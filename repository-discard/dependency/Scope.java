package com.dahan.eimoto.repository.dependency;

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
 * Creates on 2020/12/3.
 */

/**
 * 依赖存在范围
 *
 * @author tiansheng
 */
public enum Scope
{
    COMPILE,

    /**
     * 只在测试时使用，打包并不会将scope为test的依赖打包到classpath下。
     */
    TEST,

    /**
     * 系统依赖，该依赖是指本地存在的依赖信息。且会打包到classpath下去供运行
     * 时使用。
     */
    LOCAL,

    /**
     * EXLOCAL和LOCAL范围效果一致，但是EXLOCAL不会将本地依赖打包到classpath下。
     */
    EXLOCAL,

    PROVIDED,

}
