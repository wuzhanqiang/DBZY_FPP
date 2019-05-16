package com.nepharm.apps.fpp.biz.mr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.mr.bean.WpdaBean;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

public class MRWPDADao {
	
	/**
	 * 获得物品档案数据集
	 * @param bmallpathId
	 * @param wplx
	 * @return
	 */
	public JSONArray getWpdaxx(String bmallpathId, String wplx){
		String sql="select WPBH,WPMC,WPLX,JLDW,GGXH,ZT,XCL from BO_DY_WPGL_WPDA where instr('"+bmallpathId+"', BMBH) > 0";
		if(StringUtil.isNotEmpty(wplx))
			sql += " and WPLX = '" + wplx + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String wpbh = rs.getString("WPBH");
				String wpmc = rs.getString("WPMC");
				String wplxs = rs.getString("WPLX");
				String jldw = rs.getString("JLDW");
				String ggxh = rs.getString("GGXH");
				String zt = rs.getString("ZT");
				Double xcl = rs.getDouble("XCL");
				WpdaBean wb = new WpdaBean(wpbh, wpmc, wplxs, jldw, ggxh, zt, xcl);
				data.add(wb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

}
