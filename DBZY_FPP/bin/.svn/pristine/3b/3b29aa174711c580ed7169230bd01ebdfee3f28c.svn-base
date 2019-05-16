package com.nepharm.apps.fpp.biz.pem.kpi;

import java.util.List;

import com.nepharm.apps.fpp.biz.pem.bean.KPIBean;
import com.nepharm.apps.fpp.biz.pem.dao.KPICommonDao;

/**
 * 同一个bindID下的KPI计算入口
 * @author lizhen
 *
 */
public class KPIStartUp {

	private String uid;
	private String bindId;
	public KPIStartUp(String uid,String bindId){
		this.uid=uid;
		this.bindId=bindId;
	}
	
	public void execute(){
		KPICommonDao dao = new KPICommonDao();
		//封装获取多条KPI数据
		List<KPIBean> list = dao.getKPIInfo(this.bindId);
		//启动运算运行
		for(int i=0;i<list.size();i++){
			KPIBean bean=list.get(i);
			//如果有实现类，就执行实现类代码；没有就按默认方法走
			String className=bean.getClassName();
			try {
				className=className.trim();
			} catch (Exception e) {
				className=null;
			}
			
			KPIConstructor kpi = 
					new KPIConstructor(
							this.uid, 
							bean.getKpiNo(), 
							bean.getBoId(), 
							bean.getClassName());
			kpi.action();
			
		}
	}
	
	
	@Deprecated
	public void run(){
		KPICommonDao dao = new KPICommonDao();
		
		//封装获取多条KPI数据
		List<KPIBean> list = dao.getKPIInfo(this.bindId);
		//启动反射运行
		for(int i=0;i<list.size();i++){
			KPIBean bean=list.get(i);
			KPIConstructor kpi = 
					new KPIConstructor(
							this.uid, 
							bean.getKpiNo(), 
							bean.getBoId(), 
							bean.getClassName(), 
							bean.getFunctionName());
			kpi.action();
			
		}
	}
}
