package com.nepharm.apps.fpp.biz.pem.event;

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
import com.nepharm.apps.fpp.util.BOUtil;
/**
 * 绩效模块-操作岗日产量分配流程-录入节点-扩展按钮重新平均分配日产量功能
 * @author zhangjh
 *
 */
public class EquallyDistributedYieldBtnActionImpl extends ValueListener {

    @Override
    public String execute(ProcessExecutionContext ctx) throws Exception {
        //参数获取
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        
        UserContext user = ctx.getUserContext();
        //获得流程实例id
        String bindid = ctx.getProcessInstance().getId();
     // Ajax方式
        ResponseObject ro;
        BO bo = null;
        String sql = null;
        List<BO> grid2Data = null;
        String zbid = null;//操作岗日产量维护-产量明细表id
        float RCL = 0;//日产量
	    float GZRS = 0;//工作人数
	    StringBuffer sb = new StringBuffer();
        
    	ro = ResponseObject.newOkResponse();
    	
    	

    	
    	
        //获得主表数据
	    BO formData = SDK.getBOAPI().getByProcess(PerformanceConstant.TAB_JXGL_CZGRICLWH_M, bindid);
	    if(formData == null || "".equals(formData)){
	    	 // 错误时
	        ro = ResponseObject.newErrResponse();
	        ro.msg("请先保存表单数据！");
	    	return ro.toString();
	    }
        
	    List<BO> gridData = SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX, true).addQuery("bindid =", bindid).list();
	    if(gridData == null || gridData.size()==0){
	    	 // 错误时
	        ro = ResponseObject.newErrResponse();
	        ro.msg("请先填写子表数据并保存！");
	    	return ro.toString();
	    }
	    for(int i=0;i<gridData.size();i++){
	    	bo = gridData.get(i);
	    	zbid = bo.getString("ID");
	    	RCL = Float.valueOf(bo.getString("RCL")==null?"0":bo.getString("RCL"));//用户填写的日产量
	    	GZRS = Float.valueOf(bo.getString("GZRS")==null?"0":bo.getString("GZRS"));//岗位定额人数
	    	float zhsj=0;
			try {
				zhsj = Float.valueOf(bo.getString("DEDJ")==null?"0":bo.getString("DEDJ"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				zhsj = 0;
			}
	    	
	    	//查询公司月份下出勤数量
			sql = "select CQTS from "+PerformanceConstant.TAB_XCGL_YYCQTSWH_M+" a,"+PerformanceConstant.TAB_XCGL_YYCQTSWH_WHMX+" b "
					+ "	where a.bindid=b.bindid "
					+ "	and a.ZXNF='"+formData.getString("NF")+"' "
					+ "	and b.ZXYF='"+formData.getString("YF")+"' "
					+ " and SYGSBM='"+formData.getString("GSBM")+"'";
			float CQTS = 0;
			try {
				CQTS = Float.valueOf(DBSql.getString(sql,"CQTS"));
			} catch (NumberFormatException e) {
				CQTS = 0;
			} catch (AWSDataAccessException e) {
				CQTS = 0;
			}
	    	float RJSJE = zhsj /CQTS;
	    	sql = "select * from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" where bindid='"+zbid+"' order BY CREATEDATE";
	    	  grid2Data = BOUtil.queryEncapsulationData(sql);
	    	  if(grid2Data == null){
	    		  sb.append("产量明细第"+(i+1)+"行分配明细信息为空，请检查是否已在表单做保存动作，如不需要本行请删除。"); 
	    		  continue;
	    	  }
	    	  
	    	  for(int j=0;j<grid2Data.size();j++){
	    		  bo = grid2Data.get(j);
	    		  
	    		  sql = "update "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" set "
	    		  		+ "	FTRCL="+RCL*GZRS/grid2Data.size()+","
	    		  		+ "	RJSJE="+(RJSJE*RCL*GZRS/grid2Data.size())+","
	    		  		+ "	RJSDJ="+RJSJE+" "
	    		  		+ "	where id='"+bo.getString("ID")+"'";
	    		  DBSql.update(sql);
	    	  }
	    }
	    if("".equals(sb.toString())){
	    	 ro.msg("平均分配完成");// 返回给服务器的消息
	         ro.put("key1", "value1").put("key2", "value2");// 放入前端需要的参数
	    }else{
	    	  ro = ResponseObject.newErrResponse();
		      ro.msg(sb.toString());
	    }
	   
        boId = null;
        //表单ID
        formId = null;
        grid2Data = null;
        //BO表名
        boName = null;
        sql = null;
        user = null;
        //获得流程实例id
        bindid = null;
        
        //获得表单填写公司编码、执行年份、执行月份
        bo = null;
        zbid = null;
        grid2Data = null;
          
        return ro.toString();
       
    }
}