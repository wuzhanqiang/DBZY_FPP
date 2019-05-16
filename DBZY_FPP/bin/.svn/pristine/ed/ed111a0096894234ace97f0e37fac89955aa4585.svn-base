package com.nepharm.apps.fpp.biz.gm.event;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

public class JlqjjdFormAfterSaveStep3 extends ExecuteListener {

	public JlqjjdFormAfterSaveStep3() {
		setDescription("计量器具检定节点3，保存表单，将有效期同步给计量器具档案");
	}

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		Connection conn = DBSql.open();
		ProcessInstance proInst = context.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		BO formData = boapi.getByProcess(GMConstant.TAB_GM_JLQJJD_M, proInstId);
		String BMBH = formData.getString("BMID");
		String BM = formData.getString("BM");
		List<BO> gridData = (List) context.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
		if(gridData != null && !gridData.isEmpty()) {
			for(BO bo : gridData) {
				String WPBH = bo.getString("WPBH");
				String NKBH = bo.getString("NKBH");
				String WPMC = bo.getString("WPMC");
				String JDJG = bo.getString("JDJG");
				Date YXQ  = (Date) bo.get("YXQ");
				String ZSBH = bo.getString("ZSBH");
				if(!StringUtil.isEmpty(JDJG) && "合格".equals(JDJG) && YXQ != null && !"".equals("ZSBH")) {
					String yxq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(YXQ);
					String sql = "update "+GMConstant.TAB_GM_JLQJDA+" "
							+ "set JHSYRQ = to_date('"+yxq+"', 'yyyy-mm-dd hh24:mi:ss'), ZSBH = '"+ZSBH+"', ZT = '"+JDJG+"' "
							+ "where BMBH = '"+BMBH+"' and WPBH = '"+WPBH+"' and NKBH = '"+NKBH+"'";
					DBSql.update(conn, sql);
				}
			}
		}
		DBSql.close(conn);
	}

}
