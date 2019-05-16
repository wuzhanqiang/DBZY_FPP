package com.nepharm5.apps.fpp.gxgs.biz.bhgbs;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class BhgbsCancelOtherTask extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;

	public BhgbsCancelOtherTask() {
		setVersion("V1.0");
		setDescription("供销不合格药品报损流程.一票否决。");
		setProvider("wz");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();
		String uid = ctx.getUserContext().getUID();
		// 判断当前审核菜单是否为驳回修改
		boolean bhxg = SDK.getTaskAPI().isChoiceActionMenu(taskid, "驳回修改");

		if (bhxg) {
			// 删除其他人的待办任务
			SDK.getTaskAPI().deleteOtherTask(taskid, uid);
		}
	}

}
