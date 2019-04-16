package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRel;
import com.itiknow.mychat.mapper.UserRelMapper;
import com.itiknow.mychat.service.IUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelServiceImpl implements IUserRelService {
    @Autowired
    UserRelMapper userRelMapper;
    @Override
    public UserRel get(UserRel userRel) {
        return userRelMapper.get(userRel);
    }

    @Override
    public Integer insert(UserRel userRel) {
        return userRelMapper.insert(userRel);
    }

    @Override
    public List<User> selectUsers(String account) {
        return userRelMapper.selectUsers(account);
    }
}
