package com.nepharm.apps.fpp.biz.pem.controller;

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
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceBizDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.StringUtil;

/**
 * 绩效考核流程相关业务实现
 * @author lizhen
 */
public class PerformanceWeb extends ActionWeb {

	public PerformanceWeb(UserContext me){
		super(me);
	}
	
	/**
	 * 首页-我的绩效（自定义tab页签）
	 * @return
	 */
	public String getTabPage(){
		
		String uid=this.getContext().getUID();
		PerformanceBizDao dao = new PerformanceBizDao();
		List<SCBean> list=dao.getSCTabData(uid);
		String tab="";
		String div="";
		for(SCBean bean:list){
			tab=tab+bean.getTabString();
			div=div+bean.getDivString(this.getContext().getSessionId());
		}
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("ispc",this.getContext().getDeviceType());
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("tabList", tab);
		macroLibraries.put("divList", div);
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".jx_tab_page.htm", 
				macroLibraries);
	}
	
	
	/**
	 * 量化岗-绩效首页
	 * @return
	 */
	public String getOperIndexPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("select_year",PerformanceBiz.getYearList());
		macroLibraries.put("select_month",PerformanceBiz.getMonthList());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID,
				ConfigConstant.APP_ID+".oper_index_page.htm",
				macroLibraries);
	}
	/**
	 * 非量化岗-绩效首页
	 * @return
	 */
	public String getNOperIndexPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("select_year",PerformanceBiz.getYearList());
		macroLibraries.put("select_month",PerformanceBiz.getMonthList());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID,
				ConfigConstant.APP_ID+".noper_index_page.htm", 
				macroLibraries);
	}
	/**
	 * 量化岗-岗位定额页面
	 * @return
	 */
	public String getOperGwdePage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_gwde.htm", 
				macroLibraries);
	}
	
	/**
	 * 操作岗-岗位定额数据
	 * @return
	 */
	public String getOperGwdeData(String year,String month) {
		UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getGWGXInfo(user);//列表
		result.put("data", list.toString());
		return result.toString();
	}
	/**
	 * fei量化岗-岗位定额页面
	 * @return
	 */
	public String getNOperGwdePage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".noper_id_gwde.htm", 
				macroLibraries);
	}
	/**
	 * 非操作岗-岗位定额数据
	 * @return
	 */
	public String getNOperGwdeData(String year,String month) {
		UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONObject result=dao.getNGWGXInfo(user);//列表
		if(result==null){
			return "";
		}
		return result.toString();
	}
	/**
	 * 量化岗-绩效奖金总和页面
	 * @return
	 */
	public String getOperJxzhPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_jxzh.htm",
				macroLibraries);
	}
	/**
	 * 最终考核绩效-量化岗
	 * @return
	 */
	public String getOperJxzhData(String year,String month) {
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONObject result = dao.getJXZHInfo(this.getContext().getUID(),year,month);
		return result.toString();
	}
	/**
	 * 非量化岗-绩效奖金总和页面
	 * @return
	 */
	public String getNOperJxzhPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".noper_id_jxzh.htm",
				macroLibraries);
	}
	/**
	 * 最终考核绩效-非量化岗
	 * @return
	 */
	public String getNOperJxzhData(String year,String month) {
		UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONObject result = dao.getNJXZHInfo(this.getContext().getUID(),year,month,user);
		return result.toString();
	}
	/**
	 * 月生产计划完成-页面（103通用）
	 * @return
	 */
	public String getPlanCommitPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_jhwc.htm", 
				macroLibraries);
	}
	
	/**
	 * 获取月K3计划完成数据
	 * @param year
	 * @param month
	 * @return
	 */
	public String getPlanCommitData(String year,String month) {
		//this.getContext().getUID() year month
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		String gsbm=SDK.getRuleAPI().executeAtScript("@getUserInfo("+this.getContext().getUID()+",GSBM)");
		JSONArray list=dao.getPlanCommitInfo(gsbm, year, month);
		result.put("data", list.toString());
		return result.toString();
	}
	
	/**
	 * 月完成-echart页面（103通用）
	 * @return
	 */
	public String getPlanCommitEchartPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_jhwc_echart.htm", 
				macroLibraries);
	}
	
	/**
	 * 月完成-echart数据
	 * @param year
	 * @param month
	 * @return
	 */
	public String getPlanCommitEchartData(String year,String month) {
		//this.getContext().getUID() year month
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		String gsbm=SDK.getRuleAPI().executeAtScript("@getUserInfo("+this.getContext().getUID()+",GSBM)");
		JSONArray list=dao.getPlanCommitInfo(gsbm, year, month);
		result.put("data", list.toString());
		return result.toString();
	}
	
	/**
	 * 岗位绩效计算-103通用（只要做了量化岗的事，就会有记录）
	 * @return
	 */
	public String getGwjxjsPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_jxjs.htm",
				macroLibraries);
	}
	
	/**
	 * 岗位实时绩效计算数据（103通用）
	 * @return
	 */
	public String getJxjsData(String year,String month) {
		//this.getContext().getUID() year month
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getJXJSInfo(this.getContext().getUID(), year, month);
		result.put("data", list.toString());
		return result.toString();
	}
	/**
	 * 岗位实时绩效计算数据（103通用）
	 * @return
	 */
	public String getJxjsmxData(String year,String month,String gxbm) {
		//this.getContext().getUID() year month
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getJXJSMXInfo(this.getContext().getUID(), year, month,gxbm);
		result.put("data", list.toString());
		return result.toString();
	}
	
	/**
	 * 考核得分页面-操作岗
	 * @return
	 */
	public String getOperKhdfPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_khdf.htm",
				macroLibraries);
	}
	/**
	 * 考核得分页面-fei操作岗
	 * @return
	 */
	public String getNOperKhdfPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".noper_id_khdf.htm",
				macroLibraries);
	}
	/**
	 * 考核得分（通用）
	 * @return
	 */
	public String getOperKhdfData(String year,String month) {
		UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONObject result = dao.getKHDFInfo(this.getContext().getUID(),year,month,this.getContext().getSessionId(),user);
		return result.toString();
	}
	
	/**
	 * 制度奖惩 页面
	 * @return
	 */
	public String getOperZdjcPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_zdjc.htm",
				macroLibraries);
	}
	/**
	 * 制度奖惩
	 * @return
	 */
	public String getOperZdjcData(String year,String month) {
		//UserBean user = new UserBean(this.getContext().getUID());//当前岗位、人员、公司、工序信息
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getJCTZInfo(this.getContext().getUID(),year,month,0,1,this.getContext().getSessionId());//列表 0:制度
		result.put("data", list.toString());
		return result.toString();
	}
	/**
	 * 嘉奖否定 页面
	 * @return
	 */
	public String getOperJjfdPage(){
		Map<String, Object> macroLibraries = new HashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("year",SDK.getRuleAPI().executeAtScript("@year"));
		macroLibraries.put("month",SDK.getRuleAPI().executeAtScript("@month"));
		return HtmlPageTemplate.merge(
				ConfigConstant.APP_ID, 
				ConfigConstant.APP_ID+".oper_id_jjfd.htm",
				macroLibraries);
	}
	/**
	 * 嘉奖否定
	 * @return
	 */
	public String getOperJjfdData(String year,String month) {
		JSONObject result = new JSONObject();//主表
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getJCTZInfo(this.getContext().getUID(),year,month,2,3,this.getContext().getSessionId());//列表
		result.put("data", list.toString());
		return result.toString();
	}
	
	/**
	 * 更新个人收藏信息（我的绩效）
	 * @param bdid
	 * @param cmdid
	 * @param op
	 * @return
	 */
	public String updateSCData(String bdid,String cmdid,String mc,String op){
		JSONObject result = new JSONObject();//主表
		if(op==null){
			result.put("ZT", "0");
			result.put("MGS", "操作类型为空(NULL)。");
		}
		if("0".equals(op)){//取消关注
			String sql= "delete from "+PerformanceConstant.TAB_JCXX_JXSC +" where ZH='"+this.getContext().getUID()+"' and BDPK='"+bdid+"'";
			DBSql.update(sql);
			result.put("ZT", "1");
			result.put("MGS", "取消成功！");
		}else if("1".equals(op)){//添加关注
			String sql= "select count(ID) NUM from  "+PerformanceConstant.TAB_JCXX_JXSC +" where ZH='"+this.getContext().getUID()+"' and BDPK='"+bdid+"'";
			String numStr =DBSql.getString(sql,"NUM"); 
			int num=0;
			try {
				num=Integer.parseInt(numStr);
			} catch (Exception e) {
				num=0;
			}
			if(num==0){
				String uuid = UUID.randomUUID().toString();
				String insertSQL="insert into " +PerformanceConstant.TAB_JCXX_JXSC +"(ID,ZH,CMD,BDPK,MC) values('"+uuid+"','"+this.getContext().getUID()+"','"+cmdid+"','"+bdid+"','"+mc+"')";
				DBSql.update(insertSQL);
				result.put("ZT", "1");
				result.put("MGS", "收藏成功！");
			}else{
				result.put("ZT", "2");
				result.put("MGS", "已收藏，请勿重复操作！");
			}
		}else{
			result.put("ZT", "0");
			result.put("MGS", "操作类型不存在。");
		}
		return result.toString();
	}
	
	/**
	 * 收藏数据（我的绩效）
	 * @param bdid
	 * @param cmdid
	 * @param op
	 * @return
	 */
	public String getSCData(){
		String uid=this.getContext().getUID();
		PerformanceBizDao dao = new PerformanceBizDao();
		JSONArray list=dao.getSCData(uid);
		JSONObject result = new JSONObject();//主表
		result.put("data", list.toString());
		return result.toString();
	}
	
	public String getSCTabData(){
		String uid=this.getContext().getUID();
		PerformanceBizDao dao = new PerformanceBizDao();
		List<SCBean> list=dao.getSCTabData(uid);
		String tab="";
		String div="";
		for(SCBean bean:list){
			tab=tab+bean.getTabString();
			div=div+bean.getDivString(this.getContext().getSessionId());
		}
		
		JSONObject result = new JSONObject();//主表
		result.put("tabList", tab);
		result.put("divList", div);
		return result.toString();
	}

	/**
	 * 日产量数据删除操作（字段子表数据同时删除）
	 * @return
	 */
	public ResponseObject dayProductDataDelete(String ids) {
		if(ids==null||"".equals(ids)){
			return ResponseObject.newErrResponse("删除失败，没有数据被选中！");
		}
		String id=StringUtil.connString(ids);//将id组装成数据库查询模式
		PerformanceBizDao dao = new PerformanceBizDao();
		return dao.dayProductDataDelete(id);
//		JSONObject result = new JSONObject();//主表
//		result.put("result", "");//ok 成功，其他值为失败
//		result.put("msg", "");//失败原因
//		return result.toString();
	}
	
}
