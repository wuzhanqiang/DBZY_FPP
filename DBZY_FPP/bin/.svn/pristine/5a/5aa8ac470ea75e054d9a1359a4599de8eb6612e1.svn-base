package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;

/**
 * 绩效流程-办理后事件
 * @author lizhen
 *
 */
public class PerformanceTaskAfterComplete implements
		ExecuteListenerInterface {
	private String bindId="";

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		bindId=context.getProcessInstance().getId();
		AbilityEvent action = new AbilityEvent(bindId);
		action.run();
	}
	@Override
	public String getDescription() {
		return "绩效考核流程-办理后数据最终计算实现";
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
