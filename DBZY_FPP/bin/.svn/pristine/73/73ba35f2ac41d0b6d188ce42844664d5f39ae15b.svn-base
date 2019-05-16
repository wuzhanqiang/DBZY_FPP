package com.nepharm.apps.fpp.biz.mr.util;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.mr.constant.MrConstant;

public class MRUtil {
	
	//读取我的资源授权
	public static boolean getSQ(String userId, String type) {
		String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		List<BO> list = SDK.getBOAPI().query(MrConstant.TAB_MR_SQ_S).addQuery("GWBM = ", GWBM).list();
		if(list != null && !list.isEmpty()) {
			BO bo = list.get(0);
			if("1".equals(bo.getString(type))) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
