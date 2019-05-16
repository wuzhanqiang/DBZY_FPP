package com.dbzy.zjxs.cfba;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

import com.dbzy.zjxs.po.WfotcPO;

public class Wfotczdba extends InterruptListener {

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_XSSY_WFOTCXG_S").addQuery("BINDID = ", proInsId).list();
		List<WfotcPO> wfList = new ArrayList<WfotcPO>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				WfotcPO op = new WfotcPO();
				String jhqdxg1 = data.getString("JHQDXG1");
				String jhqdxg2 = data.getString("JHQDXG2");
				String jhqdxg3 = data.getString("JHQDXG3");				
				op.setJhqdxg1(jhqdxg1);
				op.setJhqdxg2(jhqdxg2);
				op.setJhqdxg3(jhqdxg3);				
				wfList.add(op);
			}
		}
		Connection conn = null;
		String sql = null;
		StringBuffer errJHQDXG1 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (WfotcPO op : wfList) {
				if(op.getJhqdxg1() != null && !op.getJhqdxg1().isEmpty()){
					sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
							+ op.getJhqdxg1() + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						errJHQDXG1.append(op.getJhqdxg1());
						errJHQDXG1.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQDXG1.toString().length() > 0) {
			throw new BPMNError("ERR_JHQDXG1","进货渠道修改1不是公司标准渠道客户，问题商业公司为：" + errJHQDXG1.toString());			
		}
		StringBuffer errJHQDXG2 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (WfotcPO op : wfList) {
				if(op.getJhqdxg2() != null && !op.getJhqdxg2().isEmpty()){
					sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
							+ op.getJhqdxg2() + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						errJHQDXG2.append(op.getJhqdxg2());
						errJHQDXG2.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQDXG2.toString().length() > 0) {
			throw new BPMNError("ERR_JHQDXG2","进货渠道修改2不是公司标准渠道客户，问题商业公司为：" + errJHQDXG2.toString());			
		}
		StringBuffer errJHQDXG3 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (WfotcPO op : wfList) {
				if(op.getJhqdxg3() != null && !op.getJhqdxg3().isEmpty()){
					sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
							+ op.getJhqdxg3() + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						errJHQDXG3.append(op.getJhqdxg3());
						errJHQDXG3.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQDXG3.toString().length() > 0) {
			throw new BPMNError("ERR_JHQDXG3","进货渠道修改3不是公司标准渠道客户，问题商业公司为：" + errJHQDXG3.toString());			
		}
		return true;
	}
	
}
