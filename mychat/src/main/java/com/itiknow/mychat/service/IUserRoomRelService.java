package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRoomRel;

import java.util.List;

public interface IUserRoomRelService {
    UserRoomRel getByUserAndRoom(UserRoomRel userRoomRel);
    List<User> selectUsersByRoom(String id);
    Integer insert(UserRoomRel userRoomRel);
    List<Room> selectRooms(String account);
}
