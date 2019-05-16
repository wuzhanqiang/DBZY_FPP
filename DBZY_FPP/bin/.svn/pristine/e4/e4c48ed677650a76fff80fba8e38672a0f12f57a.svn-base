package com.nepharm.apps.fpp.biz.pem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOQueryAPI;
import com.nepharm.apps.fpp.biz.pem.bean.KPIDataBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.DateUtil;
/**
 * 绩效管理流程-相关DAO操作
 * @author lizhen
 *
 */
public class PerformanceDao {
	/**
	 * 获取KPI配置表中配置的岗位编码和流程实例ID
	 * @return
	 */
	public Map<String,String> getKPIPostData(){
//		String sql="SELECT BINDID,GWBM FROM "+PerformanceConstant.TAB_JXGL_KPIPZ_M;
		String sql="SELECT BINDID,GWBM FROM "+PerformanceConstant.TAB_KPI_PZ_M;
		Map<String,String> data = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bindId=rs.getString("BINDID");//获取参数名称
					String bm=rs.getString("GWBM");//参数类型
					data.put(bm, bindId);
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
	 * 获取职业素质配置表中配置的岗位编码和流程实例ID（只限操作岗）
	 * @return
	 */
	public Map<String,String> getAbilityPostData(){
		String sql="SELECT a.BINDID BINDID,a.GWBM GWBM FROM "+PerformanceConstant.TAB_JXGL_SZPZ_M+" a  LEFT OUTER JOIN   "+PerformanceConstant.TAB_KPI_PZ_M+" b on a.GWBM=b.GWBM where b.GWBM is null ";
		Map<String,String> data = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bindId=rs.getString("BINDID");//获取参数名称
					String bm=rs.getString("GWBM");//参数类型
					data.put(bm, bindId);
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
	 * 获取职业素质配置表中配置的岗位编码和流程实例ID
	 * @return
	 */
	public Map<String,String> getAllAbilityPostData(){
		String sql="SELECT BINDID,GWBM FROM "+PerformanceConstant.TAB_JXGL_SZPZ_M;
		Map<String,String> data = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bindId=rs.getString("BINDID");//获取参数名称
					String bm=rs.getString("GWBM");//参数类型
					data.put(bm, bindId);
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
	 * KPI配置子表详细数据
	 * @param bindId
	 * @return
	 */
	public List<BO> getKPIPostSubData(String bindId){
		
//		List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_KPIPZ_S).bindId(bindId).list();
		List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_KPI_PZ_S).bindId(bindId).list();
		return list;
	}
	/**
	 * 能力配置子表详细数据
	 * @param bindId
	 * @return
	 */
	public List<BO> getAbilityPostSubData(String bindId){

		List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_SZPZ_S).bindId(bindId).list();
		
		return list;
	}
	/**
	 * KPI配置参数子表详细数据
	 * @param bindId
	 * @return
	 */
	public List<BO> getKPIParamSubData(String bm){
		String sql = "select BINDID from "+PerformanceConstant.TAB_KPI_ZBK_M+" where KPIBM ='"+bm+"'";
		String bindId=DBSql.getString(sql,"BINDID");
		List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_KPI_ZBK_CS).bindId(bindId).list();
		return list;
	}

