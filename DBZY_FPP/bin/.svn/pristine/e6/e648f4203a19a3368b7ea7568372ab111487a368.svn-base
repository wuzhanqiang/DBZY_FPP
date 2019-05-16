package com.nepharm.apps.fpp.biz.zbgl.web;

import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class ZBGLWeb extends ActionWeb {

	public ZBGLWeb() {
	}

	public ZBGLWeb(UserContext userContext) {
		super(userContext);
	}
	/**
	 * 首页面跳转
	 * @return
	 */
	public String getIndexPage() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".zbgl_index.htm", macroLibraries);
	}
	/**
	 * 获取当前登录用户id
	 * @return
	 */
	public String getUserId() {
		UserContext userContext = getContext();
		return userContext.getUID();
	}
	
	/**
	 * 获取当前登录用户NAME
	 * @return
	 */
	public String getUserName() {
		UserContext userContext = getContext();
		return userContext.getUserName();
	}

}
