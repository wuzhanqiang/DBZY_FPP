package com.nepharm5.apps.fpp.nepg.biz.zbgl;

import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

@Controller
public class ZBLCajaxGetUrl {
	
	@Mapping("com.nepharm5.apps.fpp.nepg.biz.zbgl.ZBLCajaxGetUrl")
	public String getUrl(String sid, String processInstId, String formDefId, String boId) {
		// TODO Auto-generated constructor stub
		String url = "";
		PerformanceDao dao = new PerformanceDao();
        String taskId=dao.getTaskId(processInstId);

        url = SDK.getFormAPI().getFormURL("", sid, processInstId, taskId, 2, formDefId, boId, "");
		
		return url;
	}

}
