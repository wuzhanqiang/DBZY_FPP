package com.nepharm.apps.fpp.biz.kms.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.kms.constant.KMSConstant;
import com.nepharm.apps.fpp.biz.kms.util.KMSUtil;
import com.nepharm.apps.fpp.biz.kms.web.MyKMSWeb;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.common.util.IsUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 知识管理Controller
 * @author sydq
 *
 */

@Controller
public class KMSController {
	
	@Mapping("com.nepharm.apps.fpp.mykms.indexpage")
	public String getMyKMSIndexPage() {
		MyKMSWeb web = new MyKMSWeb();
		return web.getMyKMSIndexPage();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.indexpage_portal")
	public String getMyKMSPortalPage() {
		MyKMSWeb web = new MyKMSWeb();
		return web.getMyKMSPortalPage();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.kmslist")
	public String getMyKMSList(String sid, String condition, String zslx) {
		MyKMSWeb web = new MyKMSWeb();
		String departmentPathId = web.getDepartmentPathId();
		String userId = web.getUid();
		UserContext userContext = web.getContext();
		String clientIp = userContext.getSessionIp();
		List<String> lanList = IsUtil.getLANIp();
		boolean ipState = IsUtil.checkIpState(lanList, clientIp);//true为内网
		String host = "";
		if(ipState) {
			host = "";
		}else {
			host = ConfigConstant.HOST_OUT+"/r/";
		}
		String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		ResponseObject ro = ResponseObject.newOkResponse();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from "+KMSConstant.TAB_KMS_ZSGL_S
					+" where ((instr('"+departmentPathId+"', BMBH) > 0 and instr(GWYDBM, '"+GWBM+"') > 0)"
					+" or CREATEUSER = '"+userId+"'"
					+" or (ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', YDBMBH)>0))"
					+" or instr(RYIDYDSQ, '"+userId+"')>0)";
			if("".equals(condition) && "".equals(zslx)) {
				
			}else if("".equals(condition) && !"".equals(zslx)){
				sql = sql+" and ZSLX = '"+zslx+"'";
			}else if(!"".equals(condition) && "".equals(zslx)) {
				sql = sql+" and WJMC like '%"+condition+"%'";
			}else if(!"".equals(condition) && !"".equals(zslx)) {
				sql = sql+" and WJMC like '%"+condition+"%' and ZSLX = '"+zslx+"'";
			}
			rset = stmt.executeQuery(sql);
			JSONArray array = KMSUtil.resultSetToJsonArry_M(rset, sid, host);
			ro.put("KMSList", array);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn);
		}
		return ro.toString();
		
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.kmslist_p")
	public String getMyKMSList_P(String sid) {
		MyKMSWeb web = new MyKMSWeb();
		String departmentPathId = web.getDepartmentPathId();
		String userId = web.getUid();
		UserContext userContext = web.getContext();
		String clientIp = userContext.getSessionIp();
		List<String> lanList = IsUtil.getLANIp();
		boolean ipState = IsUtil.checkIpState(lanList, clientIp);
		String host = "";
		if(ipState) {
			host="";
		}else {
			host=ConfigConstant.HOST_OUT+"/r/";
		}
		String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		ResponseObject ro = ResponseObject.newOkResponse();
		Connection conn = DBSql.open();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = conn.createStatement();
			String sql = "select * from "+KMSConstant.TAB_KMS_ZSGL_S
					+ " where ((instr('"+departmentPathId+"', BMBH) > 0 and instr(GWYDBM, '"+GWBM+"') > 0)"
					+ " or CREATEUSER = '"+userId+"'"
					+ " or (ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', YDBMBH)>0))"
					+ " or instr(RYIDYDSQ, '"+userId+"')>0"
					+ " or instr(GWBM, '"+GWBM+"')>0"
					+ " or (ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', BMBH)>0))"
					+ " or instr(RYIDSQ, '"+userId+"')>0" + ")";
//			String sql1 = "select * from "+KMSConstant.TAB_KMS_ZSGL_S+" where instr('"+departmentPathId+"', BMBH) > 0 and instr(GWBM, '"+GWBM+"') > 0";
//			String sql2 = "select * from "+KMSConstant.TAB_KMS_ZSGL_S+" where createuser = '"+userId+"'";
//			String sql3 = "select * from "+KMSConstant.TAB_KMS_ZSGL_S+" where ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', YDBMBH)>0)";
//			String sql4 = "select * from "+KMSConstant.TAB_KMS_ZSGL_S+" where instr(RYIDYDSQ, '"+userId+"')>0";
//			String sql = sql1+" union "+sql2+" union "+sql3+" union "+sql4;
			sql = "select * from ("+sql+") where rownum <=7 order by CREATEDATE desc";
			rset = stmt.executeQuery(sql);
			JSONArray array = KMSUtil.resultSetToJsonArry_P(rset, sid, host);
			ro.put("KMSList", array);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn);
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mykms.filepreviewurl")
	public String getFilePreviewURL(String boId, String WJMC) {
		MyKMSWeb web = new MyKMSWeb();
		UserContext me = web.getContext();
		DCContext sourceDc = KMSUtil.getDCContext(boId, "WJMC");
    	String url = KMSUtil.getFilePreviewURL(me, WJMC, sourceDc);
		return web.getFilePreviewPage(url);
	}
}
