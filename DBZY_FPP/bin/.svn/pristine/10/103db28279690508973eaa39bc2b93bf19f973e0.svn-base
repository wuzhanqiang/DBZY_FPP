package com.nepharm.apps.fpp.biz.pam.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class JjbqrtzFormAfterSaveStep1 extends ExecuteListener implements
		ExecuteListenerInterface {

	public JjbqrtzFormAfterSaveStep1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ProcessExecutionContext pec) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBSql.open();
		Statement st = null;
		ResultSet rs = null;
		st = conn.createStatement();
		//记录ID
        String boId = pec.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = pec.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = pec.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        //bindId
        String bindId = pec.getProcessInstance().getId();
        if(boName.equals("BO_DY_XCGL_JJBQRTZ_M")){
        	BO bo = (BO)pec.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
        	String JTLRXS = bo.getString("JTLRXS");//集团利润系数
        	if(JTLRXS != null && !"".equals(JTLRXS)){
        		double xs = Double.parseDouble(JTLRXS);
        		//去子表数据
        		List<BO> zgsDatas = SDK.getBOAPI().query("BO_DY_XCGL_JJBQRTZ_ZGS").bindId(bindId).list();
        		List<BO> bmDatas = SDK.getBOAPI().query("BO_DY_XCGL_JJBQRTZ_BM").bindId(bindId).list();
        		
        		for(BO b:zgsDatas){
        			
        			updateJJB("BO_DY_XCGL_JJBQRTZ_ZGS", b, xs);
        		}
        		
        		for(BO b:bmDatas){

        			updateJJB("BO_DY_XCGL_JJBQRTZ_BM", b, xs);
        		}
        		
        	}
        	
        }
        
       /* if(!boName.equals("BO_DY_XCGL_JJBQRTZ_M")){
        	
        	List<BO> gridData = (List) pec.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
        	
        	for(int i=0;i<gridData.size();i++){
        		
        		String JJB = gridData.get(i).getString("JJB");
        		String TZJE = gridData.get(i).getString("TZJE");
        		double dJJB = Double.parseDouble(JJB);
        		double dTZJE = Double.parseDouble(TZJE);
        		
        		double dQDJJB = dJJB + dTZJE;
        		gridData.get(i).set("QDJJB", dQDJJB);
        		
        		SDK.getBOAPI().update(boName, gridData.get(i));
        		
        	}
        }*/
        
	}
	//更新奖金包数据
	static void updateJJB(String boName, BO b, double xs){
		double JJB = Double.parseDouble(b.getString("YL1"));
		double scJJB = Double.parseDouble(b.getString("JJB"));
		double TZJE = Double.parseDouble(b.getString("TZJE"));
		double QDJJB = Double.parseDouble(b.getString("QDJJB"));
		double SJJJB = Double.parseDouble(b.getString("SJJJB"));
		double cyje = QDJJB-scJJB;//差异金额
		
		JJB = JJB * xs;//乘系数后奖金包
		QDJJB = JJB + cyje;
		SJJJB = JJB + TZJE;
		
		b.set("JJB", JJB);
		b.set("QDJJB", QDJJB);
		b.set("SJJJB", SJJJB);
		
		SDK.getBOAPI().update(boName, b);
	}
	
}
