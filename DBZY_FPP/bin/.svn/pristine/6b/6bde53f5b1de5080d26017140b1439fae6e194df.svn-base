package com.nepharm.apps.fpp.biz.tam.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;

public class RwgzfpFormCompleteValidateStep2 extends InterruptListener
		implements InterruptListenerInterface {

	public RwgzfpFormCompleteValidateStep2() {
		// 表单校验
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//参数获取
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        
        String uid = ctx.getUserContext().getUID(); 
        boolean f1 = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "接受并继续分配任务");
        boolean f2 = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "接受");
        
        List<BO> list = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_RWXX")
				.bindId(ctx.getProcessInstance().getId()).list();
        int n = 0;
        for(int i=0;i<list.size();i++){
        	
        	String JXFP = list.get(i).getString("JXFP");
        	String JSRZH = list.get(i).getString("JSRZH");
        	if(uid.equals(JSRZH)){
        		if(JXFP!=null && !"".equals(JXFP)){
        			
        			if("1".equals(JXFP)){
        				n++;
        				break;
        			} 
        		} else {
    				throw new BPMNError("001","您有任务未选择是否继续分配，请选择！");
    			}
        	}
        }
        if(f1){
        	
	    	if(n==0){
	        	throw new BPMNError("002","请选择要继续分配的任务！");
	        }
        }
        if(f2){
        	if(n>0){
	        	throw new BPMNError("003","请取消要继续分配的任务！");
	        }
        }
        
        return true;

	}

}
