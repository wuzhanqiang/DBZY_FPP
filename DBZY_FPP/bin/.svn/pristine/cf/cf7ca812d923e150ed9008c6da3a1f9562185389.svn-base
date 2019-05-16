package com.nepharm.apps.fpp.biz.kms.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

public class PxjhTaskAfterComplete extends ExecuteListener implements ExecuteListenerInterface {

	public PxjhTaskAfterComplete() {
		setDescription("培训计划数据推送至培训记录表 BO_DY_KMS_PXJL");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			if(ctx.isChoiceActionMenu("同意")) {
				String bindId = ctx.getProcessInstance().getId();
				UserContext userContext = ctx.getUserContext();
				BOAPI boApi = SDK.getBOAPI();
				stmt = conn.createStatement();
				String sql = "select t.*, t1.*, t2.*"
						+ " from BO_DY_KMS_KCJH_M t"
						+ " join BO_DY_KMS_KCJH_S1 t1 on t.bindid = t1.bindid"
						+ " join BO_DY_KMS_KCJH_S2 t2 on t.bindid = t2.bindid"
						+ " where t.bindid = '"+bindId+"'";
				rset = stmt.executeQuery(sql);
				if(rset != null) {
					while(rset.next()) {
						BO bo = new BO();
						bo.set("PXJHBH", rset.getString("PXJHBH"));
						bo.set("KCBH", rset.getString("KCBH"));
						bo.set("KCMC", rset.getString("KCMC"));
						bo.set("KCLX", rset.getString("KCLX"));
						bo.set("XXDZ", rset.getString("XXDZ"));
						bo.set("PXCJRQ", rset.getString("PXCJRQ"));
						bo.set("XXQX", rset.getString("XXQX"));
						bo.set("SJBH", rset.getString("SJBH"));
						bo.set("SJMC", rset.getString("SJMC"));
						bo.set("XYXM", rset.getString("XYXM"));
						bo.set("XYBH", rset.getString("XYBH"));
						bo.set("BMBH", rset.getString("BMBH"));
						bo.set("BMMC", rset.getString("BMMC"));
						bo.set("KSZT", "待考");
						boApi.createDataBO("BO_DY_KMS_PXJL", bo, userContext, conn);
					}
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn, stmt, rset);
		}
	}

}
