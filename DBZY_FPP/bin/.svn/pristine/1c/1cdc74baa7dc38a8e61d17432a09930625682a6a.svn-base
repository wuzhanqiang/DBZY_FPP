package com.nepharm5.apps.fpp.nepgErqi.biz.yszj;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.itextpdf.text.log.SysoCounter;
import com.nepharm5.apps.fpp.nepg.msg.WjpsMsg;

public class YszjJsljcsRtclass extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "计算部门累加次数,预算追加申请审批说明";
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		//获取操作的BO记录ID
		String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		System.out.println(boId);
		List<BO> list = SDK.getBOAPI().query("BO_DY_NEPG_ZJYS_P").bindId(bindid).list();
		BO bo = new BO();
		if(list.size() > 0) {
			bo = list.get(0);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		int count=0;
		System.out.println(bo.isNew());
		if(bo!=null && !bo.isNew()){
			String bmqlj =bo.get("SQRBMID")==null?"":bo.get("SQRBMID").toString(); 
			String id = bo.get("ID")==null?"":bo.get("ID").toString(); 
			String sqrq = bo.get("SQRQ")==null?"":bo.get("SQRQ").toString(); 
			try {
				sqrq = format.format(format.parse(sqrq));
			} catch (Exception e1) {
				e1.printStackTrace(System.err);
			}
			//获取总记录数
			if(!bmqlj.equals("")){
				String [] bmids = bmqlj.split("/");
				String bm = bmids[bmids.length-1];
				System.out.println(bm);
				String sql = "select count(*) as cnt from BO_DY_NEPG_ZJYS_P where sqrbmid like '%"
					         +bm+"%' and to_char(sqrq,'yyyy-MM')='"+sqrq+"' and isend =1";
				System.out.println(sql);
				count = DBSql.getInt(sql, "cnt")+1;
			}
			
			//更新次数
			String updatesql = "update BO_DY_NEPG_ZJYS_P set ljzjbs='"+count+"' where id='"+id+"'";
			try {
				conn = DBSql.open();
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(updatesql);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}finally{
				DBSql.close(conn, stmt, null);
			}
			/**
			 * zhangjh增加审批说明 start
			 */
			String spsm = null;
			String sql;
			 float ZJJEHJ = Float.valueOf(bo.get("ZJJEHJ")==null?"":bo.get("ZJJEHJ").toString());
			 if(ZJJEHJ < 2000 && count <= 3){
					spsm="财务部总经理";
			 }else if(ZJJEHJ < 5000 && count <= 3){
					spsm="财务总监";
			 }else{
					spsm="总裁";
			 }
			 
			 if(spsm!=null){
		    	   spsm = "此流程最终由"+spsm+"审批";
		    	   sql ="update BO_DY_NEPG_ZJYS_P set spsm = '"+spsm+"' where bindid = '"+bindid+"'";
			       DBSql.update(sql);   
		      }
			 
			 spsm = null;
			 sql = null;
			 /**
				 * zhangjh增加审批说明 end
				 */
			
		}
		
		
		
		//紧急程度为特急时，写入短信平台表
		String jjcd = bo.get("JJCD")==null?"":bo.get("JJCD").toString();//获取紧急程度字段的值
		String bt = bo.get("BT")==null?"":bo.get("BT").toString();//获取紧急程度字段的值
		String createUser =  ctx.getUserContext().getUserName();//获取当前办理人
		String taskDefId = ctx.getTaskInstance().getActivityDefId();
		//短信内容
		String msgContext = createUser+"发起的标题为【"+bt+"】的物资招标流程已流转至您的BPM系统，紧急程度为"
		                    +jjcd+",请于4小时内办理完毕！";
		if(jjcd.equals("特急")){//判断当紧急程度为特急的时候发送信息
			//WjpsMsg.InsertMsg(bindid, taskid, uc,msgContext);//调用信息写入方法
			WjpsMsg.InsertMsg(bindid, taskid, ctx.getUserContext(),msgContext,taskDefId);//调用信息写入方法
			
		}
	}

	

}
