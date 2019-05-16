package com.nepharm.apps.fpp.is.k3.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.CreateDailyOutputData;
import com.nepharm.apps.fpp.is.k3.dao.K3BusinessDataDao;


/**
 * 同步K3数据
 * @author zhangjh
 *
 */
public class SynchronousK3BusinessDataJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		K3BusinessDataDao k3BusinessDataDao = new K3BusinessDataDao();
		k3BusinessDataDao.getK3BusinessData(null, null,0,"admin" );
		k3BusinessDataDao = null;
	}

	
}
