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
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.dbzy.zjxs.po.OtczcbaPO;



public class Otczcba extends InterruptListener implements InterruptListenerInterface{

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();
		//当前流程子表BO集合
		List<BO> datas = boapi.query("BO_DY_DSJ_OTCCPZC_S").addQuery("BINDID = ", proInsId).list();
		//底价表BO集合
		List<BO> djlist = boapi.query("BO_DY_OTCDJ_S").list();
		//标准品规表BO集合
		//List<BO> bzpgList = boapi.query("BO_DY_KC_BZPG_S").list();
		//子表数据集合
		List<OtczcbaPO> otcList = new ArrayList<>();		
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				OtczcbaPO op = new OtczcbaPO();
				String bzpg = data.getString("BZPG");
				String bakpj = data.getString("BAKPJ");
				String bazcje = data.getString("BAZCJE");
				String khmc = data.getString("KHMC");
				op.setBzpg(bzpg);
				op.setBakpj(bakpj);
				op.setBazcje(bazcje);
				op.setKhmc(khmc);
				otcList.add(op);				
			}
		}
		//标准品规集合
//		List<String> blist = new ArrayList<>();
//		if(bzpgList != null && !bzpgList.isEmpty()) {
//			for(BO data : bzpgList) {				
//				String bzpg = data.getString("BZPG");				
//				blist.add(bzpg);				
//			}
//		}				
//		StringBuffer errBZPG = new StringBuffer();
//		boolean flag = true;
//		for(OtczcbaPO op : otcList) {
//			for(String s : blist) {
//				if (op.getBzpg().equals(s)) {
//					flag = false;					
//				}				
//			}
//			if(flag) {
//				errBZPG.append(op.getBzpg());
//				errBZPG.append(",");
//			}
//		}
		String sql = null;
		int cnt = 0;
		Connection conn = null;
		//标准品规校验
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			
			for(OtczcbaPO po : otcList) {
				sql = "select count(bzpg) cnt from BO_DY_OTCTG_BZPG_S where bzpg ='"
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
		//底价集合
		Map<String, Double> map = new HashMap<>(); 
		if(djlist != null && !djlist.isEmpty()) {
			for(BO da : djlist) {
				String bzpg = da.getString("BZPG");
				double dj = Double.parseDouble(da.getString("DJ"));
				map.put(bzpg, dj) ;							
			}
		}
		StringBuffer errDJ = new StringBuffer();
		double d5 = 0.00000000000001;
		for(OtczcbaPO op : otcList) {
			double d1 = Double.parseDouble(op.getBakpj());
			double d2 = Double.parseDouble(op.getBazcje());
			Set<String> set = map.keySet();
			for(String m : set) {
				if(op.getBzpg().equals(m)) {
					double d3 = map.get(m);
					double d4 = d1 - d2;
					if((d4+d5) < d3) {
						errDJ.append(op.getKhmc());
						errDJ.append("-");
						errDJ.append(op.getBzpg());
						errDJ.append(",");
					} 					
				}
			}
		}
		if(errDJ.toString().length() > 0) {
			throw new BPMNError("ERR_DJ","价格有误，问题客户名称-标准品规为：" + errDJ.toString());
		}

		return true;
	}

}
