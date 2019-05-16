package com.nepharm.apps.fpp.biz.kms.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.nepharm.apps.fpp.biz.kms.constant.KMSConstant;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class KCSHFormGridFilter extends FormGridFilterListener implements
		ValueListenerInterface {

	public KCSHFormGridFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FormGridRowLookAndFeel acceptRowData(ProcessExecutionContext context,
			List<BOItemModel> boItemList, 
			BO boData) {
		// TODO Auto-generated method stub
		//当前表单子表名
		String tableName = context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		FormGridRowLookAndFeel diyLookAndFeel=null;
		switch (tableName) {
		//课程子表
		case KMSConstant.TAB_KMS_KCSH_S:
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String bID = boData.getString("KCBINDID");
			String style="<span style=\"color:#53709a;cursor:pointer;\" onclick=\"openH('"+bID+"');\">"+"点击查看"+"</span>";
			boData.set("CKXQ", style);
			break;
		}
		return diyLookAndFeel;
	}

	@Override
	public String getCustomeTableHeaderHtml(ProcessExecutionContext arg0,
			FormItemModel arg1, List<String> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderByStatement(ProcessExecutionContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
