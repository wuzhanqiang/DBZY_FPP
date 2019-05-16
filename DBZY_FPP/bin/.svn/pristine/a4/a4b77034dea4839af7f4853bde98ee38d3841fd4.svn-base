package com.nepharm.apps.fpp.biz.ppm.controller;

import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.controller.PerformanceBiz;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 生产计划模块-业务实现
 * @author lizhen
 *
 */
public class ProductWeb extends ActionWeb{
	public ProductWeb(UserContext me){
		super(me);
	}
	
	
	public String getMonthPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		//macroLibraries.put("select_year",PerformanceBiz.getYearList());
		//macroLibraries.put("select_month",PerformanceBiz.getMonthList());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID,
				ConfigConstant.APP_ID+".prod_month_page.htm",
				macroLibraries);
	}
}
