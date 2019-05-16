package com.nepharm.apps.fpp.biz.jcm.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.nepharm.apps.fpp.biz.jcm.util.JCMUtil;

public class PunishmentTaskJob implements IJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JCMUtil.punishmentTaskExecute();
	}

}