	/**
	 * 获取KPI参数考核人信息
	 * @return
	 */
	public List<String> getKPIParamKHR() {
		String sql="select distinct(KHR) as USERID   FROM "+PerformanceConstant.VIEW_JXGL_JXKH_CS;
		List<String> data = new  ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String uid=rs.getString("USERID");//
					data.add(uid);
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

	public List<BO> getKPIParamData(String uid) {
		//select ID,GWMC,BKHR,KPIBM,ZBLB,ZBMX,ZSMB,MC,SM from VIEW_DY_JXGL_JXKH_CS where KHR='admin'  order by ID,MC ASC
		String sql="select ID,GWMC,BKHR,KPIBM,ZBLB,ZBMX,ZSMB,MC,SM   FROM "+PerformanceConstant.VIEW_JXGL_JXKH_CS+" where KHR='"+uid+"'  order by ID,MC ASC";
		List<BO> data = new  ArrayList<BO>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					BO bo = new BO();
					bo.set("YID", rs.getString("ID"));
					bo.set("GWMC", rs.getString("GWMC"));
					bo.set("BKHR", rs.getString("BKHR"));
					bo.set("KPIBM", rs.getString("KPIBM"));
					bo.set("ZBLB", rs.getString("ZBLB"));
					bo.set("ZBMX", rs.getString("ZBMX"));
					bo.set("ZSMB", rs.getString("ZSMB"));
					bo.set("MC", rs.getString("MC"));
					bo.set("SM", rs.getString("SM"));
					data.add(bo);
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
	 * 将考核填写数据更新回KPI参数中
	 * @param bindId
	 */
	public void updateKPIParamListValue(String bindId) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE "+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+" ");
		sql.append(" SET  ");
		sql.append(PerformanceConstant.TAB_JXGL_JXKH_ZBCS+".CSZ = ");
		sql.append(" (SELECT  max(CSZ)  FROM  "+PerformanceConstant.TAB_JXGL_JXTX_S+"  WHERE   "+PerformanceConstant.TAB_JXGL_JXTX_S+".YID="+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+".BINDID  and "+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+".MC="+PerformanceConstant.TAB_JXGL_JXTX_S+".MC) ");
		sql.append(" WHERE  ");
		sql.append(" EXISTS ( SELECT 1 FROM   "+PerformanceConstant.TAB_JXGL_JXTX_S+"  WHERE    "+PerformanceConstant.TAB_JXGL_JXTX_S+".YID="+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+".BINDID  and "+PerformanceConstant.TAB_JXGL_JXKH_ZBCS+".MC="+PerformanceConstant.TAB_JXGL_JXTX_S+".MC AND "+PerformanceConstant.TAB_JXGL_JXTX_S+".BINDID='"+bindId+"')  ");
		DBSql.update(sql.toString());
	}

	/**
	 * 更新绩效填写流程的状态
	 * @param bindId
	 * @param zt
	 */
	public void updateFillInStatus(String bindId, String zt) {
		String sql= "update "+PerformanceConstant.TAB_JXGL_JXTX_M+" set zt="+zt+" where bindid='"+bindId+"'";
		DBSql.update(sql);
	}

	/**
	 * 获取绩效填写流程未关闭的流程实例list
	 * @return
	 */
	public List<String> getFillInProcessIds() {
		//查找isend=0，zt=0的主表数据（返回bindidlist即可）
		String sql="select BINDID   FROM "+PerformanceConstant.TAB_JXGL_JXTX_M+" where isend='0' and zt='0' ";
		List<String> data = new  ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bindId=rs.getString("BINDID");
					data.add(bindId);
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
	 * 获取绩效考核流程 任务信息
	 * @return
	 */
	public Map<String, String> getPerformanceTasks(String param) {
		
//		select a.bindid,b.id,b.tasktitle from BO_DY_JXGL_JXKH_M a,WFC_TASK b where a.bindid=b.processinstid and b.tasktitle like '(系统定时启动)%'
		String sql = "select a.bindid BINDID,b.id ID from "+PerformanceConstant.TAB_JXGL_JXKH_M+" a,"+ConfigConstant.TAB_TASK_C+" b where a.bindid=b.processinstid and b.tasktitle like '"+param+"%' and (b.processdefverid='"+PerformanceConstant.PROCESS_JXGL_JXKH+"' or b.processdefverid='"+PerformanceConstant.PROCESS_JXGL_JXKH_LH+"')";
		Map<String, String> data = new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bindId=rs.getString("BINDID");
					String id=rs.getString("ID");
					data.put(id, bindId);
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
	 * 嘉奖否定项数据（分成制度、嘉奖两类）
	 * @param bkhr
	 * @return
	 */
	public Map<String, List<BO>> getRewards(String bkhr) {
		Map<String, List<BO>> data = new HashMap<String,List<BO>>();
		//TODO 日期条件过滤
		//select to_char(trunc(add_months(last_day(sysdate), -2) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(sysdate), -1) + 1), 'yyyy-mm-dd') EDATE  from dual
		String sql="select to_char(trunc(add_months(last_day(sysdate), -2) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(sysdate), -1) + 1), 'yyyy-mm-dd') EDATE  from dual";
		String startDate=DBSql.getString(sql,"SDATE");
		String endDate=DBSql.getString(sql,"EDATE");
		
		List<BO> list=null;
		try {
			list = SDK.getBOAPI()
				.query(PerformanceConstant.TAB_JXGL_JCTZ)
				.addQuery("BKHRZH =",bkhr)
				.addQuery("ZT =", "1")
				.addQuery("ZXRQ >=", DateUtil.string2SqlDate(startDate))
				.addQuery("ZXRQ <", DateUtil.string2SqlDate(endDate))
				.list();
		} catch (ParseException e) {
			System.out.println("PerformanceDao.getRewards()->解析时间（执行时间）出错！"+e.getMessage());
		}
		List<BO> jj= new ArrayList<BO>();//嘉奖否定
		List<BO> zd=new ArrayList<BO>();//制度奖惩
		for(BO bo:list){
			BO temp=new BO();
			String lx=(String)bo.get("JCLX");
			temp.set("JCLB", bo.get("JCLB"));
			temp.set("JCLX", lx);
			temp.set("JCMX", bo.get("JCMX"));
			temp.set("JCMX", bo.get("JCMX"));
			temp.set("ZXRQ", bo.get("ZXRQ"));
			String je=(String)bo.get("JCJE");
			
			temp.set("YBINDID", bo.getBindId());
			if("0".equals(lx)){
				temp.set("JCJE", "-"+je);
				zd.add(temp);
			}else if("1".equals(lx)){
				temp.set("JCJE", je);
				zd.add(temp);
			}else if("2".equals(lx)){
				temp.set("JCJE", bo.get("JCJE"));
				jj.add(temp);
			}else if("3".equals(lx)){
				temp.set("JCJE", "-"+je);
				jj.add(temp);
			}
			
		}
		
		data.put("ZD", zd);
		data.put("JJ", jj);
		return data;
	}

	
	
	/**
	 * 公司级奖惩数据（分成制度、嘉奖两类）
	 * @param bkhr
	 * @return
	 */
	public Map<String, List<BO>> getCompanyRewards(String bkhr) {
		Map<String, List<BO>> data = new HashMap<String,List<BO>>();
		//TODO 日期条件过滤
		//select to_char(trunc(add_months(last_day(sysdate), -2) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(sysdate), -1) + 1), 'yyyy-mm-dd') EDATE  from dual
		String sql="select to_char(trunc(add_months(last_day(sysdate), -2) + 1), 'yyyy') Y,to_char(trunc(add_months(last_day(sysdate), -2) + 1), 'mm') M  from dual";
		String year=DBSql.getString(sql,"Y");
		String month=DBSql.getString(sql,"M");
		
		List<BO> list=null;
			list = SDK.getBOAPI()
				.query("BO_DY_JXGL_GSJC")
				.addQuery("BJCRZH =",bkhr)
				.addQuery("SJLX =", "1")
				.addQuery("ZT =", "1")
				.addQuery("YEAR =", year)
				.addQuery("MONTH =", month)
				.list();
		List<BO> jj= new ArrayList<BO>();//嘉奖否定
		List<BO> zd=new ArrayList<BO>();//制度奖惩
		for(BO bo:list){
			BO temp=new BO();
			String lx=(String)bo.get("JCLX");
			//temp.set("JCLB", bo.get("JCLB"));
			temp.set("JCLX", lx);
			temp.set("JCMX", "(公司级奖惩)"+bo.get("JCYJ"));
			//temp.set("JCMX", bo.get("JCMX"));
			temp.set("ZXRQ", year+"-"+month+"-11");
			String je=(String)bo.get("JCJE");
			
			temp.set("YBINDID", bo.getBindId());
			if("0".equals(lx)){
				temp.set("JCJE", "-"+je);
				zd.add(temp);
			}else if("1".equals(lx)){
				temp.set("JCJE", je);
				zd.add(temp);
			}else if("2".equals(lx)){
				temp.set("JCJE", bo.get("JCJE"));
				jj.add(temp);
			}else if("3".equals(lx)){
				temp.set("JCJE", "-"+je);
				jj.add(temp);
			}
			
		}
		
		data.put("ZD", zd);
		data.put("JJ", jj);
		return data;
	}
	
	
	
	/**
	 * 获取一条实例的历史办结任务ID（返回按后办结的）
	 * @param bindId
	 * @return
	 */
	public String getTaskId(String bindId){
		String sql = "select ID from WFH_TASK where  processinstid='"+bindId+"' order by endtime desc";
		String taskId=DBSql.getString(sql,"ID");
		return taskId;
	}
	
	/**
	 * 获取一条实例的历史待办任务ID（返回按后办结的）
	 * @param bindId
	 * @return
	 */
	public String getwfcTaskId(String bindId){
		String sql = "select ID from WFC_TASK where  processinstid='"+bindId+"' order by begintime desc";
		String taskId=DBSql.getString(sql,"ID");
		return taskId;
	}
	
	/**
	 * 获取能力指标系数列表
	 * @param gsbm
	 * @return
	 */
	public List<BO> getAbilityLeveLData(String gsbm){
		
		String sql="SELECT DJ,XS,ZDFS,ZXFS FROM "+PerformanceConstant.VIEW_JXGL_JXXS+" where GSBM='"+gsbm+"'";
//		System.out.println(sql);
		List<BO> data = new ArrayList<BO>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					BO bo = new BO();
					bo.set("DJ",rs.getString("DJ"));
					bo.set("XS",rs.getString("XS"));
					bo.set("ZDFS",rs.getString("ZDFS"));
					bo.set("ZXFS",rs.getString("ZXFS"));
					data.add(bo);
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
	 * 将等级、系数、总分更新到绩效考核主表上去
	 * @param bindId
	 * @param nlfs
	 * @param zfs
	 * @param zdj
	 * @param zxs
	 * @param tzfs
	 * @param tzdj
	 * @param tzxs
	 * @param jxfs
	 * @param jxdj
	 * @param jxxs
	 */
	public void updateMainData(String bindId,double nlfs,double zfs,String zdj,double zxs,double tzfs,String tzdj,double tzxs,double jxfs,String jxdj,double jxxs){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("update "+PerformanceConstant.TAB_JXGL_JXKH_M+" ");
		sql.append(" set ");
		sql.append(" szfs = "+nlfs+",");
		sql.append(" zfs = "+zfs+",");
		sql.append(" zjb = '"+zdj+"',");
		sql.append(" zxs = "+zxs+",");
		sql.append(" tzfs = "+tzfs+",");
		sql.append(" tzjb = '"+tzdj+"',");
		sql.append(" tzxs = "+tzxs+",");
		sql.append(" jxfs = "+jxfs+",");
		sql.append(" jxjb = '"+jxdj+"',");
		sql.append(" jxxs = "+jxxs+" ");
		sql.append(" where ");
		sql.append(" bindid='"+bindId+"' ");

		System.out.println(sql.toString());
		DBSql.update(sql.toString());
	}
	
	/**
	 * 超时更新至KPI的
	 * @param bindId
	 */
	public void updateKPIOverTimeZt(String bindId){
		List<BO> list = SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_JXTX_S).bindId(bindId).list();
		for(BO bo:list){
			String yid=(String)bo.get("YID");
			String sql = "update "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" set zt='2' where id='"+yid+"'";
			DBSql.update(sql);
		}
		
		
	}

	/**
	 * 获取所有岗位
	 * @return
	 */
	public List<String> getGWList() {
		String sql="SELECT DISTINCT(GWBM) BM FROM "+ConfigConstant.VIEW_RYXX+" ";
//		System.out.println(sql);
		List<String> data = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bm=rs.getString("BM");
					
					data.add(bm);
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
	 * 获取某岗位下的人员列表
	 * @param gwbm
	 * @return
	 */
	public List<String> getRYList(String gwbm) {
		String sql="SELECT DISTINCT(USERID) USERID FROM "+ConfigConstant.VIEW_RYXX+" where GWBM='"+gwbm+"'";
		List<String> data = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String uid=rs.getString("USERID");
					if(uid==null||"".equals(uid)){
						continue;
					}
					data.add(uid);
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
	
	
	
}
