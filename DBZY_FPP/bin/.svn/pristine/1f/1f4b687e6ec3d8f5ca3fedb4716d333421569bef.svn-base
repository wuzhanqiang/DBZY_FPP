package com.nepharm.apps.fpp.biz.pem.event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nepharm.apps.fpp.biz.pem.util.PerformanceAppraisalUtil;

/**
 * 实现 绩效考核 流程启动初始化 操作
 * @author lizhen
 * 测试数据：
 * 操 作 岗 ：岗位编码0001-admin
 * 非操作岗：岗位编码0002-admin
 */
public class PerformanceAppraisalStartEvent {
	public static Map<String,List<String>> postData;
	/**
	 * 量化岗位流程-操作岗
	 */
	public static void startQuantization(){
		//岗位配置-关联人- map<岗位编码,List<uid>> 
		postData=PerformanceAppraisalUtil.getPostMatchPersonData();
		Map<String,String> data=PerformanceAppraisalUtil.getAbilityPostData();
		for (Iterator i = data.keySet().iterator(); i.hasNext();) {  
			String postNo =(String) i.next();//岗位编码
			String bindId =data.get(postNo);//职业素质配置表
			List<String> personList=null;
			try {
				personList = postData.get(postNo);
				if(personList==null){
					continue;
				}
				System.out.println("量化岗："+postNo);
			} catch (Exception e) {
				continue;
			}
			//创建；流程、业务数据赋值
			PerformanceAppraisalUtil.createAblityPerformance(postNo,bindId,personList);
		}
		
		
	}
	/**
	 * 非量化岗位流程-非操作岗
	 */
	public static void startNonQuantization(){
		//岗位配置-关联人- map<岗位编码,List<uid>> 
		postData=PerformanceAppraisalUtil.getPostMatchPersonData();
		//数据库查询KPI配置的岗位、bindid信息（1:1关系）
		Map<String,String> data=PerformanceAppraisalUtil.getKPIPostData();
		Map<String,String> data2=PerformanceAppraisalUtil.getAllAbilityPostData();
		for (Iterator i = data.keySet().iterator(); i.hasNext();) {  
			String postNo =(String) i.next();//岗位编码
			String bindId =data.get(postNo);//KPI配置表
			String bindId2=data2.get(postNo);//职业素质配置表
			List<String> personList=postData.get(postNo);
			System.out.println("非量化岗："+postNo);
			//创建；流程、业务数据赋值
			PerformanceAppraisalUtil.createKPIPerformance(postNo,bindId,bindId2,personList);
			
		}
		
		
	}

}
