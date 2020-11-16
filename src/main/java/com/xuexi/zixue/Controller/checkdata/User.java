package com.xuexi.zixue.Controller.checkdata;

import javax.validation.constraints.NotBlank;


public class User {



    @NotBlank(message = "用户账号不能为空")
    private String username;



    @NotBlank(message = "用户密码不能为空")
    private String password;


    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;

    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;

    }
//    public  String toString(){
//        return "Users{username="+username+",password="+password+"}";
//    }
}
