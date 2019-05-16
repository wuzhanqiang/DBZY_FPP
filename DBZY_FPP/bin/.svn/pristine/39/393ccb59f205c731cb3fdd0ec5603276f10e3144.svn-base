package com.nepharm5.apps.fpp.dyzy.biz.Syybgff;

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

public class YySyybgffPBlsxsmRTClassA extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public YySyybgffPBlsxsmRTClassA() {
		// TODO 自动生成的构造函数存根
	}

	public YySyybgffPBlsxsmRTClassA(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("水检验报告发放主流程,流程办理时限说明");
		setVersion("V1.0");
		
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO bo = SDK.getBOAPI().getByProcess("BO_DY_DYZY_SJYZLC", bindid);
		if(bo!=null && !bo.isNew()){
			String id = bo.get("ID")==null?"": bo.get("ID").toString();
			String jysx = bo.get("JYSX")==null?"": bo.get("JYSX").toString();
			//String stepName =WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);//获取节点名称
			String stepName = ctx.getTaskInstance().getActivityDefId();//节点定义ID
			if(stepName.equals("obj_c7feacfbf37000015c6e1d001d009780")){//提交计划
				blsxsm = "请在本月25日17:00前办理！";
			}	
			else 	if(stepName.equals("obj_c7fead02b5600001ff731390d2dad9e0")){//部门经理审核
				blsxsm = "此节点办理时限为3天！";
			}
			else if(stepName.equals("obj_c7fead0c5e50000189141f7012502450")){//水检验专员接收计划并汇总
				blsxsm = "请在"+jysx+"前办理！";
			}						
			else 	if(stepName.equals("obj_c7fead1532800001452319a0bf931cd0")){//水检验督导审核
				blsxsm = "此节点办理时限为3天！";
			}				
			else 	{
				blsxsm = "此节点办理时限为3天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_DYZY_SJYZLC set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
