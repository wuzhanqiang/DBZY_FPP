package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class GetWorkflowUUIDByWFId extends AbstExpression{
	public GetWorkflowUUIDByWFId(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String result = "";
		
		String wfId = getParameter(expression, 1);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select VERSION_UUID from SYSFLOW where ID = "+wfId;
		try {
			conn = DBSql.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
				result = rs.getString("VERSION_UUID");
		} catch (SQLException e) {
			DBSql.close(conn, stmt, rs);
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, stmt, rs);
		}
		
		return result;
	}
}
