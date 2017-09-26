package com.ssm.controller;

import com.ssm.dto.SeeUser;
import com.ssm.service.RedisBaiseTakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mm on 2017/9/14.
 * redis测试一
 */
@Controller
@RequestMapping("/test")
public class SeeControll {

    @Autowired
    private RedisBaiseTakes redisBaiseTakes;

    @RequestMapping("/dataOne")
    public ModelAndView getRedisDate(){
        ModelAndView mv = new ModelAndView();
        System.out.println("hello see");
        redisBaiseTakes.addObj("hello1","zz");
        mv.setViewName("hello");
        return  mv;
    }

    @RequestMapping("/hello2.do")
    public ModelAndView hello2(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        SeeUser seeUser = new SeeUser();
        seeUser.setId("1");
        seeUser.setIp("127.0.0.1");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        seeUser.setSeeTime(time);
        seeUser.setSeeCount(1);
        redisBaiseTakes.addObj1("seeUser",seeUser.getId(),seeUser);
        mv.setViewName("hello");
        return mv;
    }
    @RequestMapping("/get.do")
    @ResponseBody
    public  ModelAndView getRedisData(){
        ModelAndView modelAndView = new ModelAndView();
        redisBaiseTakes.getObj("seeUser");
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
