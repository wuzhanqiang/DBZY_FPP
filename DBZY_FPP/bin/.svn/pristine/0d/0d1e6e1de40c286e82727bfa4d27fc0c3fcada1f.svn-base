package com.nepharm.apps.fpp.biz.zbgl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.zbgl.bean.SyBean;
import com.nepharm.apps.fpp.biz.zbgl.bean.ZBBean;
import com.nepharm.apps.fpp.biz.zbgl.bean.ZBXXBean;

import jodd.util.StringUtil;



public class ZBDao {
	public List<ZBBean> getZXZB(String userId) {
		String sql = "SELECT B.SQR,B.SQRZH,B.GSMC,B.YEAR,B.MONTH,B.WEEK " + 
				"FROM BO_DY_ZBSB_M B " + 
				"INNER JOIN BO_DY_ZBSB_RYXX_ZB X " + 
				"ON B.SQR = X.SQR " + 
				"WHERE X.SRRZH LIKE '%"+userId+"%' " + 
				"AND ROWNUM = 1 ";
		sql +="ORDER BY B.CREATEDATE DESC";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<ZBBean> data = new ArrayList<ZBBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sqr = rs.getString("SQR");
				String sqrzh = rs.getString("SQRZH");
				String gsmc = rs.getString("GSMC");
				String year = rs.getString("YEAR");
				String month = rs.getString("MONTH");
				String week = rs.getString("WEEK");
				ZBBean zb = new ZBBean();
				zb.setGsmc(gsmc);
				zb.setMonth(month);
				zb.setSqr(sqr);
				zb.setSqrzh(sqrzh);
				zb.setWeek(week);
				zb.setYear(year);
				data.add(zb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
	public List<ZBBean> getZXZBs(String userId, String dwsx, String year, String month, String week) {
		String sql = "SELECT B.SQR,B.SQRZH,B.GSMC " + 
				"FROM BO_DY_ZBSB_M B " + 
				"INNER JOIN BO_DY_ZBSB_RYXX_ZB X " + 
				"ON B.SQR = X.SQR " + 
				"WHERE X.SRRZH LIKE '%"+userId+"%' " + 
				"AND B.YEAR = '" + year + "' " +
				"AND B.MONTH = '" + month + "' " +
				"AND B.WEEK = '" + week + "' " +
				"AND ROWNUM = 1 ";
		if(StringUtil.isNotEmpty(dwsx) && !dwsx.equals("0"))
			sql += "AND DWSX = '" + dwsx + "' ";
		sql +="ORDER BY B.CREATEDATE DESC";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<ZBBean> data = new ArrayList<ZBBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sqr = rs.getString("SQR");
				String sqrzh = rs.getString("SQRZH");
				String gsmc = rs.getString("GSMC");
				ZBBean zb = new ZBBean();
				zb.setGsmc(gsmc);
				zb.setMonth(month);
				zb.setSqr(sqr);
				zb.setSqrzh(sqrzh);
				zb.setWeek(week);
				zb.setYear(year);
				data.add(zb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
	public List<ZBBean> getSqrList(String userId) {
		String sql = "SELECT SQR,SQRZH,GSMC,DWSX " + 
				"FROM BO_DY_ZBSB_RYXX_ZB " + 
				"WHERE SRRZH LIKE '%"+userId+"%'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<ZBBean> data = new ArrayList<ZBBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sqr = rs.getString("SQR");
				String sqrzh = rs.getString("SQRZH");
				String gsmc = rs.getString("GSMC");
				String dwsx = rs.getString("DWSX");
				ZBBean zb = new ZBBean();
				zb.setGsmc(gsmc);
				zb.setSqr(sqr);
				zb.setSqrzh(sqrzh);
				zb.setDwsx(dwsx);
				data.add(zb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
	public ZBBean getZbModel(String userId, String year, String month, String week, ZBBean zb) {
		String sql = "SELECT Y.ZT " + 
				"FROM BO_DY_ZBSB_M B " + 
				"INNER JOIN BO_DY_ZBSB_SYLY Y " + 
				"ON B.BINDID = Y.BINDID " + 
				"WHERE B.WEEK = '"+week+"' " + 
				"AND B.YEAR = '" + year + "' " + 
				"AND B.MONTH = '" + month + "' " + 
				"AND B.SQRZH = '" + zb.getSqrzh() + "' " + 
				"AND Y.SQRZH = '" + userId + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String zt = rs.getString("ZT");
				zb.setZt(zt == null || zt == "" || zt.equals("0") ? false : true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return zb;
	}

	public ZBXXBean getZbXXById(String year, String month, String week, String sqrzh) {
		String sql = "SELECT ID,BINDID,SQR,GSMC,TJSJ,NR,NR2,NR3,NR4,NR5,ZJ " + 
				"FROM BO_DY_ZBSB_M " + 
				"WHERE WEEK = '"+week+"' " + 
				"AND YEAR = '" + year + "' " + 
				"AND MONTH = '" + month + "' " + 
				"AND SQRZH = '" + sqrzh + "' ";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		ZBXXBean zb = new ZBXXBean();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String bindId = rs.getString("BINDID");
				String id = rs.getString("ID");
				String sqr = rs.getString("SQR");
				String gsmc = rs.getString("GSMC");
				String tjsj = rs.getString("TJSJ");
				String nr = rs.getString("NR");
				String nr2 = rs.getString("NR2");
				String nr3 = rs.getString("NR3");
				String nr4 = rs.getString("NR4");
				String nr5 = rs.getString("NR5");
				String zj = rs.getString("ZJ");
				zb.setBindId(bindId);
				zb.setGsmc(gsmc);
				zb.setMonth(month);
				zb.setNr(nr);
				zb.setNr2(nr2);
				zb.setNr3(nr3);
				zb.setNr4(nr4);
				zb.setNr5(nr5);
				zb.setSqr(sqr);
				zb.setWeek(week);
				zb.setYear(year);
				zb.setZj(zj);
				zb.setTjsj(tjsj);
				zb.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return zb;
	}

	public List<SyBean> getSyListBy(String bindId, String userId) {
		String sql = "SELECT SQR,SYLY " + 
				"FROM BO_DY_ZBSB_SYLY " + 
				"WHERE BINDID = '"+bindId+"' ";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<SyBean> data = new ArrayList<SyBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sqr = rs.getString("SQR");
				String syly = rs.getString("SYLY");
				SyBean zb = new SyBean();
				zb.setSqr(sqr);
				zb.setSyly(syly);
				data.add(zb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public List<ZBBean> getSqrListByDwsx(String userId, String dwsx) {
		String sql = "SELECT SQR,SQRZH,GSMC,DWSX " + 
				"FROM BO_DY_ZBSB_RYXX_ZB " + 
				"WHERE SRRZH LIKE '%"+userId+"%' ";
		if(!dwsx.equals("0")) {
			sql += "AND DWSX = '" + dwsx + "'";
		}
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<ZBBean> data = new ArrayList<ZBBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String sqr = rs.getString("SQR");
				String sqrzh = rs.getString("SQRZH");
				String gsmc = rs.getString("GSMC");
				ZBBean zb = new ZBBean();
				zb.setGsmc(gsmc);
				zb.setSqr(sqr);
				zb.setSqrzh(sqrzh);
				zb.setDwsx(dwsx);
				data.add(zb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
}
