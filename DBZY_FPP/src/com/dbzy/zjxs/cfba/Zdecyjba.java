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
import com.dbzy.zjxs.po.EcyjPO;

public class Zdecyjba extends InterruptListener {

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_DSJ_ZDECYJBAB_S").addQuery("BINDID = ", proInsId).list();
		List<EcyjPO> zblist = new ArrayList<>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				EcyjPO ep = new EcyjPO();
				String pssykhmc = data.getString("PSSYKHMC");
				String zdbakhmc = data.getString("ZDBAKHMC");
				String bzpg = data.getString("BZPG");
				String dls = data.getString("DLS");
				
				ep.setPssykhmc(pssykhmc);
				ep.setZdbakhmc(zdbakhmc);
				ep.setBzpg(bzpg);
				ep.setDls(dls);	
				zblist.add(ep);
			}
		}
		Connection conn = null;
		String sql = null;
		// ------------------------------------限制导入配送商业客户名称----------------------
		StringBuffer errSYKHMC = new StringBuffer();
		try {
			conn = DBSql.open();
			for (EcyjPO ep : zblist) {
				sql = "select count(sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
						+ ep.getPssykhmc() + "'";
		        int	cnt = DBSql.getInt(conn,sql, "cnt");
	        	if (cnt == 0){
	        		errSYKHMC.append(ep.getPssykhmc());
	        		errSYKHMC.append(",");
	        	}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errSYKHMC.toString().length() > 0) {
			throw new BPMNError("ERR_SYKHMC","配送商业客户名称不符合公司要求，问题配送商业客户名称为：" + errSYKHMC.toString());			
		}

    	// ------------------------------------限制导入终端备案客户名称---------------------- 
		StringBuffer errZDBAKHMC = new StringBuffer();
		try {
			conn = DBSql.open();
			for (EcyjPO ep : zblist) {
				sql = "select count(mbzdkhmc) cnt from VIEW_DY_LCZD where mbzdkhmc ='"
						+ ep.getZdbakhmc() + "'";
		        int	cnt = DBSql.getInt(conn,sql, "cnt");
	        	if (cnt == 0){
	        		errZDBAKHMC.append(ep.getZdbakhmc());
	        		errZDBAKHMC.append(",");
	        	}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errZDBAKHMC.toString().length() > 0) {
			throw new BPMNError("ERR_ZDBAKHMC","终端备案客户名称不符合公司要求，问题终端备案客户名称为：" + errZDBAKHMC.toString());			
		}
		
        // ---------------------------------校验代理商----------------------------
		StringBuffer errDLS = new StringBuffer();
		try {
			conn = DBSql.open();
			for (EcyjPO ep : zblist) {
				sql = "select count(JXSXM) cnt from BO_DY_CWB_XZJXS_P  where lczt = '已完成' and JXSXM ='"
						+ ep.getDls() + "'";
		        int	cnt = DBSql.getInt(conn,sql, "cnt");
	        	if (cnt == 0){
	        		errDLS.append(ep.getDls());
	        		errDLS.append(",");
	        	}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errDLS.toString().length() > 0) {
			throw new BPMNError("ERR_DLS","备案代理商不符合公司要求，问题代理商为：" + errDLS.toString());			
		}
		// ------------------------------------校验标准品规----------------------  
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			for (EcyjPO ep : zblist) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S where bzpg ='"
						+ ep.getBzpg() + "'";
		        int	cnt = DBSql.getInt(conn,sql, "cnt");
	        	if (cnt == 0){
	        		errBZPG.append(ep.getBzpg());
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
		return true;
	}
	
}
