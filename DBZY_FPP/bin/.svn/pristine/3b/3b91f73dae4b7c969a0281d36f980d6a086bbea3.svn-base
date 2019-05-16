package com.nepharm.apps.fpp.is.ehr.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.event.CreateDailyOutputData;
import com.nepharm.apps.fpp.is.ehr.constant.SynchronousHRConstant;
import com.nepharm.apps.fpp.is.ehr.util.HRUtil;


/**
 * 同步HR人员信息
 * @author zhangjh
 *
 */
public class SynchronousPersonJob implements IJob  {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		HRUtil hrutil = new HRUtil();
		StringBuffer msg = new StringBuffer();
		try {
			hrutil.synchronousTheThirdPartyData(SynchronousHRConstant.TAB_JCXX_HRRYXXTB,msg);
			System.out.println(msg.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
