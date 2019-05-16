package com.nepharm5.apps.fpp.nepg.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.actionsoft.bpms.util.DBSql;


public class OrgUtil {

	private OrgUtil(){}
	
	private static class OrgUtilHolder{
		private static OrgUtil instance = new OrgUtil();
	}
	
	public static final OrgUtil getInstance(){
		return OrgUtilHolder.instance;
	}
	
	/**
	 * 根据部门id获取拥有指定角色的并且是管理者的人员的账号，如果有多个的话，返回第一个找到的，如果没有找到，返回"未找到"
	 * @param departmentId 部门id
	 * @param roleName 角色名称
	 * @return
	 */
	public String getManagerWithRoleNameByDepartmentId(String departmentId, String roleName){
		String result = "未找到";
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct USERID, USERNAME from(select ORGUSER.USERID, ORGUSER.USERNAME from ORGUSER ")
			.append(" inner join ORGROLE on ORGUSER.ROLEID = ORGROLE.ID")
			.append(" where ORGROLE.ROLENAME = '"+roleName+"' and ORGUSER.ISMANAGER = 1 and ORGUSER.DEPARTMENTID = '").append(departmentId+"'")
			.append(" union all")
			.append(" select ORGUSER.USERID, ORGUSER.USERNAME from ORGUSERMAP inner join ORGUSER")
			.append(" on ORGUSERMAP.USERID = ORGUSER.ID")
			.append(" inner join ORGROLE on ORGUSERMAP.ROLEID = ORGROLE.ID")
			.append(" where ORGROLE.ROLENAME = '"+roleName+"' and ORGUSERMAP.ISMANAGER = 1 and ORGUSERMAP.DEPARTMENTID = '"+departmentId+"')");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next()){
				result = rs.getString("USERID");
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			DBSql.close(conn, stmt, rs);
		}
		
		return result;
	}
	
}
