package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

/**
 * 奖惩通知流程 保存前事件，用来更新当前选择人所对应的岗位、审批人信息。
 * @author lizhen
 *
 */
public class JCTZFormBeforeSaveEvent extends InterruptListener{

	public boolean execute(ProcessExecutionContext param) throws Exception {
		String boId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		String boName = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		BO formData = (BO) param.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
		String bkhr=(String)formData.get("BKHRZH");//被考核人账号
		String gwbm=SDK.getRuleAPI().executeAtScript("@getUserInfo("+bkhr+",GWBM)");
		String gwmc=SDK.getRuleAPI().executeAtScript("@getUserInfo("+bkhr+",GWMC)");
		String ldzh=SDK.getRuleAPI().executeAtScript("@sqlValue(select RYZH from BO_DY_JXGL_JXKH_KHR  where GWBM='"+gwbm+"')");
		
		formData.set("GWMC",gwmc );
		formData.set("GWBM",gwbm );
		formData.set("LDZH",ldzh );
		
//		StringBuffer sql = new StringBuffer();
//		sql.append("update "+boName+" set GWMC='"+gwmc+"',GWBM='"+gwbm+"',LDZH='"+ldzh+"' where id='"+boId+"'");
//		DBSql.update(sql.toString());
		
		return true;
	}
	
	
	
	
	 public String getDescription() {
	        return "表单保存前的事件，更新被考核人的岗位、考核人领导信息";
	    }

	    public String getProvider() {
	        return "nepharm";
	    }

	    public String getVersion() {
	        return "1.0";
	    }
	    
	    
	    
}
