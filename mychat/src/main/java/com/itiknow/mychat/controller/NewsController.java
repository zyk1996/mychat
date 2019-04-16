package com.itiknow.mychat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.entity.News;
import com.itiknow.mychat.entity.ShortNews;
import com.itiknow.mychat.entity.other.Info;
import com.itiknow.mychat.entity.other.MyException;
import com.itiknow.mychat.service.INewsService;
import com.itiknow.mychat.utils.CommonUtils;
import com.itiknow.mychat.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    INewsService iNewsService;
    @RequestMapping("/forward/{path}")
    public String forward(@PathVariable("path")String path){
        if("select".equals(path)){
            return "forward:/news/page/1";
        }
        return "news_"+path;

    }
    @RequestMapping("/admin")
    public String adminForward(){
        return "admin";
    }
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String newsDelete(@PathVariable("id")Long id){
        Integer result1=iNewsService.deleteById(id);
        if(result1==1){
            return "success";
        }else{
            return "fail";
        }
    }
    @RequestMapping("/details/{id}")
    public String newsDetails(@PathVariable("id")Long id,Model model){
        News result1=iNewsService.selectById(id);
        model.addAttribute("news",result1);
        return "news_details";
    }
    @RequestMapping("/page/{pageNum}")
    public String pageNews(@PathVariable("pageNum")Integer pageNum,Model model){
        PageHelper.startPage(pageNum,CommonConstant.DEFAULT_NEWS_PAGE_SIZE);
        List<ShortNews> result1=iNewsService.selectAllShortNews();
        PageInfo pageInfo=new PageInfo(result1);
        model.addAttribute("pageInfo",pageInfo);
        return "news_select";
    }
    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    public String newsAdd(News news, @RequestParam(value = "contentFile",required = true)MultipartFile contentFile, @RequestParam(value = "attrSrcFile",required = false)MultipartFile attrSrcFile, Model model){
        String url="/news/forward/add";
        if(CommonUtils.empty(news.getTitle())||CommonUtils.empty(news.getDate())||CommonUtils.empty(news.getCompany())){
            throw new MyException("表单项含空项",452,url);
        }else{
            if("".equals(contentFile.getOriginalFilename())){
                throw new MyException("新闻内容为空",456,url);
            }else{
                try{
                    String content= FileUtils.read(contentFile);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                FileUtils.upload(contentFile,CommonConstant.DEFAULT_NEWS_CONTENT_DIR,CommonConstant.DEFAULT_NEWS_CONTENT_STORE_DIR);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    news.setContent(content);
                    news.setCreatetime(System.currentTimeMillis());
                    if(!"".equals(attrSrcFile.getOriginalFilename())){
                        String dest=FileUtils.upload(attrSrcFile, CommonConstant.DEFAULT_NEWS_ATTR_DIR,CommonConstant.DEFAULT_NEWS_ATTR_STORE_DIR);
                        news.setFlagAttr("是");
                        news.setAttrSrc(dest);
                    }
                    Integer result1=iNewsService.insert(news);
                    if(result1==1){
                        model.addAttribute("info",new Info("新闻添加成功",url,3));
                        return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
                    }else{
                        throw new MyException("服务器繁忙",550,url);
                    }

                }catch(IOException e){
                    throw new MyException("服务器IO异常",552,url);
                }
            }
        }
    }
}
