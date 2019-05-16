package com.nepharm.apps.fpp.biz.pam.event;

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
 * 薪资模块-奖金录入流程-录入节点办理前事件
 * @author zhangjh
 *
 */

public class CreateBonusMaintenance  extends InterruptListener implements InterruptListenerInterface   {
	 public String getDescription() {
	        return "办理后创建奖金维护信息视图";
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
	    
	      List<BO> gridData = SDK.getBOAPI().query(ProductPlanConstant.TAB_XCGL_JJLR_S, true).addQuery("bindid =", bindid).list();
	      BO bo = null;
	      //校验出勤天数不能为0
	      for(int i=0;i<gridData.size();i++){
	    	  bo = gridData.get(i);
	    	  if(bo.getString("CQTS")==null || "0".equals(bo.getString("CQTS"))){
	    		  throw new BPMNError("0001","奖金明细信息中第"+(i+1)+"行的“出勤天数”不能为0");
	    	  }
	    	  
	      }
	      

	      //获得主表数据
	      BO formData = SDK.getBOAPI().getByProcess(ProductPlanConstant.TAB_XCGL_JJLR_M, bindid);
	      String flhgbindid = null;
	    
	      formData.set("SOURCE_JJLR_FLOW_BINDID", formData.get("BINDID"));//奖金录入主表BINDID
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
	      
	      String SYGSBM = formData.getString("SYGSBM");//得到公司编码
	      String ZXNF = formData.getString("ZXNF");//得到年份
	      String ZXYF = formData.getString("ZXYF");//得到月份
	      String SFCYJS = formData.getString("SFCYJS");//大于应出勤的天数是否参与奖金计算
	      String sql;
	      String GWBM;//子表岗位编码
	      String SFTSJL;//是否特殊记录（1:特殊记录，2:正常记录）
	      String gdzdz = null;
	      String ryzhold = null;
	     
	    
	      
	      //创建奖金维护信息
	      ProcessInstance processInstance = SDK.getProcessAPI().createBOProcessInstance(ProductPlanConstant.VIEW_XCGL_JJWH_M, user.getUID(), "奖金维护"); 
	      //创建奖金维护主表信息并关联视图
	      SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJWH_M, formData, processInstance, user);
	      //创建奖金维护明细
	      for(int i=0;i<gridData.size();i++){
	    	  bo = gridData.get(i);
	    	  
	    	  
	    	  bo.set("SOURCE_JJLRMX_BOID", bo.get("ID"));//奖金录入明细BOID
	    	  //去除子表系统字段
	    	  bo.remove("UPDATEUSER");
	    	  bo.remove("UPDATEDATE");
	    	  bo.remove("CREATEUSER");
	    	  bo.remove("ISEND");
	    	  bo.remove("ORGID");
	    	  bo.remove("CREATEDATE");
	    	  bo.remove("ID");
	    	  bo.remove("BINDID");
	    	  bo.remove("PROCESSDEFID");
	    	  SFTSJL = bo.getString("SFTSJL");
	    	  float YLGZ =0;
	    	  gdzdz = bo.getString("YLGZ");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  YLGZ = Float.valueOf("0");
    		  }else{
    			  YLGZ = Float.valueOf(gdzdz);
    		  }
    		  bo.set("YLGZ",YLGZ);
    		  
