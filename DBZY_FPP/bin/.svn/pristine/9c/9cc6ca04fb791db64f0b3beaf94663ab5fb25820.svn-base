package com.nepharm5.apps.fpp.nepg.biz.zbb.event;

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
import com.nepharm5.apps.fpp.nepg.biz.zbb.constant.zbbConstant;

public class BPBJYJHCGFormGridFilter extends FormGridFilterListener implements
		ValueListenerInterface {

	public BPBJYJHCGFormGridFilter() {
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
		
		String taskId = context.getTaskInstance().getId();
		//原始任务办理者
		String uid = queryOwner(taskId);
		
		//当前表单子表名
		String tableName = context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		FormGridRowLookAndFeel diyLookAndFeel=null;
		switch (tableName) {
		//备品备件月计划采购流程子表
		case zbbConstant.TAB_ZBB_BPBJYJHCG_S:
			
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String SQRZH = boData.getString("SQRZH");//获取员工账号
			//设置本条数据是否是原始任务办理者的，是：显示 | 不是：隐藏
			if(!uid.equals(SQRZH)) {
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

	//递归查询加签任务的最初任务办理者
	private String queryOwner(String taskId) {
		String taskState = DBSql.getString("select * from WFC_TASK where ID = '"+taskId+"'", "TASKSTATE");
		//判断任务状态，11为加签
		if("11".equals(taskState)) {
			String parentTaskId = DBSql.getString("select * from WFC_TASK where ID = '"+taskId+"'", "PARENTTASKINSTID");
			return queryOwner(parentTaskId);
		}else {
			String uid = DBSql.getString("select * from WFC_TASK where ID = '"+taskId+"'", "TARGET");
			return uid;
		}
	}
}
