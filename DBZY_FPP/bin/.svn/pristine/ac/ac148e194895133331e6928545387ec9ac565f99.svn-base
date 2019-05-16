package com.nepharm.apps.fpp.is.k3.dao;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import kingdee.bos.webapi.client.K3CloudApiClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.ppm.constant.ProductPlanConstant;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;
import com.nepharm.apps.fpp.util.BOUtil;

public class EncapsulationProductionOrderJson {

	
	
	public String formatContent(String bindid){

		StringBuffer sContent = new StringBuffer();
		
		//获得主表数据
	     BO formData = SDK.getBOAPI().getByProcess(ProductPlanConstant.TAB_SCJH_QDSCJH_M, bindid);
	      
		
		

	     sContent.append("{");
	     sContent.append("\"Creator\": \"\",");
	     sContent.append("\"NeedUpDateFields\": [");
	         
	     sContent.append("],");
	     sContent.append("\"NeedReturnFields\": [");
	         
	     sContent.append("],");
	     sContent.append("\"IsDeleteEntry\": \"True\",");
	     sContent.append("\"SubSystemId\": \"\",");
	     sContent.append("\"IsVerifyBaseDataField\": \"false\",");
	     sContent.append("\"IsEntryBatchFill\": \"True\",");
	     sContent.append("\"Model\": {");
	         sContent.append("\"FID\": 0,");
	         sContent.append("\"FBillType\": {");
	             sContent.append("\"FNumber\": \"SCDD03_SYS\"");
	         sContent.append("},");
	         System.out.println(formData.getString("SQRQ"));
	         sContent.append("\"FDate\": \""+formData.getString("SQRQ")+"\",");//提交单据日期
	         sContent.append("\"FPrdOrgId\": {");
	             sContent.append("\"FNumber\": \"20101\"");
	         sContent.append("},");
	         sContent.append("\"FOwnerTypeId\": \"BD_OwnerOrg\",");
	         sContent.append("\"FIsRework\": false,");
	         sContent.append("\"FBusinessType\": \"1\",");
	         sContent.append("\"FTrustteed\": false,");
	         sContent.append("\"FIsEntrust\": false,");
	         sContent.append("\"FDescription\": \""+formData.getString("BZ")+"\","); 
	         sContent.append("\"FOrderYear\": \""+formData.getString("JHNF")+"\","); 
	         sContent.append("\"FOrderMonth\": \""+formData.getString("JHYF")+"\",");
	         sContent.append("\"FPPBOMType\": \"1\",");
	         sContent.append("\"FIssueMtrl\": false,");
	         sContent.append("\"FTreeEntity\": [");
	         List<BO> gridData = SDK.getBOAPI().query(ProductPlanConstant.TAB_SCJH_QDSCJH_S, true).addQuery("bindid =", bindid).list();
	         BO bo = null;
	         for(int i=0; i<gridData.size();i++){
		    	bo = gridData.get(i);
	             sContent.append("{");
	                 sContent.append("\"FProductType\": \"1\",");
	                 sContent.append("\"FMaterialId\": {");
	                     sContent.append("\"FNumber\": \""+bo.getString("CPBM")+"\"");//产品编码
	                 sContent.append("},");
	                 sContent.append("\"FWorkShopID\": {");
	                     sContent.append("\"FNumber\": \""+bo.getString("K3GSBM")+"\"");//公司编码
	                 sContent.append("},");
	                 sContent.append("\"FUnitId\": {");
	                 sContent.append("\"FNumber\": \""+bo.getString("CPDW")+"\"");//单位编码
	                 sContent.append("},");
	                 sContent.append("\"FQty\": "+bo.getString("QRCL")+",");//数量
	                 sContent.append("\"FYieldQty\": 222.0,");
	                 sContent.append("\"FPlanStartDate\": \""+bo.getString("JHKGSJ")+"\",");//计划开工时间
	                 sContent.append("\"FPlanFinishDate\": \""+bo.getString("JHWGSJ")+"\",");//计划完工时间
	                 sContent.append("\"FRequestOrgId\": {");
	                     sContent.append("\"FNumber\": \"20101\"");//组织编码 默认
	                 sContent.append("},");
	                 sContent.append("\"FBomId\": {");
	                     sContent.append("\"FNumber\": \"0514000001_V1.0\"");
	                 sContent.append("},");
	                 sContent.append("\"FISBACKFLUSH\": true,");
	                 sContent.append("\"FStockInOrgId\": {");
	                     sContent.append("\"FNumber\": \"20101\"");
	                 sContent.append("},");
	                 sContent.append("\"FBaseYieldQty\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FReqType\": \"1\",");
	                 sContent.append("\"FStockInUlRatio\": 10.0,");
	                 sContent.append("\"FInStockOwnerTypeId\": \"BD_OwnerOrg\",");
	                 sContent.append("\"FBaseStockInLimitH\":"+bo.getString("QRCL")+",");
	                 sContent.append("\"FInStockOwnerId\": {");
	                     sContent.append("\"FNumber\": \"20101\"");
	                 sContent.append("},");
	                 sContent.append("\"FStockInLlRatio\": 10.0,");
	                 sContent.append("\"FCheckProduct\": true,");
	                 sContent.append("\"FBaseStockInLimitL\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FBaseUnitQty\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FBaseUnitId\": {");
	                     sContent.append("\"FNumber\": \"he\"");
	                 sContent.append("},");
	                 sContent.append("\"FStockId\": {");
	                     sContent.append("\"FNumber\": \"2010323\"");
	                 sContent.append("},");
	                 sContent.append("\"FStockLocId\": {");
	                     sContent.append("\"FSTOCKLOCID__FF100509\": {");
	                         sContent.append("\"FNumber\": \"25\"");
	                     sContent.append("}");
	                 sContent.append("},");
	                 sContent.append("\"FStockInLimitH\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FStockInLimitL\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FCostRate\": 100.0,");
	                 sContent.append("\"FCreateType\": \"1\",");
	                 sContent.append("\"FYieldRate\": 100.0,");
	                 sContent.append("\"FGroup\": 1,");
	                 sContent.append("\"FNoStockInQty\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FBaseNoStockInQty\": "+bo.getString("QRCL")+",");
	                 sContent.append("\"FRowId\": \""+SDK.getRuleAPI().executeAtScript("@uuid")+"\",");
	                 sContent.append("\"FPickMtrlStatus\": \"1\",");
	                 sContent.append("\"FMOChangeFlag\": false");
	             if(gridData.size()>1 && (i+1)!=gridData.size()){//判断数据条数大于一条并且不为最后一条
	            	 sContent.append("},");
	             }else{
	            	 sContent.append("}");
	             }
	             
	         }
	         sContent.append("]");
	     
	     sContent.append("}");
	 sContent.append("}");

		System.out.println(sContent.toString());
		formData = null;
//		gridData = null;
//		bo = null;
		return sContent.toString();
	}
}
