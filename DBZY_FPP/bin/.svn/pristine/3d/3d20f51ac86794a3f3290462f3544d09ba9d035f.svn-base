package com.nepharm.apps.fpp.biz.tam.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

public class GzpjKhrFormCompleteValidate extends InterruptListener
		implements InterruptListenerInterface {

	public GzpjKhrFormCompleteValidate() {
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

        BO boData = SDK.getBOAPI().get(boName, boId);
        String KHRZH = boData.getString("KHRZH");
        String SQRZH = boData.getString("SQRZH");
        if(KHRZH.indexOf(SQRZH)==0){
        	
        	throw new BPMNError("001","考核人与被考核人不能相同");
        }
        
        return true;
		
	}

}
