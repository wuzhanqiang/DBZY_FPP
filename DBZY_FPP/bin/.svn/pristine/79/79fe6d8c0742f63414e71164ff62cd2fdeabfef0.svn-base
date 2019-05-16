package com.nepharm.apps.fpp.is.k3.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.actionsoft.bpms.bo.engine.BO;
import com.actionsoft.bpms.server.UserContext;
import com.actionsoft.bpms.util.DBSql;
import com.actionsoft.sdk.local.SDK;
import com.nepharm.apps.fpp.constant.ConfigConstant;
import com.nepharm.apps.fpp.is.k3.constant.K3Constant;
import com.nepharm.apps.fpp.is.k3.constant.SynchronousK3Constant;
import com.nepharm.apps.fpp.is.k3.web.K3Web;

import kingdee.bos.webapi.client.K3CloudApiClient;
/**
 * 写入K3需要用到的连接及保存方法
 * @author zhangjh
 *
 */
public class K3Util {
//	static String dbId = "59e40e62519a98";//数据中心ID
//	static String userName = "Administrator";//用户名称
//	static String passWord = "Dbzy@2017";
//	static String K3url = "http://172.16.111.172/k3cloud/";

	
	/**
	 * 
	 * @param client K3客户端对象 
	 * @param sFormId K3单据对应的表单ID
	 * @param sContent 封装成JSON的K3需要数据
	 * @return
	 * @throws Exception
	 */
	public String save(K3CloudApiClient client, String sFormId,String sContent) throws Exception{
//		String sFormId = "PRD_MO";//K3表单id
		
		String result = client.execute("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId, sContent}, String.class);
		return result;
	}
	
	
	public K3CloudApiClient login(StringBuffer msg,String userid) throws Exception{
		String sql = "select DBID,USERNAME,PASSWORD,URL,DK from "+SynchronousK3Constant.TAB_JCXX_K3XRDZPZ_M+" ";
		String URL = DBSql.getString(sql,"URL");
		String DBID = DBSql.getString(sql,"DBID");
		
		String DK = DBSql.getString(sql,"DK");
		if(URL==null||"".equals(URL) || DBID==null||"".equals(DBID) || 
				DK==null||"".equals(DK)){
			System.out.println("需要配置K3数据写入配置维护信息!");
			
			msg.append("需要配置K3数据写入配置维护信息，请联系管理员配置!");
			return null;
		}
		
		sql = "select K3PASSWORD from "+K3Constant.TAB_JCXX_K3ACCOUNT+"  where userid='"+userid+"' ";
		String USERNAME = userid;
		String PASSWORD = DBSql.getString(sql,"K3PASSWORD");
		if(USERNAME==null||"".equals(USERNAME) || PASSWORD==null||"".equals(PASSWORD)){
			msg.append("需要配置K3用户名及密码，配置位置与修改密码位置一致!");
			return null;
		}
		K3CloudApiClient client = new K3CloudApiClient(URL);
		boolean tf = client.login(DBID, USERNAME, PASSWORD, Integer.valueOf(DK));
		if(tf){
			
			msg.append("登录成功!");
			return client;
		}else{
			
			msg.append("系统连接K3时出错，请确认是否在K3中修改过K3登陆用户名及密码，如果修改过请在本平台进行修改，修改位置与修改密码位置一致，如没有修改过请稍后重试，如长时间没有连接上请联系管理员!");
			return null;
		}
	}
	
	//获取单点登录K3地址串
	public static String getSsoUrl(String userName, boolean ipState) throws UnsupportedEncodingException{
		String url = "";
		List<BO> list = SDK.getBOAPI().query(SynchronousK3Constant.TAB_JCXX_K3XRDZPZ_M).list();
		if(list != null && !list.isEmpty()) {
			BO bo = list.get(0);
			String dbId = bo.getString("DBID");
			String appId = bo.getString("APPID");
			String appSecret = bo.getString("APPSECRET");
//			String dbId = "59bbbc7ffd3a38";
//			String appId = "AWSBPM";
//			String appSecret = "763945c50de74329a8a9198a6b463ff7";
			String K3url = "";
			if(ipState) {
				K3url = ConfigConstant.K3_IN;
//				K3url = "http://172.16.111.172";
			}else {
				K3url = ConfigConstant.K3_OUT;
			}
			String dk = bo.getString("DK");
			long currentTime=System.currentTimeMillis()/1000;
			String timestamp=Long.toString(currentTime);
			String[] strArray ={dbId, userName, appId, appSecret,timestamp};
			Arrays.sort(strArray);
			String combStr=null;
			for(int i=0;i<strArray.length;i++){
				if(combStr==null || combStr==""){
					combStr = strArray[i];
				}else{
					combStr= combStr+strArray[i];
				}
			}
			byte[] strByte = combStr.getBytes("UTF-8");
			byte[] strSign = DigestUtils.sha(strByte);
			String sign = bytesToHexString(strSign);
			String urlPara = String.format("|%s|%s|%s|%s|%s|%s", dbId, userName, appId, sign, timestamp, dk);
			urlPara=java.net.URLEncoder.encode(urlPara,"UTF-8");
			url = K3url+"/k3cloud/html5/index.aspx?ud=" + urlPara;// Html5入口链接
		}
        return url;
	}
	
	private static String bytesToHexString(byte[] src){      
        StringBuilder stringBuilder = new StringBuilder();      
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }      
        return stringBuilder.toString();
    }
	
	public static boolean checkK3Account(String k3account, String k3password) throws NumberFormatException, Exception {
		List<BO> list = SDK.getBOAPI().query(SynchronousK3Constant.TAB_JCXX_K3XRDZPZ_M).list();
		if(list != null && !list.isEmpty()) {
			BO bo = list.get(0);
			String dbId = bo.getString("DBID");
			String url = bo.getString("URL");
			String dk = bo.getString("DK"); 
			K3CloudApiClient client = new K3CloudApiClient(url);
			boolean tf = client.login(dbId, k3account, k3password, Integer.valueOf(dk));
			if(tf) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static void updateK3Account(String k3account, String k3password) {
		K3Web web = new K3Web();
		UserContext userContext = web.getContext();
		String userId = userContext.getUID();
		String id = DBSql.getString("select ID from "+K3Constant.TAB_JCXX_K3ACCOUNT+" where USERID = '"+userId+"'", "ID");
		if("".equals(id)) {
			BO bo = new BO();
			bo.set("USERID", userId);
			bo.set("K3ACCOUNT", k3account);
			bo.set("K3PASSWORD", k3password);
			SDK.getBOAPI().createDataBO(K3Constant.TAB_JCXX_K3ACCOUNT, bo, userContext);
		}else {
			DBSql.update("update "+K3Constant.TAB_JCXX_K3ACCOUNT+" set K3ACCOUNT = '"+k3account+"', K3PASSWORD = '"+k3password+"' where USERID = '"+userId+"'");
		}
	}
	
	public static BO loadK3Account() {
		BO data = new BO();
		K3Web web = new K3Web();
		String userId = web.getUid();
		List<BO> list = SDK.getBOAPI().query(K3Constant.TAB_JCXX_K3ACCOUNT).addQuery("USERID = ", userId).list();
		if(list != null && !list.isEmpty()) {
			BO bo = list.get(0);
			data.set("K3ACCOUNT", userId);
			data.set("K3PASSWORD", bo.getString("K3PASSWORD"));
		}else {
			data.set("K3ACCOUNT", userId);
		}
		return data;
	}
}
