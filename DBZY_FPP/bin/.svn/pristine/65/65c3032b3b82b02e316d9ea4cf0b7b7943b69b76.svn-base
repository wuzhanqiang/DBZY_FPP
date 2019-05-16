package com.nepharm.apps.fpp.biz.pam.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.apps.AppsConst;
import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.cc.Adapter.DB;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.bpms.bpmn.engine.model.run.delegate.ProcessInstance;
import com.actionsoft.exception.AWSAPIException;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.nepharm.apps.fpp.biz.pam.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.util.BOUtil;
/**
 * 薪资模块-奖金录入流程-录入节点-扩展按钮同步HR系统员工基本工资功能
 * @author zhangjh
 *
 */
public class MyTBGZBtnActionImpl extends ValueListener {

    @Override
    public String execute(ProcessExecutionContext ctx) throws Exception {
        //参数获取
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //表单ID
        String formId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        
        UserContext user = ctx.getUserContext();
        //获得流程实例id
        String bindid = ctx.getProcessInstance().getId();
        
        //获得表单填写公司编码、执行年份、执行月份
        String sql;
        sql = "select SYGSBM,SYGSMC,ZXNF,ZXYF from "+ProductPlanConstant.TAB_XCGL_JJLR_M+" where bindid='"+bindid+"'";
        String SYGSBM = DBSql.getString(sql,"SYGSBM");//得到公司编码
        String SYGSMC = DBSql.getString(sql,"SYGSMC");//得到公司名称
        String ZXNF = DBSql.getString(sql,"ZXNF");//得到年份
        String ZXYF = DBSql.getString(sql,"ZXYF");//得到月份
        String LZXNF;
        String LZXYF;
        //通过查找HR系统得到所有公司下的人员及基本工资
        String SFLJHR = (String) ctx.getVariable("SFLJHR");//获得流程全局变量确认手动导入还是系统自动查找HR
        String BCYJSJS = (String) ctx.getVariable("BCYJSJS");//获得流程全局变量不参与奖金计算的角色
        BO formData;//存储数据
        List<BO> lhgwData = null;
        List<BO> tzsdData = null;
        BO bo = null;
        String RYZH = null;//员工编号
        String YGZBGSBM = null;//岗位维护表中公司编码
        String rclGWBM = null;//日产量中的操作岗位
        String YGZBGWBM = null;//岗位维护表中岗位编码
        String glzxbl;
        // Ajax方式
        ResponseObject ro;
        boolean r = true;// 针对业务进行处理
        String gdzdz = null;
        if(SFLJHR != null && SFLJHR.equals("1")){//此为手动输入
            // 错误时
            ro = ResponseObject.newErrResponse();
            ro.msg("暂时没有与HR系统对接，需要手动导入工资信息");
        	r = false;
        	
        }else if(SFLJHR != null && SFLJHR.equals("2")){//系统自动读取HR系统
        	//如果子表存在数据删除数据，再同步。
        	
        	sql = "select count(*) CNT from "+ProductPlanConstant.TAB_XCGL_JJLR_S+" where bindid='"+bindid+"'";
        	int CNT = DBSql.getInt(sql,"CNT");
        	if(CNT>0){
        		sql = "delete from "+ProductPlanConstant.TAB_XCGL_JJLR_S+" where bindid='"+bindid+"'";
        		DBSql.update(sql);
        	}
        	
        	formData = new BO();
        	//得到日产量表中人员信息（判断是否为临时调转人员：操作岗人员，非操作岗工作两天人员，其他公司人员，如果为临时调转人员创建一条数据并提示用户）
        	sql = "select a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH, count(*) CNT,sum(RJSJE) RJSJE,sum(RSJCQTS) RSJCQTS from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_M+" a,"
        			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX+" b,"
        			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" c"
        			+ "	where a.bindid=b.bindid and c.bindid=b.id and a.NF='"+ZXNF+"' and a.YF='"+ZXYF+"' and a.GSBM='"+SYGSBM+"' and a.isend=1"
        			+ " group by a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH";
        	lhgwData = BOUtil.queryEncapsulationData(sql);
        	for(int i=0;i<lhgwData.size();i++) {
        		bo = lhgwData.get(i);
        		RYZH = bo.getString("RYZH");//员工编号
        		bo.set("RYXM",bo.getString("RYMC"));
        		
        		float RSJCQTS = 0;
    			try {
    				if(bo.getString("RSJCQTS")==null || "".equals(bo.getString("RSJCQTS"))){
    					RSJCQTS = 0;
    				}else{
    					RSJCQTS = Float.valueOf(bo.getString("RSJCQTS"));
    				}
    				
    			} catch (NumberFormatException e) {
    				RSJCQTS = 0;
    			} catch (AWSDataAccessException e) {
    				RSJCQTS = 0;
    			}
        		bo.set("SJCQTS",RSJCQTS);//实际出勤天数
//        		sql = "select YLGZ from "+ProductPlanConstant.TAB_JCXX_HRYFRYXCTB+" where RYBM='"+RYZH+"' and NF='"+ZXNF+"' and YF='"+ZXYF+"'";
//        		gdzdz = DBSql.getString(sql,"YLGZ");
//	      		if(gdzdz==null || "".equals(gdzdz)){
//	      			 
//	      			bo.set("YLGZ","0");
//	      		}else{
//	      			bo.set("YLGZ",gdzdz);
//	      		}

//        		bo.set("YLGZ",DBSql.getString(sql,"YLGZ")==""?"0":DBSql.getString(sql,"YLGZ"));
        		
        		//查询公司月份下出勤数量
            	sql = "select CQTS from "+PerformanceConstant.TAB_XCGL_YYCQTSWH_M+" a,"+PerformanceConstant.TAB_XCGL_YYCQTSWH_WHMX+" b "
    					+ "	where a.bindid=b.bindid "
    					+ "	and a.ZXNF='"+ZXNF+"' "
    					+ "	and b.ZXYF='"+ZXYF+"' "
    					+ " and SYGSBM='"+SYGSBM+"'";
    			float CQTS = 0;
    			try {
    				CQTS = Float.valueOf(DBSql.getString(sql,"CQTS"));
    			} catch (NumberFormatException e) {
    				CQTS = 0;
    			} catch (AWSDataAccessException e) {
    				CQTS = 0;
    			}
    			bo.set("CQTS",CQTS);
        		
        		//通过员工编号查询员工公司
        		sql = "select b.GSBM,b.GSMC,b.BM,b.MC from "+PerformanceConstant.TAB_JCXX_HRRYXXTB+" a left join"
        				+ "	"+PerformanceConstant.TAB_JCXX_GWXX+" b "
        				+ "	on a.SZGWPK=b.HRGWPK where RYBM='"+RYZH+"'";
        		YGZBGSBM = DBSql.getString(sql,"GSBM");
        		
        		
        		if(YGZBGSBM == null || "".equals(YGZBGSBM)){
        			//没有在维护表指定公司的员工
        			bo.set("XTTSXX","员工编号为"+RYZH+"的"+bo.getString("RYMC")+"员工没有维护所属公司和所属岗位信息，"
        					+ "员工岗位按"+SYGSMC+"公司所在岗位"+bo.getString("GWMC")+"计算工资，"
        							+ "此员工系统统计工作天数为："+RSJCQTS+"天，应得基本奖金为："+bo.getString("RJSJE")+"元,"
        									+ "此数据系统默认参与奖金计算，请注意");
        			bo.set("SFTSJL","1");
        			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        			
        		}else if(YGZBGSBM != null && !YGZBGSBM.equals(SYGSBM)){
        			//如果公司不是同一个公司需要提示系统信息到子表XTTSXX列
        			bo.set("XTTSXX","员工编号为"+RYZH+"的"+bo.getString("RYMC")+"员工所属公司为"+DBSql.getString(sql,"GSMC")+","
        					+ "员工岗位按"+SYGSMC+"公司所在岗位"+bo.getString("GWMC")+"计算工资，"
        					+ "此员工系统统计工作天数为："+RSJCQTS+"天，应得基本奖金为："+bo.getString("RJSJE")+"元,"
							+ "此数据系统默认参与奖金计算，请注意");
        			bo.set("SYGSBM",YGZBGSBM);//设置人员所属公司编码
        			bo.set("SYGSMC",DBSql.getString(sql,"GSMC"));//设置人员所属公司名称
        			bo.set("SFTSJL","1");
        			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        		} else{//为本公司员工
        			//判断是操作岗还是非操作岗，如果人员为非操作岗直接创建子表记录
        			YGZBGWBM = DBSql.getString(sql,"BM");
        			rclGWBM = bo.getString("GWBM");//得到日产量表中维护的员工岗位
        			//判断与岗位表记录员工岗位是否相同
        			if(YGZBGWBM == null || "".equals(YGZBGWBM)){
        				sql = "select YLGZ from "+ProductPlanConstant.TAB_JCXX_HRYFRYXCTB+" where RYBM='"+RYZH+"' and NF='"+ZXNF+"' and YF='"+ZXYF+"'";
                		gdzdz = DBSql.getString(sql,"YLGZ");
        	      		if(gdzdz==null || "".equals(gdzdz)){
        	      			 
        	      			bo.set("YLGZ","0");
        	      		}else{
        	      			bo.set("YLGZ",gdzdz);
        	      		}
        				//没有在维护表指定岗位的员工
            			bo.set("XTTSXX","员工编号为"+RYZH+"的"+bo.getString("RYMC")+"员工没有维护所属公司和所属岗位信息，员工基本工资为："+gdzdz+"元"
            					+ "员工岗位按"+SYGSMC+"公司所在岗位"+bo.getString("GWMC")+"计算工资，"
            							+ "此员工系统统计工作天数为："+RSJCQTS+"天，应得基本奖金为："+bo.getString("RJSJE")+"元,"
            									+ "此数据系统默认参与奖金计算，请注意");
            			
            			bo.set("SFTSJL","1");
            			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        			}else if(YGZBGWBM != null && !YGZBGWBM.equals(rclGWBM) ){
        				//说明为本公司临时调岗人员（包含非操作岗转操作岗，操作岗为其他操作岗来此工作），需要创建一条记录
        				bo.set("XTTSXX","员工编号为"+RYZH+"的"+bo.getString("RYMC")+"员工，其本岗位系统记录为："+DBSql.getString(sql,"MC")+"岗，"
            					+ "员工岗位按"+SYGSMC+"公司所在岗位"+bo.getString("GWMC")+"计算工资，"
            							+ "此员工系统统计工作天数为："+RSJCQTS+"天，应得基本奖金为："+bo.getString("RJSJE")+"元,"
            									+ "此数据系统默认参与奖金计算，请注意");
        				bo.set("SFTSJL","1");
            			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        				
        			}else {
        				//岗位名称能对应上，查看操作岗定额台账信息，查看工序是否能对应上，不对应说明为临时调用工序员工或岗位对应工序本月内发生变更
        				//其他为正常操作岗人员，可按操作岗非操作岗创建记录
        				sql = "select count(*) cnt from "+PerformanceConstant.TAB_JXGL_CZGZJSTZ+" where GXBM='"+bo.getString("GXBM")+"' and GWBM='"+rclGWBM+"'";
        				int cnt = Integer.valueOf(DBSql.getString(sql,"cnt"));
        				if(cnt==0){
        					bo.set("XTTSXX","员工编号为"+RYZH+"的"+bo.getString("RYMC")+"员工，其本岗位对应工序"+bo.getString("GXMC")+"系统没有记录对应关系，"
                					+ "员工岗位按本公司所在岗位为"+bo.getString("GWMC")+"下所在工序为"+bo.getString("GXMC")+"计算工资，"
                							+ "此员工系统统计工作天数为："+RSJCQTS+"天，应得基本奖金为："+bo.getString("RJSJE")+"元,"
                									+ "此数据系统默认参与奖金计算，请注意");
        					bo.set("SFTSJL","1");
                			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        				}
        			}
        			
        		}
        		
        	}
        	
        	
        	//得到HR应领工资同步信息数据,创建系统维护配置信息的人员配置信息：此为本公司下没有调岗正常人员信息，包好操作岗和非操作岗员工
        	if(ZXYF.equals("12")){
        		LZXNF = String.valueOf(Integer.valueOf(ZXNF)+1);
                LZXYF = "01";
        	}else if(Integer.valueOf(ZXYF)<9){
        		LZXNF = ZXNF;
        		LZXYF = "0"+String.valueOf(Integer.valueOf(ZXYF)+1);
        	}else{
        		LZXNF = ZXNF;
        		LZXYF = String.valueOf(Integer.valueOf(ZXYF)+1);
        	}
        	sql = "select a.RYBM,a.XM,a.SZGWPK,a.GWMC,a.GWBM,a.NF,a.YF,a.YLGZ from "
        			+ "	"+ProductPlanConstant.TAB_JCXX_HRYFRYXCTB+" a,"
        			+ "	"+PerformanceConstant.TAB_JCXX_GWXX+" b "
        			+ "	where a.SZGWPK=b.HRGWPK and b.GSBM='"+SYGSBM+"' and a.NF='"+LZXNF+"' and a.YF='"+LZXYF+"'";
        	lhgwData = BOUtil.queryEncapsulationData(sql);
        	
        	for(int i=0;i<lhgwData.size();i++) {
        		bo = lhgwData.get(i);
        		//通过员工编号及流程变量查看是否为配置的变量下人员
        		sql = "select count(*) CNT from orguser a,orgrole b where a.ROLEID=b.id and b.ROLENAME='"+BCYJSJS+"' and a.USERID='"+bo.getString("RYBM")+"'";
        		int cnt = Integer.valueOf(DBSql.getString(sql,"CNT"));
        		if(cnt > 0){//如果为流程变量配置人员则不创建数据
        			continue;
        		}
        		bo.set("RYZH",bo.getString("RYBM"));
        		bo.set("RYXM",bo.getString("XM"));
        		bo.set("SFTSJL","2");
        		//查询公司月份下出勤数量
            	sql = "select CQTS from "+PerformanceConstant.TAB_XCGL_YYCQTSWH_M+" a,"+PerformanceConstant.TAB_XCGL_YYCQTSWH_WHMX+" b "
    					+ "	where a.bindid=b.bindid "
    					+ "	and a.ZXNF='"+ZXNF+"' "
    					+ "	and b.ZXYF='"+ZXYF+"' "
    					+ " and SYGSBM='"+SYGSBM+"'";
    			float CQTS = 0;
    			try {
    				CQTS = Float.valueOf(DBSql.getString(sql,"CQTS"));
    			} catch (NumberFormatException e) {
    				CQTS = 0;
    			} catch (AWSDataAccessException e) {
    				CQTS = 0;
    			}
    			bo.set("CQTS",CQTS);
//    			bo.set("SJCQTS",CQTS);
        		
        		sql = "select GXBM,GXMC from BO_DY_JXGL_CZGZJSTZ where GWBM='"+bo.getString("GWBM")+"'";
        		tzsdData = BOUtil.queryEncapsulationData(sql);
        		if(tzsdData.size()==0){//操作岗没有配置说明为非操作岗人员,直接创建数据
        			
        			SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        		}else{
        			//操作岗按照工序创建人员数据
        			for(int j=0;j<tzsdData.size();j++) {
        				bo.set("GXBM",tzsdData.get(j).getString("GXBM"));
        				bo.set("GXMC",tzsdData.get(j).getString("GXMC"));
        				//如果人员为多条工序，只填写第一条数据应领工资
        				if(j>0){
        					bo.remove("YLGZ");
        				}
        				sql = "select sum(RSJCQTS) RSJCQTS from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_M+" a,"
        	        			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX+" b,"
        	        			+ "	"+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" c"
        	        			+ "	where a.bindid=b.bindid and c.bindid=b.id and a.NF='"+ZXNF+"' and a.YF='"+ZXYF+"' and a.GSBM='"+SYGSBM+"' "
        	        			+ "	and a.isend=1 and c.RYZH="+bo.getString("RYBM")+" and b.GXBM='"+tzsdData.get(j).getString("GXBM")+"' and b.GWBM='"+bo.getString("GWBM")+"' "
        	        			+ " group by a.nf,a.yf,a.GSBM,a.GSMC,b.GXMC,b.GXBM,b.GWMC,b.GWBM, c.RYMC,c.RYZH";
        				float RSJCQTS = 0;
        				
//        				System.out.println("==================="+bo.getString("XM")+"=================="+bo.getString("RSJCQTS"));
//        				System.out.println("==================="+bo.getString("XM")+"=================="+sql);
            			try {
            				glzxbl = DBSql.getString(sql,"RSJCQTS");
            				if(glzxbl==null || "".equals(glzxbl)){
            					RSJCQTS = 0;
            				}else{
            					RSJCQTS = Float.valueOf(glzxbl);
            				}
            			} catch (NumberFormatException e) {
            				RSJCQTS = 0;
            			} catch (AWSDataAccessException e) {
            				RSJCQTS = 0;
            			}
                		bo.set("SJCQTS",RSJCQTS);//实际出勤天数
        				SDK.getBOAPI().create(ProductPlanConstant.TAB_XCGL_JJLR_S, bo, ctx.getProcessInstance(), user);
        				//去除主表系统字段
        				bo.remove("UPDATEUSER");
        				bo.remove("UPDATEDATE");
        				bo.remove("CREATEUSER");
        				bo.remove("ISEND");
        				bo.remove("ORGID");
        				bo.remove("CREATEDATE");
        				bo.remove("ID");//资产入库ID
        				bo.remove("BINDID");//资产入库bindID
        				bo.remove("PROCESSDEFID");
        				

        			}
        		}
        	}
   
        	
        	
        	ro = ResponseObject.newOkResponse();
        	ro.msg("保存成功了");// 返回给服务器的消息
            ro.put("key1", "value1").put("key2", "value2");// 放入前端需要的参数
        	
        }else{
        	 // 错误时
            ro = ResponseObject.newErrResponse();
            ro.msg("配置人员导入还是系统读取HR系统参数信息错误，请联系管理员重新配置");
        	r = false;
        }
        
        
        
    
        
        boId = null;
        //表单ID
        formId = null;
        //BO表名
        boName = null;
        
        user = null;
        //获得流程实例id
        bindid = null;
        glzxbl = null;
        
        //获得表单填写公司编码、执行年份、执行月份
        sql = null;

        SYGSBM = null;//得到公司编码
        ZXNF = null;//得到年份
        ZXYF = null;//得到月份
        //通过查找HR系统得到所有公司下的人员及基本工资
        SFLJHR = null;//获得流程全局变量确认手动导入还是系统自动查找HR
        formData = null;//存储数据
        lhgwData = null;
        bo = null;
        RYZH = null;//员工编号
        YGZBGSBM = null;//岗位维护表中公司编码
        rclGWBM = null;//日产量中的操作岗位
        YGZBGWBM = null;//岗位维护表中岗位编码
        tzsdData = null;
        gdzdz = null;
        LZXNF = null;
        LZXYF = null;
       
          
        return ro.toString();
       
    }
}