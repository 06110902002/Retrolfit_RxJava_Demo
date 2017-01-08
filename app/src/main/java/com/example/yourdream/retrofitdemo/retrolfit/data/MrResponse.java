package com.example.yourdream.retrofitdemo.retrolfit.data;

import com.google.gson.annotations.SerializedName;

/**
 * 这个返回类型的数据与服务端一致 ，
 * 服务端给的数据格式为：将前面的封装出来
 * {
     "result": 0,
     "msg": {},
     "method": "icy.getHotSearch",
     "data": {}
  }
 * Created by Administrator on 2017/1/7.
 */
public class MrResponse {

    /**
     * 响应结果
     */
    @SerializedName("result")
    private int result;

    /**
     * 响应信息
     */
    @SerializedName("msg")
    private MsgEntity msg;

    /**
     * 请求方法
     */
    @SerializedName("method")
    private String method;

    /**
     * 响应内容
     */
    @SerializedName("data")
    private Object data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

