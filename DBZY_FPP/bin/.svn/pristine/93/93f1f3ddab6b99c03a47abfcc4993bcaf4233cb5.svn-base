package com.nepharm5.apps.fpp.nepgErqi.biz.Erqiblsxsm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class Zgswtzbblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Zgswtzbblsxsm() {
		// TODO 自动生成的构造函数存根
		setProvider("ZZ");
		setDescription("委托招标申请(子公司)流程,委托招标申请（总部）流程,流程办理时限说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		//int lcslid =  this.getParameter(this.PARAMETER_INSTANCE_ID).toInt();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_WTZBSQD_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String jjcd = ht.get("JJCD")==null?"": ht.get("JJCD").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			if( stepName.equals("obj_c7ff9954228000019b6c7986bfc01d54")){//提出委托招标申请
				blsxsm = "此节点无办理时限！";
			}
			
			else 	if(stepName.equals("obj_c7ff995ae19000018eb215ac1a451261") //总经理级审批
					|| stepName.equals("obj_c7ff9957782000018be8c7c0c478e140")){//部门总经理审核
				blsxsm = "此节点办理时限为2天！";
			}
			else 	{
				blsxsm = "此节点办理时限为1天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_WTZBSQD_P set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
