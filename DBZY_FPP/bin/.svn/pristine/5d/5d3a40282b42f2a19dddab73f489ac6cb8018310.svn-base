package com.dbzy.zjxs.jcsjdr;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

public class LccpzdbaxgTaskAfterComplete extends ExecuteListener implements ExecuteListenerInterface {

	public LccpzdbaxgTaskAfterComplete() {
		setDescription("临床产品终端备案修改数据导入，处理主子表数据");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		UserContext userContext = ctx.getUserContext();
		ProcessInstance processInstance = ctx.getProcessInstance();
		String processInstId = processInstance.getId();
		BOAPI boapi = SDK.getBOAPI();
		List<BO> list1 = boapi.query("BO_DY_ZDZC_LCZDXGDR_S1").addQuery("BINDID = ", processInstId).list();
		List listMain = new ArrayList();
		if(list1 != null && !list1.isEmpty()) {
			for(BO bo : list1) {
				String OBINDID = bo.getString("OBINDID");
				bo.set("BINDID", OBINDID);
				listMain.add(bo);
			}
		}
		
		List<BO> list2 = boapi.query("BO_DY_ZDZC_LCZDXGDR_S2").addQuery("BINDID = ", processInstId).list();
		List listSub = new ArrayList();
		if(list2 != null && !list2.isEmpty()) {
			for(BO bo : list2) {
				String OBINDID = bo.getString("OBINDID");
				bo.set("BINDID", OBINDID);
				listSub.add(bo);
			}
		}
		
		boapi.createDataBO("BO_DY_ZDZC_LCZDXG_P", listMain, userContext);
		boapi.createDataBO("BO_DY_ZDZC_LCZDXG_S", listSub, userContext);
	}

}
