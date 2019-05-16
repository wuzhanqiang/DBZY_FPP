package com.nepharm.apps.fpp.biz.zbgl.controller;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.zbgl.bean.FjBean;
import com.nepharm.apps.fpp.biz.zbgl.bean.SyBean;
import com.nepharm.apps.fpp.biz.zbgl.bean.ZBBean;
import com.nepharm.apps.fpp.biz.zbgl.bean.ZBXXBean;
import com.nepharm.apps.fpp.biz.zbgl.dao.ZBDao;
import com.nepharm.apps.fpp.biz.zbgl.event.ZbglSytz;
import com.nepharm.apps.fpp.biz.zbgl.util.ZBUtil;
import com.nepharm.apps.fpp.biz.zbgl.web.ZBGLWeb;

import jodd.util.StringUtil;

/**
 * 岗位课程管理Controller
 * @author liuyc
 *
 */
@Controller
public class ZBGLController {
	@Mapping("com.nepharm.apps.fpp.zbgl.index")
	public String getMyResourceIndexPage_P() {
		ZBGLWeb web = new ZBGLWeb();
		return web.getIndexPage();
	}
	
	@Mapping("com.nepharm.apps.fpp.zbgl.getZbXXById")
	public String getZbXXById(String year, String month, String week, String sqrzh, String sid) {
		ZBDao dao  = new ZBDao();
		ZBGLWeb web = new ZBGLWeb();
		ResponseObject ro = ResponseObject.newOkResponse();
		ZBXXBean zb = dao.getZbXXById(year, month, week, sqrzh);
		ro.put("zb", zb);
		if(zb != null) {
			List<SyBean> sbList = dao.getSyListBy(zb.getBindId(), web.getUserId());
			ro.put("sbList", sbList);
			List<FjBean> fbList = ZBUtil.getDownLoadURL(zb.getId(), "FJ", sid, "附件");
			ro.put("fbList", fbList);
		}
		return ro.toString();
	}
	
	/**
	 * 最新周报提交列表数据集
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.zbgl.getZbTJList")
	public String getZbTJList() {
		ZBGLWeb web = new ZBGLWeb();
		ZBDao dao  = new ZBDao();
		ResponseObject ro = ResponseObject.newOkResponse();
		List<ZBBean> list = dao.getZXZB(web.getUserId());
		if(list != null && !list.isEmpty()) {
			ZBBean zb = list.get(0);
			List<ZBBean> zbList = dao.getSqrList(web.getUserId());
			if(zbList != null && !zbList.isEmpty()) {
				for(ZBBean zbBean : zbList) {
					zbBean.setMonth(zb.getMonth());
					zbBean.setWeek(zb.getWeek());
					zbBean.setYear(zb.getYear());
					zbBean = dao.getZbModel(web.getUserId(), zb.getYear(), zb.getMonth(), zb.getWeek(), zbBean);
				}
			}
			ro.put("zbList", zbList);
		}
		return ro.toString();
	}
	
	/**
	 * 条件搜索
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.zbgl.zbSearch")
	public String zbSearch(String year, String month, String week, String dwsx, String isEmpty) {
		ZBGLWeb web = new ZBGLWeb();
		ZBDao dao  = new ZBDao();
		ResponseObject ro = ResponseObject.newOkResponse();
		List<ZBBean> list = dao.getZXZBs(web.getUserId(), dwsx, year, month, week);
		if(list != null && !list.isEmpty()) {
			ZBBean zb = list.get(0);
			List<ZBBean> zbList = dao.getSqrListByDwsx(web.getUserId(), dwsx);
			List<ZBBean> wkList = new ArrayList<ZBBean>();
			if(zbList != null && !zbList.isEmpty()) {
				for(ZBBean zbBean : zbList) {
					zbBean.setMonth(zb.getMonth());
					zbBean.setWeek(zb.getWeek());
					zbBean.setYear(zb.getYear());
					zbBean = dao.getZbModel(web.getUserId(), zb.getYear(), zb.getMonth(), zb.getWeek(), zbBean);
					if(isEmpty.equals("20")) {
						ZBXXBean zb1 = dao.getZbXXById(year, month, week, zbBean.getSqrzh());
						if(zb1 != null && StringUtil.isEmpty(zb1.getNr()) && StringUtil.isEmpty(zb1.getNr2()) && StringUtil.isEmpty(zb1.getNr3()) && StringUtil.isEmpty(zb1.getNr4()) && StringUtil.isEmpty(zb1.getNr5()) && StringUtil.isEmpty(zb1.getZj())) {
							wkList.add(zbBean);
						}
					}
					if(isEmpty.equals("10")) {
						ZBXXBean zb1 = dao.getZbXXById(year, month, week, zbBean.getSqrzh());
						if(zb1 != null && (StringUtil.isNotEmpty(zb1.getNr()) || StringUtil.isNotEmpty(zb1.getNr2()) && StringUtil.isNotEmpty(zb1.getNr3()) && StringUtil.isNotEmpty(zb1.getNr4()) && StringUtil.isNotEmpty(zb1.getNr5()) && StringUtil.isNotEmpty(zb1.getZj()))) {
							wkList.add(zbBean);
						}
					}
					
				}
			}
			if(isEmpty.equals("0"))
				ro.put("zbList", zbList);
			else
				ro.put("zbList", wkList);
		}
		return ro.toString();
	}
	
	@Mapping("com.nepharm.apps.fpp.zbgl.sylySave")
	public String sylySave(String syly, String bindId, String year, String month, String week, String sqrzh) {
		
		ResponseObject ro = ResponseObject.newOkResponse();
		//写审阅留言
		ZBGLWeb web = new ZBGLWeb();
		String id = DBSql.getString("SELECT ID FROM BO_DY_ZBSB_SYLY WHERE BINDID=? AND SQRZH=?", new Object[] { bindId, web.getUserId() });
		if(StringUtil.isNotEmpty(id)) {
			DBSql.update("UPDATE BO_DY_ZBSB_SYLY SET SYLY = ? WHERE ID = ?",new Object[] { syly, id });
		}else {
			BO b = new BO();
			b.set("SQR", web.getUserName());
			b.set("SQRZH", web.getUserId());
			b.set("ZT", "1");
			b.set("SYLY", syly);
			SDK.getBOAPI().create("BO_DY_ZBSB_SYLY", b, bindId, web.getUserId());
			
			//发通知
			ZbglSytz tz = new ZbglSytz();
			tz.sytz(bindId, year, month, week, web.getUserName(), web.getUserId(), sqrzh);
		}
		ro.put("ok", 1);
		return ro.toString();
	}
}
