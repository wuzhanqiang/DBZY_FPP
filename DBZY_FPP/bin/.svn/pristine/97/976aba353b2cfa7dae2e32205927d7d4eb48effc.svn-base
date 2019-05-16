package com.nepharm5.apps.fpp.cw;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.util.DBSql;

public class ndys_update  extends ExecuteListener implements ExecuteListenerInterface{

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		String bindid = ctx.getProcessInstance().getId();
		String sql;
		sql = "select bh,bcyszjsped from BO_DY_CW_NDYSZJ_P where bindid = '" + bindid +"'";
		double yszjsped = DBSql.getDouble(sql, "bcyszjsped");//---------------获取追加预算金额
		int bh = DBSql.getInt(sql, "bh");//------------------获取申请预算编号
		sql="select ndysed from BO_DY_CW_BMNDYS_S t where t.bh='"+bh+"'";
		double ndysed=DBSql.getDouble(sql, "ndysed");
		double zjed=ndysed+yszjsped;
		//-----------------------------------------------------------更新年度预算基础资料表
		sql = "update BO_DY_CW_BMNDYS_S s set s.ndysed='" + zjed
				+ "' where s.bh='" + bh + "'";
		DBSql.update(sql);
	}

}
