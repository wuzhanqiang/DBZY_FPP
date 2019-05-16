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

public class QsbgBlsxsmStepRTClassA extends ExecuteListener {

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {

		String bindid = ctx.getProcessInstance().getId();
		//String taskId=SDK.getProcessAPI().getInstanceById(bindid).getStartTaskInstId();
		String blsxsm = "";
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_QSBG", bindid);
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			String jjcd = formData.get("JJCD")==null?"": formData.get("JJCD").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//总经理级审批请示、报告报批单
				if( stepName.equals("obj_c7fb6bfa3dc00001dcfd1bd016656a80")){
					blsxsm = "此节点无办理时限！";
				}
				else if( jjcd.equals("特急")){
					blsxsm = "此节点办理时限为4小时！";
				}
				
				else 	if( jjcd.equals("紧急")){
					blsxsm = "此节点办理时限为8小时！";
				}
				//副总裁审阅请示、报告报批单
				else 	if(stepName.equals("obj_c7fb6bfe1c50000140d912e0aac41bfa") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁批示请示、报告报批单
				else 	if(stepName.equals("obj_c7fb6c075d900001bed0112a186c1136")){
					blsxsm = "此节点办理时限为3天！";
				}
				//董事长批示请示、报告报批单
				else 	if(stepName.equals("obj_c7fb6c12ece00001894516c071991dfd") ){
					blsxsm = "此节点办理时限为4天！";
				}
				//总裁办主任接收（总裁）总裁办主任接收（董事长）召开会议
				else 	if(stepName.equals("obj_c7fb6c1283000001a49f1173107019f1") || stepName.equals("obj_c7fb6c06ef300001b815530059101f60") || stepName.equals("obj_c7fb6c07dcc0000179231ab21e841c42")  ){
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
				String sql = "update BO_DY_XZBG_QSBG set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
