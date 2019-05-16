package com.nepharm.apps.fpp.is.ehr.controller;

import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp.is.ehr.web.HRWeb;

/**
 * 
 * @author sydq
 *
 */
@Controller
public class HRController {
	
	@Mapping("com.nepharm.apps.fpp.ehr.sso")
	public String EHRSSO() {
		HRWeb web = new HRWeb();
		return web.getEHRSSOPage();
	}
	
}
