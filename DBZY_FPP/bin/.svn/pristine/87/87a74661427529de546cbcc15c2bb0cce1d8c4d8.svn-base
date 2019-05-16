package com.nepharm.apps.fpp.biz.pem.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.biz.pem.bean.KPIParamBean;
import com.nepharm.apps.fpp.biz.pem.constant.PerformanceConstant;
import com.nepharm.apps.fpp.biz.pem.dao.KPICommonDao;
import com.nepharm.apps.fpp.util.MathUtil;
import com.nepharm.apps.fpp.util.NumberUtil;

/**
 * KPI相关工具
 * @author lizhen
 *
 */
public class KPICommonUtil {
	
	/**
	 * KPI操作方法汇总
	 * 1)计算分值
	 * 2)更新数据
	 * @param boId
	 * @param fs
	 * @param funcData
	 */
	public static void result(String boId,double fs,Map<String, Double> funcData){
		double value=getKPICalculationResult(boId, fs);
		updateKPIData(boId, funcData, value);
	}
	/**
	 * 通过 权重、难易系数等 计算最终的结果
	 * @param boId
	 * @param score
	 * @return
	 */
	private static double getKPICalculationResult(String boId,double fs){
		double score=0;
		int multiple=0;
		String ysgs="";//"单条指标分数=(基础分+(得分-目标分值)/考核纬度*(增/减项分值))*权重系数*难以系数";//(得分-目标分值)/考核纬度的结果四舍五入取整
		KPICommonDao dao = new KPICommonDao();
		//获取本条KPI的运算标准规则
		Map<String,Double> standard=dao.getKPIStandard(boId);
		double goal=standard.get("goal");//基础目标结果值
		double basic=standard.get("basic");//基础目标结果值对应的分值
		double unit=standard.get("unit");//考核纬度-单位
		double up=standard.get("up");//单位下增项分值
		double down=standard.get("down");//单位下减项分值
		double weight=standard.get("weight");//权重
		double difficulty=standard.get("difficulty");//难易系数
		//Difference value 差值
		double dValue = fs-goal;
		
		if(dValue<0&&unit<=0){
			//如果差值小于0并且没有设置单位颗粒度，直接返回0
			return 0;
		}else if((dValue>0&&unit<=0)||dValue==0){
			//如果差值大于0并且没有设置单位颗粒度，直接返回basic
			//如果差值=0，直接返回basic
			score= basic*weight*difficulty;
			ysgs="(无考核维度)"+basic+"*"+weight+"*"+difficulty;
		}else{
			try {
				//求差值与单位对的倍数
				System.out.println(NumberUtil.doubleFormat((dValue/unit),0));
				multiple=(int)(Double.parseDouble(NumberUtil.doubleFormat((dValue/unit), 0)));
			} catch (NumberFormatException e) {
				multiple=0;
				System.out.println("KPICommonUtil.getKPICalculationResult()->将String转int失败:"+e);
			}
			double coefficient=0;//系数
			if(dValue>0){
				coefficient=up;
			}else{
				coefficient=down;
			}
			score=(basic+multiple*coefficient)*weight*difficulty;	
			ysgs="("+basic+"+"+"("+fs+"-"+goal+")/"+unit+"*"+coefficient+")*"+weight+"*"+difficulty;
		}
		ysgs=ysgs+"="+score;
		String sql = "update "+PerformanceConstant.TAB_JXGL_JXKH_KPI +" set  ZSMB='"+ysgs+"'  where id='"+boId+"'";
		DBSql.update(sql);
		return Double.parseDouble(NumberUtil.doubleFormat(score, 2));
	}
	
	
	/**
	 * 更新一条KPI数据的分数、模板信息
	 * @param boId  KPI考核ID
	 * @param funcData 参数数据
	 * @param score 分数
	 */
	private static  void updateKPIData(String boId,Map<String, Double> funcData,double score){
		KPICommonDao dao = new KPICommonDao();
		//更新分数数据
		dao.updateKPIScore(boId, score);
		
		/*
		String template=dao.getKPITemplate(boId);
		template=replaceMark(template, funcData);
		//更新模板数据，更新的内容为null || "" 时 跳过更新 
		if(null==template||"".equals(template)){
			return ;
		}
		dao.updateKPITemplate(boId, template);
		*/
	}
	/**
	 * 根据变量名、标签，将模板的数据替换上去（只适用于KPI）
	 * @param template
	 * @param funcData
	 * @return
	 */
	private static String replaceMark(String template,Map<String, Double> funcData){
		if(null==template||"".equals(template)){
			return null;
		}
		
		for (Iterator i = funcData.keySet().iterator(); i.hasNext();) {  
			String key =(String) i.next();//参数名
			double value=funcData.get(key);//参数值
			String tag="<#"+key+"#>";
			template=template.replace(tag, value+"");
		}
		//template=template.replace("<#result#>", score+"");//计算结果
		return template;
	}
	
	
	/**
	 * 根据参数变量，找出条件，执行sql，查出数据
	 * @param bean
	 * @return
	 */
	public static double parse(String kpiNo,String boId,String key,String userId){
		KPICommonDao dao = new KPICommonDao();
		String zq=DBSql.getString("select KHZQ from "+PerformanceConstant.TAB_KPI_ZBK_M+" where KPIBM='"+kpiNo+"'","KHZQ");
		String where=dao.getWhereSQL(kpiNo, key,userId);
		String source=dao.getSourceSQL(kpiNo, key,zq);
		String sql=source+where;
		System.out.println("拼接的SQL："+sql);
		String num=DBSql.getString(sql,"NUM");
		if(num==null||"".equals(num)){
			return 0;
		}
		return Double.parseDouble(num);
	}
	public static double markResult(String id,String gs, Map<String, Double> funcData) {
		for(Iterator it=funcData.keySet().iterator();it.hasNext();){
			String key=(String)it.next();
			double value=funcData.get(key);
			
			gs=gs.replace(key, value+"");
		}
		KPICommonDao dao = new KPICommonDao();
		dao.updateKPITemplate(id, gs+"");
		double jg=0;
		try {
			jg= MathUtil.runExpression(gs);
		} catch (ScriptException e) {
			jg= 0;
		}
		//如果是不是number或无限大的数，默认给0
		if(Double.isNaN(jg)||Double.isInfinite(jg)){
			jg=0;
		}
		dao.updateKPITemplate(id, gs+"="+jg);
		return jg;
	}
	
	
	/**
	 * 判断是否是超时任务
	 * @param boId
	 * @return
	 */
	public static boolean isOverTime(String boId){
		BO bo=SDK.getBOAPI().get(PerformanceConstant.TAB_JXGL_JXKH_KPI, boId);
		String zt=(String)bo.get("ZT");
		if("2".equals(zt)){
			String fsStr=(String)bo.get("JSFZ");
			String qzStr=(String)bo.get("KHQZ");
			String nyStr=(String)bo.get("NYXS");
			double fs=0;
			double qz=0;
			double ny=0;
			try {
				fs=Double.parseDouble(fsStr);
			} catch (NumberFormatException e) {
				fs=0;
			}
			try {
				qz=Double.parseDouble(qzStr);
			} catch (NumberFormatException e) {
				qz=0;
			}
			try {
				ny=Double.parseDouble(nyStr);
			} catch (NumberFormatException e) {
				ny=0;
			}
			double zf=(fs*qz*ny);
			String ysgs=fs+"*"+qz+"*"+ny+"="+zf;
			String sql = "update "+PerformanceConstant.TAB_JXGL_JXKH_KPI +" set FS='"+zf+"',ZSXX='本条指标评分人超时，自动满分',ZSMB='"+ysgs+"'  where id='"+boId+"'";
			DBSql.update(sql);
			return true;
		}
		
		return false;
	}
}
