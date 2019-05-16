package com.nepharm.apps.fpp.biz.pem.controller;

import com.actionsoft.sdk.local.SDK;

public class PerformanceBiz {
	
	/**
	 * 获取从2018-今的所有年份，并生成select的选项今年默认被选中
	 * @return
	 */
	public static String getYearList(){
		String def="<option value='2018' selected>2018年</option>";
		StringBuffer op = new StringBuffer();
		String year = SDK.getRuleAPI().executeAtScript("@year");
		int nowYear=Integer.parseInt(year);
		int startYear=2018;
		int sub = nowYear-startYear;
		if(sub<=0){
			return def;
		}
		for(int i=sub;i>=0;i--){
			if(i==sub){
				op.append("<option value='"+(startYear+i)+"' selected>"+(startYear+i)+"年</option>");
			}else{
				op.append("<option value='"+(startYear+i)+"'>"+(startYear+i)+"年</option>");
			}
		}
		return op.toString();
	}
	public static String getMonthList(){
		StringBuffer op = new StringBuffer();
		String nowMonth = SDK.getRuleAPI().executeAtScript("@month");
		
		for(int i=1;i<=12;i++){
			String month="";
			if(i<10){
				month="0"+i;
			}else{
				month=""+i;
			}
			if(month.equals(nowMonth)){
				op.append("<option value='"+month+"' selected>"+month+"月</option>");
			}else{
				op.append("<option value='"+month+"'>"+month+"月</option>");
			}
		}
		return op.toString();
	}
}
