package com.nepharm.apps.fpp.biz.jcm.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.jcm.bean.CfrwBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KctmmBean;
import com.nepharm.apps.fpp.biz.jcm.bean.KscjBean;
import com.nepharm.apps.fpp.biz.jcm.bean.YgxxBean;
import com.nepharm.apps.fpp.biz.jcm.dao.JCMFLDao;
import com.nepharm.apps.fpp.biz.jcm.dao.JCMGWGLDao;
import com.nepharm.apps.fpp.biz.jcm.dao.JCMKSTMXXDao;
import com.nepharm.apps.fpp.biz.jcm.dao.JCMTKDao;
import com.nepharm.apps.fpp.biz.jcm.web.JCMWeb;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

/**
 * 岗位课程管理Controller
 * @author liuyc
 *
 */
@Controller
public class JCMController {
	

	/***
	 * 岗位课程信息列表页
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jcm.indexpage_portal")
	public String getMyResourceIndexPage_P() {
		JCMWeb web = new JCMWeb();
		return web.getMyResourceIndexPage_P();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.view")
	public String jcmView(String pzbm, boolean isXX) {
		JCMWeb web = new JCMWeb();
		if(isXX) {
			JCMTKDao dao = new JCMTKDao();
			List<CfrwBean> cbList = dao.getGwkcxxsByPzbm(pzbm);
			if(cbList != null && !cbList.isEmpty()) {
				for(CfrwBean cb : cbList) {
					String sql = "SELECT BKHRZH FROM BO_DY_KMS_YGCJ_M WHERE BKHRZH='" +web.getUserId()+ "' AND KCBM='" + cb.getKcbm() + "'";
					String zh = DBSql.getString(sql,"BKHRZH");
					if(StringUtil.isEmpty(zh))
						dao.createYgcj(cb, pzbm, web.getUserId(), web.getUserName());
				}
			}
		}
		
		return web.getView(pzbm);
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.show")
	public String jcmShow(String bindId) {
		JCMWeb web = new JCMWeb();
		return web.getShow(bindId);
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.index_tk")
	public String jcmView() {
		JCMWeb web = new JCMWeb();
		return web.getTkList();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.flsz")
	public String flsz() {
		JCMWeb web = new JCMWeb();
		return web.getFlsz();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.tk.add")
	public String jcmAdd(String bindId) {
		JCMWeb web = new JCMWeb();
		return web.getTkAdd(bindId);
	}
	
	
	@Mapping("com.nepharm.apps.fpp.jcm.kcxx")
	public String getKcxx(String pzbm) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMGWGLDao dao = new JCMGWGLDao();
		JCMWeb web = new JCMWeb();
		List<KscjBean> arr = dao.getKcxx(web.getUserId(), pzbm);
		if(arr != null && !arr.isEmpty()) {
			int ywcNum = 0;
			for(KscjBean k : arr) {
				if(k.getWczt().equals("已完成")) {
					ywcNum = ywcNum + 1;
				}
			}
			KscjBean kb = arr.get(0);
			if(kb != null) {
				YgxxBean yb = dao.getYgxx(kb.getGsbm(), kb.getGwbm());
				if(yb != null) {
					yb.setUserId(web.getUserId());
					yb.setUserName(web.getUserName());
					if(ywcNum == 0) {
						yb.setXxjd("0%");
					} else {
						DecimalFormat dFormat = new DecimalFormat("#.00");
						yb.setXxjd(dFormat.format((float)ywcNum / arr.size() * 100) + "%");
					}
					ro.put("ygxx", yb);
				}
			}
		}
		ro.put("kcxxList", arr);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.kstmxx")
	public String getKstmxx(String kcbm) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMKSTMXXDao dao = new JCMKSTMXXDao();
		ro.put("kstmxxList", dao.getKstmxx(kcbm));
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.cjztgx")
	public String kscjztUpdate(String kcbm, String userId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMKSTMXXDao dao = new JCMKSTMXXDao();
		if(dao.kscjztUpdate(kcbm, userId) > 0) {
			ro.put("result", "ok");
		}else {
			ro.put("result", "");
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.gwkcxx")
	public String getGwkcList() {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMWeb web = new JCMWeb();
		String userId = web.getUserId();
		String gsbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GSBM)");
		String gwbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		
		JCMGWGLDao dao = new JCMGWGLDao();
		ro.put("gwkcList", dao.getGwkcList(gsbm,gwbm));
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.kctk")
	public String getKctkList() {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		JCMWeb web = new JCMWeb();
		ro.put("KctkList", dao.getCktkList(web.getUserId()));
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.kctkedit")
	public String kctkEdit(String bindId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		KctmmBean kb = dao.getKctmModel(bindId);
		if(kb != null) {
			ro.put("kb", kb);
			JCMKSTMXXDao jstDao = new JCMKSTMXXDao();
			ro.put("tmList", jstDao.getKstmxx(kb.getKcbm()));
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.kcxxsave")
	public String kcxxSave(String kctm, String kcsm, String xxdz, String bindId, String flId) {
		JCMTKDao dao = new JCMTKDao();
		JCMWeb web = new JCMWeb();
		String bId = dao.kcxxSave(web.getUserId(), kctm, kcsm, xxdz, bindId, flId);
		ResponseObject ro = ResponseObject.newOkResponse();
		ro.put("bindId", bId);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.tmxxsave")
	public String tmxxsave(String khtm, String bindId, String a, String b, String c, String d, String e, String f, String zqda) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		JCMWeb web = new JCMWeb();
		dao.tmxxsave(web.getUserId(), khtm, bindId, a, b, c, d, e, f, zqda);
		ro.put("ok", 1);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.tmDelete")
	public String tmDelete(String tmId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		dao.tmDelete(tmId);
		ro.put("ok", 1);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.getZlList")
	public String getZlList(String parentId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMFLDao dao = new JCMFLDao();
		JSONArray arr = dao.getZlList(parentId);
		ro.put("rows", arr);
		ro.put("total", arr.size());
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.zflSave")
	public String zflSave(String parentId, String flmcs) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMFLDao dao = new JCMFLDao();
		JCMWeb web = new JCMWeb();
		dao.zflSave(web.getUserId(), parentId, flmcs);
		ro.put("ok", 1);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.getAllZlList")
	public String getAllZlList() {
		JCMFLDao dao = new JCMFLDao();
		return JSONArray.fromObject(dao.getAllZlList()).toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.fflUpdate")
	public String fflUpdate(String id, String flmcm) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMFLDao dao = new JCMFLDao();
		dao.fflUpdate(id, flmcm);
		ro.put("ok", 1);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.zflDelete")
	public String zflDelete(String id) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMFLDao dao = new JCMFLDao();
		dao.zflDelete(id);
		ro.put("ok", 1);
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.getAllBxkcList")
	public String getAllBxkcList(String kctm,String kcsm,String xxdz,String flid,int pageSize,int pageIndex) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		JCMWeb web2 = new JCMWeb();
		String userId = web2.getUserId();
		String gsbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GSBM)");
		String gwbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		List<String> flidList = new ArrayList<String>();
		
		String temp = "'"+flid+"'"; 
		flidList.add(temp);
		boolean bol = true;
		if(StringUtils.isNotEmpty(temp))
			while(bol) {
				temp = dao.getFlid(temp);
				if(StringUtils.isEmpty(temp)) {
					bol = false;
				}else {
					flidList.add(temp);
				}
			}
		flid = StringUtils.strip(flidList.toString(),"[]");
		JSONArray arr = dao.getAllBxkcList(kctm,kcsm,xxdz,flid,gsbm,gwbm,pageSize,pageIndex);
		ro.put("rows", arr);
		ro.put("total", dao.getTotal(kctm,kcsm,xxdz,flid,gsbm,gwbm,1));
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.jcm.getAllXxkcList")
	public String getAllXxkcList(String kctm,String kcsm,String xxdz,String flid,int pageSize,int pageIndex) {
		ResponseObject ro = ResponseObject.newOkResponse();
		JCMTKDao dao = new JCMTKDao();
		JCMWeb web = new JCMWeb();
		String userId = web.getUserId();
		String gsbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GSBM)");
		String gwbm = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		List<String> flidList = new ArrayList<String>();
		
		String temp = "'"+flid+"'"; 
		flidList.add(temp);
		boolean bol = true;
		if(StringUtils.isNotEmpty(temp))
			while(bol) {
				temp = dao.getFlid(temp);
				if(StringUtils.isEmpty(temp)) {
					bol = false;
				}else {
					flidList.add(temp);
				}
			}
		flid = StringUtils.strip(flidList.toString(),"[]");
		JSONArray arr = dao.getAllXxkcList(kctm,kcsm,xxdz,flid,gsbm,gwbm,pageSize,pageIndex);
		ro.put("rows", arr);
		ro.put("total", dao.getTotal(kctm,kcsm,xxdz,flid,gsbm,gwbm,2));
		return ro.toString();
	}
}
