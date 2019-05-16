package com.nepharm5.apps.fpp.nepgErqi.biz.YQblsxsm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class Wcsqblsxsm extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindid = ctx.getProcessInstance().getId();
		String blsxsm = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_WCSQ", bindid);
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//提交外出申请
				if( stepName.equals("obj_c7fb6fe98c3000011d2713001ab06a10")){
					blsxsm = "此节点无办理时限！";
				}
			    //副总裁审批
				else 	if(stepName.equals("obj_c7fb6feb3a700001ff32f3d061f4114d") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁审批外出申请
				else 	if(stepName.equals("obj_c7fb6febea900001248f1ae89700122c")){
					blsxsm = "此节点办理时限为3天！";
				}

				else 	if(stepName.equals("待（总裁）审批外出申请") ||stepName.equals("总裁办主任递交外出申请")  ){
					blsxsm = "此节点无办理时限！";
				}
				//董事长审批外出申请
				else 	if(stepName.equals("obj_c7fb6ff7dab00001a9ec72e087424ee0")){
					blsxsm = "此节点办理时限为4天！";
				}	
				else 	{
					blsxsm = "此节点办理时限为1天！";
				}
			}catch (Exception e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace(System.err);
			
			}
			
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_XZBG_WCSQ set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
