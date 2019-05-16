package com.nepharm.apps.fpp.biz.pem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.BOUtil;
import com.nepharm.apps.fpp.util.DateUtil;

/**
 * 绩效考核流程相关工具方法
 * @author lizhen
 *
 */
public class PerformanceAppraisalUtil {
	private static List<String> kpiSubIds;//KPI基础库编码
	
	private static List<List<BO>> paramData;
	/**
	 * 获取系统中所有岗位下的人员列表
	 * @return
	 */
	public static Map<String,List<String>> getPostMatchPersonData(){
		//TODO 具体业务实现后，丰富此项数据,目前测试阶段写死了几条条数据
//		String key1="0001";//操作岗
//		String key2="0002";//非操作岗
//		String uid1="admin";
//		String uid2="admin2";
//		
//		List<String> list1 = new ArrayList<String>();
//		list1.add(uid1);
//		List<String> list2 = new ArrayList<String>();
//		//list2.add(uid1);
//		list2.add(uid2);
		
		Map<String,List<String>> data = new HashMap<String, List<String>>();
		PerformanceDao dao = new PerformanceDao();
		List<String> gwList=dao.getGWList();
		for(String gwbm:gwList){
			List<String> ryList=dao.getRYList(gwbm);
			if(!ryList.isEmpty()){
				data.put(gwbm, ryList);
			}
			
		}
		return data;
	}
	
	/**
	 * 获取KPI指标配置中所有岗位信息
	 * @return
	 */
	public static Map<String,String> getKPIPostData(){
		PerformanceDao dao = new PerformanceDao();
		Map<String,String> data=dao.getKPIPostData();
		return data;
	}
	/**
	 * 获取职业素质指标配置中所有岗位信息
	 * @return
	 */
	public static Map<String,String> getAllAbilityPostData(){
		PerformanceDao dao = new PerformanceDao();
		Map<String,String> data=dao.getAllAbilityPostData();
		return data;
	}
	/**
	 * 获取职业素质指标配置中操作岗岗位信息
	 * @return
	 */
	public static Map<String,String> getAbilityPostData(){
		PerformanceDao dao = new PerformanceDao();
		Map<String,String> data=dao.getAbilityPostData();
		return data;
	}
	/**
	 * 创建绩效流程（非量化）、业务数据赋值
	 * @param postNo    岗位编码
	 * @param bindId     KPI配置表bindid
	 * * @param bindId   能力配置表bindid
	 * @param personList 岗位下的人员账户名
	 */
	public static void createKPIPerformance(String postNo, String bindId,String bindId2,
			List<String> personList) {
		
		for(int i=0;i<personList.size();i++){
			kpiSubIds=new ArrayList<String>();
			String bkhr=personList.get(i);//被考核人
			BO mainData=getKPIMainData(postNo,bindId,bindId2,bkhr);//封装主表数据
			List<BO> kpiData = getKPISubData(bindId);//KPI考核子表
			
			paramData = new ArrayList<List<BO>>();//kpi子表的子表
			getKPIParamSubData();
			
			//能力考核子表
			List<BO> abilityData = getAbilitySubData(bindId2);//能力考核子表
			//嘉奖否定\制度考核
			Map<String,List<BO>> rewards =getRewards(bkhr);
			
			//更新主表的三个金额字段
			mainData=updateMainBO(mainData,rewards);
			//创建
			try {
				
				KPICreate(SDK.getRuleAPI().executeAtScript("@userName("+bkhr+")"),postNo,mainData,kpiData,abilityData,rewards.get("ZD"),rewards.get("JJ"));
			} catch (Exception e) {
				System.out.println("岗位编号："+postNo+" 的KPI指标配置项，在基础库中存在参数子表不存在数据的情况！");
				break;
			}
		}
	}
	
	
	/**
	 * 创建绩效流程（非量化）、业务数据赋值
	 * @param postNo    岗位编码
	 * @param bindId     KPI配置表bindid
	 * * @param bindId   能力配置表bindid
	 * @param personList 岗位下的人员账户名
	 */
	public static void createAblityPerformance(String postNo, String bindId,
			List<String> personList) {
		
		for(int i=0;i<personList.size();i++){
			String bkhr=personList.get(i);//被考核人
			BO mainData=getAbilityMainData(postNo,bindId,bkhr);//封装主表数据
			
			//能力考核子表
			List<BO> abilityData = getAbilitySubData(bindId);//能力考核子表
			//嘉奖否定\制度考核
			Map<String,List<BO>> rewards =getRewards(bkhr);
			
			//更新主表的三个金额字段
			mainData=updateMainBO(mainData,rewards);
			//创建
			try {
				
				abilityCreate(SDK.getRuleAPI().executeAtScript("@userName("+bkhr+")"),postNo,mainData,abilityData,rewards.get("ZD"),rewards.get("JJ"));
			} catch (Exception e) {
				System.out.println("岗位编号："+postNo+" :"+e.getMessage());
				break;
			}
		}
	}
	/**
	 * 更新主表的 奖励金额、触发金额、总金额
	 * @param mainData
	 * @param rewards
	 * @return
	 */
	private static BO updateMainBO(BO mainData, Map<String, List<BO>> rewards) {
		List<BO> list1=rewards.get("ZD");
		List<BO> list2=rewards.get("JJ");
		double jl=0;
		double cf=0;
		for(BO bo :list1){
			String lx=(String)bo.get("JCLX");
			String jeStr=(String)bo.get("JCJE");
			double je=Double.parseDouble(jeStr);
			if("0".equals(lx)){
				cf=cf+je;
			}else{
				jl=jl+je;
			}
		}
		for(BO bo :list2){
			String lx=(String)bo.get("JCLX");
			String jeStr=(String)bo.get("JCJE");
			double je=Double.parseDouble(jeStr);
			if("3".equals(lx)){
				cf=cf+je;
			}else{
				jl=jl+je;
			}
		}
		 
		mainData.set("JJJE", jl);
		mainData.set("CFJE", cf);
		mainData.set("ZJE", jl+cf);
		return mainData;
	}

