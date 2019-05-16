package com.nepharm.apps.fpp.biz.tam.controller;

import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp.bean.UserBean;
import com.nepharm.apps.fpp.biz.pem.controller.PerformanceWeb;

/**
 * 工作评价相关业务命令
 * @author shibin
 *
 */
@Controller
public class GzpjController {
	
	/**
	 * 入口
	 * @param sid
	 * @param me
	 * @param type
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.workgrade_index_page")
	public String getIndexPage(String sid,UserContext me,String type){
		GzpjWeb web = new GzpjWeb(me);
		//TODO 访问人的请求的类型（操作岗、非操作岗）
		UserBean user= new UserBean(me.getUID());
		
		return web.getWorkGradeIndexPage();
	}
	/**
	 * 工作评分表
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.work_grade_data")
	public String getWorkGradeData(String sid,UserContext me,String year,String month){
		GzpjWeb web = new GzpjWeb(me);
		return web.getWorkGradeData(year,month);
	}
	
}
