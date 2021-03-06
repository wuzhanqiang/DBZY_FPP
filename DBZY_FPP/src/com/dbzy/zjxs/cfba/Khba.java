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
import com.dbzy.zjxs.po.KhbaPO;


public class Khba extends InterruptListener {

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_ZDZC_KHBA_S").addQuery("BINDID = ", proInsId).list();
		
		List<KhbaPO> khbaList = new ArrayList<>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				KhbaPO kp = new KhbaPO();
				String sq = data.getString("SQ");
				String khbzmc = data.getString("KHBZMC");
				String bzpg = data.getString("BZPG");
				String khjb = data.getString("KHJB");
				kp.setSq(sq);
				kp.setKhbzmc(khbzmc);
				kp.setBzpg(bzpg);
				kp.setKhjb(khjb);
				khbaList.add(kp);
			}
		}
		String sql = null;
		Connection conn = null;
		StringBuffer errKHSQ = new StringBuffer();
		List<String> mclist = new ArrayList<>();
		for(KhbaPO po : khbaList) {
			StringBuffer khsq = new StringBuffer();
			khsq.append(po.getKhbzmc());
			khsq.append(po.getSq());
			khsq.append(po.getBzpg());
			mclist.add(khsq.toString());			
		}
		try {
			conn = DBSql.open();
			for(String s : mclist) {
				sql =  "select count(*) cnt from BO_DY_YJSYGS_S2 t where t.sygs||t.sq||t.bzpg in '"+ s +"'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt > 0) {
					errKHSQ.append(s);
					errKHSQ.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errKHSQ.toString().length() > 0) {
			throw new BPMNError("ERR_KHSQ","客户名称+省份+标准品规存在重复,重复项为:" + errKHSQ.toString());			
		}
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			for(KhbaPO po : khbaList) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S where bzpg ='"
						+ po.getBzpg() + "'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errBZPG.append(po.getBzpg());
					errBZPG.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errBZPG.toString().length() > 0) {
			throw new BPMNError("ERR_BZPG","备案标准品规不符合公司要求，问题品规为：" + errBZPG.toString());			
		}	

		StringBuffer errKHJB = new StringBuffer();
		for(KhbaPO po : khbaList) {
			
			if (!po.getKhjb().equals("一级") && !po.getKhjb().equals("二级")) {
				errKHJB.append(po.getKhjb());
				errKHJB.append(",");
			}
		}		
		if (errKHJB.toString().length() > 0) {
			throw new BPMNError("ERR_KHJB","客户级别不符合公司要求，问题级别为：" + errKHJB.toString());			
		}
		
		return true;
	}

}
