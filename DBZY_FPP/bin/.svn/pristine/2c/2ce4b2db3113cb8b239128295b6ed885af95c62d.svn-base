package com.nepharm.apps.fpp.is.k3.manual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.apps.AppsConst;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.cc.Adapter.DB;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.exception.AWSAPIException;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;
import com.nepharm.apps.fpp.is.k3.dao.K3BusinessDataDao;
import com.nepharm.apps.fpp.util.BOUtil;
/**
 * 基础信息-扩展按钮同步K3系统业务数据功能
 * @author zhangjh
 *
 */
public class ManualSynchronousK3DataBtnActionImpl extends ValueListener {

    @Override
    public String execute(ProcessExecutionContext ctx) throws Exception {
        //参数获取
        //记录ID
       
        
        UserContext user = ctx.getUserContext();
      //获得流程实例id
        String bindid = ctx.getProcessInstance().getId();
     // Ajax方式
        ResponseObject ro;
        
      //获得主表数据
	    BO formData = SDK.getBOAPI().getByProcess(SynchronousK3Constant.TAB_JCXX_K3TBDZSZ_M, bindid);
	    if(formData.getString("TBKSSJ")==null || "".equals(formData.getString("TBKSSJ")) 
	    		|| formData.getString("TBJSSJ")==null || "".equals(formData.getString("TBJSSJ")) ){
	    	 // 错误时
	        ro = ResponseObject.newErrResponse();
	        ro.msg("请填写手动同步开始时间及手动同步结束时间");
	    	
	        System.out.println(ro.toString());
	          
	        return ro.toString();
	    }
        
        K3BusinessDataDao k3BusinessDataDao = new K3BusinessDataDao();
		String fhxx = k3BusinessDataDao.getK3BusinessData(formData.getString("TBKSSJ"), formData.getString("TBJSSJ"),1,"admin" );
       
        if(fhxx==null || "".equals(fhxx) ){
        	ro = ResponseObject.newOkResponse();
         	ro.msg("同步数据完成");// 返回给服务器的消息
            ro.put("key1", "value1").put("key2", "value2");// 放入前端需要的参数
        }else{
        	 // 错误时
            ro = ResponseObject.newErrResponse();
            ro.msg("同步信息有问题，问题内容为："+fhxx);
        }

       System.out.println(ro.toString());
        
       
    	
       
          
        return ro.toString();
       
    }
}