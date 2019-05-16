package com.nepharm.apps.fpp.portal.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ActionWeb;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.UtilString;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.portal.dao.TaskDao;

/**
 * 门户内容，相关业务
 * @author lizhen
 *
 */
public class PortalWeb extends ActionWeb{
	public PortalWeb(UserContext me){
		super(me);
	}
	
	public String getWorkBoxPage(){
		String device=this.getContext().getDeviceType();
		//System.out.println("当前登陆者："+device);
		String style="";
		if("pc".equals(device)){
			style="";
		}else{
			style="display:none;";
		}
		Map<String, Object> macroLibraries = new LinkedHashMap<String, Object>();
		macroLibraries.put("ispc",device);
		macroLibraries.put("sid",this.getContext().getSessionId());
		macroLibraries.put("showDiv",style);
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, "portal-workbox.htm", macroLibraries);
	}
	
	/**
	 * 获取已办待办数据
	 * @return
	 */
	public String getWorkBoxData(){
		JSONObject result = new JSONObject();
		TaskDao dao = new TaskDao();
		JSONArray list1=dao.getDBTaskData(this.getContext().getUID());
		JSONArray list2=dao.getYBTaskData(this.getContext().getUID());
		String num=dao.getTaskNumber(this.getContext().getUID());
		result.put("num",num);
		result.put("dblist",list1);
		result.put("yblist",list2);
		return result.toString();
	}
	/*
	public String getAssPage() {
		Map<String, Object> macroLibraries = new LinkedHashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, "portal-assistant.htm", macroLibraries);
	}
	
	*/
	public String getMainFramePage() {
		Map<String, Object> macroLibraries = new LinkedHashMap<String, Object>();
		macroLibraries.put("sid",this.getContext().getSessionId());
		return HtmlPageTemplate.merge(ConfigConstant.APP_ID, "portal-index-iframe.htm", macroLibraries);
	}
	
	/**
	 * 门户-导航搜索功能
	 * 根据菜单名字，匹配菜单列表信息
	 * 返回 text—value形式的json对象
	 * @param nav
	 * @return
	 */
	public String getNavLiveSearchInfoList(String nav){

		JSONArray list = new JSONArray();//返回菜单、url
		//获取当前登陆者的所有菜单
		JSONArray navTree = SDK.getPortalAPI().getNavTree(this.getContext());
		// 第一层级遍历 
        for(int fri=0; fri<navTree.size(); fri++){
        	JSONObject system = navTree.getJSONObject(fri);
        	String systemName = system.getString("name");
        	String systemUrl = system.getString("url");
        	String systemTarget = system.getString("target");
        	// 判断当前菜单是否为包含所搜项
        	searchNav(list,systemName,systemUrl,systemTarget,nav);
        	//获取当前菜单下的第二级菜单集合
        	JSONArray directories = system.getJSONArray("directory");
        	if(directories.isEmpty()){
        		//没有下级目录,跳出本次循环,进入下了主菜单
        		continue ;
        	}
        	//遍历二级
        	for(int sec=0; sec<directories.size(); sec++){
        		JSONObject directory = directories.getJSONObject(sec);
        		String directoryName = directory.getString("name");
        		String directoryUrl = directory.getString("url");
    			String directoryTarget = directory.getString("target");

    			searchNav(list, directoryName, directoryUrl, directoryTarget, nav);
    			JSONArray functions = directory.getJSONArray("function");
    			if(functions.isEmpty()){
            		//没有下级目录,跳出本次循环,进入下个二菜单
            		continue ;
            	}
    			//遍历三级
    			for(int thr=0; thr<functions.size(); thr++){
					JSONObject function = functions.getJSONObject(thr);
					String functionUrl = function.getString("url");
					String functionTarget = function.getString("target");
					String functionName = function.getString("name");
					searchNav(list, functionName, functionUrl, functionTarget, nav);
    			}
    			
        	}
        	
        }
		return list.toString();
	}

	/**
	 * 判断是否含搜索内容
	 * @param list 
	 * @param systemName 菜单名称
	 * @param systemUrl  地址
	 * @param systemTarget 打开目标
	 * @param nav 搜索项
	 */
	private void searchNav(JSONArray list, String name, String url,
			String target,String nav) {
		if(nav==null||"".equals(nav)){
			return ;//检索项无效 直接return
		}
		if(UtilString.isEmpty(url) ||"/".equals(url)){
			return ;//当前菜单没有有效的地址 直接return
		}
		//菜单名称中包含 检索 值
		if(name.indexOf(nav)>-1){
			String text=name;
			if(name.length()>15){//名字超过9个字符，进行截取。
				name=name.substring(0, 15);
				text=name+"...";
        	}
			String value=url+"@@@@"+target;
			JSONObject data = new JSONObject();
			data.put("value", value);
			data.put("text", text);
			list.add(data);
		}
	}
}
