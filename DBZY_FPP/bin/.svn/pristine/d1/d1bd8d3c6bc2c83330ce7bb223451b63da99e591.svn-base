package com.nepharm.apps.fpp.biz.kms.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class SjdaFormAfterSave extends ExecuteListener implements ExecuteListenerInterface {

	public SjdaFormAfterSave() {
		setDescription("表单保存后，更新主表试题数量和总分");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindId = ctx.getProcessInstance().getId();
		List<BO> gridData = SDK.getBOAPI().query("BO_DY_KMS_SJDA_S").addQuery("BINDID = ", bindId).list();
//		List<BO> gridData = (List) ctx.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
		int stsl = 0;//试题数量
		int zf = 0;//总分
		if(gridData != null && !gridData.isEmpty()) {
			stsl = gridData.size();
			for(BO bo : gridData) {
				int fz = Integer.parseInt(bo.getString("FZ"));//分值
				zf += fz;
			}
		}
		DBSql.update("update BO_DY_KMS_SJDA_M set STSL = "+stsl+", ZF = "+zf+" where BINDID = '"+bindId+"'");
	}

}
