package com.nepharm.apps.fpp.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 获取人员信息（公司、岗位、是否为操作岗、工序（操作岗））
 * @author lizhen
 */
public class UserBean {
	private String uid;
	private String userName;
	private String gwbm;
	private String gwmc;
	private String gsbm;
	private String gsmc;
	private boolean isOper=false;//是否为量化岗，默认不是
	
	private Map<String,String> gxxx=new HashMap<String,String>();//工序信息
	
	public UserBean(String uid){
		this.uid=uid;
		this.findInfo();
		
	}
	private void findInfo(){
		this.gsbm=SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSBM)");
		this.gsmc=SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSMC)");
		this.gwbm=SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GWBM)");
		this.gwmc=SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GWMC)");
		this.findGS();
		try {
			
			if(this.gxxx.size()==0){
				this.isOper=false;
			}else{
				this.isOper=true;
			}
		} catch (Exception e) {
			this.isOper=false;
		}
	}
	private void findGS(){
		
		String sql="select DISTINCT(GXBM) GXBM,GXMC from "+ConfigConstant.VIEW_RYXX+" where GSBM='"+gsbm+"' and GWBM='"+gwbm+"'";
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				
				try {
					String bm=rs.getString("GXBM");
					String mc=rs.getString("GXMC");
					if(bm!=null){
						this.gxxx.put(bm, mc);
					}
					
				} catch (Exception e) {
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBSql.close(conn, pstat, rs);
		}
		
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGwbm() {
		return gwbm;
	}
	public void setGwbm(String gwbm) {
		this.gwbm = gwbm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGsbm() {
		return gsbm;
	}
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}
	public String getGsmc() {
		return gsmc;
	}
	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}
	public boolean isOper() {
		return isOper;
	}
	public void setOper(boolean isOper) {
		this.isOper = isOper;
	}
	public Map<String, String> getGxxx() {
		return gxxx;
	}
	public void setGxxx(Map<String, String> gxxx) {
		this.gxxx = gxxx;
	}
	
}
