package com.dbzy.zjxs.process.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.dbzy.zjxs.util.StringUtil;


public class ProcessFormBeforeLoad extends ExecuteListener implements ExecuteListenerInterface {

	public ProcessFormBeforeLoad() {
		setDescription("流程全局事件，表单加载前，更新审批记录表(更改角色名称)");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance processInst = ctx.getProcessInstance();
		String processInstId = processInst.getId();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from WFC_COMMENT where PROCESSINSTID = '"+processInstId+"'"
					+ " and CREATEDATE >= to_date('2018-12-10', 'yyyy-mm-dd')";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				while(rset.next()) {
					String createUser = rset.getString("CREATEUSER");
					String positionName = rset.getString("POSITIONNAME");
					String id = rset.getString("ID");
					String gwmcSql = "select * from ORGUSER where USERID = '"+createUser+"'";
					String gwmc = DBSql.getString(conn, gwmcSql, "EXT1");
					if(!StringUtil.isEmpty(gwmc) && !gwmc.equals(positionName)) {
						String updateSql = "update WFC_COMMENT set POSITIONNAME = '"+gwmc+"' where ID = '"+id+"'";
						DBSql.update(conn, updateSql);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
	}

}
