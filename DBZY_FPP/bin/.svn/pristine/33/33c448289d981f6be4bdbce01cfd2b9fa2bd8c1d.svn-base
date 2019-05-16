package com.nepharm.apps.fpp.biz.kms.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.actionsoft.bpms.dw.design.event.DataWindowFormatGridEventInterface;
import com.actionsoft.bpms.dw.exec.data.DataSourceEngine;
import com.actionsoft.bpms.dw.exec.data.grid.Grid;
import com.actionsoft.bpms.dw.exec.data.grid.GridColumn;
import com.actionsoft.bpms.dw.exec.data.grid.GridRow;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.kms.util.KMSUtil;

public class KMSDWFormatGridEvent implements DataWindowFormatGridEventInterface {

	public KMSDWFormatGridEvent() {
		
	}

	@Override
	public Grid formatGrid(UserContext me, Grid grid) {
		String userId = me.getUID();
		String sid = me.getSessionId();
		String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		List<String> downloadList = KMSUtil.getDownloadSQ(GWBM, me);//可下载的附件BOID集合
		List<GridRow> rows = grid.getRows();
		for(Iterator it = rows.iterator() ; it.hasNext();) {// 遍历每行数据
			GridRow gridRow = (GridRow) it.next();
			List columns = gridRow.getCols();
			String fileNameOriginal = gridRow.getColumn("WJMC").getColumnValue();
			String boId = gridRow.getColumn("BOID").getColumnValue(); 
			
			// 在线阅读 
			GridColumn gridColumn = gridRow.getColumn("YD");	// 取到 阅读 字段列对象
			String columnName = gridColumn.getColumnName();		// 取到 阅读 的字段名称
			String columnValue = gridColumn.getColumnValue();	// 取到 阅读 的字段值
			
			String url = "./w?sid="+sid+"&cmd=com.nepharm.apps.fpp.mykms.filepreviewurl&boId="+boId+"&WJMC="+fileNameOriginal;
			columnValue = "<a href='#' style='color:black;font-size:12px;' onclick=\"window.open('"+url+"', '_blank')\">在线阅读</a>";
			gridColumn.setColumnValue(columnValue, true);
			
//			GridColumn newGridColumn = new GridColumn();
//			newGridColumn.setColumnName(columnName+DataSourceEngine.AWS_DW_FIXED_CLOMUN_SHOW_RULE_SUFFIX);
//			newGridColumn.setColumnValue(columnValue, true);
//			gridRow.putColumn(newGridColumn);// 把新的GridColumn放到容器中
			
			// 按照授权，取消下载附件
			if(!downloadList.contains(boId)) {
				gridColumn = gridRow.getColumn("WJMC");
				gridColumn.setColumnValue("", true);
			}
		}
		
		return grid;
	}

}
