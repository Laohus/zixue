package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class Login {

    @Autowired
    private UserAccount userService;

    @RequestMapping("/login/account")
    public String Moduser(HttpServletRequest request, HttpSession session) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> umap = new HashMap<String,String>();
        umap.put("username",username);
        umap.put("password",password);
        Integer res = userService.accountquery(umap);
        if (res==1){
            session.setAttribute("AccountUser",umap);
            return "success";
        }else if (res==3){
//            map.put("messageerror","输入的账号不存在，请重新输入！");
            return "输入的账号不存在，请重新输入！";
        }
        else {
//            map.put("messageerror","输入的账号密码不正确，请重新输入！");
            return "输入的账号密码不正确，请重新输入！";
        }

    }
}
