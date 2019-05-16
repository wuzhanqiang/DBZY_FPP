package com.dbzy.zjxs.bhwy;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class OtctszcbhwyFORM_AFTER_SAVE extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext arg0) throws Exception {
String bindid = arg0.getProcessInstance().getId();
		
        List<BO> list = SDK.getBOAPI().query("BO_DY_OTCTSZC_S").bindId(bindid).list();
        if(list.size() > 0) {
        	for(int i = 0; i < list.size(); i++) {
        		BO bo = list.get(i);
        		String ZCBH = bo.getString("ZCBH1");
    			String sqlCheck = "select COUNT(*) from BO_DY_OTCTSZC_S WHERE ZCBH1 = '" + ZCBH + "'";
    			String flagStr = DBSql.getString(sqlCheck);
    			int flag = 0;
    			if("".equals(flagStr) && flagStr != null) {
    				flag = Integer.parseInt(flagStr);
    			}
    			if(flag != 1) {
    				String a = SDK.getRuleAPI().executeAtScript("Otctszc@sequenceYear(CRM_CUSTOMER,8,0)");
    				bo.set("ZCBH1",a);
    				SDK.getBOAPI().update("BO_DY_OTCTSZC_S", bo);
    			}
        	}
        }
		// TODO Auto-generated method stub
		
	}

}
