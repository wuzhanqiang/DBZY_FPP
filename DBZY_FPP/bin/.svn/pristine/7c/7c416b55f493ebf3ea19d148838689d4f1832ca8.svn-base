package com.nepharm.apps.fpp.biz.ppm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOCopyAPI;
import com.actionsoft.sdk.local.api.BOQueryAPI;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 * 拟订生产计划 分公司生产经理办理结束后触发事件
 * 生成物料需求计划流程
 * @author lizhen
 * @date 2018-04-25
 */
public class DraftProductTaskAfterComplete implements ExecuteListenerInterface {

	
	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		UserContext uc= context.getUserContext();//当前操作者usercontext
		String bindId=context.getProcessInstance().getId();
		//TODO 通过uid @ 标签 查找本人公司编码、名称,暂时设定为103
		String userCompanyId=SDK.getRuleAPI().executeAtScript("@getUserInfo("+context.getUserContext().getUID()+",GSBM)");
		String userCompanyName=SDK.getRuleAPI().executeAtScript("@getUserInfo("+context.getUserContext().getUID()+",GSMC)");
		String year=DBSql.getString("SELECT JHNF FROM "+ProductPlanConstant.TAB_SCJH_NSCJH_M+" WHERE BINDID='"+bindId+"'","JHNF");
		String month=DBSql.getString("SELECT JHYF FROM "+ProductPlanConstant.TAB_SCJH_NSCJH_M+" WHERE BINDID='"+bindId+"'","JHYF");
		
		BO bo =new BO();
		bo.set("DJBH",SDK.getRuleAPI().executeAtScript("WLJH@sequenceMonth(DY_WLJH,6,0)"));//TODO 单据号，seq规则生成
		bo.set("BM", uc.getDepartmentModel().getName());
		bo.set("BMID", uc.getDepartmentModel().getId());
		bo.set("SQR",uc.getUserName() );
		bo.set("SQRZH",uc.getUID() );
		bo.set("SQRQ", SDK.getRuleAPI().executeAtScript("@date"));
		bo.set("YEAR", SDK.getRuleAPI().executeAtScript("@year"));
		bo.set("MONTH", SDK.getRuleAPI().executeAtScript("@month"));
		bo.set("JHNF", year);
		bo.set("JHYF", month);
		bo.set("GSBM", userCompanyId);
		bo.set("GSMC",userCompanyName);
		
		
		//创建流程实例
		ProcessInstance pi=SDK.getProcessAPI().createProcessInstance(
				ProductPlanConstant.PROCESS_SCJH_WLJH, uc.getUID(),year+"年"+month+"月"+userCompanyName+"物料需求计划申请");
		//启动这个流程实例
		SDK.getProcessAPI().start(pi);
		//创建数据
		SDK.getBOAPI().create(
				ProductPlanConstant.TAB_SCJH_WLJH_M, 
				bo,
				pi.getId(), 
				uc.getUID());
		getGoodsInfo(pi,userCompanyId,year,month);
	}

	/**
	 * 获取物料模板数据
	 * @param userCompanyId
	 * @param year
	 * @param month
	 * @return
	 */
	private void getGoodsInfo(ProcessInstance pi,String userCompanyId, String year,
			String month) {
		
		
		BOQueryAPI query = SDK.getBOAPI().query(ProductPlanConstant.TAB_SCJH_WLMB_S, true).addQuery("GSBM = ", userCompanyId);
		BOCopyAPI copyAPI = query.copyTo(ProductPlanConstant.TAB_SCJH_WLJH_S, pi.getId());
		
		copyAPI.addNewData("JHNF", year);
		copyAPI.addNewData("JHYF", month);
		copyAPI.exec();
		System.out.println("");
	}

	@Override
	public String getDescription() {
		return "拟订生产计划-分公司生产经理-启动物料计划流程";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
	/*
	 //当前任务id
	 pi.getStartTaskInstId();
	 
	 */
}
