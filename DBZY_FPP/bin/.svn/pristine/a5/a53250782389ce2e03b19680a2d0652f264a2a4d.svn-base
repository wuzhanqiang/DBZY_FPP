package com.nepharm.apps.fpp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.actionsoft.bpms.util.DBSql;


public class DateUtil {
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(string2UtilDate("2010-02-23 00:00:00"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	     * 获取现在时间
	     * 
	     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	     */
	  public static String getStringDate(java.util.Date date) {
	    
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	     String dateString = formatter.format(date);
	     return dateString;
	  }
	/** 
    * 获取当前日期是星期几
    *  
    * @param dt 
    * @return 当前日期是星期几 
    */  
	public static String getWeekOfDate(java.util.Date dt) {  
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};  
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(dt);  
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;  
	    if (w < 0)
	    	w = 0;  
	    return weekDays[w];  
	} 
	/**
	 * 获取下一天
	 * @param dt
	 * @return
	 */
	public static java.util.Date getNextDate(java.util.Date dt){
        return getNextDate(dt,1);
	}
	/**
	 * 获取未来指定的第几天
	 * @param dt
	 * @param num
	 * @return
	 */
	public static java.util.Date getNextDate(java.util.Date dt,int num){
		Calendar c = Calendar.getInstance();  
        c.setTime(dt);  
        c.add(Calendar.DAY_OF_MONTH, num);// 今天+num天  
        java.util.Date tomorrow = c.getTime(); 
        return tomorrow;
	}
	   
	   
	public static java.sql.Date string2SqlDate(String date) throws ParseException{
		
		java.util.Date utilDate=string2UtilDate(date);
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	
	public static java.util.Date string2UtilDate(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		java.util.Date utilDate=sdf.parse(date); 
		return utilDate;
	}
	
	
	public static int getYearOfUpMonth(String year,String month){
		int m=Integer.parseInt(month);
		int y = Integer.parseInt(year);
		m=m-1;
		if(m==0){
			y=y-1;
		}
		return y;
	}
	/**
	 * 根据月份（02）返回季度
	 * @param month
	 * @return
	 */
	public static String getQuarter(int month){
		String m="";
		if(month<=9){
			m="0"+month;
		}else{
			m=month+"";
		}
		return getQuarter(m);
	}
	/**
	 * 上个月是属于哪个季度
	 * @param month
	 * @return
	 */
	public static String getUpMonthQuarter(String month){
		int m=Integer.parseInt(month);
		m=m-1;
		if(m==0){
			m=12;
		}
		return getQuarter(m);
	}
	/**
	 * 根据月份（02）返回季度
	 * @param month
	 * @return
	 */
	public static String getQuarter(String month){
		
		String fir="01,02,03";
		String sec="04,05,06";
		String thi="07,08,09";
		String fou="10,11,12";
		
		if(fir.indexOf(month)>=0){
			return "1";
		}else if(sec.indexOf(month)>=0){
			return "2";
		}else if(thi.indexOf(month)>=0){
			return "3";
		}else if(fou.indexOf(month)>=0){
			return "4";
		}else{
			return null;
		}
	}
	
	/**
	 * 获取上个月所在的年份、月份
	 * @return
	 */
	public static String[] getLastDatInfoe(){
		String sql1="select to_char(add_months(sysdate,-1),'mm') as M from dual";
		String sql2="select to_char(add_months(sysdate,-1),'yyyy') as Y from dual";
		String month = DBSql.getString(sql1,"M");
		String year = DBSql.getString(sql2,"Y");
		return new String[]{year,month};
		
	}
	
	public static void main(String[] args) {
		System.out.println(getQuarter("04"));
	}
}
