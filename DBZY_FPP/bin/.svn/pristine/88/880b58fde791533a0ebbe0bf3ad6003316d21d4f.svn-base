package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.kpi.KPIStartUp;
import com.nepharm.apps.fpp.util.BOUtil;

/**
 * 领导评分、保存事件：更新最终绩效分数、等级、系数。
 * @author lizhen
 *
 */
public class PerformanceFormSaveEvent extends ExecuteListener   {

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		
		UserContext uc= context.getUserContext();//当前操作者usercontext
		String uid=uc.getUID();
		String bindId=context.getProcessInstance().getId();
		AbilityEvent action = new AbilityEvent(bindId);
		action.run();
	}
	
	@Override
	public String getDescription() {
		return "领导评分、保存事件：更新最终绩效分数、等级、系数。";
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
