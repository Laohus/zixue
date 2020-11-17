package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Controller.checkdata.connsql;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
public class loginpage {
    @RequestMapping("/")
    public String login() {
        return "/page/login.html";

    }

    @PostMapping("/home")
    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
                            Map<String,Object> map){

        boolean res = new connsql().checklogin(username,password);
        if (username==null || password==null){
            map.put("msg","输入的用户信息不能为null");
            return "redirect:/";
        }
        if (res){
            return "redirect:/page/home.html";
        }else {
            map.put("msg","输入的用户信息不正确");
            return "redirect:/";

        }
    }

}
