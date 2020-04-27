package com.hongtao.common.entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class AjaxResponse implements Serializable {

	public static Integer ERROR=403; //无访问权限
	public static Integer SUCCESS=200; //访问成功
	public static Integer NOTFOUND=404; //找不到请求资源

	private boolean isok;
	private int code;
	private String message;
	private Map<String,Object> data;

	
	public static AjaxResponse error()
	{
		AjaxResponse result=new AjaxResponse();
		result.setIsok(false);
		result.setCode(ERROR);
		result.setMessage("认证失败，请检查用户名/密码");
		result.setData(null);
		return result;
	}

	public static AjaxResponse NotAuth()
	{
		AjaxResponse result=new AjaxResponse();
		result.setIsok(false);
		result.setCode(ERROR);
		result.setMessage("权限不足");
		result.setData(null);
		return result;
	}
	
	public static AjaxResponse success(Map<String,Object> map)
	{
		AjaxResponse result=new AjaxResponse();
		result.setIsok(true);
		result.setCode(SUCCESS);
		result.setMessage("认证成功");
		result.setData(map);
		return result;
	}

	public boolean isIsok() {
		return isok;
	}

	public void setIsok(boolean isok) {
		this.isok = isok;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Integer getERROR() {
		return ERROR;
	}

	public static void setERROR(Integer ERROR) {
		AjaxResponse.ERROR = ERROR;
	}

	public static Integer getSUCCESS() {
		return SUCCESS;
	}

	public static void setSUCCESS(Integer SUCCESS) {
		AjaxResponse.SUCCESS = SUCCESS;
	}

	public static Integer getNOTFOUND() {
		return NOTFOUND;
	}

	public static void setNOTFOUND(Integer NOTFOUND) {
		AjaxResponse.NOTFOUND = NOTFOUND;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
