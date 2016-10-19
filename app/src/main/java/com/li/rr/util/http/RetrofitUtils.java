package com.li.rr.util.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LMW on 2016/6/7.
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private RetrofitUtils() {
    }

    public static RetrofitUtils getInstance() {
        if (null == retrofitUtils) {
            synchronized (RetrofitUtils.class) {
                if (null == retrofitUtils)
                    retrofitUtils = new RetrofitUtils();
            }
        }
        return retrofitUtils;
    }

    private static Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private OkHttpClient getOkHttpInstance() {
        if (null == okHttpClient) {
            synchronized (OkHttpClient.class) {
                if (null == okHttpClient) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

    public Retrofit getRetrofit(String baseURL) {
        if (null == retrofit) {
            synchronized (Retrofit.class) {
                if (null == retrofit) {
                    //构建Retrofit
                    retrofit = new Retrofit.Builder()
                            .client(getOkHttpInstance())
                            //配置服务器路径
                            .baseUrl(baseURL)
                            //配置转化库，默认是Gson
                            .addConverterFactory(GsonConverterFactory.create())
                            //配置回调库，采用RxJava
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            //设置OKHttpClient为网络客户端
                            .build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 得到service对象
     * @param baseURL
     * @param <T>
     * @return
     */
    public <T> T retrofitCtreate(String baseURL,Class<T> clzz) {
        return  getRetrofit(baseURL).create(clzz);
    }

}
