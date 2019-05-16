package com.nepharm.apps.fpp.biz.kms.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.kms.web.MyCourseWeb;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 培训管理Controller
 * @author sydq
 *
 */

@Controller
public class PXController {

	@Mapping("com.nepharm.apps.fpp.mykms.mycourse_page")
	public String getMyCoursePage() {
		MyCourseWeb web = new MyCourseWeb();
		return web.getMyCoursePage();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.mycourse_list")
	public String getMyCourseList(String sid, String kcmc, String sjmc, String kszt) {
		ResponseObject ro = ResponseObject.newOkResponse();
		MyCourseWeb web = new MyCourseWeb();
		String uid = web.getUid();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from BO_DY_KMS_PXJL where XYBH = '"+uid+"'";
			if(!"".equals(kszt)) {
				sql = sql + " and KSZT = '"+kszt+"'";
			}
			if(!"".equals(kcmc)) {
				sql = sql + " and KCMC like '%"+kcmc+"%'";
			}
			if(!"".equals(sjmc)) {
				sql = sql + " and SJMC like '%"+sjmc+"%'";
			}
			rset = stmt.executeQuery(sql);
			JSONArray array = new JSONArray();   
			ResultSetMetaData metaData = rset.getMetaData();   
		    int columnCount = metaData.getColumnCount();
		    while (rset.next()) {   
		    	JSONObject jsonObj = new JSONObject();   
		    	for (int i = 1; i <= columnCount; i++) {   
		    		String columnName =metaData.getColumnLabel(i);   
		    		String value = rset.getString(columnName);
		        	jsonObj.put(columnName, value);   
		    	}
		    	array.add(jsonObj);
		    }   
		    ro.put("MyCourseList", array);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.examine_page")
	public String getExaminePage(String sid, String boid) {
		MyCourseWeb web = new MyCourseWeb();
		return web.getExaminePage(boid);
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.question_page")
	public String getQuestionPage(String sid, String sjbh) {
		MyCourseWeb web = new MyCourseWeb();
		return web.getQuestionPage(sjbh);
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.examinationAnswer_submit")
	public String answerSubmit(String sid, String answer, String pxjhbh) {
		System.out.println(answer);
		ResponseObject ro = ResponseObject.newOkResponse();
		MyCourseWeb web = new MyCourseWeb();
		ro  = web.checkAnswer(answer, pxjhbh);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.examine_result_page")
	public String getExamineResultPage(String sid, String KCMC, String SJMC, String SCORE, String HGFS, String PASS) {
		MyCourseWeb web = new MyCourseWeb();
		return web.getExamineResultPage(KCMC, SJMC, SCORE, HGFS, PASS);
	}
}
