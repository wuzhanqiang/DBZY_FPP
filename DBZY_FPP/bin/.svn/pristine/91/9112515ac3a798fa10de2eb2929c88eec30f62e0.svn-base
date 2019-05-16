package com.nepharm.apps.fpp.biz.gm.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.gm.util.GMUtil;

public class JlqjjdTaskAfterCompleteStep2 extends ExecuteListener {

	public JlqjjdTaskAfterCompleteStep2() {
		setDescription("审核菜单选择同意后，给计控中心发送只读通知");
	}

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		ProcessInstance processInst = context.getProcessInstance();
		TaskInstance taskInst = context.getTaskInstance();
		UserContext userContext = context.getUserContext();
		String title = taskInst.getTitle();
		boolean isChoice = SDK.getTaskAPI().isChoiceActionMenu(taskInst, "同意");
//		String participant = "00005212";//计控中心总经理 
		if(isChoice) {
			String departmentId = "c0d5e498-bf3b-4f4c-81fd-3b7ca6ef8bbc";// 东北制药集团沈阳计控有限公司\综合部
			String roleName = "总经理";//角色名
			String participant = GMUtil.getUserId(departmentId, roleName);
			SDK.getTaskAPI().createUserNotifyTaskInstance(processInst, taskInst, userContext, participant, title);
		}
	}
}
