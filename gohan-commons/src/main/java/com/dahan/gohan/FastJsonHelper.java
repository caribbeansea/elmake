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
 * Creates on 2020/5/11 19:47.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * 统一的json工具类，避免因fastjson序列化所导致的bug难以定位
 *
 * @author tiansheng
 */
public class FastJsonHelper
{

  /**
   * 对象转JsonString
   */
  public static String toJSONString(Object obj)
  {
    return JSON.toJSONString(obj,
            SerializerFeature.DisableCircularReferenceDetect, // 避免内存中循环引用导致解析错误
            SerializerFeature.WriteNullBooleanAsFalse,        // 如果遇到为null的boolean替换成false
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteMapNullValue
    );
  }

  /**
   * 将对象转换成JSONObject
   */
  public static JSONObject toJSONObject(Object obj)
  {
    return JSONObject.parseObject(obj instanceof String ? String.valueOf(obj) : toJSONString(obj));
  }

  /**
   * JSON字符串转对象
   */
  public static <T> T toObject(String json, Class<T> clazz)
  {
    try
    {
      return JSON.parseObject(json, clazz);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * JSON字符串转JavaList
   */
  public static <E> List<E> toList(String input, Class<E> clazz)
  {
    JSONArray array = JSONArray.parseArray(input);
    return array.toJavaList(clazz);
  }

  /**
   * 创建一个JSONObject对象实例
   *
   * @return JSONObject对象实例
   */
  public static JSONObject newJSONObject()
  {
    return new JSONObject();
  }

  /**
   * 创建一个JSONObject对象实例
   *
   * @return JSONObject对象实例
   */
  public static JSONObject newJSONObject(Map<String, Object> value)
  {
    return new JSONObject(value);
  }

}
