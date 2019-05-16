package com.nepharm.apps.fpp.biz.pem.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.BOUtil;
import com.nepharm.apps.fpp.util.DateUtil;
/**
 * 创建日产量维护流程及数据
 * 系统默认所有编码以HR编码为主——即：公司编码、岗位编码表单上数据都是HR系统中的编码
 * @author zhangjh
 */
public class DailyOutputDataCalculation  extends InterruptListener implements InterruptListenerInterface{
	
	
	
	@Override
	public boolean execute(ProcessExecutionContext param) throws Exception {
		// TODO Auto-generated method stub
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
	    
	      List<BO> gridData = SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX, true).addQuery("bindid =", bindid).list();
	      List<BO> grid2Data = null;

	      //获得主表数据
	      BO formData = SDK.getBOAPI().getByProcess(PerformanceConstant.TAB_JXGL_CZGRICLWH_M, bindid);
	      BO bo = null;
	      String zbid = null;//操作岗日产量维护-产量明细表id
	      float DEDJ = 0;//定额单价
	      float RCL = 0;//日产量
	      float GZRS = 0;//工作人数
	      String SCRQ = formData.getString("SCRQ");
	      String GSBM = formData.getString("GSBM");
	     
	      float FTRCL = 0;
	      String sql = null;
	      String GXMC = null;
	      for(int i=0;i<gridData.size();i++){
	    	  float bhzje = 0;//子表一条记录的总数量
		      float zdzbzje = 0;//字段子表总数量
	    	  bo = gridData.get(i);
	    	  GXMC = bo.getString("GXMC");
	    	  zbid = bo.getString("ID");
	    	  DEDJ = Float.valueOf(bo.getString("DEDJ")==null?"0":bo.getString("DEDJ"));
	    	  RCL = Float.valueOf(bo.getString("RCL")==null?"0":bo.getString("RCL"));
	    	  GZRS = Float.valueOf(bo.getString("GZRS")==null?"0":bo.getString("GZRS"));
	    	  //子表一条总金额：日产量 * 工作人数，四舍五入保留4位小数。
	    	  bhzje = (float)(Math.round(RCL*GZRS*10000))/10000;
	    	  //通过子表id查询操作岗日产量维护-产量分配明细表数据
	    	  sql = "select * from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" where bindid='"+zbid+"' order BY CREATEDATE";
	    	  grid2Data = BOUtil.queryEncapsulationData(sql);
	    	  for(int j=0;j<grid2Data.size();j++){
	    		  bo = grid2Data.get(j);
	    		  FTRCL = Float.valueOf(bo.getString("FTRCL")==null?"0":bo.getString("FTRCL"));
//	    		  System.out.println("===============RCL=================================="+RCL);
//	    		  System.out.println("===============FTRCL=================================="+FTRCL);
//	    		  //用户填写数量不能大于子表日常量
//	    		  if(FTRCL > RCL){
//	    			  throw new BPMNError("0001","产量明细信息中工序为"+GXMC+"的“产量分配明细表”中第"+(j+1)+"条分摊日产量不能大于产量明细信息的日产量");
//	    		  }
	    		  float RJSDJ = Float.valueOf(bo.getString("RJSDJ")==null?"0":bo.getString("RJSDJ"));
	    		//获得岗位定额月
	    		//查询公司月份下出勤数量
					sql = "select CQTS from "+PerformanceConstant.TAB_XCGL_YYCQTSWH_M+" a,"+PerformanceConstant.TAB_XCGL_YYCQTSWH_WHMX+" b "
							+ "	where a.bindid=b.bindid "
							+ "	and a.ZXNF='"+SCRQ.substring(0,4)+"' "
							+ "	and b.ZXYF='"+SCRQ.substring(5,7)+"' "
							+ " and SYGSBM='"+GSBM+"'";
					float CQTS = 0;
					try {
						CQTS = Float.valueOf(DBSql.getString(sql,"CQTS"));
					} catch (NumberFormatException e) {
						CQTS = 0;
					} catch (AWSDataAccessException e) {
						CQTS = 0;
					}
		  			float zhsj;
		  			try {
		  				zhsj = Float.valueOf(DEDJ);
		  			} catch (NumberFormatException e) {
		  				// TODO Auto-generated catch block
		  				zhsj = 0;
		  			}
		  			RJSDJ = zhsj /CQTS;
	    		  sql = "update "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" set RJSJE="+(RJSDJ*FTRCL)+",RJSDJ="+RJSDJ+" where id='"+bo.getString("ID")+"'";
	    		  DBSql.update(sql);
	    		  zdzbzje = zdzbzje + FTRCL;
	    	  }
//	    	  zdzbzje = (float)(Math.round(zdzbzje*10))/10;
	    	  int s = (int)zdzbzje;
	    	  //如果字段子表数量之和大于子表一条总数量
	    	  if(bhzje < s){
	    		  throw new BPMNError("0001","产量明细信息中工序为"+GXMC+"的“产量分配明细表”总数量已大于产量明细信息的（ 日产量 * 工作人数）");
	    	  }
	      }
	    GXMC = null;
	    sql = null;
	    grid2Data = null;
	    user = null;
	    gridData = null;
	    bindid = null;
	    zbid = null;
	    SCRQ = null;
	    GSBM = null;
		return true;
	}
	
	
	
}
