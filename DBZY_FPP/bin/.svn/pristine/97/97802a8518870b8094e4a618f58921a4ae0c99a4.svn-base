package com.dbzy.zjxs.cfba;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;


public class Hygllc extends FormGridFilterListener implements ValueListenerInterface {
	
	public Hygllc() {
	}
	/**
	 * @param ctx 流程引擎提供给监听器的上下文对象
	 * @param formItemModel 子表项模型，可通过该模型获取到子表的BO模型
	 * @param displayPolicy 应用显示策略后的可见的字段列表，其中key为字段名
	 * @return
	 */
	
	@Override
	public FormGridRowLookAndFeel acceptRowData(
			ProcessExecutionContext context,
			List<BOItemModel> boItemList, 
			BO boData) {
		// TODO 获取操作者账号
		String uid=context.getUserContext().getUID();
		
		//当前表单子表名
		String tableName = context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		FormGridRowLookAndFeel diyLookAndFeel=null;
		switch (tableName) {
		//会议管理流程子表
		case zcbConstant.TAB_ZCB_HYGLLC_S:
			
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String HBDWZH = boData.getString("HBDWZH");//获取员工账号
			//设置本条数据是否是操作者的，是：显示 | 不是：隐藏
			if(HBDWZH==null||"".equals(HBDWZH)||!uid.equals(HBDWZH)){
				diyLookAndFeel.setDisplay(false);//不显示这条数据
			}
			break;
		}
		
		return diyLookAndFeel;
	}
	
	@Override
	public String getCustomeTableHeaderHtml(ProcessExecutionContext ctx,
			FormItemModel formItemModel, List<String> displayPolicy) {
		return null;
	}

	@Override
	public String orderByStatement(ProcessExecutionContext context) {
		return null;
	}

}
