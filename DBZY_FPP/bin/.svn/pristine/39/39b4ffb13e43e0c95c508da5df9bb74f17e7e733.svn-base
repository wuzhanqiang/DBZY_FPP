package com.nepharm.apps.fpp.biz.pem.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.PerformanceEvent;

/**
 * 绩效考核流程节点办结定时器
 * 
 * @author lizhen
 *
 */
public class PerformanceStepJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//获取step参数 non1
		String param = SDK.getJobAPI().getJobParameter(context);
		if(param==null||"".equals(param)){
			return ;
		}
//		PerformanceDao dao = new PerformanceDao();
		switch (param) {
		//绩效考核流程（非量化）第一节点
		case "non1":
			PerformanceEvent.closeStep("(系统");
			break;
		case "non2":
			PerformanceEvent.closeStep("(被考核人");
			break;
		case "non3":
			PerformanceEvent.closeStep("(领导");
			break;
		}
		
	}

	
}
