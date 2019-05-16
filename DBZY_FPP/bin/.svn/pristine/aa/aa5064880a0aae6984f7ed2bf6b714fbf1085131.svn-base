package com.nepharm.apps.fpp.biz.sem;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.sem.dao.TimeManagerDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 时限管理抓取数据发起流程定时器
 * 
 * @author XingJin
 *
 */
public class TimeManageSecondJob implements IJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 获得超时任务记录表中未处罚的数据
		List<BO> data = TimeManagerDao.getCSJLData();
		//通知时间（平台配置）
		String TZSJ = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "TZSJ");
		for (int i = 0; i < data.size(); i++) {
			if (TZSJ.indexOf(data.get(i).get("DATATYPE").toString())>=0) {
				// 触发超时通知提醒流程
				TimeManagerDao.CstztxProcess(data.get(i));
			} else {
				// 触发公司级奖惩流程
				TimeManagerDao.GsjjcProcess(data.get(i));
			}
		}

	}

}
