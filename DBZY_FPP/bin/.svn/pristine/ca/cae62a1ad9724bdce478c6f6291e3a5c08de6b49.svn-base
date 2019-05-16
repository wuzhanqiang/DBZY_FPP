package com.nepharm.apps.fpp.biz.mr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.mr.bean.UserDetailBean;

import net.sf.json.JSONArray;


public class MrUserDao {
	
	/**
	 * 根据部门ID查询下一层极所有部门ID集合
	 * @param parentDepartmentId
	 * @return
	 */
	public String getDepartmentData(String parentDepartmentId){
		String sql="select ID from ORGDEPARTMENT where PARENTDEPARTMENTID='" + parentDepartmentId + "'";
		StringBuffer sb = new StringBuffer();  
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String departmentId = rs.getString("ID");
				sb.append("'").append(departmentId).append("'").append(",");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return sb.length() > 0 ? sb.toString() : null;
	}
	
	/**
	 * 根据部门ID集查询下一层极所有部门ID集合
	 * @param ids
	 * @return
	 */
	public String getDepartmentIds(String ids) {
		String sql="select ID from ORGDEPARTMENT where PARENTDEPARTMENTID in (" + ids.substring(0, ids.length() - 1) + ")";
		StringBuffer sb = new StringBuffer();  
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String departmentId = rs.getString("ID");
				sb.append("'").append(departmentId).append("'").append(",");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return sb.length() > 0 ? sb.toString() : null;
	}
	
	/**
	 * 根据用户ID查询部门ID
	 * @param userId
	 * @return
	 */
	public String getDepartmentId(String userId) {
		String sql="select DEPARTMENTID from ORGUSER where USERID='" + userId + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String departmentId = "";
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				departmentId = rs.getString("DEPARTMENTID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return departmentId;
	}
	
	/**
	 * 根据部门ID集合查询用户详细信息集合
	 * @param departmentIds
	 * @return
	 */
	public JSONArray getUserInfo(String departmentIds){
		String sql = "SELECT XX.RYBM,OU.USERNAME,XX.XUELI,GW.MC,XX.ZHICHENG,XX.ZHUANYE,XX.DENGJI FROM ORGUSER OU " + 
				"LEFT JOIN " + 
				"BO_DY_JCXX_HRRYXXTB XX " + 
				"ON " + 
				"OU.USERID = XX.RYBM " + 
				"LEFT JOIN " + 
				"BO_DY_JCXX_GWXX GW " + 
				"ON " + 
				"XX.SZGWPK = GW.HRGWPK " + 
				"WHERE " + 
				"OU.DEPARTMENTID IN (" + departmentIds + ")";
		JSONArray data = new JSONArray();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String rybm = rs.getString("RYBM");
				String userName = rs.getString("USERNAME");
				String xueli = rs.getString("XUELI");
				String mc = rs.getString("MC");
				String zhicheng = rs.getString("ZHICHENG");
				String zhuanye = rs.getString("ZHUANYE");
				String dengji = rs.getString("DENGJI");
				UserDetailBean bean = new UserDetailBean(rybm, userName, xueli, mc, zhicheng, zhuanye, dengji);
				data.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
}
