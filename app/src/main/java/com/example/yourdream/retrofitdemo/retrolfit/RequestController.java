package com.example.yourdream.retrofitdemo.retrolfit;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.text.TextUtils;

import com.example.yourdream.retrofitdemo.retrolfit.data.MrResponse;
import com.example.yourdream.retrofitdemo.retrolfit.httprequestapi.HttpApi;
import com.example.yourdream.retrofitdemo.retrolfit.netexption.NetException;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/7.
 * 请求控制器类，主要返回的是一个Observable<T>泛型对象，即被观察者对象
 */
public class RequestController {

    public static final int STATUS_CODE_SUCCESS = 0;
    public static final int ERROR_CODE_CAPTCHA_CODE_REG = 7906;
    private static HttpApi HTTPSApi;  //HTTPS请求
    private static Gson gson;
    private static String API_Url = "http://192.168.0.102:8080/";

    /**
     * 登录注册接口
     * @param userId
     * @param pwd
     * @param tClass
     * @param <T> 需要解析的类型,是由GSON解析转换后得到的数据对象
     * @return
     */
    public static <T> Observable<T> Login(String userId,String pwd, Class<T> tClass) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("pwd", pwd);

        return defaultPost(params, "login", tClass);
    }



    /**
     * 调用 default 请求
     *
     * @param params     传入的参数
     * @param methodName 方法名
     * @param tClass     需要解析的类型
     * @return
     */
    public static <T> Observable<T> defaultPost(Map<String, Object> params, String methodName, final Class<T> tClass) {
        return retrofitPost(params, methodName, tClass);
    }



    /**
     * retrolfit网络请求接口
     *
     * @param params     传入的参数
     * @param methodName 方法名
     * @param tClass     需要解析的类型
     *
     * @return
     */
    public static <T> Observable<T> retrofitPost(Map<String, Object> params, String methodName, final Class<T> tClass) {
        return HttpUtils.initParams(params, methodName)
                .flatMap(new Func1<Map<String, Object>, Observable<MrResponse>>() {
                    @Override
                    public Observable<MrResponse> call(Map<String, Object> stringStringMap) {
                        return getRequestObservable(stringStringMap);
                    }
                })
                .flatMap(new Func1<MrResponse, Observable<T>>() {
                    @Override
                    public Observable<T> call(MrResponse response) {
                        if (response.getResult() == STATUS_CODE_SUCCESS) {
                            String json = getGson().toJson(response.getData());
                            System.out.println("76---retrofitPost：json---------------->"+json);
                            if(TextUtils.isEmpty(json)) {
                                return null;
                            }
                            T data = getGson().fromJson(json, tClass);
                            return Observable.just(data);
                        } else {
                            return Observable.error(new NetException(response));
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("90----------------retrofitPost--throwable => " + throwable);
                    }
                });
    }


    /**
     * 根据方法名和请求类型,确定网络请求类型，并返回对应的observable
     *
     * @param params 请求参数
     * @return
     */
    public static Observable<MrResponse> getRequestObservable(Map<String, Object> params) {
        HttpApi api = getHTTPSApi();
        /*if (LogUtils.DEBUG) {
            return api.defaultGET(params).subscribeOn(Schedulers.io());
        } else {
            return api.defaultPost(params).subscribeOn(Schedulers.io());
        }
        */
        return api.defaultPost(params).subscribeOn(Schedulers.io());
    }


    /**
     * 初始化Retrolfit网络请求类
     * @return
     */
    public static HttpApi getHTTPSApi() {
        if (HTTPSApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //.client(okHttpClient()) 这一句暂时省略
                    .build();
            HTTPSApi = retrofit.create(HttpApi.class);
        }
        return HTTPSApi;
    }

    /**
     * 创建OKHttpClient
     *
     * @return
     */
    /*private OkHttpClient okHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(new HeadInterceptor()).build();
    }*/


    /**
     * 实例一个gson对象
     * @return
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }




}
