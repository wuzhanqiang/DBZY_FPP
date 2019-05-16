package com.nepharm5.apps.fpp.nepg.zcb;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class HyglFormCompleteValidateWZQ extends InterruptListener implements InterruptListenerInterface {

	public HyglFormCompleteValidateWZQ() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub		
		String bindid = ctx.getProcessInstance().getId();
		String sj = SDK.getRuleAPI().executeAtScript("@datetime");
		String HBDWZH = ctx.getUserContext().getUID();
		List<BO>list = SDK.getBOAPI().query("BO_DY_ZCB_HYGLLC_S").bindId(bindid).addQuery("HBDWZH=", HBDWZH).list();
		for (BO bo:list){
			bo.set("BZ", sj);
			SDK.getBOAPI().update("BO_DY_ZCB_HYGLLC_S", bo);		
		}
			
		return true;
	}

}
