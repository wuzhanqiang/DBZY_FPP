package com.nepharm.apps.fpp.biz.pem.event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSAPIException;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.PerformanceDao;
import com.nepharm.apps.fpp.constant.ConfigConstant;
/**
 * 定时绩效考核流程-通用相关业务
 * @author lizhen
 */
public class PerformanceEvent {
	
	/**
	 * 定时器关闭入口
	 * @param param 标题标识（系统、被考核人、领导）
	 */
	public static void closeStep(String param){
//		
		PerformanceDao dao = new PerformanceDao();
		//查找isend=0，zt=0的主表数据（返回bindidlist即可）
		Map<String, String> list=dao.getPerformanceTasks(param);
		for (Iterator i = list.keySet().iterator(); i.hasNext();) {  
			String taskId =(String) i.next();
			String bindId=list.get(taskId);
			if("(领导".equals(param)){
				String sql = "update "+PerformanceConstant.TAB_JXGL_JXKH_SZ+" set LPFS='5' where BINDID='"+bindId+"'";
				DBSql.update(sql);
			}
			
			try {
				String uid=DBSql.getString("select TARGET from WFC_TASK where id='"+taskId+"'","TARGET");
				if(uid==null||"".equals(uid)){
					uid="admin";
				}
				//插入一条审核留言
				SDK.getTaskAPI().commitComment(taskId, uid, "<span style='color:red'>定时器操作</span>", "<span style='color:red'>未按时办结任务或定时触发的任务，系统自动执行关闭！</span>", true);
				//办结任务
				SDK.getTaskAPI().completeTask(taskId,  uid);
			} catch (AWSDataAccessException e) {
				e.printStackTrace();
			} catch (BPMNError e) {
				e.printStackTrace();
			} catch (AWSAPIException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
