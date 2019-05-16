package com.nepharm5.apps.fpp.nepg.zcb;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class tzlc extends ExecuteListener{

	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindid = ctx.getProcessInstance().getId();
		String sql;
		String tjr;
		String tjrzh;
		StringBuffer sb = new StringBuffer(" ");
		List<BO> formData = SDK.getBOAPI().query("BO_DY_ZCB_TZLC_S").bindId(bindid).list();
		//tjr = formData.get("TJR").toString();
		for(int i=0;i<formData.size();i++) {
			sb.append(formData.get(i).get("TJRZH")+"<"+formData.get(i).get("TJR").toString()+">"+' ');
			
		}
		
		//String =SDK.getRuleAPI().executeAtScript("");
		//sb.append(tjr);
		sql = "update BO_DY_ZCB_TZLC_P t set t.TZFW='"+sb.substring(0,sb.length()-1)+"' where t.bindid='"+bindid+"'";
		
		DBSql.update(sql);
	}

}
