package com.nepharm5.apps.fpp.dyzy.biz.Syybgff;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm5.apps.fpp.nepg.msg.WjpsMsg;

public class YySyybgffPGxjysxRTClassA extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	
	public YySyybgffPGxjysxRTClassA() {
		// TODO Auto-generated constructor stub
	}

	public YySyybgffPGxjysxRTClassA(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("水检验报告发放主流程,更新检验时限字段");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//获取流程实例Id
		String bindid = ctx.getProcessInstance().getId();
		//获取操作的BO记录ID
		String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		//获取子表数据
		BO bo = SDK.getBOAPI().get("BO_DY_DYZY_SJYZLC", boId);
		
		if(bo!=null && !bo.isNew()){
			String sql = "update BO_DY_DYZY_SJYZLC set  JYSX=add_months(last_day(trunc(createdate)+(1+17/24)),1) where BINDID = '"+bindid+"'";
				conn = DBSql.open();
				try {
					stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace(System.err);
				}finally{
					DBSql.close(conn, stmt, null);
				}				
			}		
	}

}
