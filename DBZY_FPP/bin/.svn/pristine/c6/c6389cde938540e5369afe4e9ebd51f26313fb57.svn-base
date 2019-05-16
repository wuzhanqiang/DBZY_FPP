package com.nepharm.apps.fpp.biz.kms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.commons.formfile.model.delegate.FormFile;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.AppAPI;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.kms.web.MyKMSWeb;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.common.util.IsUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class KMSUtil {
	
	//获取附件下载URL
	public static String getDownLoadURL(String boId, String fieldName, String sid, String WJMC) {
		String url = "";
		BOAPI boapi = SDK.getBOAPI();
		List<FormFile> list = boapi.getFiles(boId, fieldName);
		if(list != null) {
			FormFile formFile = list.get(0);
			DCContext context = boapi.getFileDCContext(formFile);
			context.setFileNameShow(WJMC);
			context.setSecurityFileName(WJMC);
//			url = context.getDownloadURL();
			url = context.getStremURL();
			url = url.replace("sid=null", "sid="+sid);
		}
		return url;
	}
	
	//移动端 result转JSONArray
	public static JSONArray resultSetToJsonArry_M(ResultSet rs, String sid, String host) throws SQLException,JSONException{
		JSONArray array = new JSONArray();   
		ResultSetMetaData metaData = rs.getMetaData();   
	    int columnCount = metaData.getColumnCount();
	    while (rs.next()) {   
	    	JSONObject jsonObj = new JSONObject();   
	    	for (int i = 1; i <= columnCount; i++) {   
	    		String columnName =metaData.getColumnLabel(i);   
	    		String value = rs.getString(columnName);
	        	jsonObj.put(columnName, value);   
	    	}
	    	String boId = rs.getString("ID");
	    	String WJMC = rs.getString("WJMC");
	    	String url = KMSUtil.getDownLoadURL(boId, "WJMC", sid, WJMC);
	    	jsonObj.put("URL", host+url);
	    	array.add(jsonObj);
	    }   
	    return array;   
	}
	
	// PC端portal result转JSONArray
	public static JSONArray resultSetToJsonArry_P(ResultSet rs, String sid, String host) throws SQLException,JSONException{   
		MyKMSWeb web = new MyKMSWeb();
		UserContext me = web.getContext();
		JSONArray array = new JSONArray();   
		ResultSetMetaData metaData = rs.getMetaData();   
	    int columnCount = metaData.getColumnCount();
	    while (rs.next()) {   
	    	JSONObject jsonObj = new JSONObject();   
	    	for (int i = 1; i <= columnCount; i++) {   
	    		String columnName =metaData.getColumnLabel(i);   
	    		String value = rs.getString(columnName);
	        	jsonObj.put(columnName, value);   
	    	}
	    	String boId = rs.getString("ID");
	    	String WJMC = rs.getString("WJMC");
	    	//在线预览URL
//	    	String url = "./w?sid="+sid+"&cmd=com.nepharm.apps.fpp.mykms.filepreviewurl&boId="+boId+"&WJMC="+WJMC;
	    	//文件下载URL
	    	String url = getDownLoadURL(boId, "WJMC", sid, WJMC);
	    	jsonObj.put("URL", host+url);
	    	array.add(jsonObj);
	    }   
	    return array;   
	}
	
	//获取在线阅读URL
	public static String getFilePreviewURL(UserContext me, String fileNameOriginal, DCContext sourceDc) {
		String url = "";
		if(sourceDc == null) {
			return url;
		}
		AppAPI appApi = SDK.getAppAPI();
		if(appApi.isActive("com.actionsoft.apps.addons.onlinedoc")) {
			String sourceAppId = ConfigConstant.APP_ID;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sid", me.getSessionId());
			params.put("fileNameOriginal", fileNameOriginal);
			params.put("sourceDc", sourceDc);
			params.put("isShowDefaultToolbar", false);//工具条
			params.put("isCopy", true);//复制
			params.put("isPrint",false);//打印
            params.put("isDownload",false);//下载
            params.put("isEncrypt", true);//文件加密
            params.put("isShowBackbtn", false);
            ResponseObject responseObject = appApi.callASLP(appApi.getAppContext(sourceAppId), "aslp://com.actionsoft.apps.addons.onlinedoc/filePreview", params);
            if (responseObject != null) {
                if (responseObject.isOk()) {
                    url = responseObject.get("url").toString();
                }
                
                //判断内外网IP
        		String clientIp = me.getSessionIp();
        		List<String> lanList = IsUtil.getLANIp();
        		boolean ipState = IsUtil.checkIpState(lanList, clientIp);
        		if(!ipState) {
        			url = ConfigConstant.HOST_OUT + url.substring(url.indexOf("/r/w"), url.length());
        		}
                
            }
            
		}
		return url;
	}
	
	//获取附件DC对象
	public static DCContext getDCContext(String boId, String fieldName) {
		BOAPI boapi = SDK.getBOAPI();
		List<FormFile> list = boapi.getFiles(boId, fieldName);
		if(list != null && !list.isEmpty()) {
			FormFile formFile = list.get(0);
			DCContext sourceDc = boapi.getFileDCContext(formFile);
			return sourceDc;
		}else {
			return null;
		}
		
	}
	
	//获取允许下载的附件BOID集合
	public static List getDownloadSQ(String GWBM, UserContext me) {
		String userId = me.getUID();
		String departmentPathId = me.getDepartmentModel().getPathIdOfCache();
		List<String> list = new ArrayList<String>();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from BO_DY_KMS_ZSGL_S where instr(GWBM, '"+GWBM+"')>0 or createuser = '"+userId+"'"
					+ " or (ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', BMBH)>0)) or instr(RYIDSQ, '"+userId+"')>0";
			rset = stmt.executeQuery(sql);
			if(rset != null) {
				while(rset.next()) {
					String boId = rset.getString("ID");
					list.add(boId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, stmt, rset);
		}
		return list;
	}
}
