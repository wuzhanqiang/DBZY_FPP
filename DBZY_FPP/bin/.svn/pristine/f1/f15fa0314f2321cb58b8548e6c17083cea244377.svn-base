package com.nepharm.apps.fpp.biz.pam.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.sdk.local.SDK;

public class JjbqrtzTaskAfterCompleteStep1 extends ExecuteListener implements
		ExecuteListenerInterface {

	public JjbqrtzTaskAfterCompleteStep1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		String uid = ctx.getUserContext().getUID();
		String username = ctx.getUserContext().getUserName();
		//获取表数据
		BO mainBoData = SDK.getBOAPI().getByProcess("BO_DY_XCGL_JJBQRTZ_M", bindId);
		List<BO> zgsBoDatas = SDK.getBOAPI().query("BO_DY_XCGL_JJBQRTZ_ZGS").bindId(bindId).list();
		List<BO> bmBoDatas = SDK.getBOAPI().query("BO_DY_XCGL_JJBQRTZ_BM").bindId(bindId).list();
		String year = mainBoData.getString("YEAR");
		String month = mainBoData.getString("MONTH");
		//更新主表数据
		mainBoData.set("SQR", username);
		mainBoData.set("SQRZH", uid);
		SDK.getBOAPI().update("BO_DY_XCGL_JJBQRTZ_M", mainBoData);
		
		//查询奖金包维护表，判断是否已有数据
		for(int i=0;i<zgsBoDatas.size();i++){
			String SYGSBM = zgsBoDatas.get(i).getString("GSBM");
			String SYGSMC = zgsBoDatas.get(i).getString("GSMC");
			String ZJBJE = zgsBoDatas.get(i).getString("QDJJB");
			
			createDatas(SYGSBM, SYGSMC, uid, year, month, ZJBJE);
		}
		for(int i=0;i<bmBoDatas.size();i++){
			String SYGSBM = bmBoDatas.get(i).getString("BMID");
			String SYGSMC = bmBoDatas.get(i).getString("BM");
			String ZJBJE = bmBoDatas.get(i).getString("QDJJB");
			
			createDatas(SYGSBM, SYGSMC, uid, year, month, ZJBJE);
		}
	}
	
	static void createDatas(String SYGSBM, String SYGSMC, String uid, 
			String year, String month, String ZJBJE){
		
		String dwBindId = "";
		List<BO> jjbmBoDatas = SDK.getBOAPI().query("BO_DY_XCGL_JJB_M").addQuery("SYGSBM = ", SYGSBM).list();
		//判断主表是否有公司/部门信息
		if(jjbmBoDatas.size()==0){
			//创建视图
			String title = "奖金包维护";
			String processDefId = "obj_bb01604f3f9b479a80542be206eced31";//obj_a6f8cc51e6df462ca95a62f30567930a
			ProcessInstance dwProcess = SDK.getProcessAPI().createBOProcessInstance(processDefId, uid, title);
			//初始化视图数据
			dwBindId = dwProcess.getId();
			
			BO jjbmBoData = new BO();
			jjbmBoData.set("SYGSBM", SYGSBM);
			jjbmBoData.set("SYGSMC", SYGSMC);
			SDK.getBOAPI().create("BO_DY_XCGL_JJB_M", jjbmBoData, dwBindId, uid);
		} else {

			dwBindId = jjbmBoDatas.get(0).getBindId();
		}
		
		BO jjbzBoData = new BO();
		jjbzBoData.set("NF", year);
		jjbzBoData.set("YF", month);
		jjbzBoData.set("ZJBJE", ZJBJE);
		SDK.getBOAPI().create("BO_DY_DY_XCGL_JJB_S", jjbzBoData, dwBindId, uid);
	}

}
