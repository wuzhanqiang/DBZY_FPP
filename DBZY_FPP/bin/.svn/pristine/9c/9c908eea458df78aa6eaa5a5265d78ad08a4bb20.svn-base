package com.nepharm.apps.fpp.biz.sem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.model.def.ProcessDefinition;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

import me.chanjar.weixin.common.util.StringUtils;

public class TimeManagerDao {
	/**
	 * 获得监控日志表以及超时任务记录表中所需要的其他数据
	 * 
	 * @return
	 */
	public static List<BO> getData() {
		//奖惩开关（24小时）
		//String jckg24 = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "JCKG24");
		//奖惩开关（72小时）
		//String jckg72 = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "JCKG72");
		//奖惩开始时间
		String JCSJ = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "JCSJ");
		//通知时间点
		String TZSJ = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "TZSJ");
		//奖惩时间点
		String JCSJD = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "JCSJD");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT lg.ID,lg.YEAR,lg.MONTH,lg.DAY,lg.HM,lg.RECORDTIME,lg.PINSTID"
				+ ",lg.TINSTID,lg.TASKTITLE,lg.KPISCORE,lg.PDEFID,lg.TDEFID,lg.USERID"
				+ ",lg.USERNAME,lg.DEPTID,lg.DEPTNAME" + " FROM " + PerformanceConstant.TAB_ACT_TASKTIMEOUT_LOG
				+ " lg left join " + PerformanceConstant.TAB_SXGL_CSJL
				+ " csjl on lg.ID=csjl.LOGID where csjl.LOGID IS NULL ");
		sql.append(" and KPISCORE in ( ");
		//判断超时类型为通知的条件
		if(StringUtils.isNotEmpty(TZSJ)) {
			sql.append(TZSJ);
		}
		//判断超时类型为奖惩的条件
		if(StringUtils.isNotEmpty(JCSJD)) {
			if(StringUtils.isNotEmpty(TZSJ)) sql.append(",");
			sql.append(JCSJD);
		}
		sql.append(")");

		if (StringUtils.isNotEmpty(JCSJ)) {
			if(JCSJ.length()>10){
				JCSJ=JCSJ.substring(0, 10);
			}
			
			sql.append(" and lg.RECORDTIME >= to_date('" + JCSJ + "','yyyy-mm-dd')");//yyyy-mm-dd hh24:mi:ss
		}
		List<BO> data = new ArrayList<BO>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while (rs.next()) {
				try {
					BO bo = new BO();
					String userId=rs.getString("USERID");
					bo.set("LOGID", rs.getString("ID"));
					bo.set("YEAR", rs.getInt("YEAR"));
					bo.set("MONTH", rs.getInt("MONTH"));
					bo.set("DAY", rs.getInt("DAY"));
					bo.set("HM", rs.getString("HM"));
					// 超时记录时间
					bo.set("RECORDTIME", rs.getDate("RECORDTIME"));
					bo.set("PINSTID", rs.getString("PINSTID"));
					bo.set("TINSTID", rs.getString("TINSTID"));
					bo.set("TASKTITLE", rs.getString("TASKTITLE"));
					// 超时类型 ,91:22小时，92：24小时，93：72小时
					bo.set("DATATYPE", rs.getInt("KPISCORE"));
					String wfId=rs.getString("PDEFID");//流程名
					String wfName="";
					try {
						ProcessDefinition process=SDK.getRepositoryAPI().getProcessDefinition(wfId);
						wfName=process.getName();
					} catch (Exception e) {
						wfName="未知";
					}
					bo.set("PDEFID",wfId );
					bo.set("LCMC", wfName);
					bo.set("TDEFID", rs.getString("TDEFID"));
					bo.set("USERID", rs.getString("USERID"));
					bo.set("USERNAME", rs.getString("USERNAME"));
					bo.set("DEPTID", rs.getString("DEPTID"));
					bo.set("DEPTNAME", rs.getString("DEPTNAME"));
					bo.set("COMPANYID",
							SDK.getRuleAPI().executeAtScript("@getUserInfo(" + userId + ",GSBM)"));
					bo.set("COMPANYNAME",
							SDK.getRuleAPI().executeAtScript("@getUserInfo(" + userId + ",GSMC)"));
					// 岗位级别
					bo.set("POSLEVEL",
							SDK.getRuleAPI().executeAtScript("@getUserInfo(" + userId + ",GWJB)"));
					// 奖惩金额
					String amont = SDK.getRuleAPI().executeAtScript(
							"@getUserInfo(" + userId + "," + rs.getInt("KPISCORE") + ")");
					bo.set("AMONT", StringUtils.isEmpty(amont) ? 0 : amont);
					// 状态，0:未处罚|1:处罚中|2:已处罚|-1:执行终止
					bo.set("ZT", 0);
					// 错误计数
					bo.set("ERRORNUM", 0);
					bo.set("EXT3", "生效");
					data.add(bo);
				} catch (Exception e) {
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	/**
	 * 将日志表中的数据插入超时任务记录表中
	 * 
	 * @param data
	 */
	public static void insert(List<BO> data) {
		SDK.getBOAPI().createDataBO(PerformanceConstant.TAB_SXGL_CSJL, data, UserContext.fromUID("admin"));
	}

	/**
	 * 获得超时任务记录表中未处罚的数据
	 * 
	 * @return
	 */
	public static List<BO> getCSJLData() {
		//错误上限次数
		String cwsx = SDK.getAppAPI().getProperty(ConfigConstant.APP_ID, "CWSX");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ID,LOGID,YEAR,MONTH,DAY,HM,RECORDTIME,PINSTID,TINSTID,"
				+ "TASKTITLE,DATATYPE,PDEFID,TDEFID,USERID,USERNAME,DEPTID,"
				+ "DEPTNAME,DEPTID,DEPTNAME,COMPANYID,COMPANYNAME,POSLEVEL," + "AMONT,ZT,ERRORNUM,EXT1,EXT3 from "
				+ PerformanceConstant.TAB_SXGL_CSJL);
		sql.append(" where ZT<=0 ");//0 未执行、-1执行终止
		try {
			if (StringUtils.isNotEmpty(cwsx) && Integer.parseInt(cwsx) > 0) {
				sql.append(" and ERRORNUM <= " + cwsx);
			} else {
				//默认上限次数为3
				sql.append(" and ERRORNUM <= 3");
			}
		} catch (Exception e) {
			sql.append(" and ERRORNUM <= 3");
		}
		List<BO> data = new ArrayList<BO>();
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			pstat = conn.prepareStatement(sql.toString());
			rs = pstat.executeQuery();
			while (rs.next()) {
				try {
					BO bo = new BO();
					bo.set("LOGID", rs.getString("LOGID"));
					bo.set("YEAR", rs.getInt("YEAR"));
					bo.set("MONTH", rs.getInt("MONTH"));
					bo.set("DAY", rs.getInt("DAY"));
					bo.set("HM", rs.getString("HM"));
					// 超时记录时间
					bo.set("RECORDTIME", rs.getDate("RECORDTIME"));
					bo.set("PINSTID", rs.getString("PINSTID"));
					bo.set("TINSTID", rs.getString("TINSTID"));
					bo.set("TASKTITLE", rs.getString("TASKTITLE"));
					// 超时类型,91:22小时，92：24小时，93：72小时
					bo.set("DATATYPE", rs.getInt("DATATYPE"));
					bo.set("PDEFID", rs.getString("PDEFID"));
					bo.set("TDEFID", rs.getString("TDEFID"));
					bo.set("USERID", rs.getString("USERID"));
					bo.set("USERNAME", rs.getString("USERNAME"));
					bo.set("DEPTID", rs.getString("DEPTID"));
					// 部门名称
					bo.set("DEPTNAME", rs.getString("DEPTNAME"));
					bo.set("COMPANYID", rs.getString("COMPANYID"));
					// 单位名称
					bo.set("COMPANYNAME", rs.getString("COMPANYNAME"));
					// 岗位级别
					bo.set("POSLEVEL", rs.getString("POSLEVEL"));
					// 奖惩金额
					bo.set("AMONT", rs.getInt("AMONT"));
					// 状态，0:未处罚|1:处罚中|2:已处罚|-1:执行终止
					bo.set("ZT", rs.getInt("ZT"));
					// 错误计数
					bo.set("ERRORNUM", rs.getInt("ERRORNUM"));
					bo.set("EXT1", rs.getString("EXT1"));
					bo.set("EXT3", rs.getString("EXT3"));
					data.add(bo);
					/*
					 * BO BoData = new BO(); BoData.set("ID", rs.getString("ID")); BoData.set("ZT",
					 * 1); //执行奖惩时间 BoData.set("ACTIONTIME", DateUtil.getStringDate(new Date()));
					 */
					// SDK.getBOAPI().update(boName, recordData, conn);
					Connection conn1 = DBSql.open();
					try {
						conn1.setAutoCommit(false);
						String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						// 获得未处罚的数据后，将超时任务记录表中状态字段修改为1，即处罚中，并更新奖惩执行时间
						String sql1 = "update " + PerformanceConstant.TAB_SXGL_CSJL + ""
								+ " set ZT=1,ACTIONTIME=to_date('" + date + "', 'yyyy-mm-dd hh24:mi:ss') where ID='"
								+ rs.getString("ID") + "' ";
						DBSql.update(conn, sql1);
						conn1.commit();
					} catch (Exception e) {
						try {
							conn1.rollback();// 删除数据回滚
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} finally {
						DBSql.close(conn1, null, null);
					}

					// 跟新状态为1和执行奖惩时间
					// SDK.getBOAPI().update(PerformanceConstant.TAB_SXGL_CSJL, BoData);
				} catch (Exception e) {
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBSql.close(conn, pstat, rs);
		}
		return data;
	}

	/**
	 * 触发超时通知提醒流程
	 * 
	 * @param bo
	 */
	public static void CstztxProcess(BO bo) {
		try {
			String sql1 = "select CNNAME from BO_ACT_DICT_KV_ITEM"
					+ "  where DICTKEY='DY.SXCFLX' and ITEMNO='"+bo.get("DATATYPE")+"' and EXTTEXT1='0'";
			//获得超时类型所对应的小时数
			String cnName = DBSql.getString(sql1,"CNNAME");
			ProcessInstance processIns = SDK.getProcessAPI().createProcessInstance(//cnName
					PerformanceConstant.PROCESS_SXGL_JBJC, "admin", bo.get("USERNAME") + ",您有一条流程即将超"+"“办理时限”"+"未审批,特此通知");
			SDK.getProcessAPI().start(processIns);
			BO boData = new BO();
			// 流程ID
			boData.set("LCID", bo.get("PINSTID"));
			// 任务ID
			boData.set("RWID", bo.get("TINSTID"));
			// 任务标题
			boData.set("RWBT", bo.get("TASKTITLE"));
			// 被通知人
			boData.set("BTZR", bo.get("USERID"));
			SDK.getBOAPI().create(PerformanceConstant.TAB_SXGL_TZTX, boData, // "BO对象"
					processIns.getId(), // 流程实例对象
					"admin");
			// 关闭流程
			String taskInstId = processIns.getStartTaskInstId();
			SDK.getTaskAPI().completeTask(taskInstId, "admin");
			// 将超时任务记录表中状态修改为2，即已处罚或已通知
			String sql = "update " + PerformanceConstant.TAB_SXGL_CSJL + " set ZT=2 where LOGID='" + bo.get("LOGID")
					+ "'";
			Connection conn = DBSql.open();
			// DBSql.update(sql);
			try {
				conn.setAutoCommit(false);
				DBSql.update(conn, sql);
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();// 删除数据回滚
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				DBSql.close(conn, null, null);
			}
		} catch (Exception e) {
			/* 数据 发送通知、奖惩成功  处理时 失败，记录失败原因（统一一个方法调用，注意事务处理。）
			String sql = "update " + PerformanceConstant.TAB_SXGL_CSJL + " set ZT=-1,ERRORMEG='发起通知流程时报错，信息为："
					+ e.getMessage() + "' where LOGID='" + bo.get("LOGID") + "'";
			DBSql.update(sql);*/
			String errorMessage = "发起通知流程时报错，信息为："+ e.getMessage();
			//发生异常后，对超时任务记录表进行跟新
			cwts(PerformanceConstant.TAB_SXGL_CSJL,errorMessage,bo.get("LOGID").toString());
		}
	}

	/**
	 * 触发公司级奖惩流程
	 * 
	 * @param bo
	 */
	public static void GsjjcProcess(BO bo) {
		try {
			//对应 的 奖惩制度
			String cfzd = SDK.getRuleAPI()
					.executeAtScript("@getUserInfo(" + bo.get("USERID") + "," + bo.get("DATATYPE") + ",1)");
			String temp = "";
			try {
				temp = cfzd.split(",")[1];
			}catch(Exception e) {
				
			}
			String sql1 = "select CNNAME from BO_ACT_DICT_KV_ITEM"
					+ " where DICTKEY='DY.SXCFLX' and ITEMNO='"+bo.get("DATATYPE")+"' and EXTTEXT1='1'";
			//获得超时类型所对应的小时数
			String cnName = DBSql.getString(sql1,"CNNAME");
			ProcessInstance processIns = SDK.getProcessAPI().createProcessInstance(//cnName//temp
					PerformanceConstant.PROCESS_JXGL_GSJC, "admin", bo.get("USERNAME") + "，有一条流程超过"+"“办理时限”"+"未审批，依据"+"制度"+"规定予以处罚");
			SDK.getProcessAPI().start(processIns);
			BO boMData = new BO();
			List<BO> boSData = new ArrayList<BO>();
			// BO boSData = new BO();
			boMData = getGscjMData(bo,cnName);
			boSData = getGscjSData(bo);
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
			String sql = "update " + PerformanceConstant.TAB_SXGL_CSJL + " set ZT=2 where LOGID='" + bo.get("LOGID")
					+ "'";
			Connection conn = DBSql.open();
			try {
				conn.setAutoCommit(false);
				DBSql.update(conn, sql);
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();// 删除数据回滚
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				DBSql.close(conn, null, null);
			}
		} catch (Exception e) {
			//DBSql.update("update " + PerformanceConstant.TAB_SXGL_CSJL
			//		+ " set ZT=-1 , ERRORNUM=ERRORNUM+1 where LOGID='" + bo.get("LOGID") + "'");
			String errorMessage = "发起奖惩流程时报错，信息为："+ e.getMessage();
			//发生异常后，对超时任务记录表进行跟新
			cwts(PerformanceConstant.TAB_SXGL_CSJL,errorMessage,bo.get("LOGID").toString());
		}
	}

	/**
	 *  获得公司级奖惩子表数据
	 * @param bo
	 * @return 
	 */
	private static List<BO> getGscjSData(BO bo) {
		List<BO> boSData = new ArrayList<BO>();
		BO data = new BO();
		// 单据类型
		data.set("DJLX", 0);
		// 数据类型
		data.set("SJLX", 1);
		// 奖惩类型
		data.set("JCLX", "0");
		// 奖惩金额
		data.set("JCJE", bo.get("AMONT"));
		// 被奖惩公司
		data.set("GSMC", SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + bo.get("USERID") + ",GSMC)"));
		String gsbm=SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + bo.get("USERID") + ",GSBM)");
		data.set("GSBM",gsbm );
		// 被奖惩人
		data.set("BJCR", bo.get("USERNAME"));
		data.set("BJCRZH", bo.get("USERID"));
		data.set("YEAR", SDK.getRuleAPI().executeAtScript("@year"));
		data.set("MONTH", SDK.getRuleAPI().executeAtScript("@month"));
		// 状态，0:未处罚|1:处罚中|2:已处罚|-1:执行终止
		data.set("ZT", 1);
		String rule="@sqlValue(select USERID from (select USERID,USERNAME,ROLEID from orguser where DEPARTMENTID in( select id from orgdepartment start with departmentno = '"+gsbm+"' connect by prior id = parentdepartmentid) )where roleid like '52d8f37e-163d-417a-b3e2-07ac8eaaf85%')";
		String zjl=SDK.getRuleAPI().executeAtScript(rule);
		data.set("ZJL", zjl);
		
		
		String cfzd = SDK.getRuleAPI()
				.executeAtScript("@getUserInfo(" + bo.get("USERID") + "," + bo.get("DATATYPE") + ",1)");
		//如果奖惩制度为空，则将平台中默认的制度名称与制度编码插入表中
		if(StringUtils.isNotEmpty(cfzd)) {
			// 制度编码
			data.set("ZDBM",cfzd.split(",")[0]);
			try {
				data.set("JCYJ", cfzd.split(",")[1]);// 制度名称
				
				String mx=DBSql.getString("select EXTTEXT3 from BO_ACT_DICT_KV_ITEM where DICTKEY ='DY.ZDFL' and ITEMNO='"+cfzd.split(",")[0]+"'","EXTTEXT3");
				mx=mx==null?"":mx;
				data.set("ZDMX", mx);//明细、制度依据
			} catch (Exception e) {
				data.set("JCYJ", "");
				data.set("ZDMX","");
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
		data.set("BZ", bo.getString("EXT1"));
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
	private static BO getGscjMData(BO bo,String cnName) {
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
		data.set("YBINDID", bo.get("PINSTID"));
		data.set("YTASKID", bo.get("TINSTID"));
		data.set("BZ","超过流程时限，自动处罚。" );//"流程时限类型为"+cnName+"，自动处罚。"
		return data;
	}
	
	/**
	 * 执行通知或奖惩是发生错误的处理
	 * @param boName
	 * @param errorMessage
	 * @param logid
	 */
	private static void cwts(String boName, String errorMessage, String logid) {
		//超时任务记录表中状态设为执行终止（-1），错误次数加一
		String sql = "update "+boName+" set ZT=-1,ERRORMEG='"+errorMessage+"' where LOGID='"+logid+"'";
		Connection conn = DBSql.open();
		try {
			conn.setAutoCommit(false);
			DBSql.update(conn, sql);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();// 删除数据回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBSql.close(conn, null, null);
		}
	}

}
