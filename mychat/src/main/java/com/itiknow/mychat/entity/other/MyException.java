package com.itiknow.mychat.entity.other;

import lombok.Data;

@Data
public class MyException extends RuntimeException {
    private String url;
    private Integer code;
    public MyException(String message, Integer code, String url){
        super(message);
        this.code=code;
        this.url=url;
    }
    public MyException(String message, Integer code){
        this(message,code,null);
    }
    public MyException(String message){
        super(message);
    }
    public MyException(){
        this(null,null,null);
    }
}
