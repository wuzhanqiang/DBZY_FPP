package com.nepharm.apps.fpp.is.ehr.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.common.util.IsUtil;
import com.nepharm.apps.fpp.is.ehr.constant.HRConstant;

import jpack.util.BASE64Encoder;

public class HRWeb extends ActionWeb {

	public HRWeb() {
	}

	public HRWeb(UserContext userContext) {
		super(userContext);
	}
	
	public String getEHRSSOPage() {
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		UserContext userContext = super.getContext();
		String uid = userContext.getUID();
		String usercode = DBSql.getString("select USER_CODE from "+HRConstant.TAB_JCXX_HRDDDBTB+" where PSNCODE = '"+uid+"'");
		String key = usercode;
		String url = getSSOUrl(key, usercode);
		macroLibraries.put("url", url);
		macroLibraries.put("usercode", usercode);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".hr_sso_page.htm", macroLibraries);
	}
	
	public String getSSOUrl(String key, String usercode) {
		BASE64Encoder encoder = new BASE64Encoder();
		UserContext userContext = super.getContext();
		String clientIp = userContext.getSessionIp();
		List<String> lanList = IsUtil.getLANIp();
		boolean ipState = IsUtil.checkIpState(lanList, clientIp);//true为内网
		String host = "";
		if(ipState) {
			host = ConfigConstant.HR_IN;
		}else {
			host = ConfigConstant.HR_OUT;
		}
		String urlAddress = host+"/service/RegisterServlet?";
		String content = "";
		try {
			content = "key="+encoder.encode(key.getBytes("UTF-8"))+
					"&usercode="+encoder.encode(usercode.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = urlAddress+content;
		return url;
	}
}
