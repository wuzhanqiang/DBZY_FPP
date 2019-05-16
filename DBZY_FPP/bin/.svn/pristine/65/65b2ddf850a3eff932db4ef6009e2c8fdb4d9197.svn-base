package com.nepharm.apps.fpp.biz.tam.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.UserTaskModel;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class GzpjRoleJumpStep2 implements HumanPerformerInterface {

	public GzpjRoleJumpStep2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHumanPerformer(UserContext ctx, ProcessInstance ProcessInstance,
			TaskInstance arg2, UserTaskModel arg3, Map<String, Object> arg4) {
		// TODO Auto-generated method stub
		BO boData = SDK.getBOAPI().getByProcess("BO_DY_RWGL_GZPJB", ProcessInstance.getId()); 
		String uid = boData.getString("KHRZH");
		
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