    		  float GWJLJT =0;
	    	  gdzdz = bo.getString("GWJLJT");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  GWJLJT = Float.valueOf("0");
    		  }else{
    			  GWJLJT = Float.valueOf(gdzdz);
    		  }
    		  bo.set("GWJLJT",GWJLJT);
    		  
    		  float QTJJ =0;
	    	  gdzdz = bo.getString("QTJJ");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  QTJJ = Float.valueOf("0");
    		  }else{
    			  QTJJ = Float.valueOf(gdzdz);
    		  }
    		  bo.set("QTJJ",QTJJ);
    		  
    		  
    		  
	    	  
	    	  float JXJS = 0;
	    	  float JXKHJS = 0;
	    	  float fl = 0;
	    	  
	    	  float SJCQTS = 0 ;//实际出勤天数
	    	  gdzdz = bo.getString("SJCQTS");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  SJCQTS = Float.valueOf("0");
    		  }else{
    			  SJCQTS = Float.valueOf(gdzdz);
    		  }
    		  bo.set("SJCQTS",SJCQTS);
	    	  float CQTS = 0 ;//出勤天数
	    	  gdzdz = bo.getString("CQTS");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  CQTS = Float.valueOf("0");
    		  }else{
    			  CQTS = Float.valueOf(gdzdz);
    		  }
    		  bo.set("CQTS",CQTS);
	    	  //来源于绩效考核基数数据
	    	  //判断本条是否为特殊记录，如果为特殊记录需要取日产量表中绩效工资相关信息
	    	  if(SFTSJL!=null && "1".equals(SFTSJL) ){
	    		  sql = "select a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH,b.JLJJ JXJS, count(*) CNT,sum(RJSJE) JXKHJS from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_M+" a,"
	          			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX+" b,"
	          			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" c"
	          			+ "	where a.bindid=b.bindid and c.bindid=b.id and a.NF='"+ZXNF+"' and a.YF='"+ZXYF+"' "
	          					+ "	and a.GSBM='"+SYGSBM+"' and c.RYZH='"+bo.getString("RYZH")+"' and b.GXBM='"+bo.getString("GXBM")+"' and b.GWBM='"+bo.getString("GWBM")+"' and a.isend=1"
	          			+ " group by a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH,b.JLJJ";
	    		  gdzdz = DBSql.getString(sql,"JXJS");
	    		  if(gdzdz==null || "".equals(gdzdz)){
	    			  JXJS = Float.valueOf("0");
	    		  }else{
	    			  JXJS = Float.valueOf(gdzdz);
	    		  }
	    		  gdzdz = DBSql.getString(sql,"JXKHJS");
	    		  if(gdzdz==null || "".equals(gdzdz)){
	    			  JXKHJS = Float.valueOf("0");
	    		  }else{
	    			  JXKHJS = Float.valueOf(gdzdz);
	    		  }
	    		  
	    		  
	    	  }else{
	    		  
//	 	    	 //绩效基数（来源于：绩效工资基数设定中“绩效工资基数”——非操作岗，岗位定额设定中“月定额”——操作岗）
	 	    	  sql = "select count(*) CNT from (select GSMC,GSBM,GWMC,GWBM,JLJJ JXJS,'1' Fl from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" "
	 	    	  		+ "	where ZT='1' and GXBM='"+bo.getString("GXBM")+"'"
	 	    	  		+ "	and GWBM='"+bo.getString("GWBM")+"' and  GSBM='"+SYGSBM+"'"
	 	    	  		+ "	UNION select GSMC,GSBM,GWMC,GWBM,JXGZJS JXJS,'2' Fl from "+PerformanceConstant.TAB_JXGL_FCZGZJS+" "
	 	    	  		+ "	where GSBM='"+SYGSBM+"' "
	 	    	  		+ "	and GWBM='"+bo.getString("GWBM")+"') ";
	 	    	  int CNT = 0;
	 	    	  boolean flag = true;
	 	    	 gdzdz = DBSql.getString(sql,"CNT");
	    		  if(gdzdz!=null && !"".equals(gdzdz)){
	    			  CNT = Integer.valueOf(gdzdz);
	    			  if(CNT >1){//配置了多条岗位
	    				  bo.set("XTTSXX",bo.getString("XTTSXX")+"  岗位配置了量化岗定额也配置了非量化岗定额，系统默认岗位基数为0");
	    				  JXJS = Float.valueOf("0");
	    				  flag = false;
	    			  }
	    		  }
//	 	    	  System.out.println("======================================================================"+DBSql.getString(sql,"JXJS"));
	    		  sql = "select GSMC,GSBM,GWMC,GWBM,JLJJ JXJS,'1' Fl from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" "
		 	    	  		+ "	where ZT='1' and GXBM='"+bo.getString("GXBM")+"'"
		 	    	  		+ "	and GWBM='"+bo.getString("GWBM")+"' and  GSBM='"+SYGSBM+"'"
		 	    	  		+ "	UNION select GSMC,GSBM,GWMC,GWBM,JXGZJS JXJS,'2' Fl from "+PerformanceConstant.TAB_JXGL_FCZGZJS+" "
		 	    	  		+ "	where GSBM='"+SYGSBM+"' "
		 	    	  		+ "	and GWBM='"+bo.getString("GWBM")+"'";
	    		  if(flag){
	    			  gdzdz = DBSql.getString(sql,"JXJS");
	  	    		  if(gdzdz==null || "".equals(gdzdz)){
	  	    			  JXJS = Float.valueOf("0");
	  	    		  }else{
	  	    			  JXJS = Float.valueOf(gdzdz);
	  	    		  }
	    		  }
  	 	    	 
	    		  
//	 	    	  JXJS = Float.valueOf((DBSql.getString(sql,"JXJS")==""?"0":DBSql.getString(sql,"JXJS")));	
	 	    	 
	 	    	  fl = Float.valueOf((DBSql.getString(sql,"Fl")==""?"0":DBSql.getString(sql,"Fl")));//1为操作岗，2为非操作岗
	 	    	//绩效考核基数（1、操作岗（日产量*金额）的当月累计和，2、非操作岗（岗位月定额*实际天数/出勤天数）如果实际出勤天数大于等于出勤天数，则为岗位月定额(即：绩效基数)）
	 	    	  if(fl==1){
	 	    		 //操作岗取日产量统计和
	 	    		 sql = "select a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH,b.JLJJ JXJS, count(*) CNT,sum(RJSJE) JXKHJS from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_M+" a,"
	 	          			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX+" b,"
	 	          			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" c"
	 	          			+ "	where a.bindid=b.bindid and c.bindid=b.id and a.NF='"+ZXNF+"' and a.YF='"+ZXYF+"' "
	 	          					+ "	and a.GSBM='"+SYGSBM+"' and c.RYZH='"+bo.getString("RYZH")+"' and b.GXBM='"+bo.getString("GXBM")+"' and b.GWBM='"+bo.getString("GWBM")+"'"
	 	          			+ " group by a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH,b.JLJJ";
	 	    		  gdzdz = DBSql.getString(sql,"JXKHJS");
		    		  if(gdzdz==null || "".equals(gdzdz)){
		    			  JXKHJS = Float.valueOf("0");
		    		  }else{
		    			  JXKHJS = Float.valueOf(gdzdz);
		    		  }
		    		  
//	 	    		  JXKHJS = Float.valueOf((DBSql.getString(sql,"JXKHJS")==""?"0":DBSql.getString(sql,"JXKHJS"))); 
	 	    	  }else{
	 	    		  //判断非量化岗是否维护了人员的奖金基数
	 	    		  sql = "select bindid from "+PerformanceConstant.TAB_JXGL_FCZGZJS+"  where GSBM='"+SYGSBM+"' "
		 	    	  		+ "	and GWBM='"+bo.getString("GWBM")+"'";
	 	    		  flhgbindid = DBSql.getString(sql,"bindid");
	 	    		  sql = "select count(*) cnt from "+PerformanceConstant.TAB_JXGL_FCZGZJS_S+" where bindid='"+flhgbindid+"' and RYZH='"+bo.getString("RYZH")+"'";
	 	    		  int cnt = Integer.valueOf(DBSql.getString(sql,"cnt"));
	 	    		  if(cnt>0){//维护了人员奖金基数,得到奖金基数赋值
	 	    			  sql = "select JXGZJS JXJS from "+PerformanceConstant.TAB_JXGL_FCZGZJS_S+" where bindid='"+flhgbindid+"' and RYZH='"+bo.getString("RYZH")+"'";
	 	    			  gdzdz = DBSql.getString(sql,"JXJS");
			    		  if(gdzdz==null || "".equals(gdzdz)){
			    			  JXJS = Float.valueOf("0");
			    		  }else{
			    			  JXJS = Float.valueOf(gdzdz);
			    		  }

	 	    		  }
	 	    		  if(SFCYJS.equals("否")){
	 	    			 JXKHJS = JXJS;
	 	    		  }else{
	 	    			  if(CQTS!=0){
	 	    				 JXKHJS = JXJS*SJCQTS/CQTS; 
	 	    			  }
	 	    			 
	 	    		  }
	 	    	  }
	    	  }
	    	
	    	  
