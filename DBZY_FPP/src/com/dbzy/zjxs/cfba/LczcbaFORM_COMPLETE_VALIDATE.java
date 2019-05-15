package com.dbzy.zjxs.cfba;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.dbzy.zjxs.po.LccpzcbaPO;


public class LczcbaFORM_COMPLETE_VALIDATE extends InterruptListener implements InterruptListenerInterface{

	@Override
	public boolean execute(ProcessExecutionContext arg0) throws Exception {
		ProcessInstance proIns = arg0.getProcessInstance();
		String proInsId = proIns.getId();
		BOAPI boapi = SDK.getBOAPI();
		
		List<BO> datas = boapi.query("BO_DY_ZDZC_LCZC_S").addQuery("BINDID = ", proInsId).list();
		List<LccpzcbaPO> lczcList = new ArrayList<LccpzcbaPO>();
		if(datas != null && !datas.isEmpty()) {
			for(BO data : datas) {	
				LccpzcbaPO lp = new LccpzcbaPO();
				String dls = data.getString("DLS");
				lp.setDls(dls);	
				lczcList.add(lp);
			}
		}
		Connection conn = null;
		String sql = null;
		int cnt = 0;
		StringBuffer errDLS = new StringBuffer();
		try {
			conn = DBSql.open();
			for(LccpzcbaPO po : lczcList) {
				sql = "select count(JXSXM) cnt from BO_DY_CWB_XZJXS_P  where lczt = '已完成' and JXSXM ='"
						+ po.getDls() + "'";
			    cnt = DBSql.getInt(conn,sql, "cnt");
				if (cnt == 0) {
					errDLS.append(po.getDls());
					errDLS.append(","); //遍历上面代理商集合 ，查数cnt, 不符合的拼到errDLS字符串里
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBSql.close(conn);
		}
		if (errDLS.toString().length() > 0) {
			throw new BPMNError("ERR_DLS","请按照公司要求填写负责代理商，问题代理商为：" + errDLS.toString());			
		}	
		return true;
	}
	
}
