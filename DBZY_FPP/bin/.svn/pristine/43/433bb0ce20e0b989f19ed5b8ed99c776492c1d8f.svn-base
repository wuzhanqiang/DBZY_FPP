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

public class Cgfwzbspsxsm extends ExecuteListener implements
		ExecuteListenerInterface {
	
	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	
	public Cgfwzbspsxsm() {
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
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_XZBG_SBZB_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String jjcd = ht.get("JJCD")==null?"": ht.get("JJCD").toString();
			String stepName = ctx.getTaskInstance().getActivityDefId();//获取节点ID
			if( stepName.equals("obj_c801874742f0000130c81057161b2180")){//提交采购（服务）招标报批单
				blsxsm = "此节点无办理时限！";
			}
			else if( jjcd.equals("特急")){
				blsxsm = "此节点办理时限为4小时！";
			}
			
			else 	if( jjcd.equals("紧急")){
				blsxsm = "此节点办理时限为8小时！";
			}
			else 	if(stepName.equals("obj_c8018756f1300001e720913f2280f2d0") ){//副总裁审阅采购（服务）招标报批单
				blsxsm = "此节点办理时限为2天！";
			}
			else 	if(stepName.equals("obj_c801875c05500001a7162210a15f1f34")){//总裁批示采购（服务）招标报批单
				blsxsm = "此节点办理时限为3天！";
			}
			else 	if(stepName.equals("obj_c8018763ef800001362a8be06cd0c630") ){//董事长批示采购（服务）招标报批单
				blsxsm = "此节点办理时限为4天！";
			}
			else 	if(stepName.equals("obj_c80187624c900001189c1af61aa01953") //总裁办主任接收（董事长）
					|| stepName.equals("obj_c8018759a3600001baffe6201f978a40") //总裁办主任接收（总裁）
					|| stepName.equals("obj_c8018760b3d00001fdc91f7519ab1be3")  ){//召开会议
				blsxsm = "此节点无办理时限！";
			}
			
			else 	{
				blsxsm = "此节点办理时限为1天！";
			}
		
			if(!blsxsm.equals("")){
				String sql = "update BO_XZBG_SBZB_P set blsxsm = '"+blsxsm+"' where id = '"+id+"'";
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
