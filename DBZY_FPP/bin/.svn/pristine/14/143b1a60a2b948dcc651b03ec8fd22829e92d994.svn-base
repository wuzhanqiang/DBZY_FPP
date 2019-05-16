package com.nepharm.apps.fpp.biz.gm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.actionsoft.sdk.local.api.ProcessAPI;

public class Sjdr extends ExecuteListener {

	public Sjdr() {
		setDescription("期初数据导入");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		UserContext userContext = ctx.getUserContext();
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		ProcessAPI proapi = SDK.getProcessAPI();
		List<BO> list = boapi.query("BO_DY_WPGL_SJDR_S").addQuery("BINDID = ", proInstId).list();
		if(list != null && !list.isEmpty()) {
			DBSql.update("delete from BO_DY_SBGL_SBDA");
			for(BO data : list) {
				BO bo = new BO();
				bo.set("SBBH", data.get("SBBH"));
				bo.set("SBMC", data.get("SBMC"));
				bo.set("GGXH", data.get("GGXH"));
				bo.set("ZT", data.get("ZT"));
				bo.set("BMBH", data.get("BMBH"));
				bo.set("BM", data.get("BM"));
				bo.set("RZRQ", data.get("RZRQ"));
				bo.set("GJYZ", data.get("GJYZ"));
				bo.set("CFDD", data.get("CFDD"));
				bo.set("SCCJ", data.get("SCCJ"));
				ProcessInstance p = proapi.createBOProcessInstance("obj_d7ff8d9fd2884b34ad938d0ccd5a8790", "admin", "设备档案");
				boapi.create("BO_DY_SBGL_SBDA", bo, p, userContext);
			}
		}
	}

}
