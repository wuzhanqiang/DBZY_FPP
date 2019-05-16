package com.nepharm.apps.fpp.biz.gm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;

public class WprkTaskAfterComplete extends ExecuteListener {

	public WprkTaskAfterComplete() {
		setDescription("物品入库表单数据同步至物品档案");
	}

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		UserContext userContext = context.getUserContext();
		ProcessInstance proIns = context.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();
		BO bo = boapi.getByProcess(GMConstant.TAB_GM_WPRK_M, proInsId);
		String BM = bo.getString("BM");
		String BMID = bo.getString("BMID");
		List<BO> datas = boapi.query(GMConstant.TAB_GM_WPRK_S).addQuery("BINDID = ", proInsId).list();
		for(BO data : datas) {
			String WPBH = data.getString("WPBH");
			double RKSL = Double.parseDouble(data.getString("RKSL"));
			List<BO> wpdaBOs = boapi.query(GMConstant.TAB_GM_WPDA).addQuery("WPBH = ", WPBH).addQuery("BMBH = ", BMID).list();
			if(wpdaBOs != null && !wpdaBOs.isEmpty()) {
				BO wpdaBO = wpdaBOs.get(0);
				double XCL = Double.parseDouble(wpdaBO.getString("XCL"));
				XCL = XCL + RKSL;
				wpdaBO.set("XCL", XCL);
				boapi.update(GMConstant.TAB_GM_WPDA, wpdaBO);
			}else {
				BO recordData = createBO(data);
				recordData.set("BMBH", BMID);
				recordData.set("BM", BM);
				ProcessInstance wpdaProIns = SDK.getProcessAPI().createBOProcessInstance(GMConstant.DEF_GM_WPDA, userContext.getUID(), "物品档案");
				boapi.create(GMConstant.TAB_GM_WPDA, recordData, wpdaProIns, userContext);
			}
		}
	}

	public BO createBO(BO data) {
		BO bo = new BO();
		bo.set("WPBH", data.getString("WPBH"));
		bo.set("WPMC", data.getString("WPMC"));
		bo.set("WPLX", data.getString("WPLX"));
		bo.set("JLDW", data.getString("JLDW"));
		bo.set("GGXH", data.getString("GGXH"));
		bo.set("CLFW", data.getString("CLFW"));
		bo.set("GYSMC", data.getString("GYSMC"));
		bo.set("ZZSMC", data.getString("ZZSMC"));
		bo.set("CKBH", data.getString("CKBH"));
		bo.set("CKMC", data.getString("CKMC"));
		bo.set("XCL", data.get("RKSL"));
		return bo;
	}
}
