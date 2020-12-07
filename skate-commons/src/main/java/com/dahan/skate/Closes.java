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
 * Creates on 2020/5/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author kevin
 */
public class Closes {

    private static final Logger mLog = LoggerFactory.getLogger(Closes.class);

    public static void doClose(Closeable... closeables) {
        try {
            if (closeables == null) return;
            for (Closeable closeable : closeables) {
                if (closeable == null) continue;
                closeable.close();
            }
        } catch (IOException e) {
            mLog.error("close failure.", e);
        }
    }

}