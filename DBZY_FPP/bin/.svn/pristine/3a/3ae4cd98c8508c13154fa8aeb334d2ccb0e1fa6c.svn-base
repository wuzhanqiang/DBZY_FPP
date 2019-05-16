package com.nepharm.apps.fpp.biz.sem;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.schedule.IJob;
import com.nepharm.apps.fpp.biz.sem.dao.TimeManagerDao;

/**
 * 时限管理收集数据定时器
 * 
 * @author xingJin
 *
 */
public class TimeManageFirstJob implements IJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		List<BO> data = new ArrayList<BO>();
		// 获取任务超时记录表中所需的数据
		data = TimeManagerDao.getData();
		// 将日志表中的数据插入超时任务记录表中
		TimeManagerDao.insert(data);
	}

}
