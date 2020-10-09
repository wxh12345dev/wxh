package com.wxh.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
 
/**
 * 利用okhttp进行get和post的访问
 * 
 * @author cp
 *
 */
public class HttpUtils {
 
    /**
     * get请求
     * @param url
     * @return
     * @throws IOException 
     */
    public static String httpGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
 
    /**
     * post请求
     * @param url
     * @param data  提交的参数为key=value&key1=value1的形式
     * @throws IOException 
     */
    public static String httpPost(String url, String data) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }
}