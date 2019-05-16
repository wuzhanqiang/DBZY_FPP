package com.nepharm.apps.fpp.is.ehr.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import kingdee.bos.webapi.client.K3CloudApiClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.is.ehr.constant.SynchronousHRConstant;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;
import com.nepharm.apps.fpp.is.k3.dao.EncapsulationProductionOrderJson;
import com.nepharm.apps.fpp.util.BOUtil;
import com.nepharm.apps.fpp.util.DateUtil;
/**
 * 调用接口同步第三方数据到系统
 * @author zhangjh
 *
 */
public class HRUtil {


	
	/**
	 * 调用接口同步第三方数据到系统，此方法用于基础数据全同步。
	 * @param boname //我方系统表名
	 * @param msg //返回的错误信息
	 * @throws Exception
	 */
	
	public void synchronousTheThirdPartyData(String boname,StringBuffer msg) throws Exception{
		//通过用户传入的表名称查询维护信息对应视图
		String sql = "select XTBM,HRSTM,HRSTBT,XTBBT,BZ,CCUUID,BINDID,LCUUID,TJYJ,ZDYSQL from "+SynchronousHRConstant.TAB_JCXX_HRBDZGXSZ_M+" where XTBM='"+boname+"'";
		String XTBM = DBSql.getString(sql,"XTBM");//系统表名
		String HRSTM = DBSql.getString(sql,"HRSTM");
		String XTBBT = DBSql.getString(sql,"XTBBT");
		String CCUUID = DBSql.getString(sql,"CCUUID");
		String bindid = DBSql.getString(sql,"BINDID");
		String LCUUID = DBSql.getString(sql,"LCUUID");
		String TJYJ = DBSql.getString(sql,"TJYJ");
		String ZDYSQL = DBSql.getString(sql,"ZDYSQL");
		String cxstql = null;
		String wheresql = null;
		//获得系统时间
		Date date = new Date();
		//时间进行格式化
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(date);
		
		StringBuffer sb =null;
		List<BO> dzgzwhzblist = null;
		BO bo = null;
		List<BO> dsffhjglist = null;
		BO dsffhjgbo = null;
		BO crxtbo = null;
		List<BO> lssjlist = null;
		BO lssjbo = null;
		String HRGSMC = null;
		String HRGSBM = null;
		ProcessInstance processInstance = null;
		UserContext user = null;
		if(XTBM==null || "".equals(XTBM) || HRSTM==null || "".equals(HRSTM) 
				|| CCUUID==null || "".equals(CCUUID) || bindid==null || "".equals(bindid)){//如果没有维护无法进行同步
			msg.append("需要维护系统表与第三方系统对照关系表");
			return;
		}else{
			 //查询对照关系维护表通过子表组合语句
			 sql = "select SJL,HRSJL from "+SynchronousHRConstant.TAB_JCXX_HRBDZGXSZ_S+" where bindid='"+bindid+"'";
			 dzgzwhzblist = BOUtil.queryEncapsulationData(sql);
			 if(dzgzwhzblist.size() == 0){//说明没有维护
				 msg.append(HRSTM+"需要在维护系统表列名与第三方系统表列名对照关系");
				 return; 
			}
			//判断是否使用用户自定义sql
			if(ZDYSQL!=null && !"".equals(ZDYSQL)){
				cxstql = ZDYSQL;
				
				
			}else{
				//查询维护表组合SQL语句
				 sb = new StringBuffer();
				 sb.append("select  ");
				 
				 
				 for(int i=0;i<dzgzwhzblist.size();i++){
					 bo = dzgzwhzblist.get(i);
					 if(dzgzwhzblist.size()==(i+1)){//为最后一条数据
						 sb.append(bo.getString("HRSJL")+" ");
					 }else{
						 sb.append(bo.getString("HRSJL")+",");
					 }
					 
				 }
				
				 
				 sb.append(" from "+HRSTM+"  ");
				 if(TJYJ!=null && !"".equals(TJYJ)){
					 sb.append(TJYJ);//有条件按条件查询，没有全查
				 }
				 cxstql = sb.toString();
			}
			if(XTBM.equals(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB)){//如果为工资子表,增加上月查询条件，例如201806样式
				cxstql = cxstql+" and yearmoth='"+dateString.substring(0,4)+dateString.substring(5,7)+"'";//得到当月的年份月份进行组合，例：201806
			}
			System.out.println(cxstql);
			//查询第三方系统表数据
			 dsffhjglist = BOUtil.selectCCEncapsulationList(CCUUID,cxstql);
			 if(dsffhjglist.size()>0){
				 user = UserContext.fromUID("admin");
				 //删除历史数据
				//判断是否为视图流程
				 if(LCUUID!=null &&!"".equals(LCUUID)){//流程视图
					 
					 //查询数据表所有数据
					 //判断是否同步工资子表
					 if(XTBM.equals(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB)){//如果为工资子表，查询主表数据删除本月创建的数据
						 sql = "select BINDID from "+SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB_M+" "
						 		+ "	where to_char(CREATEDATE,'yyyy')=to_char(sysdate,'yyyy') "
						 		+ "	and to_char(CREATEDATE, 'MM')=to_char(sysdate, 'MM')";
					 }else{//不是用配置表
						 if(ZDYSQL!=null && !"".equals(ZDYSQL)){//判断是否为自定义sql
							 //判断是否有where条件
							 if(ZDYSQL.indexOf("where")>0){
								 wheresql = ZDYSQL.substring(ZDYSQL.indexOf("where"));
//								 System.out.println("========================================"+wheresql);
							 }else if(ZDYSQL.indexOf("WHERE")>0){
								 wheresql = ZDYSQL.substring(ZDYSQL.indexOf("WHERE"));
								 
							 }
							 if(wheresql!=null && !"".equals(wheresql)){
								 //循环遍历where条件中是否包含字段名，包含替换成系统表字段名
								 for(int j=0;j<dzgzwhzblist.size();j++){
									 bo = dzgzwhzblist.get(j);
									 if(wheresql.indexOf(bo.getString("HRSJL"))>0){
										 wheresql = wheresql.replaceAll(bo.getString("HRSJL"), bo.getString("SJL"));
//										 System.out.println("========================================"+bo.getString("SJL"));
//										 System.out.println("========================================"+bo.getString("HRSJL"));
									 }
								 }
								 sql =  "select BINDID from "+XTBM+" "+wheresql;
							 }else{
								 sql =  "select BINDID from "+XTBM; 
							 }
							
							 
							
//							 System.out.println("========================================"+sql);
						 }else{
							 if(TJYJ!=null && !"".equals(TJYJ)){//判断是否有where语句
								 wheresql = TJYJ;
								 if(wheresql!=null && !"".equals(wheresql)){
									 //循环遍历where条件中是否包含字段名，包含替换成系统表字段名
									 for(int j=0;j<dzgzwhzblist.size();j++){
										 bo = dzgzwhzblist.get(j);
										 if(wheresql.indexOf(bo.getString("HRSJL"))>0){
											 wheresql = wheresql.replaceAll(bo.getString("HRSJL"), bo.getString("SJL"));
//											 System.out.println("========================================"+bo.getString("SJL"));
//											 System.out.println("========================================"+bo.getString("HRSJL"));
										 }
									 } 
								 }
								 sql =  "select BINDID from "+XTBM+" "+wheresql;
							 }else{
								 sql = "select BINDID from "+XTBM; 
							 }
							 
							 
						 }
						 
					 }
					 
					 lssjlist = BOUtil.queryEncapsulationData(sql);
					 for(int i=0;i<lssjlist.size();i++){
						 lssjbo = lssjlist.get(i);
						 if(lssjbo.getString("BINDID")!=null && !"".equals(lssjbo.getString("BINDID"))){
							 //获得流程实例信息
							 processInstance = SDK.getProcessAPI().getInstanceById(lssjbo.getString("BINDID"));
							 //删除流程实例
							 SDK.getProcessAPI().delete(processInstance, user); 
						 }
						 
					 }
					 
				 }else{//
					 
					 if(ZDYSQL!=null && !"".equals(ZDYSQL)){//判断是否为自定义sql
						 //判断是否有where条件
						 if(ZDYSQL.indexOf("where")>0){
							 wheresql = ZDYSQL.substring(ZDYSQL.indexOf("where"));
//							 System.out.println("========================================"+wheresql);
						 }else if(ZDYSQL.indexOf("WHERE")>0){
							 wheresql = ZDYSQL.substring(ZDYSQL.indexOf("WHERE"));
							 
						 }
						 if(wheresql!=null && !"".equals(wheresql)){
							 //循环遍历where条件中是否包含字段名，包含替换成系统表字段名
							 for(int j=0;j<dzgzwhzblist.size();j++){
								 bo = dzgzwhzblist.get(j);
								 if(wheresql.indexOf(bo.getString("HRSJL"))>0){
									 wheresql = wheresql.replaceAll(bo.getString("HRSJL"), bo.getString("SJL"));
//									 System.out.println("========================================"+bo.getString("SJL"));
//									 System.out.println("========================================"+bo.getString("HRSJL"));
								 }
							 } 
						 }
						
//						 System.out.println("========================================"+wheresql);
						 if(wheresql!=null && !"".equals(wheresql)){
							 sql = "delete  from "+XTBM+" "+wheresql; 
						 }else{
							 sql = "delete  from "+XTBM+" ";
						 }
						 
//						 sql =  "select BINDID from "+XTBM+wheresql;
					 }else{
						 if(TJYJ!=null && !"".equals(TJYJ)){//判断是否有where语句
							 wheresql = TJYJ;
							 if(wheresql!=null && !"".equals(wheresql)){
								 //循环遍历where条件中是否包含字段名，包含替换成系统表字段名
								 for(int j=0;j<dzgzwhzblist.size();j++){
									 bo = dzgzwhzblist.get(j);
									 if(wheresql.indexOf(bo.getString("HRSJL"))>0){
										 wheresql = wheresql.replaceAll(bo.getString("HRSJL"), bo.getString("SJL"));
//										 System.out.println("========================================"+bo.getString("SJL"));
//										 System.out.println("========================================"+bo.getString("HRSJL"));
									 }
								 } 
							 }
							 sql = "delete  from "+XTBM+" "+wheresql;
//							 sql =  "select BINDID from "+XTBM+wheresql;
						 }else{
							 sql = "delete  from "+XTBM;  
						 }
						 
						 
					 }
					 
					 DBSql.update(sql);//删除所有数据
				 }
				 System.out.println("=========================="+dsffhjglist.size());
				//判断是否同步工资子表
				 if(XTBM.equals(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB)){//如果为工资子表，只创建一次视图实例
					//创建视图实例
				      processInstance = SDK.getProcessAPI().createBOProcessInstance(LCUUID, "admin", "同步"+XTBBT+"数据"); 
				      crxtbo = new BO();
				      crxtbo.set("CJZH","admin");
				    //视图主表数据放入
				      SDK.getBOAPI().create(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB_M, crxtbo, processInstance, user);
				 }
				 //创建同步数据
				 for(int i=0;i<dsffhjglist.size();i++){
					 dsffhjgbo = dsffhjglist.get(i);
					 crxtbo = new BO();//需要放数据的BO
					 //循环维护表表字段名，进行数据存储转换
					 for(int j=0;j<dzgzwhzblist.size();j++){
						 bo = dzgzwhzblist.get(j);
//						 System.out.println("=========================="+bo.getString("SJL"));
//						 System.out.println("=========================="+bo.getString("HRSJL"));
//						 System.out.println("=========================="+dsffhjgbo.getString(bo.getString("HRSJL")));
						 crxtbo.set(bo.getString("SJL"),dsffhjgbo.getString(bo.getString("HRSJL")));////得到第三方表字段值放入我方系统字段名中
					 }
					 //如果为同步产品信息需要放入HR公司编码
					 if(XTBM.equals(SynchronousK3Constant.TAB_JCXX_CPXX)){
//						 HRGSBM = DBSql.getString("select BM,MC from "+SynchronousHRConstant.TAB_JCXX_DWXX+" where K3GSBM='"+crxtbo.getString("K3GSBM")+"'","BM");
//						 HRGSMC = DBSql.getString("select BM,MC from "+SynchronousHRConstant.TAB_JCXX_DWXX+" where K3GSBM='"+crxtbo.getString("K3GSBM")+"'","MC");
						 
						 HRGSBM = DBSql.getString("select GSBM,GSMC from BO_DY_JCXX_K3BMDZB where K3BM='"+crxtbo.getString("K3GSBM")+"'","GSBM");
						 HRGSMC = DBSql.getString("select GSBM,GSMC from BO_DY_JCXX_K3BMDZB where K3BM='"+crxtbo.getString("K3GSBM")+"'","GSMC");
						 if(HRGSBM!=null && !"".equals(HRGSBM)){
							 crxtbo.set("HRGSBM",HRGSBM);
						 }
						 if(HRGSMC!=null && !"".equals(HRGSMC)){
							 crxtbo.set("HRGSMC",HRGSMC);
						 }
						 
					 }
					 //判断是否需要启动视图流程
					 if(LCUUID!=null &&!"".equals(LCUUID)){
						 if(!XTBM.equals(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB)){//如果不为工资子表
							//创建视图实例
						      processInstance = SDK.getProcessAPI().createBOProcessInstance(LCUUID, "admin", "同步"+XTBBT+"数据"); 
						 }else{//如果为工资子表
							 crxtbo.set("NF",dateString.substring(0,4));//得到当月所在年份放入年份
							 crxtbo.set("YF",dateString.substring(5,7));//得到当月放入月份
							 
						 }
						  
					      //视图表数据放入
					      SDK.getBOAPI().create(XTBM, crxtbo, processInstance, user);
					 }else{
						  //创建数据信息
						  SDK.getBOAPI().createDataBO(XTBM, crxtbo,  user);
					 }
					 
				 }
			 }else{//说明没有查询到数据
				 msg.append(HRSTM+"对应第三方系统表没有数据");
				 return; 
			 }
		}

		sql = null;
		XTBM = null;//系统表名
		HRSTM = null;
		XTBBT = null;
		CCUUID = null;
		bindid = null;
		LCUUID = null;
		TJYJ = null;
		sb =null;
		dzgzwhzblist = null;
		bo = null;
		dsffhjglist = null;
		dsffhjgbo = null;
		crxtbo = null;
		lssjlist = null;
		lssjbo = null;
		processInstance = null;
		ZDYSQL = null;
		TJYJ = null;
		user = null;
		cxstql = null;
		HRGSMC = null;
		HRGSBM = null;
		wheresql = null;
		date = null;
		formatter = null;
		dateString = null;
		
		
	}
	
	
}
