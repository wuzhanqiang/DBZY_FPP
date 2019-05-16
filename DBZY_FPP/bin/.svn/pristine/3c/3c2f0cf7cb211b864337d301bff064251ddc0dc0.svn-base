package com.nepharm.apps.fpp.biz.ppm.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

/**
 * 确认生产计划 最有一人办理结束前验证
 * @author lizhen
 * @date 2018-04-25
 */
public class ProductTaskCompleteValidate implements InterruptListenerInterface {

	@Override
	public boolean execute(ProcessExecutionContext context) throws Exception {
		
		
		//int num=SDK.getTaskQueryAPI().userTaskOfWorking().list().size();
		//SDK.getTaskAPI().
		
		return false;
	}
	
	@Override
	public String getDescription() {
		return "确认生产计划 -办理验证-推送K3生产订单数据";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	
}
