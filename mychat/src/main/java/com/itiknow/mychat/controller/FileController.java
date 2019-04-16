package com.itiknow.mychat.controller;

import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.utils.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
public class FileController {
    @RequestMapping("/download/message_file")
    public ResponseEntity<byte[]> downloadMessageFile(@RequestParam("filename")String filename,@RequestParam("desc")String desc){
        if(filename.indexOf('/')!=-1){
            filename=filename.substring(filename.lastIndexOf('/')+1);
        }
        File file=new File(CommonConstant.DEFAULT_MESSAGE_FILE_STORE_DIR,filename);
        return doDownload(file,desc);
    }



    private ResponseEntity<byte[]> doDownload(File file,String desc) {
            if (!file.exists()) {
                return null;
            } else {
                try{
                    HttpHeaders headers = new HttpHeaders();
                    String fileName = new String(desc.getBytes("UTF-8"), "ISO-8859-1");
                    headers.setContentDispositionFormData("attachment", fileName);
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
    }
}
