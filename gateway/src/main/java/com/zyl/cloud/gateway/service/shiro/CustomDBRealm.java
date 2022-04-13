package com.zyl.cloud.gateway.service.shiro;

import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.dto.LogUser;
import com.zyl.cloud.base.pojo.User;
import com.zyl.cloud.base.util.common.UserUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomDBRealm extends AuthorizingRealm {

	@Autowired
	private ProviderFeignRemote providerFeignRemote;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("无法获取授权用户");
		}
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(providerFeignRemote.findRoleByUserId(user.getId()));
		info.setStringPermissions(providerFeignRemote.findPermsByUserId(user.getId()));
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取登录凭证 分为name/phone/email三种
		String login = token.getPrincipal().toString();
		User unAuthUser = checkLoginType(login);
		//查询用户名称
		LogUser user = providerFeignRemote.getLogUser(unAuthUser);
		if (null == user) {
			return null;
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	private User checkLoginType(String login) {
		// 如果是邮箱用邮箱登录 如果是手机号用手机号登录
		User user = new User();
		if (UserUtil.isEmail(login)) {
			user.setEmail(login);
		} else if (UserUtil.isPhone(login)) {
			user.setPhone(login);
		} else {
			user.setName(login);
		}
		return user;
	}

}
