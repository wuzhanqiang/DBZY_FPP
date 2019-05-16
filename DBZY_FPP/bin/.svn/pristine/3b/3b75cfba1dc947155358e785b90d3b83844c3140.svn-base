package com.nepharm.apps.fpp.biz.mr.web;

import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class MRWeb extends ActionWeb {

	public MRWeb() {
	}

	public MRWeb(UserContext userContext) {
		super(userContext);
	}
	/**
	 * 获取portal页面
	 * @return
	 */
	public String getMyResourceIndexPage_P() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".my_resource_index_page_p.htm", macroLibraries);
	}
	/**
	 * 获取PC端页面
	 * @return
	 */
	public String getMyResourceIndexPage() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".my_resource_index_page.htm", macroLibraries);
	}
	/**
	 * 获取移动端页面
	 * @return
	 */
	public String getMyResourceIndexPage_M() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".my_resource_index_page_m.htm", macroLibraries);
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public String getUserId() {
		UserContext userContext = getContext();
		return userContext.getUID();
	}
}
