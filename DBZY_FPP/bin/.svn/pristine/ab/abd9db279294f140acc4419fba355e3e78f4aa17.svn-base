package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.exception.BPMNError;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.util.PerformanceUtil;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 *  保存前校验岗位是否重复配置（KPI、职业素质、系数、考核人）
 * @author lizhen
 *
 */
public class CommonFormBeforeSaveEvent extends InterruptListener {

	@Override
	public boolean execute(ProcessExecutionContext param) throws Exception {
		
		String boId="";
		try {
			boId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		} catch (Exception e) {
			boId="";
		}
		
		String boName = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		
		BO formData = (BO) param.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
		
		boolean flag=false;
		if(boName==null){
			flag=true;
		}else{
			switch (boName) {
			case PerformanceConstant.TAB_KPI_PZ_M://KPI指标库主表
				flag=!PerformanceUtil.haveData(boName, "GWBM", (String)formData.get("GWBM"), boId);
				break;
			case PerformanceConstant.TAB_JXGL_SZPZ_M://职业素质指标库主表
				flag=!PerformanceUtil.haveData(boName, "GWBM", (String)formData.get("GWBM"), boId);
				break;
			case PerformanceConstant.TAB_JXGL_JXXS_M://考核系数库主表
				flag=!PerformanceUtil.haveData(boName, "GSBM", (String)formData.get("GSBM"), boId);
				break;
			case PerformanceConstant.TAB_JXGL_GWGHR_M://考核人主表
				flag=!PerformanceUtil.haveData(boName, "GWBM", (String)formData.get("GWBM"), boId);
				break;
			case ProductPlanConstant.TAB_SCJH_WLMB_M://物料模板主表
				flag=!PerformanceUtil.haveData(boName, "GSBM", (String)formData.get("GSBM"), boId);
				break;
			default:
				flag=true;
				break;
			}
		}

		if(!flag){
			throw new BPMNError("0313","维护的信息已存在，不能进行保存操作");
		}
		return flag;
	}
	public String getDescription() {
        return " KPI、职业素质、系数、考核人：保存前校验公司、岗位是否重复配置";
    }

    public String getProvider() {
        return "nepharm";
    }

    public String getVersion() {
        return "1.0";
    }
}
