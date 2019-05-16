package com.nepharm.apps.fpp.biz.jcm.web;

import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class JCMWeb extends ActionWeb {

	public JCMWeb() {
	}

	public JCMWeb(UserContext userContext) {
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
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_index.htm", macroLibraries);
	}
//	/**
//	 * 获取PC端页面
//	 * @return
//	 */
//	public String getMyResourceIndexPage() {
//		String sid = super.getContext().getSessionId();
//		UserContext userContext = getContext();
//		String userId = userContext.getUID();
//		Map<String, Object> macroLibraries = new HashMap<String, Object>();
//		macroLibraries.put("sid", sid);
//		macroLibraries.put("userId", userId);
//		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".my_resource_index_page.htm", macroLibraries);
//	}
//	/**
//	 * 获取移动端页面
//	 * @return
//	 */
//	public String getMyResourceIndexPage_M() {
//		String sid = super.getContext().getSessionId();
//		UserContext userContext = getContext();
//		String userId = userContext.getUID();
//		Map<String, Object> macroLibraries = new HashMap<String, Object>();
//		macroLibraries.put("sid", sid);
//		macroLibraries.put("userId", userId);
//		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".my_resource_index_page_m.htm", macroLibraries);
//	}
//	
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

	public String getView(String pzbm) {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		macroLibraries.put("pzbm", pzbm);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_view.htm", macroLibraries);
	}

	public String getTkList() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_tk_list.htm", macroLibraries);
	}

	public String getTkAdd(String bindId) {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		macroLibraries.put("bindId", bindId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_tk_add.htm", macroLibraries);
	}

	public String getFlsz() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_flsz.htm", macroLibraries);
	}

	public String getDemo() {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, "demo.htm", macroLibraries);
	}

	public String getShow(String bindId) {
		String sid = super.getContext().getSessionId();
		UserContext userContext = getContext();
		String userId = userContext.getUID();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("userId", userId);
		macroLibraries.put("bindId", bindId);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".jcm_tk_show.htm", macroLibraries);
	}
}
