package com.nepharm.apps.fpp.demo;

import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.AppAPI;

@Controller
public class DemoController {
	@Mapping(value="com.nepharm.apps.fpp.demo_get_data",
				session = false,
				noSessionEvaluate = "无安全隐患",
				noSessionReason = "用于MVC框架稳定性测试")
	public String getData(String uid,String value){
		//http://localhost:8088/portal/r/w?cmd=com.nepharm.apps.fpp.demo_get_data&uid=admin2&value=123456
		//SDK.getTaskAPI().createUserNotifyTaskInstance(null, null, "admin", uid, "新到的任务");
		ResponseObject result = ResponseObject.newOkResponse();
	    try
	    {
	      String aslp = "aslp://com.actionsoft.apps.notification/sendMessage";
	      Map<String, Object> params = new HashMap<String, Object>();
	      AppAPI appAPI = SDK.getAppAPI();
	      params.put("sender", "");
	      params.put("targetIds", uid);
	      params.put("content", value);
	      params.put("level", "info");
	      params.put("systemName", "通知中心");
	      result = appAPI.callASLP(appAPI.getAppContext("com.actionsoft.apps.notification"), aslp, params);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      result.err(e.getMessage());
	    }
	    return result.toString();
		//return uid+" and "+value;
	}
}
