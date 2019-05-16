package com.nepharm.apps.fpp.biz.gm.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;

public class BgypFormBeforeSave extends InterruptListener {

	public BgypFormBeforeSave() {
		setDescription("校验办公用品子表数量");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		List<BO> datas = (List) ctx.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
		List<BO> errList = new ArrayList();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				int SL = Integer.parseInt(data.getString("SL"));
				if(SL <= 0) {
					errList.add(data);
				}
			}
		}
		
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String WPMC = errBO.getString("WPMC");
				String SL = errBO.getString("SL");
				content.append(WPBH+" "+WPMC+"的数量为"+SL+"，输入有误！\n");
			}
			throw new BPMNError("ERR_BGYP", content.toString());
		}else {
			return true;
		}
		
	}

}
