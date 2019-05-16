package com.nepharm.apps.fpp.biz.gm.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;
import com.nepharm.apps.fpp.biz.gm.util.GMUtil;

public class JlqjjdFormToolbarBuild extends ValueListener implements ValueListenerInterface {

	public JlqjjdFormToolbarBuild() {
		setDescription("加载下月待检定计量器具");
	}

	@Override
	public String execute(ProcessExecutionContext ctx){
		ResponseObject ro = ResponseObject.newOkResponse();
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		UserContext userContext = ctx.getUserContext();
		BOAPI boapi = SDK.getBOAPI();
		BO bo = boapi.getByProcess(GMConstant.TAB_GM_JLQJJD_M, proInstId);
		String YEAR = bo.getString("YEAR");
		String MONTH = bo.getString("MONTH");
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from BO_DY_WPGL_JLQJDA where TO_CHAR(JHSYRQ,'yyyy-mm') = '"+YEAR+"-"+MONTH+"'";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				List<BO> list = GMUtil.resultSetToBo(rset);
				boapi.removeByBindId(GMConstant.TAB_GM_JLQJJD_S, proInstId, conn);
				boapi.create(GMConstant.TAB_GM_JLQJJD_S, list, proInst, userContext, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		return ro.toString();
	}

}
