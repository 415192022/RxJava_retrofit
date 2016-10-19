package com.li.rr.minterface;

import com.li.rr.mvp.bean.netbean.MovieModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/29.
 */
public interface INetServie <T>{
    @GET("top250")
    Observable<MovieModel> getMovieModel(@Query("start") int start, @Query("count") int count);
    @GET("celebrity/large/17525.jpg")
    Observable getImage();
//    Call<MovieModel> getMovieModel(@Query("start") int start, @Query("count") int count);
}
