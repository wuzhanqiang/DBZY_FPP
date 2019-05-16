package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;

/**
 * 绩效填写流程-办理回填数据实现
 * @author lizhen
 *
 */
public class PerformanceFillInTaskAfterComplete implements
		ExecuteListenerInterface {
	private String bindId="";

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		bindId=context.getProcessInstance().getId();
		//TODO 获取当前子表数据，将通过ID、MC作为条件，更新CSZ的值（KPI参数子表）
		PerformanceFillInEvent.closeTaskBiz(bindId, "1");
	}
	@Override
	public String getDescription() {
		return "绩效填写流程-办理回填数据实现";
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
