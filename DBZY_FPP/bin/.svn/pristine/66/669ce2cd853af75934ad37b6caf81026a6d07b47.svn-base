package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class getIDAndXmByRole extends AbstExpression {
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstat = null;

	public getIDAndXmByRole(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String roleName = getParameter(expression, 1).trim();
		String groupName = getParameter(expression, 2).trim();
		StringBuffer result = new StringBuffer();
		String sql = "";
		if(!groupName .equals("") && groupName !=null){
			sql = "select orgu.userid as USERID,orgu.username as name from orguser orgu ,orgrole orgr,orgdepartment ts "
				+ "where orgu.roleid=orgr.id and ts.id=orgu.departmentid and orgr.rolename='"
				+ roleName
				+ "' and orgu.disenable=0 and ts.departmentname ='"+groupName+"'  union "
				+ " select orgu.userid as USERID,orgu.username as name from orgusermap om," +
					"orgrole orgr,orguser orgu,orgdepartment ts " +
					"where om.roleid=orgr.id and orgu.id = om.mapid  and om.departmentid = ts.id "
				+ "and om.roleid=orgr.id and orgr.rolename='"+roleName
				+ "' and ts.departmentname ='"+groupName+"'";
		}else{
			sql = "select orgu.userid as USERID,orgu.username as name from orguser orgu ,orgrole orgr "
				+ "where orgu.roleid=orgr.id and orgr.rolename='"
				+ roleName
				+"' and orgu.disenable=0  union "
			+ "select orgu.userid as USERID,orgu.username as name from orgusermap om,orgrole orgr ,orguser orgu "
				+ "where om.roleid=orgr.id and orgr.rolename='"
				+ roleName
				+ "' and orgu.id=om.mapid";
			
		}
		try {
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String s = rs.getString("USERID");
					String d = rs.getString("NAME");
					result.append(s+"<"+d+">").append(" ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, pstat, null);
		}
		return result.toString();
	}

}
