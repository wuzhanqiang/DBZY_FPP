package com.nepharm.apps.fpp.biz.dm.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.dm.constant.DMConstant;

public class WhwxFormCompleteValidateStep1 extends InterruptListener {

	public WhwxFormCompleteValidateStep1() {
		setDescription("设备维护维修表单校验");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		List<BO> datas = boapi.query(DMConstant.TAB_DM_WHWX_S).addQuery("BINDID = ", proInstId).list();
		List<BO> errList = new ArrayList();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				double SYSL = Double.parseDouble(data.getString("SYSL"));
				if(SYSL <= 0) {
					errList.add(data);
				}
			}
		}
		
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String WPMC = errBO.getString("WPMC");
				String SYSL = errBO.getString("SYSL");
				content.append(WPBH+" "+WPMC+"的使用数量为"+SYSL+"，使用数量有误！\n");
			}
			throw new BPMNError("ERR_SBWHWX",content.toString());
		}else {
			return true;
		}
		
	}

}
