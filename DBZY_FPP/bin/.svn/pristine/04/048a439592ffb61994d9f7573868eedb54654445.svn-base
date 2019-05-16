package com.nepharm.apps.fpp.is.common.controller;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp.is.common.util.IsUtil;
import com.nepharm.apps.fpp.is.common.web.IsWeb;

/**
 * 集成系统通用Controller
 * @author sydq
 *
 */
@Controller
public class IsController {
	@Mapping("com.nepharm.apps.fpp.is.ip_state")
	public String getState() {
		ResponseObject ro = ResponseObject.newOkResponse();
		IsWeb web = new IsWeb();
		String ipState = "";
		UserContext userContext = web.getUserContext();
		String clientIp = userContext.getSessionIp();
		List<String> lanList = IsUtil.getLANIp();
		if(IsUtil.checkIpState(lanList, clientIp)) {
			ipState = "in";
		}else {
			ipState = "out";
		}
		ro.put("ipState", ipState);
		return ro.toString();
	}
}
