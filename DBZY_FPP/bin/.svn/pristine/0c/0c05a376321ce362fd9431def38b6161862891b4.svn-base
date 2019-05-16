/*
 * 法定假日加班申请流程,子流程主表数据插入主流程子表
 * 创建人：李鑫
 * 创建日期：2018-05-25
 * 触发器：TASK_AFTER_COMPLETE
 */
package com.nepharm5.apps.fpp.ylyscsyb.biz.gcxmgl.jxgcyjh;

import java.io.InputStream;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.commons.formfile.model.delegate.FormFile;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;

public class YlyjxgcyjhTAfter extends ExecuteListener implements ExecuteListenerInterface {

	public YlyjxgcyjhTAfter() {
		// TODO Auto-generated constructor stub
		this.setDescription("检修工程月计划流程,子流程数据插入主流程子表");
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}


	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ProcessInstance pis = ctx.getProcessInstance();
		String parentId = pis.getParentProcessInstId();
		String parenttaskId = pis.getParentTaskInstId();
		String bindid = pis.getId();
		String uid = pis.getCreateUser();
		boolean sh = ctx.isChoiceActionMenu("同意");
		BO data = SDK.getBOAPI().getByProcess("BO_DY_YLYSCSYB_GCJHSB_ZI", bindid);
		String boid = DBSql.getString("select ID from BO_DY_YLYSCSYB_GCJHSB_ZI where bindid = '"+bindid+"'", "ID");
		if(sh) {
			if(data!=null) {
				String TJR = data.get("TJR")==null?"":data.get("TJR").toString();
				String TJBM = data.get("TJBM")==null?"":data.get("TJBM").toString();
				BO newData = new BO();
				newData.set("TJR", TJR);
				newData.set("TJBM", TJBM);
				int i = SDK.getBOAPI().create("BO_DY_YLYSCSYB_GCJHSB_Z_S", newData, parentId, uid);
				String sql = "select * from BO_DY_YLYSCSYB_GCJHSB_Z_S where bindid = '"+parentId+"' and createuser = '"+uid+"'";
				String newId = DBSql.getString(sql, "ID");
				SDK.getBOAPI().copyFileTo(boid, "JXJH", String.valueOf(newId), "BO_DY_YLYSCSYB_GCJHSB_Z_S", "JXJH", parentId, null);
			}
		}
	}

}
