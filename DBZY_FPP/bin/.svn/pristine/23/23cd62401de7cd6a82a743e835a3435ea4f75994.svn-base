package com.nepharm.apps.fpp.biz.tam.event;

import java.util.Iterator;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.sdk.local.SDK;

public class RwgzfpTaskAfterCompleteStep1 extends ExecuteListener implements
		ExecuteListenerInterface {

	public RwgzfpTaskAfterCompleteStep1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		
		//流程ID
		String bindId = ctx.getProcessInstance().getId();
		/*//记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);*/
        
        BO boData = SDK.getBOAPI().getByProcess("BO_DY_RWGL_RWGZFP_MAIN", bindId);
        String RWBH = boData.getString("RWBH");
        String RWMC = boData.getString("RWMC");
        int STEP = Integer.parseInt(boData.getString("STEP"));
        
        SDK.getBOAPI().updateByBindId("BO_DY_RWGL_RWGZFP_RWXX", bindId, "FRWMC", RWMC);
        SDK.getBOAPI().updateByBindId("BO_DY_RWGL_RWGZFP_RWXX", bindId, "FRWBH", RWBH);
        SDK.getBOAPI().updateByBindId("BO_DY_RWGL_RWGZFP_RWXX", bindId, "STEP", STEP+1);
        
        List<BO> boList = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_RWXX").bindId(bindId).list();
        Iterator ite = boList.iterator();
        List<BO> xsBo = SDK.getBOAPI().query("BO_DY_RWGL_NDXSDY").list();
        String ZDFS = xsBo.get(0).getString("ZDFS");
        String ZGFS = xsBo.get(0).getString("ZGFS");
        String ZDXS = xsBo.get(0).getString("ZDXS");
        String ZGXS = xsBo.get(0).getString("ZGXS");
        while(ite.hasNext()){
        	BO bo = (BO) ite.next();
        	//更新子表任务编号
        	if(bo.getString("RWBH") == null || "".equals(bo.getString("RWBH"))){
        		
        		String strRWBH = SDK.getRuleAPI().executeAtScript("@sequenceMonth(test,6,0)");
        		bo.set("RWBH", strRWBH);
        		SDK.getBOAPI().update("BO_DY_RWGL_RWGZFP_RWXX", bo);
        	}
        	
        	//计算难度系数
        	String ndbindId = bo.getId();
        	List<BO> ndBoList = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_RWND").bindId(ndbindId).list();
        	double zf = 0.0;
        	int n = 0;
        	for(int i=0;i<ndBoList.size();i++){
        		if(ndBoList.get(i).getString("PJJG") != null){
        			
        			double pjjg = Double.parseDouble(ndBoList.get(i).getString("PJJG"));
        			zf += pjjg;
        			n++;
        		}
        		
        	}
        	double avg = Math.round(zf / n * 100) / 100.0;
        	//换算系数
        	double dZDFS = Double.parseDouble(ZDFS);
        	double dZGFS = Double.parseDouble(ZGFS);
        	double dZDXS = Double.parseDouble(ZDXS);
        	double dZGXS = Double.parseDouble(ZGXS);
        	double fsc = dZGFS-dZDFS;
        	double xsc = dZGXS-dZDXS;
        	xsc = Math.round(xsc * 100) / 100.0;
        	double xsz = fsc/xsc;
        	double sjfsc = avg-dZDFS;
        	avg = sjfsc/xsz+1;
        	avg = Math.round(avg * 100) / 100.0;
        	
        	bo.set("NDXS", avg);
        	
        	SDK.getBOAPI().update("BO_DY_RWGL_RWGZFP_RWXX", bo);
        }
        
	}

}
