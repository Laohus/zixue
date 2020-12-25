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

        String sqlaccount =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\";",map.get("username"));
        String n = jdbcTemplate.queryForObject(sqlaccount,String.class);
        assert n != null;
        if (n.equals("0")){
            return 3;
        }
        String sql =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",map.get("username"),map.get("password"));
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

//    查询新密码与旧密码是否相同，相同返回0，修改成功返回1
    public Integer passwordquery(Map<String,String> map){

        String sqlaccount =String.format("SELECT PASSWORD FROM `user` WHERE NAME=\"%s\";",map.get("username"));
        String oldpassword = jdbcTemplate.queryForObject(sqlaccount,String.class);
        assert oldpassword != null;
        if (oldpassword.equals(map.get("newpassword"))){
            return 0;
        }
        String Modpassword =String.format("UPDATE `user` SET PASSWORD=\"%s\" WHERE NAME=\"%s\";",map.get("newpassword"),map.get("username"));
        return jdbcTemplate.update(Modpassword);

    }



}
