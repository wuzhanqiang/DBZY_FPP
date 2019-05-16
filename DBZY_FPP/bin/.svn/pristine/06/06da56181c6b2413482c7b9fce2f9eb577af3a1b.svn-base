package com.nepharm.apps.fpp.biz.pem.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.nepharm.apps.fpp.biz.pem.event.PerformanceFillInEvent;

/**
 * 绩效填写流程定时启动器
 * @author lizhen
 *
 */
public class PerformanceFillInStartJob implements IJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("开始执行考核填写流程");
		PerformanceFillInEvent.startFillIn();
		System.out.println("结束执行考核填写流程");
	}

}
