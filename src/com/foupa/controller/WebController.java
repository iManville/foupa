package com.foupa.controller;

import com.foupa.util.StaticFactory;

import com.jfinal.core.Controller;

public class WebController extends Controller{
	
	@Override
	public void render(String view) {
		setAttr("ctx", getRequest().getContextPath());
		setAttr("systemConfig", StaticFactory.getSystemConfigMap());
		/** header */
		setAttr("myProject", StaticFactory.getMyProjectList());
		/** footer */
		setAttr("friendlyLink", StaticFactory.getFriendlyLinkList());
		super.render(view);
	}
}
