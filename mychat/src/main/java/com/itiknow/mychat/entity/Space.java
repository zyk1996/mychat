package com.itiknow.mychat.entity;

import lombok.Data;

import java.util.List;

@Data
public class Space {
    private Long id;
    private String accountFrom;
    private String content;
    private Long createtime;
    private String datetime;
    private String flagAddr;
    private String addr;
    private String flagAttr;
    private String attrSrc;
    private User user;
    private List<Comment> comments;
    private List<Praise> praises;

}
