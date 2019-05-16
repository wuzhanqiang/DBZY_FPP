package com.nepharm.apps.fpp.biz.qm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

public class SczlycFormCompleteValidateStep2 extends InterruptListener
		implements InterruptListenerInterface {

	public SczlycFormCompleteValidateStep2() {
		// TODO Auto-generated constructor stub
		setDescription("表单校验");
		setVersion("V1.0");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		//参数获取
        String bindId = ctx.getProcessInstance().getId();
        String uid = ctx.getUserContext().getUID();
        BO bo = SDK.getBOAPI().getByProcess("BO_DY_ZLGL_SCGCBHGPSQCLD", bindId);
        String FXPG = bo.getString("FXPG");
        String CLYJ = bo.getString("CLYJ");
        String CLJG = bo.getString("CLJG");
        if(FXPG == null || "".equals(FXPG)){
        	throw new BPMNError("001","请填写风险评估");
        }
        if(CLYJ == null || "".equals(CLYJ)){
        	throw new BPMNError("002","请填写处理意见");
        }
		if(CLJG == null || "".equals(CLJG)){
			throw new BPMNError("003","请填写处理结果");
		}
        
		return true;
	}

}
