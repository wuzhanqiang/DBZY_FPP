package com.nepharm.apps.fpp.biz.qm.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class SHBSupdateTaskCompleteValidate extends InterruptListener implements
		InterruptListenerInterface {

	public SHBSupdateTaskCompleteValidate() {
		// TODO Auto-generated constructor stub
	}
	public SHBSupdateTaskCompleteValidate(UserContext uc) {
		super();
		this.setVersion("1.0.0");
		this.setDescription("根据审核条件更新审核标识");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		//BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        
        BO boData = SDK.getBOAPI().getByProcess(boName, bindId);
        boolean f = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "不同意");
		if(f){
			boData.set("SHBS", "0");
			SDK.getBOAPI().update(boName, boData);
		}
        
		return true;
	}

}
