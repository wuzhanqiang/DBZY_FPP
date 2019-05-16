package com.nepharm.apps.fpp.plugin;

import java.util.ArrayList;
import java.util.List;

import com.actionsoft.apps.listener.PluginListener;
import com.actionsoft.apps.resource.AppContext;
import com.actionsoft.apps.resource.plugin.profile.AWSPluginProfile;
import com.actionsoft.apps.resource.plugin.profile.AtFormulaPluginProfile;
import com.actionsoft.apps.resource.plugin.profile.SkinsPluginProfile;
import com.nepharm.apps.fpp.portal.controller.PortalSkins;
import com.nepharm5.apps.fpp.formula.GetSsdwByQlj;
import com.nepharm5.apps.fpp.formula.GetWorkflowUUIDByWFId;
import com.nepharm5.apps.fpp.formula.getBMJLByFqr;
import com.nepharm5.apps.fpp.formula.getBMLX;
import com.nepharm5.apps.fpp.formula.getIDAndXmByRole;
import com.nepharm5.apps.fpp.formula.getJLZJByFqr;
import com.nepharm5.apps.fpp.formula.getUserByRole;
import com.nepharm5.apps.fpp.formula.getZjlByFqr;
import com.nepharm5.apps.fpp.formula.getZjlnameByFqr;
import com.nepharm5.apps.fpp.formula.managerbydepartmentid;
import com.nepharm5.apps.fpp.formula.pdZjlSfFZXC;
import com.nepharm5.apps.fpp.nepg.rule.ManagerByDepartmentId;
import com.nepharm5.apps.fpp.nepg.rule.managerwithrolenamebydepartmentid;

public class FppPlugins implements PluginListener {

	public FppPlugins(){}
	@Override
	public List<AWSPluginProfile> register(AppContext context) {
		List<AWSPluginProfile> list = new ArrayList<AWSPluginProfile>();
		// 注册门户主题风格
        list.add(new SkinsPluginProfile(PortalSkins.class.getName(), false));
	    // 注册@公式
	    list.add(new AtFormulaPluginProfile("字符串", "@getUserInfo(*uid,*mc)", FindUserInfoExpression.class.getName(), "获取当前人的岗位、公司信息", "获取指定人的岗位|公司信息"));
	    list.add(new AtFormulaPluginProfile("字符串", "@managerwithrolenamebydepartmentid(*str)", managerwithrolenamebydepartmentid.class.getName(), "根据部门id获取拥有指定角色的并且是管理者的人员的账号", "根据部门id获取拥有指定角色的并且是管理者的人员的账号，如果有多个的话，返回第一个找到的，如果没有找到，返回[未找到]"));
	    list.add(new AtFormulaPluginProfile("字符串", "@getZjlByFqr(*str)", getZjlByFqr.class.getName(), "发起人为经理级时获取总经理帐号", "发起人为经理级时获取总经理帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getUserByRole(*str,*str)", getUserByRole.class.getName(), "根据传入的角色获取隶属该角色的帐号", "根据传入的角色获取隶属该角色的帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getJLZJByFqr(*str)", getJLZJByFqr.class.getName(), "获取经理总监级帐号", "获取经理总监级帐号"));
		list.add(new AtFormulaPluginProfile("字符串", "@getBMJLByFqr(*str)", getBMJLByFqr.class.getName(), "根据发起人部门全路径获取部门经理", "根据发起人部门全路径获取部门经理"));
		list.add(new AtFormulaPluginProfile("字符串", "@getIDAndXmByRole(*str)", getIDAndXmByRole.class.getName(), "根据角色获取用户的帐号和姓名", "根据角色获取用户的帐号和姓名"));
		list.add(new AtFormulaPluginProfile("字符串", "@getBMLX(*str)", getBMLX.class.getName(), "根据发起人部门获取部门类型", "根据发起人部门获取部门类型"));
		list.add(new AtFormulaPluginProfile("字符串", "@GetWorkflowUUIDByWFId(*str)", GetWorkflowUUIDByWFId.class.getName(), "根据流程模型id获取流程对应的UUID", "根据流程模型id获取流程对应的UUID"));
		list.add(new AtFormulaPluginProfile("字符串", "@pdZjlSfFZXC(*str)", pdZjlSfFZXC.class.getName(), "根据传入的帐号判断此账号是否公司副总裁", "根据传入的帐号判断此账号是否公司副总裁"));
		list.add(new AtFormulaPluginProfile("字符串", "@getZjlnameByFqr(*str)", getZjlnameByFqr.class.getName(), "获取总经理姓名", "获取总经理姓名"));
		list.add(new AtFormulaPluginProfile("字符串", "@managerbydepartmentid(*str)", managerbydepartmentid.class.getName(), "根据部门id获取该部门的管理者账号，如果没有找到的话，返回[未找到]", "根据部门id获取该部门的管理者账号，如果没有找到的话，返回[未找到]"));
		list.add(new AtFormulaPluginProfile("字符串", "@GetSsdwByQlj(*str)", GetSsdwByQlj.class.getName(), "根据部门全路径,取所属单位", "根据部门全路径,取所属单位"));
		return list;
	}

}
