package com.nepharm.apps.fpp.is.k3.controller;

import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.is.k3.constant.K3Constant;
import com.nepharm.apps.fpp.is.k3.util.K3Util;
import com.nepharm.apps.fpp.is.k3.web.K3Web;

@Controller
public class K3Controller {
	@Mapping("com.nepharm.apps.fpp.k3_sso")
	public String jumpToK3(){
		K3Web web = new K3Web();
		return web.getIndexPage();
	}
	
	@Mapping("com.nepharm.apps.fpp.k3.update_k3info")
	public String saveK3Info(String k3account, String k3password) throws NumberFormatException, Exception {
		ResponseObject ro = ResponseObject.newOkResponse();
		boolean tf = K3Util.checkK3Account(k3account, k3password);
		if(tf) {
			K3Util.updateK3Account(k3account, k3password);
			return ro.toString();
		}else {
			ro.err();
			ro.err("K3密码错误！请修改后重试！");
			ro.msg("K3密码错误！请修改后重试！");
			return ro.toString();
		}
	}
	
	@Mapping("com.nepharm.apps.fpp.k3.load_k3info")
	public String loadK3Info() {
		ResponseObject ro = ResponseObject.newOkResponse();
		BO data = K3Util.loadK3Account();
		ro.put("k3account", data.get("K3ACCOUNT"));
		ro.put("k3password", data.get("K3PASSWORD"));
		return ro.toString();
	}
}
