package com.itiknow.mychat.controller;

import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.entity.*;
import com.itiknow.mychat.entity.other.Info;
import com.itiknow.mychat.entity.other.MyException;
import com.itiknow.mychat.service.IUserRelService;
import com.itiknow.mychat.service.IUserRoomRelService;
import com.itiknow.mychat.service.IUserService;
import com.itiknow.mychat.utils.CommonUtils;
import com.itiknow.mychat.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserRelService iUserRelService;
    @Autowired
    IUserRoomRelService iUserRoomRelService;

    @RequestMapping("/info")
    public String userInfo(){

        return "userInfo";
    }
    @RequestMapping("/home")
    public String forwardHome(){
        return "home";
    }

    @RequestMapping("/add")
    public String user_add(){
        return "user_add";
    }
    @ResponseBody
    @RequestMapping("/getRoomsAndFriends/{account}")
    public MSG getRoomsAndFriends(@PathVariable("account")String account){
        List<User> users=iUserRelService.selectUsers(account);
        List<Room> rooms=iUserRoomRelService.selectRooms(account);
        MSG sussess=MSG.success();
        if(users!=null&&users.size()>0){
            sussess.put("users",users);
        }
        if(rooms!=null&&rooms.size()>0){
            sussess.put("rooms",rooms);
        }
        if(sussess.getHashMap().get("users")!=null||sussess.getHashMap().get("rooms")!=null){
            return sussess;
        }
        return MSG.fail();
    }

    @RequestMapping("/get")
    public String userGet(@RequestParam("account")String account,HttpSession httpSession,Model model){
        User user=new User();
        user.setAccount(account);
        User result1=iUserService.getUserByAccount(user);
        if(result1!=null){
            UserRel userRel=new UserRel();

            userRel.setPrimaryAccount(((User)(httpSession.getAttribute("user"))).getAccount());
            userRel.setSecondaryAccount(account);
            UserRel result2=iUserRelService.get(userRel);
            if(result2!=null){
                model.addAttribute("added",true);
            }else{
                model.addAttribute("added",false);
            }
            model.addAttribute("user",result1);
        }
        return "search_result_user";
    }
    @ResponseBody
    @RequestMapping("/userrel/add")
    public MSG addUserRel(UserRel userRel){
        userRel.setCreatetime(System.currentTimeMillis());
        UserRel var1=new UserRel();
        var1.setPrimaryAccount(userRel.getSecondaryAccount());
        var1.setSecondaryAccount(userRel.getPrimaryAccount());
        var1.setCreatetime(userRel.getCreatetime());
        Integer result1=iUserRelService.insert(userRel);
        Integer result2=iUserRelService.insert(var1);
        if(result1==1&&result2==1){
            return MSG.success();
        }else{
            return MSG.fail();
        }
    }

    @RequestMapping("/forward/{attr}")
    public String forward(@PathVariable("attr")String attr){
        return "user_change_"+attr;
    }
    @RequestMapping(value="/updatePassword",method = {RequestMethod.POST})
    public String updatePassword(User user,HttpSession httpSession,@RequestParam("prePassword1") String password1,@RequestParam("prePassword2")String password2,Model model){
        String url="/user/changePassword";
        User var1=(User)(httpSession.getAttribute("user"));
        user.setAccount(var1.getAccount());
        user.setPassword(CommonUtils.encodePassword(user.getPassword()));
        User result1=iUserService.userLogin(user);
        if(result1==null){
            throw new MyException("用户名或密码错误",450,url);
        }else{
            if(CommonUtils.empty(password1)||CommonUtils.empty(password2)){
                throw new MyException("表单项含空项",452,url);
            }else{
                if(!password1.equals(password2)){
                    throw new MyException("两次密码不一致",454,url);
                }else{
                    result1.setPassword(CommonUtils.encodePassword(password1));
                    Integer result2=iUserService.updateUserPassword(result1);
                    if(result2==1){
                        model.addAttribute("info",new Info("密码更新成功","/user/info",5));
                        var1.setPassword(CommonUtils.encodePassword(password1));
                        return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;

                    }else{
                        throw new MyException("服务器繁忙",550,url);
                    }
                }
            }
        }
    }

    @RequestMapping("/updateImage")
    public String updateImage(@RequestParam("image")MultipartFile multipartFile, HttpSession httpSession,Model model){
        String url="/user/changeImage";
        try{
            User user= (User) httpSession.getAttribute("user");
            String var1= FileUtils.upload(multipartFile,CommonConstant.DEFAULT_USER_IMAGE_DIR,CommonConstant.DEFAULT_USER_IMAGE_STORE_DIR);
            User var2=new User();
            var2.setAccount(user.getAccount());
            var2.setImage(var1);
            Integer result1=iUserService.updateUserImage(var2);
            if(result1==1){
                model.addAttribute("info",new Info("头像更新成功","/user/info",5));
                user.setImage(var1);
                return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
            }else{
                throw new MyException("服务器繁忙",550,url);
            }
        }catch (IOException exception){
            throw new MyException("服务器IO异常",552,url);
        }
    }
    @RequestMapping("/updateFlagVip")
    public String updateFlagVip(HttpSession httpSession,Model model){
        String url="/user/changeFlagVip";
        if(paySuccess()){
            long now=System.currentTimeMillis();
            User user=(User)(httpSession.getAttribute("user"));
            User var1=new User();
            var1.setAccount(user.getAccount());
            var1.setFlagVip("是");
            var1.setTimeStartVip(now);

            Integer result1=iUserService.updateUserFlagVip(var1);
            if(result1==1){
                model.addAttribute("info",new Info("会员开通成功","/user/info",5));
                user.setFlagVip("是");
                user.setTimeStartVip(now);
                return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
            }else{
                throw new MyException("服务器繁忙",550,url);
            }
        }else{
            throw new MyException("支付异常，支付失败",551,url);
        }
    }



    @RequestMapping(value = "/change",method = {RequestMethod.POST})
    public String change(User user,HttpSession httpSession,Model model){
        String url="/user/info";
        User var1=(User)(httpSession.getAttribute("user"));
        user.setAccount(var1.getAccount());
        if(CommonUtils.empty(user.getBirthday())||CommonUtils.empty(user.getPhone())||CommonUtils.empty(user.getName())||CommonUtils.empty(user.getSex())){

            throw new MyException("表单项含空项",452,url);
        }else{
            if(!CommonUtils.match(user.getPhone())){
                throw new MyException("手机格式错误，不合法",451,url);
            }else{
                Integer result1=iUserService.updateUserInfo(user);
                if(result1==1){
                    model.addAttribute("info",new Info("用户信息更新成功",url,5));
                    User result2=iUserService.getUserByAccount(user);
                    httpSession.setAttribute("user",result2);
                    return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;

                }else{
                    throw new MyException("服务器繁忙",550,url);
                }
            }
        }
    }
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public String loginUser(User user, HttpSession httpSession){
        String url="/index.html";
        if(CommonUtils.empty(user.getAccount())||CommonUtils.empty(user.getPassword())){
            throw new MyException("表单项含空项",452,url);
        }
        user.setPassword(CommonUtils.encodePassword(user.getPassword()));
        User result=iUserService.userLogin(user);
        if(result!=null){
            result.init();
            httpSession.setAttribute("user",result);
            return "home";
        }else{
            throw new MyException("用户名或密码错误",450,url);
        }

    }
    @RequestMapping("/logout")
    public String logoutUser(HttpSession httpSession){
        if(httpSession.getAttribute("user")!=null){
            httpSession.removeAttribute("user");
        }
        return "redirect:/index.html";
    }
    @RequestMapping(value="/register",method = {RequestMethod.POST})
    public String registerUser(@RequestParam("phone") String phone, Model model){
        String url="/register.html";
        if(CommonUtils.empty(phone)){
            throw new MyException("表单项含空项",452,url);
        }else{
            if(!CommonUtils.match(phone)){
                throw new MyException("手机格式错误，不合法",451,url);
            }else{
                String account=CommonUtils.randomAccount();
                User user=new User();
                user.setAccount(account);
                user.setPhone(phone);
                user.setCreatetime(System.currentTimeMillis());
                user.setBirthday("2000-00-00");
                user.setPassword(CommonUtils.encodePassword(CommonConstant.DEFAULT_USER_PASSWORD));
                Integer result=iUserService.userRegister(user);
                if(result==1){
                    String message="您的账号是："+account+"，您的初始密码是："
                            +CommonConstant.DEFAULT_USER_PASSWORD+
                            "，请妥善管理好您的账号密码，记得更改密码哦";
                    model.addAttribute("info",new Info(message,"/index.html",20));
                    return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
                }else{
                    throw new MyException("服务器繁忙",550,url);
                }
            }
        }
    }
    @RequestMapping(value="/findPassword",method = {RequestMethod.POST})
    public String findPassword(User user,@RequestParam("prePassword")String prePassword,Model model){
        String url="/findPassword.html";
        if(CommonUtils.empty(user.getPassword())||CommonUtils.empty(user.getAccount())||CommonUtils.empty(user.getPhone())||CommonUtils.empty(prePassword)){
            throw new MyException("表单项含空项",452,url);
        }else{
            User result1=iUserService.getUserByAccountAndPhone(user);
            if(result1==null){
                throw new MyException("用户名或手机号错误",453,url);
            }else{
                if(!prePassword.equals(user.getPassword())){
                    throw new MyException("两次密码不一致",454,url);
                }else{
                    user.setPassword(CommonUtils.encodePassword(user.getPassword()));
                    Integer result2=iUserService.updateUserPassword(user);
                    if(result2!=1){
                        throw new MyException("服务器繁忙或者和原先密码一致",455,url);
                    }else{
                        model.addAttribute("info",new Info("密码更新成功，请妥善保管好您的密码哦","/index.html",5));
                        return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
                    }
                }
            }
        }
    }
    private boolean paySuccess() {
        return true;
    }
}
