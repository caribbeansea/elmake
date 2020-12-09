package com.dahan.gohan;

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
 * Creates on 2020/5/18.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author kevin
 */
public class ShellExecute {

    /**
     * 调用linux下的.sh脚本文件
     */
    public static String callShell(String script) {
        return exec(script);
    }

    public static String exec(String... commands) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process pro = runtime.exec(commands);
            int status = pro.waitFor();
            if (status != 0) {
                System.out.println("Failed to call shell's command");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream(), Charsets.GBK));
            StringBuilder strbr = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                strbr.append(line).append("\n");
            }
            return strbr.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}