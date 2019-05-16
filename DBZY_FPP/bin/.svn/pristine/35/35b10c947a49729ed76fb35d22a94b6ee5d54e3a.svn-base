package com.nepharm.apps.fpp.biz.jcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.jcm.bean.GwkcBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KscjBean;
import com.nepharm.apps.fpp.biz.jcm.bean.YgxxBean;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

public class JCMGWGLDao {
	public List<KscjBean> getKcxx(String userId, String pzbm){
		String sql = "SELECT CJ.WCSJ, CJ.GSBM, CJ.GWBM, CJ.KCBM, KC.KCTM, KC.XXDZ FROM BO_DY_KMS_YGCJ_M CJ , BO_DY_KMS_KCTM_M KC WHERE CJ.KCBM = KC.KCBM AND KC.ZT = '1' AND CJ.BKHRZH='" + userId + "' AND CJ.PZBM='" + pzbm + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<KscjBean> list = new ArrayList<KscjBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String wcsj = rs.getString("WCSJ");
				String cktm = rs.getString("KCTM");
				String xxdz = rs.getString("XXDZ");
				String gsbm = rs.getString("GSBM");
				String gwbm = rs.getString("GWBM");
				String kcbm = rs.getString("KCBM");
				KscjBean kb = new KscjBean(StringUtil.isNotEmpty(wcsj) ? "已完成" : "待完成", cktm, xxdz, gsbm, gwbm, kcbm);
				list.add(kb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return list;
	}

	public YgxxBean getYgxx(String gsbm, String gwbm) {
		String sql = "SELECT GWMC, KHZQ FROM BO_DY_KMS_GWKC WHERE GSBM = '" + gsbm + "' AND GWBM='" + gwbm + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		YgxxBean yb = new YgxxBean();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String gwmc = rs.getString("GWMC");
				Date khzq = rs.getDate("KHZQ");
				yb.setGwmc(gwmc);
				if(khzq != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					yb.setXxsj(formatter.format(khzq));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return yb;
	}
	
	public List<YgxxBean> getRyList(String gsbm, String gwbm) {
		String sql = "select b.RYBM,b.XM from BO_DY_JCXX_HRRYXXTB b where b.SZGWPK = (select a.HRGWPK from BO_DY_JCXX_GWXX a where a.GSBM='"+gsbm+"' and a.BM='"+gwbm+"') and b.NO1CODE='"+gsbm+"'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<YgxxBean> list = new ArrayList<YgxxBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String userId = rs.getString("RYBM");
				String userName = rs.getString("XM");
				YgxxBean user = new YgxxBean();
				user.setUserId(userId);
				user.setUserName(userName);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return list;
	}

	public JSONArray getGwkcList(String gsbm, String gwbm) {
		String sql = "SELECT S.KCTM,S.KCSM,S.XXDZ,M.PZBM FROM BO_DY_KMS_GWKC_S S,BO_DY_KMS_GWKC M WHERE S.BINDID = M.BINDID AND M.GSBM = '"+gsbm+"' AND M.GWBM = '"+gwbm+"'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String kctm = rs.getString("KCTM");
				String kcsm = rs.getString("KCSM");
				String xxdz = rs.getString("XXDZ");
				String pzbm = rs.getString("PZBM");
				GwkcBean gb = new GwkcBean();
				gb.setKctm(kctm);
				gb.setKcsm(kcsm);
				gb.setXxdz(xxdz);
				gb.setPzbm(pzbm);
				data.add(gb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
}
