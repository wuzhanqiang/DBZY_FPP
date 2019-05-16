package com.nepharm.apps.fpp.biz.kms.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.PermAPI;
import com.nepharm.apps.fpp.biz.kms.web.MyKMSWeb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class CMSController {

	@Mapping("com.nepharm.apps.fpp.mykms.cmslist")
	public String getMyCMSList() {
		ResponseObject ro = ResponseObject.newOkResponse();
		JSONArray array = new JSONArray();
		MyKMSWeb web = new MyKMSWeb();
		String uid = web.getUid();
		PermAPI permApi = SDK.getPermAPI();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from APP_ACT_CMS_DATA order by createtime desc";
			sql = "select * from ("+sql+") where rownum <=6";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				while(rset.next()) {
					JSONObject obj = new JSONObject();
					String ID = rset.getString("ID");
					String MSGTITLE = rset.getString("MSGTITLE");
					String CREATETIME = rset.getString("CREATETIME").substring(0, 10);
					
					boolean ac = permApi.havingACPermission(uid, "platform.process", ID, 0);//授权，暂不用

					obj.put("ID", ID);
					obj.put("MSGTITLE", MSGTITLE);
					obj.put("CREATETIME", CREATETIME);
					array.add(obj);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		ro.put("CMSList", array);
		return ro.toString();
	}
}
