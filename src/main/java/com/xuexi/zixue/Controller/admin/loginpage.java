package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Controller.checkdata.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class loginpage {
    @RequestMapping("/")
    public String login() {
        return "/page/login.html";

    }



//    @GetMapping("/home")
//    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
//                            Model model){
//
//        boolean res = new connsql().checklogin(username,password);
//        if (res){
//            return "/page/home.html";
//        }else {
////            map.put("msg","输入账号信息不正确，请重试！");
//            model.addAttribute("msg","输入账号信息不正确，请重试！");
//
//            return "/";
//
//        }
//
//    }

    @RequestMapping("/home")
    public String loginhome(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/page/login.html";
        }

//        boolean res = new connsql().checklogin(username,password);
//        if (res){
//            return "/page/home.html";
//        }else {
////            map.put("msg","输入账号信息不正确，请重试！");
//            model.addAttribute("msg","输入账号信息不正确，请重试！");
//
//            return "/";
//
//        }
//
        return "ok";
    }
}
