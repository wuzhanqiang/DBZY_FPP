package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.dw.design.event.DataWindowFormatSQLEventInterface;
import com.actionsoft.bpms.dw.exec.component.DataView;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.util.DateUtil;
/**
 * 季度考核数据统计结果
 * @author lizhen
 *
 */
public class QuarterDataSQLEvent implements DataWindowFormatSQLEventInterface {

	@Override
	public String formatSQL(UserContext me, DataView view, String sql) {
		//select YEAR,SSJD,GSBM,GSMC,GWMC,BKHRZH,BKHR,ZF from VIEW_DY_JXKH_LSCJ where GSBM='000001' and  1=1 
		
		String[] where= sql.split("from");
		String sql2="select count(*) NUM from "+where[1];
		int num=DBSql.getInt(sql2, "NUM");
		String select="(case when row_number() over(ORDER BY ZF desc)/"+num+"<0.2 then 'A' "
				+ "when row_number() over(ORDER BY ZF desc)/"+num+"<0.6 then 'B' "
						+ "when row_number() over(ORDER BY ZF desc)/"+num+"<0.9 then 'C'"
								+ " else 'D' end ) DJ";
		where[0] = where[0].replace("DJ", "DJ as DJX "); 
		sql = where[0]+","+select+" from "+where[1];
		String yearStr = SDK.getRuleAPI().executeAtScript("@year");
		String month=SDK.getRuleAPI().executeAtScript("@month");
		int year = DateUtil.getYearOfUpMonth(yearStr,month);
		sql=sql.replace(" 1=1 ", " YEAR='"+year+"' and SSJD='"+DateUtil.getUpMonthQuarter(month)+"' ");
		System.out.println(sql);
	    return sql; 
	}

}
