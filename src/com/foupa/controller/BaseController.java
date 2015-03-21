package com.foupa.controller;

import java.util.HashMap;
import java.util.Map;

import com.foupa.util.StaticFactory;

import com.jfinal.core.Controller;

/**
 * 基础类
 * 
 * @author Manville
 * @date 2014年11月30日 下午3:12:08
 */
public abstract class BaseController extends Controller {
	
	public void index() {
		setAttr("ctx", getRequest().getContextPath());
		renderError(404);
	};
	
	@Override
	public void render(String view) {
		setAttr("systemConfig", StaticFactory.getSystemConfigMap());
		setAttr("ctx", getRequest().getContextPath());
		super.render(view);
	}
	
	/**
	 * 转换 json 格式输出
	 * @param statusCode 状态码
	 * @param message 返回信息
	 */
	public void toJson(Integer statusCode,String message){
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("statusCode", statusCode);
		if(message!=null) {
			jsonMap.put("message",message);
		}
		this.renderJson(jsonMap);
	}
}