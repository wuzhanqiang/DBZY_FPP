package com.nepharm5.apps.fpp.nepgSanqi.biz.gtgl;

import java.sql.Connection;
import java.sql.Statement;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;

public class GtglSpxqBg extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public GtglSpxqBg() {
		// TODO Auto-generated constructor stub
		setDescription("工程项目变更审批说明");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String sql = "select * from BO_DY_GTGL_XMBG_P where bindid = '" + bindid +"'";
		//下面4行,判断D
		double qdxmztz = Double.valueOf(DBSql.getString(sql, "QDXMZTZ")==null?"":DBSql.getString(sql, "QDXMZTZ"));
		double bgxmztz = Double.valueOf(DBSql.getString(sql, "BGXMZTZ")==null?"":DBSql.getString(sql, "BGXMZTZ"));	
		String spsm = null;
		if (bgxmztz<=qdxmztz){
			spsm = "此流程最终由工程项目管理专员备案";
		} else {
			spsm = "此流程最终由班子会审批";
		}

		if (spsm != null) {
			sql = "update BO_DY_GTGL_XMBG_P set spsm = '" + spsm+ "' where bindid = '" + bindid +"'";
			DBSql.update(sql);
		}
	}

}
