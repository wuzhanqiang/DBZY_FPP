package com.nepharm5.apps.fpp.nepgErqi.biz.zblc;

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
import com.nepharm5.apps.fpp.nepg.msg.WjpsMsg;

public class CgfwzbSpsmRtclass extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	
	public CgfwzbSpsmRtclass() {
		// TODO Auto-generated constructor stub
		setProvider("houwh");
		setDescription("流程审批中增加审批说明");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String spsm = "";
		BO ht = SDK.getBOAPI().getByProcess("BO_DY_XZBG_SBZB_P", bindid);
		if(ht!=null && !ht.isNew()){
			String id = ht.get("ID")==null?"": ht.get("ID").toString();
			String sfgkzb = ht.get("SFGKZB")==null?"": ht.get("SFGKZB").toString();
			String sfgzxm = ht.get("SFGZXM")==null?"":ht.get("SFGZXM").toString();
			double je = DBSql.getDouble("select sum(htjg) as sm from BO_DY_XZBG_SBZB_S where bindid ="+bindid, "sm");
		
			/*
			//非公开招标金额小于10W时，由工程项目部部长审批
			if(sfgkzb.equals("否") && sfgzxm.equals("否") && je<10){
				spsm = "此次招标由工程项目部部长最终审批";
			}
			//非公开招标，金额大于等于10W小于50W时，总裁审批
			if(sfgkzb.equals("否") && sfgzxm.equals("否") && je>=10 && je<50){
				spsm = "此次招标由总裁最终审批";
			}
			//非公开招标并且招标总金额大于50万由董事长审批
			if(sfgkzb.equals("否") && sfgzxm.equals("否") && je>=50){
				spsm = "此次招标由董事长最终审批";
			}
			//公开招标项目或者国债项目，招标最终由董事长审批
			if(sfgkzb.equals("是") || sfgzxm.equals("是")){
				spsm = "此次招标由董事长最终审批";
			}
			*/
			//公开招标并且是国债项目并且招标总金额≥10万由董事长审批
			if(sfgkzb.equals("是") || sfgzxm.equals("是")||je>=10){
				spsm = "此次招标由董事长最终审批";
			}
			else{
				spsm = "此次招标由总裁最终审批";
			}
				
			if(!spsm.equals("")){
				String sql = "update BO_DY_XZBG_SBZB_P set spsm = '"+spsm+"' where id = '"+id+"'";
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
		
		//紧急程度为特急时写入短信表
		String jjcd = ht.get("JJCD")==null?"":ht.get("JJCD").toString();//获取紧急程度字段的值
		String bt = ht.get("BT")==null?"":ht.get("BT").toString();//获取紧急程度字段的值
		String createUser =  uc.getUserModel().getUserName();//获取当前办理人
		String taskDefId = ctx.getTaskInstance().getActivityDefId();
		//短信内容
		String msgContext = createUser+"发起的标题为【"+bt+"】的采购服务招标流程已流转至您的BPM系统，紧急程度为"
		                    +jjcd+",请于4小时内办理完毕！";
		if(jjcd.equals("特急")){//判断当紧急程度为特急的时候发送信息
			WjpsMsg.InsertMsg(bindid, taskid, uc,msgContext,taskDefId);//调用信息写入方法
			//WjpsMsg.InsertMsg(ctx.getTaskInstance().getTarget());//调用信息写入方法
		}
		

	}

}
