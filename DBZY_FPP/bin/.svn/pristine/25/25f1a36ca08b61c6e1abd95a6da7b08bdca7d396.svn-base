package com.nepharm5.apps.fpp.nepgSanqi.biz.gtgl;

import java.sql.Connection;
import java.sql.Statement;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;

public class GtglSpxqNdjhw extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public GtglSpxqNdjhw() {
		// TODO Auto-generated constructor stub
		setDescription("工程项目年度计划外审批说明");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String sql = "select * from BO_DY_GTGL_NDJHW where bindid = '" + bindid +"'";
		//下面4行,判断D
		double ndjhwxmztz = Double.valueOf(DBSql.getString(sql, "NDJHWXMZTZ")==null?"":DBSql.getString(sql, "NDJHWXMZTZ"));
		String spsm = null;
		if (ndjhwxmztz<20){
			spsm = "此流程最终工程项目部长审批";
		} else if  (ndjhwxmztz>=100){
			spsm = "此流程最终由班子会审批";
		}else  {
			spsm = "此流程最终由董事长审批";
		}

		if (spsm != null) {
			sql = "update BO_DY_GTGL_NDJHW set spsm = '" + spsm+ "' where bindid = '" + bindid +"'";
			DBSql.update(sql);
		}
	}

}
