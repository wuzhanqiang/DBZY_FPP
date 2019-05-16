package com.nepharm.apps.fpp.biz.zbgl.util;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.commons.formfile.model.delegate.FormFile;
import com.actionsoft.bpms.server.fs.DCContext;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOAPI;
import com.nepharm.apps.fpp.biz.zbgl.bean.FjBean;

public class ZBUtil {
	public static List<FjBean> getDownLoadURL(String boId, String fieldName, String sid, String WJMC) {
		String url = "";
		BOAPI boapi = SDK.getBOAPI();
		List<FormFile> list = boapi.getFiles(boId, fieldName);
		List<FjBean> fbList = new ArrayList<FjBean>();
		if(list != null && !list.isEmpty()) {
			for(FormFile formFile : list) {
				FjBean fb = new FjBean();
				DCContext context = boapi.getFileDCContext(formFile);
				context.setFileNameShow(WJMC);
				context.setSecurityFileName(WJMC);
				url = context.getStremURL();
				url = url.replace("sid=null", "sid="+sid);
				fb.setUrl(url);
				fb.setFileName(context.getFileName());
				fbList.add(fb);
			}
		}
		return fbList;
	}
}
