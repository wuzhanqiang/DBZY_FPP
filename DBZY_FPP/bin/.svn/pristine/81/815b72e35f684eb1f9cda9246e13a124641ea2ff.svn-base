package com.nepharm.apps.fpp.biz.pem.event;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSDataAccessException;
import com.actionsoft.exception.BPMNError;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.util.BOUtil;

public class ChildTableSaveCreateFieldChildTableData extends InterruptListener {

    private static final boolean BO = false;

	public String getDescription() {
        return "子表保存前创建字段子表数据";
    }

    public String getProvider() {
        return "Actionsoft";
    }

    public String getVersion() {
        return "1.0";
    }

    public boolean execute(ProcessExecutionContext param) throws Exception {
//        //参数获取
//        //注意：除特殊说明外，下列参数仅在该事件中场景有效
//        //记录ID
//        String boId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
//        //表单ID
//        String formId = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_FORMID);
//        //BO表名
//        String boName = param.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
    	
    	 //获得流程实例id
	      String bindid = param.getProcessInstance().getId();
        
        UserContext user = param.getUserContext();
//        System.out.println(user.getUID());
        String userid = user.getUID();
        String zhsl = null;
        String sql = null;
        List<BO> lhgwData = null;

        // 保存前的表单数据，注意：该参数针对不同场景获取内容会有所不同
        // 主表中的保存场景获取主表数据；普通子表页面的保存场景获取的是该条子表的数据；如果需要获得其他数据请使用BOQueryAPI获取
//        BO formData = (BO) param.getParameter(ListenerConst.FORM_EVENT_PARAM_FORMDATA);
        BO formData = SDK.getBOAPI().getByProcess(PerformanceConstant.TAB_JXGL_CZGRICLWH_M, bindid);
        if(formData == null){
        	return true;
        }
        CreateDailyOutputData createDailyOutputData = new CreateDailyOutputData();
        String msg = null;

        // 获取Ajax子表的数据，由于Ajax子表的数据会同主表保存动作一起触发，需要使用该参数获取
        // 在Ajax子表的工具栏上的“保存”动作和主表的“保存”动作中有效
        // 注意：该数据并不是从数据库中获取，获取的数据取决于表单上对Ajax子表新增的数据与修改的数据的和
        List<BO> gridData = null;
        List<BO> bdData = (List) param.getParameter(ListenerConst.FORM_EVENT_PARAM_GRIDDATA);
        List<BO> sjkData = SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX).addQuery("bindid =", bindid).list();;
        if(bdData!=null){//参考录入时
        	return true;
        }else if(sjkData!=null){
        	gridData = sjkData;//保存数据时
        }else{
        	return true;
        }
        		
        
        //遍历子表的数据
        for (BO rowData : gridData) {
            //下面一行示例代码，可以获取Ajax子表的每行记录的新建状态
//            boolean rowDataIsCreate = Boolean.parseBoolean(rowData.getString("isCreate"));//注意：isCreate并不是BO的一个字段，该字段是有接口上层赋值的
        	
        	
        	//判断是否已维护过数据，如果维护过不需要创建数据
        	sql = "select count(*) CNT from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" where bindid ='"+rowData.getString("ID")+"'";
        	int cnt = DBSql.getInt(sql,"CNT");
        	boolean flag = false;
        	if(cnt>0){
        		sql = "select FTRCL  from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" where bindid ='"+rowData.getString("ID")+"'";
        		lhgwData = BOUtil.queryEncapsulationData(sql);
        		for(BO lhzbData : lhgwData){//当前字段子表数据是否都为0，如果为0 需要删除重新加载
        			
        			zhsl = lhzbData.getString("FTRCL");
                	float FTRCL = 0;
                	if(zhsl!=null &&!"".equals(zhsl)){
                		FTRCL = Float.valueOf(zhsl);
                		
                	}
//                	System.out.println("=========================FTRCL========================="+FTRCL);
                	if(FTRCL!=0){
            			flag = true;
                		break;
                	}
                	
        		}
        		if(flag){
        			continue;
        		}
    			sql = "delete from "+PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX+" where bindid ='"+rowData.getString("ID")+"'";
        		DBSql.update(sql);
        		
        		
        	}
        	zhsl = rowData.getString("RCL");
        	float RCLL = 0;
        	if(zhsl!=null &&!"".equals(zhsl)){
        		RCLL = Float.valueOf(zhsl);
        	}
//        	System.out.println("=========================================="+RCLL);
//        	System.out.println("=========================================="+formData.getString("GSBM"));
        	//查询公司月份下出勤数量
        	sql = "select CQTS from "+PerformanceConstant.TAB_XCGL_YYCQTSWH_M+" a,"+PerformanceConstant.TAB_XCGL_YYCQTSWH_WHMX+" b "
					+ "	where a.bindid=b.bindid "
					+ "	and a.ZXNF='"+formData.getString("NF")+"' "
					+ "	and b.ZXYF='"+formData.getString("YF")+"' "
					+ " and SYGSBM='"+formData.getString("GSBM")+"'";
			float CQTS = 0;
			try {
				CQTS = Float.valueOf(DBSql.getString(sql,"CQTS"));
			} catch (NumberFormatException e) {
				CQTS = 0;
			} catch (AWSDataAccessException e) {
				CQTS = 0;
			}
			int GZRS = 0;
			try {
				GZRS = Integer.valueOf(rowData.getString("GZRS"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				GZRS = 0;
			}
        	msg = createDailyOutputData.createFieldSubtableBO(rowData.getString("ID"),PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX,userid,rowData.getString("GWBM"),rowData.getString("GWMC"),formData.getString("GSBM"),RCLL,rowData.getString("DEDJ"),CQTS,GZRS);
        	if(msg!=null && !"".equals(msg)){
        		System.out.println(msg);
        	}
        }

//        // 该记录是否新建的状态，由于机制调整，BO对象中的ID是不为空的，不能通过ID判断记录是否处于新建状态还是修改状态
//        //注意：该参数仅适用保存前（后）事件中，该参数仅能获取主表的是否新建状态
//        boolean isCreate = param.getParameterOfBoolean(ListenerConst.FORM_EVENT_PARAM_ISCREATE);
//
//        //该记录是否通过复制功能创建，用于普通子表的复制时判断该状态
//        //注意：该参数仅适用保存前（后）事件中
//        boolean isCopy = param.getParameterOfBoolean(ListenerConst.FORM_EVENT_PARAM_ISCOPY);
        user = null;
        
        createDailyOutputData = null;
        gridData = null;
        msg = null;
        sql = null;
        sjkData=null;
        bdData=null;
        zhsl = null;
        lhgwData = null;
        formData = null;
        user = null;
        userid = null;
        return true;//返回true可以正常保存表单
        //return false;//可阻止表单保存
        //或者直接抛出BPMNErr异常
        //throw new BPMNError("0313","数据不完整，不能进行保存操作");
    }
}