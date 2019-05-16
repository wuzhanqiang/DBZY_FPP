package com.nepharm.apps.fpp.biz.pam.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.schedule.IJob;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class JjbqrtzJob implements IJob {

	public JjbqrtzJob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		Connection conn = DBSql.open();
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		try {
			st = conn.createStatement();
			//创建流程
			String processDefId = "obj_e512f5f8f01f41db84a1ce41db093d9c";
			String uid = "admin";
			String title = "";
			ProcessInstance p =  SDK.getProcessAPI().createProcessInstance(processDefId, uid, title);
			SDK.getProcessAPI().start(p);
			String bindId = p.getId();
			//创建主表数据
//			BO mainBoData = SDK.getBOAPI().getByProcess("BO_DY_XCGL_JJBQRTZ_M", bindId);
//			SDK.getBOAPI().update("BO_DY_XCGL_JJBQRTZ_M", mainBoData);
			BO mainBoData = new BO();
			mainBoData.set("YEAR", SDK.getRuleAPI().executeAtScript("@year"));
			mainBoData.set("MONTH", SDK.getRuleAPI().executeAtScript("@month"));
			mainBoData.set("SQRQ", SDK.getRuleAPI().executeAtScript("@date"));
			mainBoData.set("SQR", SDK.getRuleAPI().executeAtScript("@userName"));
			mainBoData.set("SQRZH", SDK.getRuleAPI().executeAtScript("@uid"));
			mainBoData.set("JTLRXS", "1.0");
			
			SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_M", mainBoData, bindId, uid);
			
			//获取数据
			//子公司奖金包
			//月初月末日期
			//String monthBegin = SDK.getRuleAPI().executeAtScript("@monthBegin");
			//String monthEnd = SDK.getRuleAPI().executeAtScript("@monthEnd");
			String year = SDK.getRuleAPI().executeAtScript("@year");
			//String month = SDK.getRuleAPI().executeAtScript("@month");
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String month = format.format(c.getTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String monthEnd = sdf.format(c.getTime()); //上月最后一天
			//System.out.println(gtime);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01");
			String monthBegin = sdf2.format(c.getTime()); //上月第一天
			//System.out.println(gtime2);
			
			
			//获取角色为总经理的人员名单
			//获取流程变量（角色ID）
			/*String jsId = "";
			jsId = SDK.getProcessAPI().getVariable(p, "ROLEID").toString();*/
			
			//查询品种售价维护信息-主表
			List<BO> zgsBo = SDK.getBOAPI().query("BO_DY_JCXX_PZSJWHXX_M").list();
			for(int i=0;i<zgsBo.size();i++){
				
				BO zgsBoData = new BO();
				String hrGSMC = zgsBo.get(i).getString("GSMC");
				String hrGSBM = zgsBo.get(i).getString("GSBM");
				String gsBindId = zgsBo.get(i).getString("BINDID");
				double JJB = 0.0;
				//创建子公司奖金包
				zgsBoData.set("GSMC", hrGSMC);
				zgsBoData.set("GSBM", hrGSBM);
				SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_ZGS", zgsBoData, bindId, uid);
				String zgsBoId = zgsBoData.getId();
				
				//单位信息表
//				List<BO> dwxxBo = SDK.getBOAPI().query("BO_DY_JCXX_DWXX")
//						.addQuery("BM = ", hrGSBM).list();
				List<BO> dwxxBo = SDK.getBOAPI().query("BO_DY_JCXX_K3BMDZB")
						.addQuery("GSBM = ", hrGSBM).list();
				for(int k=0;k<dwxxBo.size();k++){
					
					String k3GSBM = dwxxBo.get(k).getString("K3BM");
					//产品单价
					List<BO> cpdjBo = SDK.getBOAPI().query("BO_DY_JCXX_PZSJWHXX")
							.bindId(gsBindId).list();
					for(int j=0;j<cpdjBo.size();j++){
						BO zgsxxBoData = new BO();
						String CPMC = cpdjBo.get(j).getString("CPMC");
						String CPGG = cpdjBo.get(j).getString("CPGG");
						String CPBM = cpdjBo.get(j).getString("CPBM");
						String CPDJ = cpdjBo.get(j).getString("SJSJ");
						double CPZJ = 0.0;
						
						//String zgsxxId = zgsxxBoData.getId();
						//k3产品入库数量
						//测试
//					monthBegin="2018-04-01";
//					monthEnd="2018-04-30";
						sql = "SELECT sum(FREALQTY) as sum "
								+ "FROM BO_DY_JCXX_K3SCRKDTB "
								+ "where TO_CHAR(FDATE,'yyyy-mm-dd') >= '"+monthBegin+"' "
								+ "and TO_CHAR(FDATE,'yyyy-mm-dd') <= '"+monthEnd+"' " 
								+ "and FMATERIALID='"+CPBM+"' and GSBM='"+k3GSBM+"' "
								+ "GROUP BY FMATERIALID";
						
						rs = st.executeQuery(sql);
						String SUM = "0";
						while(rs.next()){
							SUM = rs.getString("SUM");
						}
						
						if(SUM != null && !"0".equals(SUM)){
							double dCPDJ = Double.parseDouble(CPDJ);
							double dCPCL = Double.parseDouble(SUM);
							CPZJ = dCPDJ*dCPCL;
							JJB += CPZJ;
						}
						
						//创建子公司奖金包详细
						zgsxxBoData.set("CPMC", CPMC);
						zgsxxBoData.set("CPGG", CPGG);
						zgsxxBoData.set("CPBM", CPBM);
						zgsxxBoData.set("CPDJ", CPDJ);
						zgsxxBoData.set("CPZJ", CPZJ);
						zgsxxBoData.set("CPCL", SUM);
						SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_ZGS_ZDZB", zgsxxBoData, zgsBoId, uid);
					}
				}
				
				//更新子公司奖金包
				JJB = Math.round(JJB*100)/100.0;
				zgsBoData.set("JJB", JJB);
				zgsBoData.set("YL1", JJB);
				
				Hashtable<String, String> ht = getJJB(JJB, hrGSBM, year, month, st, rs, zgsBoId, uid);
				double TZJE = Double.parseDouble(ht.get("TZJE").toString());
				double QDJJB = Double.parseDouble(ht.get("QDJJB").toString());
				double SJJJB = Double.parseDouble(ht.get("SJJJB").toString());
				
				zgsBoData.set("TZJE", TZJE);
				zgsBoData.set("QDJJB", QDJJB);
				zgsBoData.set("SJJJB", SJJJB);
				SDK.getBOAPI().update("BO_DY_XCGL_JJBQRTZ_ZGS", zgsBoData);
				
			}
			
			//部门奖金包
			List<BO> bmdbBo = SDK.getBOAPI().query("BO_DY_JCXX_BMDB").list();
			for(int i=0;i<bmdbBo.size();i++){
				BO bmBOData = new BO();
				String BM = bmdbBo.get(i).getString("BM");
				String BMID = bmdbBo.get(i).getString("BMID");
				String BMDB = bmdbBo.get(i).getString("BMDB");
				String DBDJ = bmdbBo.get(i).getString("DBDJ");
				
				double dBMDB = Double.parseDouble(BMDB);
				double dDBDJ = Double.parseDouble(DBDJ);
				double dJJB = dBMDB * dDBDJ;
				bmBOData.set("BM", BM);
				bmBOData.set("BMID", BMID);
				bmBOData.set("BMDB", BMDB);
				bmBOData.set("DBDJ", DBDJ);
				bmBOData.set("JJB", dJJB);
				bmBOData.set("YL1", dJJB);
				SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_BM", bmBOData, bindId, uid);
				String bmBoId = bmBOData.getId();
				//调整奖金包
				Hashtable<String, String> ht = getJJB(dJJB, BMID, year, month, st, rs, bmBoId, uid);
				double TZJE = Double.parseDouble(ht.get("TZJE").toString());
				double QDJJB = Double.parseDouble(ht.get("QDJJB").toString());
				double SJJJB = Double.parseDouble(ht.get("SJJJB").toString());
				
				bmBOData.set("TZJE", TZJE);
				bmBOData.set("QDJJB", QDJJB);
				bmBOData.set("SJJJB", SJJJB);
				
				SDK.getBOAPI().update("BO_DY_XCGL_JJBQRTZ_BM", bmBOData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBSql.close(conn, st, rs);
		}
		
	}
	
	public Hashtable<String, String> getJJB(double JJB, String hrGSBM, String year, String month, 
			Statement st, ResultSet rs, String BoId, String uid) throws SQLException{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		
		//调整奖金包
		//JCLX 奖惩类型 (0:惩罚|1:奖励|2:奖励|3:惩罚)
		//SJLX 数据类型(0:部门|1:个人)
		//DJLX 单据类型(0:奖惩|1:冲账)
		double zjl = 0.0;
		double zcf = 0.0;
		double TZJE = 0.0;
		double QDJJB = 0.0;//确定奖金包（奖金计算用）
		double SJJJB = 0.0;//实际奖金包（显示）
		//获取奖励金额（部门）
		String sql = "SELECT sum(JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and (JCLX='1' or JCLX='2') and SJLX='0'"
				+ "and YEAR='"+year+"' and MONTH='"+month+"' and ZT='1' "
				+ "GROUP BY GSBM";
		rs = st.executeQuery(sql);
		double gsjl = 0.0;
		while(rs.next()){
			if(rs.getString("SUM") != null && !"".equals(rs.getString("SUM"))){
				
				gsjl = rs.getDouble("SUM");
			}
		}
		//获取惩罚金额（部门）
		sql = "SELECT sum(JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and (JCLX='0' or JCLX='3') and SJLX='0'"
				+ "and YEAR='"+year+"' and MONTH='"+month+"' and ZT='1' "
				+ "GROUP BY GSBM";
		rs = st.executeQuery(sql);
		double gscf = 0.0;
		while(rs.next()){
			if(rs.getString("SUM") != null && !"".equals(rs.getString("SUM"))){
				gscf = rs.getDouble("SUM");
			}
		}
		//部门总调整金额
		TZJE = gsjl - gscf;
		//确定奖金包（奖金计算用）
		QDJJB = TZJE + JJB;
		
		//获取奖励金额（个人）
		/*sql = "SELECT sum(t1.JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC t1 "
				+ "join ORGUSER t2 on t1.BJCRZH=t2.USERID "
				+ "where t1.GSBM='"+hrGSBM+"' and (t1.JCLX='1' or t1.JCLX='2') and t1.SJLX='1'"
				+ "and t1.YEAR='"+year+"' and t1.MONTH='"+month+"' and t1.ZT='1' "
						+ "and t2.ROLEID<>'"+jsId+"' " 
				+ "GROUP BY t1.GSBM";*/
		//@公式获得需要去除的账号
		sql = "SELECT sum(JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and (JCLX='1' or JCLX='2') and SJLX='1'"
				+ "and YEAR='"+year+"' and MONTH='"+month+"' and ZT='1' and ZJL<>BJCRZH "
				+ "GROUP BY GSBM";
		rs = st.executeQuery(sql);
		double grjl = 0.0;
		while(rs.next()){
			if(rs.getString("SUM") != null && !"".equals(rs.getString("SUM"))){
				grjl = rs.getDouble("SUM");
			}
		}
		//获取惩罚金额（个人）
		/*sql = "SELECT sum(t1.JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC t1 "
				+ "join ORGUSER t2 on t1.BJCRZH=t2.USERID "
				+ "where t1.GSBM='"+hrGSBM+"' and (t1.JCLX='0' or t1.JCLX='3') and t1.SJLX='1'"
				+ "and t1.YEAR='"+year+"' and t1.MONTH='"+month+"' and t1.ZT='1' "
						+ "and t2.ROLEID<>'"+jsId+"' " 
				+ "GROUP BY t1.GSBM";*/
		//@公式获得需要去除的账号
		sql = "SELECT sum(JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and (JCLX='0' or JCLX='3') and SJLX='1'"
				+ "and YEAR='"+year+"' and MONTH='"+month+"' and ZT='1' and ZJL<>BJCRZH "
				+ "GROUP BY GSBM";
				/*"SELECT sum(JCJE) as sum "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and (JCLX='0' or JCLX='3') and SJLX='1'"
				+ "and YEAR='"+year+"' and MONTH='"+month+"' and ZT='1' "
				+ "GROUP BY GSBM";*/
		rs = st.executeQuery(sql);
		double grcf = 0.0;
		while(rs.next()){
			if(rs.getString("SUM") != null && !"".equals(rs.getString("SUM"))){
				
				grcf = rs.getDouble("SUM");
			}
		}
		//个人+部门的总调整金额
		TZJE += grjl - grcf;
		//实际奖金包（显示）
		SJJJB = TZJE + JJB;
		
		ht.put("TZJE", TZJE+"");
		ht.put("QDJJB", QDJJB+"");
		ht.put("SJJJB", SJJJB+"");
		
		//将奖惩明细显示
		/*List<BO> JCMX = SDK.getBOAPI().query("BO_DY_JXGL_GSJC").addQuery("GSBM", hrGSBM)
				.addQuery("YEAR", year).addQuery("MONTH", month).addQuery("ZT", "1").list();
		for(BO b:JCMX){
			BO TZJEXX = new BO();
			TZJEXX.set("JCLX", b.getString("JCLX"));
			TZJEXX.set("DJLX", b.getString("DJLX"));
			TZJEXX.set("JCJE", b.getString("JCJE"));
			TZJEXX.set("GSMC", b.getString("GSMC"));
			TZJEXX.set("GSBM", b.getString("GSBM"));
			if(b.getString("YGXM") == null || "".equals(b.getString("YGXM"))){
				TZJEXX.set("YGXM", "");
			} else {
				TZJEXX.set("YGXM", b.getString("YGXM"));
			}
			
			if(b.getString("YGBM") == null || "".equals(b.getString("YGBM"))){
				TZJEXX.set("YGBM", "");
			} else {
				TZJEXX.set("YGBM", b.getString("YGBM"));
			}
			TZJEXX.set("YEAR", b.getString("YEAR"));
			TZJEXX.set("MONTH", b.getString("MONTH"));
			TZJEXX.set("JCYJ", b.getString("JCYJ"));
			
			SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_TZJEXX", TZJEXX, BoId, "admin");
		}*/
		
		/*sql = "SELECT t1.JCLX,t1.DJLX,t1.JCJE,t1.GSMC,t1.GSBM,t1.BJCR,t1.BJCRZH,t1.YEAR,t1.MONTH,t1.JCYJ "
				+ "FROM BO_DY_JXGL_GSJC t1 "
				+ "join ORGUSER t2 on t1.BJCRZH=t2.USERID "
				+ "where t1.GSBM='"+hrGSBM+"' and t1.YEAR='"+year+"' and t1.MONTH='"+month+"' "
						+ "and t1.ZT='1' and t2.ROLEID<>'"+jsId+"' " 
				+ "GROUP BY t1.GSBM";*/
		sql = "SELECT JCLX,DJLX,JCJE,GSMC,GSBM,BJCR,BJCRZH,YEAR,MONTH,JCYJ "
				+ "FROM BO_DY_JXGL_GSJC "
				+ "where GSBM='"+hrGSBM+"' and YEAR='"+year+"' and MONTH='"+month+"' "
						+ "and ZT='1' and ((ZJL <> BJCRZH AND BJCRZH IS NOT NULL) or BJCRZH IS NULL) ";
		rs = st.executeQuery(sql);
		while(rs.next()){
			BO TZJEXX = new BO();
			TZJEXX.set("JCLX", rs.getString("JCLX"));
			TZJEXX.set("DJLX", rs.getString("DJLX"));
			TZJEXX.set("JCJE", rs.getString("JCJE"));
			TZJEXX.set("GSMC", rs.getString("GSMC"));
			TZJEXX.set("GSBM", rs.getString("GSBM"));
			if(rs.getString("BJCR") == null || "".equals(rs.getString("BJCR"))){
				TZJEXX.set("YGXM", "");
			} else {
				TZJEXX.set("YGXM", rs.getString("BJCR"));
			}
			
			if(rs.getString("BJCRZH") == null || "".equals(rs.getString("BJCRZH"))){
				TZJEXX.set("YGBM", "");
			} else {
				TZJEXX.set("YGBM", rs.getString("BJCRZH"));
			}
			TZJEXX.set("YEAR", rs.getString("YEAR"));
			TZJEXX.set("MONTH", rs.getString("MONTH"));
			TZJEXX.set("JCYJ", rs.getString("JCYJ"));
			SDK.getBOAPI().create("BO_DY_XCGL_JJBQRTZ_TZJEXX", TZJEXX, BoId, uid);
		}
		return ht;
	}

}
