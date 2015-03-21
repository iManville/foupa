package com.foupa.listener;

import com.foupa.core.Constant;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * session过期处理
 * 
 * @author Manville
 * @date 2014年11月30日 下午4:41:01
 */
public class WebSessionListener extends SessionListenerAdapter {

	/**
	 * seesion 结束时调用
	 */
	@Override
    public void onStop(Session session) {
		super.onExpiration(session);
		session.removeAttribute(Constant.SHIRO_LOGIN_USER); // session结束，移除用户
    }
	
	/**
	 * session过期处理
	 */
	@Override
	public void onExpiration(Session session) {
		super.onExpiration(session);
		session.removeAttribute(Constant.SHIRO_LOGIN_USER); // 移除旧的session
	}
	
}
