package com.nepharm.apps.fpp.biz.pem.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.actionsoft.bpms.util.DBSql;

public class NbjctzFormGridFilter extends FormGridFilterListener implements ValueListenerInterface {

	public NbjctzFormGridFilter() {
	}

	@Override
	public FormGridRowLookAndFeel acceptRowData(ProcessExecutionContext ctx, List<BOItemModel> boItemList, BO boData) {
		String taskId = ctx.getTaskInstance().getId();
		String uid = ctx.getTaskInstance().getTarget();
		String taskState = DBSql.getString("select * from WFC_TASK where ID = '"+taskId+"'", "TASKSTATE");
		if("".equals(taskState)) {
			taskState = DBSql.getString("select * from WFH_TASK where ID = '"+taskId+"'", "TASKSTATE");
		}
		String tableName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		if("9".equals(taskState) && "BO_DY_JXGL_JCTZ".equals(tableName)) {
			FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
			String BKHRZH = boData.getString("BKHRZH");
			if(!uid.equals(BKHRZH)) {
				diyLookAndFeel.setDisplay(false);
			}
			return diyLookAndFeel;
		}
		return null;
	}

	@Override
	public String getCustomeTableHeaderHtml(ProcessExecutionContext arg0, FormItemModel arg1, List<String> arg2) {
		return null;
	}

	@Override
	public String orderByStatement(ProcessExecutionContext arg0) {
		return null;
	}

}
