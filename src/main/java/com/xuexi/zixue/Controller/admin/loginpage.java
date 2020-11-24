package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Controller.checkdata.connsql;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String login() {
        return "login"; }

//    @RequestMapping("/home")
//    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
//                            Map<String,Object> map, HttpSession session){
//        Map<String, String> mapsource = new HashMap<String,String>();
//        mapsource.put("driver",driver);
//        mapsource.put("url",url);
//        mapsource.put("user",user);
//        mapsource.put("userpassword",userpassword);
//        System.out.println(username);
//
//        if (username.equals("null") || password.equals("null")){
//            map.put("messageerror","输入的用户信息不能包含特殊字符");
//            return "login";
//        }
//        boolean res = new connsql().checklogin(username,password,mapsource);
//        if (res){
//            session.setAttribute("username",username);
//            return "home";
//        }else {
//            map.put("messageerror","输入的账号密码不正确，请重新输入！");
//            return "login";
//
//        }
//    }



    @RequestMapping("/login/home")
    public String loginhome(HttpServletRequest request, Map<String,Object> map, HttpSession session){

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (session.getAttribute("username")==null && username==null){
                return "login";
            }else if (session.getAttribute("username")!=null && username==null){
                return "home";
            }
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
                session.setAttribute("username",username);
                return "home";
            }else {
                map.put("messageerror","输入的账号密码不正确，请重新输入！");
                return "login";

            }

    }

    @RequestMapping("/homepage")
    public String loginhomepage(HttpSession session){
        String sessionche = (String) session.getAttribute("username");
        if (sessionche!=null && sessionche.length()>0){
            return "homepage";
        }else {
            return "login";
        }

    }

}
