package com.nepharm.apps.fpp.biz.mr.controller;

import java.util.List;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.mr.bean.FyxxBean;
import com.nepharm.apps.fpp.biz.mr.dao.MRBGYPXXDao;
import com.nepharm.apps.fpp.biz.mr.dao.MRFYXXDao;
import com.nepharm.apps.fpp.biz.mr.dao.MRSBDADao;
import com.nepharm.apps.fpp.biz.mr.dao.MRWPDADao;
import com.nepharm.apps.fpp.biz.mr.dao.MrUserDao;
import com.nepharm.apps.fpp.biz.mr.util.MRUtil;
import com.nepharm.apps.fpp.biz.mr.web.MRWeb;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;

/**
 * 我的资源Controller
 * @author sydq
 *
 */
@Controller
public class MRController {
	
	@Mapping("com.nepharm.apps.fpp.mr.indexpage_portal")
	public String getMyResourceIndexPage_P() {
		MRWeb web = new MRWeb();
		return web.getMyResourceIndexPage_P();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.indexpage")
	public String getMyResourceIndexPage() {
		MRWeb web = new MRWeb();
		return web.getMyResourceIndexPage();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.indexpage_m")
	public String getMyResourceIndexPage_M() {
		MRWeb web = new MRWeb();
		return web.getMyResourceIndexPage_M();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.ryxx")
	public String getRYXX(String userId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		boolean isShow = MRUtil.getSQ(userId, "RY");//人员授权
		if(isShow) {
			StringBuffer sb = new StringBuffer();
			MrUserDao urd = new MrUserDao();
			String departmentId = urd.getDepartmentId(userId);
			if(StringUtil.isNotEmpty(departmentId)) {
				sb.append("'").append(departmentId).append("'").append(",");
				String ids = urd.getDepartmentData(departmentId);
				if(StringUtil.isNotEmpty(ids)) {
					sb.append(ids);
					//查到最底层部门为止
					while(StringUtil.isNotEmpty(urd.getDepartmentIds(ids))) {
						ids = urd.getDepartmentIds(ids);
						sb.append(ids);
					}
				}
			}
			ro.put("userDetailList", urd.getUserInfo(sb.toString().substring(0, sb.length() - 1)));
		}else {
			ro.put("userDetailList", "");
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.fyxx")
	public String getFYXX(String year, String userId) {
		boolean isShow = MRUtil.getSQ(userId, "FY");//费用授权
		if(isShow) {
			MRFYXXDao dao = new MRFYXXDao();
			String no1code = dao.getNo1Code(userId);
			List<FyxxBean> fbList = null;
			if(StringUtil.isNotEmpty(no1code)) {
				String k3bm = dao.getK3Code(no1code);
				fbList = dao.getNcfyyswh(no1code, year);
				if(fbList != null && !fbList.isEmpty()) {
					for(FyxxBean fb : fbList) {
						//fb = dao.getK3clf(fb, k3bm, year);//获得差旅费报销总金额
						fb = dao.getK3fybx(fb, k3bm, year);//获得费用报销总金额
						fb = dao.getK3qtck(fb, k3bm, year);//获得其他费用支出总金额
					}
				}
			}
			return getJsonArray(fbList);			
		}else {
			return getJsonArray(null);
		}
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.bgyp")
	public String getBGYPXX(String userId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		MRBGYPXXDao dao = new MRBGYPXXDao();
		ro.put("bgypxxList", dao.getBgypxx(userId));
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.sb")
	public String getSBDAXX(String userId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		MRWeb web = new MRWeb();
		boolean isShow = MRUtil.getSQ(userId, "WP");//物品授权
		if(true) {
			String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");//岗位编码
			String bmallpathId = web.getContext().getDepartmentModel().getPathIdOfCache();
			String bmallpathName = web.getContext().getDepartmentModel().getPathNameOfCache();
			MRSBDADao dao = new MRSBDADao();
			ro.put("sbdaList", dao.getSbdaxx(bmallpathId, bmallpathName, GWBM));
		}else {
			ro.put("sbdaList", "");
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.mr.wp")
	public String getWPDAXX(String wplx, String userId) {
		ResponseObject ro = ResponseObject.newOkResponse();
		MRWeb web = new MRWeb();
		boolean isShow = MRUtil.getSQ(userId, "WP");//物品授权
		if(isShow) {
			String bmallpathId = web.getContext().getDepartmentModel().getPathIdOfCache();
			MRWPDADao dao = new MRWPDADao();
			ro.put("wpdaList", dao.getWpdaxx(bmallpathId, wplx));
		}else {
			ro.put("wpdaList", "");
		}
		return ro.toString();
	}
	
	/**
	 * 计算剩余金额
	 * @param list
	 * @return
	 */
	public String getJsonArray(List<FyxxBean> list) {
		ResponseObject ro = ResponseObject.newOkResponse();
		if(list != null && !list.isEmpty()) {
			for(FyxxBean fb : list) {
				fb.setSYJE(fb.getYSJE() - fb.getHFJE());
			}
			JSONArray jsonArray =JSONArray.fromObject(list);//把list转成JSONArray
			ro.put("fyxxList", jsonArray);
		}else {
			ro.put("fyxxList", "");
		}
		
		return ro.toString();
	}
}
