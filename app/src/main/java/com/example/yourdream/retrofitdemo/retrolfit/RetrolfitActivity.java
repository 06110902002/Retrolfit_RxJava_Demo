package com.example.yourdream.retrofitdemo.retrolfit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yourdream.retrofitdemo.R;
import com.example.yourdream.retrofitdemo.retrolfit.data.HotSearchEntity;

import data.HttpResult;
import data.UserInfo;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Administrator on 2017/1/7.
 */

public class RetrolfitActivity extends Activity {

    private Button normal;
    private Button gson;
    private Subscription subscription;  //retrolfit订阅主题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_retrolfit);

        init();
    }

    private void init(){
        normal = (Button)findViewById(R.id.retrun_normal);
        gson = (Button)findViewById(R.id.retrun_gson);

        EventClick onClick = new EventClick();
        normal.setOnClickListener(onClick);
    }

    private class EventClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.retrun_normal:
                    String userId = "liuhulai2017";
                    String pwd = "123456";
                    login(userId,pwd);
                    break;

                case R.id.retrun_gson:
                    break;
            }
        }
    }

    private void login(String userId,String pwd){

        subscription = RequestController.Login(userId,pwd,HotSearchEntity.class)
                .subscribe(new Subscriber<HotSearchEntity>(){

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HotSearchEntity hotSearchEntity) {
                        System.out.println("71----------placeholder"+hotSearchEntity.getPlaceHolder());
                        System.out.println("76----------List<HotSearch>"+hotSearchEntity.getList());
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
