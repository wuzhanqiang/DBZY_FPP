package com.dbzy.zjxs.cfba;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Map;

import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListener;
import com.actionsoft.bpms.bpmn.engine.listener.InterruptListenerInterface;
import com.actionsoft.bpms.bpmn.engine.listener.ListenerConst;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.exception.BPMNError;

public class AutoDanJuHao extends InterruptListener implements InterruptListenerInterface {

	
	public boolean execute(ProcessExecutionContext ctx) {
		//获取流程实例Id
		String processInstId=ctx.getProcessInstance().getId();
		//获取操作的BO记录ID
		//String boId = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		//获取操作的BO对象名
		String maintable = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BONAME);
		//获取当前表单模型Id
		String mainid = ctx.getParameterOfString(ListenerConst.FORM_EVENT_PARAM_BOID);
		
		String taskInstanceId = ctx.getTaskInstance().getId();
		
		String uid = ctx.getUserContext().getUID();
	
		return this.createDanJuHao(ctx, uid, processInstId, mainid, taskInstanceId, maintable);
	}
	
	public boolean createDanJuHao(ProcessExecutionContext ctx, String uid, String processInstanceId, String mainid,String taskInstanceId,String maintable){
		if (maintable == null || "".equals(maintable)) {
			return true;
		}
		String geshi = null;
		String col = null;
		String prefix = null;
		try {
			Map<String, Object> t = ctx.getVariables();
			if (t == null) {
				throw new BPMNError("请添加流程变量编码格式和单据编号列名！","请添加流程变量编码格式和单据编号列名！");
			}
			geshi = (String) t.get("GESHI");
			if (geshi == null || geshi.equals("")) {
				geshi = "00000";
			}
			col = (String) t.get("COL");
			if (col == null || col.equals("")) {
				throw new BPMNError("请添加流程变量单据编号列名[COL]！","请添加流程变量单据编号列名[COL]！");
			}
			prefix = (String) t.get("PREFIX");
			if (prefix == null || prefix.equals("")) {
				throw new BPMNError("请添加流程变量单据编号前缀[PREFIX]！","请添加流程变量单据编号前缀[PREFIX]！");
			}
			String sql = "select " + col + " as CODE from " + maintable + " where id = '" + mainid + "'";
			String code = DBSql.getString(sql, "CODE");
			if (code != null && !code.trim().equals("")) {
				return true;
			}
		} catch (Exception e) {
			throw new BPMNError("error","系统异常！" + e.getMessage());
		}

		boolean isupdate = this.setDanJuHao(maintable, col, geshi, mainid, prefix);

		if (!isupdate) {
			throw new BPMNError("更新单据编号失败","更新单据编号失败！");
		}
		return true;
	}
    /*
     * 更新表中的编号信息
     */
	public boolean setDanJuHao(String tableName, String codeFieldName,
			String intdf, String id, String prefix) {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		
		int prefixlen = prefix.length();
		if (intdf == null || intdf.equals("")) {
			intdf = "00000";
		}
		int intlen = intdf.length();
		DecimalFormat df = new DecimalFormat(intdf);

		String sql = "select max(" + codeFieldName + ") as MAXCODE from " + tableName;
		String maxCode = DBSql.getString(sql, "MAXCODE");

		String danjuhao = null;

		if (maxCode == null || "".equals(maxCode)) {
			maxCode = prefix + currentYear + df.format(0);
		}
		String year = maxCode.substring(prefixlen + 0, prefixlen + 4);

		if ((currentYear+"").equals(year)) {
			danjuhao = prefix + currentYear + df.format((new Integer(maxCode.substring(prefixlen + 4, prefixlen + 4 + intlen)) + 1));
		} else {
			danjuhao = prefix + currentYear + df.format(1);
		}
		Connection conn = DBSql.open();
		try {
			DBSql.update(conn, "update " + tableName + " set " + codeFieldName + "='" + danjuhao + "' where id = '" + id + "'");
		} catch (Exception e) {
			return false;
		} finally {
			DBSql.close(conn, null, null);
		}
		return true;
	}

}
