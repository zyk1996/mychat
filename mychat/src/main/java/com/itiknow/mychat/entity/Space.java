package com.itiknow.mychat.entity;

import lombok.Data;

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
}
