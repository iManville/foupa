package com.foupa.controller.admin;

import com.foupa.controller.BaseController;
import com.foupa.core.Constant;
import com.foupa.model.WebConfig;
import com.foupa.service.admin.WebConfigService;

import com.jfinal.ext.render.DwzRender;
import com.jfinal.ext.route.ControllerBind;

/**
 * config 控制层
 * 
 * @author Manville
 * @date 2014年12月6日 下午7:46:47
 */
@ControllerBind(controllerKey = "/admin/webConfig")
public class WebConfigController extends BaseController {
	private WebConfigService webConfigService = new WebConfigService();
	
	public void list() {
		keepModel(WebConfig.class);
		WebConfig webConfig = getModel(WebConfig.class);
		
		int pageNumber = getParaToInt(Constant.DWZ_PAGE_NUMBER, 1);
		int pageSize = getParaToInt(Constant.DWZ_PAGE_SIZE, Constant.PAGESIZE);
		setAttr("datas", webConfigService.getConfigPage(pageNumber, pageSize, webConfig));
		render("list.html");
	}
	
	/**
	 * 跳转到添加or编辑页面
	 */
	public void toModifyPage() {
		String id = getPara();
		WebConfig webConfig = WebConfig.dao.findById(id);
		setAttr("webConfig", null != webConfig ? webConfig : "");
		
		render("form.html");
	}
	
	/**
	 * 更新 或 保存webConfig实例
	 */
	public void modify() {
		WebConfig webConfig = getModel(WebConfig.class);
		
		if (webConfigService.modify(webConfig)) {
			render(new DwzRender(webConfigService.getMessage(), "webConfigList", "closeCurrent"));
		} else {
			render(DwzRender.error(webConfigService.getMessage()));
		}
	}
	
	/**
	 * 批量删除webConfig
	 */
	public void delete() {
		String[] ids = getParaValues("ids");
		webConfigService.Deletes(ids);
		render(DwzRender.refresh("webConfigList"));
	}
}
