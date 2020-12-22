package com.dahan.elmake;

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
 * Creates on 2020/9/12.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 和文件操作相关的工具类
 *
 * @author tiansheng
 */
public class Files {

    /**
     * 获取文件后缀名
     *
     * @param file 文件路径或者是文件名
     * @return 文件后缀名
     */
    public static String getExtension(String file) {
        String name = new File(Assert.requiredNonNull(file)).getName();
        int indexOf = name.lastIndexOf('.');
        return name.substring(indexOf + 1);
    }

    public static String readString(File file) throws IOException
    {
        return readString(file.getPath());
    }

    /**
     * 将文件读取到String字符串中
     *
     * @param path 文件路径
     * @return 文件内容
     */
     public static String readString(String path) throws IOException
     {
         return java.nio.file.Files.readString(Paths.get(path), Charsets.UTF_8);
     }

    /**
     * 如果目录不存在就去创建
     */
    public static void mkdirsNotExist(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}