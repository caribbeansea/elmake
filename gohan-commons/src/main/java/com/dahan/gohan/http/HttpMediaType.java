package com.dahan.gohan.http;

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

/**
 * 字符编码统一管理
 *
 * @author kevin
 */
public enum HttpMediaType {

  JSON {
    public String getValue() {
      return "application/json; charset=utf-8";
    }
  },

  FORMAT {
    public String getValue() {
      return "application/x-www-form-urlencoded;charset=UTF-8";
    }
  },

  TEXT {
    public String getValue() {
      return "application/text;charset=UTF-8";
    }
  },

  IMAGE {
    @Override
    public String getValue() {
      return "image/png";
    }
  },

  STREAM {
    @Override
    public String getValue() {
      return "application/octet-stream";
    }
  },

  FORCE_DOWNLOAD {
    @Override
    public String getValue() {
      return "application/force-download";
    }
  },

  FORM {
    @Override
    public String getValue() {
      return null;
    }
  },

  ;

  public abstract String getValue();

}