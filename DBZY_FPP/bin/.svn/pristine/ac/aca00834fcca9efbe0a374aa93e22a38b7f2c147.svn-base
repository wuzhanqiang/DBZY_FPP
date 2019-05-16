package com.dbzy.zjxs.cfba;

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

import com.dbzy.zjxs.po.LnzdbaPO;

public class Lnzdba extends InterruptListener implements InterruptListenerInterface{

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		//获取当前流程
    	ProcessInstance proIns = ctx.getProcessInstance();
    	//获取流程id
		String proInsId = proIns.getId();
		//BO操作
		BOAPI boapi = SDK.getBOAPI();		
		//将子表录入数据存在】到datas集合中
		List<BO> datas = boapi.query("BO_DY_XSSY_LNZDBA_S").addQuery("BINDID = ", proInsId).list();
		List<LnzdbaPO> LnList = new ArrayList<>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				LnzdbaPO lp = new LnzdbaPO();
				String ywy = data.getString("YWY");
				String khmc = data.getString("KHMC");
				String bzpg = data.getString("BZPG");
				String khlx = data.getString("KHLX");
				String jhqd1 = data.getString("JHQD1");
				String jhqd2 = data.getString("JHQD2");
				String jhqd3 = data.getString("JHQD3");
				lp.setYwy(ywy);
				lp.setKhmc(khmc);
				lp.setBzpg(bzpg);
				lp.setKhlx(khlx);
				lp.setJhqd1(jhqd1);
				lp.setJhqd2(jhqd2);
				lp.setJhqd3(jhqd3);
				LnList.add(lp);
			}
		}
		Connection conn = null;
		String sql = null;
		int cnt = 0;
		//业务员数量校验----------------------------------------
		StringBuffer errYWY = new StringBuffer();
		try {
			conn = DBSql.open();
			//遍历业务员集合，查询业务员总数量
			for(LnzdbaPO po : LnList) {
				sql = "select count(distinct(t.ywy||t.khmc)) cnt from BO_DY_XSSY_LNZDBA_S t,BO_DY_XSSY_LNZDBA_P s "
						+ "where t.bindid=s.bindid "
						+ "and t.ywy='" + po.getYwy() + "'";
		//				+ "and (s.lczt is null or s.lczt<>'流程终止') "
		//				+ "and s.banf='2018'";
				
				//获得第一个记录的字段值，并取int类型getInt(java.lang.String sql, java.lang.String filedName)
				cnt = DBSql.getInt(conn,sql, "cnt");			
				if (cnt > 100) {
					errYWY.append(po.getYwy());
					errYWY.append(",");
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		
		if ( errYWY.toString().length() > 0 ) {
			throw new BPMNError("ERR_YWY","业务员+终端名称备案客户数量大于100户，业务员为:" + errYWY.toString());
		}
		// -------------------------------------终端名称与标准品规绑定校验唯一-------------------
		StringBuffer errMCPG = new StringBuffer();
		List<String> mclist = new ArrayList<>();
		for(LnzdbaPO po : LnList){
			StringBuffer mcpg = new StringBuffer();
			mcpg.append(po.getKhmc());
			mcpg.append(po.getBzpg());
			mclist.add(mcpg.toString());		
			
		}
		try {
			conn = DBSql.open();
			for(String s : mclist) {
				sql = "select count(*) cnt from BO_DY_XSSY_LNZDBA_S t,BO_DY_XSSY_LNZDBA_P s " +
						  "where t.bindid=s.bindid and (s.lczt is null or s.lczt<>'流程终止') " +
						  "and t.khmc||t.bzpg in ('"
						  + s + "')";
					cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt > 1) {
						errMCPG.append(s);
						errMCPG.append(",");
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errMCPG.toString().length() > 0) {
			throw new BPMNError("ERR_MCPG","终端名称+标准品规存在重复,重复项为:" + errMCPG.toString());			
		}
		// ---------------------------------校验标准品规----------------------------
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			for (LnzdbaPO po : LnList) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S  where bzpg ='" + po.getBzpg() + "'";
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
			throw new BPMNError("ERR_BZPG","备案标准品规不符合公司要求，问题品规为：" + errBZPG.toString());
		}
		//------------------------------客户类型------------------------------------
				
		StringBuffer errKHLX = new StringBuffer();			
		try {
			conn = DBSql.open();
			for (LnzdbaPO po : LnList) {
				sql = "select count(khlx) cnt from bo_dy_khlx_jcsj where khlx ='" + po.getKhlx() + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				
				if (cnt == 0) {
					errKHLX.append(po.getKhlx());
					errKHLX.append(",");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errKHLX.toString().length() > 0) {
			throw new BPMNError("ERR_KHLX","备案终端类型不符合公司要求，问题终端类型为：" + errKHLX.toString());
		}
		//------------------------------进货渠道1------------------------------------
		StringBuffer errJHQD1 = new StringBuffer();
		 try {
				conn = DBSql.open();
			 for(LnzdbaPO po : LnList) {
				 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S where sygs = '"
						 + po.getJhqd1() +"'";
				 cnt = DBSql.getInt(conn,sql, "cnt");
				 if(cnt==0){
					 errJHQD1.append(po.getJhqd1());
					 errJHQD1.append(",");
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errJHQD1.toString().length()>0){
			throw new BPMNError("ERR_JHQD1","进货渠道1不符合公司要求，问题渠道为：" +errJHQD1.toString());
		}
		//-----------------------------------进货渠道2----------------------------------
		 StringBuffer errJHQD2 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LnzdbaPO po : LnList) {
				 if(po.getJhqd2() != null && !po.getJhqd2().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S where sygs = '"
							 + po.getJhqd2()+"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errJHQD2.append(po.getJhqd2());
						 errJHQD2.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errJHQD2.toString().length()>0){
			throw new BPMNError("ERR_JHQD2","进货渠道2不符合公司要求，问题渠道为：" +errJHQD2.toString());
		}
		//-----------------------------------进货渠道3----------------------------------
		 StringBuffer errJHQD3 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LnzdbaPO po : LnList) {
				 if(po.getJhqd3() != null && !po.getJhqd3().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S where sygs = '"
							 + po.getJhqd3()+"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errJHQD3.append(po.getJhqd3());
						 errJHQD3.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errJHQD3.toString().length()>0){
			throw new BPMNError("ERR_JHQD3","进货渠道3不符合公司要求，问题渠道为：" +errJHQD3.toString());
		}
		return true;
	}

}
