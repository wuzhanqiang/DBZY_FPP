package com.nepharm5.apps.fpp.nepg.biz.xzbg;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.org.cache.UserCache;
import com.actionsoft.bpms.org.model.UserModel;
import com.actionsoft.sdk.local.SDK;

public class CKZLBiz extends InterruptListener implements InterruptListenerInterface{

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		try {
			if(isAgreeOrDispatch(ctx)){
				String bindid = ctx.getProcessInstance().getId();
				BO formData = SDK.getBOAPI().getByProcess("BO_DY_XZBG_CKZL", bindid);
				addWJPSHZInfo(formData, bindid,ctx);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
		return true;
	}
	private void addWJPSHZInfo(BO formData, String bindid,ProcessExecutionContext ctx){
		String uid = ctx.getUserContext().getUID();
		String receivers = formData.get("FFFW").toString();
		BO recordData = new BO();
		if(receivers.trim().length() > 0){
			recordData.set("FFFW", receivers);
			recordData.set("FS", receivers.split(" ").length);//根据分发范围中选择的人员，统计分发份数
		}
		recordData.set("BOID", formData.get("ID"));
		recordData.set("LCUUID", formData.get("LCUUID"));//流程的UUID
		recordData.set("FQRBLSJ", formData.get("FQRBLSJ"));//发起人办理时间
		recordData.set("GSFZPSSJ", formData.get("GSFZPSSJ"));//公司副总在审核菜单中选择[同意]时的办理时间
		recordData.set("ZJLPSSJ", formData.get("ZJLPSSJ"));//总经理在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("DSZPSSJ", formData.get("DSZPSSJ"));//董事长在审核菜单中选择[秘书办理]时的办理时间
		recordData.set("SBBM", formData.get("TJBM"));//提交部门
		recordData.set("PSLX", formData.get("PSLX"));//批示类型
		recordData.set("FQR", formData.get("TJR"));//提交人
		recordData.set("NO", formData.get("NO"));
		String manager = formData.get("ZGFZ").toString();//提交人的主管副总
		String managerName = "";
		if(!"未找到".equals(manager.trim())){
			UserModel userModel = (UserModel) UserCache.getModel(manager);
			if(userModel!=null) managerName = userModel.getUserName();
		}
		recordData.set("ZGLD", managerName);//主管领导
		recordData.set("TM", formData.get("BT"));//标题
		
		SDK.getBOAPI().create("BO_DY_XZBG_WJPSHZ", recordData, bindid, uid);
	}
	
	/**
	 * 判断审核菜单选择的是否是[同意]、[不同意][分发批示]
	 * @return
	 */
	private boolean isAgreeOrDispatch(ProcessExecutionContext ctx){
		//String bindid = ctx.getProcessInstance().getId();
		//String taskId=SDK.getProcessAPI().getInstanceById(bindid).getStartTaskInstId();
		//判读审核菜单选择的是否是[同意]
		boolean isAgree = ctx.isChoiceActionMenu("同意");
		
		//判断审核菜单选择的是否是[分发批示]
		boolean isDispatch = ctx.isChoiceActionMenu("分发批示");
				
		//判断审核菜单选择的是否是[不同意]
		boolean no = ctx.isChoiceActionMenu("不同意");
		
		return (isAgree || isDispatch || no);
	}



}
