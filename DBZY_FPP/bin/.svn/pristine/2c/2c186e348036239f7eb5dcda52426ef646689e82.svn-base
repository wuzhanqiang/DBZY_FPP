package com.nepharm.apps.fpp.biz.pem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.bean.UserBean;
import com.nepharm.apps.fpp.biz.pem.bean.GWGXBean;
import com.nepharm.apps.fpp.biz.pem.bean.JCXXBean;
import com.nepharm.apps.fpp.biz.pem.bean.JHWCBean;
import com.nepharm.apps.fpp.biz.pem.bean.JXJSBean;
import com.nepharm.apps.fpp.biz.pem.bean.SCBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.util.NumberUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * cmd页面涉及的DAO操作（绩效相关）
 * @author lizhen
 *
 */
public class PerformanceBizDao {

		/**
		 * 岗位工序信息（操作岗）
		 * @param user
		 * @return
		 */
		public JSONArray getGWGXInfo(UserBean user){
			
			String sql="select DEDJ,DEDW,GXBM,GXMC from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" where GSBM='"+user.getGsbm()+"' and GWBM='"+user.getGwbm()+"'";
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String bm=rs.getString("GXBM");
						String mc=rs.getString("GXMC");
						double dj=rs.getDouble("DEDJ");
						String dw=rs.getString("DEDW");
						GWGXBean bean = 
								new GWGXBean(
										user.getGwbm(), user.getGwmc(), user.getGsbm(), user.getGsmc(),
										bm, mc, dw, dj);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
			
		}
		/**
		 * 岗位工序信息（非操作岗）
		 * @param user
		 * @return
		 */
		public JSONObject getNGWGXInfo(UserBean user){
			JSONObject result = new JSONObject();//主表
			List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_FCZGZJS).addQuery("GWBM =", user.getGwbm()).addQuery("GSBM =", user.getGsbm()).list();
			if(list==null||list.size()==0){
				return null;
			}
			
			
			BO bo=list.get(0);
			String mc=bo.getString("GWMC");
			String je=bo.getString("JXGZJS");
			//TODO 
			String sql="select JXGZJS from BO_DY_JXGL_FCZGZJS_S where RYZH='"+user.getUid()+"'";
			String num=DBSql.getString(sql,"JXGZJS");
			if(num==null||"".equals(num)){
				num="0";
			}else{
				je=num;
			}
			
