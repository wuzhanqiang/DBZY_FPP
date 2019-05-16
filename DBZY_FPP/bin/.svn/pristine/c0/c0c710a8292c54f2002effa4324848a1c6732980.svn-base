package com.dbzy.zjxs.bhwy;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class OtctszcSjkgx extends InterruptListener implements InterruptListenerInterface{

	@Override
	public boolean execute(ProcessExecutionContext arg0) throws Exception {
		// TODO Auto-generated method stub
		List<BO> list = SDK.getBOAPI().query("BO_DY_DSJ_TSZC_S").list();
	     
        if(list.size() > 0) {
		String a = "";
        	for(int i = 0; i < list.size(); i++) {
        		a = SDK.getRuleAPI().executeAtScript("Otctszc@sequenceYear(CRM_CUSTOMER,8,0)");
        		BO bo = list.get(i);
        		bo.set("ZCBH1",a);
        		SDK.getBOAPI().update("BO_DY_DSJ_TSZC_S", bo);        		       		      	       		       		
        	}
        }
		return false;
	}

}
