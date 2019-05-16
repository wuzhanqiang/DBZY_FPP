package com.nepharm5.apps.fpp.nepg.rule;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

public class ManagerByDepartmentId extends AbstExpression {

	public ManagerByDepartmentId(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String departmentId = getParameter(expression, 1);//部门id
		
		String managerUserId = DBSql.getString("select USERID from ORGUSER where DEPARTMENTID = '"+departmentId+"' and ISMANAGER = '1'", "USERID");
		if(StringUtil.isEmpty(managerUserId)){
			return "未找到";	
		}else{
			return managerUserId;
		}
	}

}
