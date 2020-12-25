package com.dahan.elmake.dsl;

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
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
 * Creates on 2020/12/21.
 */

/**
 * 闭包标签支持，比如<code>
 *     includes {
 *         include("com.alibaba:fastjson:2.6.66")
 *     }
 * </code>
 *
 * @author tiansheng
 */
public interface TagFuncApi
{

    /**
     * <code>includes</code>函数块，在includes中使用{@link SpecificApi#include},
     * 引用的包就会存在<code>依赖传递</code>的特性。
     *
     * 即子模块也能使用父模块中定义的依赖包，如果想让子模块可选引用，可以使用{@link #includeManager(MakeFunction)}。
     */
    void includes(MakeFunction makeFunction);

    /**
     * <code>includeManager</code>, 如果你使用过Maven那你一定知道maven中的 {@code dependencyManager} 标签。
     *
     * includeManager的作用与Maven一致，就是做依赖管理的工作，让项目所有依赖统一在父模块中进行版本的管理。
     * 子模块可选引用。
     */
    // void includeManager(MakeFunction makeFunction);

}
