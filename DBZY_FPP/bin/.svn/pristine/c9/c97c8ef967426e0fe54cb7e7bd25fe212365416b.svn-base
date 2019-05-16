package com.nepharm.apps.fpp.biz.pem.kpi;

import java.lang.reflect.Method;
import java.util.Map;

import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.KPICommonDao;

/**
 * KPI运算构造器执行类
 * @author lizhen
 *
 */
public class KPIConstructor {
	//被考核人 账户ID
	private String userId;
	//指标编码
	private String kpiNo; 
	//绩效流程中KPI指标的boId
	private String boId; 
	//类名
	private String className=PerformanceConstant.KPI_DEFAULT_IMPLEMENT_CLASSNAME;
	//方法名前缀
	private String functionPrefixName=PerformanceConstant.KPI_DEFAULT_FUNCTION_PREFIX;

	private String functionName=PerformanceConstant.KPI_DEFAULT_FUNCTION;
	/**
	 * 默认构造器
	 * @param userId 被考核人 账户ID
	 * @param kpiNo  指标编码
	 * @param boId   绩效流程中KPI指标的boId
	 */
	public KPIConstructor(String userId,String kpiNo,String boId){
		this.kpiNo=kpiNo;
		this.userId=userId;
		this.boId=boId;
	}
	/**
	 * 需要初始化实现类以及方法名前缀
	 * @param userId 被考核人 账户ID
	 * @param kpiNo  指标编码
	 * @param boId   绩效流程中KPI指标的boId
	 * @param className 类名
	 * @param functionName 指定方法名
	 */
	@Deprecated
	public KPIConstructor(String userId,String kpiNo,String boId,String className,String functionName){
		this.kpiNo=kpiNo;
		this.userId=userId;
		this.boId=boId;
		this.className=className;
		if(functionName!=null&&!"".equals(functionName)){
			this.functionName=functionName;
		}
		
	}
	/**
	 * 需要初始化实现类
	 * @param userId 被考核人 账户ID
	 * @param kpiNo  指标编码
	 * @param boId   绩效流程中KPI指标的boId
	 * @param className 类名
	 */
	public KPIConstructor(String userId,String kpiNo,String boId,String className){
		this.kpiNo=kpiNo;
		this.userId=userId;
		this.boId=boId;
		if(className!=null&&!"".equals(className)){
			this.className=className;
		}
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public void action(){
		
		try {
			KPICommonDao dao =new KPICommonDao();
			//获取参数集合
			Map data=dao.getKPIParams(this.boId);
			//获取对象类
			Class<?> cls = Class.forName(this.className);  
			//构造实现类方法
//			if(this.functionName==null||"".equals(this.functionName)){
//				this.functionName=this.functionPrefixName+this.kpiNo;
//			}
			Method m = cls.getDeclaredMethod(this.functionName,new Class[]{String.class,String.class,String.class,Map.class});  
			//执行构造方法
			m.invoke(cls.newInstance(),this.userId,this.kpiNo,this.boId,data);
		} catch (Exception e) {
			System.out.println(kpiNo+"  KPIConstructor.action()--> 反射机制异常："+e);
		} 
	}
	
}
