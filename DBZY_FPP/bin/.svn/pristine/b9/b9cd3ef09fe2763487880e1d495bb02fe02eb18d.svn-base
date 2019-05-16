package com.nepharm.apps.fpp.is.k3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.bean.KPIBean;
import com.nepharm.apps.fpp.biz.pem.bean.KPIParamBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;
import com.nepharm.apps.fpp.util.BOUtil;
import com.nepharm.apps.fpp.util.DateUtil;

/**
 * K3数据同步
 * @author zhangjh
 *
 */
public class K3BusinessDataDao {
	
	
	public String  getK3BusinessData(String kssj, String jssj,int lx,String USERID ){
		
		String sql = "select CCUUID,bindid from "+SynchronousK3Constant.TAB_JCXX_K3TBDZSZ_M+"  ";
		
		StringBuffer sb = new StringBuffer();//返回信息
		StringBuffer sb1 = null;//组合sql查询语句信息
		StringBuffer sb2 = null;//组合sql删除语句信息
		
		String CCUUID = DBSql.getString(sql,"CCUUID");
		List<BO> listPzbData = null;
		BO bo = null;
		List<BO> listTjzb = null;
		BO botjzb = null;
		String ZQFHDZZ = null;
		
		List<BO> listTjzbss = null;
		BO botjzbss = null;
		
		List<BO> listrst = null;
		BO borst = null;
		Date d = null;
		SimpleDateFormat sdf = null;
		String XTRQ = null;
		String[] fris = null;
		String NextM = null;
		if(CCUUID == null || "".equals(CCUUID)){
			sb.append("请填写连接中心UUID");
		}else{
			//得到系统时间
			d = new Date();  
//					        System.out.println(d);  
	        sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        XTRQ = sdf.format(d); 
			//查询K3配置表子表配置信息
			sql = "select XTBM,XTBBT,K3STM,K3STBT,SJZD,ID,TBZQ from "
					+ "	"+SynchronousK3Constant.TAB_JCXX_K3TBDZSZ+" where bindid='"+DBSql.getString(sql,"bindid")+"'";
			listPzbData = BOUtil.queryEncapsulationData(sql);
			for(int i=0;i<listPzbData.size();i++) {
				bo = listPzbData.get(i);
				sb1 = new StringBuffer();//组合sql查询语句信息
				sb2 = new StringBuffer();//组合sql删除语句信息
				//判断是否配置视图名及时间列
				if(bo.getString("K3STM")!=null && !"".equals(bo.getString("K3STM")) 
						&& bo.getString("SJZD")!=null && !"".equals(bo.getString("SJZD"))){
					
					sb1.append("select * from "+bo.getString("K3STM")+" where 1=1 ");
					sb2.append("delete from "+bo.getString("XTBM")+" where 1=1 ");
					
					//查询条件子表
					sql = "select LX,ZQBM,ZQBBT,ZQLM,ZQLBT,ZQFHDZZ,SJLMC,SJL,CSZ from "+SynchronousK3Constant.TAB_K3TBDZSZ_ZDTJZB+" where bindid='"+bo.getString("ID")+"'";
					listTjzb = BOUtil.queryEncapsulationData(sql);
					for(int j=0;j<listTjzb.size();j++) {
						botjzb = listTjzb.get(j);
						//类型：1:条件过滤|2:系统对照替换
						if("1".equals(botjzb.getString("LX"))){
							sb1.append(" and "+botjzb.getString("SJL")+"='"+botjzb.getString("CSZ")+"' ");
							sb2.append(" and "+botjzb.getString("SJL")+"='"+botjzb.getString("CSZ")+"' ");
						}
						
					}
					
					//lx为0定时器，1手动
					if(lx == 0){
						
						if(bo.getString("TBZQ")!=null && !"".equals(bo.getString("TBZQ"))){
							
							try {
								fris = BOUtil.getLastMonthDate();
								NextM = BOUtil.getNextMonthDate();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

													
					        //0:本年|1:本月|2:上月|3:当日
							if("0".equals(bo.getString("TBZQ"))){
								kssj = XTRQ.substring(0,4)+"-01-01";
								jssj = XTRQ.substring(0,4)+"-12-31";
							}else if("1".equals(bo.getString("TBZQ"))){
								kssj = fris[1].substring(0,4)+"-"+fris[1].substring(5,7)+"-01";
								jssj = NextM.substring(0,4)+"-"+NextM.substring(5,7)+"-01";
							}else if("2".equals(bo.getString("TBZQ"))){
								
								kssj = fris[0].substring(0,4)+"-"+fris[0].substring(5,7)+"-01";
								jssj = fris[1].substring(0,4)+"-"+fris[1].substring(5,7)+"-01";
							}else{
								kssj = XTRQ;
								jssj = XTRQ;
							}
					 
							
						}else{
							sb.append("  子表第"+(i+1)+"行需要同步范围");
							continue;
						}
					}else{
						if(kssj==null || "".equals(kssj) || jssj==null || "".equals(jssj)){
							sb.append("  第"+(i+1)+"行需要同步开始时间及同步结束时间");
							continue;
						}
					}
					
					//生产订单单独处理
					if(bo.getString("K3STM").equals("DC_V_AWS_SCDD")){
						sb1.append(" and ((FORDERYEAR='"+kssj.substring(0,4)+"' and FORDERMONTH='"+kssj.substring(5,7)+"') or (FORDERYEAR='"+jssj.substring(0,4)+"' and FORDERMONTH='"+jssj.substring(5,7)+"') )");
						sb2.append(" and ((FORDERYEAR='"+kssj.substring(0,4)+"' and FORDERMONTH='"+kssj.substring(5,7)+"') or (FORDERYEAR='"+jssj.substring(0,4)+"' and FORDERMONTH='"+jssj.substring(5,7)+"') )");
					}else{
						sb1.append(" and "+bo.getString("SJZD")+" between to_date('"+kssj+"','yyyy-MM-dd') and to_date('"+jssj+"','yyyy-MM-dd')");
						sb2.append(" and "+bo.getString("SJZD")+" between to_date('"+kssj+"','yyyy-MM-dd') and to_date('"+jssj+"','yyyy-MM-dd')");
					}
					System.out.println(sb1.toString());
					
					System.out.println(sb2.toString());
					//删除原数据
					DBSql.update(sb2.toString());
					
					//连接远程K3，创建系统表数据
					listrst = BOUtil.selectCCEncapsulationList(CCUUID,sb1.toString());
					for(int j=0;j<listrst.size();j++) {
						borst = listrst.get(j);
						if(USERID==null || "".equals(USERID)){//默认系统管理员
							USERID = "admin";
						}
						try {
							borst.set("XTCCRQ",DateUtil.string2SqlDate(XTRQ.substring(0,4)+"-"+XTRQ.substring(5,7)+"-01"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//查询条件子表所有需要对照替换的维护信息
						sql = "select LX,ZQBM,ZQBBT,ZQLM,ZQLBT,ZQFHDZZ,SJLMC,SJL,CSZ from "+SynchronousK3Constant.TAB_K3TBDZSZ_ZDTJZB+" where bindid='"+bo.getString("ID")+"' and LX='2'";
						listTjzbss = BOUtil.queryEncapsulationData(sql);
						for(int z=0;z<listTjzbss.size();z++) {
							botjzbss = listTjzbss.get(z);
							botjzbss.getString("SJL");//
							//通过查询维护得到系统数据列名获得K3同步数据列值，进而查询对照关系表获得对照值
							sql = "select "+botjzbss.getString("ZQFHDZZ")+" from "+botjzbss.getString("ZQBM")+" where "+botjzbss.getString("ZQLM")+"='"+borst.getString(botjzbss.getString("SJL"))+"'";
							ZQFHDZZ = DBSql.getString(sql,botjzbss.getString("ZQFHDZZ"));
							if(ZQFHDZZ != null && !"".equals(ZQFHDZZ)){
								borst.set(botjzbss.getString("SJL"),ZQFHDZZ);
							}
						}
						SDK.getBOAPI().createDataBO(bo.getString("XTBM"), borst,  UserContext.fromUID(USERID));
					}
						
				}else{
					sb.append("  子表第"+(i+1)+"行需要配置视图名及时间列");
				}
				
				//bo.getSting();
			}
			
			
		}
		
		
		listPzbData = null;
		bo =null;
		sql = null;
		CCUUID = null;
		sdf = null;
		XTRQ = null;
		sb1 = null;
		d = null;
		listTjzb = null;
		botjzb = null;
		listrst = null;
		borst = null;
		fris = null;
		listTjzbss = null;
		botjzbss = null;
		ZQFHDZZ = null;
		return sb.toString();
		
	}
	
	 
	
}
