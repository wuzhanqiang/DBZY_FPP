package com.nepharm.apps.fpp.biz.pem.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;

public class JclbFormGridFilter extends FormGridFilterListener implements ValueListenerInterface {

	public JclbFormGridFilter() {
	}

	@Override
	public FormGridRowLookAndFeel acceptRowData(ProcessExecutionContext ctx, List<BOItemModel> boItemList, BO boData) {
		String departmentPathId = ctx.getUserContext().getDepartmentModel().getPathIdOfCache();
		String uid = ctx.getUserContext().getUID();
		String BMBM = boData.getString("BMBM");
		FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		if(!"admin".equals(uid) && (departmentPathId.indexOf(BMBM)<0 || "".equals(BMBM.trim()))) {
			diyLookAndFeel.setDisplay(false);
		}
		return diyLookAndFeel;
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
