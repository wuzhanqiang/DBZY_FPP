/*
 * 功能：供销公司通知流程表单保存后事件
 * autor:wuzhanqiang
 * date:2018.11.14
 * 
*/
package com.nepharm5.apps.fpp.nepg.Gxgsplugs;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public  class GxZgsTzlc extends ExecuteListener 
{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		//获取流程ID
		String bindid = ctx.getProcessInstance().getId();
		//String类型 初始化变量
		String sql;
		String fkr;
		//StringBuffer类型初始化
		//StringBuffer类中的方法主要偏重于对于字符串的变化，例如追加、插入和删除等，这个也是StringBuffer和String类的主要区别。
		StringBuffer sb_obj_fkr = new StringBuffer(" ");
		//定义一个集合
		List<BO> formData = SDK.getBOAPI().query("BO_DY_GXGS_TZLC_S").bindId(bindid).list();
		//fkr = formData.get("FKR").toString();
		for(int i=0;i<formData.size();i++)
		{
			sb_obj_fkr.append(formData.get(i).get("FKR").toString()+' ');	
		}
		//String = SDK.getRuleAPI().ExecuteAtScript("");
		//sb_obj_fkr.append(fkr);
		//更新数据库
		sql = "update BO_DY_GXGS_TZLC_P t set t.FFFW ='" +sb_obj_fkr.substring(0,sb_obj_fkr.length()-1)+"' where t.bindid = '"+bindid+"'";
		DBSql.update(sql);
		
	}
	
}
