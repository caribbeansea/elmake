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
 * Creates on 2020/6/18.
 */

import com.dahan.elmake.collect.Lists;
import com.dahan.elmake.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiansheng
 */
public class Matches {

    /**
     * 正则表达式匹配字符串，并返回结果
     *
     * @param s 源字符串
     * @param r 正则表达式, 如果是Java的情况下请传入字符串, groovy则传入
     *          两个斜杠组成的正则表达式语句，例子匹配井号后面的字符：/#(.*)/
     * @return 匹配结果数组，如果没有匹配到结果则返回空数组(不是空对象)
     */
    public static String[] find(String s, String r) {
        return find(s, r, null);
    }

    /**
     * 正则表达式匹配字符串，并返回结果
     *
     * @param s       源字符串
     * @param r       正则表达式, 如果是Java的情况下请传入字符串, groovy则传入
     *                两个斜杠组成的正则表达式语句，例子匹配井号后面的字符：/#(.*)/
     * @param closure 闭包，处理字符串内容。如果匹配结果需要另外的处理的话则创建一个{@link Consumer}对象
     *                并实现{@link Consumer#accept}方法，进行处理
     * @return 匹配结果数组，如果没有匹配到结果则返回空数组(不是空对象)
     */
    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    public static String[] find(String s, String r, Callback<String> closure) {
        Pattern par = Pattern.compile(r);
        Matcher matcher = par.matcher(s);
        List<String> groups = Lists.newArrayList();
        while (matcher.find()) {
            String value = matcher.group(1);
            if (closure == null) {
                groups.add(value);
            } else {
                groups.add(closure.accept(value));
            }
        }
        return groups.toArray(new String[groups.size()]);
    }

    public static String findAndReplace(String s, String r, Callback<String> closure) {
        Result result = new Result(s);
        find(s, r, o -> {
            String value = closure.accept(o);
            result.sourceString = result.sourceString.replaceAll("\\$\\{".concat(o).concat("}"), value);
            return null;
        });
        return result.sourceString;
    }

    private static class Result {
        public String sourceString;

        public Result(String sourceString) {
            this.sourceString = sourceString;
        }

    }

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "张三");
        map.put("age", "18");
        String s = findAndReplace("aa ${name} ${age} xx", "\\$\\{(.*?)}", map::get);
        System.out.println(s);
    }

}
