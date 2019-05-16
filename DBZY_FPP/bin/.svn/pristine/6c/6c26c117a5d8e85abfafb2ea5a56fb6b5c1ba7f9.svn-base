package com.nepharm.apps.fpp.biz.gm.event;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

public class JljqjdTaskBeforeCompleteStep3 extends InterruptListener implements InterruptListenerInterface {

	public JljqjdTaskBeforeCompleteStep3() {
		setDescription("计量器具检定节点3办理后，将有效期同步给计量器具档案");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		ProcessInstance proInst = ctx.getProcessInstance();
		String proInstId = proInst.getId();
		BOAPI boapi = SDK.getBOAPI();
		String BMBH = (String) boapi.getByProcess(GMConstant.TAB_GM_JLQJJD_M, proInstId, "BMID");
		List<BO> datas = boapi.query(GMConstant.TAB_GM_JLQJJD_S).addQuery("BINDID = ", proInstId).list();
		List<BO> errList = new ArrayList();
		List<BO> correctList = new ArrayList();
		if(datas != null && !datas.isEmpty()) {
			for(BO bo : datas) {
				String JDJG = bo.getString("JDJG");
				Date YXQ  = (Date) bo.get("YXQ");
				if(!StringUtil.isEmpty(JDJG) && 
						(("合格".equals(JDJG) && YXQ != null && !"".equals("ZSBH")) || !"合格".equals(JDJG))) {
					correctList.add(bo);
				}else {
					errList.add(bo);
				}
			}
		}
		
		if(errList != null && !errList.isEmpty()) {
			StringBuffer content = new StringBuffer();
			for(BO errBO : errList) {
				String WPBH = errBO.getString("WPBH");
				String NKBH = errBO.getString("NKBH");
				String WPMC = errBO.getString("WPMC");
				String JDJG = errBO.getString("JDJG");
				String ZSBH = errBO.getString("ZSBH");
				Date YXQ  = (Date) errBO.get("YXQ");
				if("".equals(JDJG)) {
					content.append("内控编号："+NKBH+"， "+WPBH+" "+WPMC+"的“检定结果”未填写！\n");
				}else if("合格".equals(JDJG)) {
					content.append("内控编号："+NKBH+"， "+WPBH+" "+WPMC+"的“有效期至”和“证书编号”不能为空！\n");
				}
			}
			throw new BPMNError("ERR_JLQJJD",content.toString());
		}else {
			Connection conn = DBSql.open();
			for(BO bo : correctList) {
				String WPBH = bo.getString("WPBH");
				String NKBH = bo.getString("NKBH");
				String WPMC = bo.getString("WPMC");
				String JDJG = bo.getString("JDJG");
				Date YXQ  = (Date) bo.get("YXQ");
				String ZSBH = bo.getString("ZSBH");
				String yxq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(YXQ);
				String sql = "update "+GMConstant.TAB_GM_JLQJDA+" "
						+ "set JHSYRQ = to_date('"+yxq+"', 'yyyy-mm-dd hh24:mi:ss'), ZHBH = '"+ZSBH+"', JDJG = '"+JDJG+"' "
						+ "where BMBH = '"+BMBH+"' and WPBH = '"+WPBH+"' and NKBH = '"+NKBH+"'";
				DBSql.update(conn, sql);
			}
			DBSql.close(conn);
			return true;
		}
	}

}
