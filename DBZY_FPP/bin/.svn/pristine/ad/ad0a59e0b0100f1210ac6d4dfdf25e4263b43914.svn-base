package com.nepharm.apps.fpp.common.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.commons.log.auditing.Auditor;
import com.actionsoft.bpms.schedule.IJob;

public class RZtestJob implements IJob {

	public RZtestJob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.err.println("LgConf="+Auditor.getLgConf().isService());

	}

}
