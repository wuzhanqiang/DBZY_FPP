package com.nepharm.apps.fpp.biz.zbgl.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class ZbglTaskBeforeComplete extends InterruptListener implements
		InterruptListenerInterface {

	public ZbglTaskBeforeComplete() {
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		
		String bindId = ctx.getProcessInstance().getId();
		String boName = "BO_DY_ZBSB_M";
		BO recordData = SDK.getBOAPI().getByProcess(boName, bindId);
		
		recordData.set("TJSJ", SDK.getRuleAPI().executeAtScript("@datetime"));
		SDK.getBOAPI().update(boName, recordData);
		
		return true;
	}

}
