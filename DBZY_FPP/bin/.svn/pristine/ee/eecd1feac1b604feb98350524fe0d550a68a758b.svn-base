package com.nepharm.apps.fpp.biz.tam.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class GzpjWfkTaskAfterComplete extends ExecuteListener implements
		ExecuteListenerInterface {

	public GzpjWfkTaskAfterComplete() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext pec) throws Exception {
		// TODO Auto-generated method stub
		UserContext ctx = pec.getUserContext();
		String processInstId = pec.getProcessInstance().getId();
		String parentTaskInstId = pec.getTaskInstance().getId();
		String uid = ctx.getUID();
		
		BO bo = SDK.getBOAPI().getByProcess("BO_DY_RWGL_GZPJB", processInstId);
		String participant = bo.getString("SQRZH");
		String title = "您有一封工作评价通知，请查看！";
		
		SDK.getTaskAPI().createUserNotifyTaskInstance(processInstId, parentTaskInstId, uid, participant, title);

	}

}
