package com.dbzy.zjxs.zdbaxg;

import java.sql.Connection;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;

public class Lnzdbaxg extends InterruptListener implements InterruptListenerInterface{
	
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
    	
    	//获取当前流程
    	ProcessInstance proIns = ctx.getProcessInstance();
    	//获取流程id
		String proInsId = proIns.getId();
		//BO操作
		//BOAPI boapi = SDK.getBOAPI();
		
		//将查询的结果集存在datas集合中
		//List<BO> datas = boapi.query("BO_DY_XSSY_LNZDBA_S").addQuery("BINDID = ", proInsId).list();
		
		String sql = "select jhqdxg1,jhqdxg2,jhqdxg3 from BO_DY_XSSY_LNZDBAXG_S where bindid = '" + proInsId + "'";
		String pssy1 = DBSql.getString(sql, "JHQDXG1");
		String pssy2 = DBSql.getString(sql, "JHQDXG2");
		String pssy3 = DBSql.getString(sql, "JHQDXG3");

		StringBuffer sb_obj_pssy1 = new StringBuffer();
		
		sb_obj_pssy1.append(pssy1);
		String[] jhqd1_arr = sb_obj_pssy1.toString().split(",");
		StringBuffer sb_obj_aa = new StringBuffer();
		Connection conn = null;
		try {
			conn = DBSql.open();
			for (int i = 0; i < jhqd1_arr.length; i++) {
				if (!jhqd1_arr[i].equals("null")) {
					sql = "select count(sygs) cnt from VIEW_DY_QDKH where sygs ='" + jhqd1_arr[i] + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						sb_obj_aa.append(jhqd1_arr[i]);
						sb_obj_aa.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_aa.toString().length() > 0) {
			throw new BPMNError("ERR_JHQD1","进货渠道修改1不是公司标准渠道客户，问题商业公司为：" + sb_obj_aa.toString());
		}
		
		/*********************************************************************************************/
		StringBuffer sb_obj_pssy2 = new StringBuffer();
		
		sb_obj_pssy2.append(pssy2);
		String[] jhqd2_arr = sb_obj_pssy2.toString().split(",");
		StringBuffer sb_obj_bb = new StringBuffer();
		try {
			conn = DBSql.open();
			for (int i = 0; i < jhqd2_arr.length; i++) {
				if (!jhqd2_arr[i].equals("null")) {
					sql = "select count(sygs) cnt from VIEW_DY_QDKH where sygs ='" + jhqd2_arr[i] + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						sb_obj_bb.append(jhqd2_arr[i]);
						sb_obj_bb.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_bb.toString().length() > 0) {
			throw new BPMNError("ERR-JHQD2","进货渠道修改2不是公司标准渠道客户，问题商业公司为：" + sb_obj_bb.toString());
		}
		
		/*********************************************************************************************/
		StringBuffer sb_obj_pssy3 = new StringBuffer();
		sb_obj_pssy3.append(pssy3);
		String[] jhqd3_arr = sb_obj_pssy3.toString().split(",");
		StringBuffer sb_obj_cc = new StringBuffer();
		try {
			conn = DBSql.open();
			for (int i = 0; i < jhqd3_arr.length; i++) {
				if (!jhqd3_arr[i].equals("null")) {
					sql = "select count(sygs) cnt from VIEW_DY_QDKH where sygs ='" + jhqd3_arr[i] + "'";
					int cnt = DBSql.getInt(conn,sql, "cnt");
					if (cnt == 0) {
						sb_obj_cc.append(jhqd3_arr[i]);
						sb_obj_cc.append(",");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_cc.toString().length() > 0) {
			throw new BPMNError("ERR-JHQD3","进货渠道修改3不是公司标准渠道客户，问题商业公司为：" + sb_obj_cc.toString());
		}

		return true;
	}
}