	/**
	 * 制度、嘉奖 两个list数据
	 * @param bkhr
	 * @return
	 */
	private static Map<String, List<BO>> getRewards(String bkhr) {
		PerformanceDao dao = new PerformanceDao();
		Map<String, List<BO>> map1=dao.getRewards(bkhr);
		Map<String, List<BO>> map2=dao.getCompanyRewards(bkhr);
		
		List<BO> list1=map1.get("ZD");
		List<BO> list2=map2.get("ZD");
		list1.addAll(list2);
		
		List<BO> list3=map1.get("JJ");
		List<BO> list4=map2.get("JJ");
		list3.addAll(list4);
		
		Map<String, List<BO>> map = new HashMap<String, List<BO>>();
		map.put("ZD", list1);map.put("JJ", list3);
		return map;
	}

	/**
	 * 获取KPI参数子表数据
	 */
	private static void getKPIParamSubData() {
		PerformanceDao dao = new PerformanceDao();
		
		for(String bm:kpiSubIds){
			List<BO> list=dao.getKPIParamSubData(bm);
			List<BO> list2=new ArrayList<BO>();
			for(BO bo:list){
				String id=new String(bo.getId());
				bo=BOUtil.cleanBO(bo);
				bo.set("CSID", id);
				list2.add(bo);
			}
			paramData.add(list2);
		}
		
	}

