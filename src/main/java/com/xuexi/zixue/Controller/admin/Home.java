package com.xuexi.zixue.Controller.admin;

import com.xuexi.zixue.Service.mysql.UserAccount;
import com.xuexi.zixue.entity.PlanResult;
import com.xuexi.zixue.entity.Responseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller
@ResponseBody
public class Home extends Responseinfo {

    @Autowired
    private UserAccount userService;

    @RequestMapping("/home/edit-user")
    public PlanResult Moduser(HttpServletRequest request) throws IOException {

        String username = null;
        PlanResult result = new PlanResult();


        if(request.getHeader("Cookie")==null){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getSESSION_TIMEOUT());
            return result;
        }
        int count=request.getHeader("Cookie").indexOf("SESSION=");
        String Cookiebase=request.getHeader("Cookie").substring(8+count);
        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
        String Cookie = new String(bytes, StandardCharsets.UTF_8);


        HttpSession session = request.getSession();

        if(!Cookie.equals(session.getId())){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getSESSION_TIMEOUT());
            return result;
        }


        Enumeration<String> attrs = session.getAttributeNames();
        while(attrs.hasMoreElements()){
            username = attrs.nextElement().toString();
        }
//            Object vakue = session.getAttribute(name);
        System.out.println(username);

        Map tmpMap =(Map) session.getAttribute(username);

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
