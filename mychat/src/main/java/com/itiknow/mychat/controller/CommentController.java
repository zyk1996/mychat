package com.itiknow.mychat.controller;

import com.itiknow.mychat.entity.Comment;
import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;
    @RequestMapping("/willAdd/{spaceid}/{accountFrom}/{accountTo}/{type}")
    public String commentWillAdd(@PathVariable("spaceid")Long spaceid, @PathVariable("accountFrom")String accountFrom, @PathVariable("accountTo")String accountTo, @PathVariable("type")String type, Model model){
        model.addAttribute("spaceid",spaceid);
        model.addAttribute("accountFrom",accountFrom);
        model.addAttribute("accountTo",accountTo);
        model.addAttribute("type",type);
        return "space_comment";
    }
    @RequestMapping("/add")
    @ResponseBody
    public MSG commentAdd(Comment comment){
        if(comment.getCreatetime()==null){
            comment.setCreatetime(System.currentTimeMillis());
        }
        Integer result1=iCommentService.insert(comment);
        if(result1==1){
            return MSG.success();
        }else{
            return MSG.fail();
        }
    }
}
