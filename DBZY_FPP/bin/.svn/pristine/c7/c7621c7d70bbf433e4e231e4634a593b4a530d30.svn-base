package com.nepharm.apps.fpp.biz.zbgl.event;

import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class ZbglSytz {

	public void sytz(String bindId, String year, String month, String week, 
			String syr, String syrzh, String sqrzh){
		PerformanceDao dao = new PerformanceDao();
		String taskId=dao.getTaskId(bindId);
		
		String title = "您有一封来自"+syr+"的"+year+"年"+month+"月第"+week+"周周报审阅通知，请查看！";
		SDK.getTaskAPI().createUserNotifyTaskInstance(bindId, taskId, syrzh, sqrzh, title);
	}
}
