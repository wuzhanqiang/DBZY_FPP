package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;
import com.nepharm5.apps.fpp.nepg.util.StringUtil;

public class managerbydepartmentid extends AbstExpression {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public managerbydepartmentid(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String execute(String expression) throws AWSExpressionException {
		String departmentId = getParameter(expression, 1);
	    
	    String managerUserId = DBSql.getString("select USERID from ORGUSER where DEPARTMENTID = '"+departmentId+"' and ISMANAGER = '1'", "USERID");
	    if (StringUtil.isEmpty(managerUserId)) {
	      return "未找到";
	    }
	    return managerUserId;
	}
}
