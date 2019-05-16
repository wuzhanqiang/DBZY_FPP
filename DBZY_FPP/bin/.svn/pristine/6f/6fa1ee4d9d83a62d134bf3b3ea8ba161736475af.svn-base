package com.nepharm.apps.fpp.biz.pem.bean;

/**
 * 奖惩信息对象
 * @author lizhen
 *
 */
public class JCXXBean {
	public String LB;
	public String LX;
	public String SM;//说明
	public String MX;//明细
	public String JE;
	public String RQ;
	public String BINDID;
	public String URL;
	
	public JCXXBean(String LB,String LX,String MX,String SM,String JE,String RQ,String BINDID,String URL){
		this.LB=LB;
		if(LX.length()==4){
			this.LX=LX.substring(2,4);
		}else{
			this.LX=LX;
		}
		this.URL=URL;
		this.MX=MX;
		this.SM=SM;
		this.JE=JE;
		this.BINDID=BINDID;
		this.RQ=RQ.substring(0,10);
	}

	public String getLB() {
		return LB;
	}

	public void setLB(String lB) {
		LB = lB;
	}

	public String getLX() {
		return LX;
	}

	public void setLX(String lX) {
		LX = lX;
	}

	public String getSM() {
		return SM;
	}

	public void setSM(String sM) {
		SM = sM;
	}

	public String getMX() {
		return MX;
	}

	public void setMX(String mX) {
		MX = mX;
	}

	public String getJE() {
		return JE;
	}

	public void setJE(String jE) {
		JE = jE;
	}

	public String getRQ() {
		return RQ;
	}

	public void setRQ(String rQ) {
		RQ = rQ;
	}

	public String getBINDID() {
		return BINDID;
	}

	public void setBINDID(String bINDID) {
		BINDID = bINDID;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
