package com.itiknow.mychat.entity;

import com.itiknow.mychat.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String account;
    private String password;
    private Long createtime;
    private String flagVip;
    private Long timeVip;
    private Long timeStartVip;
    private String name;
    private String birthday;
    private String sex;
    private String image;
    private String phone;
    //用户等级：由createtime和timeVip属性共同决定，用户表中不存在此字段
    private Integer grade;
    public void init(){
        long now=System.currentTimeMillis();
        long var1=now-createtime;
        long var2=timeVip*2;
        long var3=0L;
        if("是".equals(flagVip)){
            var3=(now-timeStartVip)*2;
        }
        long total=var1+var2+var3;
        this.setGrade((int)(total/ CommonConstant.EVERY_GRADE));
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createtime=" + createtime +
                ", flagVip='" + flagVip + '\'' +
                ", timeVip=" + timeVip +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                '}';
    }
}
