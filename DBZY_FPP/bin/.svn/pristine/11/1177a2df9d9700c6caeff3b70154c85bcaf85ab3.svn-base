package com.nepharm.apps.fpp.biz.zbgl.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSAPIException;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.AWSExpressionException;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.ProcessExecuteQuery;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class ZbglEndJob implements IJob {

	public ZbglEndJob() {
	}

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		System.out.println("==========周报上报结束 定时器 START==========");
		int week = 0;
		try {
			/*SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = sdf.parse(SDK.getRuleAPI().executeAtScript("@date"));
		    Calendar calendar = Calendar.getInstance();  
		    calendar.setTime(date);  
		    //第几周  
		    week = calendar.get(Calendar.WEEK_OF_MONTH); */
			
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置日期
			String today = SDK.getRuleAPI().executeAtScript("@date");
			Date d = sdf.parse(today);
			c.setTime(d);
			
			//设置周一为一周的开始
			c.setFirstDayOfWeek(Calendar.MONDAY);
			
			week = c.get(Calendar.WEEK_OF_MONTH);
			//获取本月第一天
			c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			String firstDay = sdf.format(c.getTime());
			Date firstday = sdf.parse(firstDay);
			c.setTime(firstday);
			//获取周几
			int w = c.get(Calendar.DAY_OF_WEEK) -1 ;
			if(w != 1){
				week = week -1;
			}
		} catch (AWSExpressionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		String year = SDK.getRuleAPI().executeAtScript("@year");
		String month = SDK.getRuleAPI().executeAtScript("@month");
		//查询周报上报流程的流程是否结束
		List<BO> list = SDK.getBOAPI().query("BO_DY_ZBSB_M").addQuery("ISEND = ", 0).list();
		for(BO bo:list){
			
			try {
				String bindId = bo.getBindId();
				PerformanceDao dao = new PerformanceDao();
				String taskId=dao.getwfcTaskId(bindId);
				
				bo.set("TJSJ", SDK.getRuleAPI().executeAtScript("@datetime"));
				SDK.getBOAPI().update("BO_DY_ZBSB_M", bo);
				//SDK.getProcessAPI().terminateById(bindId, "admin");
				//定义复活的任务id
				//String parentTaskId = DBSql.getString("select PARENTTASKINSTID from WFC_TASK where ID='"+taskId+"'");
				//结束任务继续办理
				ProcessExecuteQuery peq = SDK.getTaskAPI().completeTask(taskId, "admin", true);
				//System.out.println(peq);
				//如果该流程是复活流程，则走第二次任务办理
				/*if("00000000-0000-0000-0000-000000000000".equals(parentTaskId)){
					String taskIdnew = DBSql.getString("select ID from WFC_TASK where PARENTTASKINSTID='"+taskId+"'");
					SDK.getTaskAPI().completeTask(taskIdnew, "admin", true);
				}*/
			} catch (AWSExpressionException e) {
				// TODO Auto-generated catch block
				System.out.println(bo.getString("SQR")+"第"+week+"周周报收回错误！");
				e.printStackTrace();
			} catch (AWSDataAccessException e) {
				// TODO Auto-generated catch block
				System.out.println(bo.getString("SQR")+"第"+week+"周周报收回错误！");
				e.printStackTrace();
			} catch (BPMNError e) {
				// TODO Auto-generated catch block
				System.out.println(bo.getString("SQR")+"第"+week+"周周报收回错误！");
				e.printStackTrace();
			} catch (AWSAPIException e) {
				// TODO Auto-generated catch block
				System.out.println(bo.getString("SQR")+"第"+week+"周周报收回错误！");
				e.printStackTrace();
			}
		}
		System.out.println("==========周报上报结束 定时器 END==========");
		
	}

}
