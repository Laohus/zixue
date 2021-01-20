package com.xuexi.zixue.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller
public class Page {

    @RequestMapping("/login")
    public String login() {
//        HttpSession session = request.getSession();
//        session.invalidate();
//        sessionStatu.setComplete();
        return "login";
    }


    @RequestMapping("/home")
    public String home(HttpServletRequest request) throws IOException {

        if(request.getHeader("Cookie")==null){
            return "redirect:login";
        }

        int count=request.getHeader("Cookie").indexOf("SESSION=");
        String Cookiebase=request.getHeader("Cookie").substring(8+count);
        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
        String Cookie = new String(bytes, StandardCharsets.UTF_8);

        HttpSession session = request.getSession();
        if(Cookie.equals(session.getId())){
            return "home";
        }else {
            return "redirect:login";
        }

    }

    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request, SessionStatus sessionStatu) throws IOException {


        if(request.getHeader("Cookie")==null){
            return "redirect:login";
        }
        int count=request.getHeader("Cookie").indexOf("SESSION=");
        String Cookiebase=request.getHeader("Cookie").substring(8+count);
        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
        String Cookie = new String(bytes, StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        if(Cookie.equals(session.getId())){
       //     session.removeAttribute("AccountUser");
            session.invalidate();
            sessionStatu.setComplete();
            return "redirect:login";
        }else {
            return "redirect:login";
        }



    }

}
