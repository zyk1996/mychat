package com.itiknow.mychat.controller;

import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.entity.MSG;
import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRoomRel;
import com.itiknow.mychat.entity.other.Info;
import com.itiknow.mychat.entity.other.MyException;
import com.itiknow.mychat.service.IRoomService;
import com.itiknow.mychat.service.IUserRoomRelService;
import com.itiknow.mychat.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    IUserRoomRelService iUserRoomRelService;
    @Autowired
    IRoomService iRoomService;
    @RequestMapping("/add")
    public String room_add(){
        return "room_add";
    }
    @ResponseBody
    @RequestMapping("/get/{room}")
    public MSG getRoom(@PathVariable("room")String room){
        Room result1=iRoomService.getById(room);
        if(result1==null){
            return MSG.fail();
        }else{
            return MSG.success().put("room",result1);
        }
    }
    @RequestMapping("/get")
    public String roomGet(@RequestParam("room")String room, HttpSession httpSession, Model model){
        Room result1=iRoomService.getById(room);
        if(result1!=null){
            UserRoomRel userRoomRel=new UserRoomRel();
            userRoomRel.setRoom(room);
            userRoomRel.setAccount(((User)(httpSession.getAttribute("user"))).getAccount());
            UserRoomRel result2=iUserRoomRelService.getByUserAndRoom(userRoomRel);
            if(result2!=null){
                model.addAttribute("added",true);
            }else{
                model.addAttribute("added",false);
            }
            model.addAttribute("room",result1);
        }
        return "search_result_room";
    }

    @RequestMapping("/create")
    public String create(Model model,HttpSession httpSession){
        String var1= CommonUtils.randomAccount();
        Room room=new Room();
        room.setRoom(var1);
        room.setCreatetime(System.currentTimeMillis());
        room.setCreateUser(((User)httpSession.getAttribute("user")).getAccount());
        Integer result1=iRoomService.insertRoom(room);
        if(result1==1){
            UserRoomRel userRoomRel=new UserRoomRel();
            userRoomRel.setRoom(room.getRoom());
            userRoomRel.setAccount(room.getCreateUser());
            Integer result2=iUserRoomRelService.insert(userRoomRel);
            if(result2==1){
                String message="群聊账号为："+var1+" ,你是该群的群主哦";
                model.addAttribute("info",new Info(message,"/home",20));
                return CommonConstant.DEFAULT_SUCCESS_VIEW_NAME;
            }else{
                throw new MyException("服务器繁忙",550,"/home");
            }

        }else{
            throw new MyException("服务器繁忙",550,"/home");
        }
    }
}
