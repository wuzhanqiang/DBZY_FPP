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

import com.dbzy.zjxs.po.OtcbaPO;


public class OtcbaNew extends InterruptListener {

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_XSSY_OTCBA_S").addQuery("BINDID = ", proInsId).list();
		List<OtcbaPO> otcList = new ArrayList<OtcbaPO>();
		
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				OtcbaPO op = new OtcbaPO();
				String khmc = data.getString("KHMC");
				String khlx = data.getString("KHLX");
				String ywyxz = data.getString("YWYXZ");
				String jhqd1 = data.getString("JHQD1");
				String jhqd2 = data.getString("JHQD2");
				String jhqd3 = data.getString("JHQD3");
				String otcdb = data.getString("OTCDB");
				String sf = data.getString("SF");
				op.setKhmc(khmc);
				op.setKhlx(khlx);
				op.setYwyxz(ywyxz);
				op.setJhqd1(jhqd1);
				op.setJhqd2(jhqd2);
				op.setJhqd3(jhqd3);
				op.setOtcdb(otcdb);
				op.setSf(sf);
				otcList.add(op);
			}
		}
		Connection conn = null;
		String sql = null;
		
		//校验客户是否重复
		StringBuffer errKHMC = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				sql = "select count(KHMC) cnt from BO_DY_XSSY_OTCBA_S s,BO_DY_XSSY_OTCBA_P t where s.bindid=t.bindid and khmc='"
						+ op.getKhmc() + "' and (lczt is null or lczt<>'流程终止') and t.banf='2018'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt > 1) {
					errKHMC.append(op.getKhmc());
					errKHMC.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errKHMC.toString().length() > 0) {
			throw new BPMNError("ERR_KHMC","备案客户存在重复,重复客户为:" + errKHMC.toString());			
		}
		//------------------------------客户类型------------------------------------
		StringBuffer errKHLX = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				sql = "select count(khlx) cnt from BO_DY_KHLX_JCSJ where khlx ='"
						+ op.getKhlx() + "'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errKHLX.append(op.getKhlx());
					errKHLX.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errKHLX.toString().length() > 0) {
			throw new BPMNError("ERR_KHLX","备案客户类型不符合公司要求，问题客户类型为：" + errKHLX.toString());			
		}
		//----------------------------------业务员工作性质-----------------------------
		StringBuffer errYWYXZ = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				sql = "select count(ywyxz) cnt from BO_DY_KHLX_JCSJ where ywyxz ='"
						+ op.getYwyxz() + "'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errYWYXZ.append(op.getYwyxz());
					errYWYXZ.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errYWYXZ.toString().length() > 0) {
			throw new BPMNError("ERR_YWYXZ","业务员工作性质不符合公司要求，问题业务员工作性质为：" + errYWYXZ.toString());			
		}
		// ---------------------------------校验省区----------------------------	
		StringBuffer errSF  = new StringBuffer();
		try {
			conn = DBSql.open();
			for(OtcbaPO po : otcList) {
				sql = "select count(sheng) cnt from BO_DY_DQBM_S where SHENG ='"
						+ po.getSf() + "'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if(cnt == 0){
					errSF.append(po.getSf());
					errSF.append(",");	
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errSF.toString().length() > 0) {
			throw new BPMNError("ERR_SF","备案省区不符合公司要求，问题省区为：" + errSF.toString());			
		}
		
		//------------------------------进货渠道1------------------------------------
		StringBuffer errJHQD1 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
						+ op.getJhqd1() + "'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errJHQD1.append(op.getJhqd1());
					errJHQD1.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQD1.toString().length() > 0) {
			throw new BPMNError("ERR_JHQD1","备案进货渠道1不是公司标准渠道客户，问题渠道为：" + errJHQD1.toString());			
		}
		//------------------------------进货渠道2------------------------------------
		StringBuffer errJHQD2 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				if(op.getJhqd2() != null && !op.getJhqd2().isEmpty()){
					sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
							+ op.getJhqd2() + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						errJHQD2.append(op.getJhqd2());
						errJHQD2.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQD2.toString().length() > 0) {
			throw new BPMNError("ERR_JHQD2","备案进货渠道2不是公司标准渠道客户，问题渠道为：" + errJHQD2.toString());			
		}
		//------------------------------进货渠道3------------------------------------
		StringBuffer errJHQD3 = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				if(op.getJhqd3() != null && !op.getJhqd3().isEmpty()){
					sql = "select count(distinct sygs) cnt from BO_DY_YJSYGS_S where sygs ='"
							+ op.getJhqd3() + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						errJHQD3.append(op.getJhqd3());
						errJHQD3.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errJHQD3.toString().length() > 0) {
			throw new BPMNError("ERR_JHQD3","备案进货渠道3不是公司标准渠道客户，问题渠道为：" + errJHQD3.toString());			
		}
		//--------------------------限制备案客户100户---------------------------
		StringBuffer errOtcdb = new StringBuffer();
		try {
			conn = DBSql.open();
			for (OtcbaPO op : otcList) {
				sql = "select count(*) cnt from BO_DY_XSSY_OTCBA_S t,BO_DY_XSSY_OTCBA_P s where t.bindid=s.bindid and t.otcdb='"
						+ op.getOtcdb() + "' and (s.lczt is null or s.lczt<>'流程终止') and s.banf='2018'";
				int cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt > 100) {
					errOtcdb.append(op.getOtcdb());
					errOtcdb.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errOtcdb.toString().length() > 0) {
			throw new BPMNError("ERR_OTCDB","OTC代表/自然人备案客户数量大于100户，OTC代表为:" + errOtcdb.toString());			
		}
		return true;
		
	}

}
