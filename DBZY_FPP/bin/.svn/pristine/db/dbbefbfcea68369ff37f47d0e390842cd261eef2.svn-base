package com.nepharm.apps.fpp.biz.kms.event;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.kms.constant.KMSConstant;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

/**
 * 知识管理维护，表单保存后事件，在MC字段保存附件名称。
 * @author houwei
 * 
 */

public class KMSzsglFormAfterSave extends ExecuteListener {

	public KMSzsglFormAfterSave() {
		setDescription("知识管理维护，表单保存后事件，在MC字段保存附件名称；部门授权写入 知识文件部门授权表 BO_DY_KMS_ZSBMSQ");
	}

	@Override
	public void execute(ProcessExecutionContext context) throws Exception{
		Connection conn = DBSql.open();
		BOAPI boapi = SDK.getBOAPI();
		String ZSLX = "";
		List<BO> gridData = (List) context.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
		if(gridData != null) {
			Iterator<BO> itr = gridData.iterator();
			while(itr.hasNext()) {
				BO bo = itr.next();
				String boId = bo.getId();
				if("".equals(ZSLX)) {
					String bindId = bo.getBindId();
					BO formData = boapi.getByProcess(KMSConstant.TAB_KMS_ZSGL_M, bindId);
					ZSLX = formData.getString("ZSLX");
				}
				bo.set("MC", bo.get("WJMC"));
				bo.set("ZSLX", ZSLX);
				bo.set("BOID", boId);
				boapi.update(KMSConstant.TAB_KMS_ZSGL_S, bo, conn);
				
				//部门授权写入 知识文件部门授权表 BO_DY_KMS_ZSBMSQ
				String BMBHYDSQ = bo.getString("BMBHYDSQ");//阅读授权部门编号
				String BMBHSQ = bo.getString("BMBHSQ");//下载授权部门编号
				List<BO> list = new ArrayList();
				if(!StringUtil.isEmpty(BMBHYDSQ)) {
					String[] BMBHYDSQs = BMBHYDSQ.split(",");
					for(int i=0; i<BMBHYDSQs.length; i++) {
						BO data = new BO();
						data.set("BOID", boId);
						data.set("YDBMBH", BMBHYDSQs[i]);
						list.add(data);
					}
				}
				if(!StringUtil.isEmpty(BMBHSQ)) {
					String[] BMBHSQs = BMBHSQ.split(",");
					for(int i=0; i<BMBHSQs.length; i++) {
						BO data = new BO();
						data.set("BOID", boId);
						data.set("BMBH", BMBHSQs[i]);
						list.add(data);
					}
				}
				DBSql.update(conn, "delete BO_DY_KMS_ZSBMSQ where BOID = '"+boId+"'");
				boapi.createDataBO("BO_DY_KMS_ZSBMSQ", list, context.getUserContext(), conn);
			}
			
		}
		DBSql.close(conn);
	}

}
