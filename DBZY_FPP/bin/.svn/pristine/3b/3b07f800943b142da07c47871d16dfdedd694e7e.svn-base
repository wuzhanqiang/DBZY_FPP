package com.nepharm.apps.fpp.is.ehr.constant;

import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.nepharm.apps.fpp.is.ehr.util.HRUtil;

public class ManualSynchronousSalary {
	public ResponseObject manualSynchronousSalary(){
		HRUtil hrutil = new HRUtil();
		StringBuffer msg = new StringBuffer();
		try {
			hrutil.synchronousTheThirdPartyData(SynchronousHRConstant.TAB_JCXX_HRYFRYXCTB,msg);
			System.out.println(msg.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!msg.toString().equals("")){
			return ResponseObject.newErrResponse(msg.toString());
		}
		return ResponseObject.newOkResponse("同步成功！");
	}
}
