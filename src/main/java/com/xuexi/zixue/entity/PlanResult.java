package com.xuexi.zixue.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;

public class PlanResult {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态
    private Integer code;

    //响应错误消息
    private String msg;

    //响应错误消息
    private  String errormsg;

    //响应业务参数
    private  Object data;

    public static ObjectMapper getMapper(){
        return MAPPER;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code=code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public String getErrormsg(){
        return errormsg;
    }

    public void setErrormsg(String errormsg){
        this.errormsg=errormsg;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data=data;
    }










}
