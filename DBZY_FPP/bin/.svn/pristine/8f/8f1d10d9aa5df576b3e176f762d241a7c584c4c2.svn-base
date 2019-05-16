package com.nepharm.apps.fpp.biz.qm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class RcwsjcFormBeforeLoadStep1 extends ExecuteListener implements
		ExecuteListenerInterface {

	public RcwsjcFormBeforeLoadStep1() {
		// TODO Auto-generated constructor stub
		setDescription("表单子表初始化数据");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		String uid = ctx.getUserContext().getUID();
		List<BO> boList = SDK.getBOAPI().query("BO_DY_ZLGL_JJQQJJL_S").bindId(bindId).list();
		if(boList.size()==0){
			
			List<BO> boDatas = SDK.getBOAPI().query("BO_DY_ZLGL_JJQQJDX").list();
			for(int i=0;i<boDatas.size();i++){
				BO bo = new BO();
				bo.set("QJDX", boDatas.get(i).get("QJDX"));
				SDK.getBOAPI().create("BO_DY_ZLGL_JJQQJJL_S", bo, bindId, uid);
			}
		}

	}

}
