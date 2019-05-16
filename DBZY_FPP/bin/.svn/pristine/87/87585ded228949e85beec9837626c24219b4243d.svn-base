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
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;

public class SjdrWP extends InterruptListener {

	public SjdrWP() {
		setDescription("物品数据导入");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		UserContext userContext = ctx.getUserContext();
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		ProcessAPI proapi = SDK.getProcessAPI();
		return wpda(boapi, proapi, proInstId, userContext);
	}
	private boolean wpda(BOAPI boapi, ProcessAPI proapi, String proInstId, UserContext userContext) throws SQLException {
		List<BO> formList = boapi.query(BDConstant.TAB_BD_SJDR_M).addQuery("BINDID = ", proInstId).list();
		if(formList != null && !formList.isEmpty()) {
			BO formData = formList.get(0);
			String BMBH = formData.getString("BMBH");//部门ID
			String BM = formData.getString("BM");//部门名称
			String MS = formData.getString("MS");//导入模式 0为覆盖导入，1为增量导入
			List<BO> list = boapi.query(BDConstant.TAB_BD_SJDR_WP).addQuery("BINDID = ", proInstId).list();
			if(list != null && !list.isEmpty()) {
				if("0".equals(MS)) {
					Connection conn = DBSql.open();
					try {
//						DBSql.update("delete from "+GMConstant.TAB_GM_WPDA+" where BMBH = '"+BMBH+"'");
						List<BO> oList = boapi.query(GMConstant.TAB_GM_WPDA).addQuery("BMBH = ", BMBH).list();
						if(oList != null && !oList.isEmpty()) {
							for(BO obo : oList) {
								String bindId = obo.getBindId();
								boapi.removeByBindId(GMConstant.TAB_GM_WPDA, bindId, conn);
							}
						}
						
					}catch(Exception e) {
						
					}finally {
						conn.close();
					}
				}
				for(BO data : list) {
					BO bo = new BO();
					bo.set("WPBH", data.get("WPBH"));
					bo.set("WPMC", data.get("WPMC"));
					bo.set("WPLX", data.get("WPLX"));
					bo.set("JLDW", data.get("JLDW"));
					bo.set("GGXH", data.get("GGXH"));
					bo.set("CLFW", data.get("CLFW"));
					bo.set("GYSMC", data.get("GYSMC"));
					bo.set("ZZSMC", data.get("ZZSMC"));
					bo.set("CKBH", data.get("CKBH"));
					bo.set("CKMC", data.get("CKMC"));
					bo.set("ZT", data.get("ZT"));
					bo.set("XCL", data.get("XCL"));
					bo.set("BMBH", BMBH);
					bo.set("BM", BM);
					ProcessInstance p = proapi.createBOProcessInstance(GMConstant.DEF_GM_WPDA, "admin", "物品档案");
					boapi.create(GMConstant.TAB_GM_WPDA, bo, p, userContext);
				}
			}
			return true;
		}else {
			return false;
		}
		
	}
}
