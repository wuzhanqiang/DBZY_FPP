package com.nepharm.apps.fpp.biz.gm.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;

public class WplyFormCompleteValidateStep1 extends InterruptListener {

	public WplyFormCompleteValidateStep1() {
	}

	@Override
	public boolean execute(ProcessExecutionContext context) throws Exception {
		ProcessInstance proIns = context.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();
		BO bo = boapi.getByProcess(GMConstant.TAB_GM_WPLY_M, proInsId);
		String SQRZH = bo.getString("SQRZH");
		String BMBH = SDK.getRuleAPI().executeAtScript("@sqlValue(select ID from ORGDEPARTMENT where DEPARTMENTNO = '@getUserInfo("+SQRZH+",GSBM)')");
		List<BO> datas = boapi.query(GMConstant.TAB_GM_WPLY_S).addQuery("BINDID = ", proInsId).list();
		//判断子表是否为空，数量是否为0
		if(datas == null || datas.isEmpty()){
			throw new BPMNError("ERR_WPLY","请填写领用的物品信息！");
		} 
		
		List<BO> errList = new ArrayList<BO>();//领用异常数据
		for(BO data : datas) {
			double LYSL = Double.parseDouble(data.getString("LYSL"));
			String WPBH = data.getString("WPBH");
			String WPMC = data.getString("WPMC");
			List<BO> wpdaBOs = boapi.query(GMConstant.TAB_GM_WPDA).addQuery("BMBH = ", BMBH).addQuery("WPBH = ", WPBH).list();
			if(wpdaBOs != null && !wpdaBOs.isEmpty()) {
				BO wpdaBO = wpdaBOs.get(0);
				double KC = Double.parseDouble(wpdaBO.getString("XCL"));
				double XCL = KC - LYSL;
				if(XCL<0 || LYSL<=0) {//库存不足或领用数量为0，放入异常数据list
					BO errBO = new BO();
					errBO.set("WPBH", WPBH);
					errBO.set("WPMC", WPMC);
					errBO.set("KC", KC);
					errBO.set("LYSL", LYSL);
					errList.add(errBO);
				}
			}
		}
		
		//校验是否有异常数据
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String WPMC = errBO.getString("WPMC");
				String KC = errBO.getString("KC");
				double LYSL = Double.parseDouble(errBO.getString("LYSL"));
				if(LYSL <= 0) {
					content.append(WPBH+" "+WPMC+"的领用数量为"+LYSL+"， 领用数量有误！\n");
				}else {
					content.append(WPBH+" "+WPMC+"的库存数量为"+KC+"，领用数量为"+LYSL+"， 库存不足！\n");
				}
			}
			throw new BPMNError("ERR_WPLY",content.toString());
		}else {
			return true;
		}
	}

}
