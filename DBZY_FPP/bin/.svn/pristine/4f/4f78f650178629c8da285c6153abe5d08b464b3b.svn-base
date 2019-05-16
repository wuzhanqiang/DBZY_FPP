/*
 * 功能：供销通知流程子表过滤
 * autor:wuzhanqiang
 * date:2018.11.14
 * */
package com.nepharm5.apps.fpp.nepg.Gxgsplugs;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class tzlcGxZgsSheetFilterBiz extends FormGridFilterListener implements ValueListenerInterface {

	@Override
	public FormGridRowLookAndFeel acceptRowData(ProcessExecutionContext context, List<BOItemModel> boItemList, BO boData) {
		// TODO Auto-generated method stub
		if(context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME).toString().equals("BO_DY_GXGS_TZLC_S"))
		{
			//初始化字符串
			StringBuffer productManagers = new StringBuffer();
			String bindid = context.getProcessInstance().getId();
			//定义一个集合
			List<BO> formData = SDK.getBOAPI().query("BO_DY_GXGS_TZLC_S").bindId(bindid).list();
			int i = 0;
			for(int count =formData.size();i<count;i++)
			{
				BO record =(BO)formData.get(i);
				productManagers.append(", ").append(record.get("FKR"));
			}
			
			FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
			UserContext user = context.getUserContext();
			String productManager = boData.getString("FKR");
			String currentUser = user.getUID() + "<" + user.getUserModel().getUserName() + ">";
			if (productManager.equals(currentUser)) {
		        diyLookAndFeel.setDisplay(true);
		      } else if ((!productManager.equals(currentUser)) && (productManagers.indexOf(currentUser) == -1)) {
		        diyLookAndFeel.setDisplay(true);
		      } else {
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
