package com.zyl.cloud.provider.service.mvc.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyl.cloud.base.api.provider.remote.ProviderFeignRemote;
import com.zyl.cloud.base.dto.LogUser;
import com.zyl.cloud.base.pojo.App;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.base.pojo.User;
import com.zyl.cloud.provider.service.mvc.service.service.IAppService;
import com.zyl.cloud.provider.service.mvc.service.service.IRolePrivilegeService;
import com.zyl.cloud.provider.service.mvc.service.service.IUserRoleService;
import com.zyl.cloud.provider.service.mvc.service.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

// remote里面的FeignClient注解是为了让消费者发现并注入生产者的bean 而这里的RestController是为了在自身项目中创建bean
// 所以不能加@RequestMapping 而且必须用RestController(不太懂)
@RestController
public class FeignController implements ProviderFeignRemote {

	@Autowired
	private IAppService appService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;

	@Override
	public List<App> listIcons(Long id) {
		QueryWrapper<App> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bind_user", id);
		return appService.list(queryWrapper);
	}

	@Override
	public LogUser getLogUser(User unAuthUser) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.setEntity(unAuthUser);
		User user = userService.getOne(queryWrapper);

		Long userId = user.getId();
		Set<Role> roles = userRoleService.listRolesByUserId(userId);

		LogUser logUser = new LogUser();
		BeanUtils.copyProperties(user, logUser);
		logUser.setRoles(roles);
		return logUser;
	}

	@Override
	public Set<String> findRoleByUserId(Long id) {
		return userRoleService.findRoleByUserId(id);
	}

	@Override
	public Set<String> findPermsByUserId(Long id) {
		return rolePrivilegeService.findPermsByUserId(id);
	}

}
