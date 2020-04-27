package com.hongtao.admin.utils;

import com.hongtao.common.entity.UserInfo;

import javax.annotation.Resource;

public class Result {

    private String msg;
    private Integer code;
    private Object data;
    private String token;

    @Resource
    TokenUtils tokenUtils;


    public Result success(UserInfo userInfo)
    {
        Result result=new Result();
        result.setMsg("认证成功");
        result.setCode(200);
        result.setData(userInfo);
        String token = tokenUtils.makeToken(userInfo.getId(), userInfo.getUsername(), userInfo.getRole());
        result.setToken(token);
        return result;
    }


    public Result error()
    {
        Result result=new Result();
        result.setMsg("认证失败，请检查后重新验证！");
        result.setCode(400);
        return result;
    }



    public Result() {
    }

    public Result(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Result(String msg, Integer code, Object data, String token) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.token = token;
    }
}
