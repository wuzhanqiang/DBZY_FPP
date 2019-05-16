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

public class Gdzcczblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Gdzcczblsxsm() {
		// TODO 自动生成的构造函数存根
	}

	public Gdzcczblsxsm(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("固定资产出租流程,流程办理时限说明");
		setVersion("V1.0");
		
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		//int lcslid =  this.getParameter(this.PARAMETER_INSTANCE_ID).toInt();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_NEPG_GDZCCZ_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String jjcd = ht.get("JJCD")==null?"": ht.get("JJCD").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			
			
			if( stepName.equals("obj_c7fdb7fc03500001477d2e7078b012a0")){//申请固定资产出租
				blsxsm = "此节点无办理时限！";
			}
			else 	if(stepName.equals("obj_c7fdb82ec5600001d93519ac140b18aa")){//主管副总裁审批
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c7fdb8354020000168ba1f1015705e20")||stepName.equals("obj_c7fdb83ff2400001f4f014361ca07b40")){//总裁办公室主任接收（总裁） || 总裁办公室主任接收（董事长）
				blsxsm = "此节点无办理时限！";
			}
			else 	if(stepName.equals("obj_c7fdb83b56d00001a45c10a11bd88760")){//总裁审批
				blsxsm = "此节点办理时限为3天！";
			}
			else 	if(stepName.equals("obj_c7fdb84c14f00001c055f920b055fa00")){//董事长审批
				blsxsm = "此节点办理时限为4天！";
			}
		else 	{
				blsxsm = "此节点办理时限为1天！";
			}
			
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_NEPG_GDZCCZ_P set blsxsm = '"+blsxsm+"' where id = "+id+"'";
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
