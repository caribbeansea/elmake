package com.dahan.gohan

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
 * @author tiansheng
 */
class Langs
{

    static String INFO_DOWNLOAD_SUCCESS(String... values)
    {
        return StringUtils.format("[SUCCESS] - 依赖【{}】下载成功，从【{}】仓库。来自【{}】依赖引用。从【{}】", values)
    }

    static String INFO_DOWNLOAD_FAILURE(String... values)
    {
        return StringUtils.format("[FAILURE] - 依赖【{}】下载失败，从【{}】。来自【{}】依赖引用。", values)
    }

    static String ERROR_DEPENDENCY_DOWNLOAD_FAILURE()
    {
        return "依赖下载是失败"
    }

    static String ERROR_DEPENDENCY_CANNOT_IMPORT()
    {
        return "下载失败，请检查依赖坐标是否正确"
    }

}