			result.put("GWMC", mc);
			result.put("GWDE", je);
			return result;
		}
		/**
		 * 获取最终奖金-量化岗
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONObject getJXZHInfo(String uid,String year,String month){
			JSONObject result = new JSONObject();
			String dateSql="select  to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), -1) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), 0) + 1), 'yyyy-mm-dd') EDATE from dual";
			String startDate=DBSql.getString(dateSql,"SDATE");
			String endDate=DBSql.getString(dateSql,"EDATE");
			
			
			String sql  = "select JXXS from "+PerformanceConstant.TAB_JXGL_JXKH_M+" where BKHRZH='"+uid+"' and ISEND=1 and month='"+month+"' and year='"+year+"'";
			String sql2="select sum(RJSJE) as JE from "+PerformanceConstant.VIEW_JXGL_RCL+" where RYZH='"+uid+"' and NF='"+year+"' and YF='"+month+"'";
			String sql3=" select SUM(JCJE) JE from "+PerformanceConstant.TAB_JXGL_JCTZ+
					" where ZT='1' and BKHRZH='"+uid+"'"+
					" and ZXRQ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and ZXRQ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and JCLX in ('0','3')";
			String sql4=" select SUM(JCJE) JE from "+PerformanceConstant.TAB_JXGL_JCTZ+
					" where ZT='1' and BKHRZH='"+uid+"'"+
					" and ZXRQ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and ZXRQ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and JCLX in ('1','2')";
			//延时津贴
			String sql5="select t2.YSJT from BO_DY_XCGL_JJWH_M t1 join BO_DY_XCGL_JJWH_S t2 on t1.bindid = t2.bindid"+
					" where t2.RYZH = '"+uid+"'"+
					" and t1.ZXNF = '"+startDate.substring(0, 4)+"'"+
					" and t1.ZXYF = '"+startDate.substring(5, 6)+"'";
			
			String xs=DBSql.getString(sql,"JXXS");
			String gwje=DBSql.getString(sql2,"JE");
			double je1=0,je2=0,je3=0;
			try {
				je1=DBSql.getDouble(sql3,"JE");
			} catch (AWSDataAccessException e) {
				je1=0;
			}
			try {
				je2=DBSql.getDouble(sql4,"JE");
			} catch (AWSDataAccessException e) {
				je2=0;
			}
			try {
				je3=DBSql.getDouble(sql5, "YSJE");
			} catch (AWSDataAccessException e) {
				je3=0;
			}
			
			if(gwje==null||"".equals(gwje)){
				gwje="0";
			}
			if(xs==null||"".equals(xs)){
				xs="1";
			}
			double je=je2-je1;
			double zje=(Double.parseDouble(gwje)*Double.parseDouble(xs)+je);
			result.put("GWJE", gwje);
			result.put("JCJE",je );
			result.put("XS", xs);
			result.put("ZJE",new DecimalFormat("#0.00").format(zje));
			result.put("YSJT", new DecimalFormat("#0.00").format(je3));
			return result;
			
		}
		/**
		 * 获取最终奖金-非量化岗
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONObject getNJXZHInfo(String uid,String year,String month,UserBean user){
			JSONObject result = new JSONObject();
			String dateSql="select  to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), -1) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), 0) + 1), 'yyyy-mm-dd') EDATE from dual";
			String startDate=DBSql.getString(dateSql,"SDATE");
			String endDate=DBSql.getString(dateSql,"EDATE");
			
			
			String sql  = "select JXXS from "+PerformanceConstant.TAB_JXGL_JXKH_M+" where BKHRZH='"+uid+"' and ISEND=1 and month='"+month+"' and year='"+year+"'";
			String sql2="select sum(RJSJE) as JE from "+PerformanceConstant.VIEW_JXGL_RCL+" where RYZH='"+uid+"' and NF='"+year+"' and YF='"+month+"'";
			String sql3=" select SUM(JCJE) JE from "+PerformanceConstant.TAB_JXGL_JCTZ+
					" where ZT='1' and BKHRZH='"+uid+"'"+
					" and ZXRQ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and ZXRQ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and JCLX in ('0','3')";
			String sql4=" select SUM(JCJE) JE from "+PerformanceConstant.TAB_JXGL_JCTZ+
					" where ZT='1' and BKHRZH='"+uid+"'"+
					" and ZXRQ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and ZXRQ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') "+
					" and JCLX in ('1','2')";
			
			
			/*公司 -制度惩罚、否定*/
			String sql5="select SUM(JCJE) JE from BO_DY_JXGL_GSJC where ZT='1' and BJCRZH='"+uid+"' and YEAR='"+year+"' and MONTH='"+month+"' and JCLX in ('0','3')";
			/*公司 -制度奖励、嘉奖*/
			String sql6="select SUM(JCJE) JE from BO_DY_JXGL_GSJC where ZT='1' and BJCRZH='"+uid+"' and YEAR='"+year+"' and MONTH='"+month+"' and JCLX in ('1','2')";
			//延时津贴
			String sql7="select t2.YSJT from BO_DY_XCGL_JJWH_M t1 join BO_DY_XCGL_JJWH_S t2 on t1.bindid = t2.bindid"+
					" where t2.RYZH = '"+uid+"'"+
					" and t1.ZXNF = '"+startDate.substring(0, 4)+"'"+
					" and t1.ZXYF = '"+startDate.substring(5, 6)+"'";
			
			String gwde="0";//非量化岗岗位定额
			List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_FCZGZJS).addQuery("GWBM =", user.getGwbm()).addQuery("GSBM =", user.getGsbm()).list();
			if(list==null||list.size()==0){
			}else{
				BO bo=list.get(0);
				String sql8="select JXGZJS from BO_DY_JXGL_FCZGZJS_S where RYZH='"+user.getUid()+"'";
				String num=DBSql.getString(sql8,"JXGZJS");
				if(num==null||"".equals(num)){
					gwde=bo.getString("JXGZJS");
				}else{
					gwde=num;
				}
			}
			
			
			
			String xs=DBSql.getString(sql,"JXXS");//系数
			String gwje=DBSql.getString(sql2,"JE");//量化工资
			double je1=0,je2=0,je3=0,je4=0,je5=0,je6=0;
			try {
				je1=DBSql.getDouble(sql3,"JE");
			} catch (AWSDataAccessException e) {
				je1=0;
			}
			try {
				je2=DBSql.getDouble(sql4,"JE");
			} catch (AWSDataAccessException e) {
				je2=0;
			}
			try {
				je3=Double.parseDouble(gwde);
			} catch (Exception e) {
				je3=0;
			}
			try {
				je4=DBSql.getDouble(sql5,"JE");
			} catch (AWSDataAccessException e) {
				je4=0;
			}
			try {
				je5=DBSql.getDouble(sql6,"JE");
			} catch (AWSDataAccessException e) {
				je5=0;
			}
			try {
				je6=DBSql.getDouble(sql7, "YSJE");
			} catch (AWSDataAccessException e) {
				je6=0;
			}
			
			if(gwje==null||"".equals(gwje)){
				gwje="0";
			}
			if(xs==null||"".equals(xs)){
				xs="1";
			}
			double je=je2-je1+je5-je4;
			double zje=(je3*Double.parseDouble(xs)+je+Double.parseDouble(gwje));
			result.put("GWDE", je3);
			result.put("GWJE", gwje);
			result.put("JCJE",je );
			result.put("XS", xs);
			result.put("ZJE",new DecimalFormat("#0.00").format(zje));
			result.put("YSJT", new DecimalFormat("#0.00").format(je6));
			return result;
			
		}
		/**
		 * 获取考核成绩（通用）
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONObject getKHDFInfo(String uid,String year,String month,String sid,UserBean user){
			JSONObject result = new JSONObject();
			String sql  = "select BINDID,KPIFS,SZFS,JXFS,JXXS,JXJB from "+PerformanceConstant.TAB_JXGL_JXKH_M+" where BKHRZH='"+uid+"' and ISEND=1 and month='"+month+"' and year='"+year+"'";
			String bindId= DBSql.getString(sql,"BINDID");
			String kpi="——";
			try {
				kpi = DBSql.getString(sql,"KPIFS");
			} catch (AWSDataAccessException e) {
			}
			String sz="——";;
			try {
				sz = DBSql.getString(sql,"SZFS");
			} catch (AWSDataAccessException e) {
			}
			String fs="——";;
			try {
				fs = DBSql.getString(sql,"JXFS");
			} catch (AWSDataAccessException e) {
			}
			String xs="1.0";;
			try {
				xs = DBSql.getString(sql,"JXXS");
			} catch (AWSDataAccessException e) {
			}
			String jb="——";;
			try {
				jb = DBSql.getString(sql,"JXJB");
			} catch (AWSDataAccessException e) {
			}
			String formId=PerformanceConstant.FORM_JXGL_JXKH_FLH;
			if(user.isOper()){
				formId=PerformanceConstant.FORM_JXGL_JXKH_LH;
			}
			String url=SDK.getFormAPI().getDWFormURL("", sid, bindId, formId, 9);
			
			result.put("URL",url);
			result.put("KPIFS",kpi==null||"".equals(kpi)?"——":kpi);
			result.put("SZFS",sz==null||"".equals(sz)?"——":sz);
			result.put("TZFS",fs==null||"".equals(fs)?"——":fs);
			result.put("XS",xs==null||"".equals(xs)?"1.0":xs);
			result.put("JB",jb==null||"".equals(jb)?"——":jb);
			
			
			return result;
			
		}

		/**
		 * 奖惩通知流程数（通用）
		 * @param uid
		 * @param year
		 * @param month
		 * @param i
		 * @param j
		 * @return
		 * @throws ParseException 
		 */
		public JSONArray getJCTZInfo(String uid, String year, String month,
				int type1,int type2,String sid)  {
			String departmentId = SDK.getRuleAPI().executeAtScript("@sqlValue(select ID from ORGDEPARTMENT where DEPARTMENTNO = '@getUserInfo(@uid,GSBM)')");
//			String sql="select  to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), -1) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), 0) + 1), 'yyyy-mm-dd') EDATE from dual";
			String sql="select  to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-01','yyyy-mm-dd')), -1) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-01','yyyy-mm-dd')), 0) + 1), 'yyyy-mm-dd') EDATE from dual";
			String startDate=DBSql.getString(sql,"SDATE");
			String endDate=DBSql.getString(sql,"EDATE");
			//DateUtil.string2SqlDate(startDate)
			StringBuffer selectSQL= new StringBuffer();
			selectSQL.append("select ");
//			selectSQL.append("(select CNNAME from BO_ACT_DICT_KV_ITEM where DICTKEY='DY.JCLB' and ITEMNO=JCLB) as LB, ");
//			selectSQL.append("(select CNNAME from BO_ACT_DICT_KV_ITEM where DICTKEY='DY.JCLX' and ITEMNO=JCLX) as LX, ");
			selectSQL.append("(select JCLB from BO_DY_JCXX_JCLB_S where BMBM = '"+departmentId+"') as LB, ");
			selectSQL.append("(select CNNAME from BO_ACT_DICT_KV_ITEM where DICTKEY='DY.JCLX' and ITEMNO=JCLX) as LX, ");
			selectSQL.append("JCMX,JCSM,ZXRQ,BINDID, ");
			selectSQL.append("(CASE WHEN JCLX='0' THEN -1*JCJE  WHEN JCLX='3' THEN -1*JCJE ELSE JCJE END) JE ");
			selectSQL.append("from ");
			selectSQL.append(PerformanceConstant.TAB_JXGL_JCTZ);
			selectSQL.append(" where ZT='1' and BKHRZH='"+uid+"'");
			selectSQL.append(" and ZXRQ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') ");
			selectSQL.append(" and ZXRQ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') ");
			selectSQL.append(" and JCLX in ('"+type1+"','"+type2+"')");
			
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(selectSQL.toString());
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String lb=rs.getString("LB");
						String lx=rs.getString("LX");
						String mx=rs.getString("JCMX");
						String sm=rs.getString("JCSM");
						String rq=rs.getString("ZXRQ");
						String je=rs.getString("JE");
						String bindId=rs.getString("BINDID");
						String url=SDK.getFormAPI().getDWFormURL("", sid, bindId, PerformanceConstant.FORM_JXGL_JCTZ, 9);
						JCXXBean bean = new JCXXBean(lb, lx,mx, sm, je, rq,bindId,url);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			
			//TODO 公司奖惩流程
			StringBuffer gsSQL= new StringBuffer();
			gsSQL.append("select ");
			gsSQL.append("(select CNNAME from BO_ACT_DICT_KV_ITEM where DICTKEY='DY.JCLX' and ITEMNO=JCLX) as LX, ");
			gsSQL.append("YEAR,MONTH,JCYJ,BINDID, ");
			gsSQL.append("(CASE WHEN JCLX='0' THEN -1*JCJE  WHEN JCLX='3' THEN -1*JCJE ELSE JCJE END) JE ");
			gsSQL.append(" from BO_DY_JXGL_GSJC where zt='1' and SJLX='1' and BJCRZH='"+uid+"' and YEAR='"+year+"' and MONTH='"+month+"' ");
			gsSQL.append(" and JCLX in ('"+type1+"','"+type2+"')");
			//System.out.println(gsSQL.toString());
			
			
			
			conn = null;
			pstat = null;
			rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(gsSQL.toString());
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String lx=rs.getString("LX");
						//String mx=rs.getString("JCMX");
						//String sm=rs.getString("JCSM");
						String lb="(公司级)"+rs.getString("JCYJ");
						
						String je=rs.getString("JE");
						String bindId=rs.getString("BINDID");
						String url=SDK.getFormAPI().getDWFormURL("", sid, bindId, "537bc1fa-eefc-44e1-8949-b473012fe43d", 9);
						JCXXBean bean = new JCXXBean(lb, lx,"", "", je, year+"-"+month+"-11",bindId,url);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
		}
		
		/**
		 * 工序产量sum统计
		 * @param uid
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONArray getJXJSInfo(String uid,String year,String month){
			
			String sql=" select   GXBM,GXMC,sum(FTRCL) CL,SUM(RJSJE) JE,DEDW  from "
						+PerformanceConstant.VIEW_JXGL_RCL
						+" where RYZH='"+uid+"' and NF='"+year+"' and YF='"+month+"'  group by GXBM,GXMC,DEDW";
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String bm=rs.getString("GXBM");
						String mc=rs.getString("GXMC");
						String cl=rs.getString("CL");
						double je=rs.getDouble("JE");
						String dw=rs.getString("DEDW");
						JXJSBean bean = new JXJSBean(null, bm, mc, null, dw, cl, je+"");
						
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
			
		}
		/**
		 * 工序产量明细统计
		 * @param uid
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONArray getJXJSMXInfo(String uid,String year,String month,String gxbm){
			
			String sql=" select   SCRQ,GXMC,DEDJ,DEDW,FTRCL,RJSJE  from "
						+PerformanceConstant.VIEW_JXGL_RCL
						+" where RYZH='"+uid+"' and NF='"+year+"' and YF='"+month+"' AND GXBM='"+gxbm+"' order by SCRQ desc";
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String rq=rs.getString("SCRQ");
						String mc=rs.getString("GXMC");
						double dj=rs.getDouble("DEDJ");
						
						double cl=rs.getDouble("FTRCL");
						double je=rs.getDouble("RJSJE");
						String dw=rs.getString("DEDW");
						JXJSBean bean = new JXJSBean(rq, gxbm, mc, dj+"", dw, cl+"", je+"");
						
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
			
		}
		/**
		 * 获取uid对应的SC数据 （JSONArray类型）
		 * @param uid
		 */
		public JSONArray getSCData(String uid){
			
			String sql="SELECT MC,CMD,BDPK FROM "+PerformanceConstant.TAB_JCXX_JXSC+" WHERE ZH='"+uid+"'";
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						String mc=rs.getString("MC");//
						String cmd=rs.getString("CMD");//
						String pk=rs.getString("BDPK");//
						SCBean bean = new SCBean(uid, mc, cmd, pk);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
		}
		/**
		 * 获取uid对应的SC数据 （List<SCBean>类型）
		 * @param uid
		 */
		public List<SCBean> getSCTabData(String uid){
			
			String sql="SELECT MC,CMD,BDPK FROM "+PerformanceConstant.TAB_JCXX_JXSC+" WHERE ZH='"+uid+"' order by updatedate asc ";
			List<SCBean> data = new ArrayList<SCBean>();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						String mc=rs.getString("MC");//
						String cmd=rs.getString("CMD");//
						String pk=rs.getString("BDPK");//
						SCBean bean = new SCBean(uid, mc, cmd, pk);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
		}
		
		
		
		/**
		 * K3 月计划、完成 数据
		 * @param gsbm
		 * @param year
		 * @param month
		 * @return
		 */
		public JSONArray getPlanCommitInfo(String gsbm,String year,String month){
			//select MC,DW,GG,DDL,RKL from VIEW_DY_JCXX_K3JHWC where YEAR='2018' and MONTH='05' and GSBM='000001'
			String sql=" select   MC,DW,GG,DDL,RKL  from "
						+PerformanceConstant.VIEW_JCXX_K3JHWC
						+" where GSBM='"+gsbm+"' and YEAR='"+year+"' and MONTH='"+month+"' order by WLBM asc";
			JSONArray data = new JSONArray();
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try{
				conn = DBSql.open();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while(rs.next()){
					
					try {
						
						String mc=rs.getString("MC");
						String dw=rs.getString("DW");
						String gg=rs.getString("GG");
						String ddl=rs.getString("DDL");
						String rkl=rs.getString("RKL");
						JHWCBean bean = new JHWCBean(null, mc, ddl, rkl, gg, dw);
						data.add(bean);
					} catch (Exception e) {
					}
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBSql.close(conn, pstat, rs);
			}
			return data;
			
		}
		
		/**
		 * 日产量数据删除操作（字段子表数据同时删除）
		 * @return
		 */
		public ResponseObject dayProductDataDelete(String id){
			//日产量子录入子表
			String son=PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX;
			//日产量字段子表—人均分配表
			String grandson=PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX;
			String sql2 = "delete from "+grandson+"  where bindid in ("+id+")";
			String sql1 = "delete from "+son+"  where id in ("+id+")";
			String result="ok";
			String meg="";
			Connection conn = DBSql.open();
			try{
				conn.setAutoCommit(false);//自动提交关闭
				DBSql.update(conn, sql2);
				DBSql.update(conn, sql1);
				conn.commit();
			}catch(Exception e){
				result="fail";
				meg=e.getMessage();
				try {
					conn.rollback();//删除数据回滚
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				DBSql.close(conn, null, null);
			}
			if(result.equals("fail")){
				return ResponseObject.newErrResponse("数据库删除异常，执行终止！");
			}
			return ResponseObject.newOkResponse("执行成功！");
		}
}
