package com.nepharm.apps.fpp.biz.pem.event;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.bpmn.engine.listener.ValueListener;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.BOCopyAPI;
import com.actionsoft.sdk.local.api.BOQueryAPI;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.util.BOUtil;
/**
 * 日产量数据扩展按钮，根据数据字典中选择的历史数据，同步插入到当前表中
 * @author lizhen
 */
public class CopyDayProductDataBtn extends ValueListener{

	@Override
	public String execute(ProcessExecutionContext ctx) throws Exception {
		
		//参数获取
		UserContext userContext = ctx.getUserContext();
        //记录ID
        String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
        //BO表名
        String boName = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
        //获得流程实例id
        String bindId = ctx.getProcessInstance().getId();
        if(boName==null||!PerformanceConstant.TAB_JXGL_CZGRICLWH_M.equals(boName)){
        	 return ResponseObject.newErrResponse("数据源不匹配,不能执行复制操作。").toString();
        }
        BO mainData = SDK.getBOAPI().get(boName, boId);//日产量主表数据
        String  yBindId=(String)mainData.get("YBINDID");//获取主表的 yBindid（原数据实例）
        
        if(yBindId==null||"".equals(yBindId)){
        	return ResponseObject.newErrResponse("没有找到有效的数据源，请确认已选择复制的数据，或点击“保存”后在重新点击“复制数据”").toString();
        }
        
        //查找原表子表的所有数据 
        //产量子表数据
        BOQueryAPI query = SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX, true).bindId(yBindId);
        //产量子表数据 的list数据
        List<BO> list= query.list();
        try {
        	//判断list是不是有效得到数据
			if(list==null||list.size()==0||list.get(0).getId()==null){
				throw new RuntimeException("无数据！");//主动跑出异常
			}
		} catch (Exception e) {
			return ResponseObject.newOkResponse("执行成功,但源数据为空。").toString();
		}
        /*
         * 循环处理子表的BO数据，将数据进行清理，然后在基础上查找孙子数据
         */
        for(BO sub:list){
        	String subBindId=sub.getId();//字段子表的bindid
        	BOQueryAPI gSub=SDK.getBOAPI().query(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX, true).bindId(subBindId);
        	
        	sub=BOUtil.cleanBO(sub,userContext.getUID());//清理数据
        	List<BO> ll= new ArrayList<BO>();
        	ll.add(sub);
        	SDK.getBOAPI().create(
        			PerformanceConstant.TAB_JXGL_CZGRICLWH_CLMX,
        			ll,//"BO对象"
    				bindId, //流程实例对象
    				userContext.getUID());//创建者
        	String newSubBindId=sub.getId();//新数据的-字段子表的bindid
        	
        	//BOCopyAPI copyAPI = gSub.copyTo(PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX, newSubBindId);
            // 执行复制操作
            //copyAPI.exec(); 
        	List<BO> lll=gSub.list();
        	List<BO> gSubList= new ArrayList<BO>(); 
        	for(BO bo:lll){
        		bo=BOUtil.cleanBO(bo,userContext.getUID());//清理数据
        		gSubList.add(bo);
        	}
        	
        	SDK.getBOAPI().create(
        			PerformanceConstant.TAB_JXGL_CZGRICLWH_CLFPMX,
        			gSubList,//"BO对象"
        			newSubBindId, //流程实例对象
    				userContext.getUID());//创建者
        	
        }
        
        return ResponseObject.newOkResponse("执行成功").toString();
	}

}
