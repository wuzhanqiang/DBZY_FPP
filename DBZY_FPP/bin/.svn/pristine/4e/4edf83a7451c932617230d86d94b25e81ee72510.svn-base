package com.nepharm.apps.fpp.biz.pem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.pem.bean.KPIBean;
import com.nepharm.apps.fpp.biz.pem.bean.KPIParamBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.util.BOUtil;
import com.nepharm.apps.fpp.util.DateUtil;

/**
 * 绩效考核-KPI指标操作相关DAO实现
 * @author lizhen
 *
 */
public class KPICommonDao {
	
	/**
	 * 获取KPI参数
	 * @param boId kpi指标数据ID
	 * @return
	 */
	public Map<String,KPIParamBean> getKPIParams(String boId){
		String sql="SELECT CSID,MC,LX,CSZ FROM "+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+" WHERE BINDID='"+boId+"'";
		Map<String,KPIParamBean> data=new HashMap<String,KPIParamBean>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String key=rs.getString("MC");//获取参数名称
					String csID=rs.getString("CSID");//原配置参数ID（用于查找条件）
					String type=rs.getString("LX");//参数类型
					double value=rs.getDouble("CSZ");//参数值
					KPIParamBean bean = new KPIParamBean(csID,key, type, value);
					data.put(key, bean);
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
	 * 获取KPI标准参数：基数、权重、难易系数等
	 * @param boId kpi指标数据ID
	 * @return
	 */
	public Map<String,Double> getKPIStandard(String boId){
		String sql="SELECT KHMBZ,JSFZ,KHWD,ZXFZ,JXFZ,KHQZ,NYXS FROM "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" WHERE ID='"+boId+"'";
		Map<String,Double> data=new HashMap<String,Double>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					double goal=rs.getDouble("KHMBZ");//基础目标结果值
					double basic=rs.getDouble("JSFZ");//基础目标结果值对应的分值
					double unit=rs.getDouble("KHWD");//考核纬度-单位
					double up=rs.getDouble("ZXFZ");//单位下增项分值
					double down=rs.getDouble("JXFZ");//单位下减项分值
					double weight=rs.getDouble("KHQZ");//权重
					double difficulty=rs.getDouble("NYXS");//难易系数
					
					data.put("goal", goal);
					data.put("basic",basic );
					data.put("unit",unit );
					data.put("up", up);
					data.put("down", down);
					data.put("weight", weight);
					data.put("difficulty", difficulty);
					
					
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
	 * 更新一条KPI数据的分数
	 * @param boId  KPI考核ID
	 * @param score 分数
	 */
	public  void updateKPIScore(String boId,double score){
		String sql = "UPDATE "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" SET FS='"+score+"' WHERE ID='"+boId+"'";
		DBSql.update(sql);
	}
	/**
	 * 更新一条KPI数据的模板信息
	 * @param boId  KPI考核ID
	 * @param template 分数
	 */
	public  void updateKPITemplate(String boId,String template){
		String sql = "UPDATE "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" SET ZSXX='"+template+"' WHERE ID='"+boId+"'";
		DBSql.update(sql);
	}

	/**
	 * 获取KPI模板字段数据
	 * @param boId
	 * @return
	 */
	public String getKPITemplate(String boId) {
		String sql ="SELECT ZSMB FROM "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" WHERE ID='"+boId+"'";
		return DBSql.getString(sql,"ZSMB");
	}


	/**
	 * 获取绩效考核流程下的KPI重要数据项数据
	 * @param bindId
	 * @return
	 */
	public List<KPIBean> getKPIInfo(String bindId) {
		
		String sql="SELECT ID,KPIBM,SXL FROM "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" WHERE BINDID='"+bindId+"'";
		List<KPIBean> data=new ArrayList<KPIBean>();
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String boId=rs.getString("ID");//boId
					String kpiNo=rs.getString("KPIBM");//KPI编码
					String className=rs.getString("SXL");//实现类
//					String functionName=rs.getString("SXFF");//实现类
					KPIBean bean = new KPIBean(boId, kpiNo, className);
//					KPIBean bean = new KPIBean(boId, kpiNo, className, functionName);
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
	
	
	public String getWhereSQL(String no,String mc,String userId){
		String sql="select * from "+PerformanceConstant.VIEW_KPI_CS+" where MC='"+mc+"' and KPIBM='"+no+"'";
		String whereSQL="  ";
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
//					String sjy=rs.getString("TJSJY");//
					String lm=rs.getString("TJSJL");//
					String value=rs.getString("CSZ");//
					if(value!=null&&"@uid".equals(value)){
						value=userId;
					}
					String temp=" and "+lm+" = '"+value+"' ";
					whereSQL=whereSQL+temp;
				} catch (Exception e) {
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return whereSQL;
		
	}
	
	public String getSourceSQL(String no,String mc,String zq){
		String sql="select DISTINCT(MC) MC,YSLJ,SJY,SJL,SJZD from "+PerformanceConstant.VIEW_KPI_CS+" where MC='"+mc+"' and KPIBM='"+no+"'";
		String source="";
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String yslj=rs.getString("YSLJ");//运算逻辑
					String sjy=rs.getString("SJY");//
					String lm=rs.getString("SJL");//
					String sj=rs.getString("SJZD");//
					String temp="1=1";
					if(sj!=null&&!"".equals(sj)){
						
						String[] dates=null;
						if(zq==null||"".equals(zq)||"0".equals(zq)){//月
							dates=BOUtil.getLastMonthDate();
						}else if("1".equals(zq)){//季度
							dates=BOUtil.getLastQuarterDate();
						}else if("2".equals(zq)){//半年
							dates=BOUtil.getLastHalfYearDate();
						}else if("3".equals(zq)){//年
							dates=BOUtil.getLastYearDate();
						}
						temp=" "+sj+">=TO_DATE('"+dates[0]+"','YYYY-MM-DD')";
						temp=temp+" and "+sj+"<TO_DATE('"+dates[1]+"','YYYY-MM-DD')";
					}
					if("1".equals(yslj)){
						source="select sum("+lm+") NUM from "+sjy+" where ";
					}else if("2".equals(yslj)){
						source="select count("+lm+") NUM from "+sjy+" where ";
					}else if("3".equals(yslj)){
						source="select max("+lm+") NUM from "+sjy+" where ";
					}else if("4".equals(yslj)){
						source="select min("+lm+") NUM from "+sjy+" where ";
					}
					source=source+temp;
				} catch (Exception e) {
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return source;
	}
}
