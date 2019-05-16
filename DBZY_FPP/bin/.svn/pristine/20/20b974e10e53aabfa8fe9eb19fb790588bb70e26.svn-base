package com.nepharm5.apps.fpp.gxgs.blsxsm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class GxzljcxgBlsxsmFormBeforeLoad extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	Connection conn;
	Statement stmt;
	
	public GxzljcxgBlsxsmFormBeforeLoad() {
		// TODO Auto-generated constructor stub
		setProvider("wz");
		setDescription("供销质量基础数据修改流程,流程办理时限说明");
		setVersion("V1.0");
	}
	
	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindid=ctx.getProcessInstance().getId();
		//取得节点名
		String stepname=null;	
		stepname = ctx.getTaskInstance().getActivityDefId();
	
		//取得办理时限说明文字
//		String blsxsm=null;
//		if(stepname.equals("部门经理审核")){
//			blsxsm="此节点办理时限为1天";
//		}else if(stepname.equals("主管总监审核")||stepname.equals("质量管理部经理审批")||stepname.equals("质量管理员修改")){
//			blsxsm="此节点办理时限为2天";		
//		}else{
//			blsxsm="此节点无办理时限";
//		}
		String blsxsm=null;
		//部门经理审核
		if(stepname.equals("obj_c7fe946d9c50000136f9137a1c1060a0")){//部门经理审核
			blsxsm="此节点办理时限为1天";
		}else if(stepname.equals("obj_c7fe94857c6000012856608c1deb2d90")//主管总监审核
				||stepname.equals("obj_c7fe94d885000001eb6bc610b1301c1e")//质量管理部经理审批
				||stepname.equals("obj_c7fe94f65b800001f0463b0020ed1a90")){//质量管理员修改
			blsxsm="此节点办理时限为2天";		
		}else{
			blsxsm="此节点无办理时限";
		}
		//拼sql
		String sql="update BO_DY_GXGS_ZLJCSJXG_P set BLSXSM='"+blsxsm+"' where bindid="+bindid;
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
