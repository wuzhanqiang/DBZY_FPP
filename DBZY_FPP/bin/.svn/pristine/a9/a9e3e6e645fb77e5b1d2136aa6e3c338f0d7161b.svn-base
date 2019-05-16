package com.nepharm.apps.fpp.biz.qm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.sdk.local.SDK;

public class RcwsjcFormAfterSaveStep1 extends ExecuteListener implements
		ExecuteListenerInterface {

	public RcwsjcFormAfterSaveStep1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		String uid = ctx.getUserContext().getUID();
		//获取主表数据，并将岗位放入数组中
		BO mainData = SDK.getBOAPI().getByProcess("BO_DY_ZLGL_JJQQJJL_M", bindId);
		String GWBM = mainData.getString("GWBM");
		String GWMC = mainData.getString("GWMC");
		if(GWMC != null && !GWMC.equals("") && GWBM!= null && !GWMC.equals("")){
			
			String[] GWBMarr = GWBM.split(" ");
			String[] GWMCarr = GWMC.split(" ");
			//获取洁净区清洁记录子表，判断是否有数据，如果没数据，初始化数据
			List<BO> boList = SDK.getBOAPI().query("BO_DY_ZLGL_JJQQJJL_S").bindId(bindId).list();
			if(boList.size()==0){
				
				List<BO> boDatas = SDK.getBOAPI().query("BO_DY_ZLGL_JJQQJDX").list();
				for(int j=0;j<GWMCarr.length;j++){
					
					for(int i=0;i<boDatas.size();i++){
						BO bo = new BO();
						bo.set("QJDX", boDatas.get(i).get("QJDX"));
						bo.set("QJJG", "是");
						bo.set("XDJG", "是");
						bo.set("JCJG", "是");
						bo.set("GWMC", GWMCarr[j]);
						bo.set("GWBM", GWBMarr[j]);
						SDK.getBOAPI().create("BO_DY_ZLGL_JJQQJJL_S", bo, bindId, uid);
					}
				}
			}
		}
		
	}

}
