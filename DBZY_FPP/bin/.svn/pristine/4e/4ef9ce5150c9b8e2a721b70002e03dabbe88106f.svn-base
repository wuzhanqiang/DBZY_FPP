package com.dbzy.zjxs.cfba;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class Lczcsjkgx extends InterruptListener implements InterruptListenerInterface {

	public Lczcsjkgx() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(ProcessExecutionContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
		//流程实例ID
				
		        
		        List<BO> list = SDK.getBOAPI().query("BO_DY_ZDZC_LCZC_S").list();
		        
		        if(list.size() > 0) {
				String a = "";
		        	for(int i = 0; i < list.size(); i++) {
		        		a = SDK.getRuleAPI().executeAtScript("Lczc@sequenceYear(CRM_CUSTOMER,8,0)");
		        		BO bo = list.get(i);
		        		bo.set("ZCBH1",a);
		        		SDK.getBOAPI().update("BO_DY_ZDZC_LCZC_S", bo);
		        		
		        		
		        		//System.out.println(a);
		        		
		        		
		        	}
		        }
		return false;
	}

}
