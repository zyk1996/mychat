package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRel;

import java.util.List;

public interface IUserRelService {
    UserRel get(UserRel userRel);
    Integer insert(UserRel userRel);
    List<User> selectUsers(String account);
}
