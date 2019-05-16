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

public class Ckzlblsxsm extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {

		String bindid = ctx.getProcessInstance().getId();
		String blsxsm = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_CKZL", bindid);
		if(formData!=null){
			String id = formData.get("ID")==null?"": formData.get("ID").toString();
			try {
				String stepName = ctx.getTaskInstance().getActivityDefId();
				//提交参考资料报批单
				if( stepName.equals("obj_c7fd45d42c50000142a9159016103840")){
					blsxsm = "此节点无办理时限！";
				}
			    //副总裁审阅参考资料报批单
				else 	if(stepName.equals("obj_c7fd45f039800001e14e12d011b019f5") ){
					blsxsm = "此节点办理时限为2天！";
				}
				//总裁批示参考资料报批单
				else 	if(stepName.equals("obj_c7fd46254c000001209814405b30d940")){
					blsxsm = "此节点办理时限为3天！";
				}

				//董事长批示参考资料报批单
				else 	if(stepName.equals("obj_c7fd4640e0400001ee531860141719ae")){
					blsxsm = "此节点办理时限为4天！";
				}	
				//总裁办主任接收（董事长）总裁办主任接收（总裁）召开会议
				else 	if(stepName.equals("obj_c7fd46349a600001b5cc1547de785860") || stepName.equals("obj_c7fd460bb6d00001598663b01ae014c1") || stepName.equals("obj_c7fd469a0e50000111bc17d01c271d67")  ){
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
				String sql = "update BO_DY_XZBG_CKZL set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
