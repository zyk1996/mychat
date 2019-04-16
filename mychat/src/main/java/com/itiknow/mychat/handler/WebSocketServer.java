package com.itiknow.mychat.handler;

import com.google.gson.Gson;
import com.itiknow.mychat.entity.Message;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ServerEndpoint("/websocket/{account}")
public class WebSocketServer {
    private static volatile ExecutorService executorService=Executors.newFixedThreadPool(10);
    private static volatile ConcurrentHashMap<String,Session> sessionMap=new ConcurrentHashMap<>();
    private static volatile ConcurrentHashMap<String, CopyOnWriteArrayList<Message>> messageMap=new ConcurrentHashMap<>();
    private static volatile boolean threadIsRun=false;
    public void handler(Message message){
        String dest=message.getAccountTo();
        if(!messageMap.containsKey(dest)){
            messageMap.put(dest,new CopyOnWriteArrayList<>());
            System.err.println("新创建了一个供"+dest+"的信箱");
        }
        messageMap.get(dest).add(message);
        System.err.println("消息"+message+"进入了信箱"+dest);
    }
    @OnOpen
    public void connect(@PathParam("account")String account,Session session){
        sessionMap.put(account,session);
        if(!threadIsRun){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    threadIsRun=true;
                    while(true){
                        sendAllMessage();
                    }
                }
            });
        }
    }
    @OnClose
    public void close(Session session){
        for (Map.Entry<String,Session> entry:sessionMap.entrySet()
             ) {
            if(session.equals(entry.getValue())){
                sessionMap.remove(entry.getKey());
            }
        }
    }
    private void sendAllMessage(){
        for(Map.Entry<String,Session> entry:sessionMap.entrySet()){
            String key=entry.getKey();
            Session val=entry.getValue();
            if(messageMap.containsKey(key)&&messageMap.get(key).size()>0){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (key){
                            sendMessages(val,messageMap.get(key));
                            if(messageMap.get(key).size()==0){
                                messageMap.remove(key);
                                System.err.println("删除了"+key+"信箱");
                            }
                        }
                    }
                });
            }
        }
    }
    private void sendMessages(Session session,CopyOnWriteArrayList<Message> messages){
        System.err.println("消息数："+messages.size());
        for (Message message:messages
             ) {
            if(session!=null){
                        try {
                            message.doUnique();
                            session.getBasicRemote().sendText(new Gson().toJson(message));
                            messages.remove(message);
                            System.err.println("发送一条消息："+message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }
        }
    }
}
