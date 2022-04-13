package com.zyl.cloud.provider.service.mvc.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyl.cloud.base.pojo.Role;
import com.zyl.cloud.base.pojo.UserRole;

import java.util.Set;

/**
 * 用户-角色表 服务类
 *
 * @author 张代富
 * @since 2022-04-07
 */
public interface IUserRoleService extends IService<UserRole> {

	Set<Role> listRolesByUserId(Long userId);

	Set<String> findRoleByUserId(Long id);

}
