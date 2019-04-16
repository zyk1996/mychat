package com.itiknow.mychat.entity;

import lombok.Data;

import java.util.List;

@Data
public class Room {
    private String room;
    private Long createtime;
    private String createUser;
    private String image;
    private String name;
    private List<User> users;
}
