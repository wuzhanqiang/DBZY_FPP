package com.nepharm.apps.fpp.is.bpm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.common.util.IsUtil;

public class BPMWeb extends ActionWeb {

	public BPMWeb() {
	}

	public BPMWeb(UserContext userContext) {
		super(userContext);
	}
	
	public String getIndexPage() {
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		UserContext userContext = super.getContext();
		String userId = userContext.getUID();
		String clientIp = userContext.getSessionIp();
		List<String> lanList = IsUtil.getLANIp();
		boolean ipState = IsUtil.checkIpState(lanList, clientIp);
		String host = "";
		if(ipState){
			host = ConfigConstant.BPM5_IN;
		}else{
			host = ConfigConstant.BPM5_OUT;
		}
		String url = host+"/workflow/?cmd=Login&userid="+userId+"&PORTAL_LANG=cn&UserCert=FPP";
		macroLibraries.put("sid",userContext.getSessionId());
		macroLibraries.put("url",url);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".bpm_sso_page.htm", macroLibraries);
	}
}
