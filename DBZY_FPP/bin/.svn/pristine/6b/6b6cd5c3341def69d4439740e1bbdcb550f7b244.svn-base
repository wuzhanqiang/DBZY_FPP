package com.nepharm.apps.fpp.is.k3.controller;

import kingdee.bos.webapi.client.K3CloudApiClient;

import com.nepharm.apps.fpp.is.k3.dao.EncapsulationProductionOrderJson;
import com.nepharm.apps.fpp.is.k3.util.K3Util;
import com.alibaba.fastjson.JSONObject;







public class CallInterfaceCreateProductionOrder {
	
	public String createProductionOrder(String bindid,String userid) throws Exception{
		K3Util k3util = new K3Util();
		StringBuffer msgnb = new StringBuffer();
		String sContent = null;
		EncapsulationProductionOrderJson epo = null;
		String rmsg = null;
		
		JSONObject obj = null;
		K3CloudApiClient client = k3util.login(msgnb,userid);
		if(client!=null){//登陆成功
			//创建封装数据-此处为创建生产订单json封装数据
			epo= new EncapsulationProductionOrderJson();
			sContent = epo.formatContent(bindid);
			sContent = k3util.save(client, "PRD_MO",sContent);
			obj = JSONObject.parseObject(sContent);
			boolean IsSuccess = obj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess");
			System.out.println(obj.toString());
			if(IsSuccess){
				rmsg = "";
				
			}else{
				obj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors");
				System.out.println(obj.toString());
				rmsg = "创建生产订单失败!请联系管理员查找问题";
				
			}
		}else{
			rmsg = msgnb.toString();
			
		}
		return rmsg;
	}
}
