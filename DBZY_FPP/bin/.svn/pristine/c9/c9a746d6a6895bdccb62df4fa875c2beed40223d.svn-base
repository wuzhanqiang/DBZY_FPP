package com.nepharm.apps.fpp.common.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.emm.commons.push.PushUtil;

/**
 * 通用超时处理时间-触发公司级奖惩流程
 * @author lizhen
 *
 */
public class OverTimeCommonEvent 
	extends ValueListener 
	implements ValueListenerInterface{

	@Override
	public String execute(ProcessExecutionContext param) throws Exception {
		System.out.println("时限执行了->:"+param.getProcessInstance().getId());
		return "111111";
	}

	@Override
	public String getDescription() {
		return "通用超时处理时间-触发公司级奖惩流程";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

}
