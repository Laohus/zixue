package com.xuexi.zixue.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class Page {

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }


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
