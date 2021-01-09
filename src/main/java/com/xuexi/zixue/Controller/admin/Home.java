package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import com.xuexi.zixue.entity.PlanResult;
import com.xuexi.zixue.entity.Responseinfo;
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
public class Home extends Responseinfo {

    @Autowired
    private UserAccount userService;

    @RequestMapping("/home/edit-user")
    public PlanResult Moduser(HttpServletRequest request, HttpSession session) {

        PlanResult result = new PlanResult();
        Map<String, Object> resultMap = new HashMap<>();

        Map tmpMap =(Map) session.getAttribute("AccountUser");
        if (tmpMap==null){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getSESSION_TIMEOUT());
            return result;
        }
        String newpassword = request.getParameter("newpassword");
        String newpassword_t = request.getParameter("newpassword_t");
        Map<String, String> umap = new HashMap<String,String>();
        if (!newpassword.equals(newpassword_t)){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_NOTSAME());
            return result;
        }
        umap.put("newpassword",newpassword);
        umap.put("username", (String) tmpMap.get("username"));
        Integer res = userService.passwordquery(umap);
        if(res==0){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_SAME());
            return result;
        }
        if(res==1){
            result.setCode(getSUCCESS_CODE());
            result.setMsg(getACCOUNT_SUCCESS());
            return result;
        }
        result.setCode(getFAIL_CODE());
        result.setErrormsg(getACCOUNT_ERROR());
        return result;
    }


}
