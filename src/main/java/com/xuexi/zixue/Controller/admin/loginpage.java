package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginpage {


    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String,Object> map, HttpSession session) {
        if (request.getParameter("username")==null && request.getParameter("password")==null){
            return "/login";
        }else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Map<String, String> umap = new HashMap<String,String>();
            umap.put("username",username);
            umap.put("password",password);
            if (username.equals("null") || password.equals("null")){
                map.put("messageerror","输入的用户信息不能包含特殊字符");
                return "login";
            }
            Integer res = userService.accountquery(umap);
            if (res==1){
                session.setAttribute("AccountUser",umap);
                return "redirect:/home";
            }else if (res==3){
                map.put("messageerror","输入的账号不存在，请重新输入！");
                return "/login";
            }
            else {
                map.put("messageerror","输入的账号密码不正确，请重新输入！");
                return "/login";

            }
        }
    }


    @Autowired
    private UserAccount userService;


    @RequestMapping("/home")
    public String home(HttpSession session){
        Map tmpMap =(Map) session.getAttribute("AccountUser");
        if (tmpMap!=null && tmpMap.size()==2 && tmpMap.get("username")!=null && tmpMap.get("username")!=""){
            return "home";
        }else {
            return "redirect:login";
        }

    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session, SessionStatus sessionStatu){
        session.removeAttribute("AccountUser");
        session.invalidate();
        sessionStatu.setComplete();
        return "redirect:login";

    }

}
