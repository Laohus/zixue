package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@PropertySource("classpath:application.properties")
public class loginpage {


    @RequestMapping("/login")
    public String login() { return "login"; }

    @Autowired
    private UserAccount userService;

    @RequestMapping("/login/home")
    public String loginhome(HttpServletRequest request, Map<String,Object> map, HttpSession session){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (session.getAttribute("username")==null && username==null){
            return "login";
        }else if (session.getAttribute("username")!=null && username==null){
            return "home";
        }
        if (username.equals("null") || password.equals("null")){
            map.put("messageerror","输入的用户信息不能包含特殊字符");
            return "login";
        }
        Integer res = userService.accountquery(username,password);
        if (res==1){
            session.setAttribute("username",username);
            return "home";
        }else if (res==3){
            map.put("messageerror","输入的账号不存在，请重新输入！");
            return "login";
        }
        else {
            map.put("messageerror","输入的账号密码不正确，请重新输入！");
            return "login";

        }

    }

    @GetMapping("/login/home")
    public String loginhomeg(HttpSession session){
        String sessionche = (String) session.getAttribute("username");
        if (sessionche!=null && sessionche.length()>0){
            return "home";
        }else {
            return "redirect:/login";
        }

    }

    @RequestMapping("/loginout")
    public String loginhomepage(HttpSession session){
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:login";

    }

}
