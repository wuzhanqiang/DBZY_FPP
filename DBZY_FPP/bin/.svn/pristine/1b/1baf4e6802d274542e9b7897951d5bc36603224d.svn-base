package com.nepharm.apps.fpp.biz.kms.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class KCSHTaskAfterComplete extends ExecuteListener implements
		ExecuteListenerInterface {

	public KCSHTaskAfterComplete() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//流程ID
		String bindId = ctx.getProcessInstance().getId();
		boolean f1 = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "同意");
		if(f1){
			List<BO> boList = SDK.getBOAPI().query("BO_DY_KMS_KCSH_S").bindId(bindId).list();
			for(BO bo:boList){
				String KCBINDID = bo.get("KCBINDID").toString();
				BO updateBo = SDK.getBOAPI().getByProcess("BO_DY_KMS_KCTM_M", KCBINDID);
				updateBo.set("ZT", "1");
				SDK.getBOAPI().update("BO_DY_KMS_KCTM_M", updateBo);
				
			}
		}
		
		
		
		
	}

}
