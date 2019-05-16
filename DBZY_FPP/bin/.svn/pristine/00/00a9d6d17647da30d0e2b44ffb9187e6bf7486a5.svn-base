package com.nepharm.apps.fpp.biz.tam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.bean.UserBean;
import com.nepharm.apps.fpp.biz.pem.bean.SCBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.controller.PerformanceBiz;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceBizDao;
import com.nepharm.apps.fpp.biz.tam.dao.GzpjDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.StringUtil;

/**
 * 工作评价相关业务实现
 * @author shibin
 */
public class GzpjWeb extends ActionWeb {

	public GzpjWeb(UserContext me){
		super(me);
	}
	
	/**
	 * 工作评分表首页
	 * @return
	 */
	public String getWorkGradeIndexPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("select_year",PerformanceBiz.getYearList());
		macroLibraries.put("select_month",PerformanceBiz.getMonthList());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID,
				ConfigConstant.APP_ID+".workgrade_index_page.htm", 
				macroLibraries);
	}
	/**
	 * 工作评分数据
	 * @return
	 */
	public String getWorkGradeData(String year,String month) {
		UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		JSONObject result = new JSONObject();//主表
		GzpjDao dao = new GzpjDao();
		JSONArray list=dao.getWorkGradeInfo(user.getUid(),year,month);//列表
		result.put("data", list.toString());
		return result.toString();
	}
}
