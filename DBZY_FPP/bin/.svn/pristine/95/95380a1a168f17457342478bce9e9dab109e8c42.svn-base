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

public class Gwcbblsxsm extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {

		String bindid = ctx.getProcessInstance().getId();
		String blsxsm = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_GWCB", bindid);
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//公文上报
				if( stepName.equals("obj_c7fd052a09d0000194d057a2a950103e")){
					blsxsm = "此节点无办理时限！";
				}
			    //副总裁审阅公文
				else 	if(stepName.equals("obj_c7fd052c5ea000018d9e15a1114beb10") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁批示公文
				else 	if(stepName.equals("obj_c7fd052d9fc00001ee3021003bda1dd7")){
					blsxsm = "此节点办理时限为3天！";
				}

				//董事长批示公文
				else 	if(stepName.equals("obj_c7fd052fd510000153b11365de8b1265")){
					blsxsm = "此节点办理时限为4天！";
				}	
				//总裁办主任接收（董事长）
				else 	if(stepName.equals("obj_c7fd052e09c0000162ca16b01ba01690")   ){
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
				String sql = "update BO_DY_XZBG_GWCB set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
