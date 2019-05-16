package com.nepharm.apps.fpp.biz.kms.event;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

public class KCSHFormAfterLoad extends ExecuteListener {

	public KCSHFormAfterLoad() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext param) throws Exception {
		// TODO Auto-generated method stub
		//记录ID
        String boId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //BO表名
        String boName = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        String bindId = param.getProcessInstance().getId();
        if(boName.equals("BO_DY_KMS_KCSH_S")){
        	
        	List<BO> list = SDK.getBOAPI().query(boName).bindId(bindId).list();
        	for(BO bo:list){
        		String bID = bo.getString("KCBINDID");
        		String style="<span style=\"color:#53709a;cursor:pointer;\" onclick=\"openH("+bID+");\">"+"点击查看"+"</span>";
        		bo.set("CKXQ", style);
        		
        	}
        	
        }

	}

	@Override
	public String getDescription() {
		return "全局设置-课程题目链接初始化";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "v1.0.0";
	}
}
