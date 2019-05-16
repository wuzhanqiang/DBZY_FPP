package com.nepharm.apps.fpp.biz.pem.util;

import com.actionsoft.bpms.util.DBSql;

/**
 * 绩效专用工具类
 * @author lizhen
 *
 */
public class PerformanceUtil {
	/**
	 * 查找是否有数据存在，ID不等于boId
	 * @param tabName
	 * @param colName
	 * @param value
	 * @param boId
	 * @return
	 */
	public static boolean haveData(String tabName,String colName,String value,String boId){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(ID) NUM from "+tabName+" where "+colName+"='"+value+"'");
		sql.append(" and id <>'"+boId+"' ");
		String numStr=DBSql.getString(sql.toString(),"NUM");
		if(numStr==null||"".equals(numStr)||"0".equals(numStr))
			return false;
		return true;
	}
}
