package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Message;

import java.util.List;

public interface IMessageService {
    Integer insert(Message message);
    List<Message> selectUserMessage(String accountFrom, String accountTo);
    List<Message> selectRoomMessage(String room);
}
