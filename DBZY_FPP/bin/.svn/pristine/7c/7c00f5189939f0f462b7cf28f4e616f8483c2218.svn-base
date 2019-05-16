package com.nepharm.apps.fpp.biz.pem.event;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.cc.Adapter.DB;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.cc.MongoDBAPI;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.tem.constant.XhfakhConstant;

/**
 * 绩效模块-岗位金额设定（量化岗）-人资部节点办理前事件
 * @author zhangjh
 *
 */

public class QuartersQuotaValidateRt  extends InterruptListener implements InterruptListenerInterface   {
	 public String getDescription() {
	        return "人资部节点数据校验";
	    }
	 public String getProvider() {
	        return "Actionsoft";
	    }

	    public String getVersion() {
	        return "1.0";
	    }


    public boolean execute(ProcessExecutionContext param) throws Exception {
    	
          //获得用户信息
	      UserContext user = param.getUserContext();
	      //获得流程实例id
	      String bindid = param.getProcessInstance().getId();
	      //获得主表数据
	      //BO formData = (BO) param.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
	      //BO formData = (BO) param.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);

	      //获得子表数据
	      //List<BO> gridData = (List) param.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
	      //List<BO> gridData = (List) param.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
	      //BO_ACT_ACCOUNTING_SINGLE 校验凭证大于0
	    
	     
	      //获得主表数据
	      BO formData = SDK.getBOAPI().getByProcess(PerformanceConstant.TAB_JXGL_CZGZJS, bindid);
	      //String sql;
	      
//	      sql = "select count(*) cnt from  "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" "
//    		  		+ "	where GSBM='"+formData.getString("GSBM")+"' and GWBM='"+formData.getString("GWBM")+"' and GXBM='"+formData.getString("GXBM")+"'";
//	      System.out.println(sql);
//	      int cnt = DBSql.getInt(sql,"cnt");
	      
	      if("1".equals(formData.getString("LX"))){//判断为新增
	    	//查询台账表是否已有对应公司对应岗位定额信息
	    	  String sql = "select count(*) cnt from  "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" "
	    		  		+ "	where GSBM='"+formData.getString("GSBM")+"' and GWBM='"+formData.getString("GWBM")+"' and GXBM='"+formData.getString("GXBM")+"'";
		      System.out.println(sql);
		      int cnt = DBSql.getInt(sql,"cnt");
		      
	    	  if(cnt > 0){
		    	  throw new BPMNError("0001","“"+formData.getString("GSMC")+"”公司下的“"+formData.getString("GWMC")+"”岗位对应的工序"+formData.getString("GXMC")+"已有相应定额信息，如需修改请选择“类型”为“修改”项");
		      }
		   
	      }else{
	    	  String sql = "select count(*) cnt from  "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" "
	    		  		+ "	where DJBH='"+formData.getString("DJBH")+"' and GSBM='"+formData.getString("GSBM")+"' and GWBM='"+formData.getString("GWBM")+"' and GXBM='"+formData.getString("GXBM")+"'";
		      System.out.println(sql);
		      int cnt = DBSql.getInt(sql,"cnt");
	    	  if(cnt<= 0){
		    	  throw new BPMNError("0002","单据号“"+formData.getString("DJBH")+"”信息："+"“"+formData.getString("GSMC")+"”公司下的“"+formData.getString("GWMC")+"”岗位对应的工序“"+formData.getString("GXMC")+"”没有有相应定额信息，如需添加请选择“类型”为“新增”项");
		      }
	    	  
	      }
	    
	    
	     
	      formData = null;
	      user = null;
	      bindid = null;
	   
	     // sql = null;
	      
	      
	     

        return true;
    }

}
