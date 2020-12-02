package com.dahan.gohan;

/*
 * Creates on 2020/12/2.
 */

import com.dahan.gohan.collect.Lists;

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

    public Formprint() {
    }

    public Formprint(String... titles) {
        titleArray.addAll(Lists.of(titles));
    }

    /**
     * 设置表格内容
     */
    public void setFormContent(String... content) {
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
    public void printf() {
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
                finalform.append(StringUtils.getSpaceOfSize(concat.length(), "#").concat("\n"));
            } else {
                finalform.append(StringUtils.getSpaceOfSize(concat.length(), "-").concat("\n"));
            }
            finalform.append(concat.concat("\n"));
        }
        System.out.println(finalform.toString());
    }

    /**
     * 一格
     */
    static class Row {

        private String value;

        Row() {
        }

        Row(String value) {
            this.value = value;
        }

        String getValue(int longest) {
            return value + StringUtils.getSpaceOfSize(longest - value.length());
        }
    }

}
