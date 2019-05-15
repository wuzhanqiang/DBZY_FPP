package com.dbzy.zjxs.cfba;

import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.UserTaskModel;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class Hygllc2 implements HumanPerformerInterface{
	
	public Hygllc2() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getHumanPerformer(UserContext ctx, ProcessInstance ProcessInstance,
			TaskInstance arg2, UserTaskModel arg3, Map<String, Object> arg4) {
		// TODO Auto-generated method stub
		List<BO> boDatas = SDK.getBOAPI().query("BO_DY_ZCB_HYGLLC_S").bindId(ProcessInstance.getId()).list(); 
		String uid = "";
		for(BO bo:boDatas){
			uid += bo.getString("HBDWZH")+" ";
		}
		return uid;
	}

	@Override
	public String getHumanPerformerByHook(UserContext arg0,
			ProcessInstance arg1, TaskInstance arg2, UserTaskModel arg3,
			Map<String, Object> arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPage(UserContext arg0, boolean arg1, ProcessInstance arg2,
			TaskInstance arg3, UserTaskModel arg4, Map<String, Object> arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPotentialOwner(UserContext arg0, ProcessInstance arg1,
			TaskInstance arg2, UserTaskModel arg3, Map<String, Object> arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSetting(UserContext arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
