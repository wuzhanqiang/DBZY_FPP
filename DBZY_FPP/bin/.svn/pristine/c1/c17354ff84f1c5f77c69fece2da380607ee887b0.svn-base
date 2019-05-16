package com.nepharm.apps.fpp.biz.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.sun.syndication.feed.rss.Description;

public class MyTimeoutEvent extends ValueListener {

	public MyTimeoutEvent() {
		setDescription("自定义超时惩罚事件，接收任务未阅读情况");
		setVersion("V1.0");
	}

	@Override
	public String execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("自定义超时惩罚事件启动");
		Connection conn = DBSql.open();
		Statement st = null;
		ResultSet rs = null;
		try {
			String uid = ctx.getUserContext().getUID();
			String PINSTID = ctx.getProcessInstance().getId();
			String TINSTID = ctx.getTaskInstance().getId();
			//获取未办任务表数据
			st = conn.createStatement();
			String sql = "SELECT * from WFC_TASK where ID='"+TINSTID+"'";
			rs = st.executeQuery(sql);
			while(rs.next()){
				
				//判断该任务是否已读
				String READSTATE = rs.getString("READSTATE");
				String READTIME = rs.getString("READTIME");

				if("0".equals(READSTATE)){
					
					String LOGID = UUID.randomUUID().toString();
					String RECORDTIME = SDK.getRuleAPI().executeAtScript("@datetime");
					String YEAR = RECORDTIME.substring(0, 4);
					String MONTH = RECORDTIME.substring(5, 7);
					String DAY = RECORDTIME.substring(8, 10);
					String HM = RECORDTIME.substring(11, 16);
					String TASKTITLE = SDK.getRuleAPI().executeAtScript("@processTitle(id,"+PINSTID+")");
					String DATATYPE = "94";
					
					//String PDEFID1 = rs.getString("PROCESSDEFID");
					//String TDEFID1 = rs.getString("ACTIVITYDEFID");
					String PDEFID = ctx.getProcessDef().getId();
					String TDEFID = ctx.getTaskInstance().getActivityDefId();
					
					String USERID = rs.getString("TARGET");
					String USERNAME = SDK.getRuleAPI().executeAtScript("@userName("+USERID+")");
					String DEPTID = ctx.getUserContext().getDepartmentModel().getId();
					String DEPTNAME = ctx.getUserContext().getDepartmentModel().getName();
					String COMPANYID = SDK.getRuleAPI().executeAtScript("@getUserInfo("+USERID+",GSBM)");
					String COMPANYNAME = SDK.getRuleAPI().executeAtScript("@getUserInfo("+USERID+",GSMC)");
					String POSLEVEL = SDK.getRuleAPI().executeAtScript("@getUserInfo("+USERID+",GWJB)");
					String AMONT = SDK.getRuleAPI().executeAtScript("@getUserInfo("+USERID+",94)");
					String ZT = "0";
					String LCMC = SDK.getRepositoryAPI().getProcessDefinition(PDEFID).getName();
					
					BO csjlBo = new BO();
					csjlBo.set("LOGID", LOGID);
					csjlBo.set("RECORDTIME", RECORDTIME);
					csjlBo.set("YEAR", YEAR);
					csjlBo.set("MONTH", MONTH);
					csjlBo.set("DAY", DAY);
					csjlBo.set("HM", HM);
					csjlBo.set("PINSTID", PINSTID);
					csjlBo.set("TINSTID", TINSTID);
					csjlBo.set("TASKTITLE", TASKTITLE);
					csjlBo.set("DATATYPE", DATATYPE);
					csjlBo.set("PDEFID", PDEFID);
					csjlBo.set("TDEFID", TDEFID);
					csjlBo.set("USERID", USERID);
					csjlBo.set("USERNAME", USERNAME);
					csjlBo.set("DEPTID", DEPTID);
					csjlBo.set("DEPTNAME", DEPTNAME);
					csjlBo.set("COMPANYID", COMPANYID);
					csjlBo.set("COMPANYNAME", COMPANYNAME);
					csjlBo.set("POSLEVEL", POSLEVEL);
					csjlBo.set("AMONT", AMONT);
					csjlBo.set("ZT", ZT);
					csjlBo.set("ERRORNUM", "0");
					csjlBo.set("LCMC", LCMC);
					csjlBo.set("EXT1", "任务未在合理时效内查看");
					csjlBo.set("EXT3", "生效");
					SDK.getBOAPI().createDataBO("BO_DY_SXGL_CSJL", csjlBo, null);
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBSql.close(conn, st, rs);
		}
		
		System.out.println("自定义超时惩罚事件结束");
		return null;
	}

}
