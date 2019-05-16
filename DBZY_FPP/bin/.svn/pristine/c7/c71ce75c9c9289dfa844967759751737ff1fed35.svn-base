package com.nepharm5.apps.fpp.gxgs.biz.tzlc;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

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

public class GxTzlcSheetFilter extends FormGridFilterListener implements
		ValueListenerInterface {

	public GxTzlcSheetFilter() {
		// TODO Auto-generated constructor stub
		setVersion("V1.0");
		setProvider("cici");
		setDescription("供销通知流程.过滤子表数据，只显示和当前用户相关的记录");
	}
	@Override
	public FormGridRowLookAndFeel acceptRowData(ProcessExecutionContext ctx,
			List<BOItemModel> listBo, BO bo) {
		// TODO Auto-generated method stub
		if(ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME).equals("BO_DY_GXGS_TZLC_S")){
			StringBuffer participants = new StringBuffer();
			String bindId = ctx.getProcessInstance().getId();
			List<BO> records = SDK.getBOAPI().query("BO_DY_GXGS_TZLC_S").list();
			for(int i = 0, count = records.size(); i < count; i++){
				participants.append(", ").append(records.get(i).get("FKR"));//反馈人
			}
			
			FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
			
			String participant = bo.getString("FKR");//获取子表中反馈人一列的值
			String currentUser = ctx.getUserContext().getUID()+"<"+ctx.getUserContext().getUserModel().getUserName()+">";//当前用户的账号和姓名
			
			if(participant.equals(currentUser)){
				//如果子表中的反馈人的值和当前用户相同，那么显示该行记录
				diyLookAndFeel.setDisplay(true);
			}else if(!participant.equals(currentUser) && participants.indexOf(currentUser) == -1){
				//如果子表中的反馈人的值和当前用户不一样，并且当前用户不在子表的反馈人中，那么显示该行记录
				diyLookAndFeel.setDisplay(true);
			}else{
				//如果子表中的反馈人员和当前用户不一样，那么不显示该行记录
				diyLookAndFeel.setDisplay(false);
			}
			
			return diyLookAndFeel;
		}else{
			return null;
		}
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
	
//	static String getFieldValue(List<BO> listBo, String str){
//		String participant = "";
//		for(BO bo:listBo){
//			participant += bo.getString(str);
//		}
//		return participant;
//	}

}
