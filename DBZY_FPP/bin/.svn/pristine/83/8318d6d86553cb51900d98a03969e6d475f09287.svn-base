package com.nepharm.apps.fpp.biz.pem.kpi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.pem.bean.KPIParamBean;
import com.nepharm.apps.fpp.biz.pem.util.KPICommonUtil;

public class Test implements KPIFunctionImplement{

	@Override
	public void execute(String userId, String kpiNo, String boId, Map data) {
		System.out.println("已经执行KPI计算了！！！！！");
		if(KPICommonUtil.isOverTime(boId)){
			return ;
		}
		
		//用来存储KPI真正有效的变量值(最终用于公式计算&模板替换)
		Map<String,Double> funcData=new HashMap<String, Double>();
		/* 1抓值
		 * 分类获取 参数数值
		 * 1)类型为系统常量、手动考核 的数据直接赋值
		 * 2)类型为系统抓取
		 * */
		for (Iterator i = data.keySet().iterator(); i.hasNext();) {  
			String key =(String) i.next();//参数名
			KPIParamBean bean=(KPIParamBean)data.get(key);//参数完整对象
			// type  0:手动考核|1:系统抓取|2:系统常量
			if("1".equals(bean.getType())){
				double value=KPICommonUtil.parse(kpiNo,boId,bean.getKey(),userId);
				funcData.put(key, value);
			}else{
				funcData.put(key, bean.getValue());
			}
		}
		//TODO 2、计算 自己去定义
		String gs=DBSql.getString("select SJGS from BO_DY_KPI_ZBK_M where KPIBM='"+kpiNo+"'","SJGS");
		double fs = KPICommonUtil.markResult(boId,gs,funcData);
		//double fs=0;//((KPIParamBean)data.get("a1")).getValue()*((KPIParamBean)data.get("a2")).getValue();
		
		KPICommonUtil.result(boId, fs, funcData);
		
		
	}

}
