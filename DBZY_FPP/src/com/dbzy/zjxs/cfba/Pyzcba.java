package com.dbzy.zjxs.cfba;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

import com.dbzy.zjxs.po.PyzcbaPO;

public class Pyzcba extends InterruptListener {

	public Pyzcba() {
		setDescription("普药产品政策备案校验-客户名称-标准品规-价格_wzq");
	}
	
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_DSJ_PYCPZC_S").addQuery("BINDID = ", proInsId).list();
		List<BO> pyzcjgList = boapi.query("BO_DY_PYGSTYKPJJCKZC_JCSJ_S").list();
		List<PyzcbaPO> pyList = new ArrayList<>();		
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				PyzcbaPO pp = new PyzcbaPO();
				String bzpg = data.getString("BZPG");
				String cxzc = data.getString("CXZC");
				String cxxs = data.getString("CXXS");
				String khmc = data.getString("KHMC");
				pp.setBzpg(bzpg);
				pp.setCxzc(cxzc);
				pp.setCxxs(cxxs);
				pp.setKhmc(khmc);
				pyList.add(pp);
			}
		}
		String sql = null;
		int cnt = 0;
		Connection conn = null;
		
		//标准品规校验
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			
			for(PyzcbaPO po : pyList) {
				sql = "select count(bzpg) cnt from BO_DY_PYGSTYKPJJCKZC_JCSJ_S where bzpg ='"
						+ po.getBzpg() + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
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
			throw new BPMNError("ERR_BZPG","请按照公司要求填写标准品规，问题标准品规为：" + errBZPG.toString());			
		}
		
		//客户名称校验
		StringBuffer errKHMC = new StringBuffer();			
		try {
			conn = DBSql.open();
			for(PyzcbaPO po : pyList) {
				if(!po.getKhmc().isEmpty()) {
					sql = "select count(zdkhmc) cnt from BO_DY_YJSYGS_S2 where zdkhmc ='"+ po.getKhmc() + "'";
					cnt = DBSql.getInt(conn, sql, "cnt");
					if (cnt == 0) {
						errKHMC.append(po.getKhmc());
						errKHMC.append(",");
					}
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errKHMC.toString().length() > 0) {
			throw new BPMNError("ERR_KHMC","填报客户名称不是标准名称，问题客户为：" + errKHMC.toString());			
		}
		
		//价格校验
		Map<String, Double> map = new HashMap<>(); 
		if(pyzcjgList != null && !pyzcjgList.isEmpty()) {
			for(BO da : pyzcjgList) {
				String bzpg = da.getString("BZPG");
				double dckzt = Double.parseDouble(da.getString("CKZT"));
				map.put(bzpg, dckzt) ;							
			}
		}
		StringBuffer errJG = new StringBuffer();
		for(PyzcbaPO po : pyList) {
			double d1 = Double.parseDouble(po.getCxzc());
			
			Set<String> set = map.keySet();
			for(String m : set) {
				if(po.getBzpg().equals(m)) {
					double d2 = map.get(m);
					
					if(d1 > d2) {
					//	errJG.append(po.getKhmc());
						errJG.append("-");
						errJG.append(po.getBzpg());
						errJG.append(",");
					} 					
				}
			}
		}
		if(errJG.toString().length() > 0) {
			throw new BPMNError("ERR_JG","价格有误（填报促销政策高于参考政策），问题标准品规为：" + errJG.toString());
		}
		
		//促销形式校验
		StringBuffer errCXXS = new StringBuffer();
		for(PyzcbaPO po : pyList) {
			
			if (!po.getCxxs().equals("发票")) {
				errCXXS.append(po.getCxxs());
				errCXXS.append(",");
			}
		}		
		if (errCXXS.toString().length() > 0) {
			throw new BPMNError("ERR_CXXS","促销形式不符合公司要求，问题促销形式为：" + errCXXS.toString());			
		}
		return true;
	}
	
}
