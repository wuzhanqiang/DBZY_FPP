package com.nepharm.apps.fpp.biz.pem.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.CreateDailyOutputData;


/**
 * 操作岗日产量同步MES及创建日产量维护信息
 * @author zhangjh
 *
 */
public class OperationPostDailyOutputJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
//		String param = SDK.getJobAPI().getJobParameter(context);
//		System.out.println("Hello AWS PaaS Job Demo! Param = " + param);
		
		//启动、初始化非操作岗流程数据
//		PerformanceAppraisalStartEvent.startNonQuantization();
		//同步MES日产量数据
		//创建日产量维护流程及数据
//		System.out.println("===================================1111");
		CreateDailyOutputData.createDailyOutputData(null);
		
	}

	
}
