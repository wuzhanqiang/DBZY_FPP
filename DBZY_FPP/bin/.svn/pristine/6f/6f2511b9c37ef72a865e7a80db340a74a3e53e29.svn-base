package com.nepharm5.apps.fpp.gxgs.biz.bhgbs;

import java.util.Hashtable;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class BhgbsbhxgaiRuleJump extends ValueListener implements
		ValueListenerInterface {

	public BhgbsbhxgaiRuleJump() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();
		boolean btg = SDK.getTaskAPI().isChoiceActionMenu(taskid, "驳回修改");
		//将办理不通过的人员任务打回
		BO a = SDK.getBOAPI().getByProcess("BO_DY_GXGS_BHGBS_P", bindid);
		String tzry = a.get("TZRY")==null?"":a.get("TZRY").toString();
		StringBuffer targetUser = new StringBuffer();
		if(btg == true && !tzry.isEmpty()){
				targetUser.append(tzry).append(" ");
		}
		
		if(btg==true ){
			return "obj_c7fea6ec25800001a3f71c90cc60d8d0:"+targetUser;
		} else {
			
			return targetUser.toString();
		}
	}

}
