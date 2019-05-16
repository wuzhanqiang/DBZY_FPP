package com.nepharm5.apps.fpp.formula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.actionsoft.bpms.commons.at.AbstExpression;
import com.actionsoft.bpms.commons.at.ExpressionContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.AWSExpressionException;

public class getZjlnameByFqr extends AbstExpression {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public getZjlnameByFqr(ExpressionContext atContext, String expressionValue) {
		super(atContext, expressionValue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(String expression) throws AWSExpressionException {
	    String deptid = getParameter(expression, 2).trim();
	    
	    StringBuffer result = new StringBuffer();
	    String[] r = deptid.split("/");
	    String sql = "";
	    if ((r.length >= 2) && 
	      (!r[1].equals("")) && (r[1] != null)) {
	      sql = "select orgu.username as USERNAME from orguser orgu ,orgdepartment ts where orgu.ismanager=1 and ts.id=orgu.departmentid and ts.id ='" + r[1] + "'  union " + " select orgu.username as USERNAME from orgusermap om," + "orguser orgu,orgdepartment ts " + "where  orgu.id = om.mapid  and om.departmentid = ts.id and om.ismanager=1" + " and ts.id ='" + r[1] + "'";
	    }
	    if (!sql.equals("")) {
	      try
	      {
	    	  conn = DBSql.open();
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery(sql);
	          if (this.rs != null) {
	              while (this.rs.next()) {
	                  result.append(this.rs.getString("USERNAME")).append(" ");
	              }
	          }
	      }
	      catch (SQLException e)
	      {
	    	  DBSql.close(this.conn, this.stmt, this.rs);
	          e.printStackTrace(System.err);
	      }
	      finally
	      {
	          DBSql.close(this.conn, this.stmt, this.rs);
	      }
	    }
	    if (result.toString().equals("")) {
	        result.append("未找到");
	    }
	    return result.toString();
	}

}
