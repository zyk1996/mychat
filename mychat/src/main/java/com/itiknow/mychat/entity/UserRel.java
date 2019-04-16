package com.itiknow.mychat.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserRel {
    private String primaryAccount;
    private String secondaryAccount;
    private Long createtime;
    private String mark;
    private String flagBlack;
    private List<User> secondarys;
}
