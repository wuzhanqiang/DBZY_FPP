package com.nepharm.apps.fpp.biz.mr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.mr.bean.SbdaBean;

import net.sf.json.JSONArray;

public class MRSBDADao {

	public Object getSbdaxx(String bmallpathId, String bmallpathName, String GWBM) {
		String sql="select SBBH,SBMC,GGXH,ZT,ZCBH from BO_DY_SBGL_SBDA where instr('"+bmallpathId+"', BMBH) > 0 and instr(GWBM, "+GWBM+") > 0";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sbbh = rs.getString("SBBH");
				String sbmc = rs.getString("SBMC");
				String ggxh = rs.getString("GGXH");
				String zt = rs.getString("ZT");
				String zcbh = rs.getString("ZCBH");
				SbdaBean wb = new SbdaBean(sbbh, sbmc, ggxh, zt, zcbh, bmallpathName);
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
