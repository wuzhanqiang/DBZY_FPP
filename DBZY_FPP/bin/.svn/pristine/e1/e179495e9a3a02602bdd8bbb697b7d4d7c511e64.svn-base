package com.nepharm.apps.fpp.biz.pem.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;

/**
 * 能力考核 -计算方式执行类
 * @author lizhen
 *
 */
public class AbilityEvent {

	private List<BO> levelList;
	private String bindId;
	private double fs;//总分数：计算前
	private int num;//总条数
	private double dfl;//得分率
	private double qz;//权重
	
	private double kpifs;//KPI得分（已乘以权重）
	
	private double zfs;//总得分
	private double zxs;//总系数
	private String zdj;//总等级
	
	private double tzfs;//调整得分
	private double tzxs;//调整系数
	private String tzdj;//调整等级
	
	private double jxfs;//最终有效得分
	private double jxxs;//最终有效系数
	private String jxdj;//最终有效等级
	
	
	private String gsbm;
	private String gsmc;
	
	/**
	 * 初始化 能力考核类
	 * @param bindId 计算的bindID
	 */
	public AbilityEvent(String bindId){
		this.bindId=bindId;
		//this.initData();
	}
	public void run(){
		initData();
	}
	//返回能力考核得分值
	private double getAbilityScore(){
		if(num==0||fs==0){
			return 0;
		}else{
			return fs/num/5*dfl*qz;
		}
	}
	/**
	 * 初始化数据
	 */
	private void initData(){
		
		//绩效考核-能力考核子表数据
		List<BO> list=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_JXKH_SZ).bindId(this.bindId).list();
		//绩效考核-主表
		BO main=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_JXKH_M).bindId(this.bindId).list().get(0);
		if(list==null||list.size()==0){
			num=0;
			fs=0;
			return ;
		}
		num=list.size();
		for(BO bo:list){
			String ldfs=(String)bo.get("LPFS");
			try {
				fs=fs+Double.parseDouble(ldfs);
			} catch (NumberFormatException e) {
			}
		}
		String szqz=(String)main.get("SZQZ");
		String szdfl=(String)main.get("SZDFL");
		
		
		try {
			qz =Double.parseDouble(szqz);
		} catch (NumberFormatException e) {
			qz=0;
		}
		try {
			dfl=Double.parseDouble(szdfl);
		} catch (NumberFormatException e) {
			dfl=0;
		}
		gsbm=(String)main.get("GSBM");
		gsmc=(String)main.get("GSMC");
		initLeveData();//等级对照表
		try {
			kpifs=Double.parseDouble((String)main.get("KPIFS"));
		} catch (NumberFormatException e) {
			kpifs=0;
		}
		
		try {
			tzfs=Double.parseDouble((String)main.get("TZFS"));
		} catch (NumberFormatException e) {
			tzfs=0;
		}
		
		zfs=getAbilityScore()+kpifs;//总分数
		
		setZFS();
		setTZFS();
		setJXFS();
		
		
		updateMainData();
	}
	
	/**
	 * 初始化等级数据列表
	 */
	private void initLeveData(){
		
		PerformanceDao dao = new PerformanceDao();
		List<BO> list =dao.getAbilityLeveLData(this.gsbm);
		levelList=list;
		
		levelList = new ArrayList<>(list);		
		
		for(int i=0;i<list.size();i++){
			if(i==0){
				BO max=new BO();
				max=list.get(0);
				max.set("MAX", max.get("ZXFS"));
				levelList.add(max);
			}
			if(i==list.size()-1){
				BO min=new BO();
				min=list.get(list.size()-1);
				min.set("MIN", min.get("ZDFS"));
				levelList.add(min);
			}
		}
		
	}
	
	/**
	 * 计算总分（流程数据）
	 */
	private void setZFS(){
		boolean isHave =false;
		for(int i=0;i<levelList.size()-2;i++){
			BO level=levelList.get(i);
			double max=0,min=0;
			try {
				max=Double.parseDouble((String)level.get("ZDFS"));
			} catch (NumberFormatException e) {
			}
			try {
				min=Double.parseDouble((String)level.get("ZXFS"));
			} catch (NumberFormatException e) {
			}
			if(max>=zfs&&min<=zfs){
				isHave=true;
				try {
					zxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				zdj=(String)level.get("DJ");
				break;
			}
		}
		
		if(!isHave){
			BO level=levelList.get(levelList.size()-2);
			double max=0;
			try {
				max=Double.parseDouble((String)level.get("MAX"));
			} catch (NumberFormatException e) {
			}
			if(max<=zfs){
				isHave=true;
				try {
					zxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				zdj=(String)level.get("DJ");
			}
		}
		
		if(!isHave){
			BO level=levelList.get(levelList.size()-1);
			double min=0;
			try {
				min=Double.parseDouble((String)level.get("MIN"));
			} catch (NumberFormatException e) {
			}
			if(min>=zfs){
				isHave=true;
				try {
					zxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				zdj=(String)level.get("DJ");
			}
		}
		
	}
	
	
	/**
	 * 计算总分（调整数据）
	 */
	private void setTZFS(){
		if(tzfs==0){
			tzxs=0;
			tzdj="";
			return ;
		}
		boolean isHave =false;
		for(int i=0;i<levelList.size()-2;i++){
			BO level=levelList.get(i);
			double max=0,min=0;
			try {
				max=Double.parseDouble((String)level.get("ZDFS"));
			} catch (NumberFormatException e) {
			}
			try {
				min=Double.parseDouble((String)level.get("ZXFS"));
			} catch (NumberFormatException e) {
			}
			if(max>=tzfs&&min<=tzfs){
				isHave=true;
				try {
					tzxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				tzdj=(String)level.get("DJ");
				break;
			}
		}
		
		if(!isHave){
			BO level=levelList.get(levelList.size()-2);
			double max=0;
			try {
				max=Double.parseDouble((String)level.get("MAX"));
			} catch (NumberFormatException e) {
			}
			if(max<=tzfs){
				isHave=true;
				try {
					tzxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				tzdj=(String)level.get("DJ");
			}
		}
		
		if(!isHave){
			BO level=levelList.get(levelList.size()-1);
			double min=0;
			try {
				min=Double.parseDouble((String)level.get("MIN"));
			} catch (NumberFormatException e) {
			}
			if(min>=tzfs){
				isHave=true;
				try {
					tzxs=Double.parseDouble((String)level.get("XS"));
				} catch (NumberFormatException e) {
				}
				tzdj=(String)level.get("DJ");
			}
		}
		
	}
	
	private void setJXFS(){
		if(tzfs==0){
			jxfs=zfs;
			jxdj=zdj;
			jxxs=zxs;
		}else{
			jxfs=tzfs;
			jxdj=tzdj;
			jxxs=tzxs;
		}
	}
	
	private void updateMainData(){
		PerformanceDao dao = new PerformanceDao();
		
		dao.updateMainData(bindId, getAbilityScore(), zfs, zdj, zxs, tzfs, tzdj, tzxs, jxfs, jxdj, jxxs);
	}
	
	
}
