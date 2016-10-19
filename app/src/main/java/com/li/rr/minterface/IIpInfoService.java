package com.li.rr.minterface;

import com.li.rr.mvp.bean.netbean.IpInfoModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface IIpInfoService {
    @GET("getIpInfo.php")
    Observable<IpInfoModel> getIpInfo(@Query("ip") String ip);
}
