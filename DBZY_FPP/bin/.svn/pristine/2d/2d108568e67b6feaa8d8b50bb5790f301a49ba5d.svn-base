package com.nepharm5.apps.fpp.nepg.biz.zbgl;

import java.sql.Connection;
import java.sql.SQLException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.org.cache.UserCache;
import com.actionsoft.bpms.org.model.UserModel;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

/**
 * 工程招标批示流程，公司副总审阅节点或者总经办秘书分发批示节点，点击办理时，向招标汇总表BO_XZBG_ZBHZ和文件批示汇总表BO_XZBG_WJPSHZ中添加记录
 * @author Administrator
 *
 */
public class GCZBBiz extends ExecuteListener implements
		ExecuteListenerInterface {

	public GCZBBiz() {
		setVersion("1.0.0");
		setDescription("点击办理时，向招标汇总表BO_DY_XZBG_ZBHZ和文件批示汇总表BO_DY_XZBG_WJPSHZ中添加记录");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			if(isAgreeOrDispatch(ctx)){
				conn = DBSql.open();
				conn.setAutoCommit(false);
				
				String bindId = ctx.getProcessInstance().getId();
				String uid = ctx.getUserContext().getUID();
				BO record = SDK.getBOAPI().getByProcess("BO_DY_XZBG_GCZB_P", bindId);
				
				addZBHZInfo(uid, record, bindId);
				addWJPSHZInfo(uid, record, bindId);
				
				conn.commit();
			}
		} catch (Exception e) {
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
	/**
	 * 添加记录到招标汇总信息表中
	 * @param record
	 * @param bindId
	 * @throws AWSSDKException
	 */
	private void addZBHZInfo(String uid, BO record, String bindId){
		BO recordData = new BO();
		String receivers = record.get("FFFW").toString();//分发范围
		
		if(receivers.trim().length() > 0){
			recordData.set("FFFW", receivers);
			recordData.set("FS", receivers.split(" ").length);//根据分发范围中选择的人员，统计分发份数
		}
		
		recordData.set("BOID", record.get("ID"));
		recordData.set("LCUUID", record.get("LCUUID"));//流程的UUID
		recordData.set("FQRBLSJ", record.get("FQRBLSJ"));//发起人办理时间
		recordData.set("GSFZPSSJ", record.get("GSFZPSSJ"));//公司副总在审核菜单中选择[同意]时的办理时间
		recordData.set("ZJLPSSJ", record.get("ZJLPSSJ"));//总经理在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("DSZPSSJ", record.get("DSZPSSJ"));//董事长在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("FQBM", record.get("ZBFQR"));//招标发起人
		recordData.set("FBBM", record.get("FBBM"));//发包部门
		recordData.set("ZBGC", record.get("ZBGC"));//招标工程
		recordData.set("PSLX", record.get("PSLX"));//批示类型
		recordData.set("FQR", record.get("SQRXM"));//申请人姓名
		
		SDK.getBOAPI().create("BO_DY_XZBG_ZBHZ", recordData, bindId, uid);
	}
	
	/**
	 * 添加记录到文件批示汇总表中
	 * @param record
	 * @param bindId
	 * @throws AWSSDKException
	 */
	private void addWJPSHZInfo(String uid, BO record, String bindId) {
		BO recordData = new BO();
		String receivers = record.get("FFFW").toString();//分发范围
		
		if(receivers.trim().length() > 0){
			recordData.set("FFFW", receivers);
			recordData.set("FS", receivers.split(" ").length);//根据分发范围中选择的人员，统计分发份数
		}
		
		recordData.set("BOID", record.get("ID"));
		recordData.set("LCUUID", record.get("LCUUID"));//流程的UUID
		recordData.set("FQRBLSJ", record.get("FQRBLSJ"));//发起人办理时间
		recordData.set("GSFZPSSJ", record.get("GSFZPSSJ"));//公司副总在审核菜单中选择[同意]时的办理时间
		recordData.set("ZJLPSSJ", record.get("ZJLPSSJ"));//总经理在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("DSZPSSJ", record.get("DSZPSSJ"));//董事长在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("PSLX", record.get("PSLX"));//批示类型
		recordData.set("FQR", record.get("SQRXM"));//申请人姓名
		recordData.set("SBBM", record.get("ZBFQR"));//招标发起人
		recordData.set("NO", record.get("NO"));
		String manager = record.get("ZGFZ").toString();//提交人的主管副总
		String managerName = "";
		if(!"未找到".equals(manager.trim())){
			UserModel userModel = (UserModel) UserCache.getModel(manager);
			managerName = userModel.getUserName();
		}
		recordData.set("ZGLD", managerName);//主管领导
		recordData.set("TM", record.get("ZBGC"));//招标工程
		SDK.getBOAPI().create("BO_DY_XZBG_WJPSHZ", recordData, bindId, uid);
	}
	
	/**
	 * 判断审核菜单选择的是否是[同意]、[不同意][分发批示]
	 * @return
	 */
	private boolean isAgreeOrDispatch(ProcessExecutionContext ctx){
		String bindId = ctx.getProcessInstance().getId();
		String taskId = ctx.getTaskInstance().getId();
		
		//判读审核菜单选择的是否是[同意]
		boolean isAgree = SDK.getTaskAPI().isChoiceActionMenu(taskId, "同意");
		
		//判断审核菜单选择的是否是[分发批示]
		boolean isDispatch = SDK.getTaskAPI().isChoiceActionMenu(taskId, "分发批示");
		
		//判断审核菜单选择的是否是[不同意]
		boolean no = SDK.getTaskAPI().isChoiceActionMenu(taskId, "不同意");
		
		return (isAgree || isDispatch || no);
	}

}
