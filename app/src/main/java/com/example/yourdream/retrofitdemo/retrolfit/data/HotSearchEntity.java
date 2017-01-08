package com.example.yourdream.retrofitdemo.retrolfit.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 测试数据，服务端给的我数据为
 * {
 "result": 0,
 "msg": {},
 "method": "icy.getHotSearch",
 "data": {
 "placeHolder": "亲肤毛衣",
 "hotSearch": [{
         "keyword": "毛衣",
         "link": ""
         }, {
         "keyword": "衬衣",
         "link": ""
         }, {
         "keyword": "呢大衣",
         "link": ""
         }, {
         "keyword": "羽绒服",
         "link": ""
         }, {
         "keyword": "连衣裙",
         "link": ""
         }, {
         "keyword": "阔腿裤",
         "link": ""
         }]
         }
 }
 * Created by Administrator on 2017/1/7.
 */
public class HotSearchEntity {
    @SerializedName("placeHolder")
    private String placeHolder;

    @SerializedName("hotSearch")
    private List<HotSearch> hotSearch;

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public List<HotSearch> getList() {
        return hotSearch;
    }

    public void setList(List<HotSearch> list) {
        this.hotSearch = list;
    }
}
