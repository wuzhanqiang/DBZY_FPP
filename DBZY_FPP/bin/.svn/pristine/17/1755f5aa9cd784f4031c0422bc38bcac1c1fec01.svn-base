package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 奖惩流程
 * @author lizhen
 *
 */
public class JCTZFlowBeforeCompleteEvent extends InterruptListener{
	public String getDescription() {
		return "奖惩通知流程结束前生成传阅";
	}

    public boolean execute(ProcessExecutionContext ctx) throws Exception {
    	String bindId=ctx.getProcessInstance().toString();
    	String taskId=ctx.getTaskInstance().getId();
    	String title =ctx.getProcessInstance().getTitle();
    	String uid=ctx.getUserContext().getUID();
    	//应用管理 >东北制药全流程全绩效系统->参数->传阅人配置
    	//读取传阅人参数信息 多个人 空格隔开
    	String CCUid = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "CCZH");
    	try {
    		//生成传阅任务操作
			SDK.getTaskAPI().createUserCCTaskInstance(bindId, taskId,uid, CCUid,"(传阅)"+title);
		} catch (Exception e) {
			System.out.println("传阅失败");
			e.printStackTrace();
		}
        return true;
    }
}
