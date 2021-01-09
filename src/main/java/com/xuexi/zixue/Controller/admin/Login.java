package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import com.xuexi.zixue.entity.PlanResult;
import com.xuexi.zixue.entity.Responseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class Login extends Responseinfo {


    @Autowired
    private UserAccount userService;

    @RequestMapping("/login/account")
    public PlanResult Moduser (HttpServletRequest request, HttpSession session) {

        PlanResult result = new PlanResult();
        Map<String, Object> resultMap = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, String> umap = new HashMap<String,String>();
        umap.put("username",username);
        umap.put("password",password);
        Integer res = userService.accountquery(umap);
        if (res==1){
            session.setAttribute("AccountUser",umap);
            result.setCode(getSUCCESS_CODE());
            result.setMsg(getACCOUNT_SUCCESS());
            return result;
        }else if (res==3){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT_NO_FOUND());
            return result;
        }
        else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT_ERROR());
            return result;
        }

    }

}
