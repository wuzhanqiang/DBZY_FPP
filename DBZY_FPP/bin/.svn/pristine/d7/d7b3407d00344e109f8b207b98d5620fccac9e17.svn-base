package com.dbzy.zjxs.test;

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

public class AddAll extends InterruptListener implements InterruptListenerInterface{
	
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
    	
    	//获取当前流程
    	ProcessInstance proIns = ctx.getProcessInstance();
    	//获取流程id
		String proInsId = proIns.getId();
		//BO操作
		BOAPI boapi = SDK.getBOAPI();
		
		//将查询的结果集存在datas集合中
		List<BO> datas = boapi.query("BO_DY_XSSY_LNZDBA_S").addQuery("BINDID = ", proInsId).list();
		
		/**********************************************************************************************/
		//新建业务员集合
		List<String> ywyList = new ArrayList<String>();
		
		//判断：如果查询集合非空，遍历集合，存到业务员集合中
		if(!datas.isEmpty()) {
			for(BO data : datas) {
				String ywy = data.getString("YWY");
				ywyList.add(ywy);
			}
		}
		Connection conn = null;
		String sql = null;
		int cnt = 0;
		//用于存储超出数量的业务员名称
		StringBuffer sb_obj_name = new StringBuffer();
		try {
			conn = DBSql.open();
			//遍历业务员集合，查询业务员总数量
			for(String s : ywyList) {
				sql = "select count(distinct(t.ywy||t.khmc)) cnt from BO_DY_XSSY_LNZDBA_S t,BO_DY_XSSY_LNZDBA_P s "
						+ "where t.bindid=s.bindid "
						+ "and t.ywy='" + s + "'";
	//					+ "and (s.lczt is null or s.lczt<>'流程终止') "
	//					+ "and s.banf='2018'";
				
				//获得第一个记录的字段值，并取int类型getInt(java.lang.String sql, java.lang.String filedName)
				cnt = DBSql.getInt(conn,sql, "cnt");
				//System.out.print(cnt + "-");
				if (cnt > 100) {
					sb_obj_name.append(s);
					sb_obj_name.append(" ");
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		
		if ( sb_obj_name.toString().length() > 0 ) {
			throw new BPMNError("ERR_KHLX","业务员+终端名称备案客户数量大于100户，业务员为:" + sb_obj_name.toString());
		}
		/********************************************************************************************/
		//新建集合
    	List<String> khmcList = new ArrayList<String>();
    	List<String> bzpgList = new ArrayList<String>();
    	List<String> khlxList = new ArrayList<String>();
    	//判断：如果查询集合非空，遍历集合，存到集合中
		if(!datas.isEmpty()) {
			for(BO data : datas) {
				String khmc = data.getString("KHMC");
				String bzpg = data.getString("BZPG");
				String khlx = data.getString("KHLX");
				khmcList.add(khmc);
				bzpgList.add(bzpg);
				khlxList.add(khlx);
			}
		}
		
		// -------------------------------------终端名称与标准品规绑定校验唯一-------------------
		String[] khmcb = new String[khmcList.size()];
		khmcb = khmcList.toArray(khmcb);
		
		//String[] bzpgb = (String[])bzpgList.toArray();//Object数组不能强转为String数组
		String[] bzpgb = new String[bzpgList.size()];
		bzpgb = bzpgList.toArray(khmcb);
		
		StringBuffer sb_obj = new StringBuffer();
		
		for(int n=0;n<khlxList.size();n++){
			sb_obj.append(khmcList.get(n) + bzpgList.get(n) + ",");
			
			//System.out.println(khlxList.get(n)+bzpgList.get(n));
		}
		
		sql = null;
		cnt = 0;
		
		String[] sb_obj_kh = sb_obj.toString().split(",");
		StringBuffer sb_obj_aa = new StringBuffer();// -----存储问题数组
		try {
			conn = DBSql.open();
			for (int i = 0; i < sb_obj_kh.length; i++) {
				sql = "select count(*) cnt from BO_DY_XSSY_LNZDBA_S t,BO_DY_XSSY_LNZDBA_P s "
						+ "where t.bindid=s.bindid "
						+ "and t.khmc||t.bzpg in ('" + sb_obj_kh[i] + "') ";
	//					+ "and (s.lczt is null or s.lczt<>'流程终止') "
	//					+ "and s.banf='2018'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt > 1) {
					sb_obj_aa.append(sb_obj_kh[i]);
					sb_obj_aa.append(" ");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_aa.toString().length() > 0) {
			throw new BPMNError("ERR_ZDMC","终端名称+标准品规存在重复,重复项为:" + sb_obj_aa.toString());
		}
		
		// ---------------------------------校验标准品规----------------------------
		StringBuffer sb_obj_bb = new StringBuffer();// -----存储问题数组
		try {
			conn = DBSql.open();
			for (int i = 0; i < bzpgb.length; i++) {
				sql = "select count(bzpg) cnt from BO_DY_KC_BZPG_S  where bzpg ='" + bzpgb[i] + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				
				if (cnt == 0) {
					sb_obj_bb.append(bzpgb[i]);
					sb_obj_bb.append(" ");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_bb.toString().length() > 0) {
			throw new BPMNError("ERR_BZPG","备案标准品规不符合公司要求，问题品规为：" + sb_obj_bb.toString());
		}		
		
		//------------------------------客户类型------------------------------------
		//String[] khlx_arr = (String[])khlxList.toArray();
		String[] khlx_arr = new String[khlxList.size()];
		khlx_arr = khlxList.toArray(khlx_arr);
		
		StringBuffer sb_obj_cc = new StringBuffer();// -----存储问题数组
		try {
			conn = DBSql.open();
			for (int i = 0; i < khlx_arr.length; i++) {
				sql = "select count(khlx) cnt from bo_dy_khlx_jcsj where khlx ='" + khlx_arr[i] + "'";
				cnt = DBSql.getInt(conn,sql, "cnt");
				
				if (cnt == 0) {
					sb_obj_cc.append(khlx_arr[i]);
					sb_obj_cc.append(" ");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (sb_obj_cc.toString().length() > 0) {
			throw new BPMNError("ERR_KHLX","备案终端类型不符合公司要求，问题终端类型为：" + sb_obj_cc.toString());
		}
		
		return true;
	}

}
