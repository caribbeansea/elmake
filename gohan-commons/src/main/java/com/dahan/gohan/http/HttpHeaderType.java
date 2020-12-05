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
 * Creates on 2020/5/14.
 */

/**
 * @author kevin
 */
public enum HttpHeaderType {

  /**
   * 配置下载文件名
   */
  CONTENT_DISPOSITION {
    @Override
    public String getKey() {
      return "Content-Disposition";
    }

    @Override
    public String getValue() {
      return null;
    }

    @Override
    public String getValue(String param) {
      return "attachment;fileName=".concat(param);
    }
  };

  public abstract String getKey();
  public abstract String getValue();
  public abstract String getValue(String param);

}