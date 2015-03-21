package com.foupa.controller.admin;

import com.foupa.controller.BaseController;
import com.foupa.model.Resc;
import com.foupa.model.Role;
import com.foupa.service.admin.DictionaryService;
import com.foupa.service.admin.RoleRescService;

import com.jfinal.ext.render.DwzRender;
import com.jfinal.ext.route.ControllerBind;

/**
 * RoleResc 控制层
 * 
 * @author Manville
 * @date 2014年12月4日 下午5:44:49
 */
@ControllerBind(controllerKey = "/admin/roleresc")
public class RoleRescController extends BaseController {
	private RoleRescService roleRescService = new RoleRescService();
	private DictionaryService dictionaryService = new DictionaryService();

	/**
	 * 读取角色和权限信息
	 */
	public void list() {
		setAttr("roles", roleRescService.getRoleList());
		setAttr("rescs", roleRescService.getRescList());
		render("list.html");
	}

	/**
	 * 得到权限树形结构
	 */
	public void getTree() {
		renderText(roleRescService.getRescTree());
	}

	/**
	 * 获得role的resc
	 */
	public void getRescByRole() {
		Integer id = getParaToInt();
		if (null == id) {
			renderText("-0-");
		} else {
			renderText(roleRescService.getRescByRoleId(id));
		}
	}

	/**
	 * 保存role对应的resc
	 */
	public void saveResc() {
		String roleId = getPara("roleId");
		String rescId = getPara("rescId");
		if (roleRescService.saveRescByRoleId(roleId, rescId)) {
			renderText("1");
		} else {
			renderText("0");
		}
	}

	/**
	 * 进入修改或添加resc界面
	 */
	public void toModifyRescPage() {
		Integer id = getParaToInt();
		setAttr("resc", null != id ? Resc.dao.findById(id) : "");
		setAttr("rescs", roleRescService.getRescList());
		setAttr("functionTypes", dictionaryService.getDictionaryByCode("functionType"));
		render("rescForm.html");
	}

	/**
	 * 修改或保存resc
	 */
	public void modifyResc() {
		Resc resc = getModel(Resc.class);
		roleRescService.modifyResc(resc);
		render(DwzRender.closeCurrentAndRefresh("rolerescList"));
	}

	/**
	 * 进入修改或添加role界面
	 */
	public void toModifyRolePage() {
		Integer id = getParaToInt();
		setAttr("role", null != id ? Role.dao.findById(id) : "");
		render("roleForm.html");
	}

	/**
	 * 修改或保存role
	 */
	public void modifyRole() {
		Role role = getModel(Role.class);
		roleRescService.modifyRole(role);
		render(DwzRender.closeCurrentAndRefresh("rolerescList"));
	}

	/**
	 * 批量启用(停用)role
	 */
	public void enableOrDisable() {
		String[] ids = this.getParaValues("ids");
		roleRescService.enableOrDisable(ids);
		render(DwzRender.success(roleRescService.getMessage()));
	}

}
