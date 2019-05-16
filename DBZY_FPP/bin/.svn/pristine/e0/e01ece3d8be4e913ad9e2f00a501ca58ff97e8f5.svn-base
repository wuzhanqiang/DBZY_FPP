package com.nepharm.apps.fpp.biz.ppm.event;

import java.util.List;

import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridFilterListener;
import com.actionsoft.bpms.bpmn.engine.listener.FormGridRowLookAndFeel;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.form.design.model.FormItemModel;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 * 拟生产计划 生产经理 打开表单后，进行数据过滤
 * @author lizhen
 *
 */
public class DraftProductFormGridFilter extends FormGridFilterListener {
	
	//加载（刷新）表单
	@Override
	public FormGridRowLookAndFeel acceptRowData(
			ProcessExecutionContext context,
			List<BOItemModel> boItemList, 
			BO boData) {
		//TODO 通过uid @ 标签 查找本人公司编码,暂时设定为103
		String userCompanyId=SDK.getRuleAPI().executeAtScript("@getUserInfo("+context.getUserContext().getUID()+",GSBM)");
		String userCompanyName=SDK.getRuleAPI().executeAtScript("@getUserInfo("+context.getUserContext().getUID()+",GSMC)");
		
		//当前表单子表名
		String tableName = context.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//FormGridRowLookAndFeel diyLookAndFeel = new FormGridRowLookAndFeel();
		FormGridRowLookAndFeel diyLookAndFeel=null;
		switch (tableName) {
		//拟生产计划子表
		case ProductPlanConstant.TAB_SCJH_NSCJH_S:
			
			
			diyLookAndFeel=new FormGridRowLookAndFeel();
			String gsbm = boData.getString("GSBM");//获取公司编码字段
			//设置本条数据是否是本公司的，是：显示 | 不是：隐藏
			if(gsbm==null||"".equals(gsbm)||!userCompanyId.equals(gsbm)){
				diyLookAndFeel.setDisplay(false);//不显示这条数据
			}
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
		return "拟生产计划-分公司生产经理-本公司产品过滤";
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
