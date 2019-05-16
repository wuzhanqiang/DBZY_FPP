package com.nepharm.apps.fpp.biz.bd.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.ProcessDefinition;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class ProcessNameJob implements IJob {

	public ProcessNameJob() {
	}
	
	private static String tabDeleteSQL="delete from BO_DY_PROCESS";
	private static String tabquerySQL="SELECT DISTINCT(PROCESSDEFID) "
				+ "from (SELECT PROCESSDEFID FROM WFC_TASK "
				+ "UNION all SELECT PROCESSDEFID from WFH_TASK)";
	private static String tabquerySQL2="select DISTINCT(PROCESSINSTID) as PROCESSINSTID "
			+ "from WFC_TASK union "
			+ "select DISTINCT(PROCESSINSTID) as PROCESSINSTID "
			+ "from WFH_TASK ";
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		System.out.println("==========更新 流程名称 定时器 START==========");
		System.out.println("SQL1->"+tabquerySQL);
		System.out.println("SQL2->"+tabquerySQL2);
		Connection conn = DBSql.open();
		Connection conn2 = DBSql.open();
		Statement st = null;
		Statement st2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(tabquerySQL);
			System.out.println("执行SQL1");
			List<BO> list = new ArrayList<BO>();
			while(rs.next()){
				//流程模型ID
				String deftId = rs.getString("PROCESSDEFID");
			
				//流程名称
				String wfName = "";
				try {
					ProcessDefinition process=SDK.getRepositoryAPI().getProcessDefinition(deftId);
					wfName = process.getName();
				} catch (Exception e) {
					wfName = "流程不存在或流程已删除";
				}
				
				BO bo = new BO();
				bo.set("DEFTID", deftId);
				bo.set("PROCESSNAME",wfName);
				bo.set("STATE", "0");
				
				list.add(bo);
			}
			
			System.out.println("执行SQL2");
			//插入流程id
			st2 = conn2.createStatement();
			rs2 = st2.executeQuery(tabquerySQL2);
			
			List<BO> list2 = new ArrayList<BO>();
			while(rs2.next()){
				//流程模型ID
				String bindId = rs2.getString("PROCESSINSTID");
			
				//流程名称
				String wfName = "";
				try {
					wfName = SDK.getRuleAPI().executeAtScript("@processTitle(id,"+bindId+")");
				} catch (Exception e) {
					wfName = "流程名称尚未更新，请稍后再试！";
				}
				
				BO bo = new BO();
				bo.set("DEFTID", bindId);
				bo.set("PROCESSNAME",wfName);
				bo.set("STATE", "1");
				
				list2.add(bo);
			}
			//删除数据
			DBSql.update(tabDeleteSQL);
			//插入数据
			SDK.getBOAPI().createDataBO("BO_DY_PROCESS", list, null);
			SDK.getBOAPI().createDataBO("BO_DY_PROCESS", list2, null);
			System.out.println("-------------流程名更新成功---------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("-------------流程名更新失败---------------");
			e.printStackTrace();
		} finally{
			DBSql.close(conn, st, rs);
			DBSql.close(conn2, st2, rs2);
		}
		
	}

}
