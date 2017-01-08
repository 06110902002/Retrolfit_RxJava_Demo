package com.example.yourdream.retrofitdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourdream.retrofitdemo.retrolfit.RetrolfitActivity;

import data.HttpResult;
import data.UserInfo;
import downupload.DownloadActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upload.UploadActivity;
import utils.SubscribeUtils;


public class MainActivity extends Activity {

    private TextView normaltxt;
    private TextView rxjavatxt;

    private Button normalBtn;
    private Button rxjava;
    private Button retrolfit;

    private Subscription tmpSubscription;        //取消订阅用的

    private static final String BASE_URL = "http://192.168.0.102:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        normaltxt = (TextView)findViewById(R.id.txt);
        rxjavatxt = (TextView)findViewById(R.id.rxjava_txt);

        normalBtn = (Button)findViewById(R.id.norml);
        rxjava = (Button)findViewById(R.id.rxjava);
        retrolfit= (Button)findViewById(R.id.retrolfit_utils);

        Button down = (Button)findViewById(R.id.download);
        Button up = (Button)findViewById(R.id.upload);


        EventClick click = new EventClick();
        normalBtn.setOnClickListener(click);
        rxjava.setOnClickListener(click);
        retrolfit.setOnClickListener(click);
        retrolfit.setOnClickListener(click);

        down.setOnClickListener(click);
        up.setOnClickListener(click);


    }

    private class EventClick implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.norml:
                    requestNormal();
                    break;
                case R.id.rxjava:
                    requestByRxjava();
                    break;

                case R.id.download:
                    Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                    startActivity(intent);
                    break;

                case R.id.upload:
                    Intent intent2 = new Intent(MainActivity.this, UploadActivity.class);
                    startActivity(intent2);
                    break;

                case R.id.retrolfit_utils:
                    Intent intent3 = new Intent(MainActivity.this, RetrolfitActivity.class);
                    startActivity(intent3);
                    break;


            }
        }
    }

    /**
     * Retrofit普通 请求
     */
    private void requestNormal()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        HttpRequest service = retrofit.create(HttpRequest.class);

        Call<UserInfo> call = service.login("liu", "123");

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                //请求成功操作
                normaltxt.setText("82---"+response.body().getUsername());
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                //请求失败操作
                normaltxt.setText("87--"+t.getMessage());
            }
        });
    }

    /**
     * Retrofit支持rxjava的网络请求
     */
    private void requestByRxjava()
    {
        //测试一
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HttpRequest service = retrofit.create(HttpRequest.class);

        service.loginByRxjava("liuhulai", "123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(UserInfo movieEntity) {
                        rxjavatxt.setText("124----"+movieEntity.getUsername());
                    }
                });*/

        //测试二，返回的数据结果为泛型类
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        HttpRequest service = retrofit.create(HttpRequest.class);

        tmpSubscription = service.loginByRxjava2("liuhulai", "123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<UserInfo>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(HttpResult<UserInfo> userInfoHttpResult) {
                        HttpResult<UserInfo> result = userInfoHttpResult;
                        rxjavatxt.setText("124----"+ result.getResultMessage() +"\n"+result.getData().getUsername());

                    }
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SubscribeUtils.unSubscribe(tmpSubscription);  //取消订阅请求用的
    }
}
