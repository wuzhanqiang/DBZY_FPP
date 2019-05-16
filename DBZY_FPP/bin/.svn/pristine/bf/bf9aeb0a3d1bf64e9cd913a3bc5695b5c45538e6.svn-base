package com.nepharm.apps.fpp.biz.kms.event;

import com.actionsoft.bpms.dw.design.event.DataWindowFormatSQLEventInterface;
import com.actionsoft.bpms.dw.exec.component.DataView;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
/**
 * 根据阅读授权格式化SQL语句
 * @author sydq
 *
 */
public class KMSDWFormatSqlEvent implements DataWindowFormatSQLEventInterface {

	public KMSDWFormatSqlEvent() {
	
	}

	@Override
	public String formatSQL(UserContext me, DataView view, String sql) {
		String departmentPathId = me.getDepartmentModel().getPathIdOfCache();
		String userId = me.getUID();
		String GWBM = SDK.getRuleAPI().executeAtScript("@getUserInfo("+userId+",GWBM)");
		String GWSQSql = "(instr('"+departmentPathId+"', BMBH)>0 and instr(GWYDBM, '"+GWBM+"')>0)";//岗位阅读授权SQL
		String CRSql = "CREATEUSER = '"+userId+"'";//创建人可见
		String BMSQSql = "(ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', YDBMBH)>0))";
		String RYSQSql = "instr(RYIDYDSQ, '"+userId+"')>0";//人员阅读授权SQL
		String GWSql = "instr(GWBM, '"+GWBM+"')>0";//岗位下载授权
		String BMSql = "(ID in (select BOID from BO_DY_KMS_ZSBMSQ where instr('"+departmentPathId+"', BMBH)>0))";
		String RYSql = "instr(RYIDSQ, '"+userId+"')>0";
		String whereSql = "("+GWSQSql+" or "+CRSql+" or "+BMSQSql+" or "+RYSQSql+" or "+GWSql+" or "+BMSql+" or "+RYSql+")";
		sql = sql.replace("1=1", whereSql);
		System.out.println(sql);
		return sql;
	}

}
