package com.itiknow.mychat.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private static final char[] chars=new char[]{'d','c','e','a','b','h','g','x','y','w'};
    /**
     * 验证手机号是否合法
     * @param phone
     * @return
     */
    public static boolean match(String phone){
        if(phone.length()!=11){
            return false;
        }else{
            String pattern="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            Pattern p=Pattern.compile(pattern);
            Matcher matcher=p.matcher(phone);
            if(matcher.matches()){
                return true;
            }
            return false;
        }

    }

    /**
     * 判断字符串是否为null或空
     * @param str
     * @return
     */
    public static boolean empty(String str){
        if(str==null){
            return true;
        }else{
            return str.trim().length()==0;
        }
    }

    /**
     * 随机生成用户账号
     * @return
     */
    public static String randomAccount(){
        return doRandomAccount();
    }

    /**
     * 对密码进行加密操作，加密后长度不超过30
     * @param password
     * @return
     */
    public static String encodePassword(String password){
        StringBuilder stringBuilder=new StringBuilder();
        for(int var1=0;var1<password.length();var1++){
            char var2=password.charAt(var1);
            if('0'<=var2&&var2<='9'){
                int var3=var2-'0';
                int var4=move(var3,3);
                int var5=move(var3,-4);
                stringBuilder.append(var5).append(var4).append(chars[var4]).append(var5);
            }else if(('a'<=var2&&var2<='z')||('A'<=var2&&var2<='Z')){
                char var6=(char)(var2+2);
                if(var6>'z'&&var6>'Z'){
                    var6=(char)(var6-26);
                }
                stringBuilder.append(var6);
            }else{
                stringBuilder.append(var2);
            }
        }
        if(stringBuilder.length()>30){
            return stringBuilder.substring(0,30);
        }
        return stringBuilder.toString();
    }

    private static Integer move(Integer i,Integer move){
        return ((i+move)+10)%10;
    }
    private static String doRandomAccount(){
        StringBuilder stringBuilder=new StringBuilder();
        int length=(int)(Math.random()*5)+6;
        int first=(int)(Math.random()*9)+1;
        stringBuilder.append(first);
        int tmp;
        for(int i=2;i<=length;i++){
            tmp=(int)(Math.random()*10);
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
    public static String random(int num){
        String var1=UUID.randomUUID().toString();
        if(var1.length()>num){
            var1= var1.substring(0,num);
        }
        return var1;
    }
}
