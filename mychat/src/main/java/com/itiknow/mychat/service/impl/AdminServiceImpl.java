package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Admin;
import com.itiknow.mychat.mapper.AdminMapper;
import com.itiknow.mychat.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.adminLogin(admin);
    }
}
