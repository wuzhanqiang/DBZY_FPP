package com.nepharm.apps.fpp.biz.ppm.controller;

import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
/**
 * 生产计划模块相关控制器处理
 * @author lizhen
 *
 */
@Controller
public class ProductController {

	/**
	 * 月计划页面
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.prod_month_page")
	public String getProductMonthPage(String sid,UserContext me){
		ProductWeb web =new ProductWeb(me);
		
		return web.getMonthPage();
	}
}
