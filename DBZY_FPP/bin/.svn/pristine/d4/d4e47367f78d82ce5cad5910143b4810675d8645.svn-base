package com.nepharm5.apps.fpp.nepgSanqi.biz.rzdb;

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

public class RzdbZbqhFormAfterSave extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;

	public RzdbZbqhFormAfterSave() {
		// TODO 自动生成的构造函数存根
	}

	public RzdbZbqhFormAfterSave(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("融资担保,子表相乘求和");
		setVersion("V1.0");
		
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindid = ctx.getProcessInstance().getId();
		//String taskid = ctx.getTaskInstance().getId();//获取任务实例Id
		
		List<BO> boList = SDK.getBOAPI().query("BO_DY_NEPG_RZDBSQ_S").bindId(bindid).list();
		List sqlList = new ArrayList();
		if(boList!=null && boList.size() > 0){
			for (int i = 0; i < boList.size(); i++) {
				BO ht = boList.get(i);
				String id = ht.get("ID")==null?"":ht.get("ID").toString();
				double SXJEYB = Double.valueOf(ht.get("SXJEYB")==null?"":ht.get("SXJEYB").toString());
				double HL = Double.valueOf(ht.get("HL")==null?"":ht.get("HL").toString());
				
				
				if(SXJEYB!=0 &&HL!=0 && !id.equals("")){
					String SXJEBWB=Double.toString(SXJEYB*HL);
					String sql = "update BO_DY_NEPG_RZDBSQ_S set SXJEBWB ='"+SXJEBWB+"' where id = '"+id+"'";
					sqlList.add(sql);
				}
			}
		}
		//更新数据
		try {
			conn = DBSql.open();
			stmt = conn.createStatement();
			for (int i = 0; i < sqlList.size(); i++) {
				stmt.addBatch(sqlList.get(i).toString());
			}
			int [] result = stmt.executeBatch();//执行更新
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.err);
		}finally{
			DBSql.close(conn, stmt, null);
		}
	}

}
