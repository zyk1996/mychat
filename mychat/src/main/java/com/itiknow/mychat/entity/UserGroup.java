package com.itiknow.mychat.entity;

import lombok.Data;

@Data
public class UserGroup {
    private String primaryAccount;
    private String secondaryAccount;
    private Integer group;
}
