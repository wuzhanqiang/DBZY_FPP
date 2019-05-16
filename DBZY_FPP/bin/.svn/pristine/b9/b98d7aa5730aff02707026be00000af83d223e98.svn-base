/*
 * 创建人：李鑫
 * 创建日期：2018-05-15
 */
package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class getUserByRole extends AbstExpression {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public getUserByRole(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String roleName = getParameter(expression, 1).trim();
		String groupName = getParameter(expression, 2).trim();
		StringBuffer result = new StringBuffer();
		conn = DBSql.open();
		String sql = "";
		if(groupName !=null && !groupName .equals("")  ){
			sql = "SELECT orgu.userid AS USERID FROM orguser orgu,orgrole orgr,orgdepartment ts " + 
					"WHERE orgu.roleid = orgr. ID AND ts. ID = orgu.departmentid " + 
					"AND orgr.rolename = '" + roleName + "' " + 
					"AND ts.departmentname = '" + groupName + "' " + 
					"UNION " + 
					"SELECT om.userid AS USERID FROM orgusermap om,orgrole orgr,orgdepartment ts " + 
					"WHERE om.roleid = orgr. ID AND om.departmentid = ts. ID AND om.roleid = orgr. ID " + 
					"AND orgr.rolename = '" + roleName + "' " + 
					"AND ts.departmentname = '" + groupName + "'";
		}else{
			sql = "SELECT orgu.userid AS USERID FROM orguser orgu,orgrole orgr " + 
					"WHERE orgu.roleid = orgr. ID " + 
					"AND orgr.rolename = '" + roleName + "' " + 
					"UNION " + 
					"SELECT om.userid AS USERID FROM orgusermap om,orgrole orgr " + 
					"WHERE om.roleid = orgr.ID " + 
					"AND orgr.rolename = '" + roleName + "'";
			
		}

		try {
			stmt = conn.createStatement();
//			rs = DBSql.executeQuery(conn, stmt, sql);
			rs = stmt.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					result.append(rs.getString("USERID")).append(" ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, stmt, null);
		}
		return result.toString();
	}

}
