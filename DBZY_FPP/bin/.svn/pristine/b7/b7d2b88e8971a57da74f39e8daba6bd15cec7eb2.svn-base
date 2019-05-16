package com.nepharm.apps.fpp.is.common.util;

import java.util.ArrayList;
import java.util.List;

public class IsUtil {
	
	//内网IP集合
	public static List<String> getLANIp() {
		List<String> list = new ArrayList();
		list.add("10");
		list.add("192.168");
		for(int i=16; i<=31; i++) {
			list.add("172."+i);
		}
		list.add("127");
		list.add("0");
		return list;
	}
	
	//检查是否内外网IP
	public static boolean checkIpState(List<String> lanList, String clientIp) {
		for(String LanIp : lanList) {
			if(clientIp.startsWith(LanIp)) {
				return true;
			}
		}
		return false;
	}
}
