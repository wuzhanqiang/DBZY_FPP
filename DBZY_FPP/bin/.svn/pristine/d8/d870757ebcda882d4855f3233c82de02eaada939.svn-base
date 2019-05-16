package com.nepharm5.apps.fpp.nepg.zcb;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class HyglFormCompleteValidate extends InterruptListener implements InterruptListenerInterface {

	public HyglFormCompleteValidate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();//找到这条流程
		String sj = SDK.getRuleAPI().executeAtScript("@datetime");//获取当前系统时间
		String HBDWZH = ctx.getUserContext().getUID();//找到字表里的这条数据
		List<BO> list  = SDK.getBOAPI().query("BO_DY_ZCB_HYGLLC_S").bindId(bindid).addQuery("HBDWZH = ", HBDWZH).list();//获取子表这个字段
		for (BO bo:list){
			bo.set("BZ", sj);
            SDK.getBOAPI().update("BO_DY_ZCB_HYGLLC_S", bo);
			
            //获取子表上传附件的时间，并更新时间
		}	
		
		return true;
	}

}
