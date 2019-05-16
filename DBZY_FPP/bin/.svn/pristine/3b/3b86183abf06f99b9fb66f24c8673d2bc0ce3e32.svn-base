package com.nepharm.apps.fpp.biz.kms.event;

import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.UserTaskModel;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerAbst;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class PxjhRoute extends HumanPerformerAbst implements HumanPerformerInterface {

	public PxjhRoute() {
	}

	@Override
	public String getHumanPerformer(UserContext user, ProcessInstance processInst, TaskInstance taskInst, UserTaskModel userTaskDefModel, Map<String, Object> params) {
		String processInstId = processInst.getId();
		List<BO> list = SDK.getBOAPI().query("BO_DY_KMS_KCJH_S2").addQuery("BINDID = ", processInstId).list();
		StringBuffer sb = new StringBuffer();
		if(list != null) {
			for(BO bo : list) {
				String XYBH = bo.getString("XYBH");
				sb.append(XYBH).append(" ");
			}
		}
		return sb.toString().trim();
	}

	@Override
	public String getSetting(UserContext user, Map<String, Object> params) {
		return null;
	}

}
