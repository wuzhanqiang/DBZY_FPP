package com.nepharm.apps.fpp.biz.tem.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.biz.tem.constant.XhfakhConstant;

public class XhfakhFormGridFilter extends FormGridFilterListener implements
		ValueListenerInterface {

	public XhfakhFormGridFilter() {
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
		//降成本奖励表-数据上报
		case XhfakhConstant.TAB_GYGL_JJCBJL_SJSB:
			
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String SQRZH = boData.getString("SQRZH");//获取员工账号
			//设置本条数据是否是操作者的，是：显示 | 不是：隐藏
			if(SQRZH==null||"".equals(SQRZH)||!uid.equals(SQRZH)){
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
