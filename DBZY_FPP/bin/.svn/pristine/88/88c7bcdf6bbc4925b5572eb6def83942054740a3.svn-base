package com.nepharm5.apps.fpp.nepg.msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;


public class WjpsMsg {

	public WjpsMsg() {
	}

	public static void InsertMsg(String bindid,String taskid,UserContext uc,String msgContext,String taskDefId){
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Vector v = new Vector();//用于保存短信需要发送的短信息
		List<BO> l = new ArrayList<BO>();
		String createUser =  uc.getUserModel().getUserName();//获取当前办理人
		String createUserid =  uc.getUserModel().getUID();//获取当前办理人ID
		String nextUser="";//获取下个节点办理人
		//当下一节点办理人为多个时，会同时返回多个帐号
		nextUser = SDK.getTaskAPI().getParticipantsOfPotential(createUserid, bindid, taskid, taskDefId);
//		try {
//			//当下一节点办理人为多个时，会同时返回多个帐号
//			nextUser = WorkflowTaskInstanceAPI.getInstance().getNextParticipants(uc.getUID().toString(), bindid, taskid);
//		} catch (AWSSDKException e) {
//			e.printStackTrace(System.err);
//		}

		String [] users = nextUser.split(" ");
		
		for (int i = 0; i < users.length; i++) {
			String userId = users[i].split("<")[0];
			//获取手机号码
			String sjhm =  DBSql.getString("select mobile from orguser where userid = '"+userId+"'", "mobile");
			String fshm =  DBSql.getString("select mobile from orguser where userid = '"+createUserid+"'", "mobile");
			//创建短信
			BO msg = new BO();
			msg.set("FSR", createUser);//发送人
			msg.set("FSSJ", format.format(new Date()));//发送时间
			msg.set("JSR", users[i]);//接收人
			msg.set("JSRDH", sjhm==null?"":sjhm);//接收人电话
			msg.set("DXNR", msgContext);//短信内容
			msg.set("SFFS", 0);//标识状态
			msg.set("FSRDH",fshm==null?"":fshm );//发送人电话
			msg.set("DXLX", "BPM流程");// 短信类型
			l.add(msg);
			
		}
		// 数据写入短信表
		//int [] count = SDK.getBOAPI().create("BO_JKJC_DXPD", l, bindid, createUserid);
		
//		for (int i = 0; i < users.length; i++) {
//			String userId = users[i].split("<")[0];
//			//获取手机号码
//			String sjhm =  DBSql.getString("select mobile from orguser where userid = '"+userId+"'", "mobile");
//			String fshm =  DBSql.getString("select mobile from orguser where userid = '"+createUserid+"'", "mobile");
//			//创建短信
//			Hashtable msg = new Hashtable();
//			msg.put("FSR", createUser);//发送人
//			msg.put("FSSJ", format.format(new Date()));//发送时间
//			msg.put("JSR", users[i]);//接收人
//			msg.put("JSRDH", sjhm==null?"":sjhm);//接收人电话
//			msg.put("DXNR", msgContext);//短信内容
//			msg.put("SFFS", 0);//标识状态
//			msg.put("FSRDH",fshm==null?"":fshm );//发送人电话
//			msg.put("DXLX", "BPM流程");// 短信类型
//			v.add(msg);
//		}
//		try {
//			// 数据写入短信表
//			int [] count = BOInstanceAPI.getInstance().createBOData("BO_JKJC_DXPD", v, uc.getUID());
//		} catch (AWSSDKException e) {
//			e.printStackTrace(System.err);
//		}
	}

}
