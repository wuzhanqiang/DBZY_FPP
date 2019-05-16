package com.nepharm.apps.fpp.is.k3.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.CreateDailyOutputData;

import com.nepharm.apps.fpp.is.ehr.util.HRUtil;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;


/**
 * 同步K3费用项目信息
 * @author zhangjh
 *
 */
public class SynchronousCostItemJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		HRUtil hrutil = new HRUtil();
		StringBuffer msg = new StringBuffer();
		try {
			hrutil.synchronousTheThirdPartyData(SynchronousK3Constant.TAB_JCXX_FYXMWH,msg);
			System.out.println(msg.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
