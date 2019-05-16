package com.nepharm.apps.fpp.is.k3.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.common.util.IsUtil;
import com.nepharm.apps.fpp.is.k3.util.K3Util;

public class K3Web extends ActionWeb {

	public K3Web() {
	}

	public K3Web(UserContext userContext) {
		super(userContext);
	}
	
	public String getUid() {
		return super.getContext().getUID();
	}
	
	public UserContext getUserContext() {
		return super.getContext();
	}
	
	public String getIndexPage() {
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		try {
//			String k3account = K3Util.loadK3Account();
			String k3account = getUid();
			UserContext userContext = getUserContext();
			String clientIp = userContext.getSessionIp();
			List<String> lanList = IsUtil.getLANIp();
			boolean ipState = IsUtil.checkIpState(lanList, clientIp);
			String url = K3Util.getSsoUrl(k3account, ipState);
			macroLibraries.put("sid",this.getContext().getSessionId());
			macroLibraries.put("url",url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".k3_sso_page.htm", macroLibraries);
	}
}
