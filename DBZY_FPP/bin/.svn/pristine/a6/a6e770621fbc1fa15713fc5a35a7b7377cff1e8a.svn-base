package com.nepharm.apps.fpp.biz.jcm.event;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.jcm.bean.YgxxBean;
import com.nepharm.apps.fpp.biz.jcm.dao.JCMGWGLDao;

import jodd.util.StringUtil;

/**
 * 岗位课程管理维护，表单保存后事件。
 * @author liuyc
 * 
 */

public class KMSkcglFormAfterSave extends ExecuteListener {

	public KMSkcglFormAfterSave() {
		setDescription("岗位课程管理维护，表单保存后事件。");
	}

	@Override
	public void execute(ProcessExecutionContext context) throws Exception{
		BOAPI boapi = SDK.getBOAPI();
		BO data = (BO) context.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
		if(data != null && StringUtil.isNotEmpty(data.getString("PZBM"))) {
			List<BO> gridData = SDK.getBOAPI().query("BO_DY_KMS_GWKC_S").bindId(data.getBindId()).list();
			String kcbm = null;
			if(gridData != null && !gridData.isEmpty()) {
				for(BO boData : gridData) {
					String bindId = boData.getBindId();
					String SFBX = boData.getString("SFBX");
					kcbm = boData.getString("KCBM");
					if(data != null) {
						String gsbm = data.getString("GSBM");
						String gwbm = data.getString("GWBM");
						JCMGWGLDao dao = new JCMGWGLDao();
						List<YgxxBean> userList = dao.getRyList(gsbm, gwbm);
						if(userList != null && !userList.isEmpty()) {
							for(YgxxBean user : userList) {
								BO bo1 = new BO();
								bo1.set("GSBM", gsbm);
								bo1.set("GSMC", data.getString("GSMC"));
								bo1.set("GWBM", gwbm);
								bo1.set("GWMC", data.getString("GWMC"));
								bo1.set("BKHRZH", user.getUserId());
								bo1.set("BKHRMC", user.getUserName());
								bo1.set("KCBM", kcbm);
								bo1.set("PZBM", data.getString("PZBM"));
								bo1.set("SFBX", SFBX);
								String sql = "SELECT BKHRZH FROM BO_DY_KMS_YGCJ_M WHERE BKHRZH='" +user.getUserId()+ "' AND KCBM='" + kcbm + "'";
								String zh = DBSql.getString(sql,"BKHRZH");
								if(StringUtil.isEmpty(zh))
									boapi.create("BO_DY_KMS_YGCJ_M", bo1, bindId, boData.getCreateUser());
							}
						}
					}
				}
			}
		}
		
	}
}
