package com.nepharm.apps.fpp.biz.pem.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.actionsoft.sdk.local.api.ProcessAPI;
import com.nepharm.apps.fpp.biz.bd.constant.BDConstant;

public class JcxxSjdr extends InterruptListener {

	public JcxxSjdr() {
		setDescription("奖惩信息数据导入");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		UserContext userContext = ctx.getUserContext();
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		ProcessAPI proapi = SDK.getProcessAPI();
		return jcxx(boapi, proapi, proInstId, userContext);
	}
	
	private boolean jcxx(BOAPI boapi, ProcessAPI proapi, String proInstId, UserContext userContext) throws SQLException{
		String GSBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userContext.getUID()+",GSBM)");
		String GSMC = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userContext.getUID()+",GSMC)");
		List<BO> formList = boapi.query(BDConstant.TAB_BD_SJDR_M).addQuery("BINDID = ", proInstId).list();
		if(formList != null && !formList.isEmpty()) {
			BO formData = formList.get(0);
			String BMBH = formData.getString("BMBH");//部门ID
			String BM = formData.getString("BM");//部门名称
			String MS = formData.getString("MS");//导入模式 0为覆盖导入，1为增量导入
			List<BO> list = boapi.query(BDConstant.TAB_BD_SJDR_JCXX).addQuery("BINDID = ", proInstId).list();
			if(list != null && !list.isEmpty()) {
				if("0".equals(MS)) {//覆盖模式先删除原始奖惩信息字典
					Connection conn = DBSql.open();
					try {
						List<BO> oList = boapi.query("BO_DY_JCXX_JCXX").addQuery("GSBM = ", GSBM).list();
						if(oList != null && !oList.isEmpty()) {
							for(BO obo : oList) {
								String bindId = obo.getBindId();
								boapi.removeByBindId("BO_DY_JCXX_JCXX", bindId, conn);
							}
						}
					}catch (Exception e) {
						
					} finally {
						conn.close();
					}
				}
				for(BO data : list) {
					BO bo = new BO();
					bo.set("LB", data.get("LB"));
					bo.set("LX", data.get("LX"));
					bo.set("MX", data.get("MX"));
					bo.set("SM", data.get("SM"));
					bo.set("JE", data.get("JE"));
					bo.set("GSBM", GSBM);
					bo.set("GSMC", GSMC);
					ProcessInstance p = proapi.createBOProcessInstance("obj_bb338242a0a845bf9520eb57e9512f4f", userContext.getUID(), "奖惩信息字典");
					boapi.create("BO_DY_JCXX_JCXX", bo, p, userContext);
				}
			}
			return true;
		}else {
			return false;
		}

	}
}
