package com.itiknow.mychat.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileUtils {
    /**
     * 文件上传处理
     * @param multipartFile
     * @return
     */
    public static String upload(MultipartFile multipartFile,String dir,String store) throws IOException {
        String filename=multipartFile.getOriginalFilename();
        String suffix=filename.substring(filename.lastIndexOf('.'));
        String destname=uniqueRandom(30)+suffix;
        File file=new File(store);
        if(!file.exists()){
            file.mkdirs();
        }
        File dest=new File(file,destname);
        multipartFile.transferTo(dest);
        return dir+destname;
    }
    public static String read(MultipartFile multipartFile) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(multipartFile.getInputStream(),"utf-8"));
        String tmp=null;
        while((tmp=bufferedReader.readLine())!=null){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }

    private static String uniqueRandom(int num){
        String var1;
        while(redisContains(var1=CommonUtils.random(num))){
        }
        return var1;


    }
    private static boolean redisContains(String filename){
        return false;
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        BufferedInputStream bi=new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream bo=new ByteArrayOutputStream((int)file.length());
        byte[] buffer=new byte[1024];
        int len=-1;
        while((len=bi.read(buffer))!=-1){
            bo.write(buffer,0,len);
        }
        return bo.toByteArray();

    }
}
