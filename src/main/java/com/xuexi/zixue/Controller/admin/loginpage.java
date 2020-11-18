package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Controller.checkdata.connsql;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class loginpage {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/home")
    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
                            Map<String,Object> map){
        boolean res = new connsql().checklogin(username,password);
        if (username.equals("null") || password.equals("null")){
            map.put("messageerror","输入的用户信息不能包含特殊字符");
            return "login";
        }
        if (res){
            return "redirect:/page/home.html";
        }else {
            map.put("messageerror","输入的账号密码不正确，请重新输入！");
            return "login";

        }
    }

}
