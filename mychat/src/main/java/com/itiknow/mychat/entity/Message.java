package com.itiknow.mychat.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private Long id;
    private String accountFrom;
    private String accountTo;
    private Long createtime;
    private String content;
    //是否为认证消息 0表示普通消息 1表示添加好友前的认证消息
    private String type;
    //是否为群消息 0表示普通消息 1表示群消息
    private String flagRoom;
    //消息来源的实体类，有可能是user,有可能是room
    private String room;
    private Object fromObject;
    //供前端判断
    private String unique;
    public void doUnique(){
        if("0".equals(flagRoom)){
            unique= flagRoom+"_"+accountFrom;
        }else{
            unique=flagRoom+"_"+room;
        }
    }

}
