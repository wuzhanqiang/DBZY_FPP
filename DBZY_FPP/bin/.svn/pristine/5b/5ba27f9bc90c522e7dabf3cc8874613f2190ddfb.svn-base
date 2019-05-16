package com.nepharm.apps.fpp.biz.jcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.jcm.bean.CfrwBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KctkBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KctmmBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KcxxBean;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

public class JCMTKDao {

	public JSONArray getCktkList(String userId) {
		String sql = "SELECT KCTM,KCNR,XXDZ,BINDID FROM BO_DY_KMS_KCTM_M WHERE CREATEUSER = '" + userId + "'";
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
				String kcsm = rs.getString("KCNR");
				String xxdz = rs.getString("XXDZ");
				String bindId = rs.getString("BINDID");
				KctkBean kb = new KctkBean();
				kb.setBindId(bindId);
				kb.setKcsm(kcsm);
				kb.setKctm(kctm);
				kb.setXxdz(xxdz);
				data.add(kb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public KctmmBean getKctmModel(String bindId) {
		String sql = "SELECT KCTM,KCNR,XXDZ,KCBM,FLID FROM BO_DY_KMS_KCTM_M WHERE BINDID = '" + bindId + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		KctmmBean kb = new KctmmBean();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String kctm = rs.getString("KCTM");
				String kcsm = rs.getString("KCNR");
				String xxdz = rs.getString("XXDZ");
				String kcbm = rs.getString("KCBM");
				String flId = rs.getString("FLID");
				kb.setBindId(bindId);
				kb.setKcsm(kcsm);
				kb.setKctm(kctm);
				kb.setXxdz(xxdz);
				kb.setKcbm(kcbm);
				kb.setFlId(flId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return kb;
	}

	public String kcxxSave(String userId, String kctm, String kcsm, String xxdz, String bindId, String flId) {
		String sql = "";
		if(StringUtil.isNotEmpty(bindId)) {
			sql = "UPDATE BO_DY_KMS_KCTM_M SET UPDATEUSER = ?, KCTM = ?, KCNR = ?, XXDZ = ?, FLID = ? WHERE BINDID = ?";
			DBSql.update(sql, new Object[] { userId, kctm, kcsm, xxdz, flId, bindId});
		}else {
			String uuid = UUID.randomUUID().toString();
			bindId = UUID.randomUUID().toString();
			String kcbm = UUID.randomUUID().toString();
			sql = "insert into BO_DY_KMS_KCTM_M (ID,BINDID,CREATEUSER,KCBM,KCTM,KCNR,XXDZ,FLID,ZT) values('"+uuid+"','"+bindId+"','"+userId+"','"+kcbm+"','"+kctm+"','"+kcsm+"','"+xxdz+"','"+flId+"','0')";
			DBSql.update(sql);
		}
		
		return bindId;
	}

	public void tmxxsave(String userId, String khtm, String bindId, String a, String b, String c, String d, String e,
			String f, String zqda) {
		String uuid = UUID.randomUUID().toString();
		String insertSQL="insert into BO_DY_KMS_KCTM_S (ID,BINDID,CREATEUSER,KHTM,A,B,C,D,E,F,ZQDA) values('"+uuid+"','"+bindId+"','"+userId+"','"+khtm+"','"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+zqda+"')";
		DBSql.update(insertSQL);
	}

	public void tmDelete(String tmId) {
		String sql = "delete from BO_DY_KMS_KCTM_S where id='" + tmId + "'";
		DBSql.update(sql);
	}

	public JSONArray getAllBxkcList(String kctm, String kcsm, String xxdz, String flid,String gsbm,String gwbm,int pageSize,int pageIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT GS.KCTM,GS.KCSM,GS.XXDZ,GW.PZBM , ROWNUM RN FROM	BO_DY_KMS_GWKC gw " + 
				"LEFT JOIN BO_DY_KMS_GWKC_S gs ON GW.BINDID = GS.BINDID " + 
				"LEFT JOIN BO_DY_KMS_KCTM_M km ON km.kcbm = gs.kcbm where km.zt='1' ");
		if(!StringUtils.isEmpty(kctm)) {
			sql.append("and gs.KCTM like '%"+kctm+"%'");
		}
		if(!StringUtils.isEmpty(kcsm)) {
			sql.append("and gs.KCSM like '%"+kcsm+"%'");
		}
		if(!StringUtils.isEmpty(xxdz)) {
			sql.append("and gs.XXDZ like '%"+xxdz+"%'");
		}
		if(!flid.equals("''")) {
			sql.append("and km.FLID in ("+flid+")");
		}
		//if(!StringUtils.isEmpty(gsbm)) {
			sql.append("and gw.GSBM = '"+gsbm+"'");
		//}
		//if(!StringUtils.isEmpty(gwbm)) {
			sql.append("and gw.GWBM = '"+gwbm+"'");
			sql.append("and ROWNUM  <= '"+(pageSize+pageIndex)+"'");
			sql.append(") WHERE RN > "+pageIndex);
		//}
		//sql.append("and gw.GSBM = '"+gsbm+"'");
		//sql.append("and gw.GWBM = '"+gwbm+"'");
		//String sql = "SELECT ID,FLMC FROM BO_DY_KMS_XXDZFL";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while(rs.next()){
			
				//String id = rs.getString("ID");
				//String flmc = rs.getString("FLMC");
				KcxxBean xb = new KcxxBean();
				xb.setXxdz(rs.getString("XXDZ"));
				xb.setPzbm(rs.getString("PZBM"));
				xb.setKcsm(rs.getString("KCSM"));
				xb.setKctm(rs.getString("KCTM"));
				data.add(xb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
	
	public JSONArray getAllXxkcList(String kctm, String kcsm, String xxdz, String flid, String gsbm,String gwbm,int pageSize,int pageIndex) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT GS.KCTM,GS.KCSM,GS.XXDZ,GW.PZBM, ROWNUM RN FROM	BO_DY_KMS_GWKC gw " + 
				"LEFT JOIN BO_DY_KMS_GWKC_S gs ON GW.BINDID = GS.BINDID " + 
				"LEFT JOIN BO_DY_KMS_KCTM_M km ON km.kcbm = gs.kcbm where km.zt='1' ");
		if(!StringUtils.isEmpty(kctm)) {
			sql.append("and gs.KCTM like '%"+kctm+"%'");
		}
		if(!StringUtils.isEmpty(kcsm)) {
			sql.append("and gs.KCSM like '%"+kcsm+"%'");
		}
		if(!StringUtils.isEmpty(xxdz)) {
			sql.append("and gs.XXDZ like '%"+xxdz+"%'");
		}
		if(!flid.equals("''")) {
			sql.append("and km.FLID in ("+flid+")");
		}
		//if(!StringUtils.isEmpty(gsbm)) {
			sql.append("and (gw.GSBM != '"+gsbm+"'");
			sql.append("or gw.GWBM != '"+gwbm+"')");
		//}
			sql.append("and ROWNUM  <= '"+(pageSize+pageIndex)+"'");
			sql.append(") WHERE RN > "+pageIndex);
		//String sql = "SELECT ID,FLMC FROM BO_DY_KMS_XXDZFL";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while(rs.next()){
			
				//String id = rs.getString("ID");
				//String flmc = rs.getString("FLMC");
				KcxxBean xb = new KcxxBean();
				xb.setXxdz(rs.getString("XXDZ"));
				xb.setPzbm(rs.getString("PZBM"));
				xb.setKcsm(rs.getString("KCSM"));
				xb.setKctm(rs.getString("KCTM"));
				data.add(xb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public String getFlid(String flid) {
		String sql="select ID from BO_DY_KMS_XXDZFL where PARENTID in ("+flid+")";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<String> list1 = new ArrayList<String>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				list1.add("'"+rs.getString("ID")+"'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return StringUtils.strip(list1.toString(),"[]");
	}

	public int getTotal(String kctm, String kcsm, String xxdz, String flid, String gsbm, String gwbm,int lx) {
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT count(gs.ID) as sl  FROM	BO_DY_KMS_GWKC gw " + 
			"LEFT JOIN BO_DY_KMS_GWKC_S gs ON GW.BINDID = GS.BINDID " + 
			"LEFT JOIN BO_DY_KMS_KCTM_M km ON km.kcbm = gs.kcbm where km.zt='1' ");
	if(!StringUtils.isEmpty(kctm)) {
		sql.append("and gs.KCTM like '%"+kctm+"%'");
	}
	if(!StringUtils.isEmpty(kcsm)) {
		sql.append("and gs.KCSM like '%"+kcsm+"%'");
	}
	if(!StringUtils.isEmpty(xxdz)) {
		sql.append("and gs.XXDZ like '%"+xxdz+"%'");
	}
	if(!flid.equals("''")) {
		sql.append("and km.FLID in ("+flid+")");
	}
	if(lx==1) {
		sql.append("and gw.GSBM = '"+gsbm+"'");
		sql.append("and gw.GWBM = '"+gwbm+"'");
	}else {
		sql.append("and (gw.GSBM != '"+gsbm+"'");
		sql.append("or gw.GWBM != '"+gwbm+"')");
	}
	return DBSql.getInt(sql.toString(), "sl");
	}

	public List<CfrwBean> getGwkcxxsByPzbm(String pzbm) {
		String sql = "SELECT S.KCBM,M.GSBM,M.GSMC,M.GWBM,M.GWMC FROM BO_DY_KMS_GWKC_S S LEFT JOIN BO_DY_KMS_GWKC M ON S.BINDID = M.BINDID WHERE M.PZBM = '"+pzbm+"'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<CfrwBean> data = new ArrayList<CfrwBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String kcbm = rs.getString("KCBM");
				String gsbm = rs.getString("GSBM");
				String gsmc = rs.getString("GSMC");
				String gwbm = rs.getString("GWBM");
				String gwmc = rs.getString("GWMC");
				CfrwBean cb = new CfrwBean();
				cb.setKcbm(kcbm);
				cb.setGsbm(gsbm);
				cb.setGsmc(gsmc);
				cb.setGwbm(gwbm);
				cb.setGwmc(gwmc);
				data.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public void createYgcj(CfrwBean cb, String pzbm, String userId, String userName) {
		String uuid = UUID.randomUUID().toString();
		String bindId = UUID.randomUUID().toString();
		String sql = "insert into BO_DY_KMS_YGCJ_M (ID,BINDID,CREATEUSER,KCBM,GSBM,GSMC,GWBM,GWMC,BKHRZH,BKHRMC,PZBM,SFBX) values('"+uuid+"','"+bindId+"','"+userId+"','"+cb.getKcbm()+"','"+cb.getGsbm()+"','"+cb.getGsmc()+"','"+cb.getGwbm()+"','"+cb.getGwmc()+"','"+userId+"','"+userName+"','"+pzbm+"','0')";
		DBSql.update(sql);
	}
	
}
