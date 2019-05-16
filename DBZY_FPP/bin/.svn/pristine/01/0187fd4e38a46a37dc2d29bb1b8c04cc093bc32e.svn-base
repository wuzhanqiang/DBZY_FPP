package com.nepharm5.apps.fpp.gxgs.blsxsm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;

public class GxbhgbsBlsxsmFormBeforeLoad extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	Connection conn;
	Statement stmt;
	public GxbhgbsBlsxsmFormBeforeLoad() {
		setProvider("wz");
		setDescription("供销不合格药品报损流程,流程办理时限说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid=ctx.getProcessInstance().getId();
		//取得节点名
		String stepname=null;		
		String currentUser = ctx.getUserContext().getUserName();//+">";//当前用户的账号和姓名
		stepname=ctx.getTaskInstance().getActivityDefId();
		//WorkflowInstanceAPI.getInstance().getCurrentStepName(bindid);
		//取得办理时限说明文字
		String blsxsm=null;
		if(stepname.equals("obj_c7fea23858200001466f8c001cf0196b")){//保管员确认
			blsxsm="此节点办理时限为1.5天";
		}else if(stepname.equals("obj_c7fea21f59000001f3ee1610189e8b70")//部门经理审核
				||stepname.equals("obj_c7fea25af4700001d2bfc77032001398")//质量/仓储经理审核
				||stepname.equals("obj_c7fea3a559200001f9b89900149b4280")//营销支持总监审核
				||stepname.equals("obj_c7fea5e4e78000016d121920199110aa")//运营部副经理审核
				||stepname.equals("obj_c7fea5f69e00000176233e4b16507e20")//运营部经理审核
				||stepname.equals("obj_c7fea6274ba0000138db12e011104500")//财务部副经理审核
				||stepname.equals("obj_c7fea6ec25800001a3f71c90cc60d8d0")){//报损专员处理
			blsxsm="此节点办理时限为1天";	
		}else if(stepname.equals("obj_c7fea6bee2100001c4c11a90129e67e0")//总经理审批（1）
				||stepname.equals("obj_c7fea3b359000001106a1f631eb01941")//财务总监审核
				||stepname.equals("obj_c7fea72578400001462e1fd03b00da60")){//总经理审批（2）
			blsxsm="此节点办理时限为2天";	
		}else if(stepname.equals("obj_c7fea6ec25800001a3f71c90cc60d8d0")//报损专员处理/运营部副经理反馈
				&&(currentUser.equals("牟林"))){
			blsxsm="此节点办理时限为30天";	
		}else if(stepname.equals("obj_c7fea6ec25800001a3f71c90cc60d8d0")//报损专员处理/运营部副经理反馈
				&&(currentUser.equals("赵宁"))){
			blsxsm="此节点办理时限为1天";	
		}else{
			blsxsm="此节点无办理时限";
		}
		//拼sql
		String sql="update BO_DY_GXGS_BHGBS_P set BLSXSM='"+blsxsm+"' where bindid="+bindid;
		//执行update,更新主表一个字段值
		conn=DBSql.open();
		try {
			stmt=conn.createStatement();
			int result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}finally{
			DBSql.close(conn, stmt, null);
		}
	}

}
