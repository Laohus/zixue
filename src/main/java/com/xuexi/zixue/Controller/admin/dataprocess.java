package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Controller
@ResponseBody
public class dataprocess {

    @Autowired
    private UserAccount userService;

    @RequestMapping("/home/edit-user")
    public String Moduser(HttpServletRequest request, HttpSession session) {
        Map tmpMap =(Map) session.getAttribute("AccountUser");
        if (tmpMap==null){
            return "session is timeout";
        }
        String newpassword = request.getParameter("newpassword");
        String newpassword_t = request.getParameter("newpassword_t");
        Map<String, String> umap = new HashMap<String,String>();
        if (!newpassword.equals(newpassword_t)){
            return "新密码与确认密码不一致，请重新输入";
        }
        umap.put("newpassword",newpassword);
        umap.put("username", (String) tmpMap.get("username"));
        Integer res = userService.passwordquery(umap);
        if(res==0){
            return "新密码与旧密码相同，请重新输入";
        }
        if(res==1){
            return "success";
        }
        return "修改密码失败";
    }





}
