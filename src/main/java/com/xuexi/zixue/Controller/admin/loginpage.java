package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Controller.checkdata.connsql;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@PropertySource("classpath:application.properties")
public class loginpage {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String userpassword;

    @RequestMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/home")
    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
                            Map<String,Object> map){
        Map<String, String> mapsource = new HashMap<String,String>();
        mapsource.put("driver",driver);
        mapsource.put("url",url);
        mapsource.put("user",user);
        mapsource.put("userpassword",userpassword);

        if (username.equals("null") || password.equals("null")){
            map.put("messageerror","输入的用户信息不能包含特殊字符");
            return "login";
        }
        boolean res = new connsql().checklogin(username,password,mapsource);
        if (res){
            return "redirect:/page/home.html";
        }else {
            map.put("messageerror","输入的账号密码不正确，请重新输入！");
            return "login";

        }
    }

}
