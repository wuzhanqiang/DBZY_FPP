package com.nepharm5.apps.fpp.gxgs.biz.tzlc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class GxTzlcFsrwTAfter extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	public GxTzlcFsrwTAfter() {
		setVersion("V1.0");
		setDescription("供销通知流程.点击办理时，根据主表中选择的反馈人，向子表中添加相应的记录。");
		setProvider("cici");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		String uid = ctx.getUserContext().getUID();
		
		//判断审核菜单选择是否是[提交]
		boolean tj = SDK.getTaskAPI().isChoiceActionMenu(taskid, "提交");
		boolean fstz = SDK.getTaskAPI().isChoiceActionMenu(taskid, "发送通知");
		BO table = SDK.getBOAPI().getByProcess("BO_DY_GXGS_TZLC_P", bindid);

		if((tj == true) || (fstz== true)){//用户选择协调协助人，向子表中插入数据
			
			try {
				String userAccount = uc.getUID();
				
				conn = DBSql.open();
				conn.setAutoCommit(false);
				
				List<String> newParticipants = getNewParticipants(bindid);
				BO recordData = null;
				for(String participant : newParticipants){
					recordData = new BO();
					recordData.set("FKR", participant);//反馈人
					SDK.getBOAPI().create("BO_DY_GXGS_TZLC_S", recordData, bindid, uid);
					
				}
				
				removeRedundantParticipants(bindid, conn);
				
				conn.commit();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				try {
					if(conn != null)
						conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace(System.err);
				}
				e.printStackTrace(System.err);
			} finally {
				DBSql.close(conn, null, null);
			}
			
		}
	}
	
	/**
	 * 获取在子表中没有记录的反馈人
	 * @param bindId
	 * @return
	 */
	private List<String> getNewParticipants(String bindId){
		List<String> result = new ArrayList<String>();
		BO record = SDK.getBOAPI().getByProcess("BO_DY_GXGS_TZLC_P", bindId);
		if(record !=null && !record.isNew()){
			String participants = record.get("FFFW").toString();		//年度计划主表中选择的干系人
			if(participants.trim().length() > 0){
				//把年度计划主表中选择的干系人放到集合中
				String[] participantArray = participants.split(" ");
				for(String participant : participantArray){
					result.add(participant);
				}
			}
			
			List<BO> records = SDK.getBOAPI().query("BO_DY_GXGS_TZLC_S")
					.bindId(bindId).list();
			if(records != null){
				//年度计划主表中选择的干系人和子表中选择的反馈人进行比较，
				//删除子表中已经存在的反馈人，剩下的人员就是在子表中不存在的年度计划
				for(int i = 0, count = records.size(); i < count; i++){
					result.remove(records.get(i).get("FKR"));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 删除子表（BO_GXGS_TZLC_S）中多余的协办人
	 * @throws SQLException 
	 * @throws AWSSDKException 
	 */
	private void removeRedundantParticipants(String bindId, Connection conn) throws SQLException{
		BO record = SDK.getBOAPI().getByProcess("BO_DY_GXGS_TZLC_P", bindId);
		String participants = record.get("FFFW").toString();		//年度计划主表中选择的反馈
		
		//生成需要删除的年度计划子表中的多余的反馈人的where查询条件
		StringBuilder conditions = new StringBuilder("select * from BO_GXGS_TZLC_S where 1=1 and ( 1=2 ");
		List<BO> records = SDK.getBOAPI().query("BO_DY_GXGS_TZLC_S")
				.bindId(bindId).list();
		if(records != null){
			String participant = null;
			for(int i = 0, count = records.size(); i < count; i++){
				participant = records.get(i).get("FKR").toString();
				if(participants.indexOf(participant) == -1){
					conditions.append(" OR FKR = '").append(participant).append("'");
				}
			}
		}
		conditions.append(")");
		
		conn = DBSql.open();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(conditions.toString());
		
		while(rs.next()){
			SDK.getBOAPI().remove("BO_DY_GXGS_TZLC_S", rs.getString("ID"));
		}
		
	}

}
