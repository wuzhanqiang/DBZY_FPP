package com.nepharm.apps.fpp.plugin;

import com.actionsoft.bpms.bpmn.engine.model.def.ProcessDefinition;
import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 查找个人信息-岗位名称、编码、公司名称、编码
 * @author lizhen
 *
 */
public class FindUserInfoExpression extends AbstExpression  {

	public FindUserInfoExpression(final ExpressionContext atContext, String expressionValue) {
        super(atContext, expressionValue);
    }

	private static String TAB_SXGL_JBJC="BO_DY_SXGL_JBJC_S";//时限管理 » 岗位级别奖惩设置子表
	private static String SQL = "select JE,ZD1||','||ZD2 as ZD from "+TAB_SXGL_JBJC+" ";
	@Override
	public String execute(String expression) throws AWSExpressionException {
		//ProcessDefinition process=SDK.getRepositoryAPI().getProcessDefinition("obj_d02da2def9b6437f8d1ef31e7512904a");
		//System.out.println("流程名称->"+process.getName());
		String uid = null;
		String zd = null;//92 ：24小时惩罚金额,93：72小时惩罚金额
		String cf = null;//是否返回 处罚制度 0:不返回 1:返回处罚制度
        boolean isInt=false;//判断zd是不是数字类型数据
		try {
			uid = getParameter(expression, 1);//当人账号
		} catch (Exception e) {
			uid=null;
		}
		try {
			zd = getParameter(expression, 2);//字段名
		} catch (Exception e) {
			zd=null;
		}
		try {
			cf = getParameter(expression, 3);//是否返回制度
		} catch (Exception e) {
			cf=null;
		}
		if(uid==null||"".equals(uid)){
			uid=SDK.getRuleAPI().executeAtScript("@uid");
		}
		if(zd==null||"".equals(zd)){
			zd="GSBM";
		}else{
			try {
				int val=Integer.parseInt(zd);
				isInt=true;
			} catch (NumberFormatException e) {
				isInt=false;
			}
			
		}
		if(cf==null||"".equals(cf)){
			cf="0";
		}
		
		if(isInt){//"92".equals(zd)||"93".equals(zd)
			//岗位级别
			String gwjb = DBSql.getString("select GWJB from "+ConfigConstant.VIEW_RYXX+" where userid='"+uid+"' order by GSBM,GWBM asc");
			//是否返回 处罚制度
			if("1".equals(cf))
				return getLevelInfo(zd,gwjb);
			return getLevelAmount(zd,gwjb);
		}

		//GWJB岗位级别，JBMC编码
		String value="";//返回值
		try {
			value = DBSql.getString("select "+zd+" from "+ConfigConstant.VIEW_RYXX+" where userid='"+uid+"' order by GSBM,GWBM asc");
		} catch (Exception e) {
			value="";
		}
		return value;
	}

	/**
	 * 获取岗位级别、奖惩级别对应下的惩罚金额
	 * @param level 奖惩级别
	 * @param gwjb 岗位级别
	 * @return
	 */
	private String getLevelAmount(String level,String gwjb){
		
		String sql=SQL+" where GWJB='"+gwjb+"' and CSLX='"+level+"' ";
		String je=DBSql.getString(sql,"JE");//奖惩金额
		if(je==null||"".equals(je)){
			//默认找不到的 返回0元。
			je= "0";
		}
		return je;
	}
	/**
	 * 获取岗位级别、奖惩级别对应下制度
	 * @param level 奖惩级别
	 * @param gwjb 岗位级别
	 * @return
	 */
	private String getLevelInfo(String level,String gwjb){
		String sql=SQL+" where GWJB='"+gwjb+"' and CSLX='"+level+"' ";
		String zd=DBSql.getString(sql,"ZD");//制度
		return zd;
	}
	//select JE,ZD1||','||ZD2||','||ZD3 as ZD from BO_DY_SXGL_JBJC_S where GWJB='ZJ0299' and CSLX='92'
}
