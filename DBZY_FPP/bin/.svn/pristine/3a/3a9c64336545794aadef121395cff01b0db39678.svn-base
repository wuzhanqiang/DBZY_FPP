package com.nepharm.apps.fpp.biz.pem.bean;

/**
 * 我的绩效-收藏对象
 * @author lizhen
 *
 */
public class SCBean {
	private String ZH;//账号
	private String MC;//名称
	private String CMD;//cmd
	private String BDPK;//表单id
	
	public SCBean(String ZH,String MC,String CMD,String BDPK){
		this.ZH=ZH;
		this.MC=MC;
		this.CMD=CMD;
		this.BDPK=BDPK;
	}
	
	
	public String getTabString(){
		String str="<li><a href='#"+this.BDPK+"' tabindex='-1' data-toggle='tab'>"+this.MC+"</a></li>";
		return str;
	}
	
	public String getDivString(String sid){
		StringBuffer str=new StringBuffer();
		str.append("<div class='tab-pane fade' id='"+this.BDPK+"'>");
		//frameborder='no'
		str.append("<iframe  src='./w?sid="+sid+"&cmd="+this.CMD+"' width='100%' height='300px'  frameborder='no' scrolling='no' ></iframe>");
		str.append("</div>");
		return str.toString();
	}
	public String getZH() {
		return ZH;
	}
	public void setZH(String zH) {
		ZH = zH;
	}
	public String getMC() {
		return MC;
	}
	public void setMC(String mC) {
		MC = mC;
	}
	public String getCMD() {
		return CMD;
	}
	public void setCMD(String cMD) {
		CMD = cMD;
	}
	public String getBDPK() {
		return BDPK;
	}
	public void setBDPK(String bDPK) {
		BDPK = bDPK;
	}
	
}
