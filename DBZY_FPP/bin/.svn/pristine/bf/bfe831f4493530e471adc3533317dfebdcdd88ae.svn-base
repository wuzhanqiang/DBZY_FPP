package com.nepharm.apps.fpp.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.portal.bean.TaskBean;

import net.sf.json.JSONArray;

public class TaskDao {
	/**
	 * 待办
	 * @param uid
	 * @return
	 */
	private static int init_num=10;
	public JSONArray getDBTaskData(String uid){
		JSONArray data = new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append("select  PROCESSINSTID as BINDID,ID as TASKID,OWNER,TASKTITLE as TITLE,BEGINTIME as TIME,TASKSTATE as STATE,READSTATE from WFC_TASK ");
		sql.append(" where target='"+uid+"'  ");//and taskstate in ('1','3','4','11')
		sql.append(" order by BEGINTIME desc");
		
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while(rs.next()){
				if(data.size()>=init_num){
					break;
				}
				String bindid=rs.getString("BINDID");
				String taskid=rs.getString("TASKID");
				String owner=rs.getString("OWNER");
				String title=rs.getString("TITLE");
				String begin=rs.getString("TIME");
				String state=rs.getString("STATE");
				String read=rs.getString("READSTATE");
				
				//获取姓名
				try {
					owner=SDK.getORGAPI().getUser(owner).getUserName();
				} catch (Exception e) {
					owner="无";
				}
				
				TaskBean bean = new TaskBean(bindid, taskid, owner, begin, title, state,read);
				data.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	
	/**
	 * 已办
	 * @param uid
	 * @return
	 */
	public JSONArray getYBTaskData(String uid){
		JSONArray data = new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append("select  PROCESSINSTID as BINDID,ID as TASKID,OWNER,TASKTITLE as TITLE,BEGINTIME as TIME,TASKSTATE as STATE from WFH_TASK ");
		sql.append(" where target='"+uid+"'  ");//and  taskstate in ('1','3','4','11') 
		sql.append(" order by BEGINTIME desc");
		
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while(rs.next()){
				if(data.size()>=init_num){
					break;
				}
				String bindid=rs.getString("BINDID");
				String taskid=rs.getString("TASKID");
				String owner=rs.getString("OWNER");
				String title=rs.getString("TITLE");
				String begin=rs.getString("TIME");
				String state=rs.getString("STATE");
				
				//获取姓名
				try {
					owner=SDK.getORGAPI().getUser(owner).getUserName();
				} catch (Exception e) {
					owner="无";
				}
				
				TaskBean bean = new TaskBean(bindid, taskid, owner, begin, title, state,"");
				data.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}
	/**
	 * 获取当前用户 待办 流程任务数
	 * @param uid
	 * @return
	 */
	public String getTaskNumber(String uid){
		
		String sql="select count(id) as NUM from WFC_TASK where target='"+uid+"' ";// and taskstate='1'
	
		String num=DBSql.getString(sql, "NUM");
		
		if(num==null||"".equals(num)){
			num="0";
		}
		return num;
		
	}
}
