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
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;

public class QsbgBlsxsmStepRTClassA_BQZHB extends ExecuteListener {

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {

		String bindid = ctx.getProcessInstance().getId();
		//String taskId=SDK.getProcessAPI().getInstanceById(bindid).getStartTaskInstId();
		String blsxsm = "";
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_QSBG_BQZHB", bindid);
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			String jjcd = formData.get("JJCD")==null?"": formData.get("JJCD").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//总经理级审批请示、报告报批单
				if( stepName.equals("obj_c8022f600ac00001c4937dec1f6f4560")){
					blsxsm = "此节点无办理时限！";
				}
				else if( jjcd.equals("特急")){
					blsxsm = "此节点办理时限为4小时！";
				}
				
				else 	if( jjcd.equals("紧急")){
					blsxsm = "此节点办理时限为8小时！";
				}
				//副总裁审阅请示、报告报批单
				else 	if(stepName.equals("obj_c8023012de500001bddf1110166022b0") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁批示请示、报告报批单
				else 	if(stepName.equals("obj_c80230342b4000011cd06b3017021ea0")){
					blsxsm = "此节点办理时限为3天！";
				}
				//董事长批示请示、报告报批单
				else 	if(stepName.equals("obj_c802306595c00001d16f1336b32a1c18") ){
					blsxsm = "此节点办理时限为4天！";
				}
				//总裁办主任接收（总裁）总裁办主任接收（董事长）召开会议
				else 	if(stepName.equals("obj_c802301d96500001c03415c0e1c01a50") || stepName.equals("obj_c802305f02a00001eaaa7fe37cb617ba") || stepName.equals("obj_c802304d60c0000111da1f00115f9bc0")  ){
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
				String sql = "update BO_DY_QSBG_BQZHB set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
