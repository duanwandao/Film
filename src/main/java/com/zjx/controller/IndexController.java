package com.zjx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根路径以及其他请求处理
 */
@Controller
public class IndexController {


    /**
     * 根路径以及其他请求处理
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("title", "首页");
        modelAndView.addObject("mainPage", "film/indexFilm");
        modelAndView.addObject("mainPageKey", "indexFilm");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 登录请求
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 进入后台管理请求
     * @return
     */
    @RequestMapping("/admin")
    public String toAdmin(){
        return "/admin/main";
    }
}
