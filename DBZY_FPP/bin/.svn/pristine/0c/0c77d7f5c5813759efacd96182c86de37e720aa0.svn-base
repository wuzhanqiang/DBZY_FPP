/*
 * 	创建人：时彬
 *	修改人：李鑫
 *	修改日期：2018-07-11
 */
package com.nepharm5.apps.fpp.nepgSanqi.biz.gtgl;

import java.util.Hashtable;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.bpmn.engine.core.delegate.ProcessExecutionContext;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListener;
import com.actionsoft.bpms.bpmn.engine.listener.ExecuteListenerInterface;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.itextpdf.text.pdf.PRAcroForm;

public class GtglNdjhwTAfter extends ExecuteListener implements
		ExecuteListenerInterface {

	private UserContext uc;

	public GtglNdjhwTAfter() {
		setDescription("年度计划外流程,年度计划外流程数据插入年度计划内流程子表中");
		setProvider("zz");
		setVersion("V1.0");
	}

	@Override
	public void execute(ProcessExecutionContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//年度计划外bindid
		String bindid = ctx.getProcessInstance().getId();
		String taskid = ctx.getTaskInstance().getId();
		//年度计划导入bindid
		String parentbindid = ctx.getProcessInstance().getParentProcessInstId();
		String parenttaskid = ctx.getProcessInstance().getParentTaskInstId();
		//取年度计划外流程审核菜单值
		boolean js = SDK.getTaskAPI().isChoiceActionMenu(taskid, "接收");
		//取年度计划外主表记录
		BO record = SDK.getBOAPI().getByProcess("BO_DY_GTGL_NDJHW", bindid);
		if(js==true){
			if (record != null && !record.isNew()) {
				//取子流程主表BO表ID
				String idsql="select ID from BO_DY_GTGL_NDJHW where bindid= '"+bindid+"'";
				String boId=DBSql.getString(idsql, "ID");
				//取年度计划外流程BO表信息
				String no =record.get("NO")==null?"":record.get("NO").toString();//流水号
				String ndjhwdw = record.get("NDJHWDW")==null?"":record.get("NDJHWDW").toString();//单位
				String ndjhwcpxhf = record.get("NDJHWCPXHF")==null?"":record.get("NDJHWCPXHF").toString();//产品线划分
				String ndjhwxmmc = record.get("NDJHWXMMC")==null?"":record.get("NDJHWXMMC").toString();//项目名称
				String ndjhwxmbh = record.get("NDJHWXMBH")==null?"":record.get("NDJHWXMBH").toString();//项目编号
				String ndjhwxmlb = record.get("NDJHWXMLB")==null?"":record.get("NDJHWXMLB").toString();//项目类别
				String ndjhwxmfzer = record.get("NDJHWXMFZR")==null?"":record.get("NDJHWXMFZR").toString();//项目负责人
				String ndjhwtzhbl = record.get("NDJHWTZHBL")==null?"":record.get("NDJHWTZHBL").toString();//投资回报率
				String ndjhwtzhsq =record.get("NDJHWTZHSQ")==null?"": record.get("NDJHWTZHSQ").toString();//投资回收期
				String ndjhwxmztz = record.get("NDJHWXMZTZ")==null?"":record.get("NDJHWXMZTZ").toString();//项目总投资
				String ndjhwtjtz = record.get("NDJHWTJTZ")==null?"":record.get("NDJHWTJTZ").toString();//土建投资
				String ndjhwsbtz =record.get("NDJHWSBTZ")==null?"": record.get("NDJHWSBTZ").toString();//设备投资
				String ndjhwaztz = record.get("NDJHWAZTZ")==null?"":record.get("NDJHWAZTZ").toString();//安装投资
				String ndjhwqttz = record.get("NDJHWQTTZ")==null?"":record.get("NDJHWQTTZ").toString();//其他投资
				String ndjhwldzj = record.get("NDJHWLDZJ")==null?"":record.get("NDJHWLDZJ").toString();//流动资金
				String ndjhwqyzy = record.get("NDJHWQYZY")==null?"":record.get("NDJHWQYZY").toString();//企业自有	
				String ndjhwyhdk = record.get("NDJHWYHDK")==null?"":record.get("NDJHWYHDK").toString();//银行贷款
				String ndjhwzfbt = record.get("NDJHWZFBT")==null?"":record.get("NDJHWZFBT").toString();//政府补贴
				String ndjhwqtly =record.get("NDJHWQTLY")==null?"":record.get("NDJHWQTLY").toString();//其他来源
				String ndjhwjhgqks = record.get("NDJHWJHGQKS")==null?"":record.get("NDJHWJHGQKS").toString();//计划工期（开始）
				String ndjhwjhgqjs = record.get("NDJHWJHGQJS")==null?"":record.get("NDJHWJHGQJS").toString();//计划公司（结束）
				String ndjhwzynr = record.get("NDJHWZYNR")==null?"":record.get("NDJHWZYNR").toString();//主要内容
				String ndjhwsfddbzz = record.get("NDJHWXYSFDDBZZ")==null?"":record.get("NDJHWXYSFDDBZZ").toString();//是否达到标准值
				String ndjhwsfcszjxy = record.get("NDJHWSFCSZJXY")==null?"":record.get("NDJHWSFCSZJXY").toString();//是否产生直接效益
				String ndjhwsflgxm = record.get("NDJHWSFLGXM")==null?"":record.get("NDJHWSFLGXM").toString();//是否零购项目
				String ndjhwsfyndjhxf = record.get("NDJHWSFYNDJHXF")==null?"":record.get("NDJHWSFYNDJHXF").toString();//是否与年度计划相符
				
				//将取出的值插入到hashtable里,注意:附件为空
				BO result = new BO();
				result.set("NDJHWNO", no);
				result.set("NDJHNDW", ndjhwdw);
				result.set("NDJHNCPXHF", ndjhwcpxhf);
				result.set("NDJHNXMMC", ndjhwxmmc);
				result.set("NDJHNXMBH", ndjhwxmbh);
				result.set("NDJHNXMLB", ndjhwxmlb);
				result.set("NDJHNXMFZR", ndjhwxmfzer);
				result.set("NDJHNTZHBL", ndjhwtzhbl);
				result.set("NDJHNTZHSQ", ndjhwtzhsq);
				result.set("NDJHNZTZ", ndjhwxmztz);
				result.set("NDJHNTJTZ", ndjhwtjtz);
				result.set("NDJHNSBTZ", ndjhwsbtz);
				result.set("NDJHNAZTZ", ndjhwaztz);
				result.set("NDJHNQTTZ", ndjhwqttz);
				result.set("NDJHNLDZJ", ndjhwldzj);
				result.set("NDJHNQYZY", ndjhwqyzy);
				result.set("NDJHNYHDK", ndjhwyhdk);
				result.set("NDJHNZFBT", ndjhwzfbt);
				result.set("NDJHNQTLY", ndjhwqtly);
				result.set("NDJHNJHGQKS", ndjhwjhgqks);
				result.set("NDJHNJHGQJS", ndjhwjhgqjs);
				result.set("NDJHNZYNR", ndjhwzynr);
				result.set("NDJHNXYSFDDBZZ", ndjhwsfddbzz);
				result.set("NDJHNXMSFCSZJXY", ndjhwsfcszjxy);
				result.set("NDJHNSFLG", ndjhwsflgxm);
				result.set("NDJHNSFYNDJHXF", ndjhwsfyndjhxf);
				result.set("NDJHWID", boId);
				//下面这个附件加不加都行
				//result.put("FJ", "");//注意,附件插入空值.
	
				if (result != null && !result.equals("")) {
					//年度计划外主表相关数据插入到年度计划内子表,t为插入数据的BO表的ID
//					int t = SDK.getBOAPI().create("BO_DY_GTGL_NDJHN_S", result, parentbindid, uc.getUID());
					int t = SDK.getBOAPI().createDataBO("BO_DY_GTGL_NDJHN_S", result, uc);
//					String selectSql = "select * from BO_DY_GTGL_NDJHN_S where bindid = '"+parentbindid+"' and createuser = '"+uc.getUID()+"'";
//					String newId = DBSql.getString(selectSql,"ID");
//					//申请部门附件
//					SDK.getBOAPI().copyFileTo(boId, "NDJHWSQBMFJ", newId, "BO_DY_GTGL_NDJHN_S", "NDJHNSQBMFJ", parentbindid, parenttaskid);
//					//专业评审组会议纪要
//					SDK.getBOAPI().copyFileTo(boId, "NDJHWZYPSZHYJY", newId, "BO_DY_GTGL_NDJHN_S", "NDJHNZYPSZHYJY", parentbindid, parenttaskid);
//					//班子会会议纪要
//					SDK.getBOAPI().copyFileTo(boId, "NDJHWBZHHYJY", newId, "BO_DY_GTGL_NDJHN_S", "NDJHNBZHHYJY", parentbindid, parenttaskid);
					
				}
				
			}
		}
	}

}
					
					/*try {
						
						//附件
						byte[] b;
						//子流程主表附件字段UUID,在bo表里找
						String ndjhwzypszhyjyUUID = "febdec291b8c3473185ce083c0e01d76";
						//主流程子表附件字段UUID,在bo表里找
						String ndjhnzypszhyjyUUID = "febdec291b8a73a20f636faeffcd4e8b";
						//将附件名字存入数组(附件可以为多个)
						String[] fileNames = BOInstanceAPI.getInstance().getFileNamesByFiled(boId, ndjhwzypszhyjyUUID);
						if(fileNames!=null && fileNames.length>0){
						    for(int i=0;i<fileNames.length;i++){
							    //循环数组取出附件名
							    String fileName=fileNames[i];
							    //控制台打印出附件名
							    System.out.println("===="+fileName);
							    //子流程主表下载附件,存入字节数组
								b=BOInstanceAPI.getInstance().downloadFileByFiled(boId, ndjhwzypszhyjyUUID, fileName);
								//主流程子表上传附件,此方法无返回值.
								BOInstanceAPI.getInstance().upFileByFiled(t, b, ndjhnzypszhyjyUUID, fileName);
						    
						    }
						}
						
						byte[] c;
						//子流程主表附件字段UUID,在bo表里找
						String ndjhwbzhhyjyUUID = "febdec291b8c816708299dba6f50b92d";
						//主流程子表附件字段UUID,在bo表里找
						String ndjhnbzhhyjyUUID = "febdec291b8ad34d48c6f582243ffcf7";
						//将附件名字存入数组(附件可以为多个)
						String[] fileNames1 = BOInstanceAPI.getInstance().getFileNamesByFiled(boId, ndjhwbzhhyjyUUID);
						if(fileNames1!=null && fileNames1.length>0){
						    for(int i=0;i<fileNames1.length;i++){
						    	//循环数组取出附件名
						    	String fileName1=fileNames1[i];
						      	//控制台打印出附件名
						      	System.out.println("===="+fileName1);
						      	//子流程主表下载附件,存入字节数组
								c=BOInstanceAPI.getInstance().downloadFileByFiled(boId, ndjhwbzhhyjyUUID, fileName1);
								//主流程子表上传附件,此方法无返回值.
								BOInstanceAPI.getInstance().upFileByFiled(t, c, ndjhnbzhhyjyUUID, fileName1);
						    
						    }
						}
							    
					
						byte[] e;
						//子流程主表附件字段UUID,在bo表里找
						String ndjhwsqmfjUUID = "febdec2918f24d2c7dbebf5296eec211";
						//主流程子表附件字段UUID,在bo表里找
						String ndjhnsqbmfjUUID = "febdec291b893089274a71f53c798c9b";
						//将附件名字存入数组(附件可以为多个)
						String[] fileNames2 = BOInstanceAPI.getInstance().getFileNamesByFiled(boId, ndjhwsqmfjUUID);
						if(fileNames2!=null && fileNames2.length>0){
						    for(int i=0;i<fileNames2.length;i++){
						    	//循环数组取出附件名
						    	String fileName2=fileNames2[i];
						    	//控制台打印出附件名
						    	System.out.println("===="+fileName2);
						    	//子流程主表下载附件,存入字节数组
						    	e=BOInstanceAPI.getInstance().downloadFileByFiled(boId, ndjhwsqmfjUUID, fileName2);
						    	//主流程子表上传附件,此方法无返回值.
						    	BOInstanceAPI.getInstance().upFileByFiled(t, e, ndjhnsqbmfjUUID, fileName2);
						  
						    }
						}
					}catch (AWSSDKException e) {
						e.printStackTrace(System.err);
					}*/

