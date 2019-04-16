package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRoomRel;
import com.itiknow.mychat.mapper.UserRoomRelMapper;
import com.itiknow.mychat.service.IUserRoomRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoomRelServiceImpl implements IUserRoomRelService {
    @Autowired
    UserRoomRelMapper userRoomRelMapper;
    @Override
    public UserRoomRel getByUserAndRoom(UserRoomRel userRoomRel) {
        return userRoomRelMapper.getByUserAndRoom(userRoomRel);
    }

    @Override
    public List<User> selectUsersByRoom(String id) {
        return userRoomRelMapper.selectUsersByRoom(id);
    }

    @Override
    public Integer insert(UserRoomRel userRoomRel) {
        return userRoomRelMapper.insert(userRoomRel);
    }

    @Override
    public List<Room> selectRooms(String account) {
        return userRoomRelMapper.selectRooms(account);
    }
}
