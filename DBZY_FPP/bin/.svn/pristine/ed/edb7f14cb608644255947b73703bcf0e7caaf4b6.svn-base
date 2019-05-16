package com.nepharm.apps.fpp.portal.controller;

import net.sf.json.JSONObject;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.org.model.UserModel;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.server.fs.impl.PhotoProcessor;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.PortalAPI;

/**
 * 门户 内容 信息
 * @author lizhen
 *
 */
@Controller
public class PortalController {
	
	/**
	 * 我的工作台
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.workbox_index_page")
	public String getWorkBoxPage(String sid,UserContext me){
		
		PortalWeb web = new PortalWeb(me); 
		return web.getWorkBoxPage();

	}
	/**
	 * 工作台中的待办任务数据
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.workbox_task_data")
	public String getWorkBoxData(String sid,UserContext me){
		
		PortalWeb web = new PortalWeb(me); 
		return web.getWorkBoxData();

	}
	
	/*
	@Mapping("com.nepharm.apps.demo.assistant_index_page")
	public String getAssPage(String sid,UserContext me){
		
		PortalWeb web = new PortalWeb(me); 
		//int i=ActiveNotificationMessageCache.getCountsOfAppId(ConfigConstant.APP_ID);
		//int i=ActiveNotificationMessageCache.getCountsOfTarget(me.getUID());
		//System.out.println(i+"----------消息中心缓存--------");
		return web.getAssPage();

	}
	
	
	*/
	@Mapping("com.nepharm.apps.fpp.portal_main_page")
	public String getMainFramePage(String sid,UserContext me){
		PortalWeb web = new PortalWeb(me); 
		return web.getMainFramePage();
	}
	@Mapping("com.nepharm.apps.fpp.personal_data")
	public String getPersonalData(String sid,UserContext me){
		PortalAPI portalApi = SDK.getPortalAPI();
		ResponseObject result = ResponseObject.newOkResponse();
		UserModel userModel = me.getUserModel();
		String gsmc=SDK.getRuleAPI().executeAtScript("@getUserInfo("+me.getUID()+",GSMC)");
		String gwmc=SDK.getRuleAPI().executeAtScript("@getUserInfo("+me.getUID()+",GWMC)");
		String bmmc=me.getDepartmentModel().getName();
		if(gsmc.length()>12){
			gsmc=gsmc.substring(0,12);
		}
		if(gwmc.length()>12){
			gwmc=gwmc.substring(0,12);
		}
		if(bmmc.length()>12){
			bmmc=bmmc.substring(0,12);
		}
		result.put("gsmc",gsmc );
		result.put("gwmc", gwmc);
		result.put("bmmc", bmmc);
		result.put("uid", userModel.getUID());
		result.put("userName", userModel.getUserName());
		result.put("userPhoto", portalApi.getUserPhoto(me, me.getUID()));
		result.put("userPhotoTmp", PhotoProcessor.getTmpPhotoUrl(me, me.getUID()));
		return result.toString();
	}
	
	/**
	 * 门户-导航搜索功能
	 * 根据菜单名字，匹配菜单列表信息
	 * 返回 text—value形式的json对象
	 * @param sid
	 * @param me
	 * @param nav  菜单名
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.portal_nav_live_search")
	public String getNavLiveSearchInfo(String sid,UserContext me,String query){
		PortalWeb web = new PortalWeb(me);
		return web.getNavLiveSearchInfoList(query);
	}
}
