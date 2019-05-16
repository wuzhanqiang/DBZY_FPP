package com.nepharm.apps.fpp2.biz.yyb.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class YlyscjhbgTaskAfterCompleteStep4 extends ExecuteListener implements
		ExecuteListenerInterface {

	public YlyscjhbgTaskAfterCompleteStep4() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getDescription() {
		return "生产计划变更，修改变更产量";
	}
	
	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		//判断审核动作
		boolean f = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "同意");
		if(f){
			
			//变更表bindid
			String bindId = ctx.getProcessInstance().getId();
			//获取变更表主表
			BO bgBo_M = SDK.getBOAPI().getByProcess("BO_DY_SCJH_YLYSCJHBG_M", bindId);
			//变更表子表
			List<BO> bgBoList = SDK.getBOAPI().query("BO_DY_SCJH_YLYSCJHBG_Z").bindId(bindId).list();
			//原计划bindid
			String yBindId = bgBo_M.getString("YBINDID");
			//原计划子表
			List<BO> jhBoList = SDK.getBOAPI().query("BO_DY_SCJH_YLYSCJH_Z").bindId(yBindId).list();
			
			for(BO bgBo:bgBoList){
				//原计划子表数据id
				String yId = bgBo.getString("YID");
				//新插入计划数据
				if("0".equals(yId)){
					BO newBo = new BO();
					newBo.set("GSMC", bgBo.getString("GSMC"));
					newBo.set("GSBM", bgBo.getString("GSBM"));
					newBo.set("CPBM", bgBo.getString("CPBM"));
					newBo.set("CPMC", bgBo.getString("CPMC"));
					newBo.set("CPGG", bgBo.getString("CPGG"));
					newBo.set("CPDW", bgBo.getString("CPDW"));
					newBo.set("JHCL", bgBo.getString("JHCL"));
					newBo.set("QRCL", bgBo.getString("QRCL"));
					newBo.set("JHKGSJ", bgBo.getString("JHKGSJ"));
					newBo.set("JHWGSJ", bgBo.getString("JHWGSJ"));
					newBo.set("JHNF", bgBo.getString("JHNF"));
					newBo.set("JHYF", bgBo.getString("JHYF"));
					newBo.set("BZ", bgBo.getString("BZ"));
					newBo.set("ZT", bgBo.getString("ZT"));
					newBo.set("K3GSBM", bgBo.getString("K3GSBM"));
					newBo.set("K3GSMC", bgBo.getString("K3GSMC"));
					SDK.getBOAPI().create("BO_DY_SCJH_YLYSCJH_Z", newBo, yBindId, null);
				} else {
					for(BO jhBo:jhBoList){
						
						if(yId.equals(jhBo.getString("ID"))){
							jhBo.set("QRCL", bgBo.getString("QRCL"));
							jhBo.set("JHKGSJ", bgBo.getString("JHKGSJ"));
							jhBo.set("JHWGSJ", bgBo.getString("JHWGSJ"));
							jhBo.set("BZ", bgBo.getString("BZ"));
							jhBo.set("ZT", bgBo.getString("ZT"));
							SDK.getBOAPI().update("BO_DY_SCJH_YLYSCJH_Z", jhBo);
						}
					}
				}
				
			}
			
		}
		
	}

}
