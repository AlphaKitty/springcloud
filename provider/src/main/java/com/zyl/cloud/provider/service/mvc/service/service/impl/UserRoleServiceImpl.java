package com.zyl.cloud.provider.service.mvc.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.base.pojo.UserRole;
import com.zyl.cloud.provider.service.mvc.service.mapper.UserRoleMapper;
import com.zyl.cloud.provider.service.mvc.service.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户-角色表 服务实现类
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public Set<Role> listRolesByUserId(Long userId) {
		return userRoleMapper.loginByUserParam(userId);
	}

	@Override
	public Set<String> findRoleByUserId(Long id) {
		return userRoleMapper.findRoleByUserId(id);
	}

}
