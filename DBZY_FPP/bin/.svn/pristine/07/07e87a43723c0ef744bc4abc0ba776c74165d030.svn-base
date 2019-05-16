package com.nepharm.apps.fpp.biz.tam.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

public class GzpjFormCompleteValidate extends InterruptListener
		implements InterruptListenerInterface {

	public GzpjFormCompleteValidate() {
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
        String JSX = boData.getString("JSX");
        String YXX = boData.getString("YXX");
        String GZTD = boData.getString("GZTD");
        String RWNDXS = boData.getString("RWNDXS");
        
        if(JSX == null || "".equals(JSX)){
        	throw new BPMNError("001","未对及时性评分，请重新对及时性进行评分");
        }
        if(YXX == null || "".equals(YXX)){
        	throw new BPMNError("002","未对有效性评分，请重新对有效性进行评分");
        }
        if(GZTD == null || "".equals(GZTD)){
        	throw new BPMNError("003","未对工作态度评分，请重新对工作态度进行评分");
        }
        
        double dJSX = Double.parseDouble(JSX);
        double dYXX = Double.parseDouble(YXX);
        double dGZTD = Double.parseDouble(GZTD);
        double dPJF = Math.round((dJSX + dYXX + dGZTD) / 3 * 100) / 100.0;
        //判断是否有任务难度
        if(RWNDXS != null && !"".equals(RWNDXS)){
        	
        	double dRWNDXS = Double.parseDouble(RWNDXS);
        	dPJF = dPJF*dRWNDXS;
        	dPJF = Math.round(dPJF * 100) / 100.0;
        }
        
        
        boData.set("PJF", dPJF+"");
        SDK.getBOAPI().update("BO_DY_RWGL_GZPJB", boData);
        
        return true;
		
	}

}
