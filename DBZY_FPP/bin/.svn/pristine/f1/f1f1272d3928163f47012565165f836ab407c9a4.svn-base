package com.nepharm.apps.fpp.biz.ppm.event;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOCopyAPI;
import com.actionsoft.sdk.local.api.BOQueryAPI;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;

/**
 * 确定生产计划 办理结束后触发事件
 * @author lizhen
 * @date 2018-04-26
 */
public class ProductTaskAfterComplete implements ExecuteListenerInterface {
	String bindId="";
	@Override
	public void execute(ProcessExecutionContext context) throws Exception {
		//UserContext uc= context.getUserContext();//当前操作者usercontext
		bindId=context.getProcessInstance().getId();
		
		String sql = "SELECT DJBH,YDJBH FROM "+ProductPlanConstant.TAB_SCJH_QDSCJH_M+" WHERE BINDID='"+bindId+"'";
		String sql2="DELETE FROM "+ProductPlanConstant.TAB_SCJH_QDSCJH_S +" WHERE BINDID='"+bindId+"'";
		
		String newBh= DBSql.getString(sql,"DJBH");//计划编号
		String oldBh = DBSql.getString(sql,"YDJBH");//原计划编号
		
		String sql3="UPDATE  "+ProductPlanConstant.TAB_SCJH_QDSCJH_M +" set YDJBH='"+newBh+"' "+" WHERE BINDID='"+bindId+"'";
		
		if(oldBh==null||"".equals(oldBh)){
			//直接初始化
			copy2ProductPlan(newBh);
		}else if(!oldBh.equals(newBh)){
			//先清空，在重新直接初始化
			DBSql.update(sql2);
			copy2ProductPlan(newBh);
			
		}
		//更新oldBh
		DBSql.update(sql3);
	}

	/**
	 * 将拟数据copy到确定数据
	 * @param newBh
	 */
	private void copy2ProductPlan(String newBh){
		String sql = "SELECT BINDID FROM "+ProductPlanConstant.TAB_SCJH_NSCJH_M+" WHERE DJBH='"+newBh+"'";
		String oldBindId= DBSql.getString(sql,"BINDID");//拟生产计划bindid
		
		BOQueryAPI query = SDK.getBOAPI().query(ProductPlanConstant.TAB_SCJH_NSCJH_S, false).addQuery("BINDID =", oldBindId);
		BOCopyAPI copyAPI = query.copyTo(ProductPlanConstant.TAB_SCJH_QDSCJH_S, bindId);
//		copyAPI.addMapping("JHCL", "QRCL");
//		copyAPI.addMapping("ZT", "1");
		copyAPI.exec(); 
		String date = SDK.getRuleAPI().executeAtScript("@date");
		//TODO 更新确认产量
		String update =" UPDATE  "+ProductPlanConstant.TAB_SCJH_QDSCJH_S+" SET   QRCL=JHCL,ZT='1',JHKGSJ= to_date('"+date+"','yyyy-mm-dd'),JHWGSJ=to_date('"+date+"','yyyy-mm-dd')  WHERE BINDID='"+bindId+"'" ;
		
		//JHKGSJ= to_date('"+date+"','yyyy-mm-dd')
		//JHWGSJ=to_date('"+date+"','yyyy-mm-dd')   
		
		DBSql.update(update);
	
	}
	
	
	@Override
	public String getDescription() {
		return "确定生产计划-公司运营部-保存后初始化子表数据（仅限编号修改时）";
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
