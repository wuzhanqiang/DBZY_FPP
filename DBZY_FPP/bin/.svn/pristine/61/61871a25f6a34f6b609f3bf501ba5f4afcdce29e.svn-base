package com.nepharm.apps.fpp.biz.jcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.jcm.bean.CfrwBean;

public class JobDao {

	public List<CfrwBean> getWckcList(String currentTime) {
		String sql = "SELECT K.KCTM,C.KCBM,C.PZBM,C.BKHRZH,C.GSMC,C.GWMC,C.GSBM,C.GWBM,C.BKHRMC "
				+ "FROM BO_DY_KMS_YGCJ_M C "
				+ "LEFT JOIN BO_DY_KMS_KCTM_M K ON C.KCBM = K.KCBM "
				+ "LEFT JOIN BO_DY_KMS_GWKC G ON C.PZBM = G.PZBM "
				+ "WHERE C.SFBX = '1' AND C.WCSJ IS NULL "
				+ "AND G.KHZQ < to_date('"+currentTime+"','yyyy-mm-dd hh24:mi:ss')";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<CfrwBean> data = new ArrayList<>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String kctm = rs.getString("KCTM");
				String gsmc = rs.getString("GSMC");
				String gwmc = rs.getString("GWMC");
				String gsbm = rs.getString("GSBM");
				String gwbm = rs.getString("GWBM");
				String bkhrmc = rs.getString("BKHRMC");
				String bkhrzh = rs.getString("BKHRZH");
				String kcbm = rs.getString("KCBM");
				String pzbm = rs.getString("PZBM");
				CfrwBean cb = new CfrwBean();
				cb.setBkhrmc(bkhrmc);
				cb.setGsmc(gsmc);
				cb.setGwmc(gwmc);
				cb.setKctm(kctm);
				cb.setBkhrzh(bkhrzh);
				cb.setKcbm(kcbm);
				cb.setPzbm(pzbm);
				cb.setGsbm(gsbm);
				cb.setGwbm(gwbm);
				data.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
}
