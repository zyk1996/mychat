package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.User;

public interface IUserService {
    User userLogin(User user);
    Integer userRegister(User user);
    User getUserByAccountAndPhone(User user);
    Integer updateUserPassword(User user);
    Integer updateUserInfo(User user);
    Integer updateUserFlagVip(User user);
    Integer updateUserImage(User user);
    User getUserByAccount(User user);
}
