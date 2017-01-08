package com.example.yourdream.retrofitdemo.retrolfit;

import java.util.Map;

import rx.Observable;

/**
 * 网络请求工具类
 * Created by Administrator on 2017/1/7.
 */
public class HttpUtils {

    /**
     * 处理params：这里处理网络请求参数
     *
     * @param params
     * @return
     */
    public static Observable<Map<String, Object>> initParams(Map<String, Object> params, String methodName) {
        return Observable.just(params);
    }
}
