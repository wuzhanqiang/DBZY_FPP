package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class pdZjlSfFZXC extends AbstExpression {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public pdZjlSfFZXC(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}
	
	public String execute(String expression) throws AWSExpressionException{
		String zjlzh = getParameter(expression, 1).trim();
		StringBuffer result = new StringBuffer();
		String re = "false";
		String sql ="select userid from orguser where roleid='65591'";
		
		try {
			if(!sql.equals("")){
				conn = DBSql.open();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					while (rs.next()) {
						result.append(rs.getString("USERID")).append(" ");
					}
				}
				if(result.toString().contains(zjlzh)){
					re = "true";
				}
			}
			
		} catch (SQLException e) {
			DBSql.close(conn, stmt, rs);
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, stmt, rs);
		}
		return re;
		
	}

	

}
