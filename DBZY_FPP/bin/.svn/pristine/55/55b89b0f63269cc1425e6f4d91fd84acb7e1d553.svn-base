package com.nepharm5.apps.fpp.nepgErqi.biz.YQblsxsm;

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

public class Gczbblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Gczbblsxsm() {
		// TODO 自动生成的构造函数存根
		setProvider("ZZ");
		setDescription("流程办理时限说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		//int lcslid =  this.getParameter(this.PARAMETER_INSTANCE_ID).toInt();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_XZBG_GCZB_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String jjcd = ht.get("JJCD")==null?"": ht.get("JJCD").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			if( stepName.equals("obj_c7feafcfdc400001a74187f71dd068c0")){//提交工程招标报批单
				blsxsm = "此节点无办理时限！";
			}
			else if( jjcd.equals("特急")){
				blsxsm = "此节点办理时限为4小时！";
			}
			
			else 	if( jjcd.equals("紧急")){
				blsxsm = "此节点办理时限为8小时！";
			}
			else 	if(stepName.equals("obj_c7feb020779000016a43139f394810f5") ){//副总裁审阅工程招标报批单
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c7feb04bfb00000114a8138013d8183e")){//总裁批示工程招标报批单
				blsxsm = "此节点办理时限为3天！";
			}
			else 	if(stepName.equals("obj_c7feb08d8ff00001d9b1a7a8f10089f0") ){//董事长批示工程招标报批单
				blsxsm = "此节点办理时限为4天！";
			}
			else 	if(stepName.equals("obj_c7feb07e50f00001f1801150faa01b7c") //总裁办主任接收（董事长）
					|| stepName.equals("obj_c7feb03ba04000017bbc127010d012d3") //总裁办主任接收（总裁）
					|| stepName.equals("obj_c7feb05e1e0000017d6f176047991492")  ){//召开会议
				blsxsm = "此节点无办理时限！";
			}
			
			else 	{
				blsxsm = "此节点办理时限为1天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_XZBG_GCZB_P set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
