package com.nepharm.apps.fpp.biz.ppm.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.util.DateUtil;

/**
 * 周生产计划-判断子表是否为空，为空初始化子表数据
 * @author lizhen
 */
public class WeekPlanFormAfterSaveEvent extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		UserContext uc=context.getUserContext();//当前办理者信息
		
		String boName=ProductPlanConstant.TAB_SCJH_ZSCJH_M;
		String boSubName=ProductPlanConstant.TAB_SCJH_ZSCJH_S;
		
		String bindId=context.getProcessInstance().getId();
		String boId=DBSql.getString("select ID from "+boName+" where bindid='"+bindId+"'");//获取主表boID
		
		int num = getSubNum(bindId,boSubName);
		
		if(num !=0){
			return ;
		}
		
		BO bo=SDK.getBOAPI().get(boName, boId);
		String kssj=bo.getString("KSSJ");
		
		
		insertSubData(uc,boSubName,bindId,kssj);
		
		
	}
	private void insertSubData(UserContext uc,String boSubName, String bindId, String kssj) throws Exception {
		Date startDate= DateUtil.string2UtilDate(kssj);//开始时间
		
		List<BO> list = new ArrayList<BO>();
		/*循环七天*/
		for(int i=0;i<7;i++){
			Date date=DateUtil.getNextDate(startDate, i);//日期
			String weekDay =DateUtil.getWeekOfDate(date);//星期几
			BO bo =new BO();
			bo.set("RQ", DateUtil.getStringDate(date));
			bo.set("XQ", weekDay);
			bo.set("GZZT", "工作");
			bo.set("FJGZ", "连续运转");
			bo.set("JNTCDW", "万粒");
			if(i==6){
				bo.set("GZZT", "休息");
				bo.set("FJGZ", "无");
			}else{
				bo.set("GZZT", "工作");
				bo.set("FJGZ", "连续运转");
				bo.set("JNTCDW", "万粒");
			}
			list.add(bo);
		}
		
		SDK.getBOAPI().create(boSubName, list, bindId, uc.getUID());
	}
	/**
	 * 获取指定表行数
	 * @param bindId
	 * @param boName
	 * @return
	 */
	private static int getSubNum(String bindId,String boName){
		int num=SDK.getBOAPI().query(boName).bindId(bindId).list().size();
		//System.out.println(num);
		return num;
	}
	
	@Override
	public String getDescription() {
		return "周生产计划 -保存后事件";
	}
	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
}
