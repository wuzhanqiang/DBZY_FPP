package com.nepharm.apps.fpp.biz.tam.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class TestFormCompleteValidate extends InterruptListener
		implements InterruptListenerInterface {

	public TestFormCompleteValidate() {
		// 表单校验
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//参数获取
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        //sid
        String sid = ctx.getUserContext().getSessionId();
        String processInstId = ctx.getProcessInstance().getId();
        BO boData = SDK.getBOAPI().get(boName, boId);
        String formDefId = boData.getString("FORMDEFID");
        
        PerformanceDao dao = new PerformanceDao();
        String taskId=dao.getTaskId(processInstId);

        String url = SDK.getFormAPI().getFormURL("", sid, processInstId, taskId, 2, formDefId, boId, "");
        System.out.println(url);
        return false;
		
	}

}
