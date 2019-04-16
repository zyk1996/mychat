package com.itiknow.mychat.entity.other;

import lombok.Data;

@Data
public class Info {
    private String message;
    //跳转url
    private String url;
    //页面跳转提示时间 单位秒
    private Integer time;
    public Info(){}
    public Info(String message,String url,Integer time){
        this.message=message;
        this.url=url;
        this.time=time;
    }

}
