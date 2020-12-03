package com.dahan.gohan.http;

/*
 * Creates on 2019/11/13.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahan.gohan.Assert;
import com.dahan.gohan.FastJsonHelper;
import com.dahan.gohan.MultiLanguage;
import com.dahan.gohan.collect.Maps;
import okhttp3.*;
import org.apache.tools.ant.taskdefs.email.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * 执行http请求
 *
 * @author kevin
 */
public class Areyouok {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(Areyouok.class);

    /**
     * 执行GET请求
     *
     * @param url url
     * @return 请求结果
     */
    public static JSONObject doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * 执行GET请求
     *
     * @param url    url
     * @param params 参数列表
     * @return 请求结果
     */
    public static JSONObject doGet(String url, Map<String, Object> params) {
        return doGet(url, null, null);
    }

    /**
     * 执行异步GET请求
     *
     * @param url url
     * @return 请求结果
     */
    public static JSONObject doGet(String url, Callback callback) {
        return doGet(url, null, null, callback);
    }

    /**
     * 执行异步GET请求
     *
     * @param url    url
     * @param params 参数列表
     * @return 请求结果
     */
    public static JSONObject doGet(String url, Map<String, Object> params, Callback callback) {
        return doGet(url, null, null, callback);
    }

    /**
     * 执行GET请求
     *
     * @param url    url
     * @param params 参数列表
     * @param header 请求头
     * @return 请求结果
     */
    public static JSONObject doGet(String url, Map<String, Object> params, Map<String, String> header, Callback callback) {
        return execute(url, params, header, HttpMethod.GET, null, callback);
    }

    /**
     * 执行POST请求
     *
     * @param url url
     * @return 请求结果
     */
    public static JSONObject doPost(String url) {
        return doPost(url, null, null, null, null);
    }

    /**
     * 执行POST请求
     *
     * @param url    url
     * @param params 参数列表
     * @return 请求结果
     */
    public static JSONObject doPost(String url, Map<String, Object> params) {
        return doPost(url, params, null, null, null);
    }

    /**
     * 执行异步POST请求
     *
     * @param url url
     * @return 请求结果
     */
    public static JSONObject doPost(String url, Callback callback) {
        return doPost(url, null, null, null, callback);
    }

    /**
     * 执行异步POST请求
     *
     * @param url    url
     * @param params 参数列表
     * @return 请求结果
     */
    public static JSONObject doPost(String url, Map<String, Object> params, Callback callback) {
        return doPost(url, params, null, null, callback);
    }

    /**
     * 执行POST请求
     *
     * @param url    url
     * @param params 参数列表
     * @param header 请求头
     * @return 请求结果
     */
    public static JSONObject doPost(String url, Map<String, Object> params,
                                    Map<String, String> header,
                                    HttpMediaType mediatype,
                                    Callback callback) {
        return execute(url, params, header, HttpMethod.POST, mediatype, callback);
    }

    /**
     * 创建请求头Map对象，并添加token
     *
     * @param token token
     * @return 请求头Map对象
     */
    public static Map<String, String> newHeaderAddToken(String token) {
        return Maps.newHashMap("Authorization", token);
    }

    /**
     * 执行方法
     *
     * @param url       url
     * @param params    参数（可空）
     * @param header    请求头（可空）
     * @param method    请求格式
     * @param mediatype 数据提交方式
     * @param callback  异步回调（如果需要同步执行传NULL即可）
     * @return 执行结果
     */
    static JSONObject execute(String url, Map<String, Object> params,
                              Map<String, String> header,
                              HttpMethod method,
                              HttpMediaType mediatype,
                              Callback callback) {
        Response response = null;
        JSONObject jsonvalue = null;
        Request request = build(url, params, header, method, mediatype);
        try {
            if (callback != null) {
                mOkHttpClient.newCall(request).enqueue(callback);
                return null;
            } else {
                response = mOkHttpClient.newCall(request).execute();
            }
            String resultString = Assert.requiredNonNull(response.body()).string();
            jsonvalue = FastJsonHelper.toJSONObject(JSON.toJSON(resultString));
            Assert.requiredNonNull(response.body()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonvalue;
    }

    /**
     * 构建Request请求对象
     *
     * @param url        url
     * @param parameters 参数（可空）
     * @param header     请求头（可空）
     * @param method     请求格式
     * @return Request请求对象
     */
    static Request build(String url, Map<String, Object> parameters,
                         Map<String, String> header,
                         HttpMethod method,
                         HttpMediaType mediatype) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        // 如果存在header
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        // 设置请求格式
        switch (method) {
            case GET:
                builder.url(parseUrl(url, parameters));
                break;
            case POST:
                RequestBody body = null;
                if (mediatype == null) {
                    FormBody.Builder formbody = new FormBody.Builder();
                    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                        formbody.add(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                    body = formbody.build();
                } else if (mediatype == HttpMediaType.JSON) {
                    body = RequestBody.create(MediaType.parse(HttpMediaType.JSON.getValue()),
                            JSONObject.toJSONString(parameters));
                }
                builder.post(body);
                break;
        }
        return builder.build();
    }

    /**
     * 构建携带参数的URL
     *
     * @param url    URL链接
     * @param params 参数
     * @return 构建好的URL
     */
    public static String parseUrl(String url, Map<String, Object> params) {
        int i = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (i == 0) {
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(param.getKey());
                builder.append("=");
                builder.append((param.getValue() == null ? "" : param.getValue().toString()));
                i++;
            }
        }
        return builder.toString();
    }

    /**
     * 文件下载
     *
     * @param url       下载地址
     * @param directory 存储目录
     * @param filename  文件名
     */
    public static void download(String url, String directory, String filename) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error(MultiLanguage.INFO_DOWNLOAD_FAILURE + url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    writeFile(response, directory, filename);
                    logger.info(MultiLanguage.INFO_DOWNLOAD_SUCCESS + url);
                }
            }
        });
    }

    private static void writeFile(Response response, String directory, String filename) {
        FileOutputStream fos = null;
        InputStream is = Objects.requireNonNull(response.body()).byteStream();
        File file = new File(directory, filename);
        try {
            fos = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
