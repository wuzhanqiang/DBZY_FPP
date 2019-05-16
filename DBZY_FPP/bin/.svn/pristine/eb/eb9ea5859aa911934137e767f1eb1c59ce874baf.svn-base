package com.nepharm.apps.fpp.biz.mr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.mr.bean.FyxxBean;

public class MRFYXXDao {
	
	/**
	 * 根据当前登录用户ID获取HR公司编码
	 * @param userId
	 * @return
	 */
	public String getNo1Code(String userId){
		String sql="select NO1CODE from BO_DY_JCXX_HRRYXXTB where RYBM='" + userId + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		String no1code = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				no1code = rs.getString("NO1CODE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return no1code;
	}
	
	/**
	 * 获取K3编码
	 * @param no1code
	 * @return
	 */
	public String getK3Code(String no1code){
		String sql="select K3GSBM from BO_DY_JCXX_DWXX where BM='" + no1code + "'";
		Connection conn = null;
		PreparedStatement pstat = null;
		String k3gsbm = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				k3gsbm = rs.getString("K3GSBM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return k3gsbm;
	}
	
	/**
	 * 获取公司所有项目年初费用预算集合
	 * @param no1code
	 * @param year
	 * @return
	 */
	public List<FyxxBean> getNcfyyswh(String no1code, String year){
		String sql="select GSMC,FYXMBM,FYXMMC,YSJE from BO_DY_JCXX_NCFYYSWH where GSBM='" + no1code + "' and NF LIKE '%"+ year + "%'";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<FyxxBean> fbList = new ArrayList<FyxxBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String gsmc = rs.getString("GSMC");
				String fyxmbm = rs.getString("FYXMBM");
				String fyxmmc = rs.getString("FYXMMC");
				Double ysje = rs.getDouble("YSJE");
				FyxxBean fb = new FyxxBean(gsmc, fyxmbm, fyxmmc, ysje);
				fbList.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return fbList;
	}
	
	/**
	 * 获取公司所有项目差旅费报销总金额
	 * @param fb
	 * @param k3bm
	 * @param year
	 * @return
	 */
	public FyxxBean getK3clf(FyxxBean fb, String k3bm, String year){
		String sql="SELECT SUM(FLOCEXPSUBMITAMOUNT) HFJE " + 
				"FROM BO_DY_JCXX_K3CLFBXDTB " + 
				"WHERE FEXPENSEDEPTID = '" + k3bm + "' " + 
				"AND FEXPID = '" + fb.getFYXMBM() + "' " + 
				"AND to_char(FDATE,'yyyy') LIKE '" + year + "' " + 
				"GROUP BY FEXPID";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				Double hfje = rs.getDouble("HFJE");
				fb.setHFJE(fb.getHFJE() + hfje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return fb;
	}

	/**
	 * 获取公司所有项目费用报销总金额
	 * @param fb
	 * @param k3bm
	 * @param year
	 * @return
	 */
	public FyxxBean getK3fybx(FyxxBean fb, String k3bm, String year) {
		String sql="SELECT SUM(FLOCEXPSUBMITAMOUNT) HFJE " + 
				"FROM BO_DY_JCXX_K3FYBXDTB " + 
				"WHERE FEXPENSEDEPTID = '" + k3bm + "' " + 
				"AND FEXPID = '" + fb.getFYXMBM() + "' " + 
				"AND to_char(FDATE,'yyyy') LIKE '" + year + "' " + 
				"GROUP BY FEXPID";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				Double hfje = rs.getDouble("HFJE");
				fb.setHFJE(fb.getHFJE() + hfje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return fb;
	}

	/**
	 * 获取公司所有项目其他费用总金额
	 * @param fb
	 * @param k3bm
	 * @param year
	 * @return
	 */
	public FyxxBean getK3qtck(FyxxBean fb, String k3bm, String year) {
		String sql="SELECT SUM(FAMOUNT) HFJE " + 
				"FROM BO_DY_JCXX_K3QTCKDTB " + 
				"WHERE FDEPTID = '" + k3bm + "' " + 
				"AND FCOSTITEM = '" + fb.getFYXMBM() + "' " + 
				"AND to_char(FDATE,'yyyy') LIKE '" + year + "' " + 
				"GROUP BY FCOSTITEM";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				Double hfje = rs.getDouble("HFJE");
				fb.setHFJE(fb.getHFJE() + hfje);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return fb;
	}

}
