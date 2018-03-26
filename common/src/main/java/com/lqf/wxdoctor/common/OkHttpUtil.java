package com.lqf.wxdoctor.common;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final OkHttpClient okHttpClient = initOkHttp();

    public static OkHttpClient initOkHttp()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    public static String get(String url, Map<String, Object> dataMap) throws IOException
    {
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.lastIndexOf("&"));
        }
        Request request = new Request.Builder()
                .url(s)
                .get()
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw e;
        }
    }

    public static String post(String url, Map<String, Object> dataMap) throws IOException
    {
        FormBody.Builder body = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw e;
        }
    }

}
