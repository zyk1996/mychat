package com.itiknow.mychat.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserRoomRel {
    private String room;
    private String account;
    private String accFlag;
    private Long starttime;
    private List<Room> rooms;
}
