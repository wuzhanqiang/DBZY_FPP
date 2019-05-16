package com.nepharm5.apps.fpp.nepgErqi.biz.zcgl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm5.apps.fpp.nepg.msg.WjpsMsg;

public class GdzcCzRtclass extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	
	/*public GdzcCzRtclass() {
		// TODO Auto-generated constructor stub
	}
	
	public GdzcCzRtclass(UserContext uc) {
		super();
		this.uc = uc;
		setVersion("V1.0");
		setDescription("计算资产出租率");
	}*/
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "计算资产出租率";
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//获取流程实例Id
		String bindid = ctx.getProcessInstance().getId();
		
		//获取子表数据
		List<BO> boList = SDK.getBOAPI().query("BO_DY_NEPG_GDZCCZ_S").bindId(bindid).list();
		
		List sqlList = new ArrayList();
		if(boList !=null && boList.size() > 0){
			for (int i = 0; i < boList.size(); i++) {
				BO bo = boList.get(i);
				String zcsl = bo.get("ZCSL")==null?"":bo.get("ZCSL").toString();
				String czsl = bo.get("CZSLMJ")==null?"":bo.get("CZSLMJ").toString();
				String id = bo.get("ID")==null?"":bo.get("ID").toString();
				double zc=0;//资产数量面积
				double cz = 0;//出租数量面积
				if(!zcsl.equals("")){
					zc = Double.valueOf(zcsl);
				}
				if(!czsl.equals("")){
					cz = Double.valueOf(czsl);
				}
		
	
				//资产出租率=出租数量面积/资产数量面积
				double czl = cz/zc;

				String t = String.format("%.4f",czl);
				String updateSql = "update BO_DY_NEPG_GDZCCZ_S set zcczl = '"+t+"' where id = '"+id+"'";
				sqlList.add(updateSql);
			}

		}
		
		//执行修改
		conn = DBSql.open();
		
		try {
			if(sqlList.size() > 0) {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				for (int i = 0; i < sqlList.size(); i++) {
					stmt.addBatch(sqlList.get(i).toString());
				}
				int [] result = stmt.executeBatch();
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DBSql.close(conn, stmt, null);
			e.printStackTrace(System.err);
		}finally{
			DBSql.close(conn, stmt, null);
		}
	}

}
