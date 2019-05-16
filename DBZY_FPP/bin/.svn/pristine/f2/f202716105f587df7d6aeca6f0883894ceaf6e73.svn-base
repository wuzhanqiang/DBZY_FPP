package com.nepharm.apps.fpp.biz.kms.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyCourseWeb extends ActionWeb {

	public MyCourseWeb() {
	}

	public MyCourseWeb(UserContext userContext) {
		super(userContext);
	}
	
	public String getMyCoursePage() {
		String sid = super.getContext().getSessionId();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms_mycourse.htm", macroLibraries);
	}
	
	public String getExaminePage(String boid) {
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		String sid = super.getContext().getSessionId();
		UserContext userContext = super.getContext();
		String uid = userContext.getUID();
		String userName = userContext.getUserName();
		BO pxjhBo = SDK.getBOAPI().get("BO_DY_KMS_PXJL", boid);		
		String SJBH = pxjhBo.getString("SJBH");
		int ZF = DBSql.getInt("select * from BO_DY_KMS_SJDA_M where SJBH = '"+SJBH+"'", "ZF");
		int HGFS = DBSql.getInt("select * from BO_DY_KMS_SJDA_M where SJBH = '"+SJBH+"'", "HGFS");
		macroLibraries.put("sid", sid);
		macroLibraries.put("examineeName", userName);
		macroLibraries.put("KCMC", pxjhBo.get("KCMC"));
		macroLibraries.put("SJMC", pxjhBo.get("SJMC"));
		macroLibraries.put("SJBH", SJBH);
		macroLibraries.put("totalScore", ZF);
		macroLibraries.put("HGFS", HGFS);
		macroLibraries.put("PXJHBH", pxjhBo.get("PXJHBH"));
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms.examine_page.htm", macroLibraries);
	}
	
	public String getQuestionPage(String sjbh) {
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select t2.* from BO_DY_KMS_SJDA_M t1"
					+ " join BO_DY_KMS_SJDA_S t2 on t1.BINDID = t2.BINDID"
					+ " where t1.SJBH = '"+sjbh+"'"
					+ " order by t2.STXH asc";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				ResultSetMetaData metaData = rset.getMetaData();   
			    int columnCount = metaData.getColumnCount();
				while(rset.next()) {
					JSONObject jsonObj = new JSONObject(); 
					JSONArray optionArray = new JSONArray();
			    	for (int i = 1; i <= columnCount; i++) {   
			    		String columnName =metaData.getColumnLabel(i);   
			    		String value = rset.getString(columnName);
			    		if(columnName.length()==1 && !StringUtil.isEmpty(value)) {
			    			JSONObject option = new JSONObject();
			    			option.put("code", columnName);
			    			option.put("content", value);
			    			optionArray.add(option);
			    			continue;
			    		}
			        	jsonObj.put(columnName, value);   
			    	}
			    	jsonObj.put("optionList", optionArray.toString());
			    	array.add(jsonObj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		macroLibraries.put("questionList", array);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms.examine_question_page.htm", macroLibraries);
	}
	
	public ResponseObject checkAnswer(String answer, String pxjhbh) {
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		int Score = 0;
		String SJMC = "";
		String KCMC = "";
		String KCBH = "";
		int HGFS = 0;
		String PASS = "";
		String uid = getUid();
		try {
			JSONObject object = JSONObject.fromObject(answer);
			String SJBH = object.getString("sjbh");
			String data = object.getString("data");
			Map<String, String> mapDa = new HashMap();
			Map<String, Integer> mapFz = new HashMap();
			stmt = conn.createStatement();
			String sql = "select t1.SJMC, t1.KCBH, t1.KCMC, t1.HGFS, t2.* from BO_DY_KMS_SJDA_M t1"
					+ " join BO_DY_KMS_SJDA_S t2 on t1.BINDID = t2.BINDID"
					+ " where t1.SJBH = '"+SJBH+"'";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				while(rset.next()) {
					if("".equals(SJMC)) {
						SJMC = rset.getString("SJMC");
					}
					if("".equals(KCBH)) {
						KCBH = rset.getString("KCBH");
					}
					if("".equals(KCMC)) {
						KCMC = rset.getString("KCMC");
					}
					if(HGFS == 0) {
						HGFS = rset.getInt("HGFS");
					}
					String CODE = rset.getString("CODE");
					String ZQDA = rset.getString("ZQDA");
					int FZ = rset.getInt("FZ");
					mapDa.put(CODE, ZQDA);
					mapFz.put(CODE, FZ);
				}
			}
			JSONArray array = JSONArray.fromObject(data);
			if(array != null && !array.isEmpty()) {
				Iterator itr = array.iterator();
				while(itr.hasNext()) {
					JSONObject obj = (JSONObject) itr.next();
					String stCode = obj.getString("code");
					String stValue = obj.getString("value");
					String ZQDA = mapDa.get(stCode);
					if(ZQDA.equals(stValue)) {
						int FZ = mapFz.get(stCode);
						Score += FZ;
					}
				}
			}
			if(Score >= HGFS ) {
				PASS = "通过";
			}else {
				PASS = "未通过";
			}
			//更新考试次数
			sql = "select * from BO_DY_KMS_PXJL where PXJHBH = '"+pxjhbh+"' and KCBH = '"+KCBH+"' and SJBH = '"+SJBH+"'"
					+ " and XYBH = '"+uid+"'";
			int KSCS = DBSql.getInt(conn, sql, "KSCS");
			KSCS++;
			sql = "update BO_DY_KMS_PXJL set KSCS = "+KSCS+", KSZT = '"+PASS+"' where PXJHBH = '"+pxjhbh+"' and KCBH = '"+KCBH+"' and SJBH = '"+SJBH+"'"
					+ " and XYBH = '"+uid+"'";
			DBSql.update(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		ResponseObject ro = ResponseObject.newOkResponse();
		ro.put("KCMC", KCMC);
		ro.put("SJMC", SJMC);
		ro.put("SCORE", Score);
		ro.put("HGFS", HGFS);
		ro.put("PASS", PASS);
		return ro;
	}
	
	public String getExamineResultPage(String KCMC, String SJMC, String SCORE, String HGFS, String PASS) {
		String sid = super.getContext().getSessionId();
		String userName = super.getContext().getUserName();
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid", sid);
		macroLibraries.put("USERNAME", userName);
		macroLibraries.put("KCMC", KCMC);
		macroLibraries.put("SJMC", SJMC);
		macroLibraries.put("SCORE", SCORE);
		macroLibraries.put("HGFS", HGFS);
		macroLibraries.put("PASS", PASS);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, ConfigConstant.APP_ID+".kms.examine_result_page.htm", macroLibraries);
	}
	
	public String getUid() {
		return getContext().getUID();
	}
	
	public UserContext getUserContext() {
		return getContext();
	}
}
