package com.nepharm.apps.fpp.biz.pem.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.nepharm.apps.fpp.biz.pem.event.PerformanceFillInEvent;

/**
 * 绩效填写流程定时关闭器
 * @author lizhen
 *
 */
public class PerformanceFillInCloseJob implements IJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("开始关闭考核填写流程");
		PerformanceFillInEvent.closeFillIn();
		System.out.println("结束关闭考核填写流程");
	}

}
