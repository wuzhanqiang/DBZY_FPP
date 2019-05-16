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
import com.actionsoft.exception.AWSExpressionException;
import com.actionsoft.sdk.local.SDK;
import com.ibm.db2.jcc.am.sd;

public class ZbglStartJob implements IJob {

	public ZbglStartJob() {
	}

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		System.out.println("==========周报上报启动 定时器 START==========");
		String year = SDK.getRuleAPI().executeAtScript("@year");
		String month = SDK.getRuleAPI().executeAtScript("@month");
		int week = 0;
		String rq1 = "";
		String rq2 = "";
		String rq3 = "";
		String rq4 = "";
		String rq5 = "";
		
		try {
			/*SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = sdf.parse(SDK.getRuleAPI().executeAtScript("@date"));
		    Calendar calendar = Calendar.getInstance();  
		    calendar.setTime(date);  
		    //第几周  
		    week = calendar.get(Calendar.WEEK_OF_MONTH); */
		    //week = 3;
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置日期
			String today = SDK.getRuleAPI().executeAtScript("@date");
			Date d = sdf.parse(today);
			c.setTime(d);
			//周六启动，周一加两天
	        c.add(Calendar.DAY_OF_MONTH, 2);// 今天+2天 周一
	        Date w1 = c.getTime();
	        c.setTime(d);
	        c.add(Calendar.DAY_OF_MONTH, 3);// 今天+3天 周二
	        Date w2 = c.getTime();
	        c.setTime(d);
	        c.add(Calendar.DAY_OF_MONTH, 4);// 今天+4天 周三
	        Date w3 = c.getTime();
	        c.setTime(d);
	        c.add(Calendar.DAY_OF_MONTH, 5);// 今天+5天 周四
	        Date w4 = c.getTime();
	        c.setTime(d);
	        c.add(Calendar.DAY_OF_MONTH, 6);// 今天+6天 周五
	        Date w5 = c.getTime();
			
			//设置周一为一周的开始
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setTime(w1);
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
			//计算周报上报周期
			rq1 = sdf.format(w1);
			rq2 = sdf.format(w2);
			rq3 = sdf.format(w3);
			rq4 = sdf.format(w4);
			rq5 = sdf.format(w5);
			
		} catch (AWSExpressionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//查询需要上报人员名单列表
		List<BO> mdList = SDK.getBOAPI().query("BO_DY_ZBSB_RYXX_ZB").list();
		for(int i=0;i<mdList.size();i++){
			
			//创建流程
			String processDefId = "obj_15470a0af8f34fbda7ce1b88baeb2ba0";
			String uid = mdList.get(i).getString("SQRZH");
			String username = mdList.get(i).getString("SQR");
			String title = username+" "+year+"年"+month+"月第"+week+"周周报";
			//判断账号是否为空
			if(uid != null && !"".equals(uid)){
				
				ProcessInstance p =  SDK.getProcessAPI().createProcessInstance(processDefId, "admin", title);
				SDK.getProcessAPI().start(p);
				String bindId = p.getId();
				String taskId = p.getStartTaskInstId();
				//创建任务
				//SDK.getTaskAPI().createUserTaskInstance(bindId, null, "admin", "obj_c808a5ccc3c0000133b211922692a120", uid, title);
				
				BO mainBoData = new BO();
				mainBoData.set("SQR", username);
				mainBoData.set("SQRZH", uid);
				mainBoData.set("GSMC", SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSMC)"));
				mainBoData.set("GSBM", SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSBM)"));
				mainBoData.set("YEAR", year);
				mainBoData.set("MONTH", month);
				mainBoData.set("WEEK", week);
				mainBoData.set("RQ1", rq1);
				mainBoData.set("RQ2", rq2);
				mainBoData.set("RQ3", rq3);
				mainBoData.set("RQ4", rq4);
				mainBoData.set("RQ5", rq5);
				mainBoData.set("KSRQ", rq1);
				mainBoData.set("JSRQ", rq5);
				
				SDK.getBOAPI().create("BO_DY_ZBSB_M", mainBoData, bindId, uid);
				
				//结束任务继续办理
				SDK.getTaskAPI().completeTask(taskId, "admin", true);
			}
		}
		System.out.println("==========周报上报启动 定时器 END==========");
	}

}
