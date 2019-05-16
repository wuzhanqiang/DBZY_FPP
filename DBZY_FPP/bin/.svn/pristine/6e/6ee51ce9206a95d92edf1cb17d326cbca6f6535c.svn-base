package com.nepharm.apps.fpp.biz.ppm.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.bean.KPIParamBean;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 * 周计划-办理后事件
 * 任务完成（结束）后被触发
 * @author lizhen
 *
 */
public class WeekPlanAfterComplete 
	extends ExecuteListener 
	implements ExecuteListenerInterface{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindId=ctx.getProcessInstance().getId();
		String boName=ProductPlanConstant.TAB_SCJH_ZSCJH_M;
		String boSubName=ProductPlanConstant.TAB_SCJH_ZSCJH_S;
		String boId=DBSql.getString("select ID from "+boName+" where bindid='"+bindId+"'");//获取主表boID
		String gsbm=null;
		try {
			gsbm=SDK.getBOAPI().get(boName, boId).getString("GSBM");//公司编码
			if(gsbm==null){
				return ;
			}
		} catch (Exception e) {
			return ;
		}
		String sql= "select RQ from "+boSubName +" where bindid='"+bindId+"' ";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String rq=rs.getString("RQ");//获取参数名称
					rq=rq.substring(0, 10);
					String sql2="update "+boSubName+" set isend='0' where id in ("+
					"select ID from VIEW_DY_SCJH_ZJH where RQ=to_date('"+rq+"','yyyy-MM-dd') and GSBM='"+gsbm+"')";
					DBSql.update(sql2);
				} catch (Exception e) {
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
	}
	@Override
	public String getDescription() {
		return "周生产计划 -办理后事件,修改重复数据的状态";
	}
	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
}
