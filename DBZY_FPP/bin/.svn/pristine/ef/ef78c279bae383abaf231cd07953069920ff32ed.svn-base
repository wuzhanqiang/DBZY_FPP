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
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.tem.constant.XhfakhConstant;

/**
 * 绩效模块-岗位金额设定（量化岗）-总经理审批节点办理前事件
 * @author zhangjh
 *
 */

public class CreatePositionAmountSetting  extends InterruptListener implements InterruptListenerInterface   {
	 public String getDescription() {
	        return "任务完成前，创建岗位金额设定台账信息（量化岗）";
	    }
	 public String getProvider() {
	        return "Actionsoft";
	    }

	    public String getVersion() {
	        return "1.0";
	    }


    public boolean execute(ProcessExecutionContext param) throws Exception {
    	  //如果选择为不同意不用创建数据
    	  if(param.isChoiceActionMenu("不同意")){
    		  return true; 
    	  }
    	  
    	  
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
	      String sql;
	      
	      //去除主表系统字段
	      formData.remove("UPDATEUSER");
	      formData.remove("UPDATEDATE");
	      formData.remove("CREATEUSER");
	      formData.remove("ISEND");
	      formData.remove("ORGID");
	      formData.remove("CREATEDATE");
	      formData.remove("ID");//资产入库ID
	      formData.remove("BINDID");//资产入库bindID
	      formData.remove("PROCESSDEFID");
	      String DJBH = null;
	      
	      if("1".equals(formData.getString("LX"))){//判断为新增
	    	  if(formData.getString("DJBH")==null ||"".equals(formData.getString("DJBH"))){
	    		  //获得量化定额单据编号
		    	  DJBH = "LHDE"+SDK.getRuleAPI().executeAtScript("@sequenceMonth(LHDE,6,0)");
		    	  formData.set("DJBH",DJBH);
		    	  //回填流程中单据编号
			      sql = "update "+PerformanceConstant.TAB_JXGL_CZGZJS+" set DJBH='"+DJBH+"' "
		    		  		+ "	where bindid='"+bindid+"'";
		    		  DBSql.update(sql);
	    	  }
	    	 
	    	
//		      SDK.getBOAPI().createDataBO(PerformanceConstant.TAB_JXGL_CZGZJSTZ, formData, user);
		    //创建奖金维护信息
		      ProcessInstance processInstance = SDK.getProcessAPI().createBOProcessInstance("obj_225f00eae18a4a33864f881d19788cc2", user.getUID(), "创建岗位金额设定台账信息"); 
		    //创建岗位金额设定台账信息（量化岗）
	    	  SDK.getBOAPI().create(PerformanceConstant.TAB_JXGL_CZGZJSTZ, formData, processInstance, user);
		   
	      }else if("2".equals(formData.getString("LX"))){//判断为修改
	    	  formData.set("ID",formData.getString("LYCCID"));//台账ID
	    	  SDK.getBOAPI().update(PerformanceConstant.TAB_JXGL_CZGZJSTZ, formData);

	      }
	    
	    
	     
	      formData = null;
	      user = null;
	      bindid = null;
	      DJBH = null;
	      sql = null;
	      
	      
	     

        return true;
    }
    
}
