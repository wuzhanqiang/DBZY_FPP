package com.nepharm.apps.fpp.biz.jcm.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.chanjar.weixin.common.util.StringUtils;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.jcm.bean.CfrwBean;
import com.nepharm.apps.fpp.biz.jcm.dao.JobDao;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.constant.ConfigConstant;

public class JCMUtil {

	public static void punishmentTaskExecute() {
		JobDao dao = new JobDao();
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(currentTime);
		List<CfrwBean> list = dao.getWckcList(dateStr);
		if(list != null && !list.isEmpty()) {
			for(CfrwBean cb : list) {
				String uuid = UUID.randomUUID().toString();
				String sql = "insert into BO_DY_JCM_KCCF (ID,BCFRZH,BCFRXM,KCMB,PZBM,GSBM,GSMC,GWBM,GWMC) values('"+uuid+"','"+cb.getBkhrzh()+"','"+cb.getBkhrmc()+"','"+cb.getKcbm()+"','"+cb.getPzbm()+"','"+cb.getGsbm()+"','"+cb.getGsmc()+"','"+cb.getGwbm()+"','"+cb.getGwmc()+"')";
				DBSql.update(sql);
				
				//处罚惩罚流程
				BO bo = new BO();
				bo.set("bkhrzh", cb.getBkhrzh());
				bo.set("bkhrmc", cb.getBkhrmc());
				bo.set("gsmc", cb.getGsmc());
				bo.set("gsbm", cb.getGsbm());
				GsjjcProcess(bo);
			}
		}
	}
	static void GsjjcProcess(BO bo){
		ProcessInstance processIns = SDK.getProcessAPI().createProcessInstance(
				PerformanceConstant.PROCESS_JXGL_GSJC, "admin", bo.get("bkhrmc") + "，您有一课程未按规定时间完成，按规定予以处罚");
		SDK.getProcessAPI().start(processIns);
		BO boMData = new BO();
		List<BO> boSData = new ArrayList<BO>();
		// BO boSData = new BO();
		boMData = setGscjMData(bo);
		boSData = setGscjSData(bo);
		// 创建数据-主表
		SDK.getBOAPI().create(PerformanceConstant.TAB_JXGL_GSJC_M, boMData, // "BO对象"
				processIns.getId(), // 流程实例对象
				"admin");// 创建者账户-默认admin
		// 创建数据-子表
		SDK.getBOAPI().create(PerformanceConstant.TAB_JXGL_GSJC, boSData, // "List<BO>对象"
				processIns.getId(), // 流程实例对象
				"admin");// 创建者账户-默认admin
		String taskInstId = processIns.getStartTaskInstId();
		SDK.getTaskAPI().completeTask(taskInstId, "admin");
		//处罚完毕将状态值跟新为2
		
	}
	/**
	 *  获得公司级奖惩子表数据
	 * @param bo
	 * @return 
	 */
	private static List<BO> setGscjSData(BO bo) {
		List<BO> boSData = new ArrayList<BO>();
		BO data = new BO();
		// 单据类型
		data.set("DJLX", 0);
		// 数据类型
		data.set("SJLX", 1);
		// 奖惩类型
		data.set("JCLX", "0");
		// 奖惩金额
		data.set("JCJE", 100);
		// 被奖惩公司
		data.set("GSMC", bo.getString("gsmc"));
		data.set("GSBM", bo.getString("gsgm"));
		// 被奖惩人
		data.set("BJCR", bo.get("bkhrmc"));
		data.set("BJCRZH", bo.get("bkhrzh"));
		data.set("YEAR", SDK.getRuleAPI().executeAtScript("@year"));
		data.set("MONTH", SDK.getRuleAPI().executeAtScript("@month"));
		// 状态，0:未处罚|1:处罚中|2:已处罚|-1:执行终止
		data.set("ZT", 1);
		data.set("ZJL", "");
		//需要明确惩罚制度
		String cfzd = SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + bo.get("USERID") + "," + bo.get("DATATYPE") + ",1)");
		//如果奖惩制度为空，则将平台中默认的制度名称与制度编码插入表中
		if(StringUtils.isNotEmpty(cfzd)) {
			// 制度编码
			data.set("ZDBM",cfzd.split(",")[0]);
			try {
				// 制度名称
				data.set("JCYJ", cfzd.split(",")[1]);
			} catch (Exception e) {
				data.set("JCYJ", "");
			}
		}else {
			String MRZDBM = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "MRZDBM");
			String MRZDMC = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "MRZDMC");
			// 制度编码
			data.set("ZDBM",MRZDBM);
			// 制度名称
			data.set("JCYJ", MRZDMC);
		}
		data.set("ZDMX", "");
		data.set("BZ", "");
//		data.set("GLGX", "<span style=\"color:red;cursor:pointer;\" onclick='openURL(\"" + bo.get("PINSTID")
//				+ "\",\"" + bo.get("TINSTID") + "\");'>查看单据</span>");
		boSData.add(data);
		return boSData;
	}

	/**
	 *  获得公司级奖惩主表数据
	 * @param bo
	 * @param cnName
	 * @return
	 */
	private static BO setGscjMData(BO bo) {
		BO data = new BO();
		// 单据编号
		data.set("DJBH", "GSJC" + SDK.getRuleAPI().executeAtScript("@sequenceMonth(DY_GSJCTZ,6,0)"));
		// 单据日期
		data.set("DJRQ", SDK.getRuleAPI().executeAtScript("@date"));
		// 申请人
		data.set("SQR", "admin");
		data.set("SQRZH", "admin");
		// 最大奖惩金额
		data.set("JCJE", 0);
		data.set("YEAR", SDK.getRuleAPI().executeAtScript("@year"));
		data.set("MONTH", SDK.getRuleAPI().executeAtScript("@month"));
		// 单据类型
		data.set("DJLX", 0);
		data.set("GSBM", SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + SDK.getRuleAPI().executeAtScript("@uid") + ",GSBM)"));
		data.set("GSMC", SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + SDK.getRuleAPI().executeAtScript("@uid") + ",GSMC)"));
		data.set("BMMC", SDK.getRuleAPI().executeAtScript("@departmentName"));
		data.set("BZ", "月度学习计划未按时间节点完成，每月扣100元/项");
		return data;
	}
}