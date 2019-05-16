package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class getBMLX extends AbstExpression {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public getBMLX(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String deptid = getParameter(expression, 1).trim();//获取某个部门
		StringBuffer result = new StringBuffer();
		String sql = "select DEPARTMENTTYPE from ORGDEPARTMENT WHERE id = '" + deptid + "'";
		
		try {
			conn = DBSql.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					//取出来的类型不为null或空
					String r = rs.getString("DEPARTMENTTYPE");
					if(r != null && !r.equals("")) {
						result.append(r);
					}
				}
			}
		} catch (SQLException e) {
			DBSql.close(conn, stmt, rs);
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, stmt, rs);
		}
		
		//部门类型为找到
		if(result == null || result.toString().equals("")){
			result.append("未找到");
		}
		return result.toString();
	}

}
