package com.xuexi.zixue.Controller.data;


import com.xuexi.zixue.entity.PlanResult;
import com.xuexi.zixue.entity.Responseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@Controller
public class userdata extends Responseinfo{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/query/userdata")
    public PlanResult queryuser(HttpServletRequest request){

        String page = request.getParameter("page");
        int n  =  (Integer.parseInt(page) - 1);
        String limit = request.getParameter("limit");

        PlanResult result = new PlanResult();

        List<Map<String, Object>> userdata = new ArrayList<>();
        List<Object> listdata = new ArrayList<>();

        String sql = String.format("SELECT * FROM `user` LIMIT %s , %s;", n,limit);
        userdata = jdbcTemplate.queryForList(sql);

        result.setCode(getSUCCESS_CODE());
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> userdatum : userdata) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userdatum);
            listdata.add(jsonObject);
        }
        result.setData(listdata);
        return result;


    }

}
