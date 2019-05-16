package com.nepharm.apps.fpp.biz.pem.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.kpi.KPIStartUp;

/**
 * 执行KPI运算(也是保存后事件)
 * @author lizhen
 *
 */
public class KPIRunEvent extends ExecuteListener   {

	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		
		UserContext uc= context.getUserContext();//当前操作者usercontext
		
		String bindId=context.getProcessInstance().getId();
		String uid =DBSql.getString("select BKHRZH from "+PerformanceConstant.TAB_JXGL_JXKH_M+" where bindid='"+bindId+"'","BKHRZH");
		KPIStartUp kpi = new KPIStartUp(uid, bindId);
		kpi.execute();
		//kpi.run();
		
		//select sum(FS) as ZF from BO_DY_JXGL_JXKH_KPI where bindid='cc4c6a3f-8986-4006-9f63-f87bee181761'
		String sumKpiSql="select sum(FS) as ZF from "+PerformanceConstant.TAB_JXGL_JXKH_KPI+" where bindid='"+bindId+"'";
		String zf=DBSql.getString(sumKpiSql,"ZF");
		double fs=0;
		if(zf!=null&&!"".equals(zf)){
			fs=Double.parseDouble(zf);
		}
		String kpiQZSql="select KPIQZ   from "+PerformanceConstant.TAB_JXGL_JXKH_M+" where bindid='"+bindId+"'";
		String qzStr=DBSql.getString(kpiQZSql,"KPIQZ");
		double qz=0;
		if(qzStr!=null&&!"".equals(qzStr)){
			qz=Double.parseDouble(qzStr);
		}
		
		//更新绩效考核主表的状态0->1
		String sql = "update "+PerformanceConstant.TAB_JXGL_JXKH_M+" set zt='1',KPIFS="+(qz*fs)+" where bindid='"+bindId+"' ";
		DBSql.update(sql);
	}
	
	@Override
	public String getDescription() {
		return "KPI系统抓数计算功能";
	}

	@Override
	public String getProvider() {
		return "nepharm";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

}
