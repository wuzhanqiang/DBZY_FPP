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

public class getZjlByFqr extends AbstExpression {

	public getZjlByFqr(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	@Override
	public String execute(String expression) throws AWSExpressionException {
		//String userid = getParameter(expression, 1).trim();//发起人角色
		String deptid = getParameter(expression, 2).trim();//获取某个部门
		/*String roleName = DBSql.getString("select rolename from orguser u " +
				"left join orgrole r on u.roleid = r.id where u.userid ='"+userid+"'", "rolename");
		*/
		StringBuffer result = new StringBuffer();
		String [] r = deptid.split("/");//  层级结构为管理本部/部室名称/二级部室名称/ 取二级部室的id
		String sql = "";
		for (int i = 0; i < r.length - 1; i++) {
			if (!r[r.length - i - 1].equals("") && r[r.length - 1 - i] != null) {
				sql = "select orgu.userid as USERID from orguser orgu ,orgdepartment ts "
						+ "where orgu.ismanager=1 and ts.id=orgu.departmentid and ts.id ='" + r[r.length - 1 - i]
						+ "'  union " + " select orgu.userid as USERID from orgusermap om,"
						+ "orguser orgu,orgdepartment ts "
						+ "where  orgu. USERID = om.USERID  and om.departmentid = ts.id and om.ismanager=1"
						+ " and ts.id ='" + r[r.length - 1 - i] + "'";
			}
			if (!sql.equals("")) {
				//Object[] parameter = { r[1] };
				try {
					conn = DBSql.open();
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (rs != null) {
						while (rs.next()) {
							result.append(rs.getString("USERID")).append(" ");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				} finally {
					DBSql.close(conn, stmt, rs);
				}
				if (!result.toString().equals("")) {
					break;
				}
			}

		}
		/*if(r.length>=2){
			if(!r[1].equals("") && r[1] !=null){
				sql = "select orgu.userid as USERID from orguser orgu ,orgdepartment ts "
					+ "where orgu.ismanager=1 and ts.id=orgu.departmentid and ts.id ='"+r[1]+"'  union "
					+ " select orgu.userid as USERID from orgusermap om," +
						"orguser orgu,orgdepartment ts " +
						"where  orgu. USERID = om.USERID  and om.departmentid = ts.id and om.ismanager=1"
					+ " and ts.id ='"+r[1]+"'";
				
			}
		}
		
		if(!sql.equals("")){
//		if(r.length>=2&&!r[1].equals("") && r[1] !=null) {
			Object[] parameter = {r[1]};
			try {
				conn = DBSql.open();
				stmt = conn.createStatement();
//				rs = DBSql.executeQuery(conn, stmt, sql);
				rs = stmt.executeQuery(sql);
//				List list = DBSql.query(sql, r, new RowMaper());

				if (rs != null) {
					while (rs.next()) {
						result.append(rs.getString("USERID")).append(" ");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			} finally {
				DBSql.close(conn, stmt, rs);
			}
		}
		if(result.toString().equals("")) {
			try {
				conn = DBSql.open();
				stmt = conn.createStatement();
//				rs = DBSql.executeQuery(conn, stmt, sql);
				rs = stmt.executeQuery(sql1);
//				List list = DBSql.query(sql, r, new RowMaper());

				if (rs != null) {
					while (rs.next()) {
						result.append(rs.getString("USERID")).append(" ");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			} finally {
				DBSql.close(conn, stmt, rs);
			}
		}*/
		
		if(result.toString().equals("")){
			result.append("未找到");
		}
		return result.toString();
	}
	

}