//	    	  JXKHJS
	    	
	    	  bo.set("JXJS", JXJS);
	    	  bo.set("JXKHJS", JXKHJS);
	    	 
//	    	  JJJS//奖金基数(绩效考核基数*绩效考核流程系数)
	    	  float jjkhxs = 0;
	    	  //来源于绩效考核流程当月
	    	  sql = "select JXXS from "+PerformanceConstant.TAB_JXGL_JXKH_M+" "
	    	  		+ "	where YEAR='"+ZXNF+"' and MONTH='"+ZXYF+"' and BKHRZH='"+bo.getString("RYZH")+"'";
	    	  gdzdz = DBSql.getString(sql,"JXXS");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  jjkhxs = Float.valueOf("1");
				  bo.set("XTTSXX",bo.getString("XTTSXX")+"  没有查找到人员对应的考核结果数据，系统默认考核系数结果为1"); 
    		  }else{
    			  if("0".equals(gdzdz)){
    				  bo.set("XTTSXX",bo.getString("XTTSXX")+"  查找到人员对应的考核结果数据考核系数结果为0");  
    			  }
    			  jjkhxs = Float.valueOf(gdzdz);
    		  }
    		  bo.set("JXKHXS", jjkhxs);
//	    	  jjkhxs =  Float.valueOf((DBSql.getString(sql,"JXXS")==null?"0":DBSql.getString(sql,"JXXS")));
	    	  
	    	  float JJJS = JXKHJS * jjkhxs;
	    	  bo.set("JJJS", JJJS);
	    	  
