package com.nepharm.apps.fpp.biz.tam.event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.constant.CallActivityDefinitionConst;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.core.delegate.TaskBehaviorContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.actionsoft.sdk.local.api.ProcessQueryAPI;
import com.actionsoft.sdk.service.BOApi;
import com.actionsoft.sdk.service.ProcessApi;
import com.ibm.db2.jcc.am.bo;

public class GzpjWfkBeforeSubprocess extends ExecuteListener implements
		ExecuteListenerInterface {

	public GzpjWfkBeforeSubprocess() {
		
	}

	@Override
	public void execute(ProcessExecutionContext pec) throws Exception {
		// TODO Auto-generated method stub
		UserContext ctx = pec.getUserContext();
		// 父流程实例对象
		ProcessInstance ParentProcessInstance = pec.getProcessInstance();
		//父流程任务对象
		TaskInstance ParentTaskInstance = pec.getTaskInstance();
		// 子流程实例上下文，此阶段子流程实例已创建，未开始流程（无任务实例）
        TaskBehaviorContext subProcessCtx = (TaskBehaviorContext) pec.getParameter(CallActivityDefinitionConst.PARAM_CALLACTIVITY_CONTEXT);
        // 子流程实例
        ProcessInstance SubProcessInstance= subProcessCtx.getProcessInstance();
        String SubBindid = SubProcessInstance.getId();
        //人员信息
        String uid = ctx.getUID();
        String username = ctx.getUserName();
        String bm = ctx.getDepartmentModel().getName();
		String bmid = ctx.getDepartmentModel().getId();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
        
        //创建表数据
		//BO boData = new BO();
        //SDK.getBOAPI().create("BO_DY_RWGL_GZPJB", boData, SubBindid, uid);
		//获取表数据
        BO boData = SDK.getBOAPI().getByProcess("BO_DY_RWGL_GZPJB", SubBindid);
        //初始化表数据
        String DJBH = SDK.getRuleAPI().executeAtScript("@sequenceMonth(DY_GZPJ,6,0)");
        String GSMC = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSMC)");
        String GSBH = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSBM)");
        boData.set("BS", "2");	//标识：1:主流程|2:子流程
        boData.set("BDLX", "2");//表单类型：1:有反馈|2:无反馈
        boData.set("KHR", username);
        boData.set("KHRZH", uid);
        boData.set("BKHR", "");
        boData.set("BKHRZH", "");
        //boData.set("BM", bm);
        //boData.set("BMID", bmid);
        boData.set("SQSJ", date);
        boData.set("PROCESSPARENTID", ParentProcessInstance.getId());
        boData.set("JSX", "0");
        boData.set("YXX", "0");
        boData.set("GZTD", "0");
        boData.set("PJF", "0");
        SDK.getBOAPI().update("BO_DY_RWGL_GZPJB", boData);
        
	}

}
