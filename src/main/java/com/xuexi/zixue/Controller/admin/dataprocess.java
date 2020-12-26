package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Controller
//@RestController
public class dataprocess {

    @Autowired
    private UserAccount userService;

    @RequestMapping("/home/edit-user")
    public String Moduser(HttpServletRequest request, Map<String,Object> map, HttpSession session) {
        Map tmpMap =(Map) session.getAttribute("AccountUser");
        if (tmpMap==null){
            return "redirect:login";
        }
        String newpassword = request.getParameter("newpassword");
        String newpassword_t = request.getParameter("newpassword_t");
        System.out.println(newpassword);
        if (newpassword.equals("null") || newpassword_t.equals("null")){
            map.put("messageerror","新密码不能为特殊字符，请重新输入");
            return "/home";
        }
        Map<String, String> umap = new HashMap<String,String>();
        if (!newpassword.equals(newpassword_t)){
            map.put("messageerror","新密码与确认密码不一致，请重新输入");
            return "/home";
        }
        umap.put("newpassword",newpassword);
        umap.put("username", (String) tmpMap.get("username"));
        Integer res = userService.passwordquery(umap);
        if(res==0){
            map.put("messageerror","新密码与旧密码相同，请重新输入");
            return "False";
        }
        if(res==1){
            map.put("message","修改密码成功");
            return "/home";
        }
        map.put("messageerror","修改密码失败");
        return "/home";
    }





}
