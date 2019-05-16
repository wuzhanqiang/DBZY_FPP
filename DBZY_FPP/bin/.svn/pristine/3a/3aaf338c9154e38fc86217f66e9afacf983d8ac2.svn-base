package com.nepharm.apps.fpp.biz.gm.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.kms.util.KMSUtil;

import net.sf.json.JSONObject;

public class GMUtil {
	/**
	 * 根据部门ID和角色名称，查找用户账号
	 * @param departmentId 部门ID 
	 * @param roleName 角色名称
	 * @return userId 多个账号空格分隔
	 */
	public static String getUserId(String departmentId, String roleName) {
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		String userId = "";
		try {
			stmt = conn.createStatement();
//			String departmentId = "c0d5e498-bf3b-4f4c-81fd-3b7ca6ef8bbc";// 东北制药集团沈阳计控有限公司\综合部
//			String roleName = "总经理";//角色名
			String sql = "select t1.USERID from ORGUSER t1 join ORGROLE t2 on t1.ROLEID = t2.ID "
					+ "where t2.ROLENAME = '"+roleName+"' and t1.DEPARTMENTID = '"+departmentId+"'";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				while(rset.next()) {
					userId = userId + " " + rset.getString("USERID");
				}
				userId = userId.trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		return userId;
	}
	
	public static List resultSetToBo(ResultSet rs) throws SQLException {
		List<BO> list = new ArrayList();
	    while (rs.next()) {   
	    	BO bo = new BO();
	    	bo.set("WPBH", rs.getString("WPBH")==null?"":rs.getString("WPBH"));
	    	bo.set("WPMC", rs.getString("WPMC")==null?"":rs.getString("WPMC"));
	    	bo.set("WPLX", rs.getString("WPLX")==null?"":rs.getString("WPLX"));
	    	bo.set("JLDW", rs.getString("JLDW")==null?"":rs.getString("JLDW"));
	    	bo.set("GGXH", rs.getString("GGXH")==null?"":rs.getString("GGXH"));
	    	bo.set("CLFW", rs.getString("CLFW")==null?"":rs.getString("CLFW"));
	    	bo.set("GYSMC", rs.getString("GYSMC")==null?"":rs.getString("GYSMC"));
	    	bo.set("ZZSMC", rs.getString("ZZSMC")==null?"":rs.getString("ZZSMC"));
	    	bo.set("ZT", rs.getString("ZT")==null?"":rs.getString("ZT"));
	    	bo.set("NKBH", rs.getString("NKBH")==null?"":rs.getString("NKBH"));
	    	bo.set("CCBH", rs.getString("CCBH")==null?"":rs.getString("CCBH"));
	    	bo.set("AZDD", rs.getString("AZDD")==null?"":rs.getString("AZDD"));
	    	bo.set("JHSYRQ", rs.getDate("JHSYRQ"));
	    	list.add(bo);
	     } 
		return list;
	}
}
