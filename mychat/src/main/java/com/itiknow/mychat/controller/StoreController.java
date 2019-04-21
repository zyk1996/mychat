package com.itiknow.mychat.controller;

import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.entity.Store;
import com.itiknow.mychat.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    IStoreService iStoreService;
    @RequestMapping("/insert/{spaceid}/{account}")
    @ResponseBody
    public MSG insert(@PathVariable("spaceid")Long spaceid,@PathVariable("account")String account){
        try{
            Store store=new Store();
            store.setAccount(account);
            store.setSpaceId(spaceid);
            Integer result1=iStoreService.insert(store);
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
