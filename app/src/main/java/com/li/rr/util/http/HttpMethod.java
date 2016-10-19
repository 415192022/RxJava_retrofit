package com.li.rr.util.http;

import android.os.Environment;

import com.li.rr.minterface.INetServie;
import com.li.rr.mvp.bean.netbean.MovieModel;
import com.li.rr.util.ApiConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/30.
 */
public class HttpMethod{
    private static final int DEFAULT_TIMEOUT=5;
    private Retrofit retrofit;
    private INetServie iNetServie;

    private static HttpMethod httpMethod;
    public static HttpMethod getInstance(){
        if (null == httpMethod){
            synchronized (HttpMethod.class){
                if (null == httpMethod){
                    httpMethod = new HttpMethod();
                }
            }
        }
        return httpMethod;
    }

    private HttpMethod(){
        OkHttpClient.Builder httpClientBuider=new OkHttpClient.Builder();
        httpClientBuider.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit=new Retrofit.Builder()
        .client(httpClientBuider.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(ApiConstants.Urls.DOUBAN_BASEURL)
        .build()
        ;
        iNetServie=retrofit.create(INetServie.class);
    }


    public void getMovie(Subscriber<MovieModel> subscriber , int start , int count){
        iNetServie.getMovieModel(start , count)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
        .subscribe(subscriber)
        ;
    }


    public File download(String strUrl){
        File file=null;
        try {
            URL url=new URL(strUrl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
             file =new File(Environment.getExternalStorageDirectory()+"/DOUBAN.jpg");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            byte[] bytes=new byte[4 * 1024];
            int lenth;
            while ((lenth = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,lenth);
            }
            fileOutputStream.flush();
            inputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
