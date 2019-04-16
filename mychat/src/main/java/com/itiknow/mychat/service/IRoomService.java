package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Room;

public interface IRoomService {
    Room getById(String room);

    Integer insertRoom(Room room);
}
