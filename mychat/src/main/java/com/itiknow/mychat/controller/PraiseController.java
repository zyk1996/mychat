package com.itiknow.mychat.controller;

import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.entity.Praise;
import com.itiknow.mychat.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/praise")
public class PraiseController {
    @Autowired
    IPraiseService iPraiseService;

    @ResponseBody
    @RequestMapping("/{spaceid}/{account}")
    public MSG insert(@PathVariable("spaceid")Long spaceid,@PathVariable("account")String account){
        try{
            Praise praise=new Praise();
            praise.setAccount(account);
            praise.setSpaceId(spaceid);
            Integer result1=iPraiseService.insert(praise);
            if(result1==1){
                return MSG.success();
            }else{
                return MSG.fail();
            }
        }catch (Exception e){
            return MSG.fail();
        }
    }
}
