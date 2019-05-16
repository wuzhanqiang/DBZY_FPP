package com.nepharm.apps.fpp.biz.jcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.jcm.bean.KstmBean;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

public class JCMKSTMXXDao {
	public JSONArray getKstmxx(String kcbm){
		String sql = "SELECT s.ID,s.KHTM,s.A,s.B,s.C,s.D,s.E,s.F,s.ZQDA FROM BO_DY_KMS_KCTM_M m , BO_DY_KMS_KCTM_S s WHERE m.BINDID = s.BINDID AND m.KCBM = '" + kcbm + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String id = rs.getString("ID");
				String khtm = rs.getString("KHTM");
				String a = rs.getString("A");
				String b = rs.getString("B");
				String c = rs.getString("C");
				String d = rs.getString("D");
				String e = rs.getString("E");
				String f = rs.getString("F");
				String zqda = rs.getString("ZQDA");
				KstmBean kb = new KstmBean();
				kb.setA(a);
				kb.setB(b);
				kb.setC(c);
				kb.setD(d);
				kb.setE(e);
				kb.setF(f);
				kb.setKhtm(khtm);
				kb.setZqda(StringUtil.isNotEmpty(zqda) ? zqda.replace(" ", "") : null);
				kb.setId(id);
				data.add(kb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public int kscjztUpdate(String kcbm, String userId) {
		String sql = "update BO_DY_KMS_YGCJ_M set WCSJ = ? where BKHRZH=? and KCBM=?";
		DBSql.update(sql, new Object[] { new Date(), userId, kcbm});
		return 1;
	}

}
