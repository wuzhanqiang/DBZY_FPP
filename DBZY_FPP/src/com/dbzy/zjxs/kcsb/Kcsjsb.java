package com.dbzy.zjxs.kcsb;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.dbzy.zjxs.po.KcsjsbPO;


public class Kcsjsb extends InterruptListener implements InterruptListenerInterface{
	
	public Kcsjsb() {
		setDescription("库存上报数据校验,wzq");
	}
	
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
    	
    	//获取当前流程
    	ProcessInstance proIns = ctx.getProcessInstance();
    	//获取流程id
		String proInsId = proIns.getId();
		//BO操作
		BOAPI boapi = SDK.getBOAPI();		
		//将子表录入信息存在datas集合中
		List<BO> datas = boapi.query("BO_DY_KCSJSB_S").addQuery("BINDID = ", proInsId).list();
		List<KcsjsbPO> kcList = new ArrayList<>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				KcsjsbPO kp = new KcsjsbPO();
				String sygsmc = data.getString("SYGSMC");
				String bzcpgg = data.getString("BZCPGG");
				kp.setSygsmc(sygsmc);
				kp.setBzcpgg(bzcpgg);				
				kcList.add(kp);
			}
		}
		String sql = null;
		int cnt = 0;
		Connection conn = null;
		//标准品规校验=====================================================================
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			
			for(KcsjsbPO po : kcList) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S where bzpg ='"
						+ po.getBzcpgg() + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errBZPG.append(po.getBzcpgg());
					errBZPG.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errBZPG.toString().length() > 0) {
			throw new BPMNError("ERR_BZPG","请按照公司要求填写标准品规，问题标准品规为：" + errBZPG.toString());			
		}
		//商业公司名称校验=========================================================================
		StringBuffer errSYGSMC = new StringBuffer();			
		try {
			conn = DBSql.open();
			for(KcsjsbPO po : kcList) {
				sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs ='"
						+ po.getSygsmc() + "'";
				cnt = DBSql.getInt(conn, sql, "cnt");
				if (cnt == 0) {
					errSYGSMC.append(po.getSygsmc());
					errSYGSMC.append(",");
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errSYGSMC.toString().length() > 0) {
			throw new BPMNError("ERR_SYGSMC","填报商业公司名称不是标准名称，问题商业公司为：" + errSYGSMC.toString());			
		}
		
		
		return true;
	}
} 