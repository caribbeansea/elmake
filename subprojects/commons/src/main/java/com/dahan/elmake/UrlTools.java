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
 * Creates on 2020/5/11.
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author tiansheng
 */
public class UrlTools {

  public static String DEFAULT_FORMAT = Charsets.UTF_8.name();

  public static String encode(String input) {
    try {
      return URLEncoder.encode(input, DEFAULT_FORMAT);
    } catch (UnsupportedEncodingException e) {
      return input;
    }
  }

  public static String decode(String input) {
    try {
      return URLDecoder.decode(input, DEFAULT_FORMAT);
    } catch (UnsupportedEncodingException e) {
      return input;
    }
  }

}