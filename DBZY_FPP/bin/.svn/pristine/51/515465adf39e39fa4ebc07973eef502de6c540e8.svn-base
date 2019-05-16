package com.nepharm.apps.fpp.biz.pem.event;

import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.UtilString;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

/**
 * 奖惩申诉流程 表单加载后设置    原绩效表单连接
 * @author lizhen
 *
 */
public class JCSSFormAfterLoad extends ExecuteListener {

	@Override
	public void execute(ProcessExecutionContext param) throws Exception {
		//记录ID
        String boId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        //String formId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);

        
        //获取表单所有的标签
        Map<String, Object> macroLibraries = param.getParameterOfMap(ListenerConst.FORM_EVENT_PARAM_TAGS);

        
        try {
        	BO bo = SDK.getBOAPI().get(boName, boId); 
			String yBindId=bo.getString("YBINDID");
			String name=bo.getString("YDJBH");
			//String fieldHtml = (String) macroLibraries.get("SSDJ");//一个字段名
			PerformanceDao dao = new PerformanceDao();
			String taskId=dao.getTaskId(yBindId);
			String style="<span style=\"color:#53709a;cursor:pointer;\" onclick=\"openHTML('"+yBindId+"','"+taskId+"');\">"+"点击查看"+"</span>";
			macroLibraries.put("DJZS", style);//展示字段
		} catch (Exception e) {
			
		}
	}

	@Override
	public String getDescription() {
		return "全局设置-奖惩单据查询链接初始化";
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
