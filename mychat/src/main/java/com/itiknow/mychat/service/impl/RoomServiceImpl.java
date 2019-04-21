package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.mapper.RoomMapper;
import com.itiknow.mychat.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    RoomMapper roomMapper;
    @Override
    public Room getById(String room) {
        return roomMapper.getById(room);
    }

    @Override
    public Integer insertRoom(Room room) {
        return roomMapper.insertRoom(room);
    }

}
