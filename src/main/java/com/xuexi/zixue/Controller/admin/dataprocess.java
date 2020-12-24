package com.xuexi.zixue.Controller.admin;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@PropertySource("classpath:application.properties")
public class dataprocess {


    @RequestMapping("/user")
    public String login() { return "login"; }



}
