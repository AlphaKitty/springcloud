package com.zyl.cloud.provider.service.mvc.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.cloud.base.pojo.RolePrivilege;
import com.zyl.cloud.provider.service.mvc.service.mapper.RolePrivilegeMapper;
import com.zyl.cloud.provider.service.mvc.service.service.IRolePrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色-权限表 服务实现类
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Service
public class RolePrivilegeServiceImpl extends ServiceImpl<RolePrivilegeMapper, RolePrivilege> implements IRolePrivilegeService {

	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;

	@Override
	public Set<String> findPermsByUserId(Long id) {
		return rolePrivilegeMapper.findPermsByUserId(id);
	}

}
