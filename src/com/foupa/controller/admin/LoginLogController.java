package com.foupa.controller.admin;

import com.jfinal.ext.route.ControllerBind;

import com.foupa.controller.BaseController;
import com.foupa.core.Constant;
import com.foupa.model.User;
import com.foupa.service.admin.LoginLogService;
import com.foupa.util.SqlParaUtils;

/**
 * loginLog 控制层
 * 
 * @author Manville
 * @date 2014年12月5日 上午10:05:56
 */
@ControllerBind(controllerKey = "/admin/loginLog")
public class LoginLogController extends BaseController {
	private LoginLogService loginLogService = new LoginLogService();
	
	public void list() {
		keepModel(User.class);
		User user = getModel(User.class);
		String startTime = SqlParaUtils.startTime(getPara("startTime"), getPara("endTime"));
		String endTime = SqlParaUtils.endTime(getPara("startTime"), getPara("endTime"));
		int pageNumber = getParaToInt(Constant.DWZ_PAGE_NUMBER, 1);
		int pageSize = getParaToInt(Constant.DWZ_PAGE_SIZE, Constant.PAGESIZE);
		setAttr("datas", loginLogService.getLoginLogPage(pageNumber, pageSize, user, startTime, endTime));
		render("list.html");
	}

	
}
