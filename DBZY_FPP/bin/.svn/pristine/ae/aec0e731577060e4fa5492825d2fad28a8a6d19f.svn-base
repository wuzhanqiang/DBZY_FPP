package com.nepharm5.apps.fpp.nepg.zjzl;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class ysyjh extends InterruptListener implements InterruptListenerInterface {

	
	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		String bindid = ctx.getProcessInstance().getId();
		String sql;
		String tjr;
		/*BO formData = SDK.getBOAPI().getByProcess("BO_DY_YSYJH_S", bindid);
		tjr = formData.get("TJR").toString();
		StringBuffer sb_obj_tjr = new StringBuffer();
		sb_obj_tjr.append(tjr);
		sql = "update BO_DY_YSYJH_P t set t.fsfw='"+sb_obj_tjr+"' where t.bindid='"+bindid+"'";
		DBSql.update(sql);*/
		
		StringBuffer sb_obj_tjr = new StringBuffer();
		List<BO> formData = SDK.getBOAPI().query("BO_DY_YSYJH_S").bindId(bindid).list();
		//tjr = formData.get("TJR").toString();
		for(int i=0;i<formData.size();i++) {
			sb_obj_tjr.append(formData.get(i).get("TJR").toString()+' ');
		}
		
		//String =SDK.getRuleAPI().executeAtScript("");
		//sb_obj_tjr.append(tjr);
		sql = "update BO_DY_YSYJH_P t set t.fsfw='"+sb_obj_tjr.substring(0,sb_obj_tjr.length()-1)+"' where t.bindid='"+bindid+"'";
		DBSql.update(sql);
		
		return true;
	}

}
