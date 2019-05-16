package com.nepharm.apps.fpp.biz.gm.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;

public class WpsqFormCompleteValidateStep1 extends InterruptListener {

	public WpsqFormCompleteValidateStep1() {
			setDescription("校验申请数量");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		List<BO> datas = SDK.getBOAPI().query(GMConstant.TAB_GM_WPSQ_S).addQuery("BINDID = ", proInstId).list();
		List<BO> errList = new ArrayList();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				double SQSL = Double.parseDouble(data.getString("SQSL"));
				if(SQSL <= 0) {
					errList.add(data);
				}
			}
		}
		
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String WPMC = errBO.getString("WPMC");
				String SQSL = errBO.getString("SQSL");
				content.append(WPBH+" "+WPMC+"的申请数量为"+SQSL+"，申请数量有误！\n");
			}
			throw new BPMNError("ERR_WPSQ", content.toString());
		}else {
			return true;
		}
	}

}
