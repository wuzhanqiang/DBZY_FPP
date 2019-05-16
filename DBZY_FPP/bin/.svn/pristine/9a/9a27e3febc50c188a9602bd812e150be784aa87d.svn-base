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

public class JlqjjdTaskBeforeCompleteStep3 extends InterruptListener {

	public JlqjjdTaskBeforeCompleteStep3() {
		setDescription("检查子表是否完全填写检定结果");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		List<BO> datas = SDK.getBOAPI().query(GMConstant.TAB_GM_JLQJJD_S).addQuery("BINDID = ", proInstId).list();
		List<BO> errList = new ArrayList();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				String JDJG = data.getString("JDJG");
				String YXQ = data.getString("YXQ");
				if("".equals(JDJG) || ("合格".equals(JDJG)&&"".equals(YXQ))) {
					errList.add(data);
				}
			}
		}
		
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String NKBH = errBO.getString("NKBH");
				String WPMC = errBO.getString("WPMC");
				String JDJG = errBO.getString("JDJG");
				String YXQ = errBO.getString("YXQ");
				if("".equals(JDJG)) {
					content.append("内控编号："+NKBH+"， "+WPBH+" "+WPMC+"的“检定结果”未填写！\n");
				}else if("合格".equals(JDJG) && "".equals(YXQ)) {
					content.append("内控编号："+NKBH+"， "+WPBH+" "+WPMC+"的“有效期至”未填写！\n");
				}
			}
			throw new BPMNError("ERR_JLQJJD", content.toString());
		}else {
			return true;
		}
	}

}
