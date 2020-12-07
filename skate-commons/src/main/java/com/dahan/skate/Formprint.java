package com.dahan.skate;

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
 * Creates on 2020/12/2.
 */

import com.dahan.skate.collect.Lists;

import java.util.List;

/**
 * 表格输出
 *
 * @author kevin
 */
public class Formprint {

    /**
     * 表格标题
     */
    private final List<String> titleArray = Lists.newLinkedList();

    /**
     * 表格内容
     */
    private final List<List<String>> formcontent = Lists.newLinkedList();

    private int count = 1;

    private String spli = "";

    public Formprint() {
    }

    public Formprint(String... titles) {
        titleArray.addAll(Lists.of(titles));
    }

    /**
     * 设置表格内容
     */
    public void addFormContent(String... content) {
        formcontent.add(Lists.of(content));
        count++;
    }

    /**
     * 打印表格数据
     * ------------------------------------
     * |   title  |    key     |   value  |
     * |----------|------------|----------|
     * |          |            |          |
     * |----------|------------|----------|
     * |          |            |          |
     * |----------|------------|----------|
     * |          |            |          |
     * |----------|------------|----------|
     */
    public String printf() {
        // 最长的长度
        int[] longests = new int[titleArray.size()];
        List<List<Row>> rows = Lists.newLinkedList();
        for (int i = 0, len = titleArray.size(); i < len; i++) {
            List<Row> arow = Lists.newLinkedList();
            String title = titleArray.get(i);
            // 获取当前最大的数
            longests[i] = Math.max(longests[i], title.length());
            arow.add(new Row(title));
            for (List<String> contents : formcontent) {
                String content = contents.get(i);
                longests[i] = Math.max(longests[i], content.length());
                arow.add(new Row(content));
            }
            rows.add(arow);
        }

        // 构建行
        List<List<String>> singleValue = Lists.newLinkedList();
        for (int i = 0, len = rows.size(); i < len; i++) {
            int longest = longests[i];
            List<Row> row = rows.get(i);
            List<String> line = Lists.newLinkedList();
            for (Row value : row) {
                line.add(" ".concat(value.getValue(longest)).concat(" |"));
            }
            singleValue.add(line);
        }

        final StringBuilder finalform = new StringBuilder();

        final String start = "| ";
        for (int i = 0, len = count; i < len; i++) {
            StringBuilder builder = new StringBuilder();
            for (List<String> strings : singleValue) {
                builder.append(strings.get(i));
            }
            String concat = start.concat(builder.toString());
            if (i == 1) {
                finalform.append("|".concat(StringUtils.getSpaceOfSize(concat.length()-2, "+").concat("|\n")));
            } else {
                if(StringUtils.isEmpty(spli)) {
                    spli = StringUtils.getSpaceOfSize(concat.length(), "-").concat("\n");
                }
                finalform.append(spli);
            }
            finalform.append(concat.concat("\n"));
        }
        finalform.append(spli);
        return "\n".concat(finalform.toString());
    }

    /**
     * 一格
     */
    static class Row {

        private final String value;

        Row(String value) {
            this.value = value;
        }

        String getValue(int longest) {
            return value + StringUtils.getSpaceOfSize(longest - value.length());
        }
    }

}
