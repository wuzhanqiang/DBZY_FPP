package com.nepharm.apps.fpp.biz.pem.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
/**
 * 定时启动、关闭 绩效填写流程 相关业务
 * @author lizhen
 */
public class PerformanceFillInEvent {
	/**
	 * 启动入口
	 */
	public static void startFillIn(){

		//一、查找启动人列表
		List<String> khrList=getKhrList();
		//二、启动人列表遍历。
		for(String uid:khrList){
			String userName=SDK.getRuleAPI().executeAtScript("@userName("+uid+")");
			//1)封装主表数据
			BO main=createMainBO(uid,userName);
			//2)查询 KPI参数数据（LIST<BO>）
			List<BO> sub=getKPIParamData(uid);
			//3)创建流程,初始化数据
			create(userName, uid, main, sub);
		}
	}

	/**
	 * 获取考核人列表
	 * @return
	 */
	private static List<String> getKhrList() {
		PerformanceDao dao = new PerformanceDao();
		List<String> list=dao.getKPIParamKHR();
		return list;
	}
	
	/**
	 * 封装主数据
	 * @param uid
	 * @return
	 */
	private static BO createMainBO(String uid,String userName) {
		BO main = new BO();
		main.set("DJBH",System.currentTimeMillis() );
		main.set("SQR",userName );
		main.set("SQRZH", uid);
		main.set("SQRQ",SDK.getRuleAPI().executeAtScript("@date"));
		main.set("BZ", "");
		main.set("ZT","0" );
		return main;
	}
	
	private static List<BO> getKPIParamData(String uid) {
		PerformanceDao dao = new PerformanceDao();
		List<BO> list = dao.getKPIParamData(uid);
		return list;
	}
	/**
	 * 启动流程、初始化数据
	 * @param userName
	 * @param userId
	 * @param main
	 * @param sub
	 */
	private static void create(String userName,String userId,BO main,List<BO> sub){
		//创建流程实例
		ProcessInstance pi=SDK.getProcessAPI().createProcessInstance(
				PerformanceConstant.PROCESS_JXGL_JXTX, userId,
				SDK.getRuleAPI().executeAtScript("@year")+"年"
				+SDK.getRuleAPI().executeAtScript("@month")+"月"
				+"-"+userName+"-"
				+"绩效填写流程");
		//启动这个流程实例
		SDK.getProcessAPI().start(pi);
		//创建数据
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXTX_M, 
				main,
				pi.getId(), 
				userId);
		//KPI指标数据，返回的boId就是字段子表的bindId
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXTX_S, 
				sub,
				pi.getId(), 
				userId);
	}
	
	/**
	 * 当手动、自动关闭任务是，回填数据，更新状态
	 * @param bindId
	 * @param zt
	 */
	public static void closeTaskBiz(String bindId,String zt){
		PerformanceDao dao = new PerformanceDao();
		dao.updateKPIParamListValue(bindId);
		dao.updateFillInStatus(bindId,zt);
	}
	
	/**
	 * 定时器关闭入口
	 */
	public static void closeFillIn(){
		PerformanceDao dao = new PerformanceDao();
		//查找isend=0，zt=0的主表数据（返回bindidlist即可）
		List<String> bindIdList=dao.getFillInProcessIds();
		for(String bindId:bindIdList){
			String taskId=SDK.getProcessAPI().getInstanceById(bindId).getStartTaskInstId();
			//插入一条审核留言
			//SDK.getTaskAPI().commitComment(taskId,  ConfigConstant.SYS_ADMIN, "超时关闭", SDK.getRuleAPI().executeAtScript("@datetime")+"未按时办结任务，系统自动关闭此任务！", true);
			SDK.getTaskAPI().commitComment(taskId, ConfigConstant.SYS_ADMIN, "<span style='color:red'>定时器操作</span>", "<span style='color:red'>未按时办结任务或定时触发的任务，系统自动执行关闭！</span>", true);
			//办结任务
			SDK.getTaskAPI().completeTask(taskId,  ConfigConstant.SYS_ADMIN);
			//更新状态为2,系统超时执行
			dao.updateFillInStatus(bindId,"2");
			
			//TODO 将子表涉及到 YID就是KPI的ID
			dao.updateKPIOverTimeZt(bindId);
			
			
			
		}
	}
	
}
