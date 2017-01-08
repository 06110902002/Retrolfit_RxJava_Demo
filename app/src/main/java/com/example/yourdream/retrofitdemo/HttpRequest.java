package com.example.yourdream.retrofitdemo;


import data.HttpResult;
import data.UserInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yourdream on 2016/11/18.
 */

public interface HttpRequest {

    /**
     * 普通的Retrolfit请求,返回的是一个Call
     * @param username
     * @param password
     * @return
     */
    @POST("RetrolfitService/index.jsp" )
    Call<UserInfo> login(@Query("username") String username, @Query("password") String password);


    /**
     * 支持rxjava，返回的是一个Observable,被观察对象
     *
     * @param username
     * @param password
     * @return
     */
    @GET("RetrolfitService/index.jsp")
    Observable<UserInfo> loginByRxjava(@Query("username") String username, @Query("password") String password);

    /**
     * 支持rxjava，返回的是一个Observable,被观察对象，其中数据用泛型封装
     * @param username
     * @param password
     * @return
     */
    @GET("RetrolfitService/index.jsp")
    Observable<HttpResult<UserInfo>> loginByRxjava2(@Query("username") String username, @Query("password") String password);
}
