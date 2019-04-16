package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Message;
import com.itiknow.mychat.mapper.MessageMapper;
import com.itiknow.mychat.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public Integer insert(Message message) {
        return messageMapper.insert(message);
    }
}
