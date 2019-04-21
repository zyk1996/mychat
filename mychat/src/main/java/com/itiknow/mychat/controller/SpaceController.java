package com.itiknow.mychat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.entity.Space;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.other.MyException;
import com.itiknow.mychat.service.ISpaceService;
import com.itiknow.mychat.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/space")
public class SpaceController {
    @Autowired
    ISpaceService iSpaceService;
    @RequestMapping("/store/page/{account}/{pageNum}")
    public String getStoreSpace(@PathVariable("account")String account,@PathVariable("pageNum")Integer pageNum,Model model){
        PageHelper.startPage(pageNum,2);
        List<Space> result1=iSpaceService.selectSpacesByStore(account);
        PageInfo pageInfo=new PageInfo(result1);
        model.addAttribute("pageInfo",pageInfo);
        return "space_store";


    }
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public MSG deleteById(@PathVariable("id")Long id){
        Integer result1=iSpaceService.deleteById(id);
        if(result1==1){
            return MSG.success();
        }else{
            return MSG.fail();
        }
    }
    @RequestMapping("/willAdd")
    public String space_add(){
        return "space_add";
    }
    @RequestMapping("/willUpdate/{id}")
    public String space_update(@PathVariable("id")Long id,Model model){
        Space result1=iSpaceService.selectByIdWithOutOther(id);
        if(result1!=null){
            model.addAttribute("space",result1);
            return "space_update";
        }else{
            throw new MyException("数据库无此朋友圈记录或服务器异常",457,"/user/home");
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public MSG spaceUpdate(@RequestParam("id")Long id,@RequestParam("content")String content,@RequestParam("flagAddr")String flagAddr,@RequestParam("addr")String addr){
        Space space=new Space();
        space.setId(id);
        space.setContent(content);
        space.setFlagAddr(flagAddr);
        space.setAddr(addr);
        Integer result1=iSpaceService.updateById(space);
        return MSG.success();
    }
    @RequestMapping("/getAll/page/{account}/{pageNum}")
    public String getAll(@PathVariable("account")String account, @PathVariable("pageNum")Integer pageNum, Model model){
        PageHelper.startPage(pageNum,2);
        List<Space> result1=iSpaceService.selectSpaceByAccountAll(account);
        PageInfo pageInfo=new PageInfo(result1);
        model.addAttribute("pageInfo",pageInfo);
        return "space_all";

    }
    @RequestMapping("/getSelf/page/{account}/{pageNum}")
    public String getSelf(@PathVariable("account")String account, @PathVariable("pageNum")Integer pageNum, Model model){
        PageHelper.startPage(pageNum,2);
        List<Space> result1=iSpaceService.selectByAccountMySelf(account);
        PageInfo pageInfo=new PageInfo(result1);
        model.addAttribute("pageInfo",pageInfo);
        return "space_self";

    }
    @ResponseBody
    @RequestMapping("/add")
    public MSG spaceAdd(Space space, HttpSession httpSession){
        space.setAccountFrom(((User)httpSession.getAttribute("user")).getAccount());
        space.setCreatetime(System.currentTimeMillis());
        space.setDatetime(DateUtils.format(new Date()));
        if(space.getFlagAttr()==null){
            space.setFlagAttr("0");
        }
        if(space.getAttrSrc()==null){
            space.setAttrSrc("");
        }
        System.err.println(space);
        Integer result1=iSpaceService.insert(space);
        if(result1==1){
            return MSG.success();
        }else{
            return MSG.fail();
        }
    }
}
