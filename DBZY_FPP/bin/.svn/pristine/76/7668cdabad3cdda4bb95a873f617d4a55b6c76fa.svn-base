package com.nepharm5.apps.fpp.nepgErqi.biz.YQblsxsm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class Hyjyblsxsm extends ExecuteListener {

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {

		String bindid = ctx.getProcessInstance().getId();
		String blsxsm = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_HYJY", bindid);
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//提交纪要报批单
				if( stepName.equals("obj_c7fb709064e0000110eb1250ff601204")){
					blsxsm = "此节点无办理时限！";
				}
			    //副总裁审阅纪要报批单
				else 	if(stepName.equals("obj_c7fb709177d00001f5e99a508f337eb0") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁批示纪要报批单
				else 	if(stepName.equals("obj_c7fb70a0c16000013a6ffd103127167a")){
					blsxsm = "此节点办理时限为3天！";
				}

				//董事长批示纪要报批单
				else 	if(stepName.equals("obj_c7fb70a29b300001d6e73afe1c0d159d")){
					blsxsm = "此节点办理时限为4天！";
				}	
				//总裁办主任接收（董事长）总裁办主任接收（总裁）召开会议
				else 	if(stepName.equals("obj_c7fb70a1f1b0000156da5df06f7417ad") || stepName.equals("obj_c7fb7092047000017cd41980bfcf12b5") || stepName.equals("obj_c7fb70a179a0000176ff1a404f291ebb")  ){
					blsxsm = "此节点无办理时限！";
				}
				else 	{
					blsxsm = "此节点办理时限为1天！";
				}
			}catch (Exception e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace(System.err);
			
			}
			
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_XZBG_HYJY set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
				conn = DBSql.open();
				try {
					st = conn.createStatement();
					int result = st.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace(System.err);
				}finally{
					DBSql.close(conn, st, null);
				}
				
			}
		}
	
	}

}
