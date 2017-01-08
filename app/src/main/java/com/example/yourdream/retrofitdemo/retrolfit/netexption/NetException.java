package com.example.yourdream.retrofitdemo.retrolfit.netexption;

import com.example.yourdream.retrofitdemo.retrolfit.data.MrResponse;
import com.example.yourdream.retrofitdemo.retrolfit.data.MsgEntity;

/**
 * retrolfit网络请求异常类
 * Created by Administrator on 2017/1/7.
 */
public class NetException extends Exception {

    private static final long serialVersionUID = -20170107;

    private MrResponse response;       //服务器返回的错误信息

    public NetException() {
    }

    /**
     * 如果错误信息是空的,就new一个,防止外面报null point
     * @param response
     */
    public NetException(MrResponse response) {
        super(response.getMsg().getMessage());
        this.response = response;

        if (this.response.getMsg() == null) {
            this.response.setMsg(new MsgEntity());
        }
    }

    public MrResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\terror: " + response.getMsg().getError() + "\n\tmessage: " + response.getMsg().getMessage();
    }
}

