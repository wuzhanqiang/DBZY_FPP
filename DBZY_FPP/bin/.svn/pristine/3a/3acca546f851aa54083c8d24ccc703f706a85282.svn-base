package com.nepharm.apps.fpp.biz.kms.event;

import java.sql.Connection;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExcelTransformListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.actionsoft.sdk.local.api.RuleAPI;

public class SjdaFormExcelTransform extends ExcelTransformListener {

	public SjdaFormExcelTransform() {
		setDescription("导入试题后，对每个试题生成UUID，保存在CODE字段中");
	}

	@Override
	public Workbook fixExcel(ProcessExecutionContext ctx, Workbook wb) {
		String timeState = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_EXCEL_TIMESTATE);
		if(ListenerConst.FORM_EXCEL_TIMESTATE_IMPORT_AFTER.equals(timeState)) {
			Connection conn = DBSql.open();
			try {
				BOAPI boApi = SDK.getBOAPI();
				RuleAPI ruleApi = SDK.getRuleAPI();
				String bindId = ctx.getProcessInstance().getId();
				List<BO> list = boApi.query("BO_DY_KMS_SJDA_S").addQuery("BINDID = ", bindId).list();
				if(list != null) {
					for(BO bo : list) {
						String CODE = ruleApi.executeAtScript("@uuid");
						bo.set("CODE", CODE);
						boApi.update("BO_DY_KMS_SJDA_S", bo, conn);
					}
				}				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBSql.close(conn);
			}
		}
		return wb;
	}

}
