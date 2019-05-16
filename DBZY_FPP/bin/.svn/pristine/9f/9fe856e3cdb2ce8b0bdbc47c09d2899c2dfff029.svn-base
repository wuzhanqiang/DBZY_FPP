/*
 * 根据部门全路径,取所属单位
 * 创建人：李鑫
 * 创建日期：2018-05-25
 */
package com.nepharm5.apps.fpp.formula;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.exception.AWSExpressionException;

public class GetSsdwByQlj extends AbstExpression {

	public GetSsdwByQlj(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
		String deptid = getParameter(expression, 1).trim();// 获取某个部门

		StringBuffer result = new StringBuffer();
		String[] r = deptid.split("/");// 层级结构为管理本部/部室名称/二级部室名称/ 取二级部室的id
		// 根据单位模糊 查询
		if (r.length >= 3) {
			if (!r[2].equals("") && r[2] != null) {
				result.append(r[2]);
			}
		}
		return result.toString();
	}

}
