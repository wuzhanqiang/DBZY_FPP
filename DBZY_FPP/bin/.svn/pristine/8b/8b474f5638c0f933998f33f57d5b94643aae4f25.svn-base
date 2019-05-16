package com.nepharm.apps.fpp.biz.kms.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.kms.constant.KMSConstant;
import com.nepharm.apps.fpp.constant.ConfigConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyKMSWeb extends ActionWeb {

	public MyKMSWeb() {
	}

	public MyKMSWeb(UserContext userContext) {
		super(userContext);
	}
	
	public String getMyKMSIndexPage() {
		String sid = super.getContext().getSessionId();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms_index.htm", macroLibraries);
	}
	
	public String getMyKMSPortalPage() {
		String sid = super.getContext().getSessionId();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms_portal.htm", macroLibraries);
	}
	
	public String getFilePreviewPage(String url) {
		String sid = super.getContext().getSessionId();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("url", url);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms_filepreviewpage.htm", macroLibraries);
	}
	
	public String getDepartmentPathId() {
		return getContext().getDepartmentModel().getPathIdOfCache(); 
	}
	
	public String getUid() {
		return getContext().getUID();
	}
	
	public UserContext getUserContext() {
		return getContext();
	}
}
