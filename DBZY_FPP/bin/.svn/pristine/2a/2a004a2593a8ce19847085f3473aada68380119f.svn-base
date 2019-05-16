package com.nepharm.apps.fpp.portal.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//import com.actionsoft.apps.skins.metro.constant.MetroSkinsConstant;
import com.actionsoft.bpms.commons.htmlframework.HtmlPageTemplate;
import com.actionsoft.bpms.commons.mvc.view.ResponseObject;
import com.actionsoft.bpms.commons.portal.skins.AbstPortalSkins;
import com.actionsoft.bpms.commons.portal.skins.PortalSkinsInterface;
import com.actionsoft.bpms.org.model.UserModel;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.server.fs.impl.PhotoProcessor;
import com.actionsoft.bpms.util.UtilString;
import com.actionsoft.exception.AWSException;
import com.actionsoft.sdk.local.SDK;
import com.actionsoft.sdk.local.api.AppAPI;
import com.actionsoft.sdk.local.api.PortalAPI;
import com.nepharm.apps.fpp.constant.ConfigConstant;

/**
 * 103Demo 自定义开发皮肤  插件
 * @author lizhen
 *
 */
public class PortalSkins extends AbstPortalSkins implements PortalSkinsInterface{

	@Override
	public String getHomePage(UserContext me) {
		StringBuilder sb = new StringBuilder();
		//NavTest navTest = new NavTest();
		//navTest.get2(me);
        //导航
        sb.append("<ul class='nav_div width_wrap'>");
        // location.reload();
        sb.append("<li id='d0' class='nav_li'><div><a id='sy' style='cursor:pointer;' onclick='index();'>首页</a></div></li>");
        JSONArray navTree = SDK.getPortalAPI().getNavTree(me);
        int max=navTree.size();
        // 第一层级遍历 
        for(int i=0; i<navTree.size(); i++){
        	JSONObject system = navTree.getJSONObject(i);
        	String systemName = system.getString("name");
        	String systemUrl = system.getString("url");
        	String systemTarget = system.getString("target");
        	if (UtilString.isEmpty(systemUrl) || systemUrl.equals("/")) {
        		systemUrl = "";
        	}
        	if(systemName.length()>4){
        		systemName=systemName.substring(0, 4);
        	}
        	//<li class="lin_l">|</li>
        	//sb.append("<li class='lin_l'>|</li>");
        	sb.append("<li id='d"+(i+1)+"' class='nav_li'><div class='div_tit'><a style='cursor:pointer' onclick='navUrl(this.name,this.target);' name='"+systemUrl+"' target='"+systemTarget+"'>"+systemName+"</a></div>");
        	JSONArray directories = system.getJSONArray("directory");
        	
        	if(!directories.isEmpty()){
        		sb.append("<ul class='select'>");
        		//第二层级遍历 
        		for(int ii=0; ii<directories.size(); ii++){
        			JSONObject directory = directories.getJSONObject(ii);
        			String directoryName = directory.getString("name");
        			String directoryUrl = directory.getString("url");
        			String directoryTarget = directory.getString("target");
        			if (UtilString.isEmpty(directoryUrl) || directoryUrl.equals("/")) {
        				directoryUrl = "";
        			}
        			if(directoryName.length()>9){
        				directoryName=directoryName.substring(0, 9);
		        	}
        			//sb.append("<li class='nav_li2'><div><a style='cursor:pointer' onclick='navUrl(this.name);' name='"+directoryUrl+"'>"+directoryName+"</a></div>");
        			sb.append("<li class='nav_li2'> <a style='cursor:pointer' onclick='navUrl(this.name,this.target);' name='"+directoryUrl+"' target='"+directoryTarget+"'>"+directoryName+"</a>");
        			JSONArray functions = directory.getJSONArray("function");
        			
        			if(!functions.isEmpty()){
        				sb.append("<ul class='select2'>");
        				// 第三层级遍历 
        				for(int iii=0; iii<functions.size(); iii++){
        					JSONObject function = functions.getJSONObject(iii);
        					String functionName = function.getString("name");
        					if(functionName.length()>9){
        						functionName=functionName.substring(0, 9);
        		        	}
        					String functionUrl = function.getString("url");
        					String functionTarget = function.getString("target");
        					if (UtilString.isEmpty(functionUrl) || functionUrl.equals("/")) {
        						functionUrl = "";
        					}
        					sb.append("<li><div><a style='cursor:pointer' onclick='navUrl(this.name,this.target);' name='"+functionUrl+"'  target='"+functionTarget+"'>"+functionName+"</a></div></li>");
        				}
        				sb.append("</li>");
        				sb.append("</ul>");
        			}
        			
        		}
        		
        	    sb.append("</li>");
        		sb.append("</ul>");
        	}
        	
        	
        }
        
        //sb.append("<li id='list_temp' style='float:left;line-height:52px;text-align:center' ><div style='color:#ff'>0</div></li>");
//        sb.append("<li id='list_btn' class='nav_li_btn' width='100px'><div><a id='last_btn' style='cursor:pointer' onclick='last()'>"
//        		+ "<!--img src='../apps/com.nepharm.apps.fpp/img/portal/back_32.png' width=16 height=16/-->|上一个"+"</a>"
//        		+ "|<a id='next_btn' style='cursor:pointer' onclick='next()'>"
//        		+ "下一个<!--img src='../apps/com.nepharm.apps.fpp/img/portal/forward_32.png' width=16 height=16/-->"+"|</a></div></li>");
        
        sb.append("<li id='list_btn' class='nav_li_btn' width='100px'><div><a id='last_btn' style='cursor:pointer;color:white;' onclick='last()'>"
        		+ "|&nbsp;&nbsp;<img src='../apps/com.nepharm.apps.fpp/img/portal/back_32.png' title='上一个' width=16 height=16 />"+"</a>"
        		+ "&nbsp;&nbsp;<a id='next_btn' style='cursor:pointer;color:white;' onclick='next()'>"
        		+ "<img src='../apps/com.nepharm.apps.fpp/img/portal/forward_32.png' title='下一个' width=16 height=16 />"+"&nbsp;&nbsp;|</a>"
        		+"&nbsp;&nbsp;"
        		+ "<a id='updateUserInfoBtn2' style='cursor:pointer;color:white;'>"
        		+"<img src='../apps/com.nepharm.apps.fpp/img/portal/js.png' title='导航检索' width=24 height=24 />"
        		+"</a>"
        		+ "</div></li>");

       
//        sb.append("<li id='list_btn' class='nav_li_btn'>"
//        		+ "<div>"
//        		+ "<input type='text' id='CHESHI' name='CHESHI' class='awsui-combobox'  style='width:100px !important;'/>"
//        		+ "</div></li>");
        sb.append("</ul>");
        
        // PortalAPI portalApi = SDK.getPortalAPI();
		//String curUserId = me.getUID();
		//ResponseObject result = ResponseObject.newOkResponse();
		//JSONObject userInfo = JSONObject.fromObject(portalApi.getUserInfo(me, curUserId));
		
		// user info
	    String userInfo = me.getUserName();
        // template merge
	    PortalAPI portalApi = SDK.getPortalAPI();
        Map<String, Object> macroLibraries = new LinkedHashMap<String, Object>();
        macroLibraries.put("userInfo", userInfo);
        macroLibraries.put("userPhoto", portalApi.getUserPhoto(me, me.getUID()));
        macroLibraries.put("nav-list", sb.toString());
        macroLibraries.put("nav-list-num", max);
        macroLibraries.put("sid", me.getSessionId());
        UserModel userModel = me.getUserModel();
      //  AppAPI appApi = SDK.getAppAPI();
      //  String isPromptUploadPortraitStr = appApi.getProperty("com.actionsoft.apps.skins.metro",  "isPromptUploadPortrait");
        macroLibraries.put("uid", userModel.getUID());
        macroLibraries.put("uniqueId", userModel.getUniqueId());
       // System.out.println(PhotoProcessor.getTmpPhotoUrl(me, me.getUID()));
       // PhotoProcessor.getTmpPhotoUrl(me, uid);
        macroLibraries.put("userPhotoTmp", PhotoProcessor.getTmpPhotoUrl(me, me.getUID()));
        return HtmlPageTemplate.merge(ConfigConstant.APP_ID, "portal-index.htm", macroLibraries);
    }

    /**
     * 退出提示页面
     */
    public String getLogoutPage(UserContext me) {
        PortalAPI portalApi = SDK.getPortalAPI();
        // 关闭session
        boolean isClosed = portalApi.closeSession(me.getSessionId());
        if (!isClosed) {
            throw new AWSException("Session关闭异常");
        }
        // 调转到你的登出页面
        return "logout sucess!";
    }

//    PersonalSetting
    
    
}
