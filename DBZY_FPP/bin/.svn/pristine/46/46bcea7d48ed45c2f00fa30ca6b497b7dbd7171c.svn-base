package com.nepharm.apps.fpp.is.bpm.controller;

import com.actionsoft.bpms.server.bind.annotation.Controller;
import com.actionsoft.bpms.server.bind.annotation.Mapping;
import com.nepharm.apps.fpp.is.bpm.web.BPMWeb;
/**
 * BPMController
 * @author sydq
 *
 */
@Controller
public class BPMController {

	@Mapping("com.nepharm.apps.fpp.bpm_sso")
	public String BPMSSO() {
		BPMWeb web = new BPMWeb();
		return web.getIndexPage();
	}
}
