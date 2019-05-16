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

public class getJLZJByFqr extends AbstExpression {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public getJLZJByFqr(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
String deptid = getParameter(expression, 1).trim();//获取某个部门
		
		StringBuffer result = new StringBuffer();
		String [] r = deptid.split("/");//  层级结构为管理本部/部室名称/二级部室名称/ 取二级部室的id
		String sql = "";
		for(int i=0;i<r.length-1;i++) {
			if(!r[r.length-i-1].equals("") && r[r.length-1-i] !=null){
				sql = "select orgu.userid as USERID from orguser orgu left join orgdepartment ts on ts. ID = orgu.departmentid "  
					+	"LEFT JOIN ORGROLE orgr on ORGU.ROLEID=ORGR.ID" + 
						" "
					+ "where (orgr.ROLENAME = '经理' and ts.id ='"+r[r.length-1-i]+"') or (orgr.ROLENAME = '总监' and ts.id ='"+r[r.length-1-i]+"') union "
					+ " select orgu.userid as USERID from orgusermap om," +
						"orguser orgu,orgdepartment ts " +
						"where  orgu. USERID = om.USERID  and om.departmentid = ts.id and om.ismanager=1"
					+ " and ts.id ='"+r[r.length-1-i]+"'";
			}
			if(!sql.equals("")){
				try {
					conn = DBSql.open();
					stmt = conn.createStatement();
//					rs = DBSql.executeQuery(conn, stmt, sql);
					rs = stmt.executeQuery(sql);

					if (rs != null) {
						while (rs.next()) {
							result.append(rs.getString("USERID")).append(" ");
						}
					}
				} catch (SQLException e) {
					DBSql.close(conn, stmt, rs);
					e.printStackTrace(System.err);
				}finally {
					DBSql.close(conn, stmt, rs);
				}
			}
			if(!result.toString().equals("")){
				break;
			}
		}
		
		//根据单位模糊 查询
		/*if(r.length>=3){
			if(!r[4].equals("") && r[4] !=null){
				sql = "select orgu.userid as USERID from orguser orgu left join orgdepartment ts on ts. ID = orgu.departmentid "  
					+	"LEFT JOIN ORGROLE orgr on ORGU.ROLEID=ORGR.ID" + 
						" "
					+ "where (orgr.ROLENAME = '经理' and ts.id ='"+r[4]+"') or (orgr.ROLENAME = '总监' and ts.id ='"+r[4]+"') union "
					+ " select orgu.userid as USERID from orgusermap om," +
						"orguser orgu,orgdepartment ts " +
						"where  orgu. USERID = om.USERID  and om.departmentid = ts.id and om.ismanager=1"
					+ " and ts.id ='"+r[4]+"'";
			}
		}*/
		/*if(!sql.equals("")){
			try {
				conn = DBSql.open();
				stmt = conn.createStatement();
//				rs = DBSql.executeQuery(conn, stmt, sql);
				rs = stmt.executeQuery(sql);

				if (rs != null) {
					while (rs.next()) {
						result.append(rs.getString("USERID")).append(" ");
					}
				}
			} catch (SQLException e) {
				DBSql.close(conn, stmt, rs);
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
