package com.nepharm.apps.fpp.biz.bd.event;

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
import com.nepharm.apps.fpp.biz.dm.constant.DMConstant;

public class SjdrSB extends InterruptListener {

	public SjdrSB() {
		setDescription("设备数据导入");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		UserContext userContext = ctx.getUserContext();
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		ProcessAPI proapi = SDK.getProcessAPI();
		return sbda(boapi, proapi, proInstId, userContext);
	}
	
	//设备档案
	private boolean sbda(BOAPI boapi, ProcessAPI proapi, String proInstId, UserContext userContext) throws SQLException {
		List<BO> formList = boapi.query(BDConstant.TAB_BD_SJDR_M).addQuery("BINDID = ", proInstId).list();
		if(formList != null && !formList.isEmpty()) {
			BO formData = formList.get(0);
			String BMBH = formData.getString("BMBH");//部门ID
			String BM = formData.getString("BM");//部门名称
			String MS = formData.getString("MS");//导入模式 0为覆盖导入，1为增量导入
			List<BO> list = boapi.query(BDConstant.TAB_BD_SJDR_SB).addQuery("BINDID = ", proInstId).list();
			if(list != null && !list.isEmpty()) {
				//覆盖导入先删原档案信息
				if("0".equals(MS)) {
					Connection conn = DBSql.open();
					try {
//						DBSql.update("delete from "+DMConstant.TAB_DM_SBDA+" where BMBH = '"+BMBH+"'");
						List<BO> oList = boapi.query(DMConstant.TAB_DM_SBDA).addQuery("BMBH = ", BMBH).list();
						if(oList != null && !oList.isEmpty()) {
							for(BO obo : oList) {
								String bindId = obo.getBindId();
								boapi.removeByBindId(DMConstant.TAB_DM_SBDA, bindId, conn);
							}
						}
						
					}catch(Exception e) {
						
					}finally {
						conn.close();
					}
				}
				for(BO data : list) {
					BO bo = new BO();
					bo.set("SBBH", data.get("SBBH"));
					bo.set("SBMC", data.get("SBMC"));
					bo.set("GGXH", data.get("GGXH"));
					bo.set("ZT", data.get("ZT"));
					bo.set("BMBH", BMBH);
					bo.set("BM", BM);
					bo.set("RZRQ", data.get("RZRQ"));
					bo.set("GJYZ", data.get("GJYZ"));
					bo.set("CFDD", data.get("CFDD"));
					bo.set("SCCJ", data.get("SCCJ"));
					ProcessInstance p = proapi.createBOProcessInstance(DMConstant.DEF_DM_SBDA, "admin", "设备档案");
					boapi.create(DMConstant.TAB_DM_SBDA, bo, p, userContext);
				}
			}
			return true;		
		}else {
			return false;
		}
		
	}
	

	
}
