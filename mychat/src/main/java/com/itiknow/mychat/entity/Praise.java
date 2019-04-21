package com.itiknow.mychat.entity;

import lombok.Data;

@Data
public class Praise {
    private Long spaceId;
    private String account;
    private User user;
}
