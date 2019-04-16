package com.itiknow.mychat.controller;

import com.itiknow.mychat.entity.Admin;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.other.MyException;
import com.itiknow.mychat.service.IAdminService;
import com.itiknow.mychat.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService iAdminService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String adminLogin(Admin admin, HttpSession httpSession){
        String url="/admin.html";
        if(CommonUtils.empty(admin.getAccount())||CommonUtils.empty(admin.getPassword())){
            throw new MyException("表单项含空项",452,url);
        }else{
            admin.setPassword(CommonUtils.encodePassword(admin.getPassword()));
            Admin var1=iAdminService.adminLogin(admin);
            if(var1==null){
                throw new MyException("用户名或密码错误",450,url);
            }else{
                httpSession.setAttribute("admin",var1);
                return "admin";
            }
        }
    }
    @RequestMapping("/logout")
    public String adminLogout(HttpSession httpSession){
        if(httpSession.getAttribute("admin")!=null){
            httpSession.removeAttribute("admin");
        }
        return "redirect:/admin.html";
    }

    public static void main(String[] args) {
        System.out.println(CommonUtils.encodePassword("88888888"));
    }

}
