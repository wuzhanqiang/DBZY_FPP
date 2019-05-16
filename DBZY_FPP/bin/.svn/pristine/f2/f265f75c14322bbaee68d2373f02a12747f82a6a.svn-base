package com.nepharm5.apps.fpp.nepg.rule;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.exception.AWSExpressionException;
import com.nepharm5.apps.fpp.nepg.util.OrgUtil;

public class managerwithrolenamebydepartmentid extends AbstExpression {

	public managerwithrolenamebydepartmentid(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String departmentId = getParameter(expression, 1);//部门id
		String roleName = getParameter(expression, 2);//角色名称
		String result = OrgUtil.getInstance().getManagerWithRoleNameByDepartmentId(departmentId, roleName);
		return result;
	}

}
