package com.nepharm.apps.fpp.util;

public class StringUtil {
	/**
	 * 将 id1,id2,id3 转换成'id1','id2','id3'
	 * @param ids
	 * @return
	 */
	public static String connString(String ids){
		String returnString="";
		for(String id:ids.split(",")){
			
			returnString=returnString+"'"+id+"',";
		}
		returnString=returnString.substring(0, returnString.length() - 1);
		return returnString;
	}
}
