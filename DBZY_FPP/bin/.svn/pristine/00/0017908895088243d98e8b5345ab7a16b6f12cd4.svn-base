package com.nepharm.apps.fpp.biz.tam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.util.DateUtil;
import com.nepharm.apps.fpp.util.NumberUtil;

/**
 * cmd页面涉及的DAO操作（绩效相关）
 * @author lizhen
 *
 */
public class GzpjDao {

	/**
	 * 工作评分列表
	 * @param year
	 * @param month
	 * @return
	 */
	public JSONArray getWorkGradeInfo(String uid,String year,String month){
		
		JSONArray data = new JSONArray();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		//日期转换
		String dateSql="select  to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), -1) + 1), 'yyyy-mm-dd') SDATE,to_char(trunc(add_months(last_day(to_date('"+year+"-"+month+"-10','yyyy-mm-dd')), 0) + 1), 'yyyy-mm-dd') EDATE from dual";
		String startDate=DBSql.getString(dateSql,"SDATE");
		String endDate=DBSql.getString(dateSql,"EDATE");
		
		
		String sql =" select * from BO_DY_RWGL_GZPJB "+
				" where ISEND='1' and SQRZH='"+uid+"'"+
				" and SQSJ <=to_date('"+endDate.substring(0,10)+"','yyyy-mm-dd') "+
				" and SQSJ >=to_date('"+startDate.substring(0,10)+"','yyyy-mm-dd') ";
		
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					JSONObject result = new JSONObject();
					result.put("SQR", rs.getString("SQR"));
					result.put("SQRZH",rs.getString("SQRZH") );
					result.put("KHR", rs.getString("KHR"));
					result.put("KHRZH", rs.getString("KHRZH"));
					result.put("SQSJ", rs.getString("SQSJ").substring(0,10));
					result.put("PJF", rs.getString("PJF"));
					result.put("WCGZJY", rs.getString("WCGZJY"));
					result.put("BINDID", rs.getString("BINDID"));
					data.add(result);
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
