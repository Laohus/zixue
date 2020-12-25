package com.xuexi.zixue.Service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserAccount {

    @Autowired
    private JdbcTemplate jdbcTemplate;


//    查询账户用户和密码是否存在，存在返回1,用户密码不正确返回0，用户名不存在返回3
    public Integer accountquery(Map<String,String> map){

        String username = map.get("username");
        String password = map.get("password");

        String sqlaccount =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\";",username);
        String n = jdbcTemplate.queryForObject(sqlaccount,String.class);
        assert n != null;
        if (n.equals("0")){
            return 3;
        }
        String sql =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",username,password);
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }


}
