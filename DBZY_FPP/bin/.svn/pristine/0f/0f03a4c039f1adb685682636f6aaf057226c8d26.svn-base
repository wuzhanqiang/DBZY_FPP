package com.nepharm.apps.fpp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.actionsoft.bpms.bo.design.cache.BOCache;
import com.actionsoft.bpms.bo.design.model.BOItemModel;
import com.actionsoft.bpms.bo.design.model.BOModel;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.cc.Adapter.DB;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSAPIException;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class BOUtil {
	/**
	 * 清理系统字段值
	 * @param bo
	 * @return
	 */
	public static BO cleanBO(BO bo){
		bo.setId(null);
		bo.setBindId(null);
		bo.setCreateDate(null);
		bo.setCreateUser(null);
		bo.setEnd(false);
		bo.setOrgId(null);
		bo.setProcessDefId(null);
		bo.setUpdateDate(null);
		bo.setUpdateUser(null);
		return bo;
	}
	public static BO cleanBO(BO bo,String uid){
		Date date = new Date();
		bo.setId(null);
		bo.setBindId(null);
		bo.setCreateDate(date);
		bo.setCreateUser(uid);
		bo.setEnd(false);
		bo.setOrgId(null);
		bo.setProcessDefId(null);
		bo.setUpdateDate(date);
		bo.setUpdateUser(uid);
		return bo;
	}
	/**
	 * 通过SQL查询需要的数据放到List里
	 * @param sql
	 * @return List<BO>
	 */
	public static  List queryEncapsulationData(String sql){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData data = null;
		List list = new ArrayList();
		BO bo = null;
		String columnName = null;
		String columnValue = null;
			try {
				conn = DBSql.open();
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				data=rs.getMetaData(); 
				while(rs.next()){
					bo = new BO();
					
					for(int i=1;i <= data.getColumnCount(); i++){
						
						columnName = data.getColumnName(i); 
//					//去除系统列
//					if(!"ID".equals(columnName) && !"ORGNO".equals(columnName)&& !"BINDID".equals(columnName)&& !"CREATEDATE".equals(columnName)
//							&& !"CREATEUSER".equals(columnName)&& !"UPDATEDATE".equals(columnName)&& !"UPDATEUSER".equals(columnName)
//							&& !"WORKFLOWID".equals(columnName)&& !"WORKFLOWSTEPID".equals(columnName)&& !"ISEND".equals(columnName)){
							//获得指定列的列值 
							columnValue = rs.getString(columnName); 
							if(columnValue!=null){
								bo.set(columnName, columnValue);
							}
							
//					}
					}
					
					list.add(bo);
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBSql.close(conn, st, rs);
				data = null;
				bo = null;
				columnName = null;
				columnValue = null;
			}
			
			
			return list;
			
	}
	/**
	 * 通过配置的CC连接UUID及sql查询语句得到数据并放入到LIST中
	 * zhangjh
	 * @param CCUUID 连接UUID
	 * @param sql 查询语句
	 * @return  LIST数据集
	 */
	public static  List<BO> selectCCEncapsulationList(String CCUUID,String sql){
		
		
		 Connection conn = null;
		 DB dbAPI = null;
		 Statement st = null;
		 BO bo = null;
		 ResultSet rs = null;
		 ResultSetMetaData data = null;
		 String columnName = null;
		 String columnValue = null;
		 List list = new ArrayList();
		
	      try {
	    	  	dbAPI = SDK.getCCAPI().getDBAPI(CCUUID);
	    	  	conn = dbAPI.open();
	    	  	st = conn.createStatement();
				rs = st.executeQuery(sql);
				data=rs.getMetaData(); 
				while(rs.next()){
					bo = new BO();
					
					for(int i=1;i <= data.getColumnCount(); i++){
						
						columnName = data.getColumnName(i); 

						//获得指定列的列值 
						columnValue = rs.getString(columnName); 
						if(columnValue!=null){
							bo.set(columnName, columnValue);
						}

					}
					
					list.add(bo);
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBSql.close(conn, st, rs);
				data = null;
				bo = null;
				columnName = null;
				columnValue = null;
				dbAPI = null;
			}
			
		return list;
	}
	
	private static String  tabInsertSQL = "insert into BO_DY_JCXX_XTBXX(id,name,title,pname,lx) values";
	private static String tabDeleteSQL="delete from BO_DY_JCXX_XTBXX";
	/**
	 * 更新系统表字典数据
	 */
	@Deprecated
	public static void updateTabDicOld(){
		DBSql.update(tabDeleteSQL);
		List<BOModel> list=BOCache.getInstance().getBOTableList();
		//insert into BO_DY_JCXX_XTBXX(id,name,title,pname) values('1','2','3','4');
		for(BOModel model:list){
			String name=model.getName();
			String title=model.getTitle();
//			System.out.println(name+":"+title);
			String uuid=UUID.randomUUID().toString();
			DBSql.update(tabInsertSQL+"('"+uuid+"','"+name+"','"+title+"',null,'0')");
			List<BOItemModel> itemList=BOCache.getInstance().getBOItemList(model);
			for(BOItemModel item:itemList){
				String name2=item.getName();
				String title2=item.getTitle();
//				System.out.println("-->"+item.getName()+":"+item.getTitle());
				String uuid2=UUID.randomUUID().toString();
				DBSql.update(tabInsertSQL+"('"+uuid2+"','"+name2+"','"+title2+"','"+name+"','1')");
			}
		}
	}
	
	/**
	 * 更新系统表字典数据
	 */
	public static void updateTabDic(){
		DBSql.update(tabDeleteSQL);
		Map<String, Map<String,BOModel>> map=BOCache.getInstance().getList();
		Map<String,BOModel> mapAPPS=map.get(ConfigConstant.APP_ID);
		for (Iterator i = mapAPPS.keySet().iterator(); i.hasNext();) {
			String key = (String)i.next();  
			BOModel model=mapAPPS.get(key);
			String name=model.getName();
			String title=model.getTitle();
//			System.out.println(name+":"+title);
			String uuid=UUID.randomUUID().toString();
			DBSql.update(tabInsertSQL+"('"+uuid+"','"+name+"','"+title+"',null,'0')");
			List<BOItemModel> itemList=BOCache.getInstance().getBOItemList(model);
			for(BOItemModel item:itemList){
				String name2=item.getName();
				String title2=item.getTitle();
//				System.out.println("-->"+item.getName()+":"+item.getTitle());
				String uuid2=UUID.randomUUID().toString();
				DBSql.update(tabInsertSQL+"('"+uuid2+"','"+name2+"','"+title2+"','"+name+"','1')");
			}
		}
	}
	
	
	/**
	 * 上个月
	 * @return
	 * @throws ParseException
	 */
	public static String[] getLastMonthDate() throws ParseException{
		String sql=
		"select trunc(add_months(sysdate,-1),'mm') FRI,trunc(add_months(sysdate,0),'mm') SEC from dual";
		String fri=DBSql.getString(sql,"FRI");
		String sec=DBSql.getString(sql,"SEC");
		fri=fri.substring(0, 10);
		sec=sec.substring(0, 10);
		return new String[]{fri,sec};
//		return new java.sql.Date[]{DateUtil.string2SqlDate(fri),DateUtil.string2SqlDate(sec)};
	}
	
	/**
	 * 下个月
	 * @return
	 * @throws ParseException
	 */
	public static String getNextMonthDate() throws ParseException{
		String sql=
		"select trunc(add_months(sysdate,1),'mm') NextM from dual";
		
		String NextM = DBSql.getString(sql,"NextM");
		NextM=NextM.substring(0, 10);
		return NextM;
//		return new java.sql.Date[]{DateUtil.string2SqlDate(fri),DateUtil.string2SqlDate(sec)};
	}
	/**
	 * 上个季度
	 * @return
	 * @throws ParseException
	 */
	public static String[] getLastQuarterDate() throws ParseException{
		String sql=
		"select trunc(add_months(sysdate,-3),'mm') FRI,trunc(add_months(sysdate,0),'mm') SEC from dual";
		String fri=DBSql.getString(sql,"FRI");
		String sec=DBSql.getString(sql,"SEC");
		fri=fri.substring(0, 10);
		sec=sec.substring(0, 10);
		return new String[]{fri,sec};
//		return new java.sql.Date[]{DateUtil.string2SqlDate(fri),DateUtil.string2SqlDate(sec)};
	}
	/**
	 * 上一年
	 * @return
	 * @throws ParseException
	 */
	public static String[] getLastHalfYearDate() throws ParseException{
		String sql=
		"select trunc(add_months(sysdate,-6),'mm') FRI,trunc(add_months(sysdate,0),'mm') SEC from dual";
		String fri=DBSql.getString(sql,"FRI");
		String sec=DBSql.getString(sql,"SEC");
		fri=fri.substring(0, 10);
		sec=sec.substring(0, 10);
		return new String[]{fri,sec};
//		return new java.sql.Date[]{DateUtil.string2SqlDate(fri),DateUtil.string2SqlDate(sec)};
	}
	/**
	 * 上一年
	 * @return
	 * @throws ParseException
	 */
	public static String[] getLastYearDate() throws ParseException{
		String sql=
		"select trunc(add_months(sysdate,-12),'mm') FRI,trunc(add_months(sysdate,0),'mm') SEC from dual";
		String fri=DBSql.getString(sql,"FRI");
		String sec=DBSql.getString(sql,"SEC");
		fri=fri.substring(0, 10);
		sec=sec.substring(0, 10);
		return new String[]{fri,sec};
//		return new java.sql.Date[]{DateUtil.string2SqlDate(fri),DateUtil.string2SqlDate(sec)};
	}
	public static void main(String[] args) {
		String s="1234567890123456";
		System.out.println(s.substring(0, 10));
	}
}
