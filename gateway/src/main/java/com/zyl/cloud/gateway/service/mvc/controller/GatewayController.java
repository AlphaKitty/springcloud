package com.zyl.cloud.gateway.service.mvc.controller;

import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.pojo.User;
import com.zyl.cloud.base.util.common.Response;
import com.zyl.cloud.base.util.common.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/gateway")
public class GatewayController {

	@Autowired
	private ProviderFeignRemote providerFeignRemote;

	@PostMapping("/user/login")
	public Response login(@RequestBody Map<String, Object> map) {
		Subject subject = SecurityUtils.getSubject();
		String name = (String) map.get("name");
		String password = (String) map.get("password");
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);

		try {
			//进行验证，这里可以捕获异常，然后返回对应信息
			subject.login(token);
			token.setRememberMe(true);
			String authorization = (String) subject.getSession().getId();
			return ResponseUtil.success("登陆成功", authorization);
		} catch (UnknownAccountException e) {
			return ResponseUtil.error("用户名不存在！", "");
		} catch (AuthenticationException e) {
			return ResponseUtil.error("账号或密码错误！", "");
		} catch (AuthorizationException e) {
			return ResponseUtil.error("没有权限！", "");
		} catch (Exception e) {
			return ResponseUtil.error("其他错误！", "");
		}

	}

	@PostMapping("/icon/list")
	@RequiresRoles(logical = Logical.AND, value = {"role1"})
	@RequiresPermissions(logical = Logical.AND, value = {"coupon:home1"})
	public Response listIcons() {
		Subject s = SecurityUtils.getSubject();
		User user = (User) s.getPrincipal();
		return ResponseUtil.success(providerFeignRemote.listIcons(user.getId()));
	}

}
