package com.nepharm5.apps.fpp.nepgErqi.biz.Erqiblsxsm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class Htfwshblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Htfwshblsxsm() {
		// TODO 自动生成的构造函数存根
		setProvider("ZZ");
		setDescription("合同法务审核流程,合同法务审核流程（子公司）,流程办理时限说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		//int lcslid =  this.getParameter(this.PARAMETER_INSTANCE_ID).toInt();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_NEPG_HTFWSH", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			if( stepName.equals("obj_c7ff9f09ab800001a290a18e1ac0105b")){//提交合同法务审批
				blsxsm = "此节点无办理时限！";
			}
			else 	if(stepName.equals("obj_c7ff9f17e8200001de8c15e063f01569")//分管副总裁审核合同
					||stepName.equals("obj_c7ff9f1c9b00000116758df0b568ed80")){//总经理审核合同
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c7ff9f1933c00001c7f61d20b1807360")){//总裁办公室主任接收（总裁）
				blsxsm = "此节点无办理时限！";
			}
			else 	if(stepName.equals("obj_c7ff9f1c9b00000116758df0b568ed80")){//总裁审核合同
				blsxsm = "此节点办理时限为3天！";
			}
			else 	{
				blsxsm = "此节点办理时限为1天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_NEPG_HTFWSH set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
				conn = DBSql.open();
				try {
					stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace(System.err);
				}finally{
					DBSql.close(conn, stmt, null);
				}
				
			}
		}
	}

}
