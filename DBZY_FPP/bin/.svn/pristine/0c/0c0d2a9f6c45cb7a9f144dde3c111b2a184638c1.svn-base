package com.nepharm.apps.fpp.biz.pem.event;

import java.util.HashSet;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
/**
 * 公司奖惩 表单办理前校验事件
 * @author lizhen
 *
 */

public class CompanyJCFormCompleteEvent 
		extends InterruptListener 
		implements InterruptListenerInterface {
	private String bindId="";
	private String year="";
	private String month="";
	private String lx=""; 
	@Override
	public boolean execute(ProcessExecutionContext param) throws Exception {
		bindId=param.getProcessInstance().getId();
		List<BO> list=SDK.getBOAPI().query("BO_DY_JXGL_GSJC").bindId(bindId).list();
		BO mainBo=SDK.getBOAPI().query("BO_DY_JXGL_GSJC_M").bindId(bindId).list().get(0);
		year=mainBo.getString("YEAR");
		month=mainBo.getString("MONTH");
		lx=mainBo.getString("DJLX");//冲账|正常
		
		/*
		 * TODO 
		 * 判断子表部门、个人类型，
		 * 如果是个人，需要填写人员字段，
		 * 如果是部门，自动更新一个部门经理
		*/
		if(list.size()==0){
			throw new BPMNError("0001","子表奖惩信息不能为空!");
		}
		
		/* 修改内容：增加公司级奖惩通知特殊部门判断
		 * 日期：2019-03-07
		 * 修改人：侯崴
		 */
		List<BO> tsbmList = SDK.getBOAPI().query("BO_DY_JXGL_JCTZTSBM").list();
		HashSet set = new HashSet();
		if(tsbmList != null && !tsbmList.isEmpty()) {
			for(BO bo:tsbmList) {
				String gsbm = bo.getString("GSBM");
				set.add(gsbm);
			}
		}
		
		
		for(BO bo:list){
			String id = bo.getId();
			String sjlx = bo.getString("SJLX");//数据类型 0：部门1：个人
			String gsbm = bo.getString("GSBM");//公司编码
			String bjcr =bo.getString("BJCRZH");//被奖惩人账号
			String rule="@sqlValue(select USERID from (select USERID,USERNAME,ROLEID from orguser where DEPARTMENTID in( select id from orgdepartment start with departmentno = '"+gsbm+"' connect by prior id = parentdepartmentid) )where roleid like '52d8f37e-163d-417a-b3e2-07ac8eaaf85%')";
			
			//公司级奖惩通知特殊部门判断
			if(set.contains(gsbm)) {
				rule="@sqlValue(select t1.USERID, t1.USERNAME from ORGUSER t1 join ORGDEPARTMENT t2 on t1.DEPARTMENTID = t2.ID where t2.DEPARTMENTNO = '"+gsbm+"' and t1.ISMANAGER = 1)";
			}
			
			String zjl=SDK.getRuleAPI().executeAtScript(rule);
			
			StringBuffer sql = new StringBuffer();
			sql.append("update BO_DY_JXGL_GSJC set DJLX='"+lx+"',");
			sql.append(" YEAR='"+year+"',MONTH='"+month+"', ");
			
			if("0".equals(sjlx)){
				//TODO 部门
				sql.append(" BJCRZH='',BJCR='', ");
			}else if("1".equals(sjlx)){
				//TODO 个人
				if("".equals(bjcr)||null==bjcr){
					throw new BPMNError("0313","子表数据类型为“个人”的数据“被奖惩人”不能为空!");
				}
			}
			sql.append(" ZJL='"+zjl+"' ");
			sql.append(" where id='"+id+"' ");
			
			DBSql.update(sql.toString());
		}
		String uid=param.getUserContext().getUID();
		String sqzjl=mainBo.getString("SQZJL");
		if(uid.equals(sqzjl)){
			String ztSql="update BO_DY_JXGL_GSJC set zt='1' where bindid='"+bindId+"'";
			DBSql.update(ztSql);
		}
		//@gridMax(BO_DY_JXGL_GSJC,JCJE,,bindId)
		String je=SDK.getRuleAPI().executeAtScript("@gridMax(BO_DY_JXGL_GSJC,JCJE,,"+bindId+")");
		String sql = "update BO_DY_JXGL_GSJC_M set JCJE='"+je+"' where bindid='"+bindId+"'";
		DBSql.update(sql);
		return true;
	}
	//throw new BPMNError("0312","订单尚未审核，不能进行支付操作");
	
	
	
	
	
	public String getDescription() {
        return "表单校验事件，校验子表的类型-以及类型是或否有值";
    }

    public String getProvider() {
        return "nepharm";
    }

    public String getVersion() {
        return "1.0";
    }
}
