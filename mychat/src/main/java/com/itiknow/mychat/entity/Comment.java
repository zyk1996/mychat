package com.itiknow.mychat.entity;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long spaceId;
    private String accountFrom;
    private String accountTo;
    private String content;
    private Long createtime;
    //type属性表示评价类型 ‘0’表示评论 ‘1’表示回复
    private String type;
    private User from;
    private User to;
}
