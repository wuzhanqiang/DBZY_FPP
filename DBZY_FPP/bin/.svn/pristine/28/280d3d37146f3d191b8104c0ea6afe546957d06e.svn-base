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
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.sdk.local.SDK;
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
public class CreateDailyOutputData{
	
	/**
	 * 创建日产量维护流程及数据
	 *
	 */
	public static void createDailyOutputData(String SCRQ){
//		
		SimpleDateFormat sdf = null;
		Date d = null;
		//如果没有传参数日期，默认为当前系统日期
		if(SCRQ==null || "".equals(SCRQ)){
			d = new Date();  
//	        System.out.println(d);  
	        sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        SCRQ = sdf.format(d); 
		}
		String sql;
		
		//查询岗位定额设定台账（量化岗）公司信息
		sql = "select GSBM,GSMC from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" group by GSBM,GSMC";
		List<BO> gridData = BOUtil.queryEncapsulationData(sql);
		List<BO> lhgwData = null;
		BO bo = null;
		String GSBM = null;//公司编码
		String GSMC = null;//公司名称
		String MESGWBM = null;
		String USERID = null;
		String MESGSBM = null;//MES公司编码
		CreateDailyOutputData createDailyOutputData = new CreateDailyOutputData();
		ProcessInstance processInstance = null;
		String gdzdz= null;
		//String RCLL = null;
		//判断是否有公司数据
		if(gridData!=null && !gridData.isEmpty()){
			 for(int i=0;i<gridData.size();i++){
				  bo = gridData.get(i);
				  GSBM = bo.getString("GSBM");
				  GSMC = bo.getString("GSMC");
				  //查询单位信息中MES公司编码
				  sql = "select MESGSBM from  "+PerformanceConstant.TAB_JCXX_DWXX+" where BM='"+GSBM+"'";
				  //如果为空默认HR公司编码
				  MESGSBM = DBSql.getString(sql, "MESGSBM")==null?GSBM:DBSql.getString(sql, "MESGSBM");
				 
				  
				  	//得到分配人账号查询公司日产量数据分配填写人维护BO_DY_JXGL_GSRCLFPSJTXWH
					sql = "select FPRYZH,FPRY from "+PerformanceConstant.TAB_JXGL_GSRCLFPSJTXWH+" where GSBM='"+GSBM+"'";
					USERID = DBSql.getString(sql, "FPRYZH");
					if(USERID==null || "".equals(USERID)){
						USERID="admin";
					}
					bo.set("SQR",DBSql.getString(sql, "FPRY"));
					bo.set("SQRZH",USERID);
					try {
						bo.set("SCRQ",DateUtil.string2UtilDate(SCRQ));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bo.set("NF",SCRQ.substring(0,4));
					bo.set("YF",SCRQ.substring(5,7));
					//创建操作岗日产量维护流程
					processInstance = SDK.getProcessAPI(). createProcessInstance(PerformanceConstant.PROCESS_JXGL_CZGRICLWH, USERID, SCRQ+"操作岗日产量维护");
		
					SDK.getProcessAPI().start(processInstance);
					///**操作岗日产量维护-主表*/BO_DY_JXGL_CZGRICLWH_M放入数据
					SDK.getBOAPI().create(PerformanceConstant.TAB_JXGL_CZGRICLWH_M, bo, processInstance.getId(), USERID);
					
					//通过公司编码查询岗位定额设定台账（量化岗）信息
					 sql = "select * from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" where GSBM='"+GSBM+"'";
					  
					  lhgwData = BOUtil.queryEncapsulationData(sql);
				  for(int j=0;j<lhgwData.size();j++) {
					  bo = lhgwData.get(j);
					  bo.remove("UPDATEUSER");
					  bo.remove("UPDATEDATE");
					  bo.remove("CREATEUSER");
					  bo.remove("ISEND");
					  bo.remove("ORGID");
					  bo.remove("CREATEDATE");
					  bo.remove("ID");//资产入库ID
					  bo.remove("BINDID");//资产入库bindID
					  bo.remove("PROCESSDEFID");
					  
//					  //通过岗位编码查询岗位信息(BO_DY_JCXX_GWXX)中的MES岗位编码
//					  sql = "select MESGWBM from "+PerformanceConstant.TAB_JCXX_GWXX+" where BM='"+bo.getString("GWBM")+"'";
//					  MESGWBM = DBSql.getString(sql,"MESGWBM");
//					  if(MESGWBM==null || "".equals(MESGWBM)){
//						  bo.set("TBSJBCZYY",bo.getString("GWBC")+"岗位对应MES岗位信息没有维护，请联系管理员进行维护");
//						 continue;
//					  }
					  //通过MES公司编码、日期、MES工序编码查询RCL日产量统计和表（BO_DY_JJXX_TBMESRCL）
					  sql = "select sum(RCL) RCL from "+PerformanceConstant.TAB_JJXX_TBMESRCL+" where MESGSBM='"+MESGSBM+"' "
					  		+ "	and  to_char(SCRQ,'yyyy-MM-dd')='"+SCRQ+"' and GXBM='"+bo.getString("GXBM")+"'";
					  float RCLL = 0;
					  gdzdz = DBSql.getString(sql,"RCL");
		    		  if(gdzdz==null || "".equals(gdzdz)){
		    			  RCLL = Float.valueOf("0");
		    		  }else{
		    			  RCLL = Float.valueOf(gdzdz);
		    		  }

//					  RCLL = Float.valueOf((DBSql.getString(sql,"RCL")==""?"0":DBSql.getString(sql,"RCL")));
					  bo.set("RCL",RCLL);
					///**操作岗日产量维护-产量明细表*/BO_DY_JXGL_CZGRICLWH_CLMX放入数据
						SDK.getBOAPI().create(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX, bo, processInstance.getId(), USERID);
						
						//做字段主子表创建工作
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
						int GZRS = 0;
						try {
							GZRS = Integer.valueOf(bo.getString("GZRS"));
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							GZRS = 0;
						}
						createDailyOutputData.createFieldSubtableBO(bo.getString("ID"),PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX,USERID,bo.getString("GWBM"),bo.getString("GWMC"),GSBM,RCLL,bo.getString("DEDJ"),CQTS,GZRS);
						
				 
				  }
				  
				
				  
				  
			}
		}
		sdf = null;
		d = null;
		sql = null;
		gridData = null;
		lhgwData = null;
		bo = null;
		GSBM = null;//公司编码
		GSMC = null;//公司名称
		MESGWBM = null;
		USERID = null;
		MESGSBM = null;//MES公司编码
		createDailyOutputData = null;
		processInstance = null;
	}
	/**
	 * 封装日产量维护-产量分配明细（BO_DY_JXGL_CZGRICLWH_CLFPMX）数据并创建此字段子表
	 * @param boid子表主键ID
	 * @param boname 字段子表表名
	 * @param createUSERID 创建人userid
	 * @param hrgwbm HR岗位编码
	 * @param hrgwmc HR岗位名称
	 * @param FTRCL  操作岗日产量中数量
	 * @param DEDJ  岗位定额设定台账（操作岗）(BO_DY_JXGL_CZGZJSTZ)中定额单价（DEDJ）
	 * @param CQTS  月出勤天数
	 * @param ders  岗位定额人数
	 */
	public String createFieldSubtableBO(String boid,String boname,String createUSERID,String hrgwbm,String hrgwmc,String gsbm,float FTRCLS,String DEDJ,float CQTS,int ders){
		//通过岗位编码查询岗位信息(BO_DY_JCXX_GWXX)获得岗位主键
		String sql = "select HRGWPK from "+PerformanceConstant.TAB_JCXX_GWXX+" where BM='"+hrgwbm+"' and GSBM='"+gsbm+"'";
		String HRGWPK = DBSql.getString(sql,"HRGWPK");
		String TBSJBCZYY = "";
		if(HRGWPK==null || "".equals(HRGWPK)){
			TBSJBCZYY = hrgwmc+"岗位名称对应的岗位信息表中没有同步HR岗位信息，请联系管理员进行同步";
		}
		//通过岗位主键查询HR人员信息同步(BO_DY_JCXX_HRRYXXTB)获得人员编号
		sql = "select RYBM,XM from "+PerformanceConstant.TAB_JCXX_HRRYXXTB+" where SZGWPK='"+HRGWPK+"' and NO1CODE='"+gsbm+"'";
		List<BO> lhgwData = BOUtil.queryEncapsulationData(sql);
		BO bo = null;
		String USERID = null;
		if(lhgwData!=null && !lhgwData.isEmpty()){
			//日产量按照岗位人员进行平均分配
			float FTRCL = FTRCLS*ders/lhgwData.size();
			//获得岗位定额月
			float zhsj;
			try {
				zhsj = Float.valueOf(DEDJ);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				zhsj = 0;
			}
			float RJSJE = zhsj /CQTS;
			for(int i=0;i<lhgwData.size();i++){
				bo = lhgwData.get(i);
				if(bo.get("XM")==null||"".equals(bo.get("XM"))){
					TBSJBCZYY = hrgwmc+"岗位名称对应的人员信息表中没有人员姓名，请联系管理员进行同步";
				}else{
					bo.set("RYMC",bo.get("XM"));
				}
				if(bo.get("RYBM")==null||"".equals(bo.get("RYBM"))){
					TBSJBCZYY = hrgwmc+"岗位名称对应的人员信息表中没有人员编号，请联系管理员进行同步";
				}else{
					//通过人员编号查询系统用户表获得账号，如果系统人员信息没有维护账号与人员编号关系，默认账号就是人员编号
					sql = "select USERID from orguser where EXT1='"+bo.get("RYBM")+"'";
					USERID = DBSql.getString(sql,"USERID");
					if(USERID==null||"".equals(USERID)){
						sql = "select USERID from orguser where USERID='"+bo.get("RYBM")+"'";
						USERID = DBSql.getString(sql,"USERID");
						if(USERID==null||"".equals(bo.get("USERID"))){
							TBSJBCZYY = "人员编号为："+bo.get("RYBM")+"人员没有在全流程全绩效系统中维护，请联系管理员进行维护";
						}else{
							bo.set("RYZH",USERID);
						}
					}else{
						bo.set("RYZH",USERID);
					}
					
					
				}
				if(DEDJ==null||"".equals(DEDJ)){
					TBSJBCZYY = hrgwmc+"岗位名称对应的“操作岗岗位定额设定维护”中没有维护“定额单价”信息，请联系管理员进行维护";
					DEDJ="0";
				}
				
				bo.set("RJSDJ",RJSJE);
				bo.set("FTRCL",FTRCL);
				bo.set("RJSJE",FTRCL*RJSJE);
				bo.set("TBSJBCZYY",TBSJBCZYY);
				this.createFieldSubtable(boid,boname,bo,createUSERID);
				
			}
		}else{
			TBSJBCZYY = hrgwmc+"岗位名称对应的人员信息表中没有同步人员信息，请联系管理员进行同步";
		}
		
		sql = null;
		HRGWPK = null;
		TBSJBCZYY = null;
		
		lhgwData = null;
		bo = null;
		 USERID = null;
		 
		 return TBSJBCZYY;
		
		
	}
	
	/**
	 * 创建字段子表信息
	 * @param boid子表id
	 * @param boname
	 * @param bo
	 * @param USERID
	 */
	public void createFieldSubtable(String boid,String boname,BO bo,String USERID){
	
		bo.set("bindid",boid);
		SDK.getBOAPI().createDataBO(boname, bo,  UserContext.fromUID(USERID));
		String sql = "update "+boname+" set bindid='"+boid+"', RSJCQTS=1 where id='"+bo.getString("ID")+"'";
		DBSql.update(sql);
		sql = null;
		
	}
	
}
