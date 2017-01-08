package com.example.yourdream.retrofitdemo.retrolfit.httprequestapi;

import com.example.yourdream.retrofitdemo.retrolfit.data.MrResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * retrolfit 网络请求接口类
 * Created by Administrator on 2017/1/7.
 */
public interface HttpApi {

    /**
     * 请求默认接口用的
     * @param options
     * @return
     */
    @FormUrlEncoded
    @POST("RetrolfitService/index.jsp" )
    Observable<MrResponse> defaultPost(@FieldMap Map<String, Object> options);

    /**
     * 请求默认接口用的
     * @param options
     * @return
     */
    @GET("RetrolfitService/index.jsp")
    Observable<MrResponse> defaultGET(@QueryMap Map<String, Object> options);
}
