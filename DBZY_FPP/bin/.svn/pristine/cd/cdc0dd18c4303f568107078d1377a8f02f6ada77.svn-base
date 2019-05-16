package com.nepharm.apps.fpp.util;

import java.math.BigDecimal;

public class NumberUtil {
	/**
	 * 四舍五入向上取整
	 * @param number   目标数
	 * @param num       保留小数点后几位
	 * @return
	 */
	public  static String doubleFormat(double number,int num){
		if(num<=-1){
			num=0;
		}
		BigDecimal b = new BigDecimal(number);
		return	b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue()+"";

	}
}
