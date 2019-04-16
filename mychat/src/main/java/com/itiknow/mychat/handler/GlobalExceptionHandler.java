package com.itiknow.mychat.handler;

import com.itiknow.mychat.constant.CommonConstant;
import com.itiknow.mychat.entity.other.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ModelAndView handlerMyException(MyException e){
        System.err.println("拦截了MyException异常");
        ModelAndView mv=new ModelAndView();
        mv.addObject("exception",e);
        mv.setViewName(CommonConstant.DEFAULT_ERROR_VIEW_NAME);
        return mv;

    }
    /*@ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception e){
        System.err.println("拦截了Exception异常");
        ModelAndView mv=new ModelAndView();
        mv.addObject("exception",e);
        mv.setViewName(CommonConstant.GLOBAL_ERROR_VIEW_NAME);
        return mv;

    }*/
}