//	    	  ZSYSJE//折算延时金额（绩效基数/出勤天数/7.5小时*工作延时时间）
	    	  float GZYS =0;
	    	  gdzdz = bo.getString("GZYS");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  GZYS = Float.valueOf("0");
    		  }else{
    			  GZYS = Float.valueOf(gdzdz);
    		  }
    		  bo.set("GZYS",GZYS);
//	    	  float GZYS = Float.valueOf(bo.getString("GZYS")) ;//工作延时时间
    		  float ZSYSJE = 0;
    		  if(CQTS!=0){
    			  ZSYSJE = (float) (JXJS/CQTS/7.5*GZYS);
    		  }
	    	  
	    	  bo.set("ZSYSJE", ZSYSJE);

	    	  //YSJT延时津贴 = 5元/小时 * 延时时间
	    	  float YSJT = 5 * GZYS;
	    	  bo.set("YSJT", YSJT);
	    	  
//	    	  判断上个人员账号与本条账号是否相同
	    	  if(ryzhold!=null && ryzhold.equals(bo.getString("RYZH"))){//等于为上一条已赋值人员不需要再赋值
	    		  //实发奖金（生产系数奖金+岗位激励津贴）
		    	  float SFGZ = Float.valueOf(bo.getString("GWJLJT"))+Float.valueOf(bo.getString("QTJJ"));
		    	  
		    	  bo.set("SFGZ", SFGZ);//此处为临时存储，为固定奖金部分（包含：岗位激励津贴）
//		    	  bo.set("INITIAL_QUANTITY", bo.get("QUANTITY"));//设置初始数量
		    	//创建奖金维护明细并关联视图
		    	  SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJWH_S, bo, processInstance, user);
		    	  ryzhold = bo.getString("RYZH");
		    	  bo = null;
		    	  
		    	  continue;
	    	  }
