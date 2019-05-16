package com.nepharm.apps.fpp.biz.gm.event;

import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.UserTaskModel;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerAbst;
import com.actionsoft.bpms.bpmn.engine.performer.HumanPerformerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.gm.constant.GMConstant;
import com.nepharm.apps.fpp.biz.gm.util.GMUtil;
/**
 * 根据申请的物品类型查找相应的办理者
 * @author sydq
 *
 */
public class WpsqRouteStep3 extends HumanPerformerAbst implements HumanPerformerInterface {

	public WpsqRouteStep3() {
	}

	@Override
	public String getHumanPerformer(UserContext uc, ProcessInstance proInst, TaskInstance taskInst, UserTaskModel userTaskModel,
			Map<String, Object> params) {
		String proInstId = proInst.getId();
		BO bo = SDK.getBOAPI().getByProcess(GMConstant.TAB_GM_WPSQ_M, proInstId);
		String WPLX = bo.getString("WPLX");
		String departmentId = "";
		String roleName = "";
		switch(WPLX) {
			case "低值易耗品":
//				departmentId = "cd33e26c-ad2d-4114-9991-769666dafadb";//东北制药集团股份有限公司/采购物流中心
				departmentId = "5451b5f4-9453-4971-acbd-faa9768bed3c";//采购物流中心/采购物流中心（股份）/采购物流中心物流（股份）/采购物流中心物流办公室（股份）
				roleName = "普通员工";
				break;
			case "备品备件":
				departmentId = "c8c2bc11-d37a-4fc3-993e-4e3af245a65f";// 东北制药集团股份有限公司/专业部室/装备部/装备部（一药）/
				roleName = "总经理";
				break;
			case "计量器具":
				departmentId = "c8c2bc11-d37a-4fc3-993e-4e3af245a65f";// 东北制药集团股份有限公司/专业部室/装备部/装备部（一药）/
				roleName = "总经理";
				break;
			case "办公用品":
//				departmentId = "b8d65f52-1ae8-4119-ba78-76ee520d98a4";// 东北制药集团股份有限公司/专业部室/总裁办公室
				departmentId = "5451b5f4-9453-4971-acbd-faa9768bed3c";//采购物流中心/采购物流中心（股份）/采购物流中心物流（股份）/采购物流中心物流办公室（股份）
				roleName = "普通员工";
				break;
		}
		String userId = GMUtil.getUserId(departmentId, roleName);
		return userId;
	}

	@Override
	public String getSetting(UserContext arg0, Map<String, Object> arg1) {
		return "";
	}

}
