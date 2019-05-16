package com.nepharm.apps.fpp.biz.pem.controller;

import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp.bean.UserBean;
import com.nepharm.apps.fpp.is.ehr.constant.ManualSynchronousSalary;

/**
 * 绩效考核流程相关业务命令
 * @author lizhen
 *
 */
@Controller
public class PerformanceController {

	
	@Mapping("com.nepharm.apps.fpp.jxzs_tab_page")
	public String getTabPage(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getTabPage();
	}
	
	
	/**
	 * 我的绩效总入口
	 * @param sid
	 * @param me
	 * @param type
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_index_page")
	public String getIndexPage(String sid,UserContext me,String type){
		PerformanceWeb web = new PerformanceWeb(me);
		//TODO 访问人的请求的类型（操作岗、非操作岗）
		UserBean user= new UserBean(me.getUID());
		if(user.isOper()){
			return web.getOperIndexPage();
		}
		return web.getNOperIndexPage();
	}
	
	/**
	 * 岗位定额页面-操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_gwde")
	public String getOperGwde(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperGwdePage();
	}
	
	/**
	 * 岗位定额-量化
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_gwde_data")
	public String getOperGwdeData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperGwdeData(year,month);
	}
	/**
	 * 岗位定额页面-fei操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_noper_gwde")
	public String getNOperGwde(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getNOperGwdePage();
	}
	/**
	 * 岗位定额-非量化
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_noper_gwde_data")
	public String getNOperGwdeData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getNOperGwdeData(year,month);
	}
	/**
	 * 绩效总工资计算界面-操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jxzh")
	public String getOperJxzh(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperJxzhPage();
	}
	/**
	 * 绩效总工资计算数据-操作岗
	 * @param sid
	 * @param me
	 * @param year
	 * @param month
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jxzh_data")
	public String getOperJxzhData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperJxzhData(year,month);
	}
	/**
	 * 绩效总工资计算界面-非操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_noper_jxzh")
	public String getNOperJxzh(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getNOperJxzhPage();
	}
	/**
	 * 绩效总工资计算数据-非操作岗
	 * @param sid
	 * @param me
	 * @param year
	 * @param month
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_noper_jxzh_data")
	public String getNOperJxzhData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getNOperJxzhData(year,month);
	}
	/**
	 * 月计划/完成情况（103所有岗位）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_ty_jhwc")
	public String getPlanCommitPage(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getPlanCommitPage();
	}
	
	/**
	 * 月计划/完成情况-数据（103所有岗位）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_ty_jhwc_data")
	public String getPlanCommitData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getPlanCommitData(year,month);
	}
	
	/**
	 * 月完成情况-echart（103所有岗位）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_ty_jhwc_echart")
	public String getPlanCommitEchartPage(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getPlanCommitEchartPage();
	}
	
	/**
	 * 月完成情况-Echart数据（103所有岗位）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_ty_jhwc_echart_data")
	public String getPlanCommitEchartData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getPlanCommitEchartData(year,month);
	}
	
	
	/**
	 * 岗位实时绩效计算（103通用）
	 * @param sid
	 * @param me
	 * @param type
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_ty_gwjxjs")
	public String getGwjxjsPage(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getGwjxjsPage();
	}
	
	/**
	 * 岗位实时绩效计算数据（103通用）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jxjs_data")
	public String getJxjsData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getJxjsData( year, month);
	}
	
	/**
	 * 岗位实时绩效计算数据-工序详情（103通用）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jxjsmx_data")
	public String getJxjsmxData(String sid,UserContext me,String year,String month,String gxbm){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getJxjsmxData( year, month,gxbm);
	}
	
	/**
	 * 考核得分页面-操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_khdf")
	public String getOpeKhdf(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperKhdfPage();
	}
	/**
	 *  考核得分页面-操作岗
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_noper_khdf")
	public String getNOpeKhdf(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getNOperKhdfPage();
	}
	/**
	 * 绩效成绩
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_khdf_data")
	public String getOperKhdfData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperKhdfData(year,month);
	}
	/**
	 * 制度奖惩 页面
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_zdjc")
	public String getOperZdjc(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperZdjcPage();
	}
	/**
	 * 制度奖惩
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_zdjc_data")
	public String getOperZdjcData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperZdjcData(year,month);
	}
	/**
	 * 嘉奖否定 页面
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jjfd")
	public String getOperJjfd(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperJjfdPage();
	}
	/**
	 * 嘉奖否定
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_oper_jjfd_data")
	public String getOperJjfdData(String sid,UserContext me,String year,String month){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getOperJjfdData(year,month);
	}
	
	/**
	 * 收藏功能（我的绩效）
	 * @param sid
	 * @param me
	 * @param bdid
	 * @param cmdid
	 * @param mc
	 * @param op
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_sc_add")
	public String updateSCData(String sid,UserContext me,String bdid,String cmdid,String mc,String op){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.updateSCData(bdid,cmdid,mc,op);
	}
	/**
	 * 收藏数据（我的绩效）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jxzs_sc_data")
	public String getSCData(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getSCData();
	}
	/**
	 * 获取 tab页的收藏数据（转换成功 <li> 形式的）
	 * @param sid
	 * @param me
	 * @return
	 */
	@Deprecated
	@Mapping("com.nepharm.apps.fpp.jxzs_sc_tab_data")
	public String getSCTabData(String sid,UserContext me){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.getSCTabData();
	}
	
	/**
	 * 日产量数据删除命令
	 * 2018-07-16新增需求
	 * @param sid
	 * @param me
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.czg_rcl_del")
	public String getDayProductDataDelete(String sid,UserContext me,String ids){
		PerformanceWeb web = new PerformanceWeb(me);
		return web.dayProductDataDelete(ids).toString();
	}
	/**
	 * 手动更新同步HR基本工资信息
	 * 2018-08-10新增需求 zhangjh
	 * @return
	 */
	@Mapping("com.nepharm.apps.fpp.jcxx_hrgztb_tb")
	public String synchronousSalary(){
		ManualSynchronousSalary web = new ManualSynchronousSalary();
		return web.manualSynchronousSalary().toString();
	}
}
