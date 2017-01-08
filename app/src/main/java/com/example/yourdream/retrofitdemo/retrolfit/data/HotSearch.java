package com.example.yourdream.retrofitdemo.retrolfit.data;

import com.google.gson.annotations.SerializedName;

/**
 * 测试用的数据
 * 数据格式具体可参看：HitSeratchEntity
 * Created by Administrator on 2017/1/7.
 */
public class HotSearch {

    @SerializedName("keyword")
    private String keyword;

    @SerializedName("link")
    private String link;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
