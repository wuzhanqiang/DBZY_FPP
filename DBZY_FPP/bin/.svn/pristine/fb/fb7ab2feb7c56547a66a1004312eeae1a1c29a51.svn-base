package com.nepharm.apps.fpp.biz.tam.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListenerInterface;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.sdk.local.SDK;

public class GzpjFormToolbarBuild extends ValueListener implements
		ValueListenerInterface {

	public GzpjFormToolbarBuild() {
		// TODO 扩展按钮
	}

	@Override
	public String execute(ProcessExecutionContext ctx) throws Exception {
		//参数获取
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        //sid
        String sid = ctx.getUserContext().getSessionId();
        
        // Ajax方式
        ResponseObject ro = ResponseObject.newOkResponse();
        boolean r = true;// 针对业务进行处理
        
        BO boData = SDK.getBOAPI().get(boName, boId);
        String processInstId = boData.getString("PROCESSPARENTID");
        String formDefId = boData.getString("FORMDEFID");
        
        if(processInstId == null || processInstId.equals("")
        		|| formDefId == null || formDefId.equals("")){
        	r = false;
        }
        
        String url = createUrl(sid, processInstId, formDefId);
        // 处理业务逻辑成功时
        if (r) {
            ro.msg("成功");// 返回给服务器的消息
            ro.put("url", url);// 放入前端需要的参数
            return ro.toString();
        } else {
            // 错误时
            ro = ResponseObject.newErrResponse();
            ro.msg("错误");
            return ro.toString();
        }
	}
	
	private String createUrl(String sid, String processInstId, String formDefId){
		String bpmPortalHost = SDK.getPlatformAPI().getPortalUrl();
		//String bpmPortalHost = "http://localhost:8088/portal";
		int openState = 9;
		String url = SDK.getFormAPI().getDWFormURL("", sid, processInstId, formDefId, openState);
		return url;
	}

}
