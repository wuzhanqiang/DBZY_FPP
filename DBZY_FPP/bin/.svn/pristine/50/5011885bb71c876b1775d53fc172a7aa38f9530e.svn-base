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

public class Yszjblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Yszjblsxsm() {
		// TODO 自动生成的构造函数存根
	}

	public Yszjblsxsm(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("预算追加申请流程,流程办理时限说明");
		setVersion("V1.0");
		
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO bo = SDK.getBOAPI().getByProcess("BO_DY_NEPG_ZJYS_P", bindid);
		if(bo!=null && !bo.isNew()){
			String id = bo.get("ID")==null?"": bo.get("ID").toString();
			String jjcd = bo.get("JJCD")==null?"": bo.get("JJCD").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			if( stepName.equals("obj_c7fcbefa726000017d8c10e9ad5218b8")){//提出申请
				blsxsm = "此节点无办理时限！";
			}
			
			else 	if(stepName.equals("obj_c7fcbff575100001ef25910014eb2000") //总经理级审批
					|| stepName.equals("obj_c7fcbff575100001ef25910014eb2000")){//财务副总裁审核
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c7fcc004f6f00001bb8138201fe5c0c0")){//总裁办主任接收
				blsxsm = "此节点无办理时限！";
			}
			else 	if(stepName.equals("obj_c7fcc0095b0000015a93d01011621468")){//总裁审批
				blsxsm = "此节点办理时限为3天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_NEPG_ZJYS_P set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
