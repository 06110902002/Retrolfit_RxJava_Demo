package com.example.yourdream.retrofitdemo.retrolfit.data;

import com.google.gson.annotations.SerializedName;

/**
 * 响应信息实体类
 * Created by Administrator on 2017/1/7.
 */
public class MsgEntity {

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

