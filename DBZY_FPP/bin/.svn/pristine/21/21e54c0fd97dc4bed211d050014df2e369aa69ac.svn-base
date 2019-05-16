package com.nepharm.apps.fpp2.biz.yyb.controller;

import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp2.biz.yyb.web.YYBWeb;


/**
 * 运营部生产计划Controller
 * @author shibin
 *
 */
@Controller
public class YYBController {
	

	/***
	 * 原料药生产计划变更-子表数据初始化
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp2.yyb.ylyscjhbg_zbcsh")
	public String Ylyscjhbg_zbcsh(String sid, String bindid, String ybindid, String gsbm) {
		YYBWeb web = new YYBWeb();
		return web.Ylyscjhbg_zbcsh(sid,bindid,ybindid,gsbm);
	}
	
	
}
