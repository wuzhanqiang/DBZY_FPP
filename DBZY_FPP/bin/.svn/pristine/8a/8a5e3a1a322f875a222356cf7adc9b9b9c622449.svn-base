package com.nepharm.apps.fpp.biz.pem.bean;

import com.nepharm.apps.fpp.util.NumberUtil;

public class JHWCBean {
	String BM;
	String MC;
	String DDL;
	String WCL;
	String GG;
	String DW;
	String WCLV;//完成率
	
	public JHWCBean(String BM,String MC,String DDL,String WCL,String GG,String DW){
		this.BM=BM;
		this.MC=MC;
		this.DDL=DDL;
		this.WCL=WCL;
		this.GG=GG;
		this.DW=DW;
		//数值型的简单处理
		this.initNumber();
	}
	private void initNumber(){
		double ddl=0;
		try {
			ddl=Double.parseDouble(DDL);
		} catch (NumberFormatException e) {
			ddl=0;
			DDL="0";
		}
		double wcl=0;
		try {
			wcl=Double.parseDouble(WCL);
		} catch (NumberFormatException e) {
			wcl=0;
			WCL="0";
		}
		double wclv=0;
		if(ddl==0||wcl==0){
			wclv=0;
		}else{
			wclv= wcl/ddl*100;
		}
		WCLV=NumberUtil.doubleFormat(wclv, 2)+"%";
	}

	public String getBM() {
		return BM;
	}

	public void setBM(String bM) {
		BM = bM;
	}

	public String getMC() {
		return MC;
	}

	public void setMC(String mC) {
		MC = mC;
	}

	public String getDDL() {
		return DDL;
	}

	public void setDDL(String dDL) {
		DDL = dDL;
	}

	public String getWCL() {
		return WCL;
	}

	public void setWCL(String wCL) {
		WCL = wCL;
	}

	public String getGG() {
		return GG;
	}

	public void setGG(String gG) {
		GG = gG;
	}

	public String getDW() {
		return DW;
	}

	public void setDW(String dW) {
		DW = dW;
	}

	public String getWCLV() {
		return WCLV;
	}

	public void setWCLV(String wCLV) {
		WCLV = wCLV;
	}
	
}	
