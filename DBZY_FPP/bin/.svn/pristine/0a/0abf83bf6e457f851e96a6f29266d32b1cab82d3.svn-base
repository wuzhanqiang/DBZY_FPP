package com.nepharm.apps.fpp.biz.tam.event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.TaskInstance;
import com.actionsoft.sdk.local.SDK;


public class RwgzfpTaskBeforeCompleteStep3 extends InterruptListener implements
		InterruptListenerInterface {

	
	public RwgzfpTaskBeforeCompleteStep3() {
		setDescription("开启工作评价流程");
	}

	@Override
	public boolean execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		
		//获取任务数据
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();
		String uid = ctx.getUserContext().getUID();
		String userName = ctx.getUserContext().getUserName();
		String bm = ctx.getUserContext().getDepartmentModel().getName();
		String bmid = ctx.getUserContext().getDepartmentModel().getId();
		List<BO> list = SDK.getBOAPI().query("BO_DY_RWGL_RWGZFP_RWXX")
				.bindId(bindid).list();
		BO MainData = SDK.getBOAPI().getByProcess("BO_DY_RWGL_RWGZFP_MAIN", bindid);
		String title = "";
		String createUid = MainData.get("CREATEUSER").toString();
		String createUname = SDK.getRuleAPI().executeAtScript("@userName("+createUid+",delimiter)");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		
		//判断审核动作
		//boolean f1 = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "接受");
		//boolean f2 = SDK.getTaskAPI().isChoiceActionMenu(ctx.getTaskInstance().getId(), "接受并继续分配任务");
		//开启评分子流程
		String SFPJ = "";//是否评价
		String JSRZH = "";//账号
		String NDXS = "";//难度系数
		String RWMC = "";//任务名称
		String WCQK = "";//完成描述
		for(int i=0;i<list.size();i++){
			
			SFPJ = list.get(i).getString("SFPJ");
			JSRZH = list.get(i).getString("JSRZH");
			NDXS = list.get(i).getString("NDXS");
			RWMC = list.get(i).getString("RWMC");
			WCQK = list.get(i).getString("WCQK");
			
			if("1".equals(SFPJ) && uid.equals(JSRZH)){
//					//创建子流程流程实例
//					ProcessInstance subProcessInst = SDK.getProcessAPI().createSubProcessInstance(uid, title, "obj_560c912fd4e442ec80233c54133efd5f", null);
//					SDK.getProcessAPI().start(subProcessInst);
//					//创建子流程任务实例
//					SDK.getTaskAPI().createCallActivityTaskInstance(subProcessInst.getId(), taskid, "obj_c7fd9d403d200001d7ed2000142f8dc0", title);
				title = date + "-任务：" + list.get(i).getString("RWMC");
				//创建流程实例
				ProcessInstance subProcessInst = SDK.getProcessAPI().createProcessInstance("obj_560c912fd4e442ec80233c54133efd5f", uid, title);
				SDK.getProcessAPI().start(subProcessInst);
				String SubBindid = subProcessInst.getId();
				//创建任务实例
				//List<TaskInstance> taskList = SDK.getTaskAPI().createUserTaskInstance(SubBindid, bindid, uid, "obj_c7fc5d2c740000011a3b17ecce2f2a60", uid, title);
				//创建表数据
				BO boData = new BO();
				SDK.getBOAPI().create("BO_DY_RWGL_GZPJB", boData, SubBindid, uid);
				//初始化表数据
				String DJBH = SDK.getRuleAPI().executeAtScript("@sequenceMonth(DY_GZPJ,6,0)");
				String GSMC = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSMC)");
				String GSBH = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSBM)");
				boData.set("DJBH", "GZPJ"+DJBH);
				boData.set("GSMC", GSMC);
				boData.set("GSBH", GSBH);
				boData.set("BS", "2");	//标识：1:主流程|2:子流程
				boData.set("BDLX", "1");//表单类型：1:有反馈|2:无反馈
				boData.set("SQR", userName);
				boData.set("SQRZH", uid);
				boData.set("BM", bm);
				boData.set("BMID", bmid);
				boData.set("SQSJ", date);
				boData.set("KHR", createUname);
				boData.set("KHRZH", createUid);
				boData.set("JSX", "0");
				boData.set("YXX", "0");
				boData.set("GZTD", "0");
				boData.set("PROCESSPARENTID", bindid);
				boData.set("FORMDEFID", "dd3eb2bd-8cd6-429b-8cfd-54bdbaa7a5b5");
				boData.set("PJF", "0");
				boData.set("RWNDXS", NDXS);
				boData.set("WCGZJY", RWMC);
				boData.set("WCGZNRMS", WCQK);
				
				
				SDK.getBOAPI().update("BO_DY_RWGL_GZPJB", boData);
				
			}
			
		}
		/*if(f1 || f2){
		}*/
		//开启任务分配流程
		/*if(f2){
			String JXFP = "";//继续分配
			String JSRZH = "";//接收人账号
			String JSR = "";//接收人
			String RWBH = "";//任务编号
			String RWMC = "";//任务名称
			String STEP = "";//等级
			for(int i=0;i<list.size();i++){
				
				JXFP = list.get(i).getString("JXFP");
				JSRZH = list.get(i).getString("JSRZH");
				JSR = list.get(i).getString("JSR");
				RWBH = list.get(i).getString("RWBH"); 
				RWMC = list.get(i).getString("RWMC");
				STEP = list.get(i).getString("STEP");
				
				if("1".equals(JXFP) && uid.equals(JSRZH)){
					
					title = date + "-任务分配-" + JSR;
					//创建流程实例
					ProcessInstance subProcessInst = SDK.getProcessAPI().createProcessInstance("obj_03a4459c388b4e58ad2e4d93ee916dc3", uid, title);
					SDK.getProcessAPI().start(subProcessInst);
					String SubBindid = subProcessInst.getId();
					//创建任务实例
					//List<TaskInstance> taskList = SDK.getTaskAPI().createUserTaskInstance(SubBindid, bindid, uid, "obj_c7fc5d2c740000011a3b17ecce2f2a60", uid, title);
					//创建表数据
			        BO boData = new BO();
			        SDK.getBOAPI().create("BO_DY_RWGL_RWGZFP_MAIN", boData, SubBindid, uid);
			        //初始化表数据
			        String DJBH = SDK.getRuleAPI().executeAtScript("@sequenceMonth(DY_RWGZFP,6,0)");
			        String GSMC = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSMC)");
			        String GSBH = SDK.getRuleAPI().executeAtScript("@getUserInfo("+uid+",GSBM)");
			        boData.set("DJBH", "RWGZFP"+DJBH);
			        boData.set("GSMC", GSMC);
			        boData.set("GSBH", GSBH);
			        boData.set("BS", "2");	//标识：1:主流程|2:子流程
			        boData.set("SQR", userName);
			        boData.set("SQRZH", uid);
			        boData.set("BM", bm);
			        boData.set("BMID", bmid);
			        boData.set("SQSJ", date);
			        boData.set("RWMC", RWMC);
			        boData.set("RWBH", RWBH);
			        boData.set("STEP", STEP);
			       
			        			        
			        SDK.getBOAPI().update("BO_DY_RWGL_RWGZFP_MAIN", boData);
				
				}
				
			}
		}*/
		return true;
	}

}
