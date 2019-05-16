package com.nepharm5.apps.fpp.nepgSanqi.biz.gtgl;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;

public class GtglSpsmQd extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public GtglSpsmQd() {
		// TODO Auto-generated constructor stub
		setDescription("工程项目启动审批说明");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		DecimalFormat format = new DecimalFormat("0.000");
		String sql = "select QDXMZTZ,YXMZTZ,ZEBD from BO_DY_GTGL_XMQD_P where bindid = '" + bindid +"'";
		//下面4行,判断D
		double qdxmztz = Double.valueOf(DBSql.getString(sql, "QDXMZTZ")==null?"":DBSql.getString(sql, "QDXMZTZ"));
		double yxmztz = Double.valueOf(DBSql.getString(sql, "YXMZTZ")==null?"":DBSql.getString(sql, "YXMZTZ"));		
		double c=(qdxmztz-yxmztz)/yxmztz;
		double d=Double.valueOf(format.format(c));
		//或者下面1行,判断zebd
		//double zebd = Double.valueOf(DBSql.getString(sql, "ZEBD")==null?"":DBSql.getString(sql, "ZEBD"));
		String spsm = null;
		if (yxmztz>=qdxmztz){
			spsm = "此流程最终由工程项目管理专员备案";
		} else if (d <= 0.05 ) {
			spsm = "此流程最终由工程项目部长审批";
		} else if (d <= 0.1) {
			spsm = "此流程最终由工程项目部主管副总审批";
		} else {
			spsm = "此流程最终由工程项目主管副总审批";
		}

		if (spsm != null) {
			sql = "update BO_DY_GTGL_XMQD_P set spsm = '" + spsm+ "' where bindid = '" + bindid +"'";
			DBSql.update(sql);
			
		}
	}

}
