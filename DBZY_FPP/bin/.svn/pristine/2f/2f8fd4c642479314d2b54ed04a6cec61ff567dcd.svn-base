package com.nepharm.apps.fpp.biz.pem.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.nepharm.apps.fpp.util.BOUtil;
/**
 * 定时器同步BO、View表结构
 * @author lizhen
 *
 */
public class SystemTableJob implements IJob{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		BOUtil.updateTabDic();
	}
}
