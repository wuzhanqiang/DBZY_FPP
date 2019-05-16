package com.nepharm.apps.fpp.biz.mr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.mr.bean.BgypxxBean;

import net.sf.json.JSONArray;

public class MRBGYPXXDao {
	public JSONArray getBgypxx(String userId){
		String sql = "select t2.WPMC,t2.WPLX,t2.JLDW,t2.GGXH,t2.ZT,t2.SL "
				+ "from BO_DY_WPGL_BGYP_M t1 join BO_DY_WPGL_BGYP_S t2 on t1.BINDID = t2.BINDID "
				+ "where t1.RYBM = '"+userId+"'";
//		String sql="select WPMC,WPLX,JLDW,GGXH,ZT,SL from BO_DY_WPGL_BGYP_S where RYBM='" + userId + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String wpmc = rs.getString("WPMC");
				String wplx = rs.getString("WPLX");
				String jldw = rs.getString("JLDW");
				String ggxh = rs.getString("GGXH");
				String zt = rs.getString("ZT");
				String sl = rs.getString("SL");
				BgypxxBean bb = new BgypxxBean(wpmc, wplx, jldw, ggxh, zt, sl);
				data.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
}
