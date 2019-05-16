package com.nepharm.apps.fpp.biz.tam.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.dw.design.event.DataWindowFormatGridEventInterface;
import com.actionsoft.bpms.dw.exec.data.DataSourceEngine;
import com.actionsoft.bpms.dw.exec.data.grid.Grid;
import com.actionsoft.bpms.dw.exec.data.grid.GridColumn;
import com.actionsoft.bpms.dw.exec.data.grid.GridRow;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;

public class RwcxDWFormatGridEvent implements
		DataWindowFormatGridEventInterface {

	public RwcxDWFormatGridEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Grid formatGrid(UserContext arg0, Grid grid) {
		List v = grid.getRows();
	    for (Iterator it = v.iterator(); it.hasNext();) { // 遍历每行数据
	    	GridRow gridRow = (GridRow) it.next();
	    	List columns = gridRow.getCols();
	    	GridColumn gridColumn = gridRow.getColumn("RWMC2");
	    	String RWBH = gridRow.getColumn("RWBH2").getColumnValue();
	    	String RWMC = gridColumn.getColumnValue();
	    	List<BO> list = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_MAIN").addQuery("RWBH = ", RWBH).list();
			if(list.size()==0){
				RWMC = "<span style=\"color:#000000;cursor:pointer;\" >"+RWMC+"</span>";
				System.out.println(RWMC);
				gridColumn.setColumnValue(RWMC, true);
			}
	    	
	    	/*List v1 = gridRow.getCols();
	    	List<GridColumn> lgR = new ArrayList<GridColumn>();
	    	for (Iterator it1 = v1.iterator(); it1.hasNext();) { // 遍历此行的每一列数据
	    		GridColumn gridColumn = (GridColumn) it1.next(); // 取到该行该列的字段属性对象
	    		String columnName = gridColumn.getColumnName(); // 取到该行该列的字段名称
	    		String columnValue = gridColumn.getColumnValue(); // 取到该行该列的字段值
	    		//int doFormat = 0; // di
	    		if ("RWBH2".equals(columnName)) {
	        	
	    			List<BO> list = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_MAIN").addQuery("RWBH = ", columnValue).list();
	    			if(list.size()==0){
	    				columnValue = "<span style=\"color:#000000;cursor:pointer;\" >"+columnValue+"测试</span>";
	    				System.out.println(columnName+"="+columnValue);
	    			}
	    			GridColumn newGridColumn = new GridColumn();
	    			//newGridColumn.setColumnName(columnName + DataSourceEngine.AWS_DW_FIXED_CLOMUN_SHOW_RULE_SUFFIX); // 重新设置并格式化字段名
	    			newGridColumn.setColumnValue(columnValue, true); // 设置字段值
	    			lgR.add(newGridColumn);
	    			//doFormat++;
	    		}
	        
//	    		if (doFormat == 13) {
//	    			break;
//	    		}
	    	}
	    	for (GridColumn newGridColumn : lgR) {
	    		gridRow.putColumn(newGridColumn); // 把新的GridColumn放到容器中
	    	}*/
	      
	    }
	    return grid;
	    
	}
	

}
