package com.zyl.cloud.provider.service.mvc.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyl.cloud.base.pojo.RolePrivilege;

import java.util.Set;

/**
 * 角色-权限表 服务类
 *
 * @author 张代富
 * @since 2022-04-07
 */
public interface IRolePrivilegeService extends IService<RolePrivilege> {

	Set<String> findPermsByUserId(Long id);

}
