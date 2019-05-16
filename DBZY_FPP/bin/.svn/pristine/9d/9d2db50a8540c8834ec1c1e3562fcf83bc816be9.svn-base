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

public class ZjysLjhjRtclass extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;
	private Connection conn;
	private Statement stmt;
	
	public ZjysLjhjRtclass() {
		// TODO Auto-generated constructor stub
	}

	public ZjysLjhjRtclass(UserContext uc) {
		super();
		this.uc = uc;
		setDescription("保存后为审批金额赋值");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//获取流程实例Id
		String bindid = ctx.getProcessInstance().getId();
		
		//获取子表数据
		List<BO> boList = SDK.getBOAPI().query("BO_DY_NEPG_ZJYS_S").bindId(bindid).list();
		
		List sqlList = new ArrayList();
		if(boList !=null && boList.size() > 0){
			for (int i = 0; i < boList.size(); i++) {
				BO bo = boList.get(i);
				String id = bo.get("ID")==null?"":bo.get("ID").toString();
				String zjje = bo.get("ZJJE")==null?"":bo.get("ZJJE").toString();//获取子表记录的追加金额
				if(!zjje.equals("") && !id.equals("")){
					String sql = "update BO_DY_NEPG_ZJYS_S set spje ='"+zjje+"' where id ='"+id+"'";
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
