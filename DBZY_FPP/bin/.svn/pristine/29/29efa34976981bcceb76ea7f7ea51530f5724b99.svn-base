package com.nepharm.apps.fpp.biz.ppm.event;

import com.actionsoft.bpms.dw.design.event.DataWindowFormatDataEventInterface;
import com.actionsoft.bpms.dw.exec.data.DataSourceEngine;
import com.actionsoft.bpms.server.UserContext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nepharm.apps.fpp.util.NumberUtil;
/**
 * 月生产计划完成情况  初始化字段：完成率
 * @author lizhen
 *
 */
public class MonthProductFormatDataEvent implements DataWindowFormatDataEventInterface{

	/**
	 * 格式化表格数据的触发器
	 * 
	 * @param me 用户上下文
	 * @param JSONArray 数据对象 
	 * 说明:
	 * 1.必须实现类 com.actionsoft.bpms.dw.design.event.DataWindowFormatDataEventInterface 
	 * 2.此示例实现的是 : 如果字段"ZT",为0显示错误图标,为1显示正确图标,为其他显示警示图标 
	 * 3.如果需要在数据导出时应用格式化数据请实现formatGridExport方法（如果不需要则不实现）
	 */
	@Override
	public void formatData(UserContext me, JSONArray datas) {
		for (Object datao : datas) {
			JSONObject data = (JSONObject) datao;
			String columnValue1 = data.getString("DDL"); 
			String columnValue2 = data.getString("RKL"); 
			double ddl=0,rkl=0,wcl=0;
			try {
				ddl=Double.parseDouble(columnValue1);
			} catch (NumberFormatException e) {
				ddl=0;
			} 
			try {
				rkl=Double.parseDouble(columnValue2);
			} catch (NumberFormatException e) {
				rkl=0;
			} 
			if(rkl==0||ddl==0){
				wcl=0;
			}else{
				try {
					wcl=rkl/ddl*100;
				} catch (Exception e) {
					wcl=0;
				}
			}
			data.put("WCL" + DataSourceEngine.AWS_DW_FIXED_CLOMUN_SHOW_RULE_SUFFIX, NumberUtil.doubleFormat(wcl, 2)+"%");
			data.put("K3GSBM" + DataSourceEngine.AWS_DW_FIXED_CLOMUN_SHOW_RULE_SUFFIX, NumberUtil.doubleFormat(wcl, 2)+"%");
		}
	}
}
