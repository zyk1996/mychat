package com.itiknow.mychat.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
@Data
public class MSG implements Serializable {
    private String message;
    private HashMap<String,Object> hashMap=new HashMap<>();
    private Integer code;

    public static MSG success(){
        MSG msg=new MSG();
        msg.setCode(200);
        msg.setMessage("success");
        return msg;
    }
    public static MSG fail(){
        MSG msg=new MSG();
        msg.setCode(400);
        msg.setMessage("fail");
        return msg;
    }
    public MSG put(String key,Object val){
         this.hashMap.put(key,val);
         return this;
    }

}
