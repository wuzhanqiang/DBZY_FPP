package com.nepharm.apps.fpp.biz.pem.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 * 将制度考核、嘉奖考核两个子表，格式重新出初始化，
 * @author lizhen
 *
 */
public class PerformanceFormGridFilter extends FormGridFilterListener {
	
	//加载（刷新）表单
	@Override
	public FormGridRowLookAndFeel acceptRowData(
			ProcessExecutionContext context,
			List<BOItemModel> boItemList, 
			BO boData) {
		//TODO 通过uid @ 标签 查找本人公司编码,暂时设定为103
		//当前表单子表名
		String tableName = context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		FormGridRowLookAndFeel diyLookAndFeel=null;
		PerformanceDao dao = new PerformanceDao();
		switch (tableName) {
		//制度子表
		case PerformanceConstant.TAB_JXGL_JXKH_ZD:
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String yBindId = boData.getString("YBINDID");
			String taskId=dao.getTaskId(yBindId);
			String style="<span style=\"color:#53709a;cursor:pointer;\" onclick=\"openHTML('"+yBindId+"','"+taskId+"');\">点击查看</span>";
			boData.set("YBINDID", style);
			break;
		//奖惩子表
		case PerformanceConstant.TAB_JXGL_JXKH_JJ:
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String yBindId2 = boData.getString("YBINDID");
			String taskId2=dao.getTaskId(yBindId2);
			String style2="<span style=\"color:#53709a;cursor:pointer;\" onclick=\"openHTML('"+yBindId2+"','"+taskId2+"');\">点击查看</span>";
			boData.set("YBINDID", style2);
			break;
		}
		
		return diyLookAndFeel;
	}

	/**
	 * @param ctx 流程引擎提供给监听器的上下文对象
	 * @param formItemModel 子表项模型，可通过该模型获取到子表的BO模型
	 * @param displayPolicy 应用显示策略后的可见的字段列表，其中key为字段名
	 * @return
	 */
	@Override
	public String getCustomeTableHeaderHtml(
			ProcessExecutionContext ctx,
			FormItemModel formItemModel, 
			List<String> displayPolicy) {
		
		return null;
	}

	@Override
	public String orderByStatement(ProcessExecutionContext context) {
		//return "field1 asc,field2 desc";//两种字段组合的排序
		return null;
	}

	@Override
	public String getDescription() {
		return "将制度考核、嘉奖考核两个子表，格式重新出初始化";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
}
