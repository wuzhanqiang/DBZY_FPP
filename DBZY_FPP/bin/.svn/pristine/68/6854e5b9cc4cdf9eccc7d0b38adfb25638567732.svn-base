package com.nepharm5.apps.fpp.nepgSanqi.biz.gtgl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;

public class GtglBgRtclass extends ExecuteListener implements
		ExecuteListenerInterface {

	public UserContext uc;
	public Connection conn;
	public Statement stmt;
	
	public GtglBgRtclass() {
		// TODO Auto-generated constructor stub
		setProvider("zz");
		setVersion("V1.0");
		setDescription("项目变更后数据写入看板");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String bindId = ctx.getProcessInstance().getId();
		BO t = SDK.getBOAPI().getByProcess("BO_DY_GTGL_XMBG_P", bindId);
		List<BO> v = SDK.getBOAPI().query("BO_DY_GTGL_XMBG_S").bindId(bindId).list();
		String xmkid ="";
		String xmkbindid="";
		String updateSql = "";//更新项目管理库中的数据
		String id ="";
		String result = "";// 执行变更的sql
		int bgid=0;
		List sqlList= new ArrayList();
		//插入到主数据的变更信息表中
		if(t!=null && !t.isNew()){
			 id = t.get("ID")==null?"":t.get("ID").toString();
			xmkid=t.get("XMKID")==null?"":t.get("XMKID").toString();//项目库id
			xmkbindid = t.get("XMKBINDID")==null?"":t.get("XMKBINDID").toString(); //项目库bindid
			String bgtzhbl = t.get("BGTZHBL")==null?"":t.get("BGTZHBL").toString();//投资回报率
			String bgtzhsq = t.get("BGTZHSQ")==null?"":t.get("BGTZHSQ").toString();//投资回收期
			String bgxmztz = t.get("BGXMZTZ")==null?"":t.get("BGXMZTZ").toString();//项目总投资
			String bgtjtz = t.get("BGTJTZ")==null?"":t.get("BGTJTZ").toString();//土建投资
			String bgsbtz = t.get("BGSBTZ")==null?"":t.get("BGSBTZ").toString();//设备投资
			String bgaztz = t.get("BGAZTZ")==null?"":t.get("BGAZTZ").toString();//安装投资
			String bgqttz = t.get("BGQTTZ")==null?"":t.get("BGQTTZ").toString();//其他投资
			String bgldzj = t.get("BGLDZJ")==null?"":t.get("BGLDZJ").toString();//流动资金
			String bgqyzy = t.get("BGQYZY")==null?"":t.get("BGQYZY").toString();//企业自有
			String bgyhdk = t.get("BGYHDK")==null?"":t.get("BGYHDK").toString();//银行贷款
			String bgqtly = t.get("BGQTLY")==null?"":t.get("BGQTLY").toString();//其他来源
			String bgzfbt = t.get("BGZFBT")==null?"":t.get("BGZFBT").toString();//政府补贴
			String bgjhgqks= t.get("BGJHGQKS")==null?"":t.get("BGJHGQKS").toString();//计划工期（开始）
			String bgjhgqjs = t.get("BGJHGQJS")==null?"":t.get("BGJHGQJS").toString();//计划工期(结束)
			String yxmztz = t.get("QDXMZTZ")==null?"":t.get("QDXMZTZ").toString();//启动项目总投资
			String bgjhysrq = t.get("BGJHYSRQ")==null?"":t.get("BGJHYSRQ").toString();//变更验收日期
			String bgjhjsrq = t.get("BGJHJSRQ")==null?"":t.get("BGJHJSRQ").toString();//变更结算日期
			String bgjhjuesrq = t.get("BGJHJUESRQ")==null?"":t.get("BGJHJUESRQ").toString();//变更决算日期
			
			
			//拼接变更数据的sql
			result ="update BO_DY_GTGL_KB_P set id = "+xmkid;
			if(!bgtzhbl.equals(""))result = result+",qdtzhbl='"+bgtzhbl+"'";
			if(!bgtzhsq.equals(""))result = result+",qdtzhsq = '"+bgtzhsq+"'";
			if(!bgxmztz.equals(""))result = result+",qdxmztz='"+bgxmztz+"'";
			if(!bgtjtz.equals(""))result =result+",qdtjtz='"+bgtjtz+"'";
			if(!bgsbtz.equals(""))result = result+",qdsbtz='"+bgsbtz+"'";
			if(!bgaztz.equals(""))result = result+",qdaztz='"+bgaztz+"'";
			if(!bgqttz.equals(""))result = result+",qdqttz='"+bgqttz+"'";
			if(!bgldzj.equals(""))result = result+",qdldzj='"+bgldzj+"'";
			
			if(!bgqyzy.equals(""))result = result+",qdqyzy='"+bgqyzy+"'";			
			if(!bgyhdk.equals(""))result = result+",qdyhdk='"+bgyhdk+"'";
			if(!bgzfbt.equals(""))result = result+",qdzfbt='"+bgzfbt+"'";
			if(!bgqtly.equals(""))result = result+",qdqtly='"+bgqtly+"'";
			if(!bgjhgqks.equals(""))result = result+",qdjhgqks=to_date('"+bgjhgqks+"','yyyy-MM-dd')";
			if(!bgjhgqjs.equals(""))result = result+",qdjhgqjs=to_date('"+bgjhgqjs+"','yyyy-MM-dd')";
			if(!bgjhysrq.equals(""))result = result+",qdjhysrq=to_date('"+bgjhysrq+"','yyyy-MM-dd')";
			if(!bgjhjsrq.equals(""))result = result+",qdjhjsrq=to_date('"+bgjhjsrq+"','yyyy-MM-dd')";
			if(!bgjhjuesrq.equals(""))result = result+",qdjhjuesrq=to_date('"+bgjhjuesrq+"','yyyy-MM-dd')";
			
			result = result+" where id = '"+xmkid+"'";
			sqlList.add(result);
			//将 变更记录插入到项目库中
			SDK.getBOAPI().create("BO_DY_GTGL_KB_BG_S", t, xmkbindid, uc.getUID());
			String selectSql = "select * from BO_DY_GTGL_KB_BG_S where bindid = '"+xmkbindid+"' and createuser = '"+uc.getUID()+"'";
			String newId = DBSql.getString(selectSql,"ID");
			
			//没有BO_DY_GTGL_KB_BG_S
			updateSql = "update BO_DY_GTGL_KB_BG_S set bgbm='BO_DY_GTGL_XMBG_P',bgid='" +id+
					"', bglcuuid='fe4f296714b207aa347ea6f6838b4bcc',bgformid='fe4f2967148473167b9adc9de3afd4a4',yxmztz="
					+ yxmztz +",xmztz="+bgxmztz+
					" where id='"+newId+"'";
			sqlList.add(updateSql);
			
		}
		
		//将合同包信息插入到项目库中的合同包信息中
		if(v!=null && v.size()>0){
			try {
				SDK.getBOAPI().create("BO_DY_GTGL_KB_FB_S", v, xmkbindid, uc.getUID());
				List<BO> boDatas = SDK.getBOAPI().query("BO_DY_GTGL_KB_FB_S").bindId(xmkbindid).list();
				for (int i = 0; i < boDatas.size(); i++) {
					String sql = "update BO_GTGL_KB_FB_S set bz='项目变更的合同包信息',bgbm='BO_GTGL_XMBG_P'," +
							"bglcuuid='fe4f296714b207aa347ea6f6838b4bcc',bgid=" +bgid+
				             			", bgformid='fe4f2967148473167b9adc9de3afd4a4' where id = '"+boDatas.get(i).getId()+"'";
					sqlList.add(sql);
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
			} 
		}
		
		//执行修改
		conn = DBSql.open();
		try {
			stmt = conn.createStatement();
			for (int i = 0; i < sqlList.size(); i++) {
				stmt.addBatch(sqlList.get(i).toString());
			}
			int [] results = stmt.executeBatch();
			System.out.println(results);
		} catch (SQLException e) {
			DBSql.close(conn, stmt, null);
			e.printStackTrace(System.err);
		}finally{
			DBSql.close(conn, stmt, null);
		}
		
	}

}