	/**
	 * 重新封装KPI子表数据
	 * @param bindId
	 * @return
	 */
	private static List<BO> getKPISubData(String bindId) {
		PerformanceDao dao = new PerformanceDao();
		List<BO> list=dao.getKPIPostSubData(bindId);
		List<BO> list2=new ArrayList<BO>();
		for(BO bo :list){
			String bm=bo.getString("KPIBM");
			kpiSubIds.add(bm);
			bo=BOUtil.cleanBO(bo);
			//bo.set("ZSXX",bo.get("ZSMB"));
			list2.add(bo);
		}
		return list2;
	}
	/**
	 * 重新封装能力子表数据
	 * @param bindId
	 * @return
	 */
	private static List<BO> getAbilitySubData(String bindId) {
		PerformanceDao dao = new PerformanceDao();
		List<BO> list=null;
		try {
			list = dao.getAbilityPostSubData(bindId);
		} catch (Exception e) {
			return null;
		}
		List<BO> list2=new ArrayList<BO>();
		for(BO bo :list){
			bo=BOUtil.cleanBO(bo);
			bo.set("ZPFS", "5");
			bo.set("LPFS", "5");
			list2.add(bo);
		}
		return list2;
	}
	/**
	 * 封装主表数据（非量化）
	 * @param postNo
	 * @param bindId
	 * @param uid
	 * @return
	 */
	private static BO getKPIMainData(String postNo, String bindId,String bindId2, String uid) {
//		String sql = "select ID from "+PerformanceConstant.TAB_JXGL_KPIPZ_M+" where bindid='"+bindId+"'";
		String sql = "select ID from "+PerformanceConstant.TAB_KPI_PZ_M+" where bindid='"+bindId+"'";
		String boId=DBSql.getString(sql,"ID");
		BO bo=SDK.getBOAPI().get(PerformanceConstant.TAB_KPI_PZ_M, boId);
		
		String sql2 = "select ID from "+PerformanceConstant.TAB_JXGL_SZPZ_M+" where bindid='"+bindId2+"'";
		String boId2=DBSql.getString(sql2,"ID");
		BO bo2=SDK.getBOAPI().get(PerformanceConstant.TAB_JXGL_SZPZ_M, boId2);
		
		BO data = new BO();
		data.set("KHBH", System.currentTimeMillis());
		data.set("KHLX","1" );
		data.set("KHZQ", bo.get("KHZQ") );
		data.set("GWBM",bo.get("GWBM") );
		
		data.set("GWMC", bo.get("GWMC") );
		data.set("GSBM", bo.get("GSBM") );
		data.set("GSMC", bo.get("GSMC") );
		data.set("BKHR",SDK.getRuleAPI().executeAtScript("@userName("+uid+")") );
		
		String[] date=DateUtil.getLastDatInfoe();
		
		data.set("YEAR",date[0] );
		data.set("MONTH",date[1]);
		String quarter=DateUtil.getQuarter(date[1]);
		System.out.println("--->quarter:"+quarter);
		data.set("SSJD",quarter);
		data.set("BKHRZH", uid);
		data.set("KPIQZ", bo.get("KHQZ") );
		try {
			data.set("SZQZ", bo2.get("KHQZ"));//能力考核权重
			data.set("SZDFL",bo2.get("DFL") );//能力得分率
		} catch (Exception e) {
			data.set("SZQZ", "1");//能力考核权重
			data.set("SZDFL","100" );//能力得分率
		}
		data.set("ZT", "0");
		return data;
	}
	/**
	 * 封装主表数据（量化）
	 * @param postNo
	 * @param bindId
	 * @param uid
	 * @return
	 */
	private static BO getAbilityMainData(String postNo, String bindId, String uid) {
		
		String sql2 = "select ID from "+PerformanceConstant.TAB_JXGL_SZPZ_M+" where bindid='"+bindId+"'";
		String boId2=DBSql.getString(sql2,"ID");
		BO bo2=SDK.getBOAPI().get(PerformanceConstant.TAB_JXGL_SZPZ_M, boId2);
		
		BO data = new BO();
		data.set("KHBH", System.currentTimeMillis());
		data.set("KHLX","0" );
		data.set("KHZQ", bo2.get("KHZQ"));
		data.set("GWBM",bo2.get("GWBM") );
		
		data.set("GWMC", bo2.get("GWMC") );
		data.set("GSBM", bo2.get("GSBM") );
		data.set("GSMC", bo2.get("GSMC") );
		data.set("BKHR",SDK.getRuleAPI().executeAtScript("@userName("+uid+")") );
		
		String[] date=DateUtil.getLastDatInfoe();
		
		data.set("YEAR",date[0] );
		data.set("MONTH",date[1]);
		String quarter=DateUtil.getQuarter(date[1]);
		data.set("SSJD",quarter);
		data.set("BKHRZH", uid);
		data.set("KPIQZ", 0);
		data.set("KPIFS", 0);
		try {
			
			data.set("SZQZ", bo2.get("KHQZ"));//能力考核权重
			data.set("SZDFL",bo2.get("DFL") );//能力得分率
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.set("ZT", "1");
		return data;
	}
	/**
	 * 创建非量化岗流程
	 * @param bo
	 * @param kpiData     kpi子表
	 * @param paramData kpi参数字段子表
	 * @param data1         能力子表
	 * @param data2         奖惩1子表
	 * @param data3         奖惩2子表
	 */
	private static void KPICreate(String userName,String postNo,BO bo,List<BO> kpiData,List<BO> abilityData,List<BO> data2,List<BO> data3){
		//创建流程实例
		ProcessInstance pi=SDK.getProcessAPI().createProcessInstance(
				PerformanceConstant.PROCESS_JXGL_JXKH, ConfigConstant.SYS_ADMIN,
				SDK.getRuleAPI().executeAtScript("@year")+"年"
				+SDK.getRuleAPI().executeAtScript("@month")+"月"
				+"-"+postNo+"岗(非量化)-"+userName+"-"
				+"绩效考核流程");
		//启动这个流程实例
		SDK.getProcessAPI().start(pi);
		//创建数据
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_M, 
				bo,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		//KPI指标数据，返回的boId就是字段子表的bindId
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_KPI, 
				kpiData,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		if(kpiData.size()!=paramData.size()){
			throw new RuntimeException("在KPI库中，存在");
		}
		for(int i=0;i<kpiData.size();i++){
			List<BO> record=paramData.get(i);
			String subBindId=kpiData.get(i).getId();//字段子表的bindId
			SDK.getBOAPI().create(
					PerformanceConstant.TAB_JXGL_JXKH_ZBCS, 
					record,
					subBindId, 
					ConfigConstant.SYS_ADMIN);
		}
		//KPI指标数据，返回的boId就是字段子表的bindId
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_SZ, 
				abilityData,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_ZD, 
				data2,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_JJ, 
				data3,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
	}
	/**
	 * 创建量化岗流程
	 * @param bo
	 * @param data1         能力子表
	 * @param data2         奖惩1子表
	 * @param data3         奖惩2子表
	 */
	private static void abilityCreate(String userName,String postNo,BO bo,List<BO> abilityData,List<BO> data2,List<BO> data3){
		//创建流程实例
		ProcessInstance pi=SDK.getProcessAPI().createProcessInstance(
				PerformanceConstant.PROCESS_JXGL_JXKH_LH, ConfigConstant.SYS_ADMIN,
				SDK.getRuleAPI().executeAtScript("@year")+"年"
				+SDK.getRuleAPI().executeAtScript("@month")+"月"
				+"-"+postNo+"岗(量化)-"+userName+"-"
				+"绩效考核流程");
		//启动这个流程实例
		SDK.getProcessAPI().start(pi);
		//创建数据
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_M, 
				bo,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		if(abilityData!=null){
			SDK.getBOAPI().create(
					PerformanceConstant.TAB_JXGL_JXKH_SZ, 
					abilityData,
					pi.getId(), 
					ConfigConstant.SYS_ADMIN);
		}
		
		
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_ZD, 
				data2,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
		
		SDK.getBOAPI().create(
				PerformanceConstant.TAB_JXGL_JXKH_JJ, 
				data3,
				pi.getId(), 
				ConfigConstant.SYS_ADMIN);
	}
	
}
