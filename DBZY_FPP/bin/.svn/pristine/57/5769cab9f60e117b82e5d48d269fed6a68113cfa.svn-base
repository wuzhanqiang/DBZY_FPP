/*
 * 创建人：李鑫
 * 创建日期：2018-05-15
 */
package com.nepharm.apps.fpp.plugin;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.apps.listener.PluginListener;
import com.actionsoft.apps.resource.AppContext;
import com.actionsoft.apps.resource.plugin.profile.AWSPluginProfile;
import com.actionsoft.apps.resource.plugin.profile.AtFormulaPluginProfile;
import com.nepharm5.apps.fpp.formula.getBMJLByFqr;
import com.nepharm5.apps.fpp.formula.getJLZJByFqr;
import com.nepharm5.apps.fpp.formula.getUserByRole;
import com.nepharm5.apps.fpp.formula.getZjlByFqr;

public class RegisteredPlugins implements PluginListener {

	public RegisteredPlugins() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AWSPluginProfile> register(AppContext arg0) {
		//李鑫增加的内容↓
		List<AWSPluginProfile> list = new ArrayList<AWSPluginProfile>();
		list.add(new AtFormulaPluginProfile("字符串", "@getZjlByFqr(*str)", getZjlByFqr.class.getName(), "发起人为经理级时获取总经理帐号", "发起人为经理级时获取总经理帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getUserByRole(*str)", getUserByRole.class.getName(), "根据传入的角色获取隶属该角色的帐号", "根据传入的角色获取隶属该角色的帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getJLZJByFqr(*str)", getJLZJByFqr.class.getName(), "获取经理总监级帐号", "获取经理总监级帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getBMJLByFqr(*str)", getBMJLByFqr.class.getName(), "根据发起人部门全路径获取部门经理", "根据发起人部门全路径获取部门经理"));
		//李鑫增加的内容↑
		return list;
	}

}
