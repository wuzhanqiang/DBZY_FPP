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
import com.dbzy.zjxs.po.LccpzdbaPO;

public class LccpzdbaWZQ extends InterruptListener implements InterruptListenerInterface{

	public LccpzdbaWZQ() {
		setDescription("临床产品终端备案校验,wzq");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ProcessInstance proIns = ctx.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();		
		List<BO> datas = boapi.query("BO_DY_ZDZC_LCZD_S").addQuery("BINDID = ", proInsId).list();//获取当前子表集合    (固定模式)
		
		List<LccpzdbaPO> lccpzdbaList = new ArrayList<>();//声明一个集合

		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {
				LccpzdbaPO lp = new LccpzdbaPO();
				
				String fzdls = data.getString("FZDLS");
				String bzpg = data.getString("BZPG");
				String mbzdkhmc = data.getString("MBZDKHMC");
				String zdlx = data.getString("ZDLX");
				String sq = data.getString("MBZDSZS1");
				String pssy1  = data.getString("PSSY1");
				String pssy2  = data.getString("PSSY2");
				String pssy3  = data.getString("PSSY3");
				String pssy4 = data.getString("PSSY4");
				String pssy5 = data.getString("PSSY5");
				
				lp.setFzdls(fzdls);
				lp.setBzpg(bzpg);
				lp.setMbzdkhmc(mbzdkhmc);
				lp.setZdlx(zdlx);
				lp.setSq(sq);
				lp.setPssy1(pssy1);
				lp.setPssy2(pssy2);
				lp.setPssy3(pssy3);
				lp.setPssy4(pssy4);
				lp.setPssy5(pssy5);
				
				lccpzdbaList.add(lp);

			}//遍历子表集合 放到 bzpgList 里面
		}
		Connection conn = null;
		String sql = null;
		int cnt = 0;
//----------------------------------"目标终端客户名称+标准品规+校验唯一"-------------------
		StringBuffer errMCPG = new StringBuffer();
		List<String> mclist = new ArrayList<>();
		for(LccpzdbaPO po : lccpzdbaList){
			StringBuffer mcpg = new StringBuffer();
			mcpg.append(po.getMbzdkhmc());
			mcpg.append(po.getBzpg());
			mclist.add(mcpg.toString());		
			
		}
		try {
			conn = DBSql.open();
			for(String s : mclist) {
				sql = "select count(*) cnt from BO_DY_ZDZC_LCZD_S t,BO_DY_ZDZC_LCZD_P s " +
						  "where t.bindid=s.bindid and s.banf = '2019' and s.lczt<>'流程终止' " +
						  "and t.mbzdkhmc||t.bzpg in ('"
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
			throw new BPMNError("ERR_BZPG","客户名称+标准品规存在重复,重复项为:" + errMCPG.toString());			
		}
		
		
// ---------------------------------校验标准品规----------------------------
		StringBuffer errBZPG = new StringBuffer();
		try {
			conn = DBSql.open();
			for(LccpzdbaPO po : lccpzdbaList) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S where bzpg ='"
						+ po.getBzpg() + "'";
			    cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errBZPG.append(po.getBzpg());
					errBZPG.append(","); //遍历上面标准品规集合 ，查数cnt, 不符合的拼到errBZPG字符串里
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
// ---------------------------------校验代理商----------------------------
		StringBuffer errDLS = new StringBuffer();
		try {
			conn = DBSql.open();
			for(LccpzdbaPO po : lccpzdbaList) {
				sql = "select count(JXSXM) cnt from BO_DY_CWB_XZJXS_P"
						+ " where lczt = '已完成' and JXSXM ='"
						+ po.getFzdls() + "'";
			    cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errDLS.append(po.getFzdls());
					errDLS.append(","); //遍历上面代理商集合 ，查数cnt, 不符合的拼到errDLS字符串里
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errDLS.toString().length() > 0) {
			throw new BPMNError("ERR_DLS","请按照公司要求填写负责代理商，问题代理商为：" + errDLS.toString());			
		}	
				
// ---------------------------------校验省区----------------------------	
		StringBuffer errSQ  = new StringBuffer();
		try {
			conn = DBSql.open();
			for(LccpzdbaPO po : lccpzdbaList) {
				sql = "select count(sheng) cnt from BO_DY_DQBM_S where SHENG ='"
						+ po.getSq() + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				if(cnt == 0){
					errSQ.append(po.getSq());
					errSQ.append(",");	
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errSQ.toString().length() > 0) {
			throw new BPMNError("ERR_SQ","备案省区不符合公司要求，问题省区为：" + errSQ.toString());			
		}
		
		
// ---------------------------------校验终端类型-------------------------
		StringBuffer errZDLX  = new StringBuffer();
		List<String> zdlxList = new ArrayList<>();
		zdlxList.add("基层医疗机构");
		zdlxList.add("三级医院");
		zdlxList.add("二级医院");
		zdlxList.add("一级医院");
		zdlxList.add("民营医院");
		zdlxList.add("院外药房");
		zdlxList.add("药房（需要市场部审批）");
		zdlxList.add("商业公司（需要市场部审批）");
		zdlxList.add("部队医院");
		boolean flag = true;
		for(LccpzdbaPO po : lccpzdbaList) {
			for(String s : zdlxList) {
				if (po.getZdlx().equals(s)) {
					flag = false;					
				}				
			}
			if(flag) {
				errZDLX.append(po.getZdlx());
				errZDLX.append(",");
			}
		}		
		if (errZDLX.toString().length() > 0) {
			throw new BPMNError("ERR_ZDLX","终端类型不符合公司要求，问题终端类型为：" + errZDLX.toString());			
		}
		
		
// ---------------------------当终端类型填写院外药房时，则院外药房挂靠医院名称为必填项-------------
		StringBuffer errywyf  = new StringBuffer();
		try {
			conn = DBSql.open();
			String sql1 = "select count(*) cnt from BO_DY_ZDZC_LCZD_S where wyyf is null and zdlx = '院外药房' and bindid = '" + proInsId +"'";
	        cnt = DBSql.getInt(conn,sql1, "cnt");
	        if (cnt > 0)
	        {
	        	errywyf.append(cnt);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
    	if (errywyf.toString().length()>0){
			throw new BPMNError("ERR_YWYF","当终端类型填写院外药房时，则院外药房挂靠医院名称为必填项：" + errywyf.toString()+"项，不满足要求。");
		}	
		
//-----------------------------------配送商业1----------------------------------
		 StringBuffer errpssy1 = new StringBuffer();
		 try {
				conn = DBSql.open();
			 for(LccpzdbaPO po : lccpzdbaList) {
				 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs = '"
						 + po.getPssy1() +"'";
				 cnt = DBSql.getInt(conn,sql, "cnt");
				 if(cnt==0){
					 errpssy1.append(po.getPssy1());
					 errpssy1.append(",");
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errpssy1.toString().length()>0){
			throw new BPMNError("ERR_PSSY1","配送商业1不符合公司要求，问题商业公司为：" +errpssy1.toString());
		}
			 
		
//-----------------------------------配送商业2----------------------------------
		 StringBuffer errpssy2 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LccpzdbaPO po : lccpzdbaList) {
				 if(po.getPssy2() != null && !po.getPssy2().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs = '"
							 + po.getPssy2()+"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errpssy2.append(po.getPssy2());
						 errpssy2.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errpssy2.toString().length()>0){
			throw new BPMNError("ERR_PSSY2","配送商业2不符合公司要求，问题商业公司为：" +errpssy2.toString());
		}
			 		
//----------------------------------配送商业3----------------------------------
		 StringBuffer errpssy3 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LccpzdbaPO po : lccpzdbaList) {
				 if(po.getPssy3() != null && !po.getPssy3().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs = '"
							 + po.getPssy3() +"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errpssy3.append(po.getPssy3());
						 errpssy3.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errpssy3.toString().length()>0){
			throw new BPMNError("ERR_PSSY3","配送商业3不符合公司要求，问题商业公司为：" +errpssy3.toString());
		}
		
//----------------------------------配送商业4----------------------------------
		 StringBuffer errpssy4 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LccpzdbaPO po : lccpzdbaList) {
				 if(po.getPssy4() != null && !po.getPssy4().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs = '"
							 + po.getPssy4() +"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errpssy4.append(po.getPssy4());
						 errpssy4.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errpssy4.toString().length()>0){
			throw new BPMNError("ERR_PSSY4","配送商业4不符合公司要求，问题商业公司为：" +errpssy4.toString());
		}
		
//----------------------------------配送商业5----------------------------------
		 StringBuffer errpssy5 = new StringBuffer();
		 try {
			 conn = DBSql.open();
			 for(LccpzdbaPO po : lccpzdbaList) {
				 if(po.getPssy5() != null && !po.getPssy5().isEmpty()){
					 sql = "select count(sygs) cnt from BO_DY_YJSYGS_S2 where sygs = '"
							 + po.getPssy5() +"'";
					 cnt = DBSql.getInt(conn,sql, "cnt");
					 if(cnt==0){
						 errpssy5.append(po.getPssy5());
						 errpssy5.append(",");
					 }
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
		 }finally {
				DBSql.close(conn);
		 }
		if(errpssy5.toString().length()>0){
			throw new BPMNError("ERR_PSSY5","配送商业5不符合公司要求，问题商业公司为：" +errpssy5.toString());
		}
			 	
		return true;
	}

}
