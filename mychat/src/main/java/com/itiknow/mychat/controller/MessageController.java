package com.itiknow.mychat.controller;

import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.entity.Message;
import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.handler.WebSocketServer;
import com.itiknow.mychat.service.IMessageService;
import com.itiknow.mychat.service.IRoomService;
import com.itiknow.mychat.service.IUserService;
import com.itiknow.mychat.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MessageController {
    @Autowired
    WebSocketServer webSocketServer;
    @Autowired
    IRoomService iRoomService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IMessageService iMessageService;
    @RequestMapping("/sendMessage")
    public MSG sendMessage(Message message){
        System.out.println("收到一条消息："+message);
        if(message.getCreatetime()==null){
            message.setCreatetime(System.currentTimeMillis());
        }
        if("0".equals(message.getFlagRoom())){
            User user=new User();
            user.setAccount(message.getAccountFrom());
            message.setFromObject(iUserService.getUserByAccount(user));
            webSocketServer.handler(message);
            System.out.println("私人信息转由webSocketServer处理："+message);
        }else{
            Room var1=iRoomService.getById(message.getRoom());
            message.setFromObject(var1);
            for(User var2:var1.getUsers()){
                message.setAccountTo(var2.getAccount());
                webSocketServer.handler(message);
                System.out.println("群信息转由webSocketServer处理："+message);
            }
        }
        Integer result1=iMessageService.insert(message);
        if(result1==1){
            return MSG.success().put("message",message.getContent());
        }else{
            return MSG.fail();
        }
    }
    @RequestMapping("/sendFile")
    public MSG sendFile(MultipartFile multipartFile,Message message){
        try {
            String var1= FileUtils.upload(multipartFile, CommonConstant.DEFAULT_MESSAGE_FILE_DIR,CommonConstant.DEFAULT_MESSAGE_FILE_STORE_DIR);
            String var2=multipartFile.getOriginalFilename();
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(var2).append(" ")
                    .append("<a href='/download/message_file?filename="+var1+"&desc="+var2+"' >下载</a>")
                    .append(" ")
                    .append("<a href='"+var1+"' >预览</a>");
            message.setContent(stringBuilder.toString());
            return sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return MSG.fail();
        }
    }
}
