package com.dbzy.zjxs.bhwy;

import java.text.NumberFormat;
import java.util.List;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

public class LczczybhwyFORM_AFTER_SAVE extends ExecuteListener{

	@Override
	public void execute(ProcessExecutionContext arg0) throws Exception {
		
		String bindid = arg0.getProcessInstance().getId();
		
        List<BO> list = SDK.getBOAPI().query("BO_DY_ZDZC_LCZCZY_S").bindId(bindid).list();
        if(list.size() > 0) {
        	for(int i = 0; i < list.size(); i++) {
        		BO bo = list.get(i);
        		String ZCBH = bo.getString("ZCBH1");
    			String sqlCheck = "select COUNT(*) from BO_DY_ZDZC_LCZC_S WHERE ZCBH1 = '" + ZCBH + "'";
    			String flagStr = DBSql.getString(sqlCheck);
    			int flag = 0;
    			if("".equals(flagStr) && flagStr != null) {
    				flag = Integer.parseInt(flagStr);
    			}
    			if(flag != 1) {
    				String a = SDK.getRuleAPI().executeAtScript("Lczczy@sequenceYear(CRM_CUSTOMER,8,0)");
    				bo.set("ZCBH1",a);
    				SDK.getBOAPI().update("BO_DY_ZDZC_LCZCZY_S", bo);
    			}
        	}
        }
        
    	//BO操作
  		BOAPI boapi = SDK.getBOAPI();
  		//获取子表集合
  		List<BO> datas = boapi.query("BO_DY_ZDZC_LCZCZY_S").addQuery("BINDID = ", bindid).list();
  		if(datas != null && !datas.isEmpty()) {
  			for(BO data : datas) {
  				String badj = data.getString("BADJ");
  				String gsgdj = data.getString("GSGDJ");
  				
  				double dbadj = Double.parseDouble(badj);
  				double dgsgdj = Double.parseDouble(gsgdj);

  				NumberFormat nf = NumberFormat.getNumberInstance();
  				nf.setMaximumFractionDigits(3);
  				
  				double dce = dbadj - dgsgdj;
  				
  				data.set("CE", dce+"");
  				
  		        SDK.getBOAPI().update("BO_DY_ZDZC_LCZCZY_S", data);
  			}
  		}
        
	}

}
