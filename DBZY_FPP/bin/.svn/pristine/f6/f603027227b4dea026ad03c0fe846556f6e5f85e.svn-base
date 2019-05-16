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

public class Wzzbblsxsm extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public Wzzbblsxsm() {
		// TODO 自动生成的构造函数存根
		setProvider("ZZ");
		setDescription("流程办理时限说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String blsxsm = "";
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_XZBG_WZZB_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String jjcd = ht.get("JJCD")==null?"": ht.get("JJCD").toString();
			String stepName = ctx.getTaskInstance().getActivityDefId();
			if( stepName.equals("obj_c7fff6fa0cf00001d4f117801607d150")){//提交物资招标报批单
				blsxsm = "此节点无办理时限！";
			}
			else if( jjcd.equals("特急")){
				blsxsm = "此节点办理时限为4小时！";
			}
			
			else 	if( jjcd.equals("紧急")){
				blsxsm = "此节点办理时限为8小时！";
			}
			else 	if(stepName.equals("obj_c7fff71181b000015a4d36501e76146f") ){//副总裁审阅物资招标报批单
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c7fff71f7660000163ee682bcff01700")){//总裁批示物资招标报批单
				blsxsm = "此节点办理时限为3天！";
			}
			else 	if(stepName.equals("obj_c7fff734c0e000013be21df69ecaca00") ){//董事长批示物资招标报批单
				blsxsm = "此节点办理时限为4天！";
			}
			else 	if(stepName.equals("obj_c7fff72d97e00001bdd3a9302ee01f4d") //总裁办主任接收（董事长）
					|| stepName.equals("obj_c7fff7192fe000013d21122e11009950") //总裁办主任接收（总裁）
					|| stepName.equals("obj_c7fff7245920000162591eb611006120")  ){//召开会议
				blsxsm = "此节点无办理时限！";
			}
			
			else 	{
				blsxsm = "此节点办理时限为1天！";
			}
		
		
		
			if(!blsxsm.equals("")){
				String sql = "update BO_DY_XZBG_WZZB_P set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
