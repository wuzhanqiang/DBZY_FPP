package com.nepharm.apps.fpp.biz.pem.job;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.design.cache.BOCache;
import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.design.model.BOModel;
import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.PerformanceAppraisalStartEvent;
import com.nepharm.apps.fpp.util.BOUtil;

/**
 * 绩效考核流程启动定时器
 * @author lizhen
 *
 */
public class PerformanceStartJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//启动、初始化非操作岗流程数据
		PerformanceAppraisalStartEvent.startNonQuantization();
		//启动、初始化操作岗流程数据
		PerformanceAppraisalStartEvent.startQuantization();
		
//		BOUtil.updateTabDic();
//		String param = SDK.getJobAPI().getJobParameter(context);
//		System.out.println("Hello AWS PaaS Job Demo! Param = " + param);
	}

	
}
