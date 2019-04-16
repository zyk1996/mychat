package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.mapper.UserMapper;
import com.itiknow.mychat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }

    @Override
    public Integer userRegister(User user) {
        return userMapper.userRegister(user);
    }

    @Override
    public User getUserByAccountAndPhone(User user) {
        return userMapper.getUserByAccountAndPhone(user);
    }

    @Override
    public Integer updateUserPassword(User user) {
        return userMapper.updateUserPassword(user);
    }

    @Override
    public Integer updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public Integer updateUserFlagVip(User user) {
        return userMapper.updateUserFlagVip(user);
    }

    @Override
    public Integer updateUserImage(User user) {
        return userMapper.updateUserImage(user);
    }

    @Override
    public User getUserByAccount(User user) {
        return userMapper.getUserByAccount(user);
    }
}
