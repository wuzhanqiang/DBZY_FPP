package com.nepharm.apps.fpp.biz.ppm.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.nepharm.apps.fpp.is.k3.controller.CallInterfaceCreateProductionOrder;

public class BeforeCompleteCreateProductionOrder extends InterruptListener {

    public String getDescription() {
        return "流程结束前创建K3生产订单信息";
    }

    public boolean execute(ProcessExecutionContext param) throws Exception {
    	 //获得用户信息
	      UserContext user = param.getUserContext();
	      //获得流程实例id
	      String bindid = param.getProcessInstance().getId();
	      CallInterfaceCreateProductionOrder cicpo = null;
	      String msg = null;
	      String sql = "select count(*) CNT from WFC_TASK a where a.PROCESSINSTID='"+bindid+"'";
	      int cnt = Integer.valueOf(DBSql.getString(sql,"CNT"));
	      if(cnt==1){//最后一个办理人
	    	  //调用创建K3生产订单接口
		     cicpo = new CallInterfaceCreateProductionOrder();
		      
		     msg = cicpo.createProductionOrder(bindid,user.getUID());
//		      System.out.println("========================================================="+msg);
//		      System.out.println();
		      if(!"".equals(msg)){
		    	  throw new BPMNError("1001",msg);
		    	  
		    	  
		      }
	      }
	     
	      
	      user = null;
	      bindid = null;
	      cicpo = null;
	      msg = null;
        return true;
    }

}