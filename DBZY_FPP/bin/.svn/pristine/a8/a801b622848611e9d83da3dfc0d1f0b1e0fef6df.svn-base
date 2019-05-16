package com.nepharm.apps.fpp.biz.qm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.sdk.local.SDK;

public class RcwsjcFormBeforeSaveStep1 extends InterruptListener {

	public RcwsjcFormBeforeSaveStep1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		String uid = ctx.getUserContext().getUID();
		BO formData = (BO) ctx.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
		String GWBM2 = formData.getString("GWBM");
		//获取主表数据
		BO mainData = SDK.getBOAPI().getByProcess("BO_DY_ZLGL_JJQQJJL_M", bindId);
		if(mainData != null){
			
			String GWBM = mainData.getString("GWBM");
			String GWMC = mainData.getString("GWMC");
			if(GWMC != null && !GWMC.equals("") && GWBM!= null && !GWMC.equals("")){
				
				// 主表中的保存场景获取主表数据；普通子表页面的保存场景获取的是该条子表的数据；如果需要获得其他数据请使用BOQueryAPI获取
				
				//判断数据库中的岗位编码是否与表单中的岗位编码相同，如果不同清空子表数据
				if(!GWBM.equals(GWBM2)){
					SDK.getBOAPI().removeByBindId("BO_DY_ZLGL_JJQQJJL_S", bindId);
				}
				
			}
			
		}
		
		return true;
		
    
	}

}