//	    	  DBJ//达标奖（来源于消耗方案制定流程岗位总金额/岗位人数）
	    	  GWBM = bo.getString("GWBM");//获得岗位编码
	    	  //通过公司编码、岗位编码、申请日期在工资月份内过滤，按岗位编码分组统计“奖励金额”之和
	    	  sql = "select sum(JLJE) JLJE from "+XhfakhConstant.TAB_GYGL_JJCBJL_M+" a, "+XhfakhConstant.TAB_GYGL_JJCBJL_MC+" b where  a.bindid = b.bindid "
	    	  		+ "	and b.GWBM='"+GWBM+"' and to_date(a.SQRQ,'yyyy-mm-dd') between to_date('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd') and last_day(trunc(TO_DATE('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd'),'MONTH')) "
	    	  		+ "	and a.GSBH='"+SYGSBM+"' and a.isend=1"
	    	  		+ "	group by b.GWBM";
	    	  float JLJE = 0;
	    	  try {
				gdzdz = DBSql.getString(sql,"JLJE");
				  if(gdzdz==null || "".equals(gdzdz)){
					  JLJE = Integer.valueOf("0");
				  }else{
					  JLJE = Integer.valueOf(gdzdz);
				  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				JLJE = 0;
			}
	    	 
	    	  
	    	 
	    	  
	    	  int GWYGS = 0;//查询本功能岗位下人员
	    	  //查询本流程实例功能岗位下人员，统计岗位下员工数（临时借调到岗位下人员）,通过公司编码，岗位编码过滤
	    	  sql = "select count(*) GWYGS from BO_DY_XCGL_JJLR_M a,"
	    	  		+ "	BO_DY_XCGL_JJLR_S b "
	    	  		+ "	where a.bindid =b.bindid and a.SYGSBM='"+SYGSBM+"'"
	    	  				+ "	 and a.ZXNF='"+ZXNF+"' and a.ZXYF='"+ZXYF+"' and b.GWBM='"+GWBM+"' and a.bindid='"+processInstance.getId()+"'";
	    	  gdzdz = DBSql.getString(sql,"GWYGS");
    		  if(gdzdz==null || "".equals(gdzdz)){
    			  GWYGS = Integer.valueOf("0");
    		  }else{
    			  GWYGS = Integer.valueOf(gdzdz);
    		  }
//	    	  GWYGS = Integer.valueOf((DBSql.getString(sql,"GWYGS")==""?"0":DBSql.getString(sql,"GWYGS"))); 
	    	  float DBJ = 0;
	    	  if(GWYGS != 0){//判断是否查询到岗位下人员，如果人员为0不用赋值
	    		  DBJ = JLJE/GWYGS;
	    	  }
	    	  bo.set("DBJ", DBJ);
	    	  //嘉奖否定只对本公司下产生的嘉奖否定做处理（包含其他公司员工临时到本公司工作产生的嘉奖否定）
//	    	  KF//扣罚（奖惩流程数据当月数据类型为扣罚金额之和）
	    	  float KF = 0;
	    	  //查询奖惩流程数据当月数据类型为扣罚金额之和------------------
	    	  sql = "select sum(JCJE)  KF from BO_DY_JXGL_JCTZ "
	    	  		+ "	where GSBM='"+SYGSBM+"' and  BKHRZH='"+bo.getString("RYZH")+"' and (JCLX='0' or JCLX='3')"
	    	  		+ "	and to_date(ZXRQ,'yyyy-mm-dd') between to_date('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd') "
	    	  		+ "	and last_day(trunc(TO_DATE('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd'),'MONTH')) and isend=1 and zt='1'";
	    	  try {
				gdzdz = DBSql.getString(sql,"KF");
				  if(gdzdz==null || "".equals(gdzdz)){
					  KF = Float.valueOf("0");
				  }else{
					  KF = Float.valueOf(gdzdz);
				  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				KF = 0;
			}
//	    	  KF = Float.valueOf((DBSql.getString(sql,"KF")==null?"0":DBSql.getString(sql,"KF")));
	    	  bo.set("KF", KF);
	    	  //集团嘉奖否定只对本公司下产生的嘉奖否定做处理（包含其他公司员工临时到本公司工作产生的嘉奖否定）
//	    	  KF//集团扣罚（奖惩流程数据当月数据类型为扣罚金额之和）
	    	  float JTKF = 0;
	    	  //查询奖惩流程数据当月数据类型为扣罚金额之和------------------
	    	  sql = "select sum(JCJE)  JTKF from BO_DY_JXGL_GSJC "
	    	  		+ "	where GSBM='"+SYGSBM+"' and  BJCRZH='"+bo.getString("RYZH")+"' and (JCLX='0' or JCLX='3')"
	    	  		+ "	and YEAR = '"+ZXNF+"' "
	    	  		+ "	and MONTH = '"+ZXYF+"' and isend=1 and zt='1'";
	    	  try {
				gdzdz = DBSql.getString(sql,"JTKF");
				  if(gdzdz==null || "".equals(gdzdz)){
					  JTKF = Float.valueOf("0");
				  }else{
					  JTKF = Float.valueOf(gdzdz);
				  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				JTKF = 0;
			}
//	    	  KF = Float.valueOf((DBSql.getString(sql,"KF")==null?"0":DBSql.getString(sql,"KF")));
	    	  bo.set("JTKF", JTKF);
	    	  //扣罚总金额=公司扣罚+集团扣罚
	    	  float KFZE = KF+JTKF;
	    	  bo.set("KFZE", KFZE);
	    	  
//	    	  JJ//嘉奖（奖惩流程数据当月数据类型为奖励金额之和）
	    	  float JJ = 0;
	    	//查询奖惩流程数据当月数据类型为嘉奖金额之和------------------
	    	  
	    	  sql = "select sum(JCJE) KF from BO_DY_JXGL_JCTZ "
		    	  		+ "	where GSBM='"+SYGSBM+"' and  BKHRZH='"+bo.getString("RYZH")+"' and (JCLX='1' or JCLX='2')"
		    	  		+ "	and to_date(ZXRQ,'yyyy-mm-dd') between to_date('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd') "
		    	  		+ "	and last_day(trunc(TO_DATE('"+ZXNF+"-"+ZXYF+"-01','yyyy-mm-dd'),'MONTH')) and isend=1 and zt='1'";
	    	  try {
				gdzdz = DBSql.getString(sql,"KF");
				  if(gdzdz==null || "".equals(gdzdz)){
					  JJ = Float.valueOf("0");
				  }else{
					  JJ = Float.valueOf(gdzdz);
				  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				JJ = 0;
			}
//	    	  JJ = Float.valueOf((DBSql.getString(sql,"KF")==null?"0":DBSql.getString(sql,"KF")));
	    	  
	    	  bo.set("JJ", JJ);
	    	  
	    	  
//	    	   JJ//集团嘉奖（奖惩流程数据当月数据类型为奖励金额之和）
	    	  float JTJJ = 0;
	    	//查询奖惩流程数据当月数据类型为嘉奖金额之和------------------
	    	 
	    	  
	    	  sql = "select sum(JCJE)  JTJJ from BO_DY_JXGL_GSJC "
		    	  		+ "	where GSBM='"+SYGSBM+"' and  BJCRZH='"+bo.getString("RYZH")+"' and (JCLX='1' or JCLX='2')"
		    	  		+ "	and YEAR = '"+ZXNF+"' "
		    	  		+ "	and MONTH = '"+ZXYF+"' and isend=1 and zt='1'";
	    	  try {
				gdzdz = DBSql.getString(sql,"JTJJ");
				  if(gdzdz==null || "".equals(gdzdz)){
					  JTJJ = Float.valueOf("0");
				  }else{
					  JTJJ = Float.valueOf(gdzdz);
				  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				JTJJ = 0;
			}
//	    	  JJ = Float.valueOf((DBSql.getString(sql,"KF")==null?"0":DBSql.getString(sql,"KF")));
	    	  
	    	  bo.set("JTJJ", JTJJ);
	    	  
	    	  //嘉奖总金额=公司嘉奖+集团嘉奖
	    	  float JJZE = JJ+JTJJ;
	    	  bo.set("JJZE", JJZE);
	    	  
	    	  
//	    	  SFGZ//实发奖金（生产系数奖金+岗位激励津贴+达标奖+嘉奖总额+其他-扣款总额） + 延时津贴
	    	  float SFGZ = Float.valueOf(bo.getString("GWJLJT"))+Float.valueOf(bo.getString("QTJJ"))+DBJ+JJZE-KFZE + YSJT;
	    	  
	    	  bo.set("SFGZ", SFGZ);//此处为临时存储，为固定奖金部分（包含：岗位激励津贴+达标奖+嘉奖+其他-扣款）
//	    	  bo.set("INITIAL_QUANTITY", bo.get("QUANTITY"));//设置初始数量
	    	//创建奖金维护明细并关联视图
	    	  SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJWH_S, bo, processInstance, user);
	    	  bo = null;
	    	  
	      }
	      
	      //计算生产系数、实发奖金等
	      //获得刚创建的奖金维护视图子表数据
	      gridData = SDK.getBOAPI().query(ProductPlanConstant.TAB_XCGL_JJWH_S, true).addQuery("bindid =", processInstance.getId()).list();
	      
	      
//    	  SCXS//生产系数（（奖金包总数-基本工资和本表所有固定额度）/个人（奖金基数+折算延时金额）之和）
    	  sql = "select ZJBJE from "+ProductPlanConstant.TAB_XCGL_JJB_M+" a ,"+ProductPlanConstant.TAB_XCGL_JJB_S+" b "
    	  		+ "	where a.bindid = b.bindid and a.SYGSBM ='"+SYGSBM+"' and b.NF='"+ZXNF+"' and b.YF='"+ZXYF+"' ";
//    	  Float.valueOf(DBSql.getString(sql,"ZJBJE")!=null?DBSql.getString(sql,"ZJBJE"):"0")
    	  float ZJBJE = 0;//获得奖金包
    	  try {
			gdzdz = DBSql.getString(sql,"ZJBJE");
			  if(gdzdz==null || "".equals(gdzdz)){
				  ZJBJE = Float.valueOf("0");
			  }else{
				  ZJBJE = Float.valueOf(gdzdz);
			  }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ZJBJE = 0;
		}
    	  //统计奖金维护子表中应领工资之和、实发奖金临时存储（固定奖金部分）之和、奖金基数之和、折算延时金额之和
    	  sql = "select sum(YLGZ) YLGZZH, sum(SFGZ) SFGZLSZH,sum(JJJS) JJJSZH,sum(ZSYSJE) ZSYSJEZH  from "
    	  		+ "	"+ProductPlanConstant.TAB_XCGL_JJWH_S+" where bindid='"+processInstance.getId()+"' ";
    	  float YLGZZH = 0;//应领工资之和
    	  try {
			gdzdz = DBSql.getString(sql,"YLGZZH");
			  if(gdzdz!=null && !"".equals(gdzdz)){
				  YLGZZH = Float.valueOf(gdzdz);
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			YLGZZH = 0;
		}
    	  float SFGZLSZH = 0;//实发奖金临时存储（固定奖金部分）之和
    	  try {
			gdzdz = DBSql.getString(sql,"SFGZLSZH");
			  if(gdzdz!=null && !"".equals(gdzdz)){
				  SFGZLSZH = Float.valueOf(gdzdz);
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			SFGZLSZH = 0;
		}
    	  float JJJSZH = 0;//奖金基数之和
    	  try {
			gdzdz = DBSql.getString(sql,"JJJSZH");
			  if(gdzdz!=null && !"".equals(gdzdz)){
				  JJJSZH = Float.valueOf(gdzdz);
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			JJJSZH = 0;
		}
    	  float ZSYSJEZH = 0;//折算延时金额之和
    	  try {
			gdzdz = DBSql.getString(sql,"ZSYSJEZH");
			  if(gdzdz!=null && !"".equals(gdzdz)){
				  ZSYSJEZH = Float.valueOf(gdzdz);
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			ZSYSJEZH = 0;
		}
    	  
    	  //判断总奖金包是否小于实发工资固定部分（奖金包总数-基本工资和本表所有固定额度），如果小于需要提示用户可能存在奖金包太小不够发放工资情况
    	  if((ZJBJE-YLGZZH-SFGZLSZH)<0){
    		  
    		  SDK.getProcessAPI().delete(processInstance, user);
    		  throw new BPMNError("0001","奖金包总数已小于所有员工（基本工资+固定奖金额度）之和，请查看是否正确维护"+ZXNF+"年"+ZXYF+"月奖金包");
    	  }
    	  if((JJJSZH+ZSYSJEZH)<=0){
    		  SDK.getProcessAPI().delete(processInstance, user);
    		  throw new BPMNError("0001","所有员工（奖金基数+折算延时金额）之和为0，请查看是否已做"+ZXNF+"年"+ZXYF+"月绩效考核及填写工作延时");
    	  }
    	  float SCXS = 0;
    	  if((JJJSZH+ZSYSJEZH)!=0){
    		  SCXS = (ZJBJE-YLGZZH-SFGZLSZH)/(JJJSZH+ZSYSJEZH);//生产系数（（奖金包总数-基本工资和本表所有固定额度）/个人（奖金基数+折算延时金额）之和）
    	  }
    	  
    	  
    	  for(int i=0;i<gridData.size();i++){
    		  
    		  bo = gridData.get(i);
//        	  SCXSJJ//生产系数奖金（（奖金基数+折算延时金额）*生产系数）
        	  float SCXSJJ = (Float.valueOf(bo.getString("JJJS")) + Float.valueOf(bo.getString("ZSYSJE")) ) * SCXS;
//        	  SFGZ//实发奖金（生产系数奖金+岗位激励津贴+达标奖+嘉奖+其他-扣款）
        	  float SFGZ = SCXSJJ + Float.valueOf(bo.getString("SFGZ"));//实发奖金为（生产系数奖金 + 实发奖金临时存储（固定奖金部分））
    		  
    		  
    		  sql = "update "+ProductPlanConstant.TAB_XCGL_JJWH_S+" set SCXS="+SCXS+",SCXSJJ="+SCXSJJ+",SFGZ="+SFGZ+" "
    		  		+ "	where ID='"+bo.getString("ID")+"'";
    		  DBSql.update(sql);
    		  bo = null;
    	  }

	    
	     
	      formData = null;
	      user = null;
	      bindid = null;
	      gridData = null;
	      
	      SYGSBM = null;//得到公司编码
	      ZXNF = null;//得到年份
	      ZXYF = null;//得到月份
	      SFCYJS = null;
	      sql = null;
	      GWBM = null;//子表岗位编码
	      processInstance = null;
	      gdzdz = null;
	      ryzhold = null;
	      flhgbindid = null;
//        
//      //创建流程实例
//        ProcessInstance processInst = SDK.getProcessAPI().createProcessInstance(processDefId, processBusinessKey,uid,createUserDeptId,createUserRoleId,title,vars);
//        //从默认的开始事件启动流程
//        List<TaskInstance> tasks = SDK.getProcessAPI().start(processInst, null).fetchActiveTasks();
//
//        //自然结束流程实例时（最后一个任务）
//        SDK.getTaskAPI().completeTask(tasks.get(0), UserContext.fromUID(startUser), true);
//
//        //或者终止一个流程实例时
//        SDK.getProcessAPI().terminate(processInst,UserContext.fromUID(terminateUser));
	      bo = null;
	      
	     

        return true;
    }

}
