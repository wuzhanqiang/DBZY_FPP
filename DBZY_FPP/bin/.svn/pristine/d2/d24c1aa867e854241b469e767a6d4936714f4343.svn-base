package com.nepharm.apps.fpp.biz.qm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

public class SjfxFormCompleteValidateStep2 extends InterruptListener
		implements InterruptListenerInterface {

	public SjfxFormCompleteValidateStep2() {
		// TODO Auto-generated constructor stub
		setDescription("表单校验");
		setVersion("V1.0");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		//参数获取
        String bindId = ctx.getProcessInstance().getId();
        String uid = ctx.getUserContext().getUID();
        List<BO> boDatas = SDK.getBOAPI().query("BO_DY_ZLGL_SJFXBG_S").bindId(bindId).list();
        for(int i=0;i<boDatas.size();i++){
        	String SQRZH = boDatas.get(i).getString("SQRZH");
        	if(SQRZH.equals(uid)){
        		String SQSJ = boDatas.get(i).getString("SQSJ");
        		String FJ = boDatas.get(i).getString("FJ");
        		if(SQSJ == null || "".equals(SQSJ)){
        			throw new BPMNError("001","请填写上传时间");
        		}
        		if(FJ == null || "".equals(FJ)){
        			throw new BPMNError("002","请上传附件");
        		}
        	}
        }
       
		return true;
	}

}
