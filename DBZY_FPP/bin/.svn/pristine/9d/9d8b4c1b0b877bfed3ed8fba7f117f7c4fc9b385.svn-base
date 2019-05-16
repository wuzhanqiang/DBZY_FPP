package com.nepharm.apps.fpp2.biz.yyb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class YYBWeb extends ActionWeb {

	public YYBWeb() {
	}

	public YYBWeb(UserContext userContext) {
		super(userContext);
	}
	/**
	 * 原料药生产计划变更-子表数据初始化
	 * @return
	 */
	public String Ylyscjhbg_zbcsh(String sid, String bindid, String ybindid, String gsbm) {
		SDK.getBOAPI().removeByBindId("BO_DY_SCJH_YLYSCJHBG_Z", bindid);
		List<BO> list = SDK.getBOAPI().query("BO_DY_SCJH_YLYSCJH_Z").bindId(ybindid).addQuery("GSBM = ", gsbm).list();
		for(BO bo:list){
			
			BO bgBo = new BO();
			bgBo.set("GSMC", bo.getString("GSMC"));
			bgBo.set("GSBM", bo.getString("GSBM"));
			bgBo.set("CPBM", bo.getString("CPBM"));
			bgBo.set("CPMC", bo.getString("CPMC"));
			bgBo.set("CPGG", bo.getString("CPGG"));
			bgBo.set("CPDW", bo.getString("CPDW"));
			bgBo.set("JHCL", bo.getString("JHCL"));
			bgBo.set("QRCL", bo.getString("JHCL"));
			bgBo.set("JHKGSJ", bo.getString("JHKGSJ"));
			bgBo.set("JHWGSJ", bo.getString("JHWGSJ"));
			bgBo.set("JHNF", bo.getString("JHNF"));
			bgBo.set("JHYF", bo.getString("JHYF"));
			bgBo.set("BZ", bo.getString("BZ"));
			bgBo.set("ZT", "1");
			bgBo.set("K3GSBM", bo.getString("K3GSBM"));
			bgBo.set("K3GSMC", bo.getString("K3GSMC"));
			bgBo.set("YID", bo.getString("ID"));
			
			SDK.getBOAPI().create("BO_DY_SCJH_YLYSCJHBG_Z", bgBo, bindid, null);
		}
		
		return "";
	}
}
