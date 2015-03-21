package com.foupa.controller.admin;

import com.foupa.controller.BaseController;
import com.foupa.core.Constant;
import com.foupa.model.Message;
import com.foupa.service.admin.MessageService;
import com.foupa.util.SqlParaUtils;

import com.jfinal.ext.render.DwzRender;
import com.jfinal.ext.route.ControllerBind;

/**
 * message 控制层
 * 
 * @author Manville
 * @date 2014年12月16日 下午2:45:28
 */
@ControllerBind(controllerKey = "/admin/message")
public class MessageController extends BaseController {
	
	private MessageService messageService =  new MessageService();

	/**
	 * 获取Message列表
	 */
	public void list() {
		keepModel(Message.class);
		Message message = getModel(Message.class);
		String startTime = SqlParaUtils.startTime(getPara("startTime"), getPara("endTime"));
		String endTime = SqlParaUtils.endTime(getPara("startTime"), getPara("endTime"));
		int pageNumber = getParaToInt(Constant.DWZ_PAGE_NUMBER, 1);
		int pageSize = getParaToInt(Constant.DWZ_PAGE_SIZE, Constant.PAGESIZE);
		
		setAttr("datas", messageService.getMessagePage(pageNumber, pageSize, message, startTime, endTime));
		
		render("list.html");
	}
	

	/**
	 * 跳转到回复页面
	 */
	public void toReplayPage() {
		String id = getPara();
		setAttr("message", null != id ? messageService.getById(id) : "");
		setAttr("replys", null != id ? messageService.getReplyByPid(id) : "");
		
		render("form.html");
	}
	
	/**
	 * 回复Message(添加一条Message作为Reply)
	 */
	public void reply() {
		Message reply = getModel(Message.class);
		
		if (messageService.modifyReply(reply)) {
			render(DwzRender.refresh("messageReply"));
		} else {
			render(DwzRender.error());
		}
	}
	
	/**
	 * 批量删除Message留言及其回复
	 */
	public void deletes() {
		String[] ids = getParaValues("ids");
		messageService.deleteMessages(ids);
		render(DwzRender.refresh("messageList"));
	}
	
	/**
	 * 删除单条留言 或 回复
	 */
	public void deleteReply() {
		String id = getPara();
		Message.dao.deleteById(id);
		render(DwzRender.refresh("messageReply"));
	}
	
}
