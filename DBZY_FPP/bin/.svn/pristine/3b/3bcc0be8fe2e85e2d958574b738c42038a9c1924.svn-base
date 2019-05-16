package com.nepharm.apps.fpp.biz.jcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.jcm.bean.XXDXFLBean;

import net.sf.json.JSONArray;

public class JCMFLDao {

	public JSONArray getZlList(String parentId) {
		String sql = "SELECT ID,FLMC FROM BO_DY_KMS_XXDZFL WHERE PARENTID = '" + parentId + "'";
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
				String flmc = rs.getString("FLMC");
				XXDXFLBean xb = new XXDXFLBean();
				xb.setName(flmc);
				xb.setId(id);
				data.add(xb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public void zflSave(String userId, String parentId, String flmcs) {
		String uuid = UUID.randomUUID().toString();
		String insertSQL="insert into BO_DY_KMS_XXDZFL (ID,CREATEUSER,FLMC,PARENTID,ISOPEN) values('"+uuid+"','"+userId+"','"+flmcs+"','"+parentId+"','"+0+"')";
		DBSql.update(insertSQL);
	}

	public List<XXDXFLBean> getAllZlList() {
		String sql = "SELECT ID,PARENTID,FLMC FROM BO_DY_KMS_XXDZFL";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<XXDXFLBean> data = new ArrayList<XXDXFLBean>();
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				String id = rs.getString("ID");
				String flmc = rs.getString("FLMC");
				String parentId = rs.getString("PARENTID");
				XXDXFLBean xb = new XXDXFLBean();
				xb.setName(flmc);
				xb.setId(id);
				xb.setPid(parentId);
				data.add(xb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	public void fflUpdate(String id, String flmcm) {
		String sql = "UPDATE BO_DY_KMS_XXDZFL SET FLMC = ? WHERE ID = ?";
		DBSql.update(sql, new Object[] { flmcm, id});
	}

	public void zflDelete(String id) {
		String sql = "delete from BO_DY_KMS_XXDZFL where id = ?";
		DBSql.update(sql, new Object[] {id});
		
	}

	
}
